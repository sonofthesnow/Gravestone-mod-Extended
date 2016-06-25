package nightkosh.gravestone_extended.structures.catacombs;

import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import nightkosh.gravestone_extended.structures.catacombs.components.CatacombsBaseComponent;
import nightkosh.gravestone_extended.structures.catacombs.components.CatacombsEntrance;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class CatacombsUnderground {

    private final EnumFacing direction;
    private CatacombsBaseComponent entrance;

    public CatacombsUnderground(World world, Random rand, EnumFacing direction, int x, int y, int z) {
        this.direction = direction;
        prepareStructurePieces(rand, x, y, z);
        build(world, rand);
    }

    /**
     * sets up Arrays with the Structure pieces and their weights
     */
    private void prepareStructurePieces(Random rand, int x, int y, int z) {
        entrance = new CatacombsEntrance(direction, rand, x, y, z);
    }

    public final void build(World world, Random rand) {
        entrance.addComponentParts(world, rand);
        List<CatacombsBaseComponent> startComponents = new ArrayList<>();
        startComponents.add(entrance);
        CatacombsLevel level = new CatacombsLevel(startComponents, 1, world, rand);
        level = new CatacombsLevel(level.getEndParts(), 2, world, rand);
        level = new CatacombsLevel(level.getEndParts(), 3, world, rand);
        new CatacombsLevel(level.getEndParts(), 4, world, rand);
    }
}