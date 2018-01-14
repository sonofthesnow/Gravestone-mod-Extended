package nightkosh.gravestone_extended.renderer.entity;

import net.minecraft.client.model.ModelZombie;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import nightkosh.gravestone_extended.core.Resources;
import nightkosh.gravestone_extended.entity.monster.EntityMummy;
import nightkosh.gravestone_extended.renderer.entity.layers.LayerMummy;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class RendererMummy extends RenderBiped<EntityMummy> {

    public RendererMummy(RenderManager renderManager) {
        super(renderManager, new ModelZombie(0, true), 0.5F);

        this.addLayer(new LayerMummy(this));
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityMummy entity) {
        return Resources.MUMMY;
    }
}
