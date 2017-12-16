package nightkosh.gravestone_extended.core;

import net.minecraft.enchantment.Enchantment;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.IForgeRegistry;
import nightkosh.gravestone_extended.enchantment.*;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
@GameRegistry.ObjectHolder(ModInfo.ID)
public class GSEnchantment {

    public static final Enchantment VAMPIRIC_TOUCH = new EnchantmentVampiricTouch();
    public static final Enchantment POISONED_BLADE = new EnchantmentPoisonedBlade();
    public static final Enchantment WITHERED_BLADE = new EnchantmentWitheredBlade();
    public static final Enchantment SHADOW_OF_DEATH = new EnchantmentShadowOfDeath();
    public static final Enchantment NECROTIC_CORROSION = new EnchantmentNecroticCorrosion();
    public static final Enchantment PAIN_MIRROR = new EnchantmentPainMirror();
    public static final Enchantment BONE_RAIN = new EnchantmentBoneRain();

    @Mod.EventBusSubscriber(modid = ModInfo.ID)
    public static class RegistrationHandler {

        @SubscribeEvent
        public static void registerBlocks(final RegistryEvent.Register<Enchantment> event) {
            final IForgeRegistry<Enchantment> registry = event.getRegistry();
            registry.registerAll(VAMPIRIC_TOUCH, POISONED_BLADE, WITHERED_BLADE, SHADOW_OF_DEATH, NECROTIC_CORROSION);
            registry.registerAll(PAIN_MIRROR);
            registry.registerAll(BONE_RAIN);
        }
    }
}
