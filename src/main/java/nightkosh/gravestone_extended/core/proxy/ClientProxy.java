package nightkosh.gravestone_extended.core.proxy;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.item.Item;
import net.minecraft.util.text.translation.I18n;
import net.minecraftforge.client.ForgeHooksClient;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import nightkosh.gravestone.core.proxy.ProxyHelper;
import nightkosh.gravestone.tileentity.TileEntityGrave;
import nightkosh.gravestone_extended.block.enums.*;
import nightkosh.gravestone_extended.core.GSBlock;
import nightkosh.gravestone_extended.core.GSItem;
import nightkosh.gravestone_extended.core.ResourcesModels;
import nightkosh.gravestone_extended.core.event.RenderEventHandler;
import nightkosh.gravestone_extended.entity.EntityRaven;
import nightkosh.gravestone_extended.entity.helper.EntityGroupOfGravesMobSpawnerHelper;
import nightkosh.gravestone_extended.entity.monster.EntityDamnedWarrior;
import nightkosh.gravestone_extended.entity.monster.EntityGSSkeleton;
import nightkosh.gravestone_extended.entity.monster.crawler.EntitySkullCrawler;
import nightkosh.gravestone_extended.entity.monster.crawler.EntitySkullCrawler.SkullCrawlerType;
import nightkosh.gravestone_extended.entity.monster.crawler.EntityWitherSkullCrawler;
import nightkosh.gravestone_extended.entity.monster.crawler.EntityZombieSkullCrawler;
import nightkosh.gravestone_extended.entity.monster.horse.EntityUndeadHorse;
import nightkosh.gravestone_extended.entity.monster.pet.EntitySkeletonCat;
import nightkosh.gravestone_extended.entity.monster.pet.EntitySkeletonDog;
import nightkosh.gravestone_extended.entity.monster.pet.EntityZombieCat;
import nightkosh.gravestone_extended.entity.monster.pet.EntityZombieDog;
import nightkosh.gravestone_extended.gui.GSGraveTextGui;
import nightkosh.gravestone_extended.item.ItemGSMonsterPlacer;
import nightkosh.gravestone_extended.models.entity.ModelDamnedWarrior;
import nightkosh.gravestone_extended.models.entity.ModelUndeadCat;
import nightkosh.gravestone_extended.models.entity.ModelUndeadDog;
import nightkosh.gravestone_extended.renderer.entity.*;
import nightkosh.gravestone_extended.renderer.tileentity.*;
import nightkosh.gravestone_extended.tileentity.*;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class ClientProxy extends CommonProxy {

    public void registerTERenderers() {
        // register Memorials renderers
        ClientRegistry.registerTileEntity(TileEntityMemorial.class, "GSMemorial", new TileEntityMemorialRenderer());
        ClientRegistry.registerTileEntity(TileEntityMemorial.Obelisk.class, "GSMemorialObelisk", new TileEntityMemorialRenderer.Obelisk());
        ClientRegistry.registerTileEntity(TileEntityMemorial.CelticCross.class, "GSMemorialCelticCross", new TileEntityMemorialRenderer.CelticCross());
        ClientRegistry.registerTileEntity(TileEntityMemorial.SteveStatue.class, "GSMemorialSteveStatue", new TileEntityMemorialRenderer.SteveStatue());
        ClientRegistry.registerTileEntity(TileEntityMemorial.VillagerStatue.class, "GSMemorialVillagerStatue", new TileEntityMemorialRenderer.VillagerStatue());
        ClientRegistry.registerTileEntity(TileEntityMemorial.AngelStatue.class, "GSMemorialAngelStatue", new TileEntityMemorialRenderer.AngelStatue());
        ClientRegistry.registerTileEntity(TileEntityMemorial.DogStatue.class, "GSMemorialDogStatue", new TileEntityMemorialRenderer.DogStatue());
        ClientRegistry.registerTileEntity(TileEntityMemorial.CatStatue.class, "GSMemorialCatStatue", new TileEntityMemorialRenderer.CatStatue());
        ClientRegistry.registerTileEntity(TileEntityMemorial.CreeperStatue.class, "GSMemorialCreeperStatue", new TileEntityMemorialRenderer.CreeperStatue());

        // register Execution renderers
        ClientRegistry.registerTileEntity(TileEntityExecution.class, "GSExecution", new TileEntityExecutionRenderer());
        ClientRegistry.registerTileEntity(TileEntityExecution.Gibbet.class, "GSExecutionGibbet", new TileEntityExecutionRenderer.Gibbet());
        ClientRegistry.registerTileEntity(TileEntityExecution.Stocks.class, "GSExecutionStocks", new TileEntityExecutionRenderer.Stocks());
        ClientRegistry.registerTileEntity(TileEntityExecution.BurningStake.class, "GSExecutionBurningStake", new TileEntityExecutionRenderer.BurningStake());

        // spawner renderer
        ClientRegistry.registerTileEntity(TileEntitySpawner.class, "GSSpawner", new TileEntitySpawnerRenderer());
        ClientRegistry.registerTileEntity(TileEntitySpawner.Skeleton.class, "GSSpawnerSkeleton", new TileEntitySpawnerRenderer.Skeleton());
        ClientRegistry.registerTileEntity(TileEntitySpawner.Zombie.class, "GSSpawnerZombie", new TileEntitySpawnerRenderer.Zombie());
        ClientRegistry.registerTileEntity(TileEntitySpawner.Spider.class, "GSSpawnerSpider", new TileEntitySpawnerRenderer.Spider());

        // register HauntedChest renderer
        ClientRegistry.registerTileEntity(TileEntityHauntedChest.class, "GSHauntedChest", new TileEntityHauntedChestRenderer());

        // register SkullCandle renderer
        ClientRegistry.registerTileEntity(TileEntitySkullCandle.class, "GSSkullCandle", new TileEntitySkullCandleRenderer());
        ClientRegistry.registerTileEntity(TileEntitySkullCandle.Zombie.class, "GSSkullCandleZombie", new TileEntitySkullCandleRenderer.Zombie());
        ClientRegistry.registerTileEntity(TileEntitySkullCandle.Wither.class, "GSSkullCandleWither", new TileEntitySkullCandleRenderer.Wither());

        // candle
        ClientRegistry.registerTileEntity(TileEntityCandle.class, "GSCandle", new TileEntityCandleRenderer());

        // pile of bones
        ClientRegistry.registerTileEntity(TileEntityPileOfBones.class, "GSPileOfBones", new TileEntityPileOfBonesRenderer());
        ClientRegistry.registerTileEntity(TileEntityPileOfBones.Skull.class, "GSPileOfBonesSkull", new TileEntityPileOfBonesRenderer.Skull());
        ClientRegistry.registerTileEntity(TileEntityPileOfBones.Crawler.class, "GSPileOfBonesCrawler", new TileEntityPileOfBonesRenderer.Crawler());

        // corpses
        ClientRegistry.registerTileEntity(TileEntityCorpse.class, "GSCorpse", new TileEntityCorpseRenderer());
        ClientRegistry.registerTileEntity(TileEntityCorpse.Steve.class, "GSCorpseSteve", new TileEntityCorpseRenderer.Steve());
        ClientRegistry.registerTileEntity(TileEntityCorpse.Villager.class, "GSCorpseVillager", new TileEntityCorpseRenderer.Villager());
        ClientRegistry.registerTileEntity(TileEntityCorpse.Dog.class, "GSCorpseDog", new TileEntityCorpseRenderer.Dog());
        ClientRegistry.registerTileEntity(TileEntityCorpse.Cat.class, "GSCorpseCat", new TileEntityCorpseRenderer.Cat());
        ClientRegistry.registerTileEntity(TileEntityCorpse.Horse.class, "GSCorpseHorse", new TileEntityCorpseRenderer.Horse());
        ClientRegistry.registerTileEntity(TileEntityCorpse.Zombie.class, "GSCorpseZombie", new TileEntityCorpseRenderer.Zombie());
        ClientRegistry.registerTileEntity(TileEntityCorpse.ZombieVillager.class, "GSCorpseZombieVillager", new TileEntityCorpseRenderer.ZombieVillager());
        ClientRegistry.registerTileEntity(TileEntityCorpse.Skeleton.class, "GSCorpseSkeleton", new TileEntityCorpseRenderer.Skeleton());
        ClientRegistry.registerTileEntity(TileEntityCorpse.Witch.class, "GSCorpseWitch", new TileEntityCorpseRenderer.Witch());

        ClientRegistry.registerTileEntity(TileEntityAltar.class, "GSAltar", new TileEntityRenderAltar());
    }

    public void registerMobsRenderers() {
        // zombie dog
        RenderingRegistry.registerEntityRenderingHandler(EntityZombieDog.class, new RenderUndeadDog(Minecraft.getMinecraft().getRenderManager(), new ModelUndeadDog(), new ModelUndeadDog()));

        // zombie cat
        RenderingRegistry.registerEntityRenderingHandler(EntityZombieCat.class, new RenderUndeadCat(Minecraft.getMinecraft().getRenderManager(), new ModelUndeadCat(), 0));

        // skeleton dog
        RenderingRegistry.registerEntityRenderingHandler(EntitySkeletonDog.class, new RenderUndeadDog(Minecraft.getMinecraft().getRenderManager(), new ModelUndeadDog(), new ModelUndeadDog()));

        // zombie cat
        RenderingRegistry.registerEntityRenderingHandler(EntitySkeletonCat.class, new RenderUndeadCat(Minecraft.getMinecraft().getRenderManager(), new ModelUndeadCat(), 0));

        // skull crawler
        RenderingRegistry.registerEntityRenderingHandler(EntitySkullCrawler.class, new RenderSkullCrawler(SkullCrawlerType.skeleton, Minecraft.getMinecraft().getRenderManager()));
        RenderingRegistry.registerEntityRenderingHandler(EntityWitherSkullCrawler.class, new RenderSkullCrawler(SkullCrawlerType.wither, Minecraft.getMinecraft().getRenderManager()));
        RenderingRegistry.registerEntityRenderingHandler(EntityZombieSkullCrawler.class, new RenderSkullCrawler(SkullCrawlerType.zombie, Minecraft.getMinecraft().getRenderManager()));

        // Skeleton
        RenderingRegistry.registerEntityRenderingHandler(EntityGSSkeleton.class, new RenderGSSkeleton(Minecraft.getMinecraft().getRenderManager()));
        // Horses
        RenderingRegistry.registerEntityRenderingHandler(EntityUndeadHorse.class, new RenderUndeadHorse(Minecraft.getMinecraft().getRenderManager()));

        // raven
        RenderingRegistry.registerEntityRenderingHandler(EntityRaven.class, new RenderRaven(Minecraft.getMinecraft().getRenderManager()));

        // Damned Warrior
        RenderingRegistry.registerEntityRenderingHandler(EntityDamnedWarrior.class, new RenderDamnedWarrior(Minecraft.getMinecraft().getRenderManager(), new ModelDamnedWarrior()));

        // Spawner Helper
        RenderingRegistry.registerEntityRenderingHandler(EntityGroupOfGravesMobSpawnerHelper.class, new RenderSpawnerHelper(Minecraft.getMinecraft().getRenderManager()));
    }

    @Override
    public String getLocalizedString(String str) {
//        String localizedString = null;
//        try {
//            localizedString = LanguageRegistry.instance().getStringLocalization(str);
//        } catch (Exception e) {
//        }
//        if (StringUtils.isBlank(localizedString)) {
//            return LanguageRegistry.instance().getStringLocalization(str, "en_US");
//        } else {
//            return localizedString;
//        }
        return I18n.translateToLocal(str);
    }

    @Override
    public String getLocalizedEntityName(String name) {
        return I18n.translateToLocal(name);
    }

    @Override
    public void openGraveTextGui(TileEntityGrave tileEntity) {
        FMLClientHandler.instance().getClient().displayGuiScreen(new GSGraveTextGui(tileEntity));
    }

    @Override
    public void registerHandlers() {
        MinecraftForge.EVENT_BUS.register(new RenderEventHandler());
    }

    @Override
    public void registerBlocksModels() {
        //memorials
        ProxyHelper.registerModelsForTEBlocks(EnumMemorials.WOODEN_CROSS.ordinal(), EnumMemorials.ICE_CROSS.ordinal(), GSBlock.MEMORIAL, ResourcesModels.memorialModel, TileEntityMemorial.class);
        ProxyHelper.registerModelsForTEBlocks(EnumMemorials.WOODEN_OBELISK.ordinal(), EnumMemorials.ICE_OBELISK.ordinal(), GSBlock.MEMORIAL, ResourcesModels.memorialModel, TileEntityMemorial.Obelisk.class);
        ProxyHelper.registerModelsForTEBlocks(EnumMemorials.WOODEN_CELTIC_CROSS.ordinal(), EnumMemorials.ICE_CELTIC_CROSS.ordinal(), GSBlock.MEMORIAL, ResourcesModels.memorialModel, TileEntityMemorial.CelticCross.class);
        ProxyHelper.registerModelsForTEBlocks(EnumMemorials.WOODEN_STEVE_STATUE.ordinal(), EnumMemorials.ICE_STEVE_STATUE.ordinal(), GSBlock.MEMORIAL, ResourcesModels.memorialModel, TileEntityMemorial.SteveStatue.class);
        ProxyHelper.registerModelsForTEBlocks(EnumMemorials.WOODEN_VILLAGER_STATUE.ordinal(), EnumMemorials.ICE_VILLAGER_STATUE.ordinal(), GSBlock.MEMORIAL, ResourcesModels.memorialModel, TileEntityMemorial.VillagerStatue.class);
        ProxyHelper.registerModelsForTEBlocks(EnumMemorials.WOODEN_ANGEL_STATUE.ordinal(), EnumMemorials.ICE_ANGEL_STATUE.ordinal(), GSBlock.MEMORIAL, ResourcesModels.memorialModel, TileEntityMemorial.AngelStatue.class);
        ProxyHelper.registerModelsForTEBlocks(EnumMemorials.WOODEN_DOG_STATUE.ordinal(), EnumMemorials.ICE_DOG_STATUE.ordinal(), GSBlock.MEMORIAL, ResourcesModels.memorialModel, TileEntityMemorial.DogStatue.class);
        ProxyHelper.registerModelsForTEBlocks(EnumMemorials.WOODEN_CAT_STATUE.ordinal(), EnumMemorials.ICE_CAT_STATUE.ordinal(), GSBlock.MEMORIAL, ResourcesModels.memorialModel, TileEntityMemorial.CatStatue.class);
        ProxyHelper.registerModelsForTEBlocks(EnumMemorials.WOODEN_CREEPER_STATUE.ordinal(), EnumMemorials.ICE_CREEPER_STATUE.ordinal(), GSBlock.MEMORIAL, ResourcesModels.memorialModel, TileEntityMemorial.CreeperStatue.class);
        //executions
        ProxyHelper.registerModelsForTEBlocks(EnumExecution.GALLOWS.ordinal(), GSBlock.EXECUTION, ResourcesModels.executionModel, TileEntityExecution.class);
        ProxyHelper.registerModelsForTEBlocks(EnumExecution.GIBBET.ordinal(), GSBlock.EXECUTION, ResourcesModels.executionModel, TileEntityExecution.Gibbet.class);
        ProxyHelper.registerModelsForTEBlocks(EnumExecution.STOCKS.ordinal(), GSBlock.EXECUTION, ResourcesModels.executionModel, TileEntityExecution.Stocks.class);
        ProxyHelper.registerModelsForTEBlocks(EnumExecution.BURNING_STAKE.ordinal(), GSBlock.EXECUTION, ResourcesModels.executionModel, TileEntityExecution.BurningStake.class);
        //spawners
        ProxyHelper.registerModelsForTEBlocks(EnumSpawner.WITHER_SPAWNER.ordinal(), GSBlock.SPAWNER, ResourcesModels.spawnerModel, TileEntitySpawner.class);
        ProxyHelper.registerModelsForTEBlocks(EnumSpawner.SKELETON_SPAWNER.ordinal(), GSBlock.SPAWNER, ResourcesModels.spawnerModel, TileEntitySpawner.Skeleton.class);
        ProxyHelper.registerModelsForTEBlocks(EnumSpawner.ZOMBIE_SPAWNER.ordinal(), GSBlock.SPAWNER, ResourcesModels.spawnerModel, TileEntitySpawner.Zombie.class);
        ProxyHelper.registerModelsForTEBlocks(EnumSpawner.SPIDER_SPAWNER.ordinal(), GSBlock.SPAWNER, ResourcesModels.spawnerModel, TileEntitySpawner.Spider.class);
        //traps
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(GSBlock.TRAP), EnumTrap.NIGHT_STONE.ordinal(), ResourcesModels.nightStoneModel);
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(GSBlock.TRAP), EnumTrap.THUNDER_STONE.ordinal(), ResourcesModels.thunderStoneModel);
        ModelBakery.registerItemVariants(Item.getItemFromBlock(GSBlock.TRAP), ResourcesModels.nightStoneModel, ResourcesModels.thunderStoneModel);
        //piles of bones
        ProxyHelper.registerModelsForTEBlocks(EnumPileOfBones.PILE_OF_BONES.ordinal(), GSBlock.PILE_OF_BONES, ResourcesModels.pileOfBonesModel, TileEntityPileOfBones.class);
        ProxyHelper.registerModelsForTEBlocks(EnumPileOfBones.PILE_OF_BONES_WITH_SKULL.ordinal(), GSBlock.PILE_OF_BONES, ResourcesModels.pileOfBonesModel, TileEntityPileOfBones.Skull.class);
        ProxyHelper.registerModelsForTEBlocks(EnumPileOfBones.PILE_OF_BONES_WITH_SKULL_CRAWLER.ordinal(), GSBlock.PILE_OF_BONES, ResourcesModels.pileOfBonesModel, TileEntityPileOfBones.Crawler.class);
        //bone blocks
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(GSBlock.BONE_BLOCK), EnumBoneBlock.BONE_BLOCK.ordinal(), ResourcesModels.boneBlockModel);
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(GSBlock.BONE_BLOCK), EnumBoneBlock.SKULL_BONE_BLOCK.ordinal(), ResourcesModels.boneBlockWithSkullModel);
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(GSBlock.BONE_BLOCK), EnumBoneBlock.CRAWLER_BONE_BLOCK.ordinal(), ResourcesModels.boneBlockModel);
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(GSBlock.BONE_BLOCK), EnumBoneBlock.CRAWLER_SKULL_BONE_BLOCK.ordinal(), ResourcesModels.boneBlockWithSkullModel);
        ModelBakery.registerItemVariants(Item.getItemFromBlock(GSBlock.BONE_BLOCK), ResourcesModels.boneBlockModel, ResourcesModels.boneBlockWithSkullModel,
                ResourcesModels.boneBlockModel, ResourcesModels.boneBlockWithSkullModel);

        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(GSBlock.BONE_SLAB), 0, ResourcesModels.boneSlabModel);
        ModelBakery.registerItemVariants(Item.getItemFromBlock(GSBlock.BONE_SLAB), ResourcesModels.boneSlabModel);
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(GSBlock.BONE_STAIRS), 0, ResourcesModels.boneStairsModel);
        ModelBakery.registerItemVariants(Item.getItemFromBlock(GSBlock.BONE_STAIRS), ResourcesModels.boneStairsModel);
        //haunted chest
        ProxyHelper.registerModelsForTEBlocks(0, EnumHauntedChest.values().length - 1, GSBlock.HAUNTED_CHEST, ResourcesModels.hauntedChestModel, TileEntityHauntedChest.class);
        //altar
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(GSBlock.ALTAR), 0, ResourcesModels.altarModel);
        ModelBakery.registerItemVariants(Item.getItemFromBlock(GSBlock.ALTAR), ResourcesModels.altarModel);

        //candle
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(GSBlock.CANDLE), 0, ResourcesModels.candleModel);
        ForgeHooksClient.registerTESRItemStack(Item.getItemFromBlock(GSBlock.CANDLE), 0, TileEntityCandle.class);
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(GSBlock.CANDLE), 0, ResourcesModels.candleModel);
        //skull candle
        ProxyHelper.registerModelsForTEBlocks(EnumSkullCandle.SKELETON_SKULL.ordinal(), GSBlock.SKULL_CANDLE, ResourcesModels.skullCandleModel, TileEntitySkullCandle.class);
        ProxyHelper.registerModelsForTEBlocks(EnumSkullCandle.WITHER_SKULL.ordinal(), GSBlock.SKULL_CANDLE, ResourcesModels.skullCandleModel, TileEntitySkullCandle.Wither.class);
        ProxyHelper.registerModelsForTEBlocks(EnumSkullCandle.ZOMBIE_SKULL.ordinal(), GSBlock.SKULL_CANDLE, ResourcesModels.skullCandleModel, TileEntitySkullCandle.Zombie.class);

        //corpses
        ProxyHelper.registerModelsForTEBlocks(EnumCorpse.STEVE.ordinal(), GSBlock.CORPSE, ResourcesModels.CORPSE, TileEntityCorpse.Steve.class);
        ProxyHelper.registerModelsForTEBlocks(EnumCorpse.VILLAGER.ordinal(), GSBlock.CORPSE, ResourcesModels.CORPSE, TileEntityCorpse.Villager.class);
        ProxyHelper.registerModelsForTEBlocks(EnumCorpse.DOG.ordinal(), GSBlock.CORPSE, ResourcesModels.CORPSE, TileEntityCorpse.Dog.class);
        ProxyHelper.registerModelsForTEBlocks(EnumCorpse.CAT.ordinal(), GSBlock.CORPSE, ResourcesModels.CORPSE, TileEntityCorpse.Cat.class);
        ProxyHelper.registerModelsForTEBlocks(EnumCorpse.HORSE.ordinal(), GSBlock.CORPSE, ResourcesModels.CORPSE, TileEntityCorpse.Horse.class);
        ProxyHelper.registerModelsForTEBlocks(EnumCorpse.ZOMBIE.ordinal(), GSBlock.CORPSE, ResourcesModels.CORPSE, TileEntityCorpse.Zombie.class);
        ProxyHelper.registerModelsForTEBlocks(EnumCorpse.ZOMBIE_VILLAGER.ordinal(), GSBlock.CORPSE, ResourcesModels.CORPSE, TileEntityCorpse.ZombieVillager.class);
        ProxyHelper.registerModelsForTEBlocks(EnumCorpse.SKELETON.ordinal(), GSBlock.CORPSE, ResourcesModels.CORPSE, TileEntityCorpse.Skeleton.class);
        ProxyHelper.registerModelsForTEBlocks(EnumCorpse.WITCH.ordinal(), GSBlock.CORPSE, ResourcesModels.CORPSE, TileEntityCorpse.Witch.class);
    }

    @Override
    public void registerItemsModels() {
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(GSItem.CHISEL, 0, ResourcesModels.chiselModel);
        ModelBakery.registerItemVariants(GSItem.CHISEL, ResourcesModels.chiselModel);

        for (ItemGSMonsterPlacer.EnumEggs egg : ItemGSMonsterPlacer.EnumEggs.values()) {
            Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(GSItem.SPAWN_EGG, egg.ordinal(), ResourcesModels.spawnEggModel);
        }
        ModelBakery.registerItemVariants(GSItem.SPAWN_EGG, ResourcesModels.spawnEggModel);
        FMLClientHandler.instance().getClient().getItemColors().registerItemColorHandler((stack, tintIndex) -> {
            int itemDamage = stack.getItemDamage();
            if (itemDamage >= 0 && itemDamage < ItemGSMonsterPlacer.EnumEggs.values().length) {
                if ((tintIndex & 1) == 0) {
                    return ItemGSMonsterPlacer.EnumEggs.getById(itemDamage).getBackgroundColor();
                } else {
                    return ItemGSMonsterPlacer.EnumEggs.getById(itemDamage).getForegroundColor();
                }
            }
            return 0xFFFFFF;
        }, GSItem.SPAWN_EGG);

    }
}
