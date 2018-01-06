package nightkosh.gravestone_extended.renderer.entity;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.layers.LayerBipedArmor;
import net.minecraft.util.ResourceLocation;
import nightkosh.gravestone_extended.core.Resources;
import nightkosh.gravestone_extended.entity.monster.EntityPossessedArmor;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class RendererPossessedArmor extends RenderBiped<EntityPossessedArmor> {

    private int ticks = 0;
//    protected ModelBiped model;

    public RendererPossessedArmor(RenderManager renderManager) {
        super(renderManager, new ModelBiped(), 0.5F);
        LayerBipedArmor layerbipedarmor = new LayerBipedArmor(this);
        this.addLayer(layerbipedarmor);
//        model = (ModelBiped) this.getMainModel();
    }

//    private static double ddz = 0.45;
//    private static double ddx = 0.45;
//
//    @Override
//    public void doRender(EntityPossessedArmor entity, double x, double y, double z, float yaw, float partialTicks) {
//        super.doRender(entity, x, y, z, yaw, partialTicks);
//
//        if (ticks > 40) {
////            double dz = 0.3 * Math.cos(Math.toRadians(45) + model.bipedHead.rotateAngleX);
//            double dz = ddz * Math.sin(Math.toRadians(entity.getRotationYawHead() + entity.rotationYaw + 90));//0.3 * Math.cos(Math.toRadians(4model.bipedHead.rotateAngleX));
//            double dy = 0;//Math.sin(model.bipedHead.rotateAngleY);
////            double dx = 0.65 * Math.cos(Math.toRadians(45) + model.bipedHead.rotateAngleX) * dy;
//            double dx = ddx * Math.cos(Math.toRadians(entity.getRotationYawHead() + entity.rotationYaw + 90));//Math.toRadians
////            double dx = ddx * Math.cos(Math.toRadians(entity.getRotationYawHead()) + entity.rotationYaw);//Math.toRadians
//
//            Minecraft.getMinecraft().effectRenderer.addEffect(new EntityGreenFlameFX(entity.world, entity.posX + dx, entity.posY + entity.getEyeHeight() + dy, entity.posZ + dz, 0, 0, 0));
//
////            Minecraft.getMinecraft().effectRenderer.addEffect(new EntityGreenFlameFX(entity.world, entity.posX + dx, entity.posY + entity.getEyeHeight() + dy, entity.posZ + dz, 0, 0, 0));
//            ticks = 0;
//        } else {
//            ticks++;
//        }
//    }

    @Override
    protected ResourceLocation getEntityTexture(EntityPossessedArmor entity) {
        return Resources.POSSESSED_ARMOR;
    }
}
