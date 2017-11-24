package nightkosh.gravestone_extended.core.proxy;

import net.minecraft.client.Minecraft;
import net.minecraft.util.text.translation.I18n;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import nightkosh.gravestone.tileentity.TileEntityGrave;
import nightkosh.gravestone_extended.core.GSItem;
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

    @Override
    public void registerEggs() {
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
}
