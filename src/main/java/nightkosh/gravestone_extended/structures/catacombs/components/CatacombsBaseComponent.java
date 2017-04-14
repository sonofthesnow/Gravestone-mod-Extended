package nightkosh.gravestone_extended.structures.catacombs.components;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
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
    protected static final IBlockState netherBrick = Blocks.NETHER_BRICK.getDefaultState();
    protected static final IBlockState nightStone = GSBlock.trap.getDefaultState();

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

    public EnumFacing getLeftDirectionForBlocks() {
        switch (this.getDirection()) {
            case EAST:
            case WEST:
                return EnumFacing.SOUTH;
            case SOUTH:
            case NORTH:
            default:
                return EnumFacing.EAST;
        }
    }

    public EnumFacing getRightDirectionForBlocks() {
        switch (this.getDirection()) {
            case EAST:
            case WEST:
                return EnumFacing.NORTH;
            case SOUTH:
            case NORTH:
            default:
                return EnumFacing.WEST;
        }
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
        return direction.rotateYCCW();
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
        return direction.rotateY();
    }

    public static Block getValuableBlock(Random random) {
        return Structures.VALUABLE_BLOCKS[random.nextInt(Structures.VALUABLE_BLOCKS.length)];
    }

    protected int getGroundLevel(World world, int x, int z) {
        return world.getTopSolidOrLiquidBlock(new BlockPos(x, 0, z)).getY();
    }

    public int getXEnd(Passage exit) {
        return this.getXWithOffset(exit.getX(), exit.getZ());
    }
    public int getZEnd(Passage exit) {
        return this.getZWithOffset(exit.getX(), exit.getZ());
    }

    public int getYEnd(Passage exit) {
        return this.boundingBox.minY + exit.getY();
    }

    public BlockSelector getCemeteryCatacombsStones() {
        return CatacombsLevel.getCatacombsStones(this.level);
    }

    public static BlockSelector getPileOfBonesSelector() {
        return catacombsPileOfBones;
    }

    public boolean canBePlacedHere(StructureBoundingBox boundingBox) {
        if (getCoordBaseMode() == EnumFacing.SOUTH || getCoordBaseMode() == EnumFacing.NORTH) {
            return this.boundingBox.maxX > boundingBox.minX && this.boundingBox.minX < boundingBox.maxX
                    && this.boundingBox.maxZ - 1 > boundingBox.minZ && this.boundingBox.minZ + 1 < boundingBox.maxZ;
        } else {
            return this.boundingBox.maxX - 1 > boundingBox.minX && this.boundingBox.minX + 1 < boundingBox.maxX
                    && this.boundingBox.maxZ > boundingBox.minZ && this.boundingBox.minZ < boundingBox.maxZ;
        }
    }

    @Override
    public void setBlockState(World world, IBlockState blockState, int xCoord, int yCoord, int zCoord, StructureBoundingBox boundingBox) {
        super.setBlockState(world, blockState, xCoord, yCoord, zCoord, boundingBox);
    }

    protected void fillDownwards(World world, IBlockState blockState, int xCoord, int yCoord, int zCoord, StructureBoundingBox boundingBox) {
        int x = this.getXWithOffset(xCoord, zCoord);
        int y = this.getYWithOffset(yCoord);
        int z = this.getZWithOffset(xCoord, zCoord);

        while ((world.isAirBlock(new BlockPos(x, y, z)) || world.getBlockState(new BlockPos(x, y, z)).getBlock().getMaterial(null).isLiquid() ||
                world.getBlockState(new BlockPos(x, y, z)).getBlock().getMaterial(null).isReplaceable()) && y > 1) {
            world.setBlockState(new BlockPos(x, y, z), blockState, 2);
            --y;
        }
    }

    protected void fillWithRandomizedPilesOfBones(World world, StructureBoundingBox box, int startX, int startY, int startZ, int endX, int endY, int endZ, boolean p_74882_9_, Random random) {
        // reworked fillWithRandomizedBlocks
        for (int y = startY; y <= endY; ++y) {
            for (int x = startX; x <= endX; ++x) {
                for (int z = startZ; z <= endZ; ++z) {
                    if (!p_74882_9_ || this.getBlockStateFromPos(world, x, y, z, box).getBlock().getMaterial(null) != Material.AIR) {
                        //TODO wtf ??? y == startY || y == endY || x == startX || x == endX || z == startZ || z == endZ
                        getPileOfBonesSelector().selectBlocks(random, x, y, z, y == startY || y == endY || x == startX || x == endX || z == startZ || z == endZ);
                        ObjectsGenerationHelper.generatePileOfBones(this, world, x, y, z, getPileOfBonesSelector().getBlockState());
                    }
                }
            }
        }
    }

    public EnumFacing getDirection() {
        return getCoordBaseMode();
    }

    protected Passage entrance;
    protected List<Passage> exitList = new ArrayList<>();
    protected List<Passage> requiredExitList = new ArrayList<>();

    public Passage getEntrance() {
        return entrance;
    }

    protected void setEntrance(Passage entrance) {
        this.entrance = entrance;
    }

    public List<Passage> getExitList() {
        return exitList;
    }

    protected void addExit(Passage exit) {
        this.exitList.add(exit);
    }

    public List<Passage> getRequiredExitList() {
        return requiredExitList;
    }

    protected void addRequiredExit(Passage exit) {
        this.requiredExitList.add(exit);
    }

    public static class Passage {
        protected int x = 0;
        protected int y = 0;
        protected int z = 0;
        protected boolean required = false;
        protected PassageState state;
        protected ComponentSide side;
        protected CatacombsBaseComponent component;

        public Passage(CatacombsBaseComponent component, int x, int y, int z) {
            this.component = component;
            this.x = x;
            this.y = y;
            this.z = z;
            this.state = PassageState.OPEN;
        }

        public Passage(CatacombsBaseComponent component, int x, int y, int z, ComponentSide side) {
            this(component, x, y, z);
            this.side = side;
        }

        public Passage(CatacombsBaseComponent component, int x, int y, int z, ComponentSide side, boolean required) {
            this(component, x, y, z);
            this.required = required;
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

        public boolean isRequired() {
            return required;
        }

        public CatacombsBaseComponent getComponent() {
            return component;
        }

        public PassageState getState() {
            return state;
        }

        public void setState(PassageState state) {
            this.state = state;
        }
    }

    public static enum ComponentSide {
        FRONT,
        LEFT,
        RIGHT
    }

    public static enum PassageState {
        OPEN,
        CLOSED,
        CONNECTED
    }
}
