package nightkosh.gravestone_extended.renderer.entity.layers;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import nightkosh.gravestone_extended.core.Resources;
import nightkosh.gravestone_extended.entity.monster.EntityBarghest;
import nightkosh.gravestone_extended.renderer.entity.RenderBarghest;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class LayerBarghestEyes implements LayerRenderer<EntityBarghest> {
    private final RenderBarghest renderer;

    public LayerBarghestEyes(RenderBarghest phantomDiverRenderer) {
        this.renderer = phantomDiverRenderer;
    }

    @Override
    public void doRenderLayer(EntityBarghest entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        this.renderer.bindTexture(Resources.BARGHEST_EYES);
        GlStateManager.enableBlend();
        GlStateManager.disableAlpha();
        GlStateManager.blendFunc(GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ONE);
        GlStateManager.disableLighting();
        GlStateManager.depthMask(!entity.isInvisible());

        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 61680, 0);
        GlStateManager.enableLighting();
        GlStateManager.color(1, 1, 1, 1);
        Minecraft.getMinecraft().entityRenderer.setupFogColor(true);
        this.renderer.getMainModel().render(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
        Minecraft.getMinecraft().entityRenderer.setupFogColor(false);
        this.renderer.setLightmap(entity);
        GlStateManager.depthMask(true);
        GlStateManager.disableBlend();
        GlStateManager.enableAlpha();
    }

    @Override
    public boolean shouldCombineTextures() {
        return false;
    }
}
