package nightkosh.gravestone_extended.models.block;

import net.minecraft.util.ResourceLocation;
import nightkosh.gravestone.models.block.ModelGraveStone;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public abstract class ModelMemorial extends ModelGraveStone {

    public void customRender(ResourceLocation texture, boolean enchanted) {
        if (enchanted) {
            renderEnchanted();
        } else {
            renderAll();
        }
    }
}
