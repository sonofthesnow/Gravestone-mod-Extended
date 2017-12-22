package nightkosh.gravestone_extended.core;

import net.minecraft.init.Items;
import net.minecraft.init.PotionTypes;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionHelper;
import net.minecraft.potion.PotionType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import nightkosh.gravestone_extended.item.ItemFish;
import nightkosh.gravestone_extended.potion.PotionBoneSkin;
import nightkosh.gravestone_extended.potion.PotionCurse;
import nightkosh.gravestone_extended.potion.PotionPurification;
import nightkosh.gravestone_extended.potion.PotionRust;
import nightkosh.gravestone_extended.potion.potion_type.PotionTypeBoneSkin;
import nightkosh.gravestone_extended.potion.potion_type.PotionTypePurification;
import nightkosh.gravestone_extended.potion.potion_type.PotionTypeRust;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
@GameRegistry.ObjectHolder(ModInfo.ID)
@Mod.EventBusSubscriber
public class GSPotion {

    public static final Potion CURSE = new PotionCurse();
    public static final Potion PURIFICATION = new PotionPurification();
    public static final Potion RUST = new PotionRust();
    public static final Potion BONE_SKIN = new PotionBoneSkin();

    public static final PotionType PURIFICATION_TYPE = new PotionTypePurification();
    public static final PotionType RUST_TYPE = new PotionTypeRust();
    public static final PotionType BONE_SKIN_TYPE = new PotionTypeBoneSkin();

    @SubscribeEvent
    public static void registerPotions(final RegistryEvent.Register<Potion> event) {
        event.getRegistry().registerAll(CURSE, PURIFICATION, RUST, BONE_SKIN);
    }

    @SubscribeEvent
    public static void registerPotionTypes(final RegistryEvent.Register<PotionType> event) {
        event.getRegistry().registerAll(PURIFICATION_TYPE, RUST_TYPE, BONE_SKIN_TYPE);
        PotionHelper.addMix(PotionTypes.AWKWARD, Items.ENDER_PEARL, PURIFICATION_TYPE);
        PotionHelper.addMix(PotionTypes.AWKWARD, GSItem.TOXIC_SLIME, RUST_TYPE);
        PotionHelper.addMix(PotionTypes.AWKWARD, Ingredient.fromStacks(new ItemStack(GSItem.FISH, 1, ItemFish.EnumFishType.BONE_FISH.ordinal())), BONE_SKIN_TYPE);

        PotionHelper.addMix(PotionTypes.AWKWARD, Ingredient.fromStacks(new ItemStack(Items.FISH, 1, 2)), PotionType.getPotionTypeForName("luck"));
    }
}
