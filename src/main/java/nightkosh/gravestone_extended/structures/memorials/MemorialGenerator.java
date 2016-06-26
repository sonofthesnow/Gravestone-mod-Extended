package nightkosh.gravestone_extended.structures.memorials;

import nightkosh.gravestone_extended.config.ExtendedConfig;
import nightkosh.gravestone_extended.core.logger.GSLogger;
import nightkosh.gravestone_extended.structures.GSStructureGenerator;
import nightkosh.gravestone_extended.structures.catacombs.CatacombsGenerator;
import net.minecraft.block.material.Material;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.ChunkCoordIntPair;
import net.minecraft.world.World;
import net.minecraftforge.common.BiomeDictionary;

import java.util.*;

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
    private static List<ChunkCoordIntPair> structuresList = new ArrayList<>();

    @Override
    public boolean generate(World world, Random rand, int x, int z, EnumFacing direction, double chance, boolean isCommand) {
        if (!isCommand) {
            x = x + (16 - ComponentMemorial.X_LENGTH) / 2;
            z = z + (16 - ComponentMemorial.Z_LENGTH) / 2;
        }
        if (isCommand || (ExtendedConfig.generateMemorials && canSpawnStructureAtCoords(world, x, z, chance) && isNoWarterUnder(world, x, z))) {
            new ComponentMemorial(0, direction, rand, x, z).addComponentParts(world, rand);
            GSLogger.logInfo("Generate memorial at " + x + "x" + z);
            structuresList.add(new ChunkCoordIntPair(x, z));
            return true;
        }

        return false;
    }

    protected static boolean canSpawnStructureAtCoords(World world, int x, int z, double chance) {
        return chance < ExtendedConfig.memorialsGenerationChance && isBiomeAllowed(world, x, z) && noAnyInRange(x, z);
    }

    protected static boolean isBiomeAllowed(World world, int x, int z) {
        List<BiomeDictionary.Type> biomeTypesList = new ArrayList<>(Arrays.asList(BiomeDictionary.getTypesForBiome(world.getBiomeGenForCoords(new BlockPos(x, 0, z)))));
        return !biomeTypesList.contains(BiomeDictionary.Type.WATER);
    }

    protected static boolean noAnyInRange(int x, int z) {
        for (ChunkCoordIntPair position : structuresList) {
            if (position.chunkXPos > x - RANGE && position.chunkXPos < x + RANGE
                    && position.chunkZPos > z - RANGE && position.chunkZPos < z + RANGE) {
                return false;
            }
        }

        for (ChunkCoordIntPair position : CatacombsGenerator.getStructuresList()) {
            if (position.chunkXPos > x - CatacombsGenerator.CATACOMBS_RANGE && position.chunkXPos < x + CatacombsGenerator.CATACOMBS_RANGE
                    && position.chunkZPos > z - CatacombsGenerator.CATACOMBS_RANGE && position.chunkZPos < z + CatacombsGenerator.CATACOMBS_RANGE) {
                return false;
            }
        }

        return true;
    }

    public static List<ChunkCoordIntPair> getStructuresList() {
        return structuresList;
    }

    private static boolean isNoWarterUnder(World world, int x, int z) {
        BlockPos pos = world.getTopSolidOrLiquidBlock(new BlockPos(x, 0, z));
        return !world.getBlockState(pos).getBlock().getMaterial().equals(Material.water);
    }
}