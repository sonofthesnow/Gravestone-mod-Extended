package nightkosh.gravestone_extended.renderer.entity;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import nightkosh.gravestone_extended.entity.monster.pet.EntityUndeadDog;
import nightkosh.gravestone_extended.models.entity.ModelUndeadDog;

import javax.annotation.Nullable;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
@SideOnly(Side.CLIENT)
public class RenderUndeadDog<T extends EntityUndeadDog> extends RenderLiving<EntityUndeadDog> {

    public RenderUndeadDog(RenderManager renderManager) {
        this(renderManager, new ModelUndeadDog());
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(EntityUndeadDog entity) {
        return entity.getTexture();
    }

    public RenderUndeadDog(RenderManager renderManager, ModelUndeadDog model) {
        super(renderManager, model, 0.5F);
    }

    @Override
    protected float handleRotationFloat(EntityUndeadDog undeadDog, float par2) {
        return undeadDog.getTailRotation();
    }
}