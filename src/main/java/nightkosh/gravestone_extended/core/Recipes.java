package nightkosh.gravestone_extended.core;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.registry.GameRegistry;
import nightkosh.gravestone_extended.block.enums.EnumMemorials;
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
        GameRegistry.addRecipe(new ItemStack(ExtendedItem.chisel), "   ", "s  ", " i ", 's', Items.stick, 'i', Items.iron_ingot);
        // pile of bones
        GameRegistry.addRecipe(new ItemStack(Block.pileOfBones, 1, 0), "   ", " b ", "bbb", 'b', Items.bone);
        GameRegistry.addRecipe(new ItemStack(Block.pileOfBones, 9, 1), "bbb", "bsb", "bbb", 'b', new ItemStack(Block.pileOfBones, 1, 0), 's', new ItemStack(Items.skull, 1, 0));
        // piles to bones
        GameRegistry.addRecipe(new ItemStack(Items.bone, 4), "p", 'p', new ItemStack(Block.pileOfBones, 1, 0));
        GameRegistry.addRecipe(new ItemStack(Items.skull, 1, 0), "ppp", "ppp", "ppp", 'p', new ItemStack(Block.pileOfBones, 1, 1));
        // bone blocks
        GameRegistry.addRecipe(new ItemStack(Block.boneBlock, 1, 0), "ppp", "ppp", "ppp", 'p', new ItemStack(Block.pileOfBones, 1, 0));
        GameRegistry.addRecipe(new ItemStack(Block.boneBlock, 9, 1), "bbb", "bsb", "bbb", 'b', new ItemStack(Block.boneBlock, 1, 0), 's', new ItemStack(Items.skull, 1, 0));

        GameRegistry.addRecipe(new ItemStack(Block.boneBlock), "x", "x", 'x', Block.boneSlab);
        GameRegistry.addRecipe(new ItemStack(Block.boneSlab, 6), "xxx", 'x', Block.boneBlock);
        GameRegistry.addRecipe(new ItemStack(Block.boneStairs, 4), "x  ", "xx ", "xxx", 'x', Block.boneBlock);
        // Bone block to piles
        GameRegistry.addRecipe(new ItemStack(Block.pileOfBones, 9, 0), "x", 'x', Block.boneBlock);


        // memorials
        // gibbets
        GameRegistry.addRecipe(getStackWithNTB(Block.memorial, EnumMemorials.GIBBET.ordinal(), "Type"), "ww ", "wr ", "wc ", 'w', Blocks.planks, 'r', Items.lead, 'c', ExtendedItem.chisel);
        // stocks
        GameRegistry.addRecipe(getStackWithNTB(Block.memorial, EnumMemorials.STOCKS.ordinal(), "Type"), "wsw", "wcw", 'w', Blocks.planks, 's', Blocks.wooden_slab, 'c', ExtendedItem.chisel);
        // burning stake
        GameRegistry.addRecipe(getStackWithNTB(Block.memorial, EnumMemorials.BURNING_STAKE.ordinal(), "Type"), " w ", "www", "hwh", 'w', Blocks.planks, 'h', Blocks.hay_block);

    //TODO ????
        // sword graves TODO remove
//        for (net.minecraft.item.Item sword : GraveStoneHelper.swordsList) {
//            GameRegistry.addRecipe(GraveStoneHelper.getSwordAsGrave(net.minecraft.item.Item.getItemFromBlock(Block.graveStone), new ItemStack(sword, 1)), "sc", 's', sword, 'c', ExtendedItem.chisel);
//        }

        // spawners
        if (ExtendedConfig.craftableWitherSpawner) {
            GameRegistry.addRecipe(new ItemStack(Block.spawner, 1, EnumSpawner.WITHER_SPAWNER.ordinal()), "bcb", "cec", "cbc",
                    'c', new ItemStack(Block.skullCandle, 1, EnumSkullCandle.WITHER_SKULL.ordinal()),
                    'b', new ItemStack(Items.dye, 1, 15), 'e', Items.ender_eye);
        }
        if (ExtendedConfig.craftableSpawners) {
            GameRegistry.addRecipe(new ItemStack(Block.spawner, 1, EnumSpawner.SKELETON_SPAWNER.ordinal()), "bcb", "cec", "cbc",
                    'c', new ItemStack(Block.skullCandle, 1, EnumSkullCandle.SKELETON_SKULL.ordinal()),
                    'b', new ItemStack(Items.dye, 1, 15), 'e', Items.ender_eye);
            GameRegistry.addRecipe(new ItemStack(Block.spawner, 1, EnumSpawner.ZOMBIE_SPAWNER.ordinal()), "bcb", "cec", "cbc",
                    'c', new ItemStack(Block.skullCandle, 1, EnumSkullCandle.ZOMBIE_SKULL.ordinal()),
                    'b', new ItemStack(Items.dye, 1, 15), 'e', Items.ender_eye);
        }

        // candles
        GameRegistry.addRecipe(new ItemStack(Block.candle, 1, 0), "t", "m", "s", 't', Items.string, 'm', new ItemStack(Items.dye, 1, 15), 's', Items.slime_ball);
        addSkullCandleReciepes(new ItemStack(Block.candle, 1, 0));

        GameRegistry.addRecipe(new ItemStack(Items.skull, 1, 0), "c", 'c', new ItemStack(Block.skullCandle, 1, EnumSkullCandle.SKELETON_SKULL.ordinal()));
        GameRegistry.addRecipe(new ItemStack(Items.skull, 1, 1), "c", 'c', new ItemStack(Block.skullCandle, 1, EnumSkullCandle.WITHER_SKULL.ordinal()));
        GameRegistry.addRecipe(new ItemStack(Items.skull, 1, 2), "c", 'c', new ItemStack(Block.skullCandle, 1, EnumSkullCandle.ZOMBIE_SKULL.ordinal()));

        if (ExtendedConfig.craftableNightStone) {
            GameRegistry.addRecipe(new ItemStack(Block.trap, 1, 0), " p ", "rnr", " s ", 'n', Blocks.nether_brick, 'p', Blocks.stone_pressure_plate, 'r', Items.redstone, 's', Blocks.soul_sand);
        }
        if (ExtendedConfig.craftableThunderStone) {
            GameRegistry.addRecipe(new ItemStack(Block.trap, 1, 1), " p ", "rnr", " s ", 'n', Blocks.stonebrick, 'p', Blocks.stone_pressure_plate, 'r', Items.redstone, 's', Blocks.soul_sand);
        }

        // altar
        net.minecraft.item.Item altarCrystal = (ExtendedConfig.hardAltarRecipe) ? Items.nether_star : Items.diamond;
        GameRegistry.addRecipe(new ItemStack(Block.altar), " h ", "sns", "bbb",
                'h', altarCrystal,
                's', new ItemStack(Items.skull, 1, 0),
                'n', new ItemStack(Block.trap, 1, 0),
                'b', new ItemStack(Block.boneBlock, 1, 0));
    }

    private static ItemStack getStackWithNTB(net.minecraft.block.Block block, int graveType, String ntbName) {
        ItemStack stack = new ItemStack(block, 1, 0);
        NBTTagCompound nbt = new NBTTagCompound();
        nbt.setInteger(ntbName, graveType);
        stack.setTagCompound(nbt);
        return stack;
    }

    public static void addSkullCandleReciepes(ItemStack candle) {
        GameRegistry.addRecipe(new ItemStack(Block.skullCandle, 1, EnumSkullCandle.SKELETON_SKULL.ordinal()), "c", "s", 's', new ItemStack(Items.skull, 1, 0), 'c', candle);
        GameRegistry.addRecipe(new ItemStack(Block.skullCandle, 1, EnumSkullCandle.WITHER_SKULL.ordinal()), "c", "s", 's', new ItemStack(Items.skull, 1, 1), 'c', candle);
        GameRegistry.addRecipe(new ItemStack(Block.skullCandle, 1, EnumSkullCandle.ZOMBIE_SKULL.ordinal()), "c", "s", 's', new ItemStack(Items.skull, 1, 2), 'c', candle);
    }

    public static void addForestryBackpack(ItemStack backpack, net.minecraft.item.Item item) {
        GameRegistry.addRecipe(backpack, "sws", "ici", "sws", 'w', Blocks.wool, 'i', item, 's', Items.string, 'c', Blocks.chest);
    }
}
