package nightkosh.gravestone_extended.core;

import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.IForgeRegistry;
import nightkosh.gravestone_extended.core.compatibility.Compatibility;
import nightkosh.gravestone_extended.core.compatibility.forestry.CompatibilityForestry;
import nightkosh.gravestone_extended.item.*;
import nightkosh.gravestone_extended.item.armor.ItemDivingHelmet;
import nightkosh.gravestone_extended.item.tools.*;
import nightkosh.gravestone_extended.item.weapon.*;

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
    public static final Item BONE_SHIELD = new ItemBoneShield();

    public static final Item BONE_FISHING_POLE = new ItemBoneFishingPole();
    public static final Item BONE_HOE = new ItemBoneHoe();
    public static final Item BONE_HOE_IRON = new ItemIronBoneHoe();
    public static final Item BONE_HOE_GOLDEN = new ItemGoldenBoneHoe();
    public static final Item BONE_HOE_DIAMOND = new ItemDiamondBoneHoe();

    public static final Item DIVING_HELMET = new ItemDivingHelmet();

    public static final Item IMP_SKULL = new ItemImpSkull();

    public static final Item TOXIC_SLIME = new ItemToxicSlime();
    public static final Item BAT_WING = new ItemBatWing();
    public static final Item FISH = new ItemFish();
    public static final Item ENCHANTED_SKULL = new ItemEnchantedSkull();

    @Mod.EventBusSubscriber(modid = ModInfo.ID)
    public static class RegistrationHandler {

        @SubscribeEvent
        public static void registerItems(final RegistryEvent.Register<Item> event) {
            final IForgeRegistry<Item> registry = event.getRegistry();
            registry.registerAll(BONE_SWORD, BONE_SWORD_IRON, BONE_SWORD_GOLDEN, BONE_SWORD_DIAMOND, BONE_SHIELD);
            registry.registerAll(CHISEL, BONE_FISHING_POLE,
                    BONE_HOE, BONE_HOE_IRON, BONE_HOE_GOLDEN, BONE_HOE_DIAMOND);
            registry.registerAll(DIVING_HELMET, IMP_SKULL);
            registry.registerAll(TOXIC_SLIME, BAT_WING, FISH);
            registry.registerAll(ENCHANTED_SKULL, SPAWN_EGG);
            if (Loader.isModLoaded(Compatibility.FORESTRY_ID)) {
                CompatibilityForestry.addBackpack(registry);
            }
        }
    }
}
