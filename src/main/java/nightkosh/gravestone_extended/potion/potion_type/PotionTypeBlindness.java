package nightkosh.gravestone_extended.potion.potion_type;

import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.PotionType;
import nightkosh.gravestone_extended.core.ModInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class PotionTypeBlindness extends PotionType {

    public PotionTypeBlindness(PotionEffect... effects) {
        super(effects);
        this.setRegistryName(ModInfo.ID, "gs_blindness_type");
    }

    @Override
    public List<PotionEffect> getEffects() {
        List<PotionEffect> effectList = new ArrayList<>(1);
        effectList.add(new PotionEffect(Potion.getPotionFromResourceLocation("blindness"), 600));
        return effectList;
    }
}
