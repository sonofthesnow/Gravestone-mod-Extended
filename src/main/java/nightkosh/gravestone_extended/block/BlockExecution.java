package nightkosh.gravestone_extended.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.VillagerRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import nightkosh.gravestone.inventory.GraveInventory;
import nightkosh.gravestone_extended.block.enums.EnumExecution;
import nightkosh.gravestone_extended.block.enums.EnumHangedMobs;
import nightkosh.gravestone_extended.core.GSBlock;
import nightkosh.gravestone_extended.core.Tabs;
import nightkosh.gravestone_extended.item.ItemCorpse;
import nightkosh.gravestone_extended.item.corpse.VillagerCorpseHelper;
import nightkosh.gravestone_extended.item.enums.EnumCorpse;
import nightkosh.gravestone_extended.particle.EntityBigFlameFX;
import nightkosh.gravestone_extended.tileentity.TileEntityExecution;

import java.util.Collection;
import java.util.List;
import java.util.Random;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class BlockExecution extends BlockContainer {

    public static final PropertyEnum VARIANT = PropertyEnum.create("variant", EnumExecution.class);

    public BlockExecution() {
        super(Material.rock);
        this.isBlockContainer = true;
        this.setStepSound(Block.soundTypeWood);
        this.setHardness(1);
        this.setResistance(5);
        this.setCreativeTab(Tabs.memorialsTab);
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileEntityExecution();
    }

    @Override
    public void setBlockBoundsBasedOnState(IBlockAccess access, BlockPos pos) {
        EnumExecution executionBlockType = (EnumExecution) access.getBlockState(pos).getValue(VARIANT);
        TileEntityExecution tileEntity = (TileEntityExecution) access.getTileEntity(pos);


        EnumFacing facing;
        if (tileEntity != null) {
            facing = EnumFacing.values()[tileEntity.getDirection()];
        } else {
            facing = EnumFacing.NORTH;
        }

        switch (executionBlockType) {
            case GALLOWS:
            case GIBBET:
            case BURNING_STAKE:
                this.setBlockBounds(0, 0, 0, 1, 3, 1);
                break;
            case STOCKS:
                switch (facing) {
                    case SOUTH:
                    case NORTH:
                        this.setBlockBounds(-0.5F, 0, 0, 1.5F, 2, 1);
                        break;
                    case EAST:
                    case WEST:
                        this.setBlockBounds(0, 0, -0.5F, 1, 2, 1.5F);
                        break;
                }
                break;
        }
    }

    @Override
    public void setBlockBoundsForItemRender() {
        this.setBlockBounds(0, 0, 0, 1, 1, 1);
    }

    @Override
    public boolean isOpaqueCube() {
        return false;
    }

    @Override
    public boolean isFullCube() {
        return false;
    }

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumFacing side, float hitX, float hitY, float hitZ) {
        TileEntityExecution te = (TileEntityExecution) world.getTileEntity(pos);

        if (te != null) {
            ItemStack item = player.inventory.getCurrentItem();
            if (item != null && item.getItem() instanceof ItemCorpse && EnumCorpse.getById((byte) item.getItemDamage()).equals(EnumCorpse.VILLAGER) && te.getHangedMob() == EnumHangedMobs.NONE) {
                te.setHangedMob(EnumHangedMobs.VILLAGER);
                te.setHangedVillagerProfession(VillagerCorpseHelper.getVillagerType(item.getTagCompound()));
                item.stackSize--;
                return true;
            }
        }

        return false;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item item, CreativeTabs tab, List list) {
        for (EnumExecution executionBlock : EnumExecution.values()) {
            for (byte mobType = 0; mobType < EnumHangedMobs.values().length; mobType++) {
                ItemStack stack = new ItemStack(item, 1, executionBlock.ordinal());
                if (!stack.hasTagCompound()) {
                    stack.setTagCompound(new NBTTagCompound());
                }
                stack.getTagCompound().setByte("HangedMob", mobType);
                switch (EnumHangedMobs.values()[mobType]) {
                    case VILLAGER:
                        ItemStack villagerStack;
                        for (byte villagerProfession = 0; villagerProfession <= 4; villagerProfession++) {
                            villagerStack = stack.copy();
                            villagerStack.getTagCompound().setInteger("HangedVillagerProfession", villagerProfession);
                            list.add(villagerStack);
                        }

                        Collection<Integer> villagerIds = VillagerRegistry.getRegisteredVillagers();
                        for (Integer villagerId : villagerIds) {
                            villagerStack = stack.copy();
                            villagerStack.getTagCompound().setInteger("HangedVillagerProfession", villagerId);
                            list.add(villagerStack);
                        }
                        break;
                    default:
                        list.add(stack);
                }

            }
        }
    }

    @Override
    public void onBlockHarvested(World world, BlockPos pos, IBlockState state, EntityPlayer player) {
        player.addExhaustion(0.025F);

        ItemStack itemStack;
        if (EnchantmentHelper.getSilkTouchModifier(player)) {
            itemStack = getBlockItemStack(world, pos);
        } else {
            itemStack = getBlockItemStackWithoutInfo(world, pos);
        }

        if (itemStack != null) {
            GraveInventory.dropItem(itemStack, world, pos);
        }
    }

    private ItemStack getBlockItemStack(World world, BlockPos pos) {
        ItemStack itemStack = this.createStackedBlock(world.getBlockState(pos));
        TileEntityExecution tileEntity = (TileEntityExecution) world.getTileEntity(pos);

        if (tileEntity != null && itemStack != null) {
            NBTTagCompound nbt = new NBTTagCompound();

            nbt.setByte("HangedMob", (byte) tileEntity.getHangedMob().ordinal());
            nbt.setInteger("HangedVillagerProfession", tileEntity.getHangedVillagerProfession());

            itemStack.setTagCompound(nbt);
        }

        return itemStack;
    }

    private ItemStack getBlockItemStackWithoutInfo(World world, BlockPos pos) {
        return this.createStackedBlock(world.getBlockState(pos));
    }

    @Override
    public void harvestBlock(World world, EntityPlayer player, BlockPos pos, IBlockState state, TileEntity te) {
    }

    @Override
    public ItemStack getPickBlock(MovingObjectPosition target, World world, BlockPos pos, EntityPlayer player) {
        ItemStack itemStack = this.createStackedBlock(world.getBlockState(pos));
        TileEntityExecution tileEntity = (TileEntityExecution) world.getTileEntity(pos);

        if (tileEntity != null && itemStack != null) {
            NBTTagCompound nbt = new NBTTagCompound();

            nbt.setByte("HangedMob", (byte) tileEntity.getHangedMob().ordinal());
            nbt.setInteger("HangedVillagerProfession", tileEntity.getHangedVillagerProfession());

            itemStack.setTagCompound(nbt);
        }
        return itemStack;
    }

    @Override
    public int getLightValue(IBlockAccess access, BlockPos pos) {
        TileEntityExecution tileEntity = (TileEntityExecution) access.getTileEntity(pos);

        if (tileEntity != null && access.getBlockState(pos).getValue(VARIANT) == EnumExecution.BURNING_STAKE && tileEntity.getHangedMob() != EnumHangedMobs.NONE) {
            return 15;
        } else {
            return super.getLightValue(access, pos);
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(World world, BlockPos pos, IBlockState state, Random random) {
        TileEntityExecution tileEntity = (TileEntityExecution) world.getTileEntity(pos);
        if (tileEntity != null && state.getValue(VARIANT) == EnumExecution.BURNING_STAKE && tileEntity.getHangedMob() != EnumHangedMobs.NONE) {
            double xPos, zPos, yPos;

            yPos = pos.getY() + 0.25;
            for (int angle = 0; angle < 20; angle++) {
                xPos = pos.getX() + 0.5 + Math.sin(angle * 0.2792) * 0.75;
                zPos = pos.getZ() + 0.5 + Math.cos(angle * 0.2792) * 0.75;

                EntityFX entityfx = new EntityBigFlameFX(world, xPos, yPos, zPos, 0, 0, 0);
                Minecraft.getMinecraft().effectRenderer.addEffect(entityfx);
            }

            yPos += 0.25;
            for (int angle = 0; angle < 11; angle++) {
                xPos = pos.getX() + 0.5 + Math.sin(angle * 0.5584) * 0.5;
                zPos = pos.getZ() + 0.5 + Math.cos(angle * 0.5584) * 0.5;

                EntityFX entityfx = new EntityBigFlameFX(world, xPos, yPos, zPos, 0, 0, 0);
                Minecraft.getMinecraft().effectRenderer.addEffect(entityfx);
            }

            yPos += 0.35;
            for (int angle = 0; angle < 5; angle++) {
                xPos = pos.getX() + 0.5 + Math.sin(angle * 1.1168) * 0.2;
                zPos = pos.getZ() + 0.5 + Math.cos(angle * 1.1168) * 0.2;

                EntityFX entityfx = new EntityBigFlameFX(world, xPos, yPos, zPos, 0, 0, 0);
                Minecraft.getMinecraft().effectRenderer.addEffect(entityfx);
                world.spawnParticle(EnumParticleTypes.LAVA, xPos, yPos, zPos, 0, 0, 0);
                world.spawnParticle(EnumParticleTypes.SMOKE_LARGE, xPos, yPos, zPos, 0, 0, 0);
            }
        }
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        return this.getDefaultState().withProperty(VARIANT, EnumExecution.getById((byte) meta));
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return ((EnumExecution) state.getValue(VARIANT)).ordinal();
    }

    @Override
    protected BlockState createBlockState() {
        return new BlockState(this, new IProperty[]{VARIANT});
    }

    @Override
    public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase player, ItemStack itemStack) {
        world.setBlockState(pos, getStateFromMeta(itemStack.getItemDamage()), 2);

        TileEntityExecution tileEntity = (TileEntityExecution) world.getTileEntity(pos);

        if (tileEntity != null) {
            tileEntity.setDirection((byte) EnumFacing.getHorizontal(MathHelper.floor_double((double) (player.rotationYaw * 4 / 360F) + 0.5D) & 3).getOpposite().ordinal());

            if (itemStack.hasTagCompound()) {
                NBTTagCompound nbt = itemStack.getTagCompound();

                tileEntity.setHangedMob(EnumHangedMobs.getById(nbt.getByte("HangedMob")));
                tileEntity.setHangedVillagerProfession(nbt.getInteger("HangedVillagerProfession"));

                placeWalls(world, pos);
            }
        }
    }

    public static void placeWalls(World world, BlockPos pos) {
        placeAdditionalBlock(world, pos, GSBlock.invisibleWall.getDefaultState(), Blocks.air);
    }

    @Override
    public void onBlockDestroyedByExplosion(World world, BlockPos pos, Explosion explosionIn) {
        removeWalls(world, pos);
        super.onBlockDestroyedByExplosion(world, pos, explosionIn);
    }

    @Override
    public boolean removedByPlayer(World world, BlockPos pos, EntityPlayer player, boolean willHarvest) {
        removeWalls(world, pos);
        return super.removedByPlayer(world, pos, player, willHarvest);
    }

    private static void removeWalls(World world, BlockPos pos) {
        placeAdditionalBlock(world, pos, Blocks.air.getDefaultState(), GSBlock.invisibleWall);
    }

    public static void placeAdditionalBlock(World world, BlockPos pos, IBlockState blockState, Block replaceableBlock) {
        TileEntityExecution tileEntity = (TileEntityExecution) world.getTileEntity(pos);

        if (tileEntity != null) {
            byte maxY = 0;
            byte maxX = 1;
            byte maxZ = 1;
            byte startX = 0;
            byte startZ = 0;

            EnumFacing facing = EnumFacing.values()[tileEntity.getDirection()];
            switch ((EnumExecution) world.getBlockState(pos).getValue(VARIANT)) {
                case GALLOWS:
                case GIBBET:
                    maxY = 3;
                    switch (facing) {
                        case NORTH:
                            startZ = -1;
                            break;
                        case EAST:
                            maxX = 2;
                            break;
                        case SOUTH:
                            maxZ = 2;
                            break;
                        case WEST:
                            startX = -1;
                            break;
                    }
                    break;
                case STOCKS:
                    maxY = 2;
                    if (facing == EnumFacing.NORTH || facing == EnumFacing.SOUTH) {
                        startX = -1;
                        maxX = 2;
                    } else {
                        startZ = -1;
                        maxZ = 2;
                    }
                    break;
                case BURNING_STAKE:
                    maxY = 3;
                    break;
            }
            for (byte shiftY = 0; shiftY < maxY; shiftY++) {
                for (byte shiftZ = startZ; shiftZ < maxZ; shiftZ++) {
                    for (byte shiftX = startX; shiftX < maxX; shiftX++) {
                        BlockPos newPos = new BlockPos(pos.getX() + shiftX, pos.getY() + shiftY, pos.getZ() + shiftZ);
                        if (world.getBlockState(newPos).getBlock() == replaceableBlock) {
                            world.setBlockState(new BlockPos(pos.getX() + shiftX, pos.getY() + shiftY, pos.getZ() + shiftZ), blockState);
                        }
                    }
                }
            }
        }
    }
}
