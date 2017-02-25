package nightkosh.gravestone_extended.structures.catacombs.components;

import nightkosh.gravestone.block.BlockGraveStone;
import nightkosh.gravestone_extended.core.GSBlock;
import nightkosh.gravestone_extended.entity.helper.EntityGroupOfGravesMobSpawnerHelper;
import nightkosh.gravestone.helper.GraveGenerationHelper.EnumGraveTypeByEntity;
import nightkosh.gravestone_extended.helper.GraveInventoryHelper;
import nightkosh.gravestone_extended.structures.BoundingBoxHelper;
import nightkosh.gravestone_extended.structures.GraveGenerationHelper;
import nightkosh.gravestone_extended.structures.ObjectsGenerationHelper;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

import java.util.Random;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class Treasury extends CatacombsBaseComponent {

    public static final int X_LENGTH = 6;
    public static final int HEIGHT = 5;
    public static final int Z_LENGTH = 7;

    public Treasury(EnumFacing facing, int level, Random random, int x, int y, int z) {
        super(0, facing, level);
        Passage entrance = new Passage(this, 1, 0, 0);
        this.setEntrance(entrance);

        boundingBox = BoundingBoxHelper.getCorrectBox(facing, x, y, z, X_LENGTH, HEIGHT, Z_LENGTH, entrance);
    }

    /**
     * Build component
     */
    @Override
    public boolean addComponentParts(World world, Random random) {
        BlockSelector stoneBricks = getCemeteryCatacombsStones();
        this.fillWithAir(world, boundingBox, 1, 1, 2, 5, 3, 6);

        // block floor
        this.fillWithRandomizedBlocks(world, boundingBox, 0, 0, 1, 6, 0, 7, false, random, stoneBricks);

        // web
        this.randomlyFillWithBlocks(world, boundingBox, random, WEB_GENERATION_CHANCE, 1, 1, 1, 5, 3, 6, Blocks.web.getDefaultState(), false);

        //block ceiling
        this.fillWithRandomizedBlocks(world, boundingBox, 0, 4, 1, 6, 4, 7, false, random, stoneBricks);

        // block walls
        this.fillWithRandomizedBlocks(world, boundingBox, 0, 1, 2, 0, 3, 6, false, random, stoneBricks);
        this.fillWithRandomizedBlocks(world, boundingBox, 6, 1, 2, 6, 3, 6, false, random, stoneBricks);
        this.fillWithRandomizedBlocks(world, boundingBox, 0, 1, 1, 6, 3, 1, false, random, stoneBricks);
        this.fillWithRandomizedBlocks(world, boundingBox, 0, 1, 7, 6, 3, 7, false, random, stoneBricks);

        // clear entrance
        this.fillWithAir(world, boundingBox, 2, 1, 1, 4, 3, 1);

        // block entrance
        this.fillWithRandomizedBlocks(world, boundingBox, 2, 1, 0, 4, 3, 0, false, random, stoneBricks);

        // nether entrance
        this.fillWithBlocks(world, boundingBox, 1, 0, 0, 5, 0, 0, netherBrick);
        this.fillWithBlocks(world, boundingBox, 1, 4, 0, 5, 4, 0, netherBrick);
        this.fillWithBlocks(world, boundingBox, 1, 1, 0, 1, 3, 0, netherBrick);
        this.fillWithBlocks(world, boundingBox, 5, 1, 0, 5, 3, 0, netherBrick);

        // graves
        IBlockState graveState = GSBlock.graveStone.getDefaultState();
        IBlockState leftGraveState = graveState.withProperty(BlockGraveStone.FACING, this.getLeftDirectionForBlocks());
        IBlockState rightGraveState = graveState.withProperty(BlockGraveStone.FACING, this.getRightDirectionForBlocks());

        EntityGroupOfGravesMobSpawnerHelper spawnerHelper = GraveGenerationHelper.createSpawnerHelper(world, this.boundingBox);

        GraveGenerationHelper.placeGrave(this, world, random, 1, 1, 2, leftGraveState, spawnerHelper, EnumGraveTypeByEntity.HUMAN_GRAVES, GraveInventoryHelper.GraveContentType.TREASURY);
        GraveGenerationHelper.placeGrave(this, world, random, 1, 1, 4, leftGraveState, spawnerHelper, EnumGraveTypeByEntity.HUMAN_GRAVES, GraveInventoryHelper.GraveContentType.TREASURY);
        GraveGenerationHelper.placeGrave(this, world, random, 1, 1, 6, leftGraveState, spawnerHelper, EnumGraveTypeByEntity.HUMAN_GRAVES, GraveInventoryHelper.GraveContentType.TREASURY);
        GraveGenerationHelper.placeGrave(this, world, random, 5, 1, 2, rightGraveState, spawnerHelper, EnumGraveTypeByEntity.HUMAN_GRAVES, GraveInventoryHelper.GraveContentType.TREASURY);
        GraveGenerationHelper.placeGrave(this, world, random, 5, 1, 4, rightGraveState, spawnerHelper, EnumGraveTypeByEntity.HUMAN_GRAVES, GraveInventoryHelper.GraveContentType.TREASURY);
        GraveGenerationHelper.placeGrave(this, world, random, 5, 1, 6, rightGraveState, spawnerHelper, EnumGraveTypeByEntity.HUMAN_GRAVES, GraveInventoryHelper.GraveContentType.TREASURY);

        // TNT
        this.fillWithBlocks(world, boundingBox, 0, 0, 3, 1, 0, 3, Blocks.tnt.getDefaultState());
        this.fillWithBlocks(world, boundingBox, 0, 0, 5, 1, 0, 5, Blocks.tnt.getDefaultState());
        this.placeBlockAtCurrentPosition(world, Blocks.tnt.getDefaultState(), 0, 0, 4, boundingBox);
        this.fillWithBlocks(world, boundingBox, 5, 0, 3, 6, 0, 3, Blocks.tnt.getDefaultState());
        this.fillWithBlocks(world, boundingBox, 5, 0, 5, 6, 0, 5, Blocks.tnt.getDefaultState());
        this.placeBlockAtCurrentPosition(world, Blocks.tnt.getDefaultState(), 6, 0, 4, boundingBox);
        this.fillWithBlocks(world, boundingBox, 3, 0, 6, 3, 0, 7, Blocks.tnt.getDefaultState());

        // treasury chests
        ObjectsGenerationHelper.generateChest(this, world, random, 1, 1, 3, this.coordBaseMode, false, ObjectsGenerationHelper.EnumChestTypes.VALUABLE_CHESTS);
        ObjectsGenerationHelper.generateChest(this, world, random, 1, 1, 5, this.coordBaseMode, false, ObjectsGenerationHelper.EnumChestTypes.VALUABLE_CHESTS);
        ObjectsGenerationHelper.generateChest(this, world, random, 5, 1, 3, this.coordBaseMode, false, ObjectsGenerationHelper.EnumChestTypes.VALUABLE_CHESTS);
        ObjectsGenerationHelper.generateChest(this, world, random, 5, 1, 5, this.coordBaseMode, false, ObjectsGenerationHelper.EnumChestTypes.VALUABLE_CHESTS);
        ObjectsGenerationHelper.generateChest(this, world, random, 3, 1, 6, this.coordBaseMode, false, ObjectsGenerationHelper.EnumChestTypes.VALUABLE_CHESTS);

        // treasury column
        this.fillWithBlocks(world, boundingBox, 3, 1, 4, 3, 3, 4, getValuableBlock(random).getDefaultState());

        return true;
    }
}
