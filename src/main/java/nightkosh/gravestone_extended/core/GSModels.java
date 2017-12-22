package nightkosh.gravestone_extended.core;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.item.Item;
import net.minecraftforge.client.ForgeHooksClient;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import nightkosh.gravestone_extended.block.enums.*;
import nightkosh.gravestone_extended.item.ItemFish;
import nightkosh.gravestone_extended.item.ItemGSMonsterPlacer;
import nightkosh.gravestone_extended.tileentity.*;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
@GameRegistry.ObjectHolder(ModInfo.ID)
public class GSModels {

    @Mod.EventBusSubscriber(modid = ModInfo.ID)
    public static class RegistrationHandler {

        @SubscribeEvent
        public static void registerModels(final ModelRegistryEvent event) {
            //memorials
            registerModelsForTEBlocks(EnumMemorials.WOODEN_CROSS.ordinal(), EnumMemorials.ICE_CROSS.ordinal(), GSBlock.MEMORIAL, ResourcesModels.MEMORIAL, TileEntityMemorial.class);
            registerModelsForTEBlocks(EnumMemorials.WOODEN_OBELISK.ordinal(), EnumMemorials.ICE_OBELISK.ordinal(), GSBlock.MEMORIAL, ResourcesModels.MEMORIAL, TileEntityMemorial.Obelisk.class);
            registerModelsForTEBlocks(EnumMemorials.WOODEN_CELTIC_CROSS.ordinal(), EnumMemorials.ICE_CELTIC_CROSS.ordinal(), GSBlock.MEMORIAL, ResourcesModels.MEMORIAL, TileEntityMemorial.CelticCross.class);
            registerModelsForTEBlocks(EnumMemorials.WOODEN_STEVE_STATUE.ordinal(), EnumMemorials.ICE_STEVE_STATUE.ordinal(), GSBlock.MEMORIAL, ResourcesModels.MEMORIAL, TileEntityMemorial.SteveStatue.class);
            registerModelsForTEBlocks(EnumMemorials.WOODEN_VILLAGER_STATUE.ordinal(), EnumMemorials.ICE_VILLAGER_STATUE.ordinal(), GSBlock.MEMORIAL, ResourcesModels.MEMORIAL, TileEntityMemorial.VillagerStatue.class);
            registerModelsForTEBlocks(EnumMemorials.WOODEN_ANGEL_STATUE.ordinal(), EnumMemorials.ICE_ANGEL_STATUE.ordinal(), GSBlock.MEMORIAL, ResourcesModels.MEMORIAL, TileEntityMemorial.AngelStatue.class);
            registerModelsForTEBlocks(EnumMemorials.WOODEN_DOG_STATUE.ordinal(), EnumMemorials.ICE_DOG_STATUE.ordinal(), GSBlock.MEMORIAL, ResourcesModels.MEMORIAL, TileEntityMemorial.DogStatue.class);
            registerModelsForTEBlocks(EnumMemorials.WOODEN_CAT_STATUE.ordinal(), EnumMemorials.ICE_CAT_STATUE.ordinal(), GSBlock.MEMORIAL, ResourcesModels.MEMORIAL, TileEntityMemorial.CatStatue.class);
            registerModelsForTEBlocks(EnumMemorials.WOODEN_CREEPER_STATUE.ordinal(), EnumMemorials.ICE_CREEPER_STATUE.ordinal(), GSBlock.MEMORIAL, ResourcesModels.MEMORIAL, TileEntityMemorial.CreeperStatue.class);
            //executions
            registerModelsForTEBlocks(EnumExecution.GALLOWS.ordinal(), GSBlock.EXECUTION, ResourcesModels.EXECUTION, TileEntityExecution.class);
            registerModelsForTEBlocks(EnumExecution.GIBBET.ordinal(), GSBlock.EXECUTION, ResourcesModels.EXECUTION, TileEntityExecution.Gibbet.class);
            registerModelsForTEBlocks(EnumExecution.STOCKS.ordinal(), GSBlock.EXECUTION, ResourcesModels.EXECUTION, TileEntityExecution.Stocks.class);
            registerModelsForTEBlocks(EnumExecution.BURNING_STAKE.ordinal(), GSBlock.EXECUTION, ResourcesModels.EXECUTION, TileEntityExecution.BurningStake.class);
            //spawners
            registerModelsForTEBlocks(EnumSpawner.WITHER_SPAWNER.ordinal(), GSBlock.SPAWNER, ResourcesModels.SPAWNER, TileEntitySpawner.class);
            registerModelsForTEBlocks(EnumSpawner.SKELETON_SPAWNER.ordinal(), GSBlock.SPAWNER, ResourcesModels.SPAWNER, TileEntitySpawner.Skeleton.class);
            registerModelsForTEBlocks(EnumSpawner.ZOMBIE_SPAWNER.ordinal(), GSBlock.SPAWNER, ResourcesModels.SPAWNER, TileEntitySpawner.Zombie.class);
            registerModelsForTEBlocks(EnumSpawner.SPIDER_SPAWNER.ordinal(), GSBlock.SPAWNER, ResourcesModels.SPAWNER, TileEntitySpawner.Spider.class);
            //piles of bones
            registerModelsForTEBlocks(EnumPileOfBones.PILE_OF_BONES.ordinal(), GSBlock.PILE_OF_BONES, ResourcesModels.PILE_OF_BONES, TileEntityPileOfBones.class);
            registerModelsForTEBlocks(EnumPileOfBones.PILE_OF_BONES_WITH_SKULL.ordinal(), GSBlock.PILE_OF_BONES, ResourcesModels.PILE_OF_BONES, TileEntityPileOfBones.Skull.class);
            registerModelsForTEBlocks(EnumPileOfBones.PILE_OF_BONES_WITH_SKULL_CRAWLER.ordinal(), GSBlock.PILE_OF_BONES, ResourcesModels.PILE_OF_BONES, TileEntityPileOfBones.Crawler.class);

            //haunted chest
            registerModelsForTEBlocks(0, EnumHauntedChest.values().length - 1, GSBlock.HAUNTED_CHEST, ResourcesModels.HAUNTED_CHEST, TileEntityHauntedChest.class);

            //skull candle
            registerModelsForTEBlocks(EnumSkullCandle.SKELETON_SKULL.ordinal(), GSBlock.SKULL_CANDLE, ResourcesModels.SKULL_CANDLE, TileEntitySkullCandle.class);
            registerModelsForTEBlocks(EnumSkullCandle.WITHER_SKULL.ordinal(), GSBlock.SKULL_CANDLE, ResourcesModels.SKULL_CANDLE, TileEntitySkullCandle.Wither.class);
            registerModelsForTEBlocks(EnumSkullCandle.ZOMBIE_SKULL.ordinal(), GSBlock.SKULL_CANDLE, ResourcesModels.SKULL_CANDLE, TileEntitySkullCandle.Zombie.class);

            //corpses
            registerModelsForTEBlocks(EnumCorpse.STEVE.ordinal(), GSBlock.CORPSE, ResourcesModels.CORPSE, TileEntityCorpse.Steve.class);
            registerModelsForTEBlocks(EnumCorpse.VILLAGER.ordinal(), GSBlock.CORPSE, ResourcesModels.CORPSE, TileEntityCorpse.Villager.class);
            registerModelsForTEBlocks(EnumCorpse.DOG.ordinal(), GSBlock.CORPSE, ResourcesModels.CORPSE, TileEntityCorpse.Dog.class);
            registerModelsForTEBlocks(EnumCorpse.CAT.ordinal(), GSBlock.CORPSE, ResourcesModels.CORPSE, TileEntityCorpse.Cat.class);
            registerModelsForTEBlocks(EnumCorpse.HORSE.ordinal(), GSBlock.CORPSE, ResourcesModels.CORPSE, TileEntityCorpse.Horse.class);
            registerModelsForTEBlocks(EnumCorpse.ZOMBIE.ordinal(), GSBlock.CORPSE, ResourcesModels.CORPSE, TileEntityCorpse.Zombie.class);
            registerModelsForTEBlocks(EnumCorpse.ZOMBIE_VILLAGER.ordinal(), GSBlock.CORPSE, ResourcesModels.CORPSE, TileEntityCorpse.ZombieVillager.class);
            registerModelsForTEBlocks(EnumCorpse.SKELETON.ordinal(), GSBlock.CORPSE, ResourcesModels.CORPSE, TileEntityCorpse.Skeleton.class);
            registerModelsForTEBlocks(EnumCorpse.WITCH.ordinal(), GSBlock.CORPSE, ResourcesModels.CORPSE, TileEntityCorpse.Witch.class);

            //traps
            ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(GSBlock.TRAP), EnumTrap.NIGHT_STONE.ordinal(), ResourcesModels.NIGHT_STONE);
            ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(GSBlock.TRAP), EnumTrap.THUNDER_STONE.ordinal(), ResourcesModels.THUNDER_STONE);
            ModelBakery.registerItemVariants(Item.getItemFromBlock(GSBlock.TRAP), ResourcesModels.NIGHT_STONE, ResourcesModels.THUNDER_STONE);

            //bone blocks
            ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(GSBlock.BONE_BLOCK), EnumBoneBlock.BONE_BLOCK.ordinal(), ResourcesModels.BONE_BLOCK);
            ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(GSBlock.BONE_BLOCK), EnumBoneBlock.SKULL_BONE_BLOCK.ordinal(), ResourcesModels.BONE_BLOCK_WITH_SKULL);
            ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(GSBlock.BONE_BLOCK), EnumBoneBlock.CRAWLER_BONE_BLOCK.ordinal(), ResourcesModels.BONE_BLOCK);
            ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(GSBlock.BONE_BLOCK), EnumBoneBlock.CRAWLER_SKULL_BONE_BLOCK.ordinal(), ResourcesModels.BONE_BLOCK_WITH_SKULL);
            ModelBakery.registerItemVariants(Item.getItemFromBlock(GSBlock.BONE_BLOCK), ResourcesModels.BONE_BLOCK, ResourcesModels.BONE_BLOCK_WITH_SKULL,
                    ResourcesModels.BONE_BLOCK, ResourcesModels.BONE_BLOCK_WITH_SKULL);


            ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(GSBlock.BONE_SLAB), 0, ResourcesModels.BONE_SLAB);
            ModelBakery.registerItemVariants(Item.getItemFromBlock(GSBlock.BONE_SLAB), ResourcesModels.BONE_SLAB);
            ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(GSBlock.BONE_STAIRS), 0, ResourcesModels.BONE_STAIRS);
            ModelBakery.registerItemVariants(Item.getItemFromBlock(GSBlock.BONE_STAIRS), ResourcesModels.BONE_STAIRS);

            //altar
            ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(GSBlock.ALTAR), 0, ResourcesModels.ALTAR);
            ModelBakery.registerItemVariants(Item.getItemFromBlock(GSBlock.ALTAR), ResourcesModels.ALTAR);

            //candle
            ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(GSBlock.CANDLE), 0, ResourcesModels.CANDLE);
            ForgeHooksClient.registerTESRItemStack(Item.getItemFromBlock(GSBlock.CANDLE), 0, TileEntityCandle.class);
            ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(GSBlock.CANDLE), 0, ResourcesModels.CANDLE);


            // ITEMS
            ModelLoader.setCustomModelResourceLocation(GSItem.CHISEL, 0, ResourcesModels.chiselModel);
            ModelBakery.registerItemVariants(GSItem.CHISEL, ResourcesModels.chiselModel);

            for (ItemGSMonsterPlacer.EnumEggs egg : ItemGSMonsterPlacer.EnumEggs.values()) {
                ModelLoader.setCustomModelResourceLocation(GSItem.SPAWN_EGG, egg.ordinal(), ResourcesModels.SPAWN_EGG_MODEL);
            }
            ModelBakery.registerItemVariants(GSItem.SPAWN_EGG, ResourcesModels.SPAWN_EGG_MODEL);

            ModelLoader.setCustomModelResourceLocation(GSItem.TOXIC_SLIME, 0, ResourcesModels.TOXIC_SLIME);

            ModelLoader.setCustomModelResourceLocation(GSItem.FISH, ItemFish.EnumFishType.BLUE_JELLYFISH.ordinal(), ResourcesModels.BLUE_JELLYFISH);
            ModelLoader.setCustomModelResourceLocation(GSItem.FISH, ItemFish.EnumFishType.GREEN_JELLYFISH.ordinal(), ResourcesModels.GREEN_JELLYFISH);
            ModelLoader.setCustomModelResourceLocation(GSItem.FISH, ItemFish.EnumFishType.MAGMA_JELLYFISH.ordinal(), ResourcesModels.MAGMA_JELLYFISH);
            ModelLoader.setCustomModelResourceLocation(GSItem.FISH, ItemFish.EnumFishType.BONE_FISH.ordinal(), ResourcesModels.BONE_FISH);
            ModelBakery.registerItemVariants(GSItem.FISH, ResourcesModels.BLUE_JELLYFISH, ResourcesModels.GREEN_JELLYFISH, ResourcesModels.MAGMA_JELLYFISH,
                    ResourcesModels.BONE_FISH);

            ModelLoader.setCustomModelResourceLocation(GSItem.BONE_SWORD, 0, ResourcesModels.BONE_SWORD);
            ModelLoader.setCustomModelResourceLocation(GSItem.BONE_SWORD_IRON, 0, ResourcesModels.BONE_SWORD_IRON);
            ModelLoader.setCustomModelResourceLocation(GSItem.BONE_SWORD_GOLDEN, 0, ResourcesModels.BONE_SWORD_GOLDEN);
            ModelLoader.setCustomModelResourceLocation(GSItem.BONE_SWORD_DIAMOND, 0, ResourcesModels.BONE_SWORD_DIAMOND);
            ModelLoader.setCustomModelResourceLocation(GSItem.BONE_SHIELD, 0, ResourcesModels.BONE_SHIELD);

            ModelLoader.setCustomModelResourceLocation(GSItem.BONE_FISHING_POLE, 0, ResourcesModels.BONE_FISHING_POLE);

            ModelLoader.setCustomModelResourceLocation(GSItem.BONE_HOE, 0, ResourcesModels.BONE_HOE);
            ModelLoader.setCustomModelResourceLocation(GSItem.BONE_HOE_IRON, 0, ResourcesModels.BONE_HOE_IRON);
            ModelLoader.setCustomModelResourceLocation(GSItem.BONE_HOE_GOLDEN, 0, ResourcesModels.BONE_HOE_GOLDEN);
            ModelLoader.setCustomModelResourceLocation(GSItem.BONE_HOE_DIAMOND, 0, ResourcesModels.BONE_HOE_DIAMOND);

            Item item = Item.getItemFromBlock(GSBlock.TOXIC_WATER);
            ModelResourceLocation modelResourceLocation = new ModelResourceLocation(ModInfo.ID + ":" + "fluid", GSBlock.TOXIC_WATER.getFluid().getName());
            ModelBakery.registerItemVariants(item);
            ModelLoader.setCustomMeshDefinition(item, stack -> modelResourceLocation);
            ModelLoader.setCustomStateMapper(GSBlock.TOXIC_WATER, new StateMapperBase() {
                protected ModelResourceLocation getModelResourceLocation(IBlockState state) {
                    return modelResourceLocation;
                }
            });

//            ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
        }

        private static void registerModelsForTEBlocks(int startMeta, int endMeta, Block block, ModelResourceLocation model, Class TEClass) {
            for (int meta = startMeta; meta <= endMeta; meta++) {
                registerModelsForTEBlocks(meta, block, model, TEClass);
            }
        }

        private static void registerModelsForTEBlocks(int meta, Block block, ModelResourceLocation model, Class TEClass) {
            ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), meta, model);
            ForgeHooksClient.registerTESRItemStack(Item.getItemFromBlock(block), meta, TEClass);
            ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), meta, model);
        }
    }
}
