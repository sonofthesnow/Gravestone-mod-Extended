package nightkosh.gravestone_extended.structures.catacombs.components;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import nightkosh.gravestone.block.BlockGraveStone;
import nightkosh.gravestone.helper.GraveGenerationHelper.EnumGraveTypeByEntity;
import nightkosh.gravestone_extended.core.GSBlock;
import nightkosh.gravestone_extended.entity.helper.EntityGroupOfGravesMobSpawnerHelper;
import nightkosh.gravestone_extended.helper.GraveInventoryHelper;
import nightkosh.gravestone_extended.structures.GraveGenerationHelper;

import java.util.Random;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class GraveYard extends CatacombsBaseComponent {

    public GraveYard(EnumFacing direction, Random random, StructureBoundingBox structureBoundingBox) {
        super(0, direction);
        boundingBox = structureBoundingBox;
    }

    /**
     * Build component
     */
    @Override
    public boolean addComponentParts(World world, Random random) {
        int positionX, positionZ, y;

        EntityGroupOfGravesMobSpawnerHelper spawnerHelper = GraveGenerationHelper.createSpawnerHelper(world, this.boundingBox);

        for (int x = 0; x < 11; x += 2) {
            for (int z = 0; z < 11; z += 2) {
                if (random.nextDouble() < 0.05) {
                    positionX = getXWithOffset(x + 1, z + 1);
                    positionZ = getZWithOffset(x + 1, z + 1);
                    y = world.getTopSolidOrLiquidBlock(new BlockPos(positionX, 0, positionZ)).getY() - boundingBox.minY;
                    this.placeBlockAtCurrentPosition(world, Blocks.DEADBUSH.getDefaultState(), x + 1, y, z + 1, boundingBox);
                }

                if (random.nextInt(5) < 2) {
                    positionX = getXWithOffset(x, z);
                    positionZ = getZWithOffset(x, z);
                    y = world.getTopSolidOrLiquidBlock(new BlockPos(positionX, 0, positionZ)).getY() - boundingBox.minY;

                    if (GraveGenerationHelper.canPlaceGrave(world, positionX, boundingBox.minY + y, positionZ, boundingBox.maxY)) {
                        IBlockState graveState = GSBlock.graveStone.getDefaultState().withProperty(BlockGraveStone.FACING, this.getCoordBaseMode().getOpposite());
                        GraveGenerationHelper.placeGrave(this, world, random, x, y, z, graveState, spawnerHelper, EnumGraveTypeByEntity.HUMAN_GRAVES, GraveInventoryHelper.GraveContentType.JUNK);
                    }
                }
            }
        }

        return true;
    }
}
