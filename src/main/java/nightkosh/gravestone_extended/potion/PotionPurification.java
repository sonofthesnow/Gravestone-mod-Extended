package nightkosh.gravestone_extended.potion;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import nightkosh.gravestone_extended.core.ModInfo;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class PotionPurification extends Potion {

    public PotionPurification() {
        super(false, 0xffffff);
        this.setRegistryName(ModInfo.ID, "gs_purification_potion");
        this.setPotionName("potion.purification.title");
    }

    @Override
    public void affectEntity(@Nullable Entity source, @Nullable Entity indirectSource, EntityLivingBase entity, int amplifier, double health) {
        Collection<PotionEffect> potions = entity.getActivePotionEffects();
        List<Potion> potionsToRemove = new ArrayList<>();
        for (PotionEffect potion : potions) {
            if (potion.getPotion().isBadEffect()) {
                potionsToRemove.add(potion.getPotion());
            }
        }
        for (Potion potion : potionsToRemove) {
            entity.removePotionEffect(potion);
        }

        if (entity.isBurning()) {
            entity.extinguish();
        }
    }

    public boolean isInstant() {
        return true;
    }

    public boolean isReady(int duration, int amplifier) {
        return duration >= 1;
    }

    @Override
    public boolean hasStatusIcon() {
        return false;
    }
}
