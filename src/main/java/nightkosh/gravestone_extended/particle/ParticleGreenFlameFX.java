package nightkosh.gravestone_extended.particle;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.ParticleFlame;
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
public class ParticleGreenFlameFX extends ParticleFlame {

    public ParticleGreenFlameFX(World world, double xPos, double yPos, double zPos, double p_i1209_8_, double p_i1209_10_, double p_i1209_12_) {
        super(world, xPos, yPos, zPos, p_i1209_8_, p_i1209_10_, p_i1209_12_);
    }

    @Override
    public void renderParticle(BufferBuilder buffer, Entity entityIn, float partialTicks, float rotationX, float rotationZ, float rotationYZ, float rotationXY, float rotationXZ) {
        Minecraft.getMinecraft().renderEngine.bindTexture(Resources.PARTICLES);
        super.renderParticle(buffer, entityIn, partialTicks, rotationX, rotationZ, rotationYZ, rotationXY, rotationXZ);
    }
}
