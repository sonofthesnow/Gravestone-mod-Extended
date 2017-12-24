package nightkosh.gravestone_extended.potion;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import nightkosh.gravestone_extended.core.ModInfo;

import javax.annotation.Nullable;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class PotionRecall extends PotionInstant {

    public PotionRecall() {
        super(false, 0xc7fb);
        this.setRegistryName(ModInfo.ID, "gs_recall_potion");
        this.setPotionName("potion.recall.title");
    }

    @Override
    public void affectEntity(@Nullable Entity source, @Nullable Entity indirectSource, EntityLivingBase entity, int amplifier, double health) {
        if (!entity.world.isRemote && entity instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) entity;

            if (player.getSpawnDimension() != player.world.provider.getDimension()) {
                player.changeDimension(0);
            }
            if (player.getServer() != null) {
                World world = player.world;
                int dimensionId = world.provider.getDimension();
                WorldServer currentWorld = player.getServer().getWorld(dimensionId);

                BlockPos pos = player.getBedLocation();
                if (pos == null) {
                    pos = currentWorld.getTopSolidOrLiquidBlock(currentWorld.getSpawnPoint());
                }
                player.setPositionAndUpdate(pos.getX(), pos.getY(), pos.getZ());
            }
        }
    }
}
