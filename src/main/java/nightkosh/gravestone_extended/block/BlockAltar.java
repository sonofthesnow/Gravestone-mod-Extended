package nightkosh.gravestone_extended.block;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import nightkosh.gravestone_extended.ModGravestoneExtended;
import nightkosh.gravestone_extended.core.GuiHandler;
import nightkosh.gravestone_extended.core.Tabs;
import nightkosh.gravestone_extended.tileentity.TileEntityAltar;

import javax.annotation.Nullable;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class BlockAltar extends BlockContainer {

    public BlockAltar() {
        super(Material.ROCK);
        this.setLightOpacity(0);
        this.setUnlocalizedName("altar");
        this.setCreativeTab(Tabs.otherItemsTab);
        this.setHarvestLevel("pickaxe", 2);
    }

    /**
     * Called upon block activation (right click on the block.)
     */
    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, @Nullable ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
//        ItemStack stack = player.getCurrentEquippedItem();

        TileEntityAltar tileEntity = (TileEntityAltar) world.getTileEntity(pos);
        if (tileEntity != null && !player.isSneaking()) {
//            if (tileEntity.hasCorpse()) {
//                if (!world.isRemote) {
//                    tileEntity.dropCorpse();
//                }
//            } else {
//                if (stack != null && stack.getItem() instanceof ItemGSCorpse) {
//                    ItemStack corpse = stack.copy();
//                    corpse.stackSize = 1;
//                    tileEntity.setCorpse(corpse);
//                    if (!player.capabilities.isCreativeMode) {
//                        stack.stackSize--;
//                    }
//                }
//            }
            player.openGui(ModGravestoneExtended.instance, GuiHandler.ALTAR_GUI_ID, world, pos.getX(), pos.getY(), pos.getZ());
            return true;
        }
        return false;
    }
//
//    /**
//     * If this block doesn't render as an ordinary block it will return False
//     * (examples: signs, buttons, stairs, etc)
//     */
//    @Override
//    public boolean renderAsNormalBlock() {
//        return false;
//    }

    /**
     * Is this block (a) opaque and (b) a full 1m cube? This determines whether
     * or not to render the shared face of two adjacent blocks and also whether
     * the player can attach torches, redstone wire, etc to this block.
     */
    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @Override
    public EnumBlockRenderType getRenderType(IBlockState state) {
        return EnumBlockRenderType.MODEL;
    }

    private static final AxisAlignedBB BB = new AxisAlignedBB(0, 0, 0, 1, 0.75F, 1);

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess access, BlockPos pos) {
        return BB;
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta) {
        return new TileEntityAltar();
    }

    @Override
    public void breakBlock(World world, BlockPos pos, IBlockState state) {
        TileEntityAltar tileEntity = (TileEntityAltar) world.getTileEntity(pos);

        if (tileEntity != null) {
            tileEntity.dropCorpse();
        }

        super.breakBlock(world, pos, state);
    }
}
