package nightkosh.gravestone_extended.core;

import net.minecraftforge.fml.common.registry.GameRegistry;
import nightkosh.gravestone_extended.ModGravestoneExtended;
import nightkosh.gravestone_extended.block.*;
import nightkosh.gravestone_extended.item.itemblock.*;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class GSBlock extends nightkosh.gravestone.core.GSBlock{

    public static BlockSpawner spawner;
    public static BlockTrap trap;
    public static BlockMemorial memorial;
    public static BlockExecution execution;
    public static BlockPileOfBones pileOfBones;

    public static BlockBoneBlock boneBlock;
    public static BlockBoneSlab boneSlab;
    public static BlockBoneStairs boneStairs;

    public static BlockHauntedChest hauntedChest;

    public static BlockCandle candle;
    public static BlockSkullCandle skullCandle;

    public static BlockAltar altar;

    public static BlockInvisibleWall invisibleWall;

    public static BlockCorpse corpse;

    public static void registration() {
        // memorials
        memorial = new BlockMemorial();
        GameRegistry.registerBlock(memorial, ItemBlockGSMemorial.class, "GSMemorial");

        // executions
        execution = new BlockExecution();
        GameRegistry.registerBlock(execution, ItemBlockExecution.class, "GSExecution");

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

        corpse = new BlockCorpse();
        GameRegistry.registerBlock(corpse, ItemBlockCorpse.class, "GSCorpse");

        ModGravestoneExtended.proxy.registerBlocksModels();
    }
}
