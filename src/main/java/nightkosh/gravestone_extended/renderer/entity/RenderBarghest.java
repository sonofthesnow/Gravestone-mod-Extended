package nightkosh.gravestone_extended.renderer.entity;

import net.minecraft.client.renderer.entity.RenderManager;
import nightkosh.gravestone_extended.models.entity.ModelBarghest;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class RenderBarghest extends RenderUndeadDog {

    public RenderBarghest(RenderManager renderManager) {
        super(renderManager, new ModelBarghest());
//        this.addLayer(new LayerWolfCollar(this));
    }
}
