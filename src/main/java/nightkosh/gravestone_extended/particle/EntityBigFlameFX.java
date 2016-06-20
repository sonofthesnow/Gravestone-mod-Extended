package nightkosh.gravestone_extended.particle;

import net.minecraft.client.particle.EntityFlameFX;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class EntityBigFlameFX extends EntityFlameFX {

    public EntityBigFlameFX(World world, double xPos, double yPos, double zPos, double p_i1209_8_, double p_i1209_10_, double p_i1209_12_) {
        super(world, xPos, yPos, zPos, p_i1209_8_, p_i1209_10_, p_i1209_12_);
    }

    @Override
    public void renderParticle(WorldRenderer worldRenderer, Entity entity, float partialTicks, float x, float y, float z, float p_180434_7_, float p_180434_8_) {
        float xz = (this.particleAge + partialTicks) / (float) this.particleMaxAge;
        this.particleScale = 3.5F * (1 - xz * xz * 0.5F);

        float f = this.particleTextureIndexX / 16F;
        float f1 = f + 0.0624375F;
        float f2 = this.particleTextureIndexY / 16F;
        float f3 = f2 + 0.0624375F;
        float scale = 0.1F * this.particleScale;

        if (this.particleIcon != null) {
            f = this.particleIcon.getMinU();
            f1 = this.particleIcon.getMaxU();
            f2 = this.particleIcon.getMinV();
            f3 = this.particleIcon.getMaxV();
        }

        float xPos = (float) (this.prevPosX + (this.posX - this.prevPosX) * (double) partialTicks - interpPosX);
        float yPos = (float) (this.prevPosY + (this.posY - this.prevPosY) * (double) partialTicks - interpPosY);
        float zPos = (float) (this.prevPosZ + (this.posZ - this.prevPosZ) * (double) partialTicks - interpPosZ);

        int brightness = this.getBrightnessForRender(partialTicks);
        int j = brightness >> 16 & 65535;
        int k = brightness & 65535;

//        worldRenderer.putColorRGB_F(this.particleRed, this.particleGreen, this.particleBlue, this.particleAlpha);//TODO ???
        worldRenderer.pos((double)(xPos - x * scale - p_180434_7_ * scale), (double)(yPos - y * scale), (double)(zPos - z * scale - p_180434_8_ * scale)).tex((double) f1, (double) f3).color(this.particleRed, this.particleGreen, this.particleBlue, this.particleAlpha).lightmap(j, k).endVertex();
        worldRenderer.pos((double)(xPos - x * scale + p_180434_7_ * scale), (double)(yPos + y * scale), (double)(zPos - z * scale + p_180434_8_ * scale)).tex((double)f1, (double)f2).color(this.particleRed, this.particleGreen, this.particleBlue, this.particleAlpha).lightmap(j, k).endVertex();
        worldRenderer.pos((double)(xPos + x * scale + p_180434_7_ * scale), (double)(yPos + y * scale), (double)(zPos + z * scale + p_180434_8_ * scale)).tex((double) f, (double) f2).color(this.particleRed, this.particleGreen, this.particleBlue, this.particleAlpha).lightmap(j, k).endVertex();
        worldRenderer.pos((double)(xPos + x * scale - p_180434_7_ * scale), (double)(yPos - y * scale), (double)(zPos + z * scale - p_180434_8_ * scale)).tex((double) f, (double) f3).color(this.particleRed, this.particleGreen, this.particleBlue, this.particleAlpha).lightmap(j, k).endVertex();
    }
}
