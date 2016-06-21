package nightkosh.gravestone_extended.renderer.tileentity;

import nightkosh.gravestone_extended.core.Resources;
import nightkosh.gravestone_extended.models.block.ModelPileOfBones;
import nightkosh.gravestone_extended.tileentity.TileEntityGSPileOfBones;
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

    private static final TileEntityGSPileOfBones PILE_OF_BONES_TE = new TileEntityGSPileOfBones();

    public void renderTileEntityCandleAt(TileEntityGSPileOfBones tileEntity, float x, float y, float z, float par8) {
        this.bindTexture(Resources.PILE_OF_BONES);

        if (tileEntity == null) {
            tileEntity = getDefaultTE();
        }

        int meta = tileEntity.getBlockMetadata();
        GL11.glPushMatrix();
        if (tileEntity.getWorld() == null) {
            GL11.glTranslatef(x + 0.5F, y + 2.7F, z + 0.5F);
            GL11.glScalef(1.8F, -1.8F, -1.8F);
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
        this.renderTileEntityCandleAt((TileEntityGSPileOfBones) tileEntity, (float) x, (float) y, (float) z, par8);
    }

    protected TileEntityGSPileOfBones getDefaultTE() {
        return PILE_OF_BONES_TE;
    }

    public static class Skull extends TileEntityPileOfBonesRenderer {
        private static final TileEntityGSPileOfBones PILE_OF_BONES_TE = new TileEntityGSPileOfBones.Skull();

        @Override
        protected TileEntityGSPileOfBones getDefaultTE() {
            return PILE_OF_BONES_TE;
        }
    }
    public static class Crawler extends TileEntityPileOfBonesRenderer {
        private static final TileEntityGSPileOfBones PILE_OF_BONES_TE = new TileEntityGSPileOfBones.Crawler();

        @Override
        protected TileEntityGSPileOfBones getDefaultTE() {
            return PILE_OF_BONES_TE;
        }
    }
}
