package nightkosh.gravestone_extended.potion;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import nightkosh.gravestone_extended.core.GSPotion;
import nightkosh.gravestone_extended.core.ModInfo;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class PotionBleeding extends PotionBase {

    public PotionBleeding() {
        super(true, 0xA2131F);
        this.setIconIndex(3, 0);
        this.setRegistryName(ModInfo.ID, "gs_bleeding_potion");
        this.setPotionName("potion.bleeding.title");
    }


    public static boolean hasPotion(EntityLivingBase entity) {
        if (entity != null) {
            for (PotionEffect potion : entity.getActivePotionEffects()) {
                if (Potion.getIdFromPotion(potion.getPotion()) == Potion.getIdFromPotion(GSPotion.BLEEDING)) {
                    return true;
                }
            }
        }
        return false;
    }
}
