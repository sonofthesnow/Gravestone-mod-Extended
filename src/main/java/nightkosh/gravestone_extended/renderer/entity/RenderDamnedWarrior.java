package nightkosh.gravestone_extended.renderer.entity;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.ResourceLocation;
import nightkosh.gravestone_extended.core.Resources;
import nightkosh.gravestone_extended.models.entity.ModelDamnedWarrior;
import nightkosh.gravestone_extended.particle.EntityBigFlameFX;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class RenderDamnedWarrior extends RenderLiving {

    private int ticks = 0;

    public RenderDamnedWarrior(RenderManager renderManager) {
        super(renderManager, new ModelDamnedWarrior(), 0.5F);
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity entity) {
        return Resources.DAMNED_WARRIOR;
    }

    @Override
    public void doRender(EntityLiving entity, double x, double y, double z, float entityYaw, float partialTicks) {
        super.doRender(entity, x, y, z, entityYaw, partialTicks);

        if (ticks > 40) {
            EntityFX chestFlame = new EntityBigFlameFX(entity.getEntityWorld(), entity.posX, entity.posY + 2.1, entity.posZ, 3, 0.1F, 0.85F, 0.1F);
            Minecraft.getMinecraft().effectRenderer.addEffect(chestFlame);


//            double dx = Math.toRadians(10) * 6;

//            EntityFX leftEyeFlame = new EntityBigFlameFX(entity.getEntityWorld(), entity.posX, entity.posY + 3, entity.posZ + 0.3F, 0.7F, 0.1F, 0.85F, 0.1F);
//            Minecraft.getMinecraft().effectRenderer.addEffect(leftEyeFlame);
//
//            EntityFX rightEyeFlame = new EntityGreenFlameFX(entity.getEntityWorld(), entity.posX, entity.posY + 2.25, entity.posZ, 0.1F, 0.85F, 0.1F);
//            Minecraft.getMinecraft().effectRenderer.addEffect(rightEyeFlame);
            ticks = 0;
        } else {
            ticks++;
        }
    }
}