package nightkosh.gravestone_extended.core.proxy;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelHorse;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.util.StatCollector;
import net.minecraftforge.client.ForgeHooksClient;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.registry.LanguageRegistry;
import net.minecraftforge.fml.common.registry.VillagerRegistry;
import nightkosh.gravestone.tileentity.TileEntityGrave;
import nightkosh.gravestone_extended.block.enums.EnumHauntedChest;
import nightkosh.gravestone_extended.block.enums.EnumPileOfBones;
import nightkosh.gravestone_extended.block.enums.EnumSkullCandle;
import nightkosh.gravestone_extended.block.enums.EnumSpawner;
import nightkosh.gravestone_extended.core.Block;
import nightkosh.gravestone_extended.core.ExtendedItem;
import nightkosh.gravestone_extended.core.Resources;
import nightkosh.gravestone_extended.core.ResourcesModels;
import nightkosh.gravestone_extended.core.event.GSRenderEventHandler;
import nightkosh.gravestone_extended.entity.EntityRaven;
import nightkosh.gravestone_extended.entity.helper.EntityGroupOfGravesMobSpawnerHelper;
import nightkosh.gravestone_extended.entity.monster.*;
import nightkosh.gravestone_extended.entity.monster.EntitySkullCrawler.SkullCrawlerType;
import nightkosh.gravestone_extended.gui.GSGraveTextGui;
import nightkosh.gravestone_extended.item.ItemGSMonsterPlacer;
import nightkosh.gravestone_extended.item.enums.EnumCorpse;
import nightkosh.gravestone_extended.models.entity.ModelUndeadCat;
import nightkosh.gravestone_extended.models.entity.ModelUndeadDog;
import nightkosh.gravestone_extended.renderer.entity.*;
import nightkosh.gravestone_extended.renderer.tileentity.*;
import nightkosh.gravestone_extended.structures.village.undertaker.VillageHandlerGSUndertaker;
import nightkosh.gravestone_extended.tileentity.*;
import org.apache.commons.lang3.StringUtils;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class ClientProxy extends CommonProxy {

    @Override
    public void registerRenderers() {
        // blocks renderers
        registerBlocksRenderers();

        // Mobs renderers
        registerMobsRenderers();
    }

    //TODO ???
    private void registerBlocksRenderers() {
        // register GraveStone renderer
        ClientRegistry.registerTileEntity(TileEntityGSMemorial.class, "GSMemorial", new TileEntityMemorialRenderer());
//        MinecraftForgeClient.registerItemRenderer(net.minecraft.item.Item.getItemFromBlock(Block.memorial), new ItemGSMemorialRenderer());

        // spawner renderer
        ClientRegistry.registerTileEntity(TileEntityGSSpawner.class, "GSSpawner", new TileEntitySpawnerRenderer());
//        MinecraftForgeClient.registerItemRenderer(net.minecraft.item.Item.getItemFromBlock(Block.spawner), new ItemGSSpawnerRenderer());

        // register HauntedChest renderer
        ClientRegistry.registerTileEntity(TileEntityGSHauntedChest.class, "GSHauntedChest", new TileEntityHauntedChestRenderer());

        // register SkullCandle renderer
        ClientRegistry.registerTileEntity(TileEntityGSSkullCandle.class, "GSSkullCandle", new TileEntitySkullCandleRenderer());
//        MinecraftForgeClient.registerItemRenderer(net.minecraft.item.Item.getItemFromBlock(Block.skullCandle), new ItemGSSkullCandleRenderer());

        // candle
        ClientRegistry.registerTileEntity(TileEntityGSCandle.class, "GSCandle", new TileEntityCandleRenderer());
//        MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(GSBlock.candle), new ItemGSCandleRenderer());

        // pile of bones
        ClientRegistry.registerTileEntity(TileEntityGSPileOfBones.class, "GSPileOfBones", new TileEntityPileOfBonesRenderer());
//        MinecraftForgeClient.registerItemRenderer(net.minecraft.item.Item.getItemFromBlock(Block.pileOfBones), new ItemGSPileOfBonesRenderer());

        // corpses
//        MinecraftForgeClient.registerItemRenderer(Item.corpse, new ItemGSCorpseRenderer());

        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityGSAltar.class, new TileEntityRenderAltar());


    }

    private void registerMobsRenderers() {
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
        RenderingRegistry.registerEntityRenderingHandler(EntityUndeadHorse.class, new RenderUndeadHorse(Minecraft.getMinecraft().getRenderManager(), new ModelHorse(), 0));

        // raven
        RenderingRegistry.registerEntityRenderingHandler(EntityRaven.class, new RenderRaven(Minecraft.getMinecraft().getRenderManager()));

        // Spawner Helper
        RenderingRegistry.registerEntityRenderingHandler(EntityGroupOfGravesMobSpawnerHelper.class, new RenderSpawnerHelper(Minecraft.getMinecraft().getRenderManager()));
    }

    @Override
    public void registerVillagers() {
        VillagerRegistry.instance().registerVillagerSkin(VillageHandlerGSUndertaker.UNDERTAKER_ID, Resources.UNDERTAKER);
    }

    @Override
    public String getLocalizedString(String str) {
        String localizedString = null;
        try {
            localizedString = LanguageRegistry.instance().getStringLocalization(str);
        } catch (Exception e) {
        }
        if (StringUtils.isBlank(localizedString)) {
            return LanguageRegistry.instance().getStringLocalization(str, "en_US");
        } else {
            return localizedString;
        }
    }

    @Override
    public String getLocalizedEntityName(String name) {
        return StatCollector.translateToLocal(name);
    }

    @Override
    public void openGraveTextGui(TileEntityGrave tileEntity) {
        FMLClientHandler.instance().getClient().displayGuiScreen(new GSGraveTextGui(tileEntity));
    }

    @Override
    public void registerHandlers() {
        MinecraftForge.EVENT_BUS.register(new GSRenderEventHandler());
    }

    @Override
    public void registerBlocksModels() {
        //memorials
        registerModelsForTEBlocks(1, Block.memorial, ResourcesModels.memorialModel, TileEntityGSMemorial.class);
        //spawners
        registerModelsForTEBlocks(EnumSpawner.values().length, Block.spawner, ResourcesModels.spawnerModel, TileEntityGSSpawner.class);
        //traps
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(net.minecraft.item.Item.getItemFromBlock(Block.trap), 0, ResourcesModels.nightStoneModel);
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(net.minecraft.item.Item.getItemFromBlock(Block.trap), 1, ResourcesModels.thunderStoneModel);
        ModelBakery.addVariantName(net.minecraft.item.Item.getItemFromBlock(Block.trap), "nightkosh.gravestone:GSTrap_night_stone", "nightkosh.gravestone:GSTrap_thunder_stone");
        //piles of bones
        registerModelsForTEBlocks(EnumPileOfBones.values().length, Block.pileOfBones, ResourcesModels.pileOfBonesModel, TileEntityGSPileOfBones.class);
        //bone blocks
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(net.minecraft.item.Item.getItemFromBlock(Block.boneBlock), 0, ResourcesModels.boneBlockModel);
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(net.minecraft.item.Item.getItemFromBlock(Block.boneBlock), 1, ResourcesModels.boneBlockWithSkullModel);
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(net.minecraft.item.Item.getItemFromBlock(Block.boneBlock), 2, ResourcesModels.boneBlockModel);
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(net.minecraft.item.Item.getItemFromBlock(Block.boneBlock), 3, ResourcesModels.boneBlockWithSkullModel);
        ModelBakery.addVariantName(net.minecraft.item.Item.getItemFromBlock(Block.boneBlock), "nightkosh.gravestone:GSBoneBlock", "nightkosh.gravestone:GSBoneBlock_with_skull",
                "nightkosh.gravestone:GSBoneBlock", "nightkosh.gravestone:GSBoneBlock_with_skull");

        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(net.minecraft.item.Item.getItemFromBlock(Block.boneSlab), 0, ResourcesModels.boneSlabModel);

        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(net.minecraft.item.Item.getItemFromBlock(Block.boneStairs), 0, ResourcesModels.boneStairsModel);
        //haunted chest
        registerModelsForTEBlocks(EnumHauntedChest.values().length, Block.hauntedChest, ResourcesModels.hauntedChestModel, TileEntityGSHauntedChest.class);
        //altar
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(net.minecraft.item.Item.getItemFromBlock(Block.altar), 0, ResourcesModels.altarModel);

        //candle
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(net.minecraft.item.Item.getItemFromBlock(Block.candle), 0, ResourcesModels.candleModel);
        ForgeHooksClient.registerTESRItemStack(net.minecraft.item.Item.getItemFromBlock(Block.candle), 0, TileEntityGSCandle.class);
        ModelLoader.setCustomModelResourceLocation(net.minecraft.item.Item.getItemFromBlock(Block.candle), 0, ResourcesModels.candleModel);
        //skull candle
        registerModelsForTEBlocks(EnumSkullCandle.values().length, Block.skullCandle, ResourcesModels.skullCandleModel, TileEntityGSSkullCandle.class);
    }

    private void registerModelsForTEBlocks(int length, net.minecraft.block.Block block, ModelResourceLocation model, Class TEClass) {
        for (int num = 0; num < length; num++) {
            Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(net.minecraft.item.Item.getItemFromBlock(block), num, model);
            ForgeHooksClient.registerTESRItemStack(net.minecraft.item.Item.getItemFromBlock(block), num, TEClass);
            ModelLoader.setCustomModelResourceLocation(net.minecraft.item.Item.getItemFromBlock(block), num, model);
        }
    }

    @Override
    public void registerItemsModels() {
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(ExtendedItem.chisel, 0, ResourcesModels.chiselModel);

        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(ExtendedItem.corpse, EnumCorpse.VILLAGER.ordinal(), ResourcesModels.CORPSE_VILLAGER);
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(ExtendedItem.corpse, EnumCorpse.DOG.ordinal(), ResourcesModels.CORPSE_DOG);
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(ExtendedItem.corpse, EnumCorpse.CAT.ordinal(), ResourcesModels.CORPSE_CAT);
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(ExtendedItem.corpse, EnumCorpse.HORSE.ordinal(), ResourcesModels.CORPSE_HORSE);
        ModelBakery.addVariantName(ExtendedItem.corpse, "nightkosh.gravestone:GSCorpseVillager", "nightkosh.gravestone:GSCorpseDog", "nightkosh.gravestone:GSCorpseCat", "nightkosh.gravestone:GSCorpseHorse");

        for (ItemGSMonsterPlacer.EnumEggs egg : ItemGSMonsterPlacer.EnumEggs.values()) {
            Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(ExtendedItem.spawnEgg, egg.ordinal(), ResourcesModels.spawnEggModel);
        }
    }
}
