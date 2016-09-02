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
    protected static final String EXECUTIONS_LOCATION = MOD_NAME + ":textures/execution/";
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
    public static final String CHISEL = MOD_NAME + ":" + GSItem.CHISEL;

    // gui
    public static final ResourceLocation ALTAR_GUI = new ResourceLocation(GUI_LOCATION + "altar_gui.png");
    public static final ResourceLocation CHISEL_GUI = new ResourceLocation(GUI_LOCATION + "chisel_gui.png");

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

    public static final String UNDERTAKER = ENTITY_LOCATION + "undertaker.png";
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
    public static final ResourceLocation MEMORIAL_WOODEN_CROSS = new ResourceLocation(MEMORIALS_LOCATION + "Cross/Wooden.png");
    public static final ResourceLocation MEMORIAL_SANDSTONE_CROSS = new ResourceLocation(MEMORIALS_LOCATION + "Cross/Sandstone.png");
    public static final ResourceLocation MEMORIAL_RED_SANDSTONE_CROSS = new ResourceLocation(MEMORIALS_LOCATION + "Cross/RedSandstone.png");
    public static final ResourceLocation MEMORIAL_STONE_CROSS = new ResourceLocation(MEMORIALS_LOCATION + "Cross/Stone.png");
    public static final ResourceLocation MEMORIAL_DIORITE_CROSS = new ResourceLocation(MEMORIALS_LOCATION + "Cross/Diorite.png");
    public static final ResourceLocation MEMORIAL_ANDESITE_CROSS = new ResourceLocation(MEMORIALS_LOCATION + "Cross/Andesite.png");
    public static final ResourceLocation MEMORIAL_GRANITE_CROSS = new ResourceLocation(MEMORIALS_LOCATION + "Cross/Granite.png");
    public static final ResourceLocation MEMORIAL_IRON_CROSS = new ResourceLocation(MEMORIALS_LOCATION + "Cross/Iron.png");
    public static final ResourceLocation MEMORIAL_GOLDEN_CROSS = new ResourceLocation(MEMORIALS_LOCATION + "Cross/Golden.png");
    public static final ResourceLocation MEMORIAL_DIAMOND_CROSS = new ResourceLocation(MEMORIALS_LOCATION + "Cross/Diamond.png");
    public static final ResourceLocation MEMORIAL_EMERALD_CROSS = new ResourceLocation(MEMORIALS_LOCATION + "Cross/Emerald.png");
    public static final ResourceLocation MEMORIAL_LAPIS_CROSS = new ResourceLocation(MEMORIALS_LOCATION + "Cross/Lapis.png");
    public static final ResourceLocation MEMORIAL_REDSTONE_CROSS = new ResourceLocation(MEMORIALS_LOCATION + "Cross/Redstone.png");
    public static final ResourceLocation MEMORIAL_OBSIDIAN_CROSS = new ResourceLocation(MEMORIALS_LOCATION + "Cross/Obsidian.png");
    public static final ResourceLocation MEMORIAL_QUARTZ_CROSS = new ResourceLocation(MEMORIALS_LOCATION + "Cross/Quartz.png");
    public static final ResourceLocation MEMORIAL_PRIZMARINE_CROSS = new ResourceLocation(MEMORIALS_LOCATION + "Cross/Prizmarine.png");
    public static final ResourceLocation MEMORIAL_ICE_CROSS = new ResourceLocation(MEMORIALS_LOCATION + "Cross/Ice.png");
    public static final ResourceLocation MEMORIAL_MOSSY_CROSS = new ResourceLocation(MEMORIALS_LOCATION + "Cross/Mossy.png");
    // steve memorials
    public static final ResourceLocation MEMORIAL_WOODEN_STEVE_STATUE = new ResourceLocation(MEMORIALS_LOCATION + "SteveStatue/Wooden.png");
    public static final ResourceLocation MEMORIAL_SANDSTONE_STEVE_STATUE = new ResourceLocation(MEMORIALS_LOCATION + "SteveStatue/Sandstone.png");
    public static final ResourceLocation MEMORIAL_RED_SANDSTONE_STEVE_STATUE = new ResourceLocation(MEMORIALS_LOCATION + "SteveStatue/RedSandstone.png");
    public static final ResourceLocation MEMORIAL_STONE_STEVE_STATUE = new ResourceLocation(MEMORIALS_LOCATION + "SteveStatue/Stone.png");
    public static final ResourceLocation MEMORIAL_DIORITE_STEVE_STATUE = new ResourceLocation(MEMORIALS_LOCATION + "SteveStatue/Diorite.png");
    public static final ResourceLocation MEMORIAL_ANDESITE_STEVE_STATUE = new ResourceLocation(MEMORIALS_LOCATION + "SteveStatue/Andesite.png");
    public static final ResourceLocation MEMORIAL_GRANITE_STEVE_STATUE = new ResourceLocation(MEMORIALS_LOCATION + "SteveStatue/Granite.png");
    public static final ResourceLocation MEMORIAL_IRON_STEVE_STATUE = new ResourceLocation(MEMORIALS_LOCATION + "SteveStatue/Iron.png");
    public static final ResourceLocation MEMORIAL_GOLDEN_STEVE_STATUE = new ResourceLocation(MEMORIALS_LOCATION + "SteveStatue/Golden.png");
    public static final ResourceLocation MEMORIAL_DIAMOND_STEVE_STATUE = new ResourceLocation(MEMORIALS_LOCATION + "SteveStatue/Diamond.png");
    public static final ResourceLocation MEMORIAL_EMERALD_STEVE_STATUE = new ResourceLocation(MEMORIALS_LOCATION + "SteveStatue/Emerald.png");
    public static final ResourceLocation MEMORIAL_LAPIS_STEVE_STATUE = new ResourceLocation(MEMORIALS_LOCATION + "SteveStatue/Lapis.png");
    public static final ResourceLocation MEMORIAL_REDSTONE_STEVE_STATUE = new ResourceLocation(MEMORIALS_LOCATION + "SteveStatue/Redstone.png");
    public static final ResourceLocation MEMORIAL_OBSIDIAN_STEVE_STATUE = new ResourceLocation(MEMORIALS_LOCATION + "SteveStatue/Obsidian.png");
    public static final ResourceLocation MEMORIAL_QUARTZ_STEVE_STATUE = new ResourceLocation(MEMORIALS_LOCATION + "SteveStatue/Quartz.png");
    public static final ResourceLocation MEMORIAL_PRIZMARINE_STEVE_STATUE = new ResourceLocation(MEMORIALS_LOCATION + "SteveStatue/Prizmarine.png");
    public static final ResourceLocation MEMORIAL_ICE_STEVE_STATUE = new ResourceLocation(MEMORIALS_LOCATION + "SteveStatue/Ice.png");
    public static final ResourceLocation MEMORIAL_MOSSY_STEVE_STATUE = new ResourceLocation(MEMORIALS_LOCATION + "SteveStatue/Mossy.png");
    // angels memorials
    public static final ResourceLocation MEMORIAL_WOODEN_ANGEL_STATUE = new ResourceLocation(MEMORIALS_LOCATION + "AngelStatue/Wooden.png");
    public static final ResourceLocation MEMORIAL_SANDSTONE_ANGEL_STATUE = new ResourceLocation(MEMORIALS_LOCATION + "AngelStatue/Sandstone.png");
    public static final ResourceLocation MEMORIAL_RED_SANDSTONE_ANGEL_STATUE = new ResourceLocation(MEMORIALS_LOCATION + "AngelStatue/RedSandstone.png");
    public static final ResourceLocation MEMORIAL_STONE_ANGEL_STATUE = new ResourceLocation(MEMORIALS_LOCATION + "AngelStatue/Stone.png");
    public static final ResourceLocation MEMORIAL_DIORITE_ANGEL_STATUE = new ResourceLocation(MEMORIALS_LOCATION + "AngelStatue/Diorite.png");
    public static final ResourceLocation MEMORIAL_ANDESITE_ANGEL_STATUE = new ResourceLocation(MEMORIALS_LOCATION + "AngelStatue/Andesite.png");
    public static final ResourceLocation MEMORIAL_GRANITE_ANGEL_STATUE = new ResourceLocation(MEMORIALS_LOCATION + "AngelStatue/Granite.png");
    public static final ResourceLocation MEMORIAL_IRON_ANGEL_STATUE = new ResourceLocation(MEMORIALS_LOCATION + "AngelStatue/Iron.png");
    public static final ResourceLocation MEMORIAL_GOLDEN_ANGEL_STATUE = new ResourceLocation(MEMORIALS_LOCATION + "AngelStatue/Golden.png");
    public static final ResourceLocation MEMORIAL_DIAMOND_ANGEL_STATUE = new ResourceLocation(MEMORIALS_LOCATION + "AngelStatue/Diamond.png");
    public static final ResourceLocation MEMORIAL_EMERALD_ANGEL_STATUE = new ResourceLocation(MEMORIALS_LOCATION + "AngelStatue/Emerald.png");
    public static final ResourceLocation MEMORIAL_LAPIS_ANGEL_STATUE = new ResourceLocation(MEMORIALS_LOCATION + "AngelStatue/Lapis.png");
    public static final ResourceLocation MEMORIAL_REDSTONE_ANGEL_STATUE = new ResourceLocation(MEMORIALS_LOCATION + "AngelStatue/Redstone.png");
    public static final ResourceLocation MEMORIAL_OBSIDIAN_ANGEL_STATUE = new ResourceLocation(MEMORIALS_LOCATION + "AngelStatue/Obsidian.png");
    public static final ResourceLocation MEMORIAL_QUARTZ_ANGEL_STATUE = new ResourceLocation(MEMORIALS_LOCATION + "AngelStatue/Quartz.png");
    public static final ResourceLocation MEMORIAL_PRIZMARINE_ANGEL_STATUE = new ResourceLocation(MEMORIALS_LOCATION + "AngelStatue/Prizmarine.png");
    public static final ResourceLocation MEMORIAL_ICE_ANGEL_STATUE = new ResourceLocation(MEMORIALS_LOCATION + "AngelStatue/Ice.png");
    public static final ResourceLocation MEMORIAL_MOSSY_ANGEL_STATUE = new ResourceLocation(MEMORIALS_LOCATION + "AngelStatue/Mossy.png");

    // models - txecution
    public static final ResourceLocation GALLOWS = new ResourceLocation(EXECUTIONS_LOCATION + "Gallows.png");
    public static final ResourceLocation GIBBET = new ResourceLocation(EXECUTIONS_LOCATION + "Gibbet.png");
    public static final ResourceLocation STOCKS = new ResourceLocation(EXECUTIONS_LOCATION + "Stocks.png");
    public static final ResourceLocation BURNING_STAKE = new ResourceLocation(EXECUTIONS_LOCATION + "BurningStake.png");

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
