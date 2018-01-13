package nightkosh.gravestone_extended.renderer.entity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import nightkosh.gravestone_extended.entity.monster.EntityBarghest;
import nightkosh.gravestone_extended.entity.monster.pet.EntityUndeadDog;
import nightkosh.gravestone_extended.models.entity.ModelBarghest;
import nightkosh.gravestone_extended.renderer.entity.layers.LayerBarghestEyes;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class RenderBarghest extends RenderUndeadDog<EntityBarghest> {

    public RenderBarghest(RenderManager renderManager) {
        super(renderManager, new ModelBarghest());
        this.addLayer(new LayerBarghestEyes(this));
    }

    @Override
    public void doRender(EntityUndeadDog entity, double x, double y, double z, float entityYaw, float partialTicks) {
        if (((EntityBarghest) entity).isBarghestInvisible()) {
            GlStateManager.color(1, 1, 1, 1);
            GlStateManager.enableNormalize();
            GlStateManager.enableBlend();
            GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
            super.doRender(entity, x, y, z, entityYaw, partialTicks);
            GlStateManager.disableBlend();
            GlStateManager.disableNormalize();
        } else {
            super.doRender(entity, x, y, z, entityYaw, partialTicks);
        }
    }

    @Override
    public ModelBase getMainModel() {
        return super.getMainModel();
    }
}
