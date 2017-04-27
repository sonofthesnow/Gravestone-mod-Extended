package nightkosh.gravestone_extended.structures.catacombs.components;

import net.minecraft.block.BlockStairs;
import net.minecraft.block.BlockStoneBrick;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import nightkosh.gravestone_extended.config.ExtendedConfig;
import nightkosh.gravestone_extended.structures.BoundingBoxHelper;
import nightkosh.gravestone_extended.structures.ObjectsGenerationHelper;

import java.util.Random;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class Crossing extends CatacombsBaseComponent {

    public static final int X_LENGTH = 13;
    public static final int HEIGHT = 6;
    public static final int Z_LENGTH = 13;

    public Crossing(EnumFacing facing, int level, Random random, int x, int y, int z) {
        super(0, facing, level);

        Passage entrance = new Passage(this, 4, 0, 0);
        this.setEntrance(entrance);

        this.addRequiredExit(new Passage(this, 4, 0, 12, ComponentSide.FRONT, true));
        switch (facing) {
            case SOUTH:
                this.addRequiredExit(new Passage(this, 12, 0, 4, ComponentSide.LEFT, true));
                this.addRequiredExit(new Passage(this, 0, 0, 4, ComponentSide.RIGHT, true));
                break;
            case NORTH:
                this.addRequiredExit(new Passage(this, 0, 0, 8, ComponentSide.LEFT, true));
                this.addRequiredExit(new Passage(this, 12, 0, 8, ComponentSide.RIGHT, true));
                break;
            case WEST:
                this.addRequiredExit(new Passage(this, 12, 0, 8, ComponentSide.LEFT, true));
                this.addRequiredExit(new Passage(this, 0, 0, 8, ComponentSide.RIGHT, true));
                break;
            case EAST:
                this.addRequiredExit(new Passage(this, 0, 0, 4, ComponentSide.LEFT, true));
                this.addRequiredExit(new Passage(this, 12, 0, 4, ComponentSide.RIGHT, true));
                break;
        }

        boundingBox = BoundingBoxHelper.getCorrectBox(facing, x, y, z, X_LENGTH, HEIGHT, Z_LENGTH, entrance);
    }

    /**
     * Build component
     */
    @Override
    public boolean addComponentParts(World world, Random random) {
        BlockSelector stoneBricks = getCemeteryCatacombsStones();
        this.fillWithAir(world, boundingBox, 1, 1, 1, 11, 4, 11);

        // web
        this.randomlyFillWithBlocks(world, boundingBox, random, WEB_GENERATION_CHANCE, 1, 1, 1, 11, 4, 11, Blocks.WEB.getDefaultState(), false);
        // piles of bones
        if (ExtendedConfig.generatePilesOfBones) {
            this.fillWithRandomizedPilesOfBones(world, boundingBox, 1, 1, 1, 12, 1, 12, false, random);
        }

        // trap floor
        this.fillWithBlocks(world, boundingBox, 0, 0, 0, 0, 0, 12, nightStone);
        this.fillWithBlocks(world, boundingBox, 4, 0, 0, 4, 0, 12, nightStone);
        this.fillWithBlocks(world, boundingBox, 8, 0, 0, 8, 0, 12, nightStone);
        this.fillWithBlocks(world, boundingBox, 12, 0, 0, 12, 0, 12, nightStone);
        this.fillWithBlocks(world, boundingBox, 1, 0, 0, 11, 0, 0, nightStone);
        this.fillWithBlocks(world, boundingBox, 1, 0, 4, 11, 0, 4, nightStone);
        this.fillWithBlocks(world, boundingBox, 1, 0, 8, 11, 0, 8, nightStone);
        this.fillWithBlocks(world, boundingBox, 1, 0, 12, 11, 0, 12, nightStone);

        // stoneBrick floor
        this.fillWithRandomizedBlocks(world, boundingBox, 1, 0, 1, 3, 0, 3, false, random, stoneBricks);
        this.fillWithRandomizedBlocks(world, boundingBox, 1, 0, 5, 3, 0, 7, false, random, stoneBricks);
        this.fillWithRandomizedBlocks(world, boundingBox, 1, 0, 9, 3, 0, 11, false, random, stoneBricks);
        this.fillWithRandomizedBlocks(world, boundingBox, 5, 0, 1, 7, 0, 3, false, random, stoneBricks);
        this.fillWithRandomizedBlocks(world, boundingBox, 5, 0, 9, 7, 0, 11, false, random, stoneBricks);
        this.fillWithRandomizedBlocks(world, boundingBox, 9, 0, 1, 11, 0, 3, false, random, stoneBricks);
        this.fillWithRandomizedBlocks(world, boundingBox, 9, 0, 5, 11, 0, 7, false, random, stoneBricks);
        this.fillWithRandomizedBlocks(world, boundingBox, 9, 0, 9, 11, 0, 11, false, random, stoneBricks);

        // nether ceiling
        this.fillWithBlocks(world, boundingBox, 0, 5, 0, 0, 5, 12, netherBrick);
        this.fillWithBlocks(world, boundingBox, 4, 5, 0, 4, 5, 12, netherBrick);
        this.fillWithBlocks(world, boundingBox, 8, 5, 0, 8, 5, 12, netherBrick);
        this.fillWithBlocks(world, boundingBox, 12, 5, 0, 12, 5, 12, netherBrick);
        this.fillWithBlocks(world, boundingBox, 1, 5, 0, 11, 5, 0, netherBrick);
        this.fillWithBlocks(world, boundingBox, 1, 5, 4, 11, 5, 4, netherBrick);
        this.fillWithBlocks(world, boundingBox, 1, 5, 8, 11, 5, 8, netherBrick);
        this.fillWithBlocks(world, boundingBox, 1, 5, 12, 11, 5, 12, netherBrick);

        // stoneBrick ceiling
        this.fillWithRandomizedBlocks(world, boundingBox, 1, 5, 1, 3, 5, 3, false, random, stoneBricks);
        this.fillWithRandomizedBlocks(world, boundingBox, 1, 5, 5, 3, 5, 7, false, random, stoneBricks);
        this.fillWithRandomizedBlocks(world, boundingBox, 1, 5, 9, 3, 5, 11, false, random, stoneBricks);
        this.fillWithRandomizedBlocks(world, boundingBox, 5, 5, 1, 7, 5, 3, false, random, stoneBricks);
        this.fillWithRandomizedBlocks(world, boundingBox, 5, 5, 9, 7, 5, 11, false, random, stoneBricks);
        this.fillWithRandomizedBlocks(world, boundingBox, 9, 5, 1, 11, 5, 3, false, random, stoneBricks);
        this.fillWithRandomizedBlocks(world, boundingBox, 9, 5, 5, 11, 5, 7, false, random, stoneBricks);
        this.fillWithRandomizedBlocks(world, boundingBox, 9, 5, 9, 11, 5, 11, false, random, stoneBricks);

        // cutted stoneBrick floor and ceiling
        IBlockState chiseledStoneState = Blocks.STONEBRICK.getDefaultState().withProperty(BlockStoneBrick.VARIANT, BlockStoneBrick.EnumType.CHISELED);
        this.fillWithBlocks(world, boundingBox, 5, 0, 5, 7, 0, 7, chiseledStoneState);
        this.fillWithBlocks(world, boundingBox, 5, 5, 5, 7, 5, 7, chiseledStoneState);

        // nether walls
        this.fillWithBlocks(world, boundingBox, 0, 1, 0, 0, 4, 12, netherBrick);
        this.fillWithBlocks(world, boundingBox, 12, 1, 0, 12, 4, 12, netherBrick);
        this.fillWithBlocks(world, boundingBox, 1, 1, 12, 11, 4, 12, netherBrick);
        this.fillWithBlocks(world, boundingBox, 1, 1, 0, 11, 4, 0, netherBrick);

        // columns
        this.fillWithRandomizedBlocks(world, boundingBox, 4, 1, 8, 4, 4, 8, false, random, stoneBricks);
        this.fillWithRandomizedBlocks(world, boundingBox, 4, 1, 4, 4, 4, 4, false, random, stoneBricks);
        this.fillWithRandomizedBlocks(world, boundingBox, 8, 1, 8, 8, 4, 8, false, random, stoneBricks);
        this.fillWithRandomizedBlocks(world, boundingBox, 8, 1, 4, 8, 4, 4, false, random, stoneBricks);

        // fire
        this.placeBlockAtCurrentPosition(world, Blocks.NETHERRACK.getDefaultState(), 1, 1, 11, boundingBox);
        this.placeBlockAtCurrentPosition(world, Blocks.NETHERRACK.getDefaultState(), 1, 1, 1, boundingBox);
        this.placeBlockAtCurrentPosition(world, Blocks.NETHERRACK.getDefaultState(), 11, 1, 11, boundingBox);
        this.placeBlockAtCurrentPosition(world, Blocks.NETHERRACK.getDefaultState(), 11, 1, 1, boundingBox);
        this.placeBlockAtCurrentPosition(world, Blocks.FIRE.getDefaultState(), 1, 2, 11, boundingBox);
        this.placeBlockAtCurrentPosition(world, Blocks.FIRE.getDefaultState(), 1, 2, 1, boundingBox);
        this.placeBlockAtCurrentPosition(world, Blocks.FIRE.getDefaultState(), 11, 2, 11, boundingBox);
        this.placeBlockAtCurrentPosition(world, Blocks.FIRE.getDefaultState(), 11, 2, 1, boundingBox);

        // fire stairs
        IBlockState netherBrickStairsState = Blocks.NETHER_BRICK_STAIRS.getDefaultState();
        IBlockState netherBrickStairsTopState = netherBrickStairsState.withProperty(BlockStairs.FACING, this.getCoordBaseMode().getOpposite());
        IBlockState netherBrickStairsBotState = netherBrickStairsState.withProperty(BlockStairs.FACING, this.getCoordBaseMode());
        IBlockState netherBrickStairsLeftState = netherBrickStairsState.withProperty(BlockStairs.FACING, this.getLeftDirectionForBlocks());
        IBlockState netherBrickStairsRightState = netherBrickStairsState.withProperty(BlockStairs.FACING, this.getRightDirectionForBlocks());
        this.placeBlockAtCurrentPosition(world, netherBrickStairsTopState, 1, 1, 2, boundingBox);
        this.placeBlockAtCurrentPosition(world, netherBrickStairsRightState, 2, 1, 2, boundingBox);
        this.placeBlockAtCurrentPosition(world, netherBrickStairsRightState, 2, 1, 1, boundingBox);
        this.placeBlockAtCurrentPosition(world, netherBrickStairsTopState, 11, 1, 2, boundingBox);
        this.placeBlockAtCurrentPosition(world, netherBrickStairsLeftState, 10, 1, 2, boundingBox);
        this.placeBlockAtCurrentPosition(world, netherBrickStairsLeftState, 10, 1, 1, boundingBox);
        this.placeBlockAtCurrentPosition(world, netherBrickStairsBotState, 1, 1, 10, boundingBox);
        this.placeBlockAtCurrentPosition(world, netherBrickStairsRightState, 2, 1, 10, boundingBox);
        this.placeBlockAtCurrentPosition(world, netherBrickStairsRightState, 2, 1, 11, boundingBox);
        this.placeBlockAtCurrentPosition(world, netherBrickStairsBotState, 11, 1, 10, boundingBox);
        this.placeBlockAtCurrentPosition(world, netherBrickStairsLeftState, 10, 1, 10, boundingBox);
        this.placeBlockAtCurrentPosition(world, netherBrickStairsLeftState, 10, 1, 11, boundingBox);

        // fill exit
        this.fillWithRandomizedBlocks(world, boundingBox, 5, 1, 12, 7, 3, 12, false, random, stoneBricks);
        this.fillWithRandomizedBlocks(world, boundingBox, 0, 1, 5, 0, 3, 7, false, random, stoneBricks);
        this.fillWithRandomizedBlocks(world, boundingBox, 12, 1, 5, 12, 3, 7, false, random, stoneBricks);

        // clear enter
        this.fillWithAir(world, boundingBox, 5, 1, 0, 7, 3, 0);

        // spawner
        ObjectsGenerationHelper.generateSpawner(this, world, random, 6, 1, 6);

        return true;
    }
}
