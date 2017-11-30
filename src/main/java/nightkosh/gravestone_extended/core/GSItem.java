package nightkosh.gravestone_extended.core;

import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.IForgeRegistry;
import nightkosh.gravestone_extended.item.ItemChisel;
import nightkosh.gravestone_extended.item.ItemGSMonsterPlacer;
import nightkosh.gravestone_extended.item.tools.ItemBoneSword;
import nightkosh.gravestone_extended.item.tools.ItemDiamondBoneSword;
import nightkosh.gravestone_extended.item.tools.ItemGoldenBoneSword;
import nightkosh.gravestone_extended.item.tools.ItemIronBoneSword;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
@GameRegistry.ObjectHolder(ModInfo.ID)
public class GSItem {

    public static final Item CHISEL = new ItemChisel();
    public static final Item SPAWN_EGG = new ItemGSMonsterPlacer();
    public static final Item BONE_SWORD = new ItemBoneSword();
    public static final Item BONE_SWORD_IRON = new ItemIronBoneSword();
    public static final Item BONE_SWORD_GOLDEN = new ItemGoldenBoneSword();
    public static final Item BONE_SWORD_DIAMOND = new ItemDiamondBoneSword();

    @Mod.EventBusSubscriber(modid = ModInfo.ID)
    public static class RegistrationHandler {

        @SubscribeEvent
        public static void registerItems(final RegistryEvent.Register<Item> event) {
            final IForgeRegistry<Item> registry = event.getRegistry();
            registry.registerAll(CHISEL, SPAWN_EGG);
            registry.registerAll(BONE_SWORD, BONE_SWORD_IRON, BONE_SWORD_GOLDEN, BONE_SWORD_DIAMOND);
//            if (Loader.isModLoaded("forestry")) {
//                registry.registerAll(CompatibilityForestry.backpackItemT1, CompatibilityForestry.backpackItemT2);
//            }
        }
    }
}
