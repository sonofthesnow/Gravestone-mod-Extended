package nightkosh.gravestone_extended.renderer.tileentity;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import nightkosh.gravestone.models.ModelRendererSkull;
import nightkosh.gravestone_extended.block.enums.EnumSkullCandle;
import nightkosh.gravestone_extended.models.block.ModelSkullCandle;
import nightkosh.gravestone_extended.tileentity.TileEntitySkullCandle;
import org.lwjgl.opengl.GL11;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
@SideOnly(Side.CLIENT)
public class TileEntitySkullCandleRenderer extends TileEntitySpecialRenderer {

    private ModelSkullCandle skullCandleModel = new ModelSkullCandle();

    private static final TileEntitySkullCandle SKULL_CANDLE_TE = new TileEntitySkullCandle();

    public void renderTileEntitySkullAt(TileEntitySkullCandle tileEntity, float x, float y, float z, float par8) {
        float rotation = 0;
        byte meta;
        if (tileEntity == null) {
            tileEntity = getDefaultTE();
        }

        GL11.glPushMatrix();
        if (tileEntity.getWorld() == null) {
            meta = (byte) tileEntity.getBlockMetadata();

            GL11.glRotatef(-35, 0, 1, 0);
            GL11.glTranslatef(x + 1, y + 1.9F, z + 0.5F);
            GL11.glScalef(1.2F, -1.2F, -1.2F);
        } else {
            rotation = (tileEntity.getRotation() * 360) / 8F;
            meta = (byte) tileEntity.getBlockMetadata();

            GL11.glTranslatef(x + 0.5F, y + 1.5F, z + 0.5F);
            GL11.glScalef(1, -1, -1);
        }
        GL11.glRotatef(rotation, 0, 1, 0);

        skullCandleModel.renderAll(getSkullType(EnumSkullCandle.getById(meta)));
        GL11.glPopMatrix();
    }

    @Override
    public void renderTileEntityAt(TileEntity tileEntity, double x, double y, double z, float par8, int par9) {
        this.renderTileEntitySkullAt((TileEntitySkullCandle) tileEntity, (float) x, (float) y, (float) z, par8);
    }

    private ModelRendererSkull.EnumSkullType getSkullType(EnumSkullCandle skullType) {
        switch (skullType) {
            case WITHER_SKULL:
                return ModelRendererSkull.EnumSkullType.WITHER_SKULL;
            case ZOMBIE_SKULL:
                return ModelRendererSkull.EnumSkullType.ZOMBIE_SKULL;
            case SKELETON_SKULL:
            default:
                return ModelRendererSkull.EnumSkullType.SKELETON_SKULL;
        }
    }

    protected TileEntitySkullCandle getDefaultTE() {
        return SKULL_CANDLE_TE;
    }

    public static class Zombie extends TileEntitySkullCandleRenderer {
        private static final TileEntitySkullCandle SKULL_CANDLE_TE = new TileEntitySkullCandle.Zombie();

        @Override
        protected TileEntitySkullCandle getDefaultTE() {
            return SKULL_CANDLE_TE;
        }
    }

    public static class Wither extends TileEntitySkullCandleRenderer {
        private static final TileEntitySkullCandle SKULL_CANDLE_TE = new TileEntitySkullCandle.Wither();

        @Override
        protected TileEntitySkullCandle getDefaultTE() {
            return SKULL_CANDLE_TE;
        }
    }
}
