package nightkosh.gravestone_extended.core;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import nightkosh.gravestone_extended.config.ExtendedConfig;
import nightkosh.gravestone_extended.entity.EntityRaven;
import nightkosh.gravestone_extended.entity.helper.EntityGroupOfGravesMobSpawnerHelper;
import nightkosh.gravestone_extended.entity.monster.EntityDamnedWarrior;
import nightkosh.gravestone_extended.entity.monster.EntityGSSkeleton;
import nightkosh.gravestone_extended.entity.monster.EntitySkeletonRaider;
import nightkosh.gravestone_extended.entity.monster.EntityZombieRaider;
import nightkosh.gravestone_extended.entity.monster.crawler.EntitySkullCrawler;
import nightkosh.gravestone_extended.entity.monster.crawler.EntityWitherSkullCrawler;
import nightkosh.gravestone_extended.entity.monster.crawler.EntityZombieSkullCrawler;
import nightkosh.gravestone_extended.entity.monster.horse.EntitySkeletonHorse;
import nightkosh.gravestone_extended.entity.monster.horse.EntityZombieHorse;
import nightkosh.gravestone_extended.entity.monster.pet.EntitySkeletonCat;
import nightkosh.gravestone_extended.entity.monster.pet.EntitySkeletonDog;
import nightkosh.gravestone_extended.entity.monster.pet.EntityZombieCat;
import nightkosh.gravestone_extended.entity.monster.pet.EntityZombieDog;

import java.util.Set;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class Entity {

    private static int mobId = 0;

    private Entity() {
    }

    public static final String SKELETON_NAME = "GSSkeleton";
    public static final String ZOMBIE_DOG_NAME = "GSZombieDog";
    public static final String ZOMBIE_CAT_NAME = "GSZombieCat";
    public static final String SKELETON_DOG_NAME = "GSSkeletonDog";
    public static final String SKELETON_CAT_NAME = "GSSkeletonCat";
    public static final String SKULL_CRAWLER_NAME = "GSSkullCrawler";
    public static final String WITHER_SKULL_CRAWLER_NAME = "GSWitherSkullCrawler";
    public static final String ZOMBIE_SKULL_CRAWLER_NAME = "GSZombieSkullCrawler";
    public static final String ZOMBIE_HORSE_NAME = "GSZombieHorse";
    public static final String SKELETON_HORSE_NAME = "GSSkeletonHorse";
    public static final String SKELETON_RAIDER_NAME = "GSSkeletonRaider";
    public static final String ZOMBIE_RAIDER_NAME = "GSZombieRaider";
    public static final String RAVEN_NAME = "GSRaven";
    public static final String DAMNED_WARRIOR_NAME = "GSDamnedWarrior";
    public static final String SPAWNER_HELPER_NAME = "GSSpawnerHelper";

    // EntityList
    public static final ResourceLocation MINECRAFT_ZOMBIE_ID = new ResourceLocation("zombie");
    public static final ResourceLocation MINECRAFT_HUSK_ID = new ResourceLocation("husk");
    public static final ResourceLocation MINECRAFT_PIGZOMBIE_ID = new ResourceLocation("zombie_pigman");
    public static final ResourceLocation MINECRAFT_SKELETON_ID = new ResourceLocation("skeleton");
    public static final ResourceLocation MINECRAFT_WITHER_SKELETON_ID = new ResourceLocation("wither_skeleton");
    public static final ResourceLocation MINECRAFT_STRAY_ID = new ResourceLocation("stray");
    public static final ResourceLocation MINECRAFT_SPIDER_ID = new ResourceLocation("spider");
    public static final ResourceLocation MINECRAFT_CAVE_SPIDER_ID = new ResourceLocation("cave_spider");
    public static final ResourceLocation MINECRAFT_ENDERMAN_ID = new ResourceLocation("enderman");
    public static final ResourceLocation MINECRAFT_CREEPER_ID = new ResourceLocation("creeper");
    public static final ResourceLocation MINECRAFT_WITHER_ID = new ResourceLocation("wither");

    public static final ResourceLocation SKELETON_ID =  new ResourceLocation(ModInfo.ID + ":" + SKELETON_NAME);
    public static final ResourceLocation ZOMBIE_DOG_ID = new ResourceLocation(ModInfo.ID + ":" + ZOMBIE_DOG_NAME);
    public static final ResourceLocation ZOMBIE_CAT_ID = new ResourceLocation(ModInfo.ID + ":" + ZOMBIE_CAT_NAME);
    public static final ResourceLocation SKELETON_DOG_ID = new ResourceLocation(ModInfo.ID + ":" + SKELETON_DOG_NAME);
    public static final ResourceLocation SKELETON_CAT_ID = new ResourceLocation(ModInfo.ID + ":" + SKELETON_CAT_NAME);
    public static final ResourceLocation SKULL_CRAWLER_ID = new ResourceLocation(ModInfo.ID + ":" + SKULL_CRAWLER_NAME);
    public static final ResourceLocation WITHER_SKULL_CRAWLER_ID = new ResourceLocation(ModInfo.ID + ":" + WITHER_SKULL_CRAWLER_NAME);
    public static final ResourceLocation ZOMBIE_SKULL_CRAWLER_ID = new ResourceLocation(ModInfo.ID + ":" + ZOMBIE_SKULL_CRAWLER_NAME);
    public static final ResourceLocation ZOMBIE_HORSE_ID = new ResourceLocation(ModInfo.ID + ":" + ZOMBIE_HORSE_NAME);
    public static final ResourceLocation SKELETON_HORSE_ID = new ResourceLocation(ModInfo.ID + ":" + SKELETON_HORSE_NAME);
    public static final ResourceLocation SKELETON_RAIDER_ID = new ResourceLocation(ModInfo.ID + ":" + SKELETON_RAIDER_NAME);
    public static final ResourceLocation ZOMBIE_RAIDER_ID = new ResourceLocation(ModInfo.ID + ":" + ZOMBIE_RAIDER_NAME);
    public static final ResourceLocation DAMNED_WARRIOR_ID = new ResourceLocation(ModInfo.ID + ":" + DAMNED_WARRIOR_NAME);
    public static final ResourceLocation RAVEN_ID = new ResourceLocation(ModInfo.ID + ":" + RAVEN_NAME);

    public static final String MINECRAFT_SKELETON_NAME = "Skeleton";
    public static final String MINECRAFT_ZOMBIE_HORSE_NAME = "zombiehorse";
    public static final String MINECRAFT_SKELETON_HORSE_NAME = "skeletonhorse";

    public static void registration() {
        // zombie dog
        registerModEntity(Resources.ZOMBIE_DOG, EntityZombieDog.class, ZOMBIE_DOG_NAME);
        if (ExtendedConfig.spawnZombieDogs) {
            addSpawn(BiomeDictionary.Type.FOREST, EntityZombieDog.class, 2, 1, 1);
        }

        // zombie cat
        registerModEntity(Resources.ZOMBIE_OZELOT, EntityZombieCat.class, ZOMBIE_CAT_NAME);
        if (ExtendedConfig.spawnZombieCats) {
            addSpawn(BiomeDictionary.Type.JUNGLE, EntityZombieCat.class, 2, 1, 1);
        }

        // skeleton dog
        registerModEntity(Resources.SKELETON_DOG, EntitySkeletonDog.class, SKELETON_DOG_NAME);
        if (ExtendedConfig.spawnSkeletonDogs) {
            addSpawn(BiomeDictionary.Type.FOREST, EntityZombieDog.class, 2, 1, 1);
        }

        // skeleton cat
        registerModEntity(Resources.SKELETON_CAT, EntitySkeletonCat.class, SKELETON_CAT_NAME);
        if (ExtendedConfig.spawnSkeletonCats) {
            addSpawn(BiomeDictionary.Type.JUNGLE, EntityZombieCat.class, 2, 1, 1);
        }

        // skull crawlers
        registerModEntity(Resources.SKELETON_SKULL_CRAWLER, EntitySkullCrawler.class, SKULL_CRAWLER_NAME);
        // wither
        registerModEntity(Resources.WITHER_SKULL_CRAWLER, EntityWitherSkullCrawler.class, WITHER_SKULL_CRAWLER_NAME);
        addSpawn(BiomeDictionary.Type.NETHER, EntityWitherSkullCrawler.class, 3, 1, 4);
        // zombie
        registerModEntity(Resources.ZOMBIE_SKULL_CRAWLER, EntityZombieSkullCrawler.class, ZOMBIE_SKULL_CRAWLER_NAME);

        registerModEntity(Resources.SKELETON, EntityGSSkeleton.class, SKELETON_NAME);
        EntityRegistry.addSpawn(EntityGSSkeleton.class, 1, 1, 3, EnumCreatureType.MONSTER);

        registerModEntity(Resources.ZOMBIE_HORSE, EntityZombieHorse.class, ZOMBIE_HORSE_NAME);
        registerModEntity(Resources.SKELETON_HORSE, EntitySkeletonHorse.class, SKELETON_HORSE_NAME);
        if (ExtendedConfig.spawnUndeadHorses) {
            addSpawn(BiomeDictionary.Type.PLAINS, EntityZombieHorse.class, 2, 1, 3);
            addSpawn(BiomeDictionary.Type.PLAINS, EntitySkeletonHorse.class, 2, 1, 3);
        }

        registerModEntity(Resources.ZOMBIE, EntityZombieRaider.class, ZOMBIE_RAIDER_NAME);
        if (ExtendedConfig.spawnZombieRaiders) {
            addSpawn(BiomeDictionary.Type.PLAINS, EntityZombieRaider.class, 1, 1, 1);
        }
        registerModEntity(Resources.SKELETON, EntitySkeletonRaider.class, SKELETON_RAIDER_NAME);
        if (ExtendedConfig.spawnSkeletonRaiders) {
            addSpawn(BiomeDictionary.Type.PLAINS, EntitySkeletonRaider.class, 1, 1, 1);
        }

        registerModEntity(Resources.RAVEN, EntityRaven.class, RAVEN_NAME);
//        EntityRegistry.addSpawn(EntityRaven.class, 1, 3, 10, EnumCreatureType.AMBIENT);//TODO!!!!

        // Damned Warrior
        registerModEntity(Resources.DAMNED_WARRIOR, EntityDamnedWarrior.class, DAMNED_WARRIOR_NAME);


        registerModEntity(Resources.EMPTY, EntityGroupOfGravesMobSpawnerHelper.class, SPAWNER_HELPER_NAME);
    }

    private static void addSpawn(BiomeDictionary.Type biomeType, Class<? extends EntityLiving> entityClass,
                          int spawnProbability, int spawnMinCount, int spawnMaxCount) {
        addSpawn(biomeType, entityClass, EnumCreatureType.MONSTER, spawnProbability, spawnMinCount, spawnMaxCount);
    }

    private static void addSpawn(BiomeDictionary.Type biomeType, Class<? extends EntityLiving> entityClass,
                          EnumCreatureType mobType, int spawnProbability, int spawnMinCount, int spawnMaxCount) {
        Set<Biome> biomeSet = BiomeDictionary.getBiomes(biomeType);
        Biome[] biomeArray = new Biome[biomeSet.size()];
        biomeSet.toArray(biomeArray);
        EntityRegistry.addSpawn(entityClass, spawnProbability, spawnMinCount, spawnMaxCount, mobType, biomeArray);
    }

    private static void registerModEntity(ResourceLocation resource, Class<? extends net.minecraft.entity.Entity> entityClass, String entityName) {
        registerModEntity(resource, entityClass, entityName, mobId, ModInfo.ID, 100, 1, true);
        mobId++;
    }

    private static void registerModEntity(ResourceLocation resource, Class<? extends net.minecraft.entity.Entity> entityClass, String entityName, int id,
                                   Object mod, int trackingRange, int updateFrequency, boolean sendsVelocityUpdates) {
        EntityRegistry.registerModEntity(resource, entityClass, entityName, id, mod, trackingRange, updateFrequency, sendsVelocityUpdates);
    }
}
