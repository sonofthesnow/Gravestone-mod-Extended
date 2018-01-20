package nightkosh.gravestone_extended.particle;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.ParticleSplash;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;
import nightkosh.gravestone_extended.core.Resources;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class ParticleToxicWaterSplash extends ParticleSplash {

    public ParticleToxicWaterSplash(World world, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
        super(world, x, y, z, xSpeed, ySpeed, zSpeed);
//        this.nextTextureIndexX();
    }

    @Override
    public void renderParticle(BufferBuilder buffer, Entity entityIn, float partialTicks, float rotationX, float rotationZ, float rotationYZ, float rotationXY, float rotationXZ) {
        Minecraft.getMinecraft().renderEngine.bindTexture(Resources.PARTICLES);
        super.renderParticle(buffer, entityIn, partialTicks, rotationX, rotationZ, rotationYZ, rotationXY, rotationXZ);
    }
}
