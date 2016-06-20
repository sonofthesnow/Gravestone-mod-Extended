package nightkosh.gravestone_extended;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import nightkosh.gravestone_extended.config.ExtendedConfig;
import nightkosh.gravestone_extended.core.*;
import nightkosh.gravestone_extended.core.commands.ExtendedCommands;
import nightkosh.gravestone_extended.core.compatibility.Compatibility;
import nightkosh.gravestone_extended.core.event.GSEventsHandler;
import nightkosh.gravestone_extended.core.event.GSTickEventHandler;
import nightkosh.gravestone_extended.core.proxy.CommonProxy;
import nightkosh.gravestone_extended.helper.GraveGenerationHelper;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
@Mod(modid = ModInfo.ID, name = ModInfo.NAME, version = ModInfo.VERSION)
public class ModGravestoneExtended {

    @Instance(ModInfo.ID)
    public static ModGravestoneExtended instance;
    @SidedProxy(clientSide = "nightkosh.gravestone_extended.core.proxy.ClientProxy", serverSide = "nightkosh.gravestone_extended.core.proxy.CommonProxy")
    public static CommonProxy proxy;


    public ModGravestoneExtended() {
        instance = this;
    }

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        ExtendedConfig.getInstance(event.getModConfigurationDirectory().getAbsolutePath() + "/GraveStoneMod/", "GraveStone.cfg");
        Structures.preInit();

        MessageHandler.init();

    }

    @Mod.EventHandler
    public void load(FMLInitializationEvent event) {
        // register death event
        MinecraftForge.EVENT_BUS.register(new GSEventsHandler());
        FMLCommonHandler.instance().bus().register(new GSTickEventHandler());
        proxy.registerHandlers();

        // tabs
        Tabs.registration();

        // blocks registration
        Block.registration();

        // items registration
        ExtendedItem.registration();

        // reciepes registration
        Recipes.registration();

        // tileEntities registration
        TileEntity.registration();

        // register structures
        Structures.getInstance();

        // register entities
        Entity.getInstance();

        Potion.init();

        NetworkRegistry.INSTANCE.registerGuiHandler(this, new GuiHandler());

        proxy.registerRenderers();
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        Compatibility.getInstance().checkMods();

        GraveGenerationHelper.addMobsItemsHandlers();
    }

    @Mod.EventHandler
    public void serverStarting(FMLServerStartingEvent event) {
        ExtendedCommands.getInstance(event);
    }
}