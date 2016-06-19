package nightkosh.gravestone_extended.models.block;

import net.minecraft.util.ResourceLocation;
import nightkosh.gravestone.models.block.ModelGraveStone;
import nightkosh.gravestone_extended.block.enums.EnumHangedMobs;
import nightkosh.gravestone_extended.block.enums.EnumMemorials;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public abstract class ModelMemorial extends ModelGraveStone {

    public void setPedestalTexture(ResourceLocation texture) {
    }

    public void customRender(ResourceLocation texture, boolean enchanted) {
        if (enchanted) {
            renderEnchanted();
        } else {
            renderAll();
        }
    }

    public void customRender(EnumMemorials memorialType, EnumHangedMobs mob, int villagerProfession) {
    }
}
