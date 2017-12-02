package nightkosh.gravestone_extended.core;

import net.minecraft.init.Items;
import net.minecraft.init.PotionTypes;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionHelper;
import net.minecraft.potion.PotionType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import nightkosh.gravestone_extended.potion.CursePotion;
import nightkosh.gravestone_extended.potion.PotionPurification;
import nightkosh.gravestone_extended.potion.potion_type.PotionTypePurification;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
@GameRegistry.ObjectHolder(ModInfo.ID)
@Mod.EventBusSubscriber
public class GSPotion {

    public static final CursePotion CURSE = new CursePotion();
    public static final PotionPurification PURIFICATION = new PotionPurification();

    public static final PotionTypePurification PURIFICATION_TYPE = new PotionTypePurification();

    @SubscribeEvent
    public static void registerPotions(final RegistryEvent.Register<Potion> event) {
        event.getRegistry().registerAll(CURSE, PURIFICATION);
    }

    @SubscribeEvent
    public static void registerPotionTypes(final RegistryEvent.Register<PotionType> event) {
        event.getRegistry().registerAll(PURIFICATION_TYPE);
        PotionHelper.addMix(PotionTypes.AWKWARD, Items.ENDER_PEARL, PURIFICATION_TYPE);
    }
}
