package nightkosh.gravestone_extended.renderer.tileentity;

import nightkosh.gravestone_extended.core.Resources;
import nightkosh.gravestone_extended.models.block.ModelCandle;
import nightkosh.gravestone_extended.tileentity.TileEntityCandle;
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
public class TileEntityCandleRenderer extends TileEntitySpecialRenderer {

    private ModelCandle candleModel = new ModelCandle();

    public void renderTileEntityCandleAt(TileEntityCandle tileEntity, float x, float y, float z, float par8) {
        this.bindTexture(Resources.CANDLE);

        GL11.glPushMatrix();//TODO tileEntity == null ??
        if (tileEntity == null || tileEntity.getWorld() == null) {
            GL11.glTranslatef(x + 0.5F, y + 2.9F, z + 0.5F);
            GL11.glScalef(1.9F, -1.9F, -1.9F);
        } else {
            GL11.glTranslatef(x + 0.5F, y + 1.5F, z + 0.5F);
            GL11.glScalef(1, -1, -1);
        }
        GL11.glRotatef(0, 0, 1, 0);

        candleModel.renderAll();
        GL11.glPopMatrix();
    }

    @Override
    public void render(TileEntity te, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
        this.renderTileEntityCandleAt((TileEntityCandle) te, (float) x, (float) y, (float) z, alpha);
    }
}
