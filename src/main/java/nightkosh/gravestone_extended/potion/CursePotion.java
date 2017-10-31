package nightkosh.gravestone_extended.potion;

import nightkosh.gravestone_extended.core.ModInfo;
import nightkosh.gravestone_extended.core.Resources;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class CursePotion extends Potion {

    public CursePotion() {
        super(true, 0);
        this.setIconIndex(0, 0);
        this.setRegistryName(ModInfo.ID, "Curse");
        this.setPotionName("Curse");
//        this.setPotionName("effect." + this.getRegistryName().toString());
    }

    @Override
    public void performEffect(EntityLivingBase entity, int p_76394_2_) {

    }

    @SideOnly(Side.CLIENT)
    public int getStatusIconIndex() {
        Minecraft.getMinecraft().renderEngine.bindTexture(Resources.POTIONS);
        return super.getStatusIconIndex();
    }
}
