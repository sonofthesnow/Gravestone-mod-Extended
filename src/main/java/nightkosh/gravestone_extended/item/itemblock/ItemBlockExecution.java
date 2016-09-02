package nightkosh.gravestone_extended.item.itemblock;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import nightkosh.gravestone_extended.block.enums.EnumExecution;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class ItemBlockExecution extends ItemBlock {

    public ItemBlockExecution(Block block) {
        super(block);
        setHasSubtypes(true);
    }

    @Override
    public int getMetadata(int damageValue) {
        return 0;
    }

    @Override
    public String getUnlocalizedName(ItemStack itemStack) {
        return EnumExecution.getById(itemStack.getItemDamage()).getUnLocalizedName();
    }

    @Override
    public void onCreated(ItemStack stack, World world, EntityPlayer player) {
        if (!stack.hasTagCompound()) {
            stack.setTagCompound(new NBTTagCompound());
        }
    }

    /**
     * Returns true if the given ItemBlock can be placed on the given side of
     * the given block position.
     */
    @Override
    @SideOnly(Side.CLIENT)
    public boolean canPlaceBlockOnSide(World world, BlockPos pos, EnumFacing side, EntityPlayer player, ItemStack stack) {
//        int x = pos.getX();//TODO !!!!!!!!!!!!!
//        int y = pos.getY();
//        int z = pos.getZ();
//        switch (side) {
//            case DOWN:
//                return false;
//            case UP:
//                y++;
//                break;
//            case NORTH:
//                z--;
//                break;
//            case SOUTH:
//                z++;
//                break;
//            case WEST:
//                x--;
//                break;
//            case EAST:
//                x++;
//                break;
//        }
//
//        EnumMemorials memorialType = EnumMemorials.getById(stack.getItemDamage());
//        byte maxY;
//        byte maxX = 1;
//        byte maxZ = 1;
//        byte startX = 0;
//        byte startZ = 0;
//
//        switch (memorialType.getMemorialType()) {
//            case CROSS:
//            case OBELISK:
//                maxY = 5;
//                maxX = 2;
//                maxZ = 2;
//                startX = -1;
//                startZ = -1;
//                break;
//            case DOG_STATUE:
//            case CAT_STATUE:
//            case CREEPER_STATUE:
//                maxY = 2;
//                break;
//            case CELTIC_CROSS:
//            case STEVE_STATUE:
//            case VILLAGER_STATUE:
//            case ANGEL_STATUE:
//            default:
//                maxY = 3;
//                break;
//        }
//
//        int airBlockId = Block.getIdFromBlock(Blocks.air);
//        for (byte shiftY = 0; shiftY < maxY; shiftY++) {
//            for (byte shiftZ = startZ; shiftZ < maxZ; shiftZ++) {
//                for (byte shiftX = startX; shiftX < maxX; shiftX++) {
//                    if (Block.getIdFromBlock(world.getBlockState(new BlockPos(x + shiftX, y + shiftY, z + shiftZ)).getBlock()) != airBlockId) {
//                        return false;
//                    }
//                }
//            }
//        }

        return true;
    }
}
