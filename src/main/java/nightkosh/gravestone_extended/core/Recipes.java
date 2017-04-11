package nightkosh.gravestone_extended.core;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.registry.GameRegistry;
import nightkosh.gravestone_extended.block.enums.EnumExecution;
import nightkosh.gravestone_extended.block.enums.EnumSkullCandle;
import nightkosh.gravestone_extended.block.enums.EnumSpawner;
import nightkosh.gravestone_extended.config.ExtendedConfig;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class Recipes {

    private Recipes() {
    }

    public static void registration() {
        // chisel recipe
        GameRegistry.addRecipe(new ItemStack(GSItem.chisel), "   ", "s  ", " i ", 's', Items.STICK, 'i', Items.IRON_INGOT);
        // pile of bones
        GameRegistry.addRecipe(new ItemStack(GSBlock.pileOfBones, 1, 0), "   ", " b ", "bbb", 'b', Items.BONE);
        GameRegistry.addRecipe(new ItemStack(GSBlock.pileOfBones, 9, 1), "bbb", "bsb", "bbb", 'b', new ItemStack(GSBlock.pileOfBones, 1, 0), 's', new ItemStack(Items.SKULL, 1, 0));
        // piles to bones
        GameRegistry.addRecipe(new ItemStack(Items.BONE, 4), "p", 'p', new ItemStack(GSBlock.pileOfBones, 1, 0));
        GameRegistry.addRecipe(new ItemStack(Items.SKULL, 1, 0), "ppp", "ppp", "ppp", 'p', new ItemStack(GSBlock.pileOfBones, 1, 1));
        // bone blocks
        GameRegistry.addRecipe(new ItemStack(GSBlock.boneBlock, 1, 0), "ppp", "ppp", "ppp", 'p', new ItemStack(GSBlock.pileOfBones, 1, 0));
        GameRegistry.addRecipe(new ItemStack(GSBlock.boneBlock, 9, 1), "bbb", "bsb", "bbb", 'b', new ItemStack(GSBlock.boneBlock, 1, 0), 's', new ItemStack(Items.SKULL, 1, 0));

        GameRegistry.addRecipe(new ItemStack(GSBlock.boneBlock), "x", "x", 'x', GSBlock.boneSlab);
        GameRegistry.addRecipe(new ItemStack(GSBlock.boneSlab, 6), "xxx", 'x', GSBlock.boneBlock);
        GameRegistry.addRecipe(new ItemStack(GSBlock.boneStairs, 4), "x  ", "xx ", "xxx", 'x', GSBlock.boneBlock);
        // Bone block to piles
        GameRegistry.addRecipe(new ItemStack(GSBlock.pileOfBones, 9, 0), "x", 'x', GSBlock.boneBlock);


        // gallows
        GameRegistry.addRecipe(getStackWithNTB(GSBlock.execution, EnumExecution.GIBBET.ordinal(), "Type"), "ww ", "wr ", "ww ", 'w', Blocks.PLANKS, 'r', Items.LEAD);
        // gibbets
        GameRegistry.addRecipe(getStackWithNTB(GSBlock.execution, EnumExecution.GIBBET.ordinal(), "Type"), "ww ", "wr ", "wi ", 'w', Blocks.PLANKS, 'r', Items.LEAD, 'i', Blocks.IRON_BLOCK);
        // stocks
        GameRegistry.addRecipe(getStackWithNTB(GSBlock.execution, EnumExecution.STOCKS.ordinal(), "Type"), "wsw", "w w", 'w', Blocks.PLANKS, 's', Blocks.WOODEN_SLAB);
        // burning stake
        GameRegistry.addRecipe(getStackWithNTB(GSBlock.execution, EnumExecution.BURNING_STAKE.ordinal(), "Type"), " w ", "www", "hwh", 'w', Blocks.PLANKS, 'h', Blocks.HAY_BLOCK);

    //TODO ????
        // sword graves TODO remove
//        for (net.minecraft.item.Item sword : GraveStoneHelper.swordsList) {
//            GameRegistry.addRecipe(GraveStoneHelper.getSwordAsGrave(net.minecraft.item.Item.getItemFromBlock(Block.graveStone), new ItemStack(sword, 1)), "sc", 's', sword, 'c', ExtendedItem.chisel);
//        }

        // spawners
        if (ExtendedConfig.craftableWitherSpawner) {
            GameRegistry.addRecipe(new ItemStack(GSBlock.spawner, 1, EnumSpawner.WITHER_SPAWNER.ordinal()), "bcb", "cec", "cbc",
                    'c', new ItemStack(GSBlock.skullCandle, 1, EnumSkullCandle.WITHER_SKULL.ordinal()),
                    'b', new ItemStack(Items.DYE, 1, EnumDyeColor.WHITE.getDyeDamage()), 'e', Items.ENDER_EYE);
        }
        if (ExtendedConfig.craftableSpawners) {
            GameRegistry.addRecipe(new ItemStack(GSBlock.spawner, 1, EnumSpawner.SKELETON_SPAWNER.ordinal()), "bcb", "cec", "cbc",
                    'c', new ItemStack(GSBlock.skullCandle, 1, EnumSkullCandle.SKELETON_SKULL.ordinal()),
                    'b', new ItemStack(Items.DYE, 1, EnumDyeColor.WHITE.getDyeDamage()), 'e', Items.ENDER_EYE);
            GameRegistry.addRecipe(new ItemStack(GSBlock.spawner, 1, EnumSpawner.ZOMBIE_SPAWNER.ordinal()), "bcb", "cec", "cbc",
                    'c', new ItemStack(GSBlock.skullCandle, 1, EnumSkullCandle.ZOMBIE_SKULL.ordinal()),
                    'b', new ItemStack(Items.DYE, 1, EnumDyeColor.WHITE.getDyeDamage()), 'e', Items.ENDER_EYE);
            GameRegistry.addRecipe(new ItemStack(GSBlock.spawner, 1, EnumSpawner.SPIDER_SPAWNER.ordinal()), "www", "ses", "www",
                    'w', Blocks.WEB, 's', Items.SPIDER_EYE, 'e', Items.ENDER_EYE);
        }

        // candles
        GameRegistry.addRecipe(new ItemStack(GSBlock.candle, 1, 0), "t", "m", "s", 't', Items.STRING, 'm', new ItemStack(Items.DYE, 1, EnumDyeColor.WHITE.getDyeDamage()), 's', Items.SLIME_BALL);
        addSkullCandleReciepes(new ItemStack(GSBlock.candle, 1, 0));

        GameRegistry.addRecipe(new ItemStack(Items.SKULL, 1, 0), "c", 'c', new ItemStack(GSBlock.skullCandle, 1, EnumSkullCandle.SKELETON_SKULL.ordinal()));
        GameRegistry.addRecipe(new ItemStack(Items.SKULL, 1, 1), "c", 'c', new ItemStack(GSBlock.skullCandle, 1, EnumSkullCandle.WITHER_SKULL.ordinal()));
        GameRegistry.addRecipe(new ItemStack(Items.SKULL, 1, 2), "c", 'c', new ItemStack(GSBlock.skullCandle, 1, EnumSkullCandle.ZOMBIE_SKULL.ordinal()));

        if (ExtendedConfig.craftableNightStone) {
            GameRegistry.addRecipe(new ItemStack(GSBlock.trap, 1, 0), " p ", "rnr", " s ", 'n', Blocks.NETHER_BRICK, 'p', Blocks.STONE_PRESSURE_PLATE, 'r', Items.REDSTONE, 's', Blocks.SOUL_SAND);
        }
        if (ExtendedConfig.craftableThunderStone) {
            GameRegistry.addRecipe(new ItemStack(GSBlock.trap, 1, 1), " p ", "rnr", " s ", 'n', Blocks.STONEBRICK, 'p', Blocks.STONE_PRESSURE_PLATE, 'r', Items.REDSTONE, 's', Blocks.SOUL_SAND);
        }

        // altar
        net.minecraft.item.Item altarCrystal = (ExtendedConfig.hardAltarRecipe) ? Items.NETHER_STAR : Items.DIAMOND;
        GameRegistry.addRecipe(new ItemStack(GSBlock.altar), " h ", "sns", "bbb",
                'h', altarCrystal,
                's', new ItemStack(Items.SKULL, 1, 0),
                'n', new ItemStack(GSBlock.trap, 1, 0),
                'b', new ItemStack(GSBlock.boneBlock, 1, 0));
    }

    private static ItemStack getStackWithNTB(net.minecraft.block.Block block, int graveType, String ntbName) {
        ItemStack stack = new ItemStack(block, 1, 0);
        NBTTagCompound nbt = new NBTTagCompound();
        nbt.setInteger(ntbName, graveType);
        stack.setTagCompound(nbt);
        return stack;
    }

    public static void addSkullCandleReciepes(ItemStack candle) {
        GameRegistry.addRecipe(new ItemStack(GSBlock.skullCandle, 1, EnumSkullCandle.SKELETON_SKULL.ordinal()), "c", "s", 's', new ItemStack(Items.SKULL, 1, 0), 'c', candle);
        GameRegistry.addRecipe(new ItemStack(GSBlock.skullCandle, 1, EnumSkullCandle.WITHER_SKULL.ordinal()), "c", "s", 's', new ItemStack(Items.SKULL, 1, 1), 'c', candle);
        GameRegistry.addRecipe(new ItemStack(GSBlock.skullCandle, 1, EnumSkullCandle.ZOMBIE_SKULL.ordinal()), "c", "s", 's', new ItemStack(Items.SKULL, 1, 2), 'c', candle);
    }

    public static void addForestryBackpack(ItemStack backpack, net.minecraft.item.Item item) {
        GameRegistry.addRecipe(backpack, "sws", "ici", "sws", 'w', Blocks.WOOL, 'i', item, 's', Items.STRING, 'c', Blocks.CHEST);
    }
}
