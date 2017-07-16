package nightkosh.gravestone_extended.core;

import net.minecraftforge.fml.common.registry.GameRegistry;
import nightkosh.gravestone_extended.block.*;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class GSBlock extends nightkosh.gravestone.core.GSBlock {

    public static final BlockMemorial MEMORIAL = new BlockMemorial();
    public static final BlockExecution EXECUTION = new BlockExecution();

    public static final BlockSpawner SPAWNER = new BlockSpawner();
    public static final BlockTrap TRAP = new BlockTrap();
    public static final BlockPileOfBones PILE_OF_BONES = new BlockPileOfBones();

    public static final BlockBoneBlock BONE_BLOCK = new BlockBoneBlock();
    public static final BlockBoneSlab BONE_SLAB = new BlockBoneSlab();
    public static final BlockBoneStairs BONE_STAIRS = new BlockBoneStairs();

    public static final BlockHauntedChest HAUNTED_CHEST = new BlockHauntedChest();

    public static final BlockCandle CANDLE = new BlockCandle();
    public static final BlockSkullCandle SKULL_CANDLE = new BlockSkullCandle();

    public static final BlockAltar ALTAR = new BlockAltar();

    public static final BlockInvisibleWall INVISIBLE_WALL = new BlockInvisibleWall();

    public static final BlockCorpse CORPSE = new BlockCorpse();

    public static void registration() {
        GameRegistry.register(MEMORIAL);
        GameRegistry.register(EXECUTION);
        GameRegistry.register(SPAWNER);
        GameRegistry.register(TRAP);
        GameRegistry.register(PILE_OF_BONES);
        GameRegistry.register(BONE_BLOCK);
        GameRegistry.register(BONE_SLAB);
        GameRegistry.register(BONE_STAIRS);
        GameRegistry.register(HAUNTED_CHEST);
        GameRegistry.register(CANDLE);
        GameRegistry.register(SKULL_CANDLE);
        GameRegistry.register(ALTAR);
        GameRegistry.register(INVISIBLE_WALL);
        GameRegistry.register(CORPSE);
    }
}
