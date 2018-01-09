package nightkosh.gravestone_extended.models.entity;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class ModelPhantomDiver extends ModelBiped {

    public ModelRenderer helmetBase;
    public ModelRenderer rim;

    public ModelPhantomDiver() {
        super(0, 0, 64, 64);

        helmetBase = new ModelRenderer(this, 0, 32);
        helmetBase.addBox(-4.5F, -8.5F, -4.5F, 9, 9, 9, 0);
        helmetBase.setRotationPoint(0, 0, 0);

        rim = new ModelRenderer(this, 38, 6);
        rim.addBox(-4, -8, -6, 8, 8, 2, 0);
        rim.setRotationPoint(0, 0, 0);
    }

    @Override
    public void render(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        super.render(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
        helmetBase.render(scale);
        rim.render(scale);
    }

    public void renderHead(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entity);
        this.bipedHead.render(scale);
    }

    @Override
    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entity) {
        super.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor, entity);
        helmetBase.rotateAngleY = bipedHead.rotateAngleY;
        helmetBase.rotateAngleX = bipedHead.rotateAngleX;
        rim.rotateAngleY = bipedHead.rotateAngleY;
        rim.rotateAngleX = bipedHead.rotateAngleX;
    }
}
