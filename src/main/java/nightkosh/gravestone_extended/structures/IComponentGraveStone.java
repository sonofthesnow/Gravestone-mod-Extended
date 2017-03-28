package nightkosh.gravestone_extended.structures;

import net.minecraft.block.state.IBlockState;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public interface IComponentGraveStone {
    public void placeBlockAtCurrentPosition(World world, IBlockState blockState, int x, int y, int z, StructureBoundingBox boundingBox);

    public StructureBoundingBox getIBoundingBox();

    public int getIXWithOffset(int x, int z);

    public int getIYWithOffset(int y);

    public int getIZWithOffset(int x, int z);
}
