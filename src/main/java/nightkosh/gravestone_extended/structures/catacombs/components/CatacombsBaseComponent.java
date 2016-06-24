package nightkosh.gravestone_extended.structures.catacombs.components;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import nightkosh.gravestone_extended.core.GSBlock;
import nightkosh.gravestone_extended.core.Structures;
import nightkosh.gravestone_extended.structures.ComponentGraveStone;
import nightkosh.gravestone_extended.structures.ObjectsGenerationHelper;
import nightkosh.gravestone_extended.structures.catacombs.CatacombsLevel;
import nightkosh.gravestone_extended.structures.catacombs.CatacombsPileOfBonesSelector;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public abstract class CatacombsBaseComponent extends ComponentGraveStone {

    public static final float WEB_GENERATION_CHANCE = 0.05F;
    protected static final StructureComponent.BlockSelector catacombsPileOfBones = new CatacombsPileOfBonesSelector();
    protected static final IBlockState netherBrick = Blocks.nether_brick.getDefaultState();
    protected static final IBlockState nightStone = GSBlock.trap.getDefaultState();

    protected int xShift = 0;
    protected int zShift = 0;
    protected int level = 0;
    protected CatacombsBaseComponent prevComponent;
    protected CatacombsBaseComponent[] nextComponents;

    protected CatacombsBaseComponent(int componentType, EnumFacing facing) {
        this(componentType, facing, 0);
    }

    protected CatacombsBaseComponent(int componentType, EnumFacing facing, int level) {
        super(componentType, facing);
        this.level = level;
    }

    public EnumFacing getLeftDirection() {
        return getLeftDirection(this.getDirection());
    }

    /**
     * Return left direction
     *
     * @param direction Component direction
     */
    public static EnumFacing getLeftDirection(EnumFacing direction) {
        switch (direction) {
            case SOUTH:
                return EnumFacing.EAST;
            case EAST:
                return EnumFacing.NORTH;
            case WEST:
                return EnumFacing.SOUTH;
            case NORTH:
            default:
                return EnumFacing.WEST;
        }
//        return direction.rotateYCCW(); TODO
    }


    public EnumFacing getRightDirection() {
        return getRightDirection(this.getDirection());
    }

    /**
     * Return right direction
     *
     * @param direction Component direction
     */
    public static EnumFacing getRightDirection(EnumFacing direction) {
        switch (direction) {
            case SOUTH:
                return EnumFacing.WEST;
            case EAST:
                return EnumFacing.SOUTH;
            case WEST:
                return EnumFacing.NORTH;
            case NORTH:
            default:
                return EnumFacing.EAST;
        }
//        return direction.rotateY(); TODO
    }

    /**
     * Return valuable block
     */
    public static Block getValuableBlock(Random random) {
        return Structures.VALUABLE_BLOCKS[random.nextInt(Structures.VALUABLE_BLOCKS.length)];
    }

    /*
     * return ground level at x z coordinates
     */
    protected int getGroundLevel(World world, int x, int z) {
        return world.getTopSolidOrLiquidBlock(new BlockPos(x, 0, z)).getY();
    }

    public int getXEnd(Exit exit) {
        return this.getXWithOffset(exit.getX(), exit.getZ());
    }
    public int getZEnd(Exit exit) {
        return this.getZWithOffset(exit.getX(), exit.getZ());
    }

    /**
     * Return Y end coord for next component
     */
    public int getYEnd(Exit exit) {
        return this.boundingBox.minY + exit.getY();
    }

    /**
     * Return StructureGSCemeteryCatacombsStones instance
     */
    public BlockSelector getCemeteryCatacombsStones() {
        return CatacombsLevel.getCatacombsStones(this.level);
    }

    public static BlockSelector getPileOfBonesSelector() {
        return catacombsPileOfBones;
    }

    /**
     * Is component can be places here
     *
     * @param boundingBox Component bounding box
     */
    public boolean canBePlacedHere(StructureBoundingBox boundingBox) {
        if (coordBaseMode == EnumFacing.SOUTH || coordBaseMode == EnumFacing.NORTH) {
            return this.boundingBox.maxX > boundingBox.minX && this.boundingBox.minX < boundingBox.maxX
                    && this.boundingBox.maxZ - 1 > boundingBox.minZ && this.boundingBox.minZ + 1 < boundingBox.maxZ;
        } else {
            return this.boundingBox.maxX - 1 > boundingBox.minX && this.boundingBox.minX + 1 < boundingBox.maxX
                    && this.boundingBox.maxZ > boundingBox.minZ && this.boundingBox.minZ < boundingBox.maxZ;
        }
    }

    /**
     * Overwrites air and liquids from selected position downwards, stops at
     * hitting anything else.
     */
    @Override
    public void setBlockState(World world, IBlockState blockState, int xCoord, int yCoord, int zCoord, StructureBoundingBox boundingBox) {
        super.setBlockState(world, blockState, xCoord, yCoord, zCoord, boundingBox);
    }

    protected void fillDownwards(World world, IBlockState blockState, int xCoord, int yCoord, int zCoord, StructureBoundingBox boundingBox) {
        int x = this.getXWithOffset(xCoord, zCoord);
        int y = this.getYWithOffset(yCoord);
        int z = this.getZWithOffset(xCoord, zCoord);

        while ((world.isAirBlock(new BlockPos(x, y, z)) || world.getBlockState(new BlockPos(x, y, z)).getBlock().getMaterial().isLiquid() || world.getBlockState(new BlockPos(x, y, z)).getBlock().getMaterial().isReplaceable()) && y > 1) {
            world.setBlockState(new BlockPos(x, y, z), blockState, 2);
            --y;
        }
    }

    protected void fillWithRandomizedPilesOfBones(World world, StructureBoundingBox box, int startX, int startY, int startZ, int endX, int endY, int endZ, boolean p_74882_9_, Random random) {
        // reworked fillWithRandomizedBlocks
        for (int y = startY; y <= endY; ++y) {
            for (int x = startX; x <= endX; ++x) {
                for (int z = startZ; z <= endZ; ++z) {
                    if (!p_74882_9_ || this.getBlockStateFromPos(world, x, y, z, box).getBlock().getMaterial() != Material.air) {
                        //TODO wtf ??? y == startY || y == endY || x == startX || x == endX || z == startZ || z == endZ
                        getPileOfBonesSelector().selectBlocks(random, x, y, z, y == startY || y == endY || x == startX || x == endX || z == startZ || z == endZ);
                        ObjectsGenerationHelper.generatePileOfBones(this, world, x, y, z, getPileOfBonesSelector().getBlockState());
                    }
                }
            }
        }
    }

    /**
     * Return component direction
     */
    public EnumFacing getDirection() {
        return coordBaseMode;
    }

    protected List<Exit> exitList = new ArrayList<>();

    public List<Exit> getExitList() {
        return exitList;
    }

    protected void addExit(Exit exit) {
        this.exitList.add(exit);
    }

    public static class Exit {
        protected int x = 0;
        protected int y = 0;
        protected int z = 0;

        protected ComponentSide side;

        public Exit(int x, int y, int z, ComponentSide side) {
            this.x = x;
            this.y = y;
            this.z = z;
            this.side = side;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public int getZ() {
            return z;
        }

        public ComponentSide getSide() {
            return side;
        }
    }

    public static enum ComponentSide {
        FRONT,
        LEFT,
        RIGHT
    }
}
