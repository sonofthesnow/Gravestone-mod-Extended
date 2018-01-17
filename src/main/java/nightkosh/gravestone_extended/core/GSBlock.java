package nightkosh.gravestone_extended.core;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.IForgeRegistry;
import nightkosh.gravestone_extended.block.*;
import nightkosh.gravestone_extended.fluid.block.BlockFluidToxicWater;
import nightkosh.gravestone_extended.item.itemblock.*;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
@GameRegistry.ObjectHolder(ModInfo.ID)
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
    public static final ItemBlockBoneSlab BONE_SLAB_IB = new ItemBlockBoneSlab(BONE_SLAB);

    public static final BlockBoneStairs BONE_STAIRS = new BlockBoneStairs();
    public static final ItemBlockBoneStairs BONE_STAIRS_IB = new ItemBlockBoneStairs(BONE_STAIRS);

    public static final BlockHauntedChest HAUNTED_CHEST = new BlockHauntedChest();
    public static final ItemBlock HAUNTED_CHEST_IB = new ItemBlockGSHauntedChest(HAUNTED_CHEST);

    public static final BlockCandle CANDLE = new BlockCandle();
    public static final ItemBlock CANDLE_IB = new ItemBlockGSCandle(CANDLE);

    public static final BlockSkullCandle SKULL_CANDLE = new BlockSkullCandle();
    public static final ItemBlock SKULL_CANDLE_IB = new ItemBlockGSSkullCandle(SKULL_CANDLE);

    public static final BlockAltar ALTAR = new BlockAltar();
    public static final ItemBlock ALTAR_IB = new ItemBlockAltar(ALTAR);

    public static final BlockInvisibleWall INVISIBLE_WALL = new BlockInvisibleWall();

    public static final BlockCorpse CORPSE = new BlockCorpse();
    public static final ItemBlock CORPSE_IB = new ItemBlockCorpse(CORPSE);

    public static final BlockFluidToxicWater TOXIC_WATER = new BlockFluidToxicWater();
    public static final ItemBlock TOXIC_WATER_IB = new ItemBlockToxicWater(TOXIC_WATER);

    @Mod.EventBusSubscriber(modid = ModInfo.ID)
    public static class RegistrationHandler {

        @SubscribeEvent
        public static void registerBlocks(final RegistryEvent.Register<Block> event) {
            final IForgeRegistry<Block> registry = event.getRegistry();
            registry.registerAll(MEMORIAL, EXECUTION, SPAWNER, TRAP, PILE_OF_BONES, BONE_BLOCK,
                    BONE_SLAB, BONE_STAIRS, HAUNTED_CHEST, CANDLE, SKULL_CANDLE, ALTAR,
                    INVISIBLE_WALL, CORPSE, TOXIC_WATER);
        }

        @SubscribeEvent
        public static void registerItemBlocks(final RegistryEvent.Register<Item> event) {
            final IForgeRegistry<Item> registry = event.getRegistry();
            registry.registerAll(MEMORIAL_IB, EXECUTION_IB, SPAWNER_IB, TRAP_IB, PILE_OF_BONES_IB,
                    BONE_BLOCK_IB, BONE_SLAB_IB, BONE_STAIRS_IB, HAUNTED_CHEST_IB, CANDLE_IB, SKULL_CANDLE_IB,
                    ALTAR_IB, CORPSE_IB);

            registry.register(TOXIC_WATER_IB);
        }
    }
}
