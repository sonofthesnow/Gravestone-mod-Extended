package nightkosh.gravestone_extended.core;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import nightkosh.gravestone_extended.gui.GSAltarGui;
import nightkosh.gravestone_extended.gui.GSChiselCraftingGui;
import nightkosh.gravestone_extended.gui.container.AltarContainer;
import nightkosh.gravestone_extended.gui.container.ChiselContainer;
import nightkosh.gravestone_extended.tileentity.TileEntityGSAltar;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class GuiHandler extends nightkosh.gravestone.core.GuiHandler {

    public static final int ALTAR_GUI_ID = 1;
    public static final int CHISEL_CRAFTING_GUI_ID = 2;

    @Override
    public Object getServerGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
        TileEntity tileEntity;
        switch (id) {
            case GRAVE_INVENTORY_GUI_ID:
                return super.getServerGuiElement(id, player, world, x, y, z);
            case ALTAR_GUI_ID:
                tileEntity = world.getTileEntity(new BlockPos(x, y, z));
                if (tileEntity instanceof TileEntityGSAltar) {
                    return new AltarContainer(player.inventory, (TileEntityGSAltar) tileEntity);
                }
                break;
            case CHISEL_CRAFTING_GUI_ID:
                return new ChiselContainer(player, player.inventory);
        }
        return null;
    }

    @Override
    public Object getClientGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
        TileEntity tileEntity;
        switch (id) {
            case GRAVE_INVENTORY_GUI_ID:
                return super.getClientGuiElement(id, player, world, x, y, z);
            case ALTAR_GUI_ID:
                tileEntity = world.getTileEntity(new BlockPos(x, y, z));
                if (tileEntity instanceof TileEntityGSAltar) {
                    return new GSAltarGui(player.inventory, (TileEntityGSAltar) tileEntity);
                }
                break;
            case CHISEL_CRAFTING_GUI_ID:
                return new GSChiselCraftingGui(player, player.inventory);
        }
        return null;
    }
}
