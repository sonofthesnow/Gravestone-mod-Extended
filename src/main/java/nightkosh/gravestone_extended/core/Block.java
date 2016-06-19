package nightkosh.gravestone_extended.core;

import nightkosh.gravestone_extended.ModGravestoneExtended;
import nightkosh.gravestone_extended.block.*;
import nightkosh.gravestone_extended.item.itemblock.*;
import net.minecraft.block.BlockStone;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class Block extends nightkosh.gravestone.core.GSBlock{

    public static final IBlockState DEFAULT_STONE_STATE = Blocks.stone.getDefaultState();
    public static final int DIORITE_META = Blocks.stone.getMetaFromState(DEFAULT_STONE_STATE.withProperty(BlockStone.VARIANT, BlockStone.EnumType.DIORITE_SMOOTH));
    public static final int ANDESITE_META = Blocks.stone.getMetaFromState(DEFAULT_STONE_STATE.withProperty(BlockStone.VARIANT, BlockStone.EnumType.ANDESITE_SMOOTH));
    public static final int GRANITE_META = Blocks.stone.getMetaFromState(DEFAULT_STONE_STATE.withProperty(BlockStone.VARIANT, BlockStone.EnumType.GRANITE_SMOOTH));

    public static BlockSpawner spawner;
    public static BlockTrap trap;
    public static BlockMemorial memorial;
    public static BlockPileOfBones pileOfBones;

    public static BlockBoneBlock boneBlock;
    public static BlockBoneSlab boneSlab;
    public static BlockBoneStairs boneStairs;

    public static BlockHauntedChest hauntedChest;

    public static BlockCandle candle;
    public static BlockSkullCandle skullCandle;

    public static BlockAltar altar;

    public static BlockInvisibleWall invisibleWall;

    public static void registration() {
        // memorials
        memorial = new BlockMemorial();
        GameRegistry.registerBlock(memorial, ItemBlockGSMemorial.class, "GSMemorial");

        // wither spawner
        spawner = new BlockSpawner();
        GameRegistry.registerBlock(spawner, ItemBlockGSSpawner.class, "GSSpawner");

        // trap
        trap = new BlockTrap();
        GameRegistry.registerBlock(trap, ItemBlockGSTrap.class, "GSTrap");

        //pile of bones
        pileOfBones = new BlockPileOfBones();
        GameRegistry.registerBlock(pileOfBones, ItemBlockGSPileOfBones.class, "GSPileOfBones");

        // bone block
        boneBlock = new BlockBoneBlock();
        GameRegistry.registerBlock(boneBlock, ItemBlockGSBoneBlock.class, "GSBoneBlock");

        // bone slab
        boneSlab = new BlockBoneSlab();
        GameRegistry.registerBlock(boneSlab, "GSBoneSlab");

        // bone stairs
        boneStairs = new BlockBoneStairs();
        GameRegistry.registerBlock(boneStairs, "GSBoneStairs");

        // hauntedChest
        hauntedChest = new BlockHauntedChest();
        GameRegistry.registerBlock(hauntedChest, ItemBlockGSHauntedChest.class, "GSHauntedChest");

        // skull candle
        candle = new BlockCandle();
        GameRegistry.registerBlock(candle, ItemBlockGSCandle.class, "GSCandle");
        skullCandle = new BlockSkullCandle();
        GameRegistry.registerBlock(skullCandle, ItemBlockGSSkullCandle.class, "GSSkullCandle");

        // altar
        altar = new BlockAltar();
        GameRegistry.registerBlock(altar, "GSAltar");

        invisibleWall = new BlockInvisibleWall();
        GameRegistry.registerBlock(invisibleWall, "GSInvisibleWall");

        ModGravestoneExtended.proxy.registerBlocksModels();
    }
}
