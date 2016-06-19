package nightkosh.gravestone_extended.core;

import net.minecraft.util.ResourceLocation;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class Resources extends nightkosh.gravestone.core.Resources{

    protected static final String GUI_LOCATION = MOD_NAME + ":textures/gui/";
    protected static final String POTIONS_LOCATION = MOD_NAME + ":textures/potions/";
    protected static final String ENTITY_LOCATION = MOD_NAME + ":textures/entities/";
    protected static final String MEMORIALS_LOCATION = MOD_NAME + ":textures/memorials/";
    protected static final String ARMOR_LOCATION = MOD_NAME + ":textures/memorials/armor/";
    protected static final String PEDESTALS_LOCATION = MOD_NAME + ":textures/memorials/pedestal/";
    // blocks
    public static final String NIGHT_STONE = "nether_brick";
    public static final String THUNDER_STONE = "stonebrick";
    public static final String BONE_BLOCK = MOD_NAME + ":bone_block";
    public static final String SKULL_BONE_BLOCK = MOD_NAME + ":skull_bone_block";
    public static final String PENTAGRAM_ICO = MOD_NAME + ":pentagram";
    public static final ResourceLocation PENTAGRAM = new ResourceLocation(BLOCK_LOCATION + "pentagram.png");
    public static final ResourceLocation CANDLE = new ResourceLocation(BLOCK_LOCATION + "candle.png");
    public static final ResourceLocation PILE_OF_BONES = new ResourceLocation(BLOCK_LOCATION + "pileOfBones.png");
    public static final String ALTAR_TOP = MOD_NAME + ":altar_top";
    public static final String ALTAR_SIDE = MOD_NAME + ":altar_side";
    // items
    public static final String CHISEL = MOD_NAME + ":GSChisel";

    // gui
    public static final ResourceLocation ALTAR_GUI = new ResourceLocation(GUI_LOCATION + "altar_gui.png");
    public static final ResourceLocation CHISEL_GUI = new ResourceLocation(GUI_LOCATION + "chisel_gui.png");
    public static final ResourceLocation CHEST_GUI = new ResourceLocation("textures/gui/container/generic_54.png");

    //potions
    public static final ResourceLocation POTIONS = new ResourceLocation(POTIONS_LOCATION + "potions.png");

    // entities
    public static final ResourceLocation STEVE = new ResourceLocation("textures/entity/steve.png");
    public static final ResourceLocation ZOMBIE = new ResourceLocation("textures/entity/zombie/zombie.png");
    public static final ResourceLocation ZOMBIE_VILLAGER = new ResourceLocation("textures/entity/zombie/zombie_villager.png");
    public static final ResourceLocation ZOMBIE_PIGMAN = new ResourceLocation("textures/entity/zombie_pigman.png");
    public static final ResourceLocation SKELETON = new ResourceLocation("textures/entity/skeleton/skeleton.png");
    public static final ResourceLocation WITHER_SKELETON = new ResourceLocation("textures/entity/skeleton/wither_skeleton.png");
    public static final ResourceLocation WITCH = new ResourceLocation("textures/entity/witch.png");

    public static final ResourceLocation UNDERTAKER = new ResourceLocation(ENTITY_LOCATION + "undertaker.png");
    public static final ResourceLocation ZOMBIE_DOG = new ResourceLocation(ENTITY_LOCATION + "ZombieDog.png");
    public static final ResourceLocation GREEN_ZOMBIE_DOG = new ResourceLocation(ENTITY_LOCATION + "GreenZombieDog.png");
    public static final ResourceLocation ZOMBIE_OZELOT = new ResourceLocation(ENTITY_LOCATION + "ZombieOzelot.png");
    public static final ResourceLocation ZOMBIE_CAT_BLACK = new ResourceLocation(ENTITY_LOCATION + "ZombieCatBlack.png");
    public static final ResourceLocation ZOMBIE_CAT_RED = new ResourceLocation(ENTITY_LOCATION + "ZombieCatRed.png");
    public static final ResourceLocation ZOMBIE_CAT_SIAMESE = new ResourceLocation(ENTITY_LOCATION + "ZombieCatSiamese.png");
    public static final ResourceLocation GREEN_ZOMBIE_OZELOT = new ResourceLocation(ENTITY_LOCATION + "GreenZombieOzelot.png");
    public static final ResourceLocation GREEN_ZOMBIE_CAT_BLACK = new ResourceLocation(ENTITY_LOCATION + "GreenZombieCatBlack.png");
    public static final ResourceLocation GREEN_ZOMBIE_CAT_RED = new ResourceLocation(ENTITY_LOCATION + "GreenZombieCatRed.png");
    public static final ResourceLocation GREEN_ZOMBIE_CAT_SIAMESE = new ResourceLocation(ENTITY_LOCATION + "GreenZombieCatSiamese.png");
    public static final ResourceLocation SKELETON_DOG = new ResourceLocation(ENTITY_LOCATION + "SkeletonDog.png");
    public static final ResourceLocation SKELETON_CAT = new ResourceLocation(ENTITY_LOCATION + "SkeletonCat.png");
    public static final ResourceLocation SKULL_CRAWLER = new ResourceLocation(ENTITY_LOCATION + "SkullCrawler.png");
    public static final ResourceLocation WITHER_SKULL_CRAWLER = new ResourceLocation(ENTITY_LOCATION + "WitherSkullCrawler.png");
    public static final ResourceLocation ZOMBIE_SKULL_CRAWLER = new ResourceLocation(ENTITY_LOCATION + "ZombieSkullCrawler.png");
    public static final ResourceLocation RAVEN = new ResourceLocation(ENTITY_LOCATION + "Raven.png");

    public static final ResourceLocation VILLAGER = new ResourceLocation("textures/entity/villager/villager.png");
    public static final ResourceLocation VILLAGER_FARMER = new ResourceLocation("textures/entity/villager/farmer.png");
    public static final ResourceLocation VILLAGER_LIBRARIAN = new ResourceLocation("textures/entity/villager/librarian.png");
    public static final ResourceLocation VILLAGER_PRIEST = new ResourceLocation("textures/entity/villager/priest.png");
    public static final ResourceLocation VILLAGER_SMITH = new ResourceLocation("textures/entity/villager/smith.png");
    public static final ResourceLocation VILLAGER_BUTCHER = new ResourceLocation("textures/entity/villager/butcher.png");

    public static final ResourceLocation WOLF = new ResourceLocation("textures/entity/wolf/wolf.png");
    public static final ResourceLocation OCELOT = new ResourceLocation("textures/entity/cat/ocelot.png");
    public static final ResourceLocation BLACK_CAT = new ResourceLocation("textures/entity/cat/black.png");
    public static final ResourceLocation RED_CAT = new ResourceLocation("textures/entity/cat/red.png");
    public static final ResourceLocation SIAMESE_CAT = new ResourceLocation("textures/entity/cat/siamese.png");
    public static final ResourceLocation DONKEY = new ResourceLocation("textures/entity/horse/donkey.png");
    public static final ResourceLocation MULE = new ResourceLocation("textures/entity/horse/mule.png");
    public static final ResourceLocation ZOMBIE_HORSE = new ResourceLocation("textures/entity/horse/horse_zombie.png");
    public static final ResourceLocation SKELETON_HORSE = new ResourceLocation("textures/entity/horse/horse_skeleton.png");

    // models - memorials
    // cross
    public static final ResourceLocation MEMORIAL_WOODEN_CROSS = new ResourceLocation(MEMORIALS_LOCATION + "WoodenCrossMemorial.png");
    public static final ResourceLocation MEMORIAL_SANDSTONE_CROSS = new ResourceLocation(MEMORIALS_LOCATION + "SandstoneCrossMemorial.png");
    public static final ResourceLocation MEMORIAL_RED_SANDSTONE_CROSS = new ResourceLocation(MEMORIALS_LOCATION + "RedSandstoneCrossMemorial.png");
    public static final ResourceLocation MEMORIAL_STONE_CROSS = new ResourceLocation(MEMORIALS_LOCATION + "StoneCrossMemorial.png");
    public static final ResourceLocation MEMORIAL_DIORITE_CROSS = new ResourceLocation(MEMORIALS_LOCATION + "DioriteCrossMemorial.png");
    public static final ResourceLocation MEMORIAL_ANDESITE_CROSS = new ResourceLocation(MEMORIALS_LOCATION + "AndesiteCrossMemorial.png");
    public static final ResourceLocation MEMORIAL_GRANITE_CROSS = new ResourceLocation(MEMORIALS_LOCATION + "GraniteCrossMemorial.png");
    public static final ResourceLocation MEMORIAL_IRON_CROSS = new ResourceLocation(MEMORIALS_LOCATION + "IronCrossMemorial.png");
    public static final ResourceLocation MEMORIAL_GOLDEN_CROSS = new ResourceLocation(MEMORIALS_LOCATION + "GoldenCrossMemorial.png");
    public static final ResourceLocation MEMORIAL_DIAMOND_CROSS = new ResourceLocation(MEMORIALS_LOCATION + "DiamondCrossMemorial.png");
    public static final ResourceLocation MEMORIAL_EMERALD_CROSS = new ResourceLocation(MEMORIALS_LOCATION + "EmeraldCrossMemorial.png");
    public static final ResourceLocation MEMORIAL_LAPIS_CROSS = new ResourceLocation(MEMORIALS_LOCATION + "LapisCrossMemorial.png");
    public static final ResourceLocation MEMORIAL_REDSTONE_CROSS = new ResourceLocation(MEMORIALS_LOCATION + "RedstoneCrossMemorial.png");
    public static final ResourceLocation MEMORIAL_OBSIDIAN_CROSS = new ResourceLocation(MEMORIALS_LOCATION + "ObsidianCrossMemorial.png");
    public static final ResourceLocation MEMORIAL_QUARTZ_CROSS = new ResourceLocation(MEMORIALS_LOCATION + "QuartzCrossMemorial.png");
    public static final ResourceLocation MEMORIAL_PRIZMARINE_CROSS = new ResourceLocation(MEMORIALS_LOCATION + "PrizmarineCrossMemorial.png");
    public static final ResourceLocation MEMORIAL_ICE_CROSS = new ResourceLocation(MEMORIALS_LOCATION + "IceCrossMemorial.png");
    public static final ResourceLocation MEMORIAL_MOSSY_CROSS = new ResourceLocation(MEMORIALS_LOCATION + "MossyCrossMemorial.png");
    // obelisk
    public static final ResourceLocation MEMORIAL_WOODEN_OBELISK = new ResourceLocation(MEMORIALS_LOCATION + "WoodenObeliskMemorial.png");
    public static final ResourceLocation MEMORIAL_SANDSTONE_OBELISK = new ResourceLocation(MEMORIALS_LOCATION + "SandstoneObeliskMemorial.png");
    public static final ResourceLocation MEMORIAL_RED_SANDSTONE_OBELISK = new ResourceLocation(MEMORIALS_LOCATION + "RedSandstoneObeliskMemorial.png");
    public static final ResourceLocation MEMORIAL_STONE_OBELISK = new ResourceLocation(MEMORIALS_LOCATION + "StoneObeliskMemorial.png");
    public static final ResourceLocation MEMORIAL_DIORITE_OBELISK = new ResourceLocation(MEMORIALS_LOCATION + "DioriteObeliskMemorial.png");
    public static final ResourceLocation MEMORIAL_ANDESITE_OBELISK = new ResourceLocation(MEMORIALS_LOCATION + "AndesiteObeliskMemorial.png");
    public static final ResourceLocation MEMORIAL_GRANITE_OBELISK = new ResourceLocation(MEMORIALS_LOCATION + "GraniteObeliskMemorial.png");
    public static final ResourceLocation MEMORIAL_IRON_OBELISK = new ResourceLocation(MEMORIALS_LOCATION + "IronObeliskMemorial.png");
    public static final ResourceLocation MEMORIAL_GOLDEN_OBELISK = new ResourceLocation(MEMORIALS_LOCATION + "GoldenObeliskMemorial.png");
    public static final ResourceLocation MEMORIAL_DIAMOND_OBELISK = new ResourceLocation(MEMORIALS_LOCATION + "DiamondObeliskMemorial.png");
    public static final ResourceLocation MEMORIAL_EMERALD_OBELISK = new ResourceLocation(MEMORIALS_LOCATION + "EmeraldObeliskMemorial.png");
    public static final ResourceLocation MEMORIAL_LAPIS_OBELISK = new ResourceLocation(MEMORIALS_LOCATION + "LapisObeliskMemorial.png");
    public static final ResourceLocation MEMORIAL_REDSTONE_OBELISK = new ResourceLocation(MEMORIALS_LOCATION + "RedstoneObeliskMemorial.png");
    public static final ResourceLocation MEMORIAL_OBSIDIAN_OBELISK = new ResourceLocation(MEMORIALS_LOCATION + "ObsidianObeliskMemorial.png");
    public static final ResourceLocation MEMORIAL_QUARTZ_OBELISK = new ResourceLocation(MEMORIALS_LOCATION + "QuartzObeliskMemorial.png");
    public static final ResourceLocation MEMORIAL_PRIZMARINE_OBELISK = new ResourceLocation(MEMORIALS_LOCATION + "PrizmarineObeliskMemorial.png");
    public static final ResourceLocation MEMORIAL_ICE_OBELISK = new ResourceLocation(MEMORIALS_LOCATION + "IceObeliskMemorial.png");
    public static final ResourceLocation MEMORIAL_MOSSY_OBELISK = new ResourceLocation(MEMORIALS_LOCATION + "MossyObeliskMemorial.png");
    // steve memorials
    public static final ResourceLocation MEMORIAL_WOODEN_STEVE_STATUE = new ResourceLocation(MEMORIALS_LOCATION + "WoodenSteveMemorial.png");
    public static final ResourceLocation MEMORIAL_SANDSTONE_STEVE_STATUE = new ResourceLocation(MEMORIALS_LOCATION + "SandstoneSteveMemorial.png");
    public static final ResourceLocation MEMORIAL_RED_SANDSTONE_STEVE_STATUE = new ResourceLocation(MEMORIALS_LOCATION + "RedSandstoneSteveMemorial.png");
    public static final ResourceLocation MEMORIAL_STONE_STEVE_STATUE = new ResourceLocation(MEMORIALS_LOCATION + "StoneSteveMemorial.png");
    public static final ResourceLocation MEMORIAL_DIORITE_STEVE_STATUE = new ResourceLocation(MEMORIALS_LOCATION + "DioriteSteveMemorial.png");
    public static final ResourceLocation MEMORIAL_ANDESITE_STEVE_STATUE = new ResourceLocation(MEMORIALS_LOCATION + "AndesiteSteveMemorial.png");
    public static final ResourceLocation MEMORIAL_GRANITE_STEVE_STATUE = new ResourceLocation(MEMORIALS_LOCATION + "GraniteSteveMemorial.png");
    public static final ResourceLocation MEMORIAL_IRON_STEVE_STATUE = new ResourceLocation(MEMORIALS_LOCATION + "IronSteveMemorial.png");
    public static final ResourceLocation MEMORIAL_GOLDEN_STEVE_STATUE = new ResourceLocation(MEMORIALS_LOCATION + "GoldenSteveMemorial.png");
    public static final ResourceLocation MEMORIAL_DIAMOND_STEVE_STATUE = new ResourceLocation(MEMORIALS_LOCATION + "DiamondSteveMemorial.png");
    public static final ResourceLocation MEMORIAL_EMERALD_STEVE_STATUE = new ResourceLocation(MEMORIALS_LOCATION + "EmeraldSteveMemorial.png");
    public static final ResourceLocation MEMORIAL_LAPIS_STEVE_STATUE = new ResourceLocation(MEMORIALS_LOCATION + "LapisSteveMemorial.png");
    public static final ResourceLocation MEMORIAL_REDSTONE_STEVE_STATUE = new ResourceLocation(MEMORIALS_LOCATION + "RedstoneSteveMemorial.png");
    public static final ResourceLocation MEMORIAL_OBSIDIAN_STEVE_STATUE = new ResourceLocation(MEMORIALS_LOCATION + "ObsidianSteveMemorial.png");
    public static final ResourceLocation MEMORIAL_QUARTZ_STEVE_STATUE = new ResourceLocation(MEMORIALS_LOCATION + "QuartzSteveMemorial.png");
    public static final ResourceLocation MEMORIAL_PRIZMARINE_STEVE_STATUE = new ResourceLocation(MEMORIALS_LOCATION + "PrizmarineSteveMemorial.png");
    public static final ResourceLocation MEMORIAL_ICE_STEVE_STATUE = new ResourceLocation(MEMORIALS_LOCATION + "IceSteveMemorial.png");
    public static final ResourceLocation MEMORIAL_MOSSY_STEVE_STATUE = new ResourceLocation(MEMORIALS_LOCATION + "MossySteveMemorial.png");
    // villagers memorials
    public static final ResourceLocation MEMORIAL_WOODEN_VILLAGER_STATUE = new ResourceLocation(MEMORIALS_LOCATION + "WoodenVillagerMemorial.png");
    public static final ResourceLocation MEMORIAL_SANDSTONE_VILLAGER_STATUE = new ResourceLocation(MEMORIALS_LOCATION + "SandstoneVillagerMemorial.png");
    public static final ResourceLocation MEMORIAL_RED_SANDSTONE_VILLAGER_STATUE = new ResourceLocation(MEMORIALS_LOCATION + "RedSandstoneVillagerMemorial.png");
    public static final ResourceLocation MEMORIAL_STONE_VILLAGER_STATUE = new ResourceLocation(MEMORIALS_LOCATION + "StoneVillagerMemorial.png");
    public static final ResourceLocation MEMORIAL_DIORITE_VILLAGER_STATUE = new ResourceLocation(MEMORIALS_LOCATION + "DioriteVillagerMemorial.png");
    public static final ResourceLocation MEMORIAL_ANDESITE_VILLAGER_STATUE = new ResourceLocation(MEMORIALS_LOCATION + "AndesiteVillagerMemorial.png");
    public static final ResourceLocation MEMORIAL_GRANITE_VILLAGER_STATUE = new ResourceLocation(MEMORIALS_LOCATION + "GraniteVillagerMemorial.png");
    public static final ResourceLocation MEMORIAL_IRON_VILLAGER_STATUE = new ResourceLocation(MEMORIALS_LOCATION + "IronVillagerMemorial.png");
    public static final ResourceLocation MEMORIAL_GOLDEN_VILLAGER_STATUE = new ResourceLocation(MEMORIALS_LOCATION + "GoldenVillagerMemorial.png");
    public static final ResourceLocation MEMORIAL_DIAMOND_VILLAGER_STATUE = new ResourceLocation(MEMORIALS_LOCATION + "DiamondVillagerMemorial.png");
    public static final ResourceLocation MEMORIAL_EMERALD_VILLAGER_STATUE = new ResourceLocation(MEMORIALS_LOCATION + "EmeraldVillagerMemorial.png");
    public static final ResourceLocation MEMORIAL_LAPIS_VILLAGER_STATUE = new ResourceLocation(MEMORIALS_LOCATION + "LapisVillagerMemorial.png");
    public static final ResourceLocation MEMORIAL_REDSTONE_VILLAGER_STATUE = new ResourceLocation(MEMORIALS_LOCATION + "RedstoneVillagerMemorial.png");
    public static final ResourceLocation MEMORIAL_OBSIDIAN_VILLAGER_STATUE = new ResourceLocation(MEMORIALS_LOCATION + "ObsidianVillagerMemorial.png");
    public static final ResourceLocation MEMORIAL_QUARTZ_VILLAGER_STATUE = new ResourceLocation(MEMORIALS_LOCATION + "QuartzVillagerMemorial.png");
    public static final ResourceLocation MEMORIAL_PRIZMARINE_VILLAGER_STATUE = new ResourceLocation(MEMORIALS_LOCATION + "PrizmarineVillagerMemorial.png");
    public static final ResourceLocation MEMORIAL_ICE_VILLAGER_STATUE = new ResourceLocation(MEMORIALS_LOCATION + "IceVillagerMemorial.png");
    public static final ResourceLocation MEMORIAL_MOSSY_VILLAGER_STATUE = new ResourceLocation(MEMORIALS_LOCATION + "MossyVillagerMemorial.png");
    // angels memorials
    public static final ResourceLocation MEMORIAL_WOODEN_ANGEL_STATUE = new ResourceLocation(MEMORIALS_LOCATION + "WoodenAngelMemorial.png");
    public static final ResourceLocation MEMORIAL_SANDSTONE_ANGEL_STATUE = new ResourceLocation(MEMORIALS_LOCATION + "SandstoneAngelMemorial.png");
    public static final ResourceLocation MEMORIAL_RED_SANDSTONE_ANGEL_STATUE = new ResourceLocation(MEMORIALS_LOCATION + "RedSandstoneAngelMemorial.png");
    public static final ResourceLocation MEMORIAL_STONE_ANGEL_STATUE = new ResourceLocation(MEMORIALS_LOCATION + "StoneAngelMemorial.png");
    public static final ResourceLocation MEMORIAL_DIORITE_ANGEL_STATUE = new ResourceLocation(MEMORIALS_LOCATION + "DioriteAngelMemorial.png");
    public static final ResourceLocation MEMORIAL_ANDESITE_ANGEL_STATUE = new ResourceLocation(MEMORIALS_LOCATION + "AndesiteAngelMemorial.png");
    public static final ResourceLocation MEMORIAL_GRANITE_ANGEL_STATUE = new ResourceLocation(MEMORIALS_LOCATION + "GraniteAngelMemorial.png");
    public static final ResourceLocation MEMORIAL_IRON_ANGEL_STATUE = new ResourceLocation(MEMORIALS_LOCATION + "IronAngelMemorial.png");
    public static final ResourceLocation MEMORIAL_GOLDEN_ANGEL_STATUE = new ResourceLocation(MEMORIALS_LOCATION + "GoldenAngelMemorial.png");
    public static final ResourceLocation MEMORIAL_DIAMOND_ANGEL_STATUE = new ResourceLocation(MEMORIALS_LOCATION + "DiamondAngelMemorial.png");
    public static final ResourceLocation MEMORIAL_EMERALD_ANGEL_STATUE = new ResourceLocation(MEMORIALS_LOCATION + "EmeraldAngelMemorial.png");
    public static final ResourceLocation MEMORIAL_LAPIS_ANGEL_STATUE = new ResourceLocation(MEMORIALS_LOCATION + "LapisAngelMemorial.png");
    public static final ResourceLocation MEMORIAL_REDSTONE_ANGEL_STATUE = new ResourceLocation(MEMORIALS_LOCATION + "RedstoneAngelMemorial.png");
    public static final ResourceLocation MEMORIAL_OBSIDIAN_ANGEL_STATUE = new ResourceLocation(MEMORIALS_LOCATION + "ObsidianAngelMemorial.png");
    public static final ResourceLocation MEMORIAL_QUARTZ_ANGEL_STATUE = new ResourceLocation(MEMORIALS_LOCATION + "QuartzAngelMemorial.png");
    public static final ResourceLocation MEMORIAL_PRIZMARINE_ANGEL_STATUE = new ResourceLocation(MEMORIALS_LOCATION + "PrizmarineAngelMemorial.png");
    public static final ResourceLocation MEMORIAL_ICE_ANGEL_STATUE = new ResourceLocation(MEMORIALS_LOCATION + "IceAngelMemorial.png");
    public static final ResourceLocation MEMORIAL_MOSSY_ANGEL_STATUE = new ResourceLocation(MEMORIALS_LOCATION + "MossyAngelMemorial.png");
    // dogs memorials
    public static final ResourceLocation MEMORIAL_WOODEN_DOG_STATUE = new ResourceLocation(MEMORIALS_LOCATION + "WoodenDogMemorial.png");
    public static final ResourceLocation MEMORIAL_SANDSTONE_DOG_STATUE = new ResourceLocation(MEMORIALS_LOCATION + "SandstoneDogMemorial.png");
    public static final ResourceLocation MEMORIAL_RED_SANDSTONE_DOG_STATUE = new ResourceLocation(MEMORIALS_LOCATION + "RedSandstoneDogMemorial.png");
    public static final ResourceLocation MEMORIAL_STONE_DOG_STATUE = new ResourceLocation(MEMORIALS_LOCATION + "StoneDogMemorial.png");
    public static final ResourceLocation MEMORIAL_DIORITE_DOG_STATUE = new ResourceLocation(MEMORIALS_LOCATION + "DioriteDogMemorial.png");
    public static final ResourceLocation MEMORIAL_ANDESITE_DOG_STATUE = new ResourceLocation(MEMORIALS_LOCATION + "AndesiteDogMemorial.png");
    public static final ResourceLocation MEMORIAL_GRANITE_DOG_STATUE = new ResourceLocation(MEMORIALS_LOCATION + "GraniteDogMemorial.png");
    public static final ResourceLocation MEMORIAL_IRON_DOG_STATUE = new ResourceLocation(MEMORIALS_LOCATION + "IronDogMemorial.png");
    public static final ResourceLocation MEMORIAL_GOLDEN_DOG_STATUE = new ResourceLocation(MEMORIALS_LOCATION + "GoldenDogMemorial.png");
    public static final ResourceLocation MEMORIAL_DIAMOND_DOG_STATUE = new ResourceLocation(MEMORIALS_LOCATION + "DiamondDogMemorial.png");
    public static final ResourceLocation MEMORIAL_EMERALD_DOG_STATUE = new ResourceLocation(MEMORIALS_LOCATION + "EmeraldDogMemorial.png");
    public static final ResourceLocation MEMORIAL_LAPIS_DOG_STATUE = new ResourceLocation(MEMORIALS_LOCATION + "LapisDogMemorial.png");
    public static final ResourceLocation MEMORIAL_REDSTONE_DOG_STATUE = new ResourceLocation(MEMORIALS_LOCATION + "RedstoneDogMemorial.png");
    public static final ResourceLocation MEMORIAL_OBSIDIAN_DOG_STATUE = new ResourceLocation(MEMORIALS_LOCATION + "ObsidianDogMemorial.png");
    public static final ResourceLocation MEMORIAL_QUARTZ_DOG_STATUE = new ResourceLocation(MEMORIALS_LOCATION + "QuartzDogMemorial.png");
    public static final ResourceLocation MEMORIAL_PRIZMARINE_DOG_STATUE = new ResourceLocation(MEMORIALS_LOCATION + "PrizmarineDogMemorial.png");
    public static final ResourceLocation MEMORIAL_ICE_DOG_STATUE = new ResourceLocation(MEMORIALS_LOCATION + "IceDogMemorial.png");
    public static final ResourceLocation MEMORIAL_MOSSY_DOG_STATUE = new ResourceLocation(MEMORIALS_LOCATION + "MossyDogMemorial.png");
    // cats memorials
    public static final ResourceLocation MEMORIAL_WOODEN_CAT_STATUE = new ResourceLocation(MEMORIALS_LOCATION + "WoodenCatMemorial.png");
    public static final ResourceLocation MEMORIAL_SANDSTONE_CAT_STATUE = new ResourceLocation(MEMORIALS_LOCATION + "SandstoneCatMemorial.png");
    public static final ResourceLocation MEMORIAL_RED_SANDSTONE_CAT_STATUE = new ResourceLocation(MEMORIALS_LOCATION + "RedSandstoneCatMemorial.png");
    public static final ResourceLocation MEMORIAL_STONE_CAT_STATUE = new ResourceLocation(MEMORIALS_LOCATION + "StoneCatMemorial.png");
    public static final ResourceLocation MEMORIAL_DIORITE_CAT_STATUE = new ResourceLocation(MEMORIALS_LOCATION + "DioriteCatMemorial.png");
    public static final ResourceLocation MEMORIAL_ANDESITE_CAT_STATUE = new ResourceLocation(MEMORIALS_LOCATION + "AndesiteCatMemorial.png");
    public static final ResourceLocation MEMORIAL_GRANITE_CAT_STATUE = new ResourceLocation(MEMORIALS_LOCATION + "GraniteCatMemorial.png");
    public static final ResourceLocation MEMORIAL_IRON_CAT_STATUE = new ResourceLocation(MEMORIALS_LOCATION + "IronCatMemorial.png");
    public static final ResourceLocation MEMORIAL_GOLDEN_CAT_STATUE = new ResourceLocation(MEMORIALS_LOCATION + "GoldenCatMemorial.png");
    public static final ResourceLocation MEMORIAL_DIAMOND_CAT_STATUE = new ResourceLocation(MEMORIALS_LOCATION + "DiamondCatMemorial.png");
    public static final ResourceLocation MEMORIAL_EMERALD_CAT_STATUE = new ResourceLocation(MEMORIALS_LOCATION + "EmeraldCatMemorial.png");
    public static final ResourceLocation MEMORIAL_LAPIS_CAT_STATUE = new ResourceLocation(MEMORIALS_LOCATION + "LapisCatMemorial.png");
    public static final ResourceLocation MEMORIAL_REDSTONE_CAT_STATUE = new ResourceLocation(MEMORIALS_LOCATION + "RedstoneCatMemorial.png");
    public static final ResourceLocation MEMORIAL_OBSIDIAN_CAT_STATUE = new ResourceLocation(MEMORIALS_LOCATION + "ObsidianCatMemorial.png");
    public static final ResourceLocation MEMORIAL_QUARTZ_CAT_STATUE = new ResourceLocation(MEMORIALS_LOCATION + "QuartzCatMemorial.png");
    public static final ResourceLocation MEMORIAL_PRIZMARINE_CAT_STATUE = new ResourceLocation(MEMORIALS_LOCATION + "PrizmarineCatMemorial.png");
    public static final ResourceLocation MEMORIAL_ICE_CAT_STATUE = new ResourceLocation(MEMORIALS_LOCATION + "IceCatMemorial.png");
    public static final ResourceLocation MEMORIAL_MOSSY_CAT_STATUE = new ResourceLocation(MEMORIALS_LOCATION + "MossyCatMemorial.png");
    // creepers memorials
    public static final ResourceLocation MEMORIAL_WOODEN_CREEPER_STATUE = new ResourceLocation(MEMORIALS_LOCATION + "WoodenCreeperMemorial.png");
    public static final ResourceLocation MEMORIAL_SANDSTONE_CREEPER_STATUE = new ResourceLocation(MEMORIALS_LOCATION + "SandstoneCreeperMemorial.png");
    public static final ResourceLocation MEMORIAL_RED_SANDSTONE_CREEPER_STATUE = new ResourceLocation(MEMORIALS_LOCATION + "RedSandstoneCreeperMemorial.png");
    public static final ResourceLocation MEMORIAL_STONE_CREEPER_STATUE = new ResourceLocation(MEMORIALS_LOCATION + "StoneCreeperMemorial.png");
    public static final ResourceLocation MEMORIAL_DIORITE_CREEPER_STATUE = new ResourceLocation(MEMORIALS_LOCATION + "DioriteCreeperMemorial.png");
    public static final ResourceLocation MEMORIAL_ANDESITE_CREEPER_STATUE = new ResourceLocation(MEMORIALS_LOCATION + "AndesiteCreeperMemorial.png");
    public static final ResourceLocation MEMORIAL_GRANITE_CREEPER_STATUE = new ResourceLocation(MEMORIALS_LOCATION + "GraniteCreeperMemorial.png");
    public static final ResourceLocation MEMORIAL_IRON_CREEPER_STATUE = new ResourceLocation(MEMORIALS_LOCATION + "IronCreeperMemorial.png");
    public static final ResourceLocation MEMORIAL_GOLDEN_CREEPER_STATUE = new ResourceLocation(MEMORIALS_LOCATION + "GoldenCreeperMemorial.png");
    public static final ResourceLocation MEMORIAL_DIAMOND_CREEPER_STATUE = new ResourceLocation(MEMORIALS_LOCATION + "DiamondCreeperMemorial.png");
    public static final ResourceLocation MEMORIAL_EMERALD_CREEPER_STATUE = new ResourceLocation(MEMORIALS_LOCATION + "EmeraldCreeperMemorial.png");
    public static final ResourceLocation MEMORIAL_LAPIS_CREEPER_STATUE = new ResourceLocation(MEMORIALS_LOCATION + "LapisCreeperMemorial.png");
    public static final ResourceLocation MEMORIAL_REDSTONE_CREEPER_STATUE = new ResourceLocation(MEMORIALS_LOCATION + "RedstoneCreeperMemorial.png");
    public static final ResourceLocation MEMORIAL_OBSIDIAN_CREEPER_STATUE = new ResourceLocation(MEMORIALS_LOCATION + "ObsidianCreeperMemorial.png");
    public static final ResourceLocation MEMORIAL_QUARTZ_CREEPER_STATUE = new ResourceLocation(MEMORIALS_LOCATION + "QuartzCreeperMemorial.png");
    public static final ResourceLocation MEMORIAL_PRIZMARINE_CREEPER_STATUE = new ResourceLocation(MEMORIALS_LOCATION + "PrizmarineCreeperMemorial.png");
    public static final ResourceLocation MEMORIAL_ICE_CREEPER_STATUE = new ResourceLocation(MEMORIALS_LOCATION + "IceCreeperMemorial.png");
    public static final ResourceLocation MEMORIAL_MOSSY_CREEPER_STATUE = new ResourceLocation(MEMORIALS_LOCATION + "MossyCreeperMemorial.png");
    // gibbet
    public static final ResourceLocation MEMORIAL_GIBBET = new ResourceLocation(MEMORIALS_LOCATION + "gibbet.png");
    // stocks
    public static final ResourceLocation MEMORIAL_STOCKS = new ResourceLocation(MEMORIALS_LOCATION + "stocks.png");
    // burning stake
    public static final ResourceLocation BURNING_STAKE = new ResourceLocation(MEMORIALS_LOCATION + "BurningStake.png");

    // models - parts
    public static final ResourceLocation CREEPER_AURA = new ResourceLocation("textures/entity/creeper/creeper_armor.png");
    // armor
    public static final ResourceLocation WOODEN_ARMOR = new ResourceLocation(ARMOR_LOCATION + "WoodenArmor.png");
    public static final ResourceLocation SANDSTONE_ARMOR = new ResourceLocation(ARMOR_LOCATION + "SandstoneArmor.png");
    public static final ResourceLocation RED_SANDSTONE_ARMOR = new ResourceLocation(ARMOR_LOCATION + "RedSandstoneArmor.png");
    public static final ResourceLocation STONE_ARMOR = new ResourceLocation(ARMOR_LOCATION + "StoneArmor.png");
    public static final ResourceLocation DIORITE_ARMOR = new ResourceLocation(ARMOR_LOCATION + "DioriteArmor.png");
    public static final ResourceLocation ANDESITE_ARMOR = new ResourceLocation(ARMOR_LOCATION + "AndesiteArmor.png");
    public static final ResourceLocation GRANITE_ARMOR = new ResourceLocation(ARMOR_LOCATION + "GraniteArmor.png");
    public static final ResourceLocation IRON_ARMOR = new ResourceLocation(ARMOR_LOCATION + "IronArmor.png");
    public static final ResourceLocation GOLDEN_ARMOR = new ResourceLocation(ARMOR_LOCATION + "GoldenArmor.png");
    public static final ResourceLocation DIAMOND_ARMOR = new ResourceLocation(ARMOR_LOCATION + "DiamondArmor.png");
    public static final ResourceLocation EMERALD_ARMOR = new ResourceLocation(ARMOR_LOCATION + "EmeraldArmor.png");
    public static final ResourceLocation LAPIS_ARMOR = new ResourceLocation(ARMOR_LOCATION + "LapisArmor.png");
    public static final ResourceLocation REDSTONE_ARMOR = new ResourceLocation(ARMOR_LOCATION + "RedstoneArmor.png");
    public static final ResourceLocation OBSIDIAN_ARMOR = new ResourceLocation(ARMOR_LOCATION + "ObsidianArmor.png");
    public static final ResourceLocation QUARTZ_ARMOR = new ResourceLocation(ARMOR_LOCATION + "QuartzArmor.png");
    public static final ResourceLocation PRIZMARINE_ARMOR = new ResourceLocation(ARMOR_LOCATION + "PrizmarineArmor.png");
    public static final ResourceLocation ICE_ARMOR = new ResourceLocation(ARMOR_LOCATION + "IceArmor.png");
    public static final ResourceLocation MOSSY_ARMOR = new ResourceLocation(ARMOR_LOCATION + "MossyArmor.png");
    // small pedestal
    public static final ResourceLocation SMALL_PEDESTAL = new ResourceLocation(MEMORIALS_LOCATION + "ModelSmallPedestal.png");
    // big pedestals
    public static final ResourceLocation MEMORIAL_WOODEN_BIG_PEDESTAL = new ResourceLocation(PEDESTALS_LOCATION + "WoodenBigPedestal.png");
    public static final ResourceLocation MEMORIAL_SANDSTONE_BIG_PEDESTAL = new ResourceLocation(PEDESTALS_LOCATION + "SandstoneBigPedestal.png");
    public static final ResourceLocation MEMORIAL_RED_SANDSTONE_BIG_PEDESTAL = new ResourceLocation(PEDESTALS_LOCATION + "RedSandstoneBigPedestal.png");
    public static final ResourceLocation MEMORIAL_STONE_BIG_PEDESTAL = new ResourceLocation(PEDESTALS_LOCATION + "StoneBigPedestal.png");
    public static final ResourceLocation MEMORIAL_DIORITE_BIG_PEDESTAL = new ResourceLocation(PEDESTALS_LOCATION + "DioriteBigPedestal.png");
    public static final ResourceLocation MEMORIAL_ANDESITE_BIG_PEDESTAL = new ResourceLocation(PEDESTALS_LOCATION + "AndesiteBigPedestal.png");
    public static final ResourceLocation MEMORIAL_GRANITE_BIG_PEDESTAL = new ResourceLocation(PEDESTALS_LOCATION + "GraniteBigPedestal.png");
    public static final ResourceLocation MEMORIAL_IRON_BIG_PEDESTAL = new ResourceLocation(PEDESTALS_LOCATION + "IronBigPedestal.png");
    public static final ResourceLocation MEMORIAL_GOLDEN_BIG_PEDESTAL = new ResourceLocation(PEDESTALS_LOCATION + "GoldenBigPedestal.png");
    public static final ResourceLocation MEMORIAL_DIAMOND_BIG_PEDESTAL = new ResourceLocation(PEDESTALS_LOCATION + "DiamondBigPedestal.png");
    public static final ResourceLocation MEMORIAL_EMERALD_BIG_PEDESTAL = new ResourceLocation(PEDESTALS_LOCATION + "EmeraldBigPedestal.png");
    public static final ResourceLocation MEMORIAL_LAPIS_BIG_PEDESTAL = new ResourceLocation(PEDESTALS_LOCATION + "LapisBigPedestal.png");
    public static final ResourceLocation MEMORIAL_REDSTONE_BIG_PEDESTAL = new ResourceLocation(PEDESTALS_LOCATION + "RedstoneBigPedestal.png");
    public static final ResourceLocation MEMORIAL_OBSIDIAN_BIG_PEDESTAL = new ResourceLocation(PEDESTALS_LOCATION + "ObsidianBigPedestal.png");
    public static final ResourceLocation MEMORIAL_QUARTZ_BIG_PEDESTAL = new ResourceLocation(PEDESTALS_LOCATION + "QuartzBigPedestal.png");
    public static final ResourceLocation MEMORIAL_PRIZMARINE_BIG_PEDESTAL = new ResourceLocation(PEDESTALS_LOCATION + "PrizmarineBigPedestal.png");
    public static final ResourceLocation MEMORIAL_ICE_BIG_PEDESTAL = new ResourceLocation(PEDESTALS_LOCATION + "IceBigPedestal.png");
    public static final ResourceLocation MEMORIAL_MOSSY_BIG_PEDESTAL = new ResourceLocation(PEDESTALS_LOCATION + "MossyBigPedestal.png");

    // haunted chest
    public static final ResourceLocation CHRISTMAS_CHEST = new ResourceLocation("textures/entity/chest/christmas.png");
    public static final ResourceLocation DEFAULT_CHEST = new ResourceLocation("textures/entity/chest/normal.png");

    // skull candle
    public static final ResourceLocation SKELETON_SKULL_CANDLE = new ResourceLocation(BLOCK_LOCATION + "SkeletonSkullCandle.png");
    public static final ResourceLocation WITHER_SKULL_CANDLE = new ResourceLocation(BLOCK_LOCATION + "WitherSkullCandle.png");
    public static final ResourceLocation ZOMBIE_SKULL_CANDLE = new ResourceLocation(BLOCK_LOCATION + "ZombieSkullCandle.png");
}
