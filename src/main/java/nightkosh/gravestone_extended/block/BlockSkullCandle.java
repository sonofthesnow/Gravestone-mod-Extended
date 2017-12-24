package nightkosh.gravestone_extended.block;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.Particle;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import nightkosh.gravestone_extended.block.enums.EnumSkullCandle;
import nightkosh.gravestone_extended.core.GSTabs;
import nightkosh.gravestone_extended.core.ModInfo;
import nightkosh.gravestone_extended.helper.TimeHelper;
import nightkosh.gravestone_extended.particle.EntityGreenFlameFX;
import nightkosh.gravestone_extended.tileentity.TileEntitySkullCandle;

import java.util.Random;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class BlockSkullCandle extends BlockContainer {

    public static final PropertyEnum VARIANT = PropertyEnum.create("variant", EnumSkullCandle.class);

    public BlockSkullCandle() {
        super(Material.CIRCUITS);
        this.setSoundType(SoundType.STONE);
        this.setHardness(0.5F);
        this.setResistance(5);
        this.setLightLevel(1);
        this.setCreativeTab(GSTabs.otherItemsTab);
        this.setRegistryName(ModInfo.ID, "gsskullcandle");
    }

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
    public boolean isFullCube(IBlockState state) {
        return false;
    }

    @Override
    public BlockFaceShape getBlockFaceShape(IBlockAccess access, IBlockState state, BlockPos pos, EnumFacing facing) {
        return BlockFaceShape.UNDEFINED;
    }

    private static final AxisAlignedBB BB = new AxisAlignedBB(0.25F, 0, 0.25F, 0.75F, 0.5F, 0.75F);

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess access, BlockPos pos) {
        return BB;
    }

    /**
     * Returns a new instance of a block's tile entity class. Called on placing
     * the block.
     */
    @Override
    public TileEntity createNewTileEntity(World world, int var2) {
        return new TileEntitySkullCandle();
    }

    @Override
    public int damageDropped(IBlockState state) {
        return ((Enum) state.getValue(VARIANT)).ordinal();
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        return this.getDefaultState().withProperty(VARIANT, EnumSkullCandle.getById((byte) meta));
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return ((EnumSkullCandle) state.getValue(VARIANT)).ordinal();
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, new IProperty[]{VARIANT});
    }

    /**
     * Called when the block is placed in the world.
     */
    @Override
    public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase entity, ItemStack stack) {
        world.setBlockState(pos, getStateFromMeta(stack.getItemDamage()), 2);

        TileEntitySkullCandle tileEntity = (TileEntitySkullCandle) world.getTileEntity(pos);
        if (tileEntity != null) {
            float skullRotation = entity.rotationYaw - 180 - 22.5F;
            if (skullRotation < 0) {
                skullRotation = 360 + skullRotation;
            }
            tileEntity.setRotation((byte) MathHelper.ceil(skullRotation * 8 / 360F));
        }
    }

    /**
     * Returns a list of blocks with the same ID, but different meta (eg: wood
     * returns 4 blocks)
     */
    @Override
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(CreativeTabs tab, NonNullList<ItemStack> list) {
        Item item = Item.getItemFromBlock(this);
        for (byte i = 0; i < EnumSkullCandle.values().length; i++) {
            list.add(new ItemStack(item, 1, i));
        }
    }

    /**
     * A randomly called display update to be able to add particles or other
     * items for display
     */
    @Override
    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(IBlockState state, World world, BlockPos pos, Random random) {
        TileEntitySkullCandle tileEntity = (TileEntitySkullCandle) world.getTileEntity(pos);
        if (tileEntity != null) {
            double xPos = pos.getX() + 0.5F;
            double yPos = pos.getY() + 0.85;
            double zPos = pos.getZ() + 0.5F;
            double rotation = Math.toRadians(tileEntity.getRotation() * 360 / 8F);
            double d = 0.07;
            double dx = -Math.sin(rotation) * d;
            double dz = Math.cos(rotation) * d;

            long dayTime = TimeHelper.getDayTime(world);
            if (dayTime < TimeHelper.SUN_SET || dayTime > TimeHelper.SUN_RISING) {
                world.spawnParticle(EnumParticleTypes.FLAME, xPos + dx, yPos, zPos + dz, 0, 0, 0);
            } else {
                Particle entityfx = new EntityGreenFlameFX(world, xPos + dx, yPos, zPos + dz, 0, 0, 0);
                Minecraft.getMinecraft().effectRenderer.addEffect(entityfx);
            }

            world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, xPos + dx, yPos, zPos + dz, 0, 0, 0);
        }
    }
}
