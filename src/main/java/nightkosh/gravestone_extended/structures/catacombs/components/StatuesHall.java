package nightkosh.gravestone_extended.structures.catacombs.components;

import nightkosh.gravestone_extended.block.BlockMemorial;
import nightkosh.gravestone_extended.config.ExtendedConfig;
import nightkosh.gravestone_extended.core.MobSpawn;
import nightkosh.gravestone_extended.structures.BoundingBoxHelper;
import nightkosh.gravestone_extended.structures.MemorialGenerationHelper;
import nightkosh.gravestone_extended.structures.MobSpawnHelper;
import nightkosh.gravestone_extended.structures.ObjectsGenerationHelper;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

import java.util.Random;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class StatuesHall extends CatacombsBaseComponent {

    public static final int X_LENGTH = 11;
    public static final int HEIGHT = 6;
    public static final int Z_LENGTH = 19;

    public StatuesHall(EnumFacing facing, int level, Random random, int x, int y, int z) {
        super(0, facing, level);
        Passage entrance = new Passage(3, 0, 0);

        this.setEntrance(entrance);
        this.addExit(new Passage(3, 0, Z_LENGTH - 1, ComponentSide.FRONT));

        boundingBox = BoundingBoxHelper.getCorrectBox(facing, x, y, z, X_LENGTH, HEIGHT, Z_LENGTH, entrance);
    }

    /**
     * Build component
     */
    @Override
    public boolean addComponentParts(World world, Random random) {
        BlockSelector stoneBricks = getCemeteryCatacombsStones();
        this.fillWithAir(world, boundingBox, 1, 1, 1, 9, 4, 17);
        this.fillWithAir(world, boundingBox, 4, 1, 0, 6, 3, 0);

        // blocks floor and ceiling
        this.fillWithRandomizedBlocks(world, boundingBox, 1, 0, 1, 9, 0, 17, false, random, stoneBricks);
        this.fillWithRandomizedBlocks(world, boundingBox, 1, 5, 1, 9, 5, 17, false, random, stoneBricks);

        // web
        this.randomlyFillWithBlocks(world, boundingBox, random, WEB_GENERATION_CHANCE, 1, 1, 1, 10, 4, 18, Blocks.web.getDefaultState(), false);
        // piles of bones
        if (ExtendedConfig.generatePilesOfBones) {
            this.fillWithRandomizedPilesOfBones(world, boundingBox, 1, 1, 1, 10, 1, 18, false, random);
        }

        // blocks wall
        this.fillWithRandomizedBlocks(world, boundingBox, 0, 1, 1, 0, 4, 17, false, random, stoneBricks);
        this.fillWithRandomizedBlocks(world, boundingBox, 10, 1, 1, 10, 4, 17, false, random, stoneBricks);
        this.fillWithRandomizedBlocks(world, boundingBox, 1, 1, 0, 2, 4, 0, false, random, stoneBricks);
        this.fillWithRandomizedBlocks(world, boundingBox, 8, 1, 0, 9, 4, 0, false, random, stoneBricks);
        this.fillWithRandomizedBlocks(world, boundingBox, 1, 1, 18, 2, 4, 18, false, random, stoneBricks);
        this.fillWithRandomizedBlocks(world, boundingBox, 8, 1, 18, 9, 4, 18, false, random, stoneBricks);

        // nether floor and ceiling
        this.fillWithBlocks(world, boundingBox, 0, 0, 0, 0, 0, 18, netherBrick);
        this.fillWithBlocks(world, boundingBox, 3, 0, 0, 3, 0, 18, netherBrick);
        this.fillWithBlocks(world, boundingBox, 7, 0, 0, 7, 0, 18, netherBrick);
        this.fillWithBlocks(world, boundingBox, 10, 0, 0, 10, 0, 18, netherBrick);
        this.fillWithBlocks(world, boundingBox, 0, 5, 0, 0, 5, 18, netherBrick);
        this.fillWithBlocks(world, boundingBox, 3, 5, 0, 3, 5, 18, netherBrick);
        this.fillWithBlocks(world, boundingBox, 7, 5, 0, 7, 5, 18, netherBrick);
        this.fillWithBlocks(world, boundingBox, 10, 5, 0, 10, 5, 18, netherBrick);
        this.fillWithBlocks(world, boundingBox, 1, 0, 0, 9, 0, 0, netherBrick);
        this.fillWithBlocks(world, boundingBox, 1, 0, 3, 9, 0, 3, netherBrick);
        this.fillWithBlocks(world, boundingBox, 1, 0, 6, 9, 0, 6, netherBrick);
        this.fillWithBlocks(world, boundingBox, 1, 0, 9, 9, 0, 9, netherBrick);
        this.fillWithBlocks(world, boundingBox, 1, 0, 12, 9, 0, 12, netherBrick);
        this.fillWithBlocks(world, boundingBox, 1, 0, 15, 9, 0, 15, netherBrick);
        this.fillWithBlocks(world, boundingBox, 1, 0, 18, 9, 0, 18, netherBrick);
        this.fillWithBlocks(world, boundingBox, 1, 5, 0, 9, 5, 0, netherBrick);
        this.fillWithBlocks(world, boundingBox, 1, 5, 3, 9, 5, 3, netherBrick);
        this.fillWithBlocks(world, boundingBox, 1, 5, 6, 9, 5, 6, netherBrick);
        this.fillWithBlocks(world, boundingBox, 1, 5, 9, 9, 5, 9, netherBrick);
        this.fillWithBlocks(world, boundingBox, 1, 5, 12, 9, 5, 12, netherBrick);
        this.fillWithBlocks(world, boundingBox, 1, 5, 15, 9, 5, 15, netherBrick);
        this.fillWithBlocks(world, boundingBox, 1, 5, 18, 9, 5, 18, netherBrick);

        // nether wall
        this.fillWithBlocks(world, boundingBox, 0, 1, 0, 0, 4, 0, netherBrick);
        this.fillWithBlocks(world, boundingBox, 0, 1, 3, 0, 4, 3, netherBrick);
        this.fillWithBlocks(world, boundingBox, 0, 1, 6, 0, 4, 6, netherBrick);
        this.fillWithBlocks(world, boundingBox, 0, 1, 9, 0, 4, 9, netherBrick);
        this.fillWithBlocks(world, boundingBox, 0, 1, 12, 0, 4, 12, netherBrick);
        this.fillWithBlocks(world, boundingBox, 0, 1, 15, 0, 4, 15, netherBrick);
        this.fillWithBlocks(world, boundingBox, 0, 1, 18, 0, 4, 18, netherBrick);
        this.fillWithBlocks(world, boundingBox, 10, 1, 0, 10, 4, 0, netherBrick);
        this.fillWithBlocks(world, boundingBox, 10, 1, 3, 10, 4, 3, netherBrick);
        this.fillWithBlocks(world, boundingBox, 10, 1, 6, 10, 4, 6, netherBrick);
        this.fillWithBlocks(world, boundingBox, 10, 1, 9, 10, 4, 9, netherBrick);
        this.fillWithBlocks(world, boundingBox, 10, 1, 12, 10, 4, 12, netherBrick);
        this.fillWithBlocks(world, boundingBox, 10, 1, 15, 10, 4, 15, netherBrick);
        this.fillWithBlocks(world, boundingBox, 10, 1, 18, 10, 4, 18, netherBrick);
        this.fillWithBlocks(world, boundingBox, 3, 1, 0, 3, 4, 0, netherBrick);
        this.fillWithBlocks(world, boundingBox, 7, 1, 0, 7, 4, 0, netherBrick);
        this.fillWithBlocks(world, boundingBox, 3, 4, 0, 7, 4, 0, netherBrick);
        this.fillWithBlocks(world, boundingBox, 3, 1, 18, 3, 4, 18, netherBrick);
        this.fillWithBlocks(world, boundingBox, 7, 1, 18, 7, 4, 18, netherBrick);
        this.fillWithBlocks(world, boundingBox, 3, 4, 18, 7, 4, 18, netherBrick);

        // spawners
        ObjectsGenerationHelper.generateMinecraftSpawner(this, world, 3, 0, 3, MobSpawn.getMobForStatueSpawner(random));
        ObjectsGenerationHelper.generateMinecraftSpawner(this, world, 3, 0, 9, MobSpawn.getMobForStatueSpawner(random));
        ObjectsGenerationHelper.generateMinecraftSpawner(this, world, 3, 0, 15, MobSpawn.getMobForStatueSpawner(random));
        ObjectsGenerationHelper.generateMinecraftSpawner(this, world, 7, 0, 3, MobSpawn.getMobForStatueSpawner(random));
        ObjectsGenerationHelper.generateMinecraftSpawner(this, world, 7, 0, 9, MobSpawn.getMobForStatueSpawner(random));
        ObjectsGenerationHelper.generateMinecraftSpawner(this, world, 7, 0, 15, MobSpawn.getMobForStatueSpawner(random));

        // loot chests
        ObjectsGenerationHelper.generateChest(this, world, random, 3, 0, 6, this.coordBaseMode, false, ObjectsGenerationHelper.EnumChestTypes.ALL_CHESTS);
        ObjectsGenerationHelper.generateChest(this, world, random, 3, 0, 12, this.coordBaseMode, false, ObjectsGenerationHelper.EnumChestTypes.ALL_CHESTS);
        ObjectsGenerationHelper.generateChest(this, world, random, 7, 0, 6, this.coordBaseMode, false, ObjectsGenerationHelper.EnumChestTypes.ALL_CHESTS);
        ObjectsGenerationHelper.generateChest(this, world, random, 7, 0, 12, this.coordBaseMode, false, ObjectsGenerationHelper.EnumChestTypes.ALL_CHESTS);

        // statues
        int memorialType = BlockMemorial.getMemorialType(world, new BlockPos(this.getXWithOffset(0, 0), this.getYWithOffset(0), this.getZWithOffset(0, 0)), random, 5);
        EnumFacing left = this.getLeftDirection(this.coordBaseMode);
        EnumFacing right = this.getRightDirection(this.coordBaseMode);
        MemorialGenerationHelper.placeMemorial(this, world, random, 3, 1, 3, left, memorialType);
        MemorialGenerationHelper.placeMemorial(this, world, random, 3, 1, 6, left, memorialType);
        MemorialGenerationHelper.placeMemorial(this, world, random, 3, 1, 9, left, memorialType);
        MemorialGenerationHelper.placeMemorial(this, world, random, 3, 1, 12, left, memorialType);
        MemorialGenerationHelper.placeMemorial(this, world, random, 3, 1, 15, left, memorialType);
        MemorialGenerationHelper.placeMemorial(this, world, random, 7, 1, 3, right, memorialType);
        MemorialGenerationHelper.placeMemorial(this, world, random, 7, 1, 6, right, memorialType);
        MemorialGenerationHelper.placeMemorial(this, world, random, 7, 1, 9, right, memorialType);
        MemorialGenerationHelper.placeMemorial(this, world, random, 7, 1, 12, right, memorialType);
        MemorialGenerationHelper.placeMemorial(this, world, random, 7, 1, 15, right, memorialType);

        // fill exit with random blocks
        this.fillWithRandomizedBlocks(world, boundingBox, 4, 1, 18, 6, 3, 18, false, random, stoneBricks);

        // spawn bats
        MobSpawnHelper.spawnBats(world, random, boundingBox);

        return true;
    }
}
