package nightkosh.gravestone_extended.renderer.entity.layers;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelSlime;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import nightkosh.gravestone_extended.entity.monster.EntityToxicSludge;
import nightkosh.gravestone_extended.renderer.entity.RendererToxicSludge;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class LayerToxicSludgeGel implements LayerRenderer<EntityToxicSludge> {
    private final RendererToxicSludge renderer;
    private final ModelBase slimeModel = new ModelSlime(0);

    public LayerToxicSludgeGel(RendererToxicSludge renderer) {
        this.renderer = renderer;
    }

    @Override
    public void doRenderLayer(EntityToxicSludge entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        if (!entity.isInvisible()) {
            this.slimeModel.setModelAttributes(this.renderer.getMainModel());
            this.slimeModel.render(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
        }
    }

    @Override
    public boolean shouldCombineTextures() {
        return false;
    }
}
