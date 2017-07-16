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
import nightkosh.gravestone.tileentity.TileEntityGraveStone;
import nightkosh.gravestone_extended.config.ExtendedConfig;
import nightkosh.gravestone_extended.core.*;
import nightkosh.gravestone_extended.core.commands.ExtendedCommands;
import nightkosh.gravestone_extended.core.compatibility.Compatibility;
import nightkosh.gravestone_extended.core.event.GSEventsHandler;
import nightkosh.gravestone_extended.core.event.TickEventHandler;
import nightkosh.gravestone_extended.core.proxy.CommonProxy;
import nightkosh.gravestone_extended.helper.FogHandler;
import nightkosh.gravestone_extended.helper.GraveGenerationHelper;
import nightkosh.gravestone_extended.helper.GraveSpawnerHelper;
import nightkosh.gravestone_extended.structures.village.VillagersHandler;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
@Mod(modid = ModInfo.ID, name = ModInfo.NAME, version = ModInfo.VERSION, dependencies = "required-after:" + nightkosh.gravestone.api.ModInfo.ID + "@[1.0.5,);")
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

        Tabs.registration();

        GSBlock.registration();

        GSItem.registration();

        TileEntity.registration();
    }

    @Mod.EventHandler
    public void load(FMLInitializationEvent event) {
        // register death event
        MinecraftForge.EVENT_BUS.register(new GSEventsHandler());
        FMLCommonHandler.instance().bus().register(new TickEventHandler());
        proxy.registerHandlers();

        // reciepes registration
        Recipes.registration();

        // register structures
        Structures.registration();

        // register entities
        Entity.registration();

        Potion.init();

        NetworkRegistry.INSTANCE.registerGuiHandler(this, new GuiHandler());

        proxy.registerRenderers();

        VillagersHandler.registerVillagers();

        proxy.registerBlocksModels();
        proxy.registerItemsModels();
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        Compatibility.getInstance().checkMods();

        GraveGenerationHelper.addMobsItemsHandlers();

        GraveSpawnerHelper.setGraveSpawnerHelper();

        TileEntityGraveStone.fogHandler = new FogHandler();
    }

    @Mod.EventHandler
    public void serverStarting(FMLServerStartingEvent event) {
        ExtendedCommands.getInstance(event);
    }
}