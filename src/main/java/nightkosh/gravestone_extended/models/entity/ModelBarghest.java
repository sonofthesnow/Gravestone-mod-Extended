package nightkosh.gravestone_extended.models.entity;

import net.minecraft.entity.Entity;
import org.lwjgl.opengl.GL11;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class ModelBarghest extends ModelUndeadDog {

    @Override
    public void render(Entity entity, float par2, float par3, float par4, float par5, float par6, float par7) {
        GL11.glPushMatrix();
        GL11.glScaled(2, 2, 2);
        GL11.glTranslated(0, -0.7, 0);
        super.render(entity, par2, par3, par4, par5, par6, par7);
        GL11.glPopMatrix();
    }
}
