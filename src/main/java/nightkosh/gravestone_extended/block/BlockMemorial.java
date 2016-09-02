package nightkosh.gravestone_extended.block;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.BlockVine;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemShears;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import nightkosh.gravestone.inventory.GraveInventory;
import nightkosh.gravestone_extended.ModGravestoneExtended;
import nightkosh.gravestone_extended.block.enums.EnumMemorials;
import nightkosh.gravestone_extended.core.GSBlock;
import nightkosh.gravestone_extended.core.Tabs;
import nightkosh.gravestone_extended.tileentity.TileEntityMemorial;

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
public class BlockMemorial extends BlockContainer {

    public static final PropertyDirection FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);

    public static final int[] TAB_MEMORIALS = {
            EnumMemorials.WOODEN_CROSS.ordinal(),
            EnumMemorials.SANDSTONE_CROSS.ordinal(),
            EnumMemorials.RED_SANDSTONE_CROSS.ordinal(),
            EnumMemorials.STONE_CROSS.ordinal(),
            EnumMemorials.DIORITE_CROSS.ordinal(),
            EnumMemorials.ANDESITE_CROSS.ordinal(),
            EnumMemorials.GRANITE_CROSS.ordinal(),
            EnumMemorials.IRON_CROSS.ordinal(),
            EnumMemorials.GOLDEN_CROSS.ordinal(),
            EnumMemorials.DIAMOND_CROSS.ordinal(),
            EnumMemorials.EMERALD_CROSS.ordinal(),
            EnumMemorials.LAPIS_CROSS.ordinal(),
            EnumMemorials.REDSTONE_CROSS.ordinal(),
            EnumMemorials.OBSIDIAN_CROSS.ordinal(),
            EnumMemorials.QUARTZ_CROSS.ordinal(),
            EnumMemorials.PRIZMARINE_CROSS.ordinal(),
            EnumMemorials.ICE_CROSS.ordinal(),
            // obelisks
            EnumMemorials.WOODEN_OBELISK.ordinal(),
            EnumMemorials.SANDSTONE_OBELISK.ordinal(),
            EnumMemorials.RED_SANDSTONE_OBELISK.ordinal(),
            EnumMemorials.STONE_OBELISK.ordinal(),
            EnumMemorials.DIORITE_OBELISK.ordinal(),
            EnumMemorials.ANDESITE_OBELISK.ordinal(),
            EnumMemorials.GRANITE_OBELISK.ordinal(),
            EnumMemorials.IRON_OBELISK.ordinal(),
            EnumMemorials.GOLDEN_OBELISK.ordinal(),
            EnumMemorials.DIAMOND_OBELISK.ordinal(),
            EnumMemorials.EMERALD_OBELISK.ordinal(),
            EnumMemorials.LAPIS_OBELISK.ordinal(),
            EnumMemorials.REDSTONE_OBELISK.ordinal(),
            EnumMemorials.OBSIDIAN_OBELISK.ordinal(),
            EnumMemorials.QUARTZ_OBELISK.ordinal(),
            EnumMemorials.PRIZMARINE_OBELISK.ordinal(),
            EnumMemorials.ICE_OBELISK.ordinal(),
            // celtic cross
            EnumMemorials.WOODEN_CELTIC_CROSS.ordinal(),
            EnumMemorials.SANDSTONE_CELTIC_CROSS.ordinal(),
            EnumMemorials.RED_SANDSTONE_CELTIC_CROSS.ordinal(),
            EnumMemorials.STONE_CELTIC_CROSS.ordinal(),
            EnumMemorials.DIORITE_CELTIC_CROSS.ordinal(),
            EnumMemorials.ANDESITE_CELTIC_CROSS.ordinal(),
            EnumMemorials.GRANITE_CELTIC_CROSS.ordinal(),
            EnumMemorials.IRON_CELTIC_CROSS.ordinal(),
            EnumMemorials.GOLDEN_CELTIC_CROSS.ordinal(),
            EnumMemorials.DIAMOND_CELTIC_CROSS.ordinal(),
            EnumMemorials.EMERALD_CELTIC_CROSS.ordinal(),
            EnumMemorials.LAPIS_CELTIC_CROSS.ordinal(),
            EnumMemorials.REDSTONE_CELTIC_CROSS.ordinal(),
            EnumMemorials.OBSIDIAN_CELTIC_CROSS.ordinal(),
            EnumMemorials.QUARTZ_CELTIC_CROSS.ordinal(),
            EnumMemorials.PRIZMARINE_CELTIC_CROSS.ordinal(),
            EnumMemorials.ICE_CELTIC_CROSS.ordinal(),
            // ANGEL memorials
            EnumMemorials.WOODEN_STEVE_STATUE.ordinal(),
            EnumMemorials.SANDSTONE_STEVE_STATUE.ordinal(),
            EnumMemorials.RED_SANDSTONE_STEVE_STATUE.ordinal(),
            EnumMemorials.STONE_STEVE_STATUE.ordinal(),
            EnumMemorials.DIORITE_STEVE_STATUE.ordinal(),
            EnumMemorials.ANDESITE_STEVE_STATUE.ordinal(),
            EnumMemorials.GRANITE_STEVE_STATUE.ordinal(),
            EnumMemorials.IRON_STEVE_STATUE.ordinal(),
            EnumMemorials.GOLDEN_STEVE_STATUE.ordinal(),
            EnumMemorials.DIAMOND_STEVE_STATUE.ordinal(),
            EnumMemorials.EMERALD_STEVE_STATUE.ordinal(),
            EnumMemorials.LAPIS_STEVE_STATUE.ordinal(),
            EnumMemorials.REDSTONE_STEVE_STATUE.ordinal(),
            EnumMemorials.OBSIDIAN_STEVE_STATUE.ordinal(),
            EnumMemorials.QUARTZ_STEVE_STATUE.ordinal(),
            EnumMemorials.PRIZMARINE_STEVE_STATUE.ordinal(),
            EnumMemorials.ICE_STEVE_STATUE.ordinal(),
            // villager memorials
            EnumMemorials.WOODEN_VILLAGER_STATUE.ordinal(),
            EnumMemorials.SANDSTONE_VILLAGER_STATUE.ordinal(),
            EnumMemorials.RED_SANDSTONE_VILLAGER_STATUE.ordinal(),
            EnumMemorials.STONE_VILLAGER_STATUE.ordinal(),
            EnumMemorials.DIORITE_VILLAGER_STATUE.ordinal(),
            EnumMemorials.ANDESITE_VILLAGER_STATUE.ordinal(),
            EnumMemorials.GRANITE_VILLAGER_STATUE.ordinal(),
            EnumMemorials.IRON_VILLAGER_STATUE.ordinal(),
            EnumMemorials.GOLDEN_VILLAGER_STATUE.ordinal(),
            EnumMemorials.DIAMOND_VILLAGER_STATUE.ordinal(),
            EnumMemorials.EMERALD_VILLAGER_STATUE.ordinal(),
            EnumMemorials.LAPIS_VILLAGER_STATUE.ordinal(),
            EnumMemorials.REDSTONE_VILLAGER_STATUE.ordinal(),
            EnumMemorials.OBSIDIAN_VILLAGER_STATUE.ordinal(),
            EnumMemorials.QUARTZ_VILLAGER_STATUE.ordinal(),
            EnumMemorials.PRIZMARINE_VILLAGER_STATUE.ordinal(),
            EnumMemorials.ICE_VILLAGER_STATUE.ordinal(),
            // angel memorials
            EnumMemorials.WOODEN_ANGEL_STATUE.ordinal(),
            EnumMemorials.SANDSTONE_ANGEL_STATUE.ordinal(),
            EnumMemorials.RED_SANDSTONE_ANGEL_STATUE.ordinal(),
            EnumMemorials.STONE_ANGEL_STATUE.ordinal(),
            EnumMemorials.DIORITE_ANGEL_STATUE.ordinal(),
            EnumMemorials.ANDESITE_ANGEL_STATUE.ordinal(),
            EnumMemorials.GRANITE_ANGEL_STATUE.ordinal(),
            EnumMemorials.IRON_ANGEL_STATUE.ordinal(),
            EnumMemorials.GOLDEN_ANGEL_STATUE.ordinal(),
            EnumMemorials.DIAMOND_ANGEL_STATUE.ordinal(),
            EnumMemorials.EMERALD_ANGEL_STATUE.ordinal(),
            EnumMemorials.LAPIS_ANGEL_STATUE.ordinal(),
            EnumMemorials.REDSTONE_ANGEL_STATUE.ordinal(),
            EnumMemorials.OBSIDIAN_ANGEL_STATUE.ordinal(),
            EnumMemorials.QUARTZ_ANGEL_STATUE.ordinal(),
            EnumMemorials.PRIZMARINE_ANGEL_STATUE.ordinal(),
            EnumMemorials.ICE_ANGEL_STATUE.ordinal(),
            // dog memorials
            EnumMemorials.WOODEN_DOG_STATUE.ordinal(),
            EnumMemorials.SANDSTONE_DOG_STATUE.ordinal(),
            EnumMemorials.RED_SANDSTONE_DOG_STATUE.ordinal(),
            EnumMemorials.STONE_DOG_STATUE.ordinal(),
            EnumMemorials.DIORITE_DOG_STATUE.ordinal(),
            EnumMemorials.ANDESITE_DOG_STATUE.ordinal(),
            EnumMemorials.GRANITE_DOG_STATUE.ordinal(),
            EnumMemorials.IRON_DOG_STATUE.ordinal(),
            EnumMemorials.GOLDEN_DOG_STATUE.ordinal(),
            EnumMemorials.DIAMOND_DOG_STATUE.ordinal(),
            EnumMemorials.EMERALD_DOG_STATUE.ordinal(),
            EnumMemorials.LAPIS_DOG_STATUE.ordinal(),
            EnumMemorials.REDSTONE_DOG_STATUE.ordinal(),
            EnumMemorials.OBSIDIAN_DOG_STATUE.ordinal(),
            EnumMemorials.QUARTZ_DOG_STATUE.ordinal(),
            EnumMemorials.PRIZMARINE_DOG_STATUE.ordinal(),
            EnumMemorials.ICE_DOG_STATUE.ordinal(),
            // cat memorials
            EnumMemorials.WOODEN_CAT_STATUE.ordinal(),
            EnumMemorials.SANDSTONE_CAT_STATUE.ordinal(),
            EnumMemorials.RED_SANDSTONE_CAT_STATUE.ordinal(),
            EnumMemorials.STONE_CAT_STATUE.ordinal(),
            EnumMemorials.DIORITE_CAT_STATUE.ordinal(),
            EnumMemorials.ANDESITE_CAT_STATUE.ordinal(),
            EnumMemorials.GRANITE_CAT_STATUE.ordinal(),
            EnumMemorials.IRON_CAT_STATUE.ordinal(),
            EnumMemorials.GOLDEN_CAT_STATUE.ordinal(),
            EnumMemorials.DIAMOND_CAT_STATUE.ordinal(),
            EnumMemorials.EMERALD_CAT_STATUE.ordinal(),
            EnumMemorials.LAPIS_CAT_STATUE.ordinal(),
            EnumMemorials.REDSTONE_CAT_STATUE.ordinal(),
            EnumMemorials.OBSIDIAN_CAT_STATUE.ordinal(),
            EnumMemorials.QUARTZ_CAT_STATUE.ordinal(),
            EnumMemorials.PRIZMARINE_CAT_STATUE.ordinal(),
            EnumMemorials.ICE_CAT_STATUE.ordinal(),
            // creeper memorials
            EnumMemorials.WOODEN_CREEPER_STATUE.ordinal(),
            EnumMemorials.SANDSTONE_CREEPER_STATUE.ordinal(),
            EnumMemorials.RED_SANDSTONE_CREEPER_STATUE.ordinal(),
            EnumMemorials.STONE_CREEPER_STATUE.ordinal(),
            EnumMemorials.DIORITE_CREEPER_STATUE.ordinal(),
            EnumMemorials.ANDESITE_CREEPER_STATUE.ordinal(),
            EnumMemorials.GRANITE_CREEPER_STATUE.ordinal(),
            EnumMemorials.IRON_CREEPER_STATUE.ordinal(),
            EnumMemorials.GOLDEN_CREEPER_STATUE.ordinal(),
            EnumMemorials.DIAMOND_CREEPER_STATUE.ordinal(),
            EnumMemorials.EMERALD_CREEPER_STATUE.ordinal(),
            EnumMemorials.LAPIS_CREEPER_STATUE.ordinal(),
            EnumMemorials.REDSTONE_CREEPER_STATUE.ordinal(),
            EnumMemorials.OBSIDIAN_CREEPER_STATUE.ordinal(),
            EnumMemorials.QUARTZ_CREEPER_STATUE.ordinal(),
            EnumMemorials.PRIZMARINE_CREEPER_STATUE.ordinal(),
            EnumMemorials.ICE_CREEPER_STATUE.ordinal()
    };
    public static final EnumMemorials[] WOODEN_GENERATED_MEMORIALS = {
            EnumMemorials.WOODEN_CROSS,
            EnumMemorials.QUARTZ_OBELISK,
            EnumMemorials.QUARTZ_CELTIC_CROSS,
            EnumMemorials.WOODEN_STEVE_STATUE,
            EnumMemorials.WOODEN_VILLAGER_STATUE,
            EnumMemorials.WOODEN_ANGEL_STATUE,
            EnumMemorials.WOODEN_DOG_STATUE,
            EnumMemorials.WOODEN_CAT_STATUE
    };
    public static final EnumMemorials[] SANDSTONE_GENERATED_MEMORIALS = {
            EnumMemorials.SANDSTONE_CROSS,
            EnumMemorials.QUARTZ_OBELISK,
            EnumMemorials.QUARTZ_CELTIC_CROSS,
            EnumMemorials.SANDSTONE_STEVE_STATUE,
            EnumMemorials.SANDSTONE_VILLAGER_STATUE,
            EnumMemorials.SANDSTONE_ANGEL_STATUE,
            EnumMemorials.SANDSTONE_DOG_STATUE,
            EnumMemorials.SANDSTONE_CAT_STATUE
    };
    public static final EnumMemorials[] STONE_GENERATED_MEMORIALS = {
            EnumMemorials.STONE_CROSS,
            EnumMemorials.QUARTZ_OBELISK,
            EnumMemorials.QUARTZ_CELTIC_CROSS,
            EnumMemorials.STONE_STEVE_STATUE,
            EnumMemorials.STONE_VILLAGER_STATUE,
            EnumMemorials.STONE_ANGEL_STATUE,
            EnumMemorials.STONE_DOG_STATUE,
            EnumMemorials.STONE_CAT_STATUE
    };
    public static final EnumMemorials[] QUARTZ_GENERATED_MEMORIALS = {
            EnumMemorials.QUARTZ_CROSS,
            EnumMemorials.QUARTZ_OBELISK,
            EnumMemorials.QUARTZ_CELTIC_CROSS,
            EnumMemorials.QUARTZ_STEVE_STATUE,
            EnumMemorials.QUARTZ_VILLAGER_STATUE,
            EnumMemorials.QUARTZ_ANGEL_STATUE,
            EnumMemorials.QUARTZ_DOG_STATUE,
            EnumMemorials.QUARTZ_CAT_STATUE
    };
    public static final EnumMemorials[] ICE_GENERATED_MEMORIALS = {
            EnumMemorials.ICE_CROSS,
            EnumMemorials.ICE_OBELISK,
            EnumMemorials.ICE_CELTIC_CROSS,
            EnumMemorials.ICE_STEVE_STATUE,
            EnumMemorials.ICE_VILLAGER_STATUE,
            EnumMemorials.ICE_ANGEL_STATUE,
            EnumMemorials.ICE_DOG_STATUE,
            EnumMemorials.ICE_CAT_STATUE
    };
    public static final EnumMemorials[] WOODEN_DOG_MEMORIALS = {EnumMemorials.WOODEN_DOG_STATUE};
    public static final EnumMemorials[] SANDSTONE_DOG_MEMORIALS = {EnumMemorials.SANDSTONE_DOG_STATUE};
    public static final EnumMemorials[] STONE_DOG_MEMORIALS = {EnumMemorials.STONE_DOG_STATUE};
    public static final EnumMemorials[] QUARTZ_DOG_MEMORIALS = {EnumMemorials.QUARTZ_DOG_STATUE};
    public static final EnumMemorials[] ICE_DOG_MEMORIALS = {EnumMemorials.ICE_DOG_STATUE};

    public static final EnumMemorials[] WOODEN_CAT_MEMORIALS = {EnumMemorials.WOODEN_CAT_STATUE};
    public static final EnumMemorials[] SANDSTONE_CAT_MEMORIALS = {EnumMemorials.SANDSTONE_CAT_STATUE};
    public static final EnumMemorials[] STONE_CAT_MEMORIALS = {EnumMemorials.STONE_CAT_STATUE};
    public static final EnumMemorials[] QUARTZ_CAT_MEMORIALS = {EnumMemorials.QUARTZ_CAT_STATUE};
    public static final EnumMemorials[] ICE_CAT_MEMORIALS = {EnumMemorials.ICE_CAT_STATUE};

    public static final EnumMemorials[] WOODEN_CREEPER_MEMORIALS = {EnumMemorials.WOODEN_CREEPER_STATUE};
    public static final EnumMemorials[] SANDSTONE_CREEPER_MEMORIALS = {EnumMemorials.SANDSTONE_CREEPER_STATUE};
    public static final EnumMemorials[] STONE_CREEPER_MEMORIALS = {EnumMemorials.STONE_CREEPER_STATUE};
    public static final EnumMemorials[] QUARTZ_CREEPER_MEMORIALS = {EnumMemorials.QUARTZ_CREEPER_STATUE};
    public static final EnumMemorials[] ICE_CREEPER_MEMORIALS = {EnumMemorials.ICE_CREEPER_STATUE};

    public static final EnumMemorials[] WOODEN_STATUES_MEMORIALS = {
            EnumMemorials.WOODEN_STEVE_STATUE,
            EnumMemorials.WOODEN_VILLAGER_STATUE,
            EnumMemorials.WOODEN_ANGEL_STATUE
    };
    public static final EnumMemorials[] SANDSTONE_STATUES_MEMORIALS = {
            EnumMemorials.SANDSTONE_STEVE_STATUE,
            EnumMemorials.SANDSTONE_VILLAGER_STATUE,
            EnumMemorials.SANDSTONE_ANGEL_STATUE
    };
    public static final EnumMemorials[] STONE_STATUES_MEMORIALS = {
            EnumMemorials.STONE_STEVE_STATUE,
            EnumMemorials.STONE_VILLAGER_STATUE,
            EnumMemorials.STONE_ANGEL_STATUE
    };
    public static final EnumMemorials[] QUARTZ_STATUES_MEMORIALS = {
            EnumMemorials.QUARTZ_STEVE_STATUE,
            EnumMemorials.QUARTZ_VILLAGER_STATUE,
            EnumMemorials.QUARTZ_ANGEL_STATUE
    };
    public static final EnumMemorials[] ICE_STATUES_MEMORIALS = {
            EnumMemorials.ICE_STEVE_STATUE,
            EnumMemorials.ICE_VILLAGER_STATUE,
            EnumMemorials.ICE_ANGEL_STATUE
    };

    public BlockMemorial() {
        super(Material.rock);
        this.isBlockContainer = true;
        this.setStepSound(net.minecraft.block.Block.soundTypeStone);
        this.setHardness(1);
        this.setResistance(5);
        this.setCreativeTab(Tabs.memorialsTab);
    }

    /**
     * Return random memorial type memorialTypetype - type of memorial 0 - all
     * memorials(20% for pets graves), except creeper 1 - only pets memorials 2
     * - only dogs memorials 3 - only cats memorials 4 - creeper memorials 5 -
     * only statues memorials(steve, villager, angel)
     */
    public static int getMemorialType(World world, BlockPos pos, Random random, int memorialType) {
        switch (memorialType) {
            default:
            case 0:
                return getRandomMemorial(getGeneratedMemorialsTypes(world, pos), random);
            case 1:
                return getRandomMemorial(getPetsMemorialsTypes(world, pos), random);
            case 2:
                return getRandomMemorial(getDogsMemorialsTypes(world, pos), random);
            case 3:
                return getRandomMemorial(getCatsMemorialsTypes(world, pos), random);
            case 4:
                return getRandomMemorial(getCreeperMemorialsTypes(world, pos), random);
            case 5:
                return getRandomMemorial(getStatuesMemorialsTypes(world, pos), random);
        }
    }

    public static int getRandomMemorial(List<EnumMemorials> memorialTypes, Random rand) {
        if (memorialTypes.size() > 0) {
            return memorialTypes.get(rand.nextInt(memorialTypes.size())).ordinal();
        } else {
            return 0;
        }
    }

    public static ArrayList<EnumMemorials> getGeneratedMemorialsTypes(World world, BlockPos pos) {
        BiomeGenBase biome = world.getBiomeGenForCoords(pos);

        ArrayList<BiomeDictionary.Type> biomeTypesList = new ArrayList<>(Arrays.asList(BiomeDictionary.getTypesForBiome(biome)));
        ArrayList<EnumMemorials> memorialTypes = new ArrayList<>();

        if (biomeTypesList.contains(BiomeDictionary.Type.SANDY) || biomeTypesList.contains(BiomeDictionary.Type.BEACH)) {
            memorialTypes.addAll(Arrays.asList(SANDSTONE_GENERATED_MEMORIALS));
        }
        if (biomeTypesList.contains(BiomeDictionary.Type.JUNGLE) || biomeTypesList.contains(BiomeDictionary.Type.SWAMP)) {
            //TODO memorialTypes.addAll(Arrays.asList(MOSSY_GENERATED_MEMORIALS));
        }
        if (biomeTypesList.contains(BiomeDictionary.Type.MOUNTAIN) || biomeTypesList.contains(BiomeDictionary.Type.HILLS) ||
                biomeTypesList.contains(BiomeDictionary.Type.PLAINS) || biomeTypesList.contains(BiomeDictionary.Type.MUSHROOM)) {
            memorialTypes.addAll(Arrays.asList(STONE_GENERATED_MEMORIALS));
        }
        if (biomeTypesList.contains(BiomeDictionary.Type.FOREST)) {
            memorialTypes.addAll(Arrays.asList(WOODEN_GENERATED_MEMORIALS));
        }
        if (biomeTypesList.contains(BiomeDictionary.Type.SNOWY)) {
            memorialTypes.addAll(Arrays.asList(ICE_GENERATED_MEMORIALS));
        }
        if (biomeTypesList.contains(BiomeDictionary.Type.NETHER)) {
            memorialTypes.addAll(Arrays.asList(QUARTZ_GENERATED_MEMORIALS));
        }

        // TODO
//        if (biomeTypesList.contains(BiomeDictionary.Type.END)) {
//        }
//        if (biomeTypesList.contains(BiomeDictionary.Type.MAGICAL)) {
//        }
//        if (biomeTypesList.contains(BiomeDictionary.Type.WATER)) {// TODO !!!!!!!!!!!
//        }
//        if (biomeTypesList.contains(BiomeDictionary.Type.WASTELAND)) {
//        }

        if (memorialTypes.isEmpty()) {
            memorialTypes.addAll(Arrays.asList(STONE_GENERATED_MEMORIALS));
        }

        return memorialTypes;
    }

    public static ArrayList<EnumMemorials> getPetsMemorialsTypes(World world, BlockPos pos) {
        ArrayList<EnumMemorials> memorialTypes = new ArrayList<>();
        memorialTypes.addAll(getDogsMemorialsTypes(world, pos));
        memorialTypes.addAll(getCatsMemorialsTypes(world, pos));

        return memorialTypes;
    }

    public static ArrayList<EnumMemorials> getDogsMemorialsTypes(World world, BlockPos pos) {
        BiomeGenBase biome = world.getBiomeGenForCoords(pos);

        ArrayList<BiomeDictionary.Type> biomeTypesList = new ArrayList<>(Arrays.asList(BiomeDictionary.getTypesForBiome(biome)));
        ArrayList<EnumMemorials> memorialTypes = new ArrayList<>();

        if (biomeTypesList.contains(BiomeDictionary.Type.SANDY) || biomeTypesList.contains(BiomeDictionary.Type.BEACH)) {
            memorialTypes.addAll(Arrays.asList(SANDSTONE_DOG_MEMORIALS));
        }
        if (biomeTypesList.contains(BiomeDictionary.Type.JUNGLE) || biomeTypesList.contains(BiomeDictionary.Type.SWAMP)) {
            //TODO memorialTypes.addAll(Arrays.asList(MOSSY_DOG_MEMORIALS));
        }
        if (biomeTypesList.contains(BiomeDictionary.Type.MOUNTAIN) || biomeTypesList.contains(BiomeDictionary.Type.HILLS) ||
                biomeTypesList.contains(BiomeDictionary.Type.PLAINS) || biomeTypesList.contains(BiomeDictionary.Type.MUSHROOM)) {
            memorialTypes.addAll(Arrays.asList(STONE_DOG_MEMORIALS));
        }
        if (biomeTypesList.contains(BiomeDictionary.Type.FOREST)) {
            memorialTypes.addAll(Arrays.asList(WOODEN_DOG_MEMORIALS));
        }
        if (biomeTypesList.contains(BiomeDictionary.Type.SNOWY)) {
            memorialTypes.addAll(Arrays.asList(ICE_DOG_MEMORIALS));
        }
        if (biomeTypesList.contains(BiomeDictionary.Type.NETHER)) {
            memorialTypes.addAll(Arrays.asList(QUARTZ_DOG_MEMORIALS));
        }

        // TODO
//        if (biomeTypesList.contains(BiomeDictionary.Type.END)) {
//        }
//        if (biomeTypesList.contains(BiomeDictionary.Type.MAGICAL)) {
//        }
//        if (biomeTypesList.contains(BiomeDictionary.Type.WATER)) {// TODO !!!!!!!!!!!
//        }
//        if (biomeTypesList.contains(BiomeDictionary.Type.WASTELAND)) {
//        }

        if (memorialTypes.isEmpty()) {
            memorialTypes.addAll(Arrays.asList(STONE_DOG_MEMORIALS));
        }

        return memorialTypes;
    }

    public static ArrayList<EnumMemorials> getCatsMemorialsTypes(World world, BlockPos pos) {
        BiomeGenBase biome = world.getBiomeGenForCoords(pos);

        ArrayList<BiomeDictionary.Type> biomeTypesList = new ArrayList<>(Arrays.asList(BiomeDictionary.getTypesForBiome(biome)));
        ArrayList<EnumMemorials> memorialTypes = new ArrayList<>();

        if (biomeTypesList.contains(BiomeDictionary.Type.SANDY) || biomeTypesList.contains(BiomeDictionary.Type.BEACH)) {
            memorialTypes.addAll(Arrays.asList(SANDSTONE_CAT_MEMORIALS));
        }
        if (biomeTypesList.contains(BiomeDictionary.Type.JUNGLE) || biomeTypesList.contains(BiomeDictionary.Type.SWAMP)) {
            //TODO memorialTypes.addAll(Arrays.asList(MOSSY_CAT_MEMORIALS));
        }
        if (biomeTypesList.contains(BiomeDictionary.Type.MOUNTAIN) || biomeTypesList.contains(BiomeDictionary.Type.HILLS) ||
                biomeTypesList.contains(BiomeDictionary.Type.PLAINS) || biomeTypesList.contains(BiomeDictionary.Type.MUSHROOM)) {
            memorialTypes.addAll(Arrays.asList(STONE_CAT_MEMORIALS));
        }
        if (biomeTypesList.contains(BiomeDictionary.Type.FOREST)) {
            memorialTypes.addAll(Arrays.asList(WOODEN_CAT_MEMORIALS));
        }
        if (biomeTypesList.contains(BiomeDictionary.Type.SNOWY)) {
            memorialTypes.addAll(Arrays.asList(ICE_CAT_MEMORIALS));
        }
        if (biomeTypesList.contains(BiomeDictionary.Type.NETHER)) {
            memorialTypes.addAll(Arrays.asList(QUARTZ_CAT_MEMORIALS));
        }

        // TODO
//        if (biomeTypesList.contains(BiomeDictionary.Type.END)) {
//        }
//        if (biomeTypesList.contains(BiomeDictionary.Type.MAGICAL)) {
//        }
//        if (biomeTypesList.contains(BiomeDictionary.Type.WATER)) {// TODO !!!!!!!!!!!
//        }
//        if (biomeTypesList.contains(BiomeDictionary.Type.WASTELAND)) {
//        }

        if (memorialTypes.isEmpty()) {
            memorialTypes.addAll(Arrays.asList(STONE_CAT_MEMORIALS));
        }

        return memorialTypes;
    }

    public static ArrayList<EnumMemorials> getCreeperMemorialsTypes(World world, BlockPos pos) {
        BiomeGenBase biome = world.getBiomeGenForCoords(pos);

        ArrayList<BiomeDictionary.Type> biomeTypesList = new ArrayList<>(Arrays.asList(BiomeDictionary.getTypesForBiome(biome)));
        ArrayList<EnumMemorials> memorialTypes = new ArrayList<>();

        if (biomeTypesList.contains(BiomeDictionary.Type.SANDY) || biomeTypesList.contains(BiomeDictionary.Type.BEACH)) {
            memorialTypes.addAll(Arrays.asList(SANDSTONE_CREEPER_MEMORIALS));
        }
        if (biomeTypesList.contains(BiomeDictionary.Type.JUNGLE) || biomeTypesList.contains(BiomeDictionary.Type.SWAMP)) {
            //TODO memorialTypes.addAll(Arrays.asList(MOSSY_CREEPER_MEMORIALS));
        }
        if (biomeTypesList.contains(BiomeDictionary.Type.MOUNTAIN) || biomeTypesList.contains(BiomeDictionary.Type.HILLS) ||
                biomeTypesList.contains(BiomeDictionary.Type.PLAINS) || biomeTypesList.contains(BiomeDictionary.Type.MUSHROOM)) {
            memorialTypes.addAll(Arrays.asList(STONE_CREEPER_MEMORIALS));
        }
        if (biomeTypesList.contains(BiomeDictionary.Type.FOREST)) {
            memorialTypes.addAll(Arrays.asList(WOODEN_CREEPER_MEMORIALS));
        }
        if (biomeTypesList.contains(BiomeDictionary.Type.SNOWY)) {
            memorialTypes.addAll(Arrays.asList(ICE_CREEPER_MEMORIALS));
        }
        if (biomeTypesList.contains(BiomeDictionary.Type.NETHER)) {
            memorialTypes.addAll(Arrays.asList(QUARTZ_CREEPER_MEMORIALS));
        }

        // TODO
//        if (biomeTypesList.contains(BiomeDictionary.Type.END)) {
//        }
//        if (biomeTypesList.contains(BiomeDictionary.Type.MAGICAL)) {
//        }
//        if (biomeTypesList.contains(BiomeDictionary.Type.WATER)) {// TODO !!!!!!!!!!!
//        }
//        if (biomeTypesList.contains(BiomeDictionary.Type.WASTELAND)) {
//        }

        if (memorialTypes.isEmpty()) {
            memorialTypes.addAll(Arrays.asList(STONE_CREEPER_MEMORIALS));
        }

        return memorialTypes;
    }

    public static ArrayList<EnumMemorials> getStatuesMemorialsTypes(World world, BlockPos pos) {
        BiomeGenBase biome = world.getBiomeGenForCoords(pos);

        ArrayList<BiomeDictionary.Type> biomeTypesList = new ArrayList<>(Arrays.asList(BiomeDictionary.getTypesForBiome(biome)));
        ArrayList<EnumMemorials> memorialTypes = new ArrayList<>();

        if (biomeTypesList.contains(BiomeDictionary.Type.SANDY) || biomeTypesList.contains(BiomeDictionary.Type.BEACH)) {
            memorialTypes.addAll(Arrays.asList(SANDSTONE_STATUES_MEMORIALS));
        }
        if (biomeTypesList.contains(BiomeDictionary.Type.JUNGLE) || biomeTypesList.contains(BiomeDictionary.Type.SWAMP)) {
            //TODO memorialTypes.addAll(Arrays.asList(MOSSY_STATUES_MEMORIALS));
        }
        if (biomeTypesList.contains(BiomeDictionary.Type.MOUNTAIN) || biomeTypesList.contains(BiomeDictionary.Type.HILLS) ||
                biomeTypesList.contains(BiomeDictionary.Type.PLAINS) || biomeTypesList.contains(BiomeDictionary.Type.MUSHROOM)) {
            memorialTypes.addAll(Arrays.asList(STONE_STATUES_MEMORIALS));
        }
        if (biomeTypesList.contains(BiomeDictionary.Type.FOREST)) {
            memorialTypes.addAll(Arrays.asList(WOODEN_STATUES_MEMORIALS));
        }
        if (biomeTypesList.contains(BiomeDictionary.Type.SNOWY)) {
            memorialTypes.addAll(Arrays.asList(ICE_STATUES_MEMORIALS));
        }
        if (biomeTypesList.contains(BiomeDictionary.Type.NETHER)) {
            memorialTypes.addAll(Arrays.asList(QUARTZ_STATUES_MEMORIALS));
        }

        // TODO
//        if (biomeTypesList.contains(BiomeDictionary.Type.END)) {
//        }
//        if (biomeTypesList.contains(BiomeDictionary.Type.MAGICAL)) {
//        }
//        if (biomeTypesList.contains(BiomeDictionary.Type.WATER)) {// TODO !!!!!!!!!!!
//        }
//        if (biomeTypesList.contains(BiomeDictionary.Type.WASTELAND)) {
//        }

        if (memorialTypes.isEmpty()) {
            memorialTypes.addAll(Arrays.asList(STONE_STATUES_MEMORIALS));
        }

        return memorialTypes;
    }

    @Override
    public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase player, ItemStack itemStack) {
        EnumFacing enumfacing = EnumFacing.getHorizontal(MathHelper.floor_double((double) (player.rotationYaw * 4 / 360F) + 0.5D) & 3).getOpposite();
        state = state.withProperty(FACING, enumfacing);
        world.setBlockState(pos, state, 2);

        TileEntityMemorial tileEntity = (TileEntityMemorial) world.getTileEntity(pos);

        if (tileEntity != null) {
            if (itemStack.hasTagCompound()) {
                NBTTagCompound nbt = itemStack.getTagCompound();
                if (nbt.hasKey("isLocalized") && nbt.getBoolean("isLocalized")) {
                    tileEntity.getDeathTextComponent().setLocalized();

                    if (nbt.hasKey("name") && nbt.hasKey("KillerName")) {
                        tileEntity.getDeathTextComponent().setName(nbt.getString("name"));
                        tileEntity.getDeathTextComponent().setKillerName(nbt.getString("KillerName"));
                    }
                }

                if (nbt.hasKey("DeathText")) {
                    tileEntity.getDeathTextComponent().setDeathText(nbt.getString("DeathText"));
                }

                tileEntity.setGraveType(itemStack.getItemDamage());
                tileEntity.setMossy(nbt.getBoolean("Mossy"));

                placeWalls(world, pos);
            }
        }
    }

    public static void placeWalls(World world, BlockPos pos) {
        TileEntityMemorial tileEntity = (TileEntityMemorial) world.getTileEntity(pos);

        if (tileEntity != null) {
            //TODO almost the same code in ItemBlockGSMemorial
            byte maxY;
            byte maxX = 1;
            byte maxZ = 1;
            byte startX = 0;
            byte startZ = 0;

            switch (tileEntity.getMemorialType().getMemorialType()) {
                case CROSS:
                case OBELISK:
                    maxY = 5;
                    maxX = 2;
                    maxZ = 2;
                    startX = -1;
                    startZ = -1;
                    break;
                case DOG_STATUE:
                case CAT_STATUE:
                    maxY = 2;
                    break;
                case CELTIC_CROSS:
                case STEVE_STATUE:
                case VILLAGER_STATUE:
                case ANGEL_STATUE:
                case CREEPER_STATUE:
                default:
                    maxY = 3;
                    break;
            }
            for (byte shiftY = 0; shiftY < maxY; shiftY++) {
                for (byte shiftZ = startZ; shiftZ < maxZ; shiftZ++) {
                    for (byte shiftX = startX; shiftX < maxX; shiftX++) {
                        BlockPos newPos = new BlockPos(pos.getX() + shiftX, pos.getY() + shiftY, pos.getZ() + shiftZ);
                        if (world.getBlockState(newPos).getBlock() == Blocks.air) {
                            world.setBlockState(newPos, GSBlock.invisibleWall.getDefaultState());
                        }
                    }
                }
            }
        }
    }

    @Override
    public void onBlockDestroyedByExplosion(World world, BlockPos pos, Explosion explosionIn) {
        removeWalls(world, pos);
        super.onBlockDestroyedByExplosion(world, pos, explosionIn);
    }

    @Override
    public boolean removedByPlayer(World world, BlockPos pos, EntityPlayer player, boolean willHarvest) {
        removeWalls(world, pos);
        return super.removedByPlayer(world, pos, player, willHarvest);
    }

    private static void removeWalls(World world, BlockPos pos) {
        //TODO almost the same code in ItemBlockGSMemorial
        byte maxY;
        byte maxX = 1;
        byte maxZ = 1;
        byte startX = 0;
        byte startZ = 0;

        TileEntityMemorial tileEntity = (TileEntityMemorial) world.getTileEntity(pos);

        if (tileEntity != null) {
            switch (tileEntity.getMemorialType().getMemorialType()) {
                case CROSS:
                case OBELISK:
                    maxY = 5;
                    maxX = 2;
                    maxZ = 2;
                    startX = -1;
                    startZ = -1;
                    break;
                case DOG_STATUE:
                case CAT_STATUE:
                    maxY = 2;
                    break;
                case CELTIC_CROSS:
                case STEVE_STATUE:
                case VILLAGER_STATUE:
                case ANGEL_STATUE:
                case CREEPER_STATUE:
                default:
                    maxY = 3;
                    break;
            }
            for (byte shiftY = 0; shiftY < maxY; shiftY++) {
                for (byte shiftZ = startZ; shiftZ < maxZ; shiftZ++) {
                    for (byte shiftX = startX; shiftX < maxX; shiftX++) {
                        BlockPos newPos = new BlockPos(pos.getX() + shiftX, pos.getY() + shiftY, pos.getZ() + shiftZ);
                        if (world.getBlockState(newPos).getBlock() == GSBlock.invisibleWall) {
                            world.setBlockState(new BlockPos(pos.getX() + shiftX, pos.getY() + shiftY, pos.getZ() + shiftZ), Blocks.air.getDefaultState());
                        }
                    }
                }
            }
        }
    }

    @Override
    public void setBlockBoundsBasedOnState(IBlockAccess access, BlockPos pos) {
        EnumFacing facing = (EnumFacing) access.getBlockState(pos).getValue(FACING);
        EnumMemorials.EnumMemorialType memorialType;
        TileEntityMemorial tileEntity = (TileEntityMemorial) access.getTileEntity(pos);

        if (tileEntity != null) {
            memorialType = tileEntity.getMemorialType().getMemorialType();
        } else {
            memorialType = EnumMemorials.EnumMemorialType.CROSS;
        }

        switch (memorialType) {
            case CROSS:
            case OBELISK:
                this.setBlockBounds(-1, 0, -1, 2, 5, 2);
                break;
            case CELTIC_CROSS:
            case STEVE_STATUE:
            case VILLAGER_STATUE:
            case ANGEL_STATUE:
                this.setBlockBounds(0.125F, 0, 0.125F, 0.875F, 3F, 0.875F);
                break;
            case DOG_STATUE:
            case CAT_STATUE:
                this.setBlockBounds(0.125F, 0, 0.125F, 0.875F, 2, 0.875F);
                break;
            case CREEPER_STATUE:
                this.setBlockBounds(0.125F, 0, 0.125F, 0.875F, 2.5F, 0.875F);
                break;
        }
    }

    @Override
    public void setBlockBoundsForItemRender() {
        this.setBlockBounds(0, 0, 0, 1, 1, 2);
    }

    @Override
    public boolean isOpaqueCube() {
        return false;
    }

    @Override
    public boolean isFullCube() {
        return false;
    }

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumFacing side, float hitX, float hitY, float hitZ) {
        TileEntityMemorial te = (TileEntityMemorial) world.getTileEntity(pos);

        if (te != null) {
            ItemStack item = player.inventory.getCurrentItem();
            if (item != null) {
                if (te.isMossy()) {
                    if (item.getItem() instanceof ItemShears) {
                        if (!world.isRemote) {
                            GraveInventory.dropItem(new ItemStack(Blocks.vine, 1), world, pos);
                        }
                        te.setMossy(false);
                        return false;
                    }
                } else {
                    if (net.minecraft.block.Block.getBlockFromItem(item.getItem()) instanceof BlockVine) {
                        te.setMossy(true);
                        player.inventory.getCurrentItem().stackSize--;
                        return true;
                    }
                }
            }

            if (world.isRemote) {
                String name;
                String killerName;
                String deathText = te.getDeathTextComponent().getDeathText();

                if (deathText.length() != 0) {
                    if (te.getDeathTextComponent().isLocalized()) {
                        name = ModGravestoneExtended.proxy.getLocalizedEntityName(te.getDeathTextComponent().getName());
                        if (name.length() != 0) {
                            killerName = ModGravestoneExtended.proxy.getLocalizedEntityName(te.getDeathTextComponent().getKillerName());

                            if (killerName.length() == 0) {
                                player.addChatComponentMessage(new ChatComponentTranslation(deathText, new Object[]{name}));
                            } else {
                                player.addChatComponentMessage(new ChatComponentTranslation(deathText, new Object[]{name, killerName}));
                            }
                            return false;
                        }
                    }
                    player.addChatComponentMessage(new ChatComponentTranslation(deathText));
                }
            }
        }

        return false;
    }

    @Override
    public TileEntity createNewTileEntity(World world, int var2) {
        return new TileEntityMemorial(world);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item item, CreativeTabs tab, List list) {
        for (int index : TAB_MEMORIALS) {
            list.add(getMemorialItemForCreativeInventory(item, index));
        }
    }

    private static ItemStack getMemorialItemForCreativeInventory(Item item, int graveType) {
        return new ItemStack(item, 1, graveType);
    }

    @Override
    public void onBlockHarvested(World world, BlockPos pos, IBlockState state, EntityPlayer player) {
        player.addExhaustion(0.025F);

        ItemStack itemStack;
        if (EnchantmentHelper.getSilkTouchModifier(player)) {
            itemStack = getBlockItemStack(world, pos);
        } else {
            itemStack = getBlockItemStackWithoutInfo(world, pos);
        }

        if (itemStack != null) {
            GraveInventory.dropItem(itemStack, world, pos);
        }
    }

    private ItemStack getBlockItemStack(World world, BlockPos pos) {
        ItemStack itemStack = this.createStackedBlock(this.getDefaultState());
        TileEntityMemorial tileEntity = (TileEntityMemorial) world.getTileEntity(pos);

        if (tileEntity != null) {
            itemStack.setItemDamage(tileEntity.getGraveTypeNum());
            NBTTagCompound nbt = new NBTTagCompound();
            if (tileEntity.getDeathTextComponent().isLocalized()) {
                nbt.setBoolean("isLocalized", true);
                nbt.setString("name", tileEntity.getDeathTextComponent().getName());
                nbt.setString("KillerName", tileEntity.getDeathTextComponent().getKillerName());
            }
            nbt.setString("DeathText", tileEntity.getDeathTextComponent().getDeathText());

            nbt.setBoolean("Enchanted", tileEntity.isEnchanted());

            nbt.setBoolean("Mossy", tileEntity.isMossy());

            itemStack.setTagCompound(nbt);
        }

        return itemStack;
    }

    private ItemStack getBlockItemStackWithoutInfo(World world, BlockPos pos) {
        ItemStack itemStack = this.createStackedBlock(this.getDefaultState());
        TileEntityMemorial tileEntity = (TileEntityMemorial) world.getTileEntity(pos);

        if (tileEntity != null) {
            itemStack.setItemDamage(tileEntity.getGraveTypeNum());
            NBTTagCompound nbt = new NBTTagCompound();
            nbt.setBoolean("Mossy", tileEntity.isMossy());
            itemStack.setTagCompound(nbt);
        }

        return itemStack;
    }

    /**
     * Called when the player destroys a block with an item that can harvest it.
     */
    @Override
    public void harvestBlock(World world, EntityPlayer player, BlockPos pos, IBlockState state, TileEntity te) {
    }

    /*
     * Drop sword as item
     */
    public void dropCreeperMemorial(World world, BlockPos pos) {
        ItemStack itemStack = new ItemStack(this, 1, BlockMemorial.getMemorialType(world, pos, new Random(), 4));
        GraveInventory.dropItem(itemStack, world, pos);
    }

    @Override
    public ItemStack getPickBlock(MovingObjectPosition target, World world, BlockPos pos, EntityPlayer player) {
        ItemStack itemStack = this.createStackedBlock(this.getDefaultState());
        TileEntityMemorial tileEntity = (TileEntityMemorial) world.getTileEntity(pos);

        if (tileEntity != null) {
            if (itemStack != null) {
                itemStack.setItemDamage(tileEntity.getGraveTypeNum());
                NBTTagCompound nbt = new NBTTagCompound();
                nbt.setBoolean("Mossy", tileEntity.isMossy());

                itemStack.setTagCompound(nbt);
            }
        }
        return itemStack;
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        EnumFacing enumfacing = EnumFacing.getFront(meta);

        if (enumfacing.getAxis() == EnumFacing.Axis.Y) {
            enumfacing = EnumFacing.NORTH;
        }

        return this.getDefaultState().withProperty(FACING, enumfacing);
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return ((EnumFacing) state.getValue(FACING)).getIndex();
    }

    @Override
    protected BlockState createBlockState() {
        return new BlockState(this, new IProperty[]{FACING});
    }
}
