package nightkosh.gravestone_extended.renderer.tileentity;

import nightkosh.gravestone_extended.core.Resources;
import nightkosh.gravestone_extended.models.block.ModelPileOfBones;
import nightkosh.gravestone_extended.tileentity.TileEntityPileOfBones;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
@SideOnly(Side.CLIENT)
public class TileEntityPileOfBonesRenderer extends TileEntitySpecialRenderer {

    private ModelPileOfBones pileOfBonesModel = new ModelPileOfBones();

    private static final TileEntityPileOfBones PILE_OF_BONES_TE = new TileEntityPileOfBones();

    public void renderTileEntityCandleAt(TileEntityPileOfBones tileEntity, float x, float y, float z, float par8) {
        this.bindTexture(Resources.PILE_OF_BONES);

        if (tileEntity == null) {
            tileEntity = getDefaultTE();
        }

        int meta = tileEntity.getBlockMetadata();
        GL11.glPushMatrix();
        if (tileEntity.getWorld() == null) {
            GL11.glRotatef(35, 1, 0, 0);

            GL11.glTranslatef(x + 0.5F, y + 2.5F, z + 0.5F);
            GL11.glScalef(1.2F, -1.2F, -1.2F);
            GL11.glRotatef(0, 0, 1, 0);
        } else {
            GL11.glTranslatef(x + 0.5F, y + 1.5F, z + 0.5F);
            GL11.glScalef(1, -1, -1);
            int direction = tileEntity.getDirection();
            switch (direction) {
                case 1:
                    direction = -90;
                    break;
                case 0:
                    direction = 180;
                    break;
                case 2:
                    direction = 0;
                    break;
                case 3:
                default:
                    direction = 90;
                    break;
            }
            GL11.glRotatef(direction, 0, 1, 0);
        }

        pileOfBonesModel.renderAll(meta != 0);
        GL11.glPopMatrix();
    }

    @Override
    public void renderTileEntityAt(TileEntity tileEntity, double x, double y, double z, float par8, int par9) {
        this.renderTileEntityCandleAt((TileEntityPileOfBones) tileEntity, (float) x, (float) y, (float) z, par8);
    }

    protected TileEntityPileOfBones getDefaultTE() {
        return PILE_OF_BONES_TE;
    }

    public static class Skull extends TileEntityPileOfBonesRenderer {
        private static final TileEntityPileOfBones PILE_OF_BONES_TE = new TileEntityPileOfBones.Skull();

        @Override
        protected TileEntityPileOfBones getDefaultTE() {
            return PILE_OF_BONES_TE;
        }
    }
    public static class Crawler extends TileEntityPileOfBonesRenderer {
        private static final TileEntityPileOfBones PILE_OF_BONES_TE = new TileEntityPileOfBones.Crawler();

        @Override
        protected TileEntityPileOfBones getDefaultTE() {
            return PILE_OF_BONES_TE;
        }
    }
}
