package nightkosh.gravestone_extended.structures.graves;

import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;
import nightkosh.gravestone_extended.config.ExtendedConfig;

import java.util.Random;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class OpenedGraveGenerator extends SingleGraveGenerator {
    private static OpenedGraveGenerator instance = new OpenedGraveGenerator();

    private OpenedGraveGenerator() {
    }

    public static OpenedGraveGenerator getInstance() {
        return instance;
    }

    @Override
    public boolean generate(World world, Random rand, int x, int z, EnumFacing direction, double chance, boolean isCommand) {
        if (!isCommand) {
            x += 7;
            z += 7;
        }
        if (isCommand || (ExtendedConfig.generateSingleGraves && canSpawnStructureAtCoords(world, x, z, chance))) {
            new ComponentOpenedGrave(0, direction, rand, x, z).addComponentParts(world, rand);
            structuresList.add(new ChunkPos(x, z));
            return true;
        }

        return false;
    }
}
