package nightkosh.gravestone_extended.core;

import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.GameRegistry;
import nightkosh.gravestone_extended.block.*;
import nightkosh.gravestone_extended.item.itemblock.*;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class GSBlock extends nightkosh.gravestone.core.GSBlock {

    public static final BlockMemorial MEMORIAL = new BlockMemorial();
    public static final ItemBlock MEMORIAL_IB = new ItemBlockGSMemorial(MEMORIAL);

    public static final BlockExecution EXECUTION = new BlockExecution();
    public static final ItemBlock EXECUTION_IB = new ItemBlockExecution(EXECUTION);

    public static final BlockSpawner SPAWNER = new BlockSpawner();
    public static final ItemBlock SPAWNER_IB = new ItemBlockGSSpawner(SPAWNER);

    public static final BlockTrap TRAP = new BlockTrap();
    public static final ItemBlock TRAP_IB = new ItemBlockGSTrap(TRAP);

    public static final BlockPileOfBones PILE_OF_BONES = new BlockPileOfBones();
    public static final ItemBlock PILE_OF_BONES_IB = new ItemBlockGSPileOfBones(PILE_OF_BONES);

    public static final BlockBoneBlock BONE_BLOCK = new BlockBoneBlock();
    public static final ItemBlock BONE_BLOCK_IB = new ItemBlockGSBoneBlock(BONE_BLOCK);
    public static final BlockBoneSlab BONE_SLAB = new BlockBoneSlab();


    public static final BlockBoneStairs BONE_STAIRS = new BlockBoneStairs();

    public static final BlockHauntedChest HAUNTED_CHEST = new BlockHauntedChest();
    public static final ItemBlock HAUNTED_CHEST_IB = new ItemBlockGSHauntedChest(HAUNTED_CHEST);

    public static final BlockCandle CANDLE = new BlockCandle();
    public static final ItemBlock CANDLE_IB = new ItemBlockGSCandle(CANDLE);

    public static final BlockSkullCandle SKULL_CANDLE = new BlockSkullCandle();
    public static final ItemBlock SKULL_CANDLE_IB = new ItemBlockGSSkullCandle(SKULL_CANDLE);

    public static final BlockAltar ALTAR = new BlockAltar();

    public static final BlockInvisibleWall INVISIBLE_WALL = new BlockInvisibleWall();

    public static final BlockCorpse CORPSE = new BlockCorpse();
    public static final ItemBlock CORPSE_IB = new ItemBlockCorpse(CORPSE);

    public static void registration() {
        GameRegistry.register(MEMORIAL);
        GameRegistry.register(MEMORIAL_IB);

        GameRegistry.register(EXECUTION);
        GameRegistry.register(EXECUTION_IB);

        GameRegistry.register(SPAWNER);
        GameRegistry.register(SPAWNER_IB);

        GameRegistry.register(TRAP);
        GameRegistry.register(TRAP_IB);

        GameRegistry.register(PILE_OF_BONES);
        GameRegistry.register(PILE_OF_BONES_IB);

        GameRegistry.register(BONE_BLOCK);
        GameRegistry.register(BONE_BLOCK_IB);

        GameRegistry.register(BONE_SLAB);
        GameRegistry.register(BONE_STAIRS);

        GameRegistry.register(HAUNTED_CHEST);
        GameRegistry.register(HAUNTED_CHEST_IB);

        GameRegistry.register(CANDLE);
        GameRegistry.register(CANDLE_IB);

        GameRegistry.register(SKULL_CANDLE);
        GameRegistry.register(SKULL_CANDLE_IB);

        GameRegistry.register(ALTAR);

        GameRegistry.register(INVISIBLE_WALL);

        GameRegistry.register(CORPSE);
        GameRegistry.register(CORPSE_IB);
    }
}
