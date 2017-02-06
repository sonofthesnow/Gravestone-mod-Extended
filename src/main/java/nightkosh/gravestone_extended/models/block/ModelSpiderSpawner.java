package nightkosh.gravestone_extended.models.block;

import net.minecraft.client.model.ModelRenderer;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class ModelSpiderSpawner extends ModelSpawnerBase {

    ModelRenderer bottomCube1;
    ModelRenderer bottomCube2;
    ModelRenderer middleCube1;
    ModelRenderer middleCube2;
    ModelRenderer web1;
    ModelRenderer web2;
    ModelRenderer web3;
    ModelRenderer web4;
    ModelRenderer web5;
    ModelRenderer web6;
    ModelRenderer web7;
    ModelRenderer web8;
    ModelRenderer web11;
    ModelRenderer web12;
    ModelRenderer web13;
    ModelRenderer web14;
    ModelRenderer web15;
    ModelRenderer web16;
    ModelRenderer web17;
    ModelRenderer web18;
    ModelRenderer web21;
    ModelRenderer web22;
    ModelRenderer web23;
    ModelRenderer web24;
    ModelRenderer web25;
    ModelRenderer web26;
    ModelRenderer web27;
    ModelRenderer web28;

    public ModelSpiderSpawner() {
        textureWidth = 64;
        textureHeight = 64;

        bottomCube1 = new ModelRenderer(this, 0, 0);
        bottomCube1.addBox(0, 0, 0, 8, 10, 8);
        bottomCube1.setRotationPoint(-4,  14, -4);
        setRotation(bottomCube1, 0, 0, 0);
        
        bottomCube2 = new ModelRenderer(this, 0, 0);
        bottomCube2.addBox(0, 0, 0, 8, 10, 8);
        bottomCube2.setRotationPoint(-5.5F, 14, 0);
        setRotation(bottomCube2, 0, 0.7853982F, 0);
        
        middleCube1 = new ModelRenderer(this, 33, 0);
        middleCube1.addBox(0, 0, 0, 6, 4, 6);
        middleCube1.setRotationPoint(-3, 10, -3);
        setRotation(middleCube1, 0, 0, 0);
        
        middleCube2 = new ModelRenderer(this, 33, 0);
        middleCube2.addBox(0, 0, 0, 6, 4, 6);
        middleCube2.setRotationPoint(-4, 10, 0);
        setRotation(middleCube2, 0, 0.7853982F, 0);
        
        web1 = new ModelRenderer(this, 0, 19);
        web1.addBox(0, 0, 0, 0, 10, 5);
        web1.setRotationPoint(4, 14, -4.3F);
        setRotation(web1, 0, 0.4363323F, 0);
        
        web2 = new ModelRenderer(this, 0, 19);
        web2.addBox(0, 0, 0, 0, 10, 5);
        web2.setRotationPoint(6.1F, 14, -0.1F);
        setRotation(web2, 0, -0.4363323F, 0);
        
        web3 = new ModelRenderer(this, 0, 19);
        web3.addBox(0, 0, 0, 0, 10, 5);
        web3.setRotationPoint(-5.7F, 14, -0.3F);
        setRotation(web3, 0, 0.3665191F, 0);
        
        web4 = new ModelRenderer(this, 0, 19);
        web4.addBox(0, 0, 0, 0, 10, 5);
        web4.setRotationPoint(-3.9F, 14, -4.4F);
        setRotation(web4, 0, -0.3839724F, 0);
        
        web5 = new ModelRenderer(this, 0, 19);
        web5.addBox(0, 0, 0, 0, 10, 5);
        web5.setRotationPoint(-0.2F, 14, -5.6F);
        setRotation(web5, 0, 1.256637F, 0);
        
        web6 = new ModelRenderer(this, 0, 19);
        web6.addBox(0, 0, 0, 0, 10, 5);
        web6.setRotationPoint(0.3F, 14, -5.6F);        
        setRotation(web6, 0, -1.256637F, 0);
        
        web7 = new ModelRenderer(this, 0, 19);
        web7.addBox(0, 0, 0, 0, 10, 5);
        web7.setRotationPoint(-4.2F,  14, 4.2F);        
        setRotation(web7, 0, 1.256637F, 0);
        
        web8 = new ModelRenderer(this, 0, 19);
        web8.addBox(0, 0, 0, 0, 10, 5);
        web8.setRotationPoint(4.5F, 14, 4.2F);        
        setRotation(web8, 0, -1.256637F, 0);
        
        web11 = new ModelRenderer(this, 11, 19);
        web11.addBox(0, 0, 0, 0, 4, 8);
        web11.setRotationPoint(0.5F, 21, -5.5F);
        setRotation(web11, 0, 0.7853982F, -0.7853982F);
        
        web12 = new ModelRenderer(this, 11, 19);
        web12.addBox(0, 0, 0, 0, 4, 8);
        web12.setRotationPoint(6, 21, 0);
        setRotation(web12, 0, -0.7853982F, -0.7853982F);
        
        web13 = new ModelRenderer(this, 11, 19);
        web13.addBox(0, 0, 0, 0, 4, 8);
        web13.setRotationPoint(-5.5F, 21, 0);
        setRotation(web13, 0, 2.356194F, -0.7853982F);
        
        web14 = new ModelRenderer(this, 11, 19);
        web14.addBox(0, 0, 0, 0, 4, 8);
        web14.setRotationPoint(0, 21, 6);
        setRotation(web14, 0, -2.356194F, -0.7853982F);
        
        web15 = new ModelRenderer(this, 11, 19);
        web15.addBox(0, 0, 0, 0, 4, 8);
        web15.setRotationPoint(4, 21, -4);
        setRotation(web15, 0, 0, -0.7853982F);
        
        web16 = new ModelRenderer(this, 11, 19);
        web16.addBox(0, 0, 0, 0, 4, 8);
        web16.setRotationPoint(-4, 21, -4);
        setRotation(web16, 0, 0, 0.7853982F);
        
        web17 = new ModelRenderer(this, 11, 19);
        web17.addBox(0, 0, 0, 0, 4, 8);
        web17.setRotationPoint(-4, 21, -4);
        setRotation(web17, 0, 1.570796F, -0.7853982F);
        
        web18 = new ModelRenderer(this, 11, 19);
        web18.addBox(0, 0, 0, 0, 4, 8);
        web18.setRotationPoint(-4, 21, 4);
        setRotation(web18, 0, 1.570796F, 0.7853982F);
        
        web21 = new ModelRenderer(this, 28, 19);
        web21.addBox(0, 0, 0, 4, 5, 0);
        web21.setRotationPoint(-3.2F, 10, -2.8F);
        setRotation(web21, -0.3141593F, 0.3839724F, 0);
        
        web22 = new ModelRenderer(this, 28, 19);
        web22.addBox(0, 0, 0, 4, 5, 0);
        web22.setRotationPoint(2.7F, 10, -3.3F);
        setRotation(web22, -0.3141593F, -1.099557F, 0);
        
        web23 = new ModelRenderer(this, 28, 19);
        web23.addBox(0, 0, 0, 4, 5, 0);
        web23.setRotationPoint(-0.4F, 10, -4.2F);
        setRotation(web23, -0.3141593F, -0.3665191F, 0);
        
        web24 = new ModelRenderer(this, 28, 19);
        web24.addBox(0, 0, 0, 4, 5, 0);
        web24.setRotationPoint(-4, 10, 0.5F);
        setRotation(web24, -0.3141593F, 1.256637F, 0);
        
        web25 = new ModelRenderer(this, 28, 19);
        web25.addBox(0, 0, 0, 4, 5, 0);
        web25.setRotationPoint(-0.3F, 10, 4.3F);
        setRotation(web25, 0.3141593F, 0.3839724F, 0);
        
        web26 = new ModelRenderer(this, 28, 19);
        web26.addBox(0, 0, 0, 4, 5, 0);
        web26.setRotationPoint(3, 10, 3.3F);
        setRotation(web26, 0.3141593F, 1.204277F, 0);
        
        web27 = new ModelRenderer(this, 28, 19);
        web27.addBox(0, 0, 0, 4, 5, 0);
        web27.setRotationPoint(-4, 10, -0.5F);
        setRotation(web27, 0.3141593F, -1.256637F, 0);
        
        web28 = new ModelRenderer(this, 28, 19);
        web28.addBox(0, 0, 0, 4, 5, 0);
        web28.setRotationPoint(-3.1F, 10, 2.8F);
        setRotation(web28, 0.3141593F, -0.3839724F, 0);
    }

    public void renderAll() {
        float f5 = 0.0625F;
        bottomCube1.render(f5);
        bottomCube2.render(f5);
        middleCube1.render(f5);
        middleCube2.render(f5);
        web1.render(f5);
        web2.render(f5);
        web3.render(f5);
        web4.render(f5);
        web5.render(f5);
        web6.render(f5);
        web7.render(f5);
        web8.render(f5);
        web11.render(f5);
        web12.render(f5);
        web13.render(f5);
        web14.render(f5);
        web15.render(f5);
        web16.render(f5);
        web17.render(f5);
        web18.render(f5);
        web21.render(f5);
        web22.render(f5);
        web23.render(f5);
        web24.render(f5);
        web25.render(f5);
        web26.render(f5);
        web27.render(f5);
        web28.render(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }
}
