package nightkosh.gravestone_extended.structures.memorials;

import net.minecraft.block.material.Material;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;
import net.minecraftforge.common.BiomeDictionary;
import nightkosh.gravestone_extended.config.ExtendedConfig;
import nightkosh.gravestone_extended.core.logger.GSLogger;
import nightkosh.gravestone_extended.structures.GSStructureGenerator;
import nightkosh.gravestone_extended.structures.catacombs.CatacombsGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class MemorialGenerator implements GSStructureGenerator {

    private static MemorialGenerator instance;

    private MemorialGenerator() {
        instance = this;
    }

    public static MemorialGenerator getInstance() {
        if (instance == null) {
            return new MemorialGenerator();
        } else {
            return instance;
        }
    }

    public static final double DEFAULT_GENERATION_CHANCE = 0.05;
    public static final short RANGE = 400;
    private static List<ChunkPos> structuresList = new ArrayList<>();

    @Override
    public boolean generate(World world, Random rand, int x, int z, EnumFacing direction, double chance, boolean isCommand) {
        if (!isCommand) {
            x = x + (16 - ComponentMemorial.X_LENGTH) / 2;
            z = z + (16 - ComponentMemorial.Z_LENGTH) / 2;
        }
        if (isCommand || (ExtendedConfig.generateMemorials && canSpawnStructureAtCoords(world, x, z, chance) && isNoWarterUnder(world, x, z))) {
            new ComponentMemorial(0, direction, rand, x, z).addComponentParts(world, rand);
            GSLogger.logInfo("Generate memorial at " + x + "x" + z);
            structuresList.add(new ChunkPos(x, z));
            return true;
        }

        return false;
    }

    protected static boolean canSpawnStructureAtCoords(World world, int x, int z, double chance) {
        return chance < ExtendedConfig.memorialsGenerationChance && isBiomeAllowed(world, x, z) && noAnyInRange(x, z);
    }

    protected static boolean isBiomeAllowed(World world, int x, int z) {
        List<BiomeDictionary.Type> biomeTypesList = new ArrayList<>(BiomeDictionary.getTypes(world.getBiome(new BlockPos(x, 0, z))));
        return !biomeTypesList.contains(BiomeDictionary.Type.WATER);
    }

    protected static boolean noAnyInRange(int x, int z) {
        for (ChunkPos position : structuresList) {
            if (position.chunkXPos > x - RANGE && position.chunkXPos < x + RANGE
                    && position.chunkZPos > z - RANGE && position.chunkZPos < z + RANGE) {
                return false;
            }
        }

        for (ChunkPos position : CatacombsGenerator.getStructuresList()) {
            if (position.chunkXPos > x - CatacombsGenerator.CATACOMBS_RANGE && position.chunkXPos < x + CatacombsGenerator.CATACOMBS_RANGE
                    && position.chunkZPos > z - CatacombsGenerator.CATACOMBS_RANGE && position.chunkZPos < z + CatacombsGenerator.CATACOMBS_RANGE) {
                return false;
            }
        }

        return true;
    }

    public static List<ChunkPos> getStructuresList() {
        return structuresList;
    }

    private static boolean isNoWarterUnder(World world, int x, int z) {
        BlockPos pos = world.getTopSolidOrLiquidBlock(new BlockPos(x, 0, z));
        return !world.getBlockState(pos).getBlock().getMaterial(null).equals(Material.WATER);
    }
}