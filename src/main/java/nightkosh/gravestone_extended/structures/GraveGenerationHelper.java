package nightkosh.gravestone_extended.structures;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import nightkosh.gravestone.helper.GraveGenerationHelper.EnumGraveTypeByEntity;
import nightkosh.gravestone.tileentity.TileEntityGraveStone;
import nightkosh.gravestone_extended.entity.helper.EntityGroupOfGravesMobSpawnerHelper;
import nightkosh.gravestone_extended.helper.GraveInventoryHelper;
import nightkosh.gravestone_extended.helper.GraveStoneHelper;
import nightkosh.gravestone_extended.helper.GraveWorldGenerationHelper;

import java.util.Random;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class GraveGenerationHelper {

    private GraveGenerationHelper() {
    }

    public static EntityGroupOfGravesMobSpawnerHelper createSpawnerHelper(World world, StructureBoundingBox boundingBox) {
        EntityGroupOfGravesMobSpawnerHelper spawnerHelper = new EntityGroupOfGravesMobSpawnerHelper(world);

        Vec3i center = boundingBox.getLength();
        spawnerHelper.setLocationAndAngles(center.getX(), center.getY(), center.getZ(), 0, 0);
        world.spawnEntity(spawnerHelper);

        return spawnerHelper;
    }

    public static void placeGrave(IComponentGraveStone component, World world, Random random, int x, int y, int z,
                                  IBlockState graveState, EntityGroupOfGravesMobSpawnerHelper spanwerHelper) {
        placeGrave(component, world, random, x, y, z, graveState, spanwerHelper, EnumGraveTypeByEntity.HUMAN_GRAVES);
    }

    public static void placeGrave(IComponentGraveStone component, World world, Random random, int x, int y, int z,
                                  IBlockState graveState, EntityGroupOfGravesMobSpawnerHelper spanwerHelper, EnumGraveTypeByEntity graveTypeByEntity) {
        placeGrave(component, world, random, x, y, z, graveState, spanwerHelper, graveTypeByEntity, GraveInventoryHelper.GraveContentType.RANDOM);
    }

    public static void placeGrave(IComponentGraveStone component, World world, Random random, int x, int y, int z,
                                  IBlockState graveState, EntityGroupOfGravesMobSpawnerHelper spanwerHelper, EnumGraveTypeByEntity graveTypeByEntity,
                                  GraveInventoryHelper.GraveContentType contentType) {
        GraveWorldGenerationHelper.GraveGenerationInfo graveInfo = GraveWorldGenerationHelper.getGrave(world, random,
                new BlockPos(component.getIXWithOffset(x, z), component.getIYWithOffset(y), component.getIZWithOffset(x, z)),
                graveTypeByEntity, contentType);
        placeGrave(component, world, x, y, z, graveState, graveInfo, spanwerHelper);
    }

    private static void placeGrave(IComponentGraveStone component, World world, int x, int y, int z,
                                  IBlockState graveState, GraveWorldGenerationHelper.GraveGenerationInfo graveInfo, EntityGroupOfGravesMobSpawnerHelper spanwerHelper) {
        component.placeBlockAtCurrentPosition(world, graveState, x, y, z, component.getIBoundingBox());
        TileEntityGraveStone tileEntity = (TileEntityGraveStone) world.getTileEntity(new BlockPos(component.getIXWithOffset(x, z), component.getIYWithOffset(y), component.getIZWithOffset(x, z)));

        if (tileEntity != null) {
            setGraveInfo(graveInfo, tileEntity);
            tileEntity.setSpawnerHelper(spanwerHelper);
        }
    }

    private static void setGraveInfo(GraveWorldGenerationHelper.GraveGenerationInfo graveInfo, TileEntityGraveStone tileEntity) {
        tileEntity.setGraveType(graveInfo.getGrave().ordinal());
        tileEntity.setEnchanted(graveInfo.isEnchanted());
        tileEntity.setMossy(graveInfo.isMossy());
        tileEntity.setSword(graveInfo.getSword());
        tileEntity.setFlower(graveInfo.getFlower());
        tileEntity.setDeathTextComponent(graveInfo.getDeathText());
        tileEntity.getInventory().setAdditionalItems(graveInfo.getItems());
    }

    public static void fillGraves(ComponentGraveStone component, World world, Random random, int xStart, int yStart, int zStart,
                                  int xEnd, int yEnd, int zEnd, IBlockState graveState, EntityGroupOfGravesMobSpawnerHelper spanwerHelper) {
        fillGraves(component, world, random, xStart, yStart, zStart, xEnd, yEnd, zEnd, graveState, spanwerHelper, EnumGraveTypeByEntity.HUMAN_GRAVES);
    }

    public static void fillGraves(ComponentGraveStone component, World world, Random random, int xStart, int yStart, int zStart,
                                  int xEnd, int yEnd, int zEnd, IBlockState graveState, EntityGroupOfGravesMobSpawnerHelper spanwerHelper,
                                  EnumGraveTypeByEntity graveTypeByEntity) {
        fillGraves(component, world, random, xStart, yStart, zStart, xEnd, yEnd, zEnd, graveState, spanwerHelper, graveTypeByEntity,
                GraveInventoryHelper.GraveContentType.RANDOM);
    }

    public static void fillGraves(ComponentGraveStone component, World world, Random random, int xStart, int yStart, int zStart,
                                  int xEnd, int yEnd, int zEnd, IBlockState graveState, EntityGroupOfGravesMobSpawnerHelper spanwerHelper,
                                  EnumGraveTypeByEntity graveTypeByEntity, GraveInventoryHelper.GraveContentType contentType) {
        for (int y = yStart; y <= yEnd; y++) {
            for (int x = xStart; x <= xEnd; x++) {
                for (int z = zStart; z <= zEnd; z++) {
                    GraveWorldGenerationHelper.GraveGenerationInfo graveInfo = GraveWorldGenerationHelper.getGrave(world, random,
                            new BlockPos(component.getXWithOffset(x, z), component.getYWithOffset(y), component.getZWithOffset(x, z)),
                            graveTypeByEntity, contentType);
                    placeGrave(component, world, x, y, z, graveState, graveInfo, spanwerHelper);
                }
            }
        }
    }

    public static boolean canPlaceGrave(World world, int x, int minY, int z, int maxY) {
        Block block;

        for (int y = maxY; y >= minY - 1; y--) {
            BlockPos pos = new BlockPos(x, y, z);
            block = world.getBlockState(pos).getBlock();

            if (block != null) {
                if (block.equals(Blocks.WATER) || block.equals(Blocks.LAVA)) {
                    return false;
                } else if (GraveStoneHelper.canPlaceBlockAt(world, block, pos)) {
                    return true;
                }
            }
        }

        return false;
    }
}
