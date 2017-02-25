package nightkosh.gravestone_extended.structures.catacombs;

import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.village.Village;
import net.minecraft.world.ChunkCoordIntPair;
import net.minecraft.world.World;
import net.minecraftforge.common.BiomeDictionary;
import nightkosh.gravestone_extended.config.ExtendedConfig;
import nightkosh.gravestone_extended.core.logger.GSLogger;
import nightkosh.gravestone_extended.structures.GSStructureGenerator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class CatacombsGenerator implements GSStructureGenerator {
    private static CatacombsGenerator instance;

    private CatacombsGenerator() {
        instance = this;
    }

    public static CatacombsGenerator getInstance() {
        if (instance == null) {
            return new CatacombsGenerator();
        } else {
            return instance;
        }
    }

    private static final int VILLAGE_RANGE = 200;
    public static final byte CATACOMBS_RANGE = 100;
    public static final int CATACOMBS_DISTANCE = 1500;
    public static final int DISTANCE_FROM_SPAWN = 1000;
    public static final double DEFAULT_GENERATION_CHANCE = 0.00025D;
    public static final int MINIMAL_SURFACE_HEIGHT = 55;

    protected static List<ChunkCoordIntPair> structuresList = new ArrayList<>();

    @Override
    public boolean generate(World world, Random rand, int x, int z, EnumFacing direction, double chance, boolean isCommand) {
        if (isCommand || (ExtendedConfig.generateCatacombs && canSpawnStructureAtCoords(world, x, z, chance) && isHeightAcceptable(world, x, z))) {
//            new Thread(() -> { // TODO ConcurrentModificationException !!
                CatacombsSurface surface = new CatacombsSurface(world, rand, x, z, direction);
                GSLogger.logInfo("Generate catacombs at " + x + "x" + z);

                if (isCommand || surface.getMausoleumY() > MINIMAL_SURFACE_HEIGHT) {//TODO !!!!!!!!!!!!!!!!!!!!!!!!!!
                    CatacombsUnderground.build(world, rand, direction, surface.getMausoleumX(), surface.getMausoleumY(), surface.getMausoleumZ());
                }

                structuresList.add(new ChunkCoordIntPair(x, z));
                GSLogger.logInfo("Catacombs was successfully generated!");
//            }).start();

            return true;
        }

        return false;
    }

    protected static boolean canSpawnStructureAtCoords(World world, int x, int z, double chance) {
        return chance < ExtendedConfig.catacombsGenerationChance && isBiomeAllowed(world, x, z) && noAnyInRange(x, z, world);
    }

    protected static boolean isBiomeAllowed(World world, int x, int z) {
        List<BiomeDictionary.Type> biomeTypesList = new ArrayList<>(Arrays.asList(BiomeDictionary.getTypesForBiome(world.getBiomeGenForCoords(new BlockPos(x, 0, z)))));
        if (!biomeTypesList.contains(BiomeDictionary.Type.WATER) && !biomeTypesList.contains(BiomeDictionary.Type.SWAMP) &&
                !biomeTypesList.contains(BiomeDictionary.Type.JUNGLE) && !biomeTypesList.contains(BiomeDictionary.Type.MAGICAL) &&
                !biomeTypesList.contains(BiomeDictionary.Type.HILLS) && !biomeTypesList.contains(BiomeDictionary.Type.MOUNTAIN)) {

            if (biomeTypesList.contains(BiomeDictionary.Type.PLAINS) || biomeTypesList.contains(BiomeDictionary.Type.FOREST) ||
                    biomeTypesList.contains(BiomeDictionary.Type.SNOWY) || biomeTypesList.contains(BiomeDictionary.Type.WASTELAND)) {
                return true;
            }
        }
        return false;
    }

    protected static boolean noAnyInRange(int x, int z, World world) {
        GSLogger.logInfo("Catacombs generation - Begin Checking area for another catacombs or villages");
        for (ChunkCoordIntPair position : structuresList) {
            if (checkStructuresInRange(position.chunkXPos, position.chunkZPos, x, z, CATACOMBS_DISTANCE)) {
                return false;
            }
        }

        if (world.villageCollectionObj != null && world.villageCollectionObj.getVillageList() != null) {
            for (Object villageObj : world.villageCollectionObj.getVillageList()) {
                BlockPos villageCenter = ((Village) villageObj).getCenter();

                if (checkStructuresInRange(villageCenter.getX(), villageCenter.getZ(), x, z, VILLAGE_RANGE)) {
                    return false;
                }

            }
        }

        if (checkStructuresInRange(world.getWorldInfo().getSpawnX(), world.getWorldInfo().getSpawnZ(), x, z, DISTANCE_FROM_SPAWN)) {
            return false;
        }

        GSLogger.logInfo("Catacombs generation - End Checking area for another catacombs or villages");
        return true;
    }

    private static boolean checkStructuresInRange(int xPos, int zPos, int x, int z, int range) {
        return xPos > x - range && xPos < x + range
                && zPos > z - range && zPos < z + range;
    }

    public static List<ChunkCoordIntPair> getStructuresList() {
        return structuresList;
    }

    private static boolean isHeightAcceptable(World world, int x, int z) {
        GSLogger.logInfo("Catacombs generation - Begin Checking area height");
        int height = 0;
        int count = 0;
        for (int xPos = x; xPos < x + 16; xPos++) {
            for (int zPos = z; zPos < z + 16; zPos++) {
                height += world.getTopSolidOrLiquidBlock(new BlockPos(xPos, 0, zPos)).getY();
                count++;
            }
        }

        GSLogger.logInfo("Catacombs generation - End Checking area height");
        return (height / count) < ExtendedConfig.maxCatacombsHeight;
    }
}
