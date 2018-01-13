package nightkosh.gravestone_extended.renderer.entity;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import nightkosh.gravestone_extended.entity.monster.pet.EntityUndeadDog;
import nightkosh.gravestone_extended.models.entity.ModelUndeadDog;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
@SideOnly(Side.CLIENT)
public class RenderUndeadDog extends RenderLiving {

    public RenderUndeadDog(RenderManager renderManager) {
        this(renderManager, new ModelUndeadDog());
    }

    public RenderUndeadDog(RenderManager renderManager, ModelUndeadDog model) {
        super(renderManager, model, 0.5F);
    }

    protected float getTailRotation(EntityUndeadDog undeadDog, float par2) {
        return undeadDog.getTailRotation();
    }

    @Override
    protected float handleRotationFloat(EntityLivingBase entityLiving, float par2) {
        return this.getTailRotation((EntityUndeadDog) entityLiving, par2);
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity entity) {
        return ((EntityUndeadDog) entity).getTexture();
    }
}