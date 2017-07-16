package nightkosh.gravestone_extended.core;

import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.GameRegistry;
import nightkosh.gravestone_extended.item.ItemChisel;
import nightkosh.gravestone_extended.item.ItemGSMonsterPlacer;
import nightkosh.gravestone_extended.item.itemblock.*;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class GSItem {

    public static final Item CHISEL = new ItemChisel();
    public static final Item SPAWN_EGG = new ItemGSMonsterPlacer();


    public static final ItemBlock MEMORIAL_IB = new ItemBlockGSMemorial(GSBlock.MEMORIAL);
    public static final ItemBlock EXECUTION_IB = new ItemBlockExecution(GSBlock.EXECUTION);
    public static final ItemBlock SPAWNER_IB = new ItemBlockGSSpawner(GSBlock.SPAWNER);
    public static final ItemBlock TRAP_IB = new ItemBlockGSTrap(GSBlock.TRAP);
    public static final ItemBlock PILE_OF_BONES_IB = new ItemBlockGSPileOfBones(GSBlock.PILE_OF_BONES);
    public static final ItemBlock BONE_BLOCK_IB = new ItemBlockGSBoneBlock(GSBlock.BONE_BLOCK);
    public static final ItemBlock HAUNTED_CHEST_IB = new ItemBlockGSHauntedChest(GSBlock.HAUNTED_CHEST);
    public static final ItemBlock CANDLE_IB = new ItemBlockGSCandle(GSBlock.CANDLE);
    public static final ItemBlock SKULL_CANDLE_IB = new ItemBlockGSSkullCandle(GSBlock.SKULL_CANDLE);
    public static final ItemBlock CORPSE_IB = new ItemBlockCorpse(GSBlock.CORPSE);

    public static void registration() {
        GameRegistry.register(CHISEL);
        GameRegistry.register(SPAWN_EGG);

        GameRegistry.register(MEMORIAL_IB);
        GameRegistry.register(EXECUTION_IB);
        GameRegistry.register(SPAWNER_IB);
        GameRegistry.register(TRAP_IB);
        GameRegistry.register(PILE_OF_BONES_IB);
        GameRegistry.register(BONE_BLOCK_IB);
        GameRegistry.register(HAUNTED_CHEST_IB);
        GameRegistry.register(CANDLE_IB);
        GameRegistry.register(SKULL_CANDLE_IB);
        GameRegistry.register(CORPSE_IB);
    }
}
