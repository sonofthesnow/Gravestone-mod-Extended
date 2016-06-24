package nightkosh.gravestone_extended.structures.catacombs.components;

import net.minecraft.block.BlockTripWireHook;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import nightkosh.gravestone_extended.structures.BoundingBoxHelper;
import nightkosh.gravestone_extended.structures.ObjectsGenerationHelper;

import java.util.Random;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class TrapCorridor extends CatacombsBaseComponent {

    public static final int X_LENGTH = 6;
    public static final int HEIGHT = 5;
    public static final int Z_LENGTH = 5;

    public TrapCorridor(EnumFacing facing, int level, Random random, int x, int y, int z) {
        super(0, facing, level);
        xShift = 1;
        boundingBox = BoundingBoxHelper.getCorrectBox(facing, x, y, z, X_LENGTH, HEIGHT, Z_LENGTH, xShift);
        this.addExit(new Exit(1, 0, Z_LENGTH - 1, ComponentSide.FRONT));
    }

    /**
     * Build component
     */
    @Override
    public boolean addComponentParts(World world, Random random) {
        BlockSelector stoneBricks = getCemeteryCatacombsStones();
        this.fillWithAir(world, boundingBox, 2, 1, 0, 4, 3, 3);

        // block floor
        this.fillWithRandomizedBlocks(world, boundingBox, 1, 0, 1, 5, 0, 3, false, random, stoneBricks);

        // trap floor
        this.fillWithBlocks(world, boundingBox, 1, 0, 0, 5, 0, 0, nightStone);

        // web
        this.randomlyFillWithBlocks(world, boundingBox, random, WEB_GENERATION_CHANCE, 2, 1, 0, 4, 3, 3, Blocks.web.getDefaultState(), false);

        // neter ceiling
        this.fillWithBlocks(world, boundingBox, 1, 4, 0, 5, 4, 3, netherBrick);

        // block walls
        this.fillWithRandomizedBlocks(world, boundingBox, 1, 1, 1, 1, 3, 3, false, random, stoneBricks);
        this.fillWithRandomizedBlocks(world, boundingBox, 5, 1, 1, 5, 3, 3, false, random, stoneBricks);
        this.fillWithRandomizedBlocks(world, boundingBox, 1, 0, 4, 5, 4, 4, false, random, stoneBricks);

        // nether walls
        this.fillWithBlocks(world, boundingBox, 1, 1, 0, 1, 3, 0, netherBrick);
        this.fillWithBlocks(world, boundingBox, 5, 1, 0, 5, 3, 0, netherBrick);
        // blocks
        this.placeBlockAtCurrentPosition(world, Blocks.stonebrick.getDefaultState(), 0, 1, 2, boundingBox);
        this.placeBlockAtCurrentPosition(world, Blocks.stonebrick.getDefaultState(), 6, 1, 2, boundingBox);

        // tripWire hook
        this.placeBlockAtCurrentPosition(world, Blocks.tripwire_hook.getDefaultState().withProperty(BlockTripWireHook.FACING, this.getRightDirection(this.coordBaseMode)), 1, 1, 2, boundingBox);
        this.placeBlockAtCurrentPosition(world, Blocks.tripwire_hook.getDefaultState().withProperty(BlockTripWireHook.FACING, this.getLeftDirection(this.coordBaseMode)), 5, 1, 2, boundingBox);

        // tripWire
        this.fillWithBlocks(world, boundingBox, 2, 1, 2, 4, 1, 2, Blocks.tripwire.getDefaultState());

        //dispencer
        ObjectsGenerationHelper.generateDispenser(world, this, random, 0, 2, 2, this.getRightDirection(this.coordBaseMode));
        ObjectsGenerationHelper.generateDispenser(world, this, random, 6, 2, 2, this.getLeftDirection(this.coordBaseMode));
        this.placeBlockAtCurrentPosition(world, Blocks.air.getDefaultState(), 1, 2, 2, boundingBox);
        this.placeBlockAtCurrentPosition(world, Blocks.air.getDefaultState(), 5, 2, 2, boundingBox);

        return true;
    }
}
