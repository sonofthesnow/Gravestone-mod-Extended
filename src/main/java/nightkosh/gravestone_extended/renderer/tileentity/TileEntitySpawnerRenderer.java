package nightkosh.gravestone_extended.renderer.tileentity;

import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import nightkosh.gravestone.renderer.tileentity.TileEntityRenderer;
import nightkosh.gravestone_extended.block.enums.EnumSpawner;
import nightkosh.gravestone_extended.core.Resources;
import nightkosh.gravestone_extended.models.block.ModelSpawnerBase;
import nightkosh.gravestone_extended.models.block.ModelSpawnerPentagram;
import nightkosh.gravestone_extended.models.block.ModelSpiderSpawner;
import nightkosh.gravestone_extended.tileentity.TileEntitySpawner;
import org.lwjgl.opengl.GL11;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
@SideOnly(Side.CLIENT)
public class TileEntitySpawnerRenderer extends TileEntityRenderer {

    private static ModelSpawnerBase witherSpawnerModel = new ModelSpawnerPentagram(Resources.WITHER_SKULL_CANDLE);
    private static ModelSpawnerBase skeletonSpawnerModel = new ModelSpawnerPentagram(Resources.SKELETON_SKULL_CANDLE);
    private static ModelSpawnerBase zombieSpawnerModel = new ModelSpawnerPentagram(Resources.ZOMBIE_SKULL_CANDLE);
    private static ModelSpawnerBase spiderSpawnerModel = new ModelSpiderSpawner();

    public static TileEntitySpawnerRenderer instance;

    private static final TileEntitySpawner SPAWNER_TE = new TileEntitySpawner();

    public TileEntitySpawnerRenderer() {
        instance = this;
    }

    /**
     * Render a skull tile entity.
     */
    public void renderSpawnerPentagramAt(TileEntitySpawner tileEntity, float x, float y, float z, float par8) {

        if (tileEntity == null) {
            tileEntity = getDefaultTE();
        }
        byte type = (byte) tileEntity.getBlockMetadata();
        EnumSpawner spawnerType = EnumSpawner.getById(type);

        GL11.glPushMatrix();
        if (tileEntity.getWorld() != null) {
            if (spawnerType == EnumSpawner.SPIDER_SPAWNER) {
                GL11.glTranslatef(x + 0.5F, y + 0.75F, z + 0.5F);
                GL11.glScalef(0.5F, -0.5F, -0.5F);
            } else {
                GL11.glTranslatef(x + 0.5F, y + 1.5F, z + 0.5F);
                GL11.glScalef(1, -1, -1);
            }
        } else {
            if (spawnerType == EnumSpawner.SPIDER_SPAWNER) {
                GL11.glTranslatef(x + 0.5F, y + 0.5F, z + 0.5F);
                GL11.glScalef(0.3F, -0.3F, -0.3F);
            } else {
                GL11.glTranslatef(x + 0.5F, y + 1, z + 0.5F);
                GL11.glScalef(0.6F, -0.6F, -0.6F);
            }
        }
        GL11.glTranslated(0, -0.01, 0);
        if (spawnerType == EnumSpawner.SPIDER_SPAWNER) {
            this.bindTexture(Resources.SPIDER_SPAWNER);
        } else {
            this.bindTexture(Resources.PENTAGRAM);
        }
        ModelSpawnerBase spawner = getSpawnerModel(spawnerType);
        spawner.renderAll();
        GL11.glPopMatrix();
    }

    @Override
    public void renderTileEntityAt(TileEntity tileEntity, double x, double y, double z, float par8, int par9) {
        this.renderSpawnerPentagramAt((TileEntitySpawner) tileEntity, (float) x, (float) y, (float) z, par8);
    }

    private static ModelSpawnerBase getSpawnerModel(EnumSpawner spawnerType) {
        switch (spawnerType) {
            case WITHER_SPAWNER:
                return witherSpawnerModel;
            case SKELETON_SPAWNER:
                return skeletonSpawnerModel;
            case SPIDER_SPAWNER:
                return spiderSpawnerModel;
            case ZOMBIE_SPAWNER:
            default:
                return zombieSpawnerModel;
        }
    }


    protected TileEntitySpawner getDefaultTE() {
        return SPAWNER_TE;
    }

    public static class Skeleton extends TileEntitySpawnerRenderer {
        private static final TileEntitySpawner SPAWNER_TE = new TileEntitySpawner.Skeleton();

        @Override
        protected TileEntitySpawner getDefaultTE() {
            return SPAWNER_TE;
        }
    }

    public static class Zombie extends TileEntitySpawnerRenderer {
        private static final TileEntitySpawner SPAWNER_TE = new TileEntitySpawner.Zombie();

        @Override
        protected TileEntitySpawner getDefaultTE() {
            return SPAWNER_TE;
        }
    }

    public static class Spider extends TileEntitySpawnerRenderer {
        private static final TileEntitySpawner SPAWNER_TE = new TileEntitySpawner.Spider();

        @Override
        protected TileEntitySpawner getDefaultTE() {
            return SPAWNER_TE;
        }
    }
}
