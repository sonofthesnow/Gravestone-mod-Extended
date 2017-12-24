package nightkosh.gravestone_extended.core;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fml.common.registry.GameRegistry;
import nightkosh.gravestone_extended.block.enums.EnumExecution;
import nightkosh.gravestone_extended.block.enums.EnumPileOfBones;
import nightkosh.gravestone_extended.block.enums.EnumSkullCandle;
import nightkosh.gravestone_extended.block.enums.EnumSpawner;
import nightkosh.gravestone_extended.config.ExtendedConfig;
import nightkosh.gravestone_extended.fluid.FluidToxicWater;
import nightkosh.gravestone_extended.item.ItemFish;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class GSRecipes {

    private static final ResourceLocation GROUP = new ResourceLocation(ModInfo.ID);

    public static void registration() {
        GameRegistry.addShapedRecipe(new ResourceLocation(ModInfo.ID, "toxic_water_bucket"), GROUP, FluidUtil.getFilledBucket(FluidRegistry.getFluidStack(FluidToxicWater.INSTANCE.getName(), 1)),
                "ws", "ss",
                'w', Items.WATER_BUCKET,
                's', GSItem.TOXIC_SLIME);

        // fishes
        GameRegistry.addShapedRecipe(new ResourceLocation(ModInfo.ID, "fish_to_bones"), GROUP, new ItemStack(GSBlock.PILE_OF_BONES),
                "f",
                'f', new ItemStack(GSItem.FISH, 1, ItemFish.EnumFishType.BONE_FISH.ordinal()));

        GameRegistry.addShapedRecipe(new ResourceLocation(ModInfo.ID, "fish_to_toxic_slime"), GROUP, new ItemStack(GSItem.TOXIC_SLIME),
                "f",
                'f', new ItemStack(GSItem.FISH, 1, ItemFish.EnumFishType.GREEN_JELLYFISH.ordinal()));

        GameRegistry.addShapedRecipe(new ResourceLocation(ModInfo.ID, "fish_to_magma_cream"), GROUP, new ItemStack(Items.MAGMA_CREAM),
                "f",
                'f', new ItemStack(GSItem.FISH, 1, ItemFish.EnumFishType.MAGMA_JELLYFISH.ordinal()));

        GameRegistry.addShapedRecipe(new ResourceLocation(ModInfo.ID, "fish_to_obsidian"), GROUP, new ItemStack(Blocks.OBSIDIAN),
                "ff",
                "ff",
                'f', new ItemStack(GSItem.FISH, 1, ItemFish.EnumFishType.OBSIDIFISH.ordinal()));

        GameRegistry.addShapedRecipe(new ResourceLocation(ModInfo.ID, "fish_to_netherrack"), GROUP, new ItemStack(Blocks.NETHERRACK),
                "f",
                'f', new ItemStack(GSItem.FISH, 1, ItemFish.EnumFishType.NETHER_SALMON.ordinal()));

        GameRegistry.addShapedRecipe(new ResourceLocation(ModInfo.ID, "fish_to_quartz"), GROUP, new ItemStack(Items.QUARTZ, 4),
                "f",
                'f', new ItemStack(GSItem.FISH, 1, ItemFish.EnumFishType.QUARTZ_COD.ordinal()));

        GameRegistry.addShapedRecipe(new ResourceLocation(ModInfo.ID, "fish_to_glowstone_dust"), GROUP, new ItemStack(Items.GLOWSTONE_DUST, 2),
                "f",
                'f', new ItemStack(GSItem.FISH, 1, ItemFish.EnumFishType.FLAREFIN_KOI.ordinal()));

        GameRegistry.addShapedRecipe(new ResourceLocation(ModInfo.ID, "fish_to_blaze_rod"), GROUP, new ItemStack(Items.BLAZE_ROD),
                "f",
                'f', new ItemStack(GSItem.FISH, 1, ItemFish.EnumFishType.BLAZE_COD.ordinal()));

        GameRegistry.addShapedRecipe(new ResourceLocation(ModInfo.ID, "fish_to_ender_pearl"), GROUP, new ItemStack(Items.ENDER_PEARL),
                "f",
                'f', new ItemStack(GSItem.FISH, 1, ItemFish.EnumFishType.ENDER_BASS.ordinal()));



        GameRegistry.addShapedRecipe(new ResourceLocation(ModInfo.ID, "chisel"), GROUP, new ItemStack(GSItem.CHISEL),
                "   ", "s  ", " i ",
                's', Items.STICK,
                'i', Items.IRON_INGOT);

        GameRegistry.addShapedRecipe(new ResourceLocation(ModInfo.ID, "bone_fishing_pole"), GROUP, new ItemStack(GSItem.BONE_FISHING_POLE),
                "p  ", "tp ", "s p",
                'p', new ItemStack(GSBlock.PILE_OF_BONES, 1, EnumPileOfBones.PILE_OF_BONES.ordinal()),
                't', Items.STRING,
                's', new ItemStack(Items.SKULL, 1, 0));

        GameRegistry.addShapedRecipe(new ResourceLocation(ModInfo.ID, "bone_hoe"), GROUP, new ItemStack(GSItem.BONE_HOE),
                "bs ", " w ", " w ",
                'b', GSBlock.BONE_BLOCK,
                's', new ItemStack(Items.SKULL, 1, 0),
                'w', Items.STICK);

        GameRegistry.addShapedRecipe(new ResourceLocation(ModInfo.ID, "bone_hoe_iron"), GROUP, new ItemStack(GSItem.BONE_HOE_IRON),
                "bi",
                'b', GSItem.BONE_HOE,
                'i', Items.IRON_HOE);

        GameRegistry.addShapedRecipe(new ResourceLocation(ModInfo.ID, "bone_hoe_golden"), GROUP, new ItemStack(GSItem.BONE_HOE_GOLDEN),
                "bg",
                'b', GSItem.BONE_HOE,
                'g', Items.GOLDEN_HOE);

        GameRegistry.addShapedRecipe(new ResourceLocation(ModInfo.ID, "bone_hoe_diamond"), GROUP, new ItemStack(GSItem.BONE_HOE_DIAMOND),
                "bd",
                'b', GSItem.BONE_HOE,
                'd', Items.DIAMOND_HOE);



        GameRegistry.addShapedRecipe(new ResourceLocation(ModInfo.ID, "bone_sword"), GROUP, new ItemStack(GSItem.BONE_SWORD),
                " b ", "sbs", " w ",
                'b', GSBlock.BONE_BLOCK,
                's', new ItemStack(Items.SKULL, 1, 0),
                'w', Items.STICK);

        GameRegistry.addShapedRecipe(new ResourceLocation(ModInfo.ID, "bone_sword_iron"), GROUP, new ItemStack(GSItem.BONE_SWORD_IRON),
                "bs",
                'b', GSItem.BONE_SWORD,
                's', Items.IRON_SWORD);

        GameRegistry.addShapedRecipe(new ResourceLocation(ModInfo.ID, "bone_sword_golden"), GROUP, new ItemStack(GSItem.BONE_SWORD_GOLDEN),
                "bs",
                'b', GSItem.BONE_SWORD,
                's', Items.GOLDEN_SWORD);

        GameRegistry.addShapedRecipe(new ResourceLocation(ModInfo.ID, "bone_sword_diamond"), GROUP, new ItemStack(GSItem.BONE_SWORD_DIAMOND),
                "bs",
                'b', GSItem.BONE_SWORD,
                's', Items.DIAMOND_SWORD);

        GameRegistry.addShapedRecipe(new ResourceLocation(ModInfo.ID, "bone_shield"), GROUP, new ItemStack(GSItem.BONE_SHIELD),
                "sss", "pbp", "pcp",
                's', new ItemStack(Items.SKULL, 1, 0),
                'p', GSBlock.PILE_OF_BONES,
                'b', GSBlock.BONE_BLOCK,
                'c', Items.SHIELD);

        GameRegistry.addShapedRecipe(new ResourceLocation(ModInfo.ID, "pile_of_bones"), GROUP, new ItemStack(GSBlock.PILE_OF_BONES, 1, 0),
                "bb", "bb",
                'b', Items.BONE);

        GameRegistry.addShapedRecipe(new ResourceLocation(ModInfo.ID, "pile_of_bones_with_skull"), GROUP, new ItemStack(GSBlock.PILE_OF_BONES, 9, 1),
                "bbb", "bsb", "bbb",
                'b', new ItemStack(GSBlock.PILE_OF_BONES, 1, 0),
                's', new ItemStack(Items.SKULL, 1, 0));

        GameRegistry.addShapedRecipe(new ResourceLocation(ModInfo.ID, "piles_to_bones"), GROUP, new ItemStack(Items.BONE, 4),
                "p",
                'p', new ItemStack(GSBlock.PILE_OF_BONES, 1, 0));

        GameRegistry.addShapedRecipe(new ResourceLocation(ModInfo.ID, "skull_from_piles"), GROUP, new ItemStack(Items.SKULL, 1, 0),
                "ppp", "ppp", "ppp",
                'p', new ItemStack(GSBlock.PILE_OF_BONES, 1, 1));

        GameRegistry.addShapedRecipe(new ResourceLocation(ModInfo.ID, "bone_block"), GROUP, new ItemStack(GSBlock.BONE_BLOCK, 1, 0),
                "ppp", "ppp", "ppp",
                'p', new ItemStack(GSBlock.PILE_OF_BONES, 1, 0));

        GameRegistry.addShapedRecipe(new ResourceLocation(ModInfo.ID, "bone_block_with_skull"), GROUP, new ItemStack(GSBlock.BONE_BLOCK, 9, 1),
                "bbb", "bsb", "bbb",
                'b', new ItemStack(GSBlock.BONE_BLOCK, 1, 0), 's', new ItemStack(Items.SKULL, 1, 0));

        GameRegistry.addShapedRecipe(new ResourceLocation(ModInfo.ID, "bone_block_from_slabs"), GROUP, new ItemStack(GSBlock.BONE_BLOCK),
                "x", "x",
                'x', GSBlock.BONE_SLAB);

        GameRegistry.addShapedRecipe(new ResourceLocation(ModInfo.ID, "bone_slabs"), GROUP, new ItemStack(GSBlock.BONE_SLAB, 6),
                "xxx",
                'x', GSBlock.BONE_BLOCK);

        GameRegistry.addShapedRecipe(new ResourceLocation(ModInfo.ID, "bone_stairs"), GROUP, new ItemStack(GSBlock.BONE_STAIRS, 4),
                "x  ", "xx ", "xxx",
                'x', GSBlock.BONE_BLOCK);

        GameRegistry.addShapedRecipe(new ResourceLocation(ModInfo.ID, "pile_of_bones_from_block"), GROUP, new ItemStack(GSBlock.PILE_OF_BONES, 9, 0),
                "x",
                'x', GSBlock.BONE_BLOCK);

        GameRegistry.addShapedRecipe(new ResourceLocation(ModInfo.ID, "gallows"), GROUP, new ItemStack(GSBlock.EXECUTION, 1, EnumExecution.GALLOWS.ordinal()),
                "ww ", "wr ", "ww ",
                'w', Blocks.PLANKS,
                'r', Items.LEAD);

        GameRegistry.addShapedRecipe(new ResourceLocation(ModInfo.ID, "gibbets"), GROUP, new ItemStack(GSBlock.EXECUTION, 1, EnumExecution.GIBBET.ordinal()),
                "ww ", "wr ", "wi ",
                'w', Blocks.PLANKS,
                'r', Items.LEAD,
                'i', Blocks.IRON_BLOCK);

        GameRegistry.addShapedRecipe(new ResourceLocation(ModInfo.ID, "stocks"), GROUP, new ItemStack(GSBlock.EXECUTION, 1, EnumExecution.STOCKS.ordinal()),
                "wsw", "w w",
                'w', Blocks.PLANKS,
                's', Blocks.WOODEN_SLAB);

        GameRegistry.addShapedRecipe(new ResourceLocation(ModInfo.ID, "burning_stake"), GROUP, new ItemStack(GSBlock.EXECUTION, 1, EnumExecution.BURNING_STAKE.ordinal()),
                " w ", "www", "hwh",
                'w', Blocks.PLANKS,
                'h', Blocks.HAY_BLOCK);

        //TODO ????
        // sword graves TODO remove
//        for (net.minecraft.item.Item sword : GraveStoneHelper.swordsList) {
//            GameRegistry.addShapedRecipe(new ResourceLocation(ModInfo.ID, ""), GROUP, GraveStoneHelper.getSwordAsGrave(net.minecraft.item.Item.getItemFromBlock(Block.graveStone), new ItemStack(sword, 1)),
//                    "sc",
//                    's', sword,
//                    'c', ExtendedItem.chisel);
//        }

        if (ExtendedConfig.craftableWitherSpawner) {
            GameRegistry.addShapedRecipe(new ResourceLocation(ModInfo.ID, "wither_spawner"), GROUP, new ItemStack(GSBlock.SPAWNER, 1, EnumSpawner.WITHER_SPAWNER.ordinal()),
                    "bcb", "cec", "cbc",
                    'c', new ItemStack(GSBlock.SKULL_CANDLE, 1, EnumSkullCandle.WITHER_SKULL.ordinal()),
                    'b', new ItemStack(Items.DYE, 1, EnumDyeColor.WHITE.getDyeDamage()), 'e', Items.ENDER_EYE);
        }
        if (ExtendedConfig.craftableSpawners) {
            GameRegistry.addShapedRecipe(new ResourceLocation(ModInfo.ID, "skeleton_spawner"), GROUP, new ItemStack(GSBlock.SPAWNER, 1, EnumSpawner.SKELETON_SPAWNER.ordinal()),
                    "bcb", "cec", "cbc",
                    'c', new ItemStack(GSBlock.SKULL_CANDLE, 1, EnumSkullCandle.SKELETON_SKULL.ordinal()),
                    'b', new ItemStack(Items.DYE, 1, EnumDyeColor.WHITE.getDyeDamage()), 'e', Items.ENDER_EYE);

            GameRegistry.addShapedRecipe(new ResourceLocation(ModInfo.ID, "zombie_spawner"), GROUP, new ItemStack(GSBlock.SPAWNER, 1, EnumSpawner.ZOMBIE_SPAWNER.ordinal()),
                    "bcb", "cec", "cbc",
                    'c', new ItemStack(GSBlock.SKULL_CANDLE, 1, EnumSkullCandle.ZOMBIE_SKULL.ordinal()),
                    'b', new ItemStack(Items.DYE, 1, EnumDyeColor.WHITE.getDyeDamage()), 'e', Items.ENDER_EYE);

            GameRegistry.addShapedRecipe(new ResourceLocation(ModInfo.ID, "spider_spawner"), GROUP, new ItemStack(GSBlock.SPAWNER, 1, EnumSpawner.SPIDER_SPAWNER.ordinal()),
                    "www", "ses", "www",
                    'w', Blocks.WEB, 's', Items.SPIDER_EYE, 'e', Items.ENDER_EYE);
        }

        GameRegistry.addShapedRecipe(new ResourceLocation(ModInfo.ID, "candle"), GROUP, new ItemStack(GSBlock.CANDLE, 1, 0),
                "t", "m", "s",
                't', Items.STRING,
                'm', new ItemStack(Items.DYE, 1, EnumDyeColor.WHITE.getDyeDamage()),
                's', Items.SLIME_BALL);

        GameRegistry.addShapedRecipe(new ResourceLocation(ModInfo.ID, "skeleton_candle"), GROUP, new ItemStack(GSBlock.SKULL_CANDLE, 1, EnumSkullCandle.SKELETON_SKULL.ordinal()),
                "c", "s",
                's', new ItemStack(Items.SKULL, 1, 0),
                'c', new ItemStack(GSBlock.CANDLE, 1, 0));

        GameRegistry.addShapedRecipe(new ResourceLocation(ModInfo.ID, "wither_candle"), GROUP, new ItemStack(GSBlock.SKULL_CANDLE, 1, EnumSkullCandle.WITHER_SKULL.ordinal()),
                "c", "s",
                's', new ItemStack(Items.SKULL, 1, 1),
                'c', new ItemStack(GSBlock.CANDLE, 1, 0));

        GameRegistry.addShapedRecipe(new ResourceLocation(ModInfo.ID, "zombie_candle"), GROUP, new ItemStack(GSBlock.SKULL_CANDLE, 1, EnumSkullCandle.ZOMBIE_SKULL.ordinal()),
                "c", "s",
                's', new ItemStack(Items.SKULL, 1, 2),
                'c', new ItemStack(GSBlock.CANDLE, 1, 0));

        GameRegistry.addShapedRecipe(new ResourceLocation(ModInfo.ID, "skeleton_skull"), GROUP, new ItemStack(Items.SKULL, 1, 0),
                "c",
                'c', new ItemStack(GSBlock.SKULL_CANDLE, 1, EnumSkullCandle.SKELETON_SKULL.ordinal()));

        GameRegistry.addShapedRecipe(new ResourceLocation(ModInfo.ID, "wither_skull"), GROUP, new ItemStack(Items.SKULL, 1, 1),
                "c",
                'c', new ItemStack(GSBlock.SKULL_CANDLE, 1, EnumSkullCandle.WITHER_SKULL.ordinal()));

        GameRegistry.addShapedRecipe(new ResourceLocation(ModInfo.ID, "zombie_skull"), GROUP, new ItemStack(Items.SKULL, 1, 2),
                "c",
                'c', new ItemStack(GSBlock.SKULL_CANDLE, 1, EnumSkullCandle.ZOMBIE_SKULL.ordinal()));

        if (ExtendedConfig.craftableNightStone) {
            GameRegistry.addShapedRecipe(new ResourceLocation(ModInfo.ID, "nightstone"), GROUP, new ItemStack(GSBlock.TRAP, 1, 0),
                    " p ", "rnr", " s ",
                    'n', Blocks.NETHER_BRICK,
                    'p', Blocks.STONE_PRESSURE_PLATE,
                    'r', Items.REDSTONE,
                    's', Blocks.SOUL_SAND);
        }
        if (ExtendedConfig.craftableThunderStone) {
            GameRegistry.addShapedRecipe(new ResourceLocation(ModInfo.ID, "thunderstone"), GROUP, new ItemStack(GSBlock.TRAP, 1, 1),
                    " p ", "rnr", " s ",
                    'n', Blocks.STONEBRICK,
                    'p', Blocks.STONE_PRESSURE_PLATE,
                    'r', Items.REDSTONE,
                    's', Blocks.SOUL_SAND);
        }

        // altar
        Item altarCrystal = (ExtendedConfig.hardAltarRecipe) ? Items.NETHER_STAR : Items.DIAMOND;
        GameRegistry.addShapedRecipe(new ResourceLocation(ModInfo.ID, "altar"), GROUP, new ItemStack(GSBlock.ALTAR),
                " h ", "sns", "bbb",
                'h', altarCrystal,
                's', new ItemStack(Items.SKULL, 1, 0),
                'n', new ItemStack(GSBlock.TRAP, 1, 0),
                'b', new ItemStack(GSBlock.BONE_BLOCK, 1, 0));
    }

    public static void addForestryBackpack(ItemStack backpack, Item item) {
        GameRegistry.addShapedRecipe(new ResourceLocation(ModInfo.ID, "backpack"), GROUP, backpack,
                "sws", "ici", "sws",
                'w', Blocks.WOOL,
                'i', item,
                's', Items.STRING,
                'c', Blocks.CHEST);
    }
}
