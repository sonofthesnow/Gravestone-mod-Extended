package nightkosh.gravestone_extended.helper;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentData;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import nightkosh.gravestone.helper.GraveGenerationHelper.EnumGraveTypeByEntity;
import nightkosh.gravestone_extended.core.Potion;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class GraveInventoryHelper {
    private static final int[] POTION_LIST = {
            Potion.REGENERATION_POTION_ID, Potion.SWIFTNESS_POTION_ID, Potion.FIRE_RESISTANCE_POTION_ID,
            Potion.HEALING_POTION_ID, Potion.NIGHT_VISION_POTION_ID, Potion.STRENGTH_POTION_ID,
            Potion.INVISIBILITY_POTION_ID, Potion.WATER_BREATHING_POTION_ID, Potion.LEAPING_POTION_ID,
            Potion.SPLASH_REGENERATION_POTION_ID, Potion.SPLASH_SWIFTNESS_POTION_ID, Potion.SPLASH_FIRE_RESISTANCE_POTION_ID,
            Potion.SPLASH_HEALING_POTION_ID, Potion.SPLASH_NIGHT_VISION_POTION_ID, Potion.SPLASH_STRENGTH_POTION_ID,
            Potion.SPLASH_INVISIBILITY_POTION_ID, Potion.SPLASH_WATER_BREATHING_POTION_ID, Potion.SPLASH_LEAPING_POTION_ID
    };

    public static enum GraveCorpseContentType {
        EMPTY,
        CORPSE,
        BONES_AND_FLESH,
        SKULL_BONES_AND_FLESH,
        RANDOM
    }

    private static GraveCorpseContentType getRandomCorpseContentType(ContentMaterials contentMaterials, Random random) {
        switch (contentMaterials) {
            case DIAMOND:
            case EMERALD:
                return random.nextBoolean() ? GraveCorpseContentType.CORPSE : GraveCorpseContentType.SKULL_BONES_AND_FLESH;//50%
            case GOLDEN:
            case REDSTONE:
            case QUARTZ:
            case LAPIS:
                if (random.nextInt(10) < 5) {
                    return GraveCorpseContentType.BONES_AND_FLESH;//50%
                } else {
                    return random.nextBoolean() ? GraveCorpseContentType.CORPSE : GraveCorpseContentType.SKULL_BONES_AND_FLESH;//25%
                }
            case IRON:
            case CHAINMAIL:
                if (random.nextInt(10) < 7) {
                    return GraveCorpseContentType.BONES_AND_FLESH;//70%
                } else {
                    return random.nextBoolean() ? GraveCorpseContentType.CORPSE : GraveCorpseContentType.SKULL_BONES_AND_FLESH;//15%
                }
            default:
            case OTHER:
                if (random.nextInt(10) < 8) {
                    return GraveCorpseContentType.BONES_AND_FLESH;//80%
                } else {
                    return random.nextBoolean() ? GraveCorpseContentType.CORPSE : GraveCorpseContentType.SKULL_BONES_AND_FLESH;//10%
                }
        }
    }

    private static void addCorpse(GraveContentType contentType, Random random, List<ItemStack> itemList) {
        switch (contentType) {
            case DOG://TODO !!!!!!!!!!
                break;
            case CAT://TODO !!!!!!!!!!
                break;
            case HORSE://TODO !!!!!!!!!!
                break;
            default://TODO !!!!!!!!!!
                break;
        }
    }

    private static void addBonesAndFlesh(Random random, List<ItemStack> itemList) {
        itemList.add(new ItemStack(Items.bone, 1 + random.nextInt(5), 0));
        itemList.add(new ItemStack(Items.rotten_flesh, 1 + random.nextInt(5), 0));
    }

    private static void addSkull(Random random, List<ItemStack> itemList) {
        if (random.nextBoolean()) {
            itemList.add(new ItemStack(Items.skull, 1, 0));//SKELETON
        } else {
            itemList.add(new ItemStack(Items.skull, 1, 2));//ZOMBIE
        }
    }

    public static enum GraveContentType {
        JUNK,
        WORKER,
        MINER,
        WIZARD,
        WARRIOR,
        ADVENTURER,
        TREASURY,
        RANDOM,
        DOG,
        CAT,
        HORSE,
        OTHER
    }

    public static enum ContentMaterials {
        OTHER,
        IRON,
        GOLDEN,
        DIAMOND,
        EMERALD,
        REDSTONE,
        QUARTZ,
        LAPIS,
        CHAINMAIL
    }

    public static GraveContentType getRandomContentType(EnumGraveTypeByEntity graveTypeByEntity, Random random) {
        switch (graveTypeByEntity) {
            case PLAYER_GRAVES:
            case VILLAGERS_GRAVES:
                int chance = random.nextInt(100);
                if (chance < 35) {
                    return GraveContentType.JUNK;//35%
                } else if (chance < 55) {
                    return GraveContentType.WORKER;//20%
                } else if (chance < 70) {
                    return GraveContentType.MINER;//15%
                } else if (chance < 85) {
                    return GraveContentType.WIZARD;//15%
                } else if (chance < 95) {
                    return GraveContentType.ADVENTURER;//10%
                } else {
                    return GraveContentType.WARRIOR;//5%
                }
            case DOGS_GRAVES:
                return GraveContentType.DOG;
            case CATS_GRAVES:
                return GraveContentType.CAT;
            case HORSE_GRAVES:
                return GraveContentType.HORSE;
            default:
                return GraveContentType.OTHER;
        }
    }

    public static ContentMaterials getContentMaterial(GraveContentType contentType, Random random) {
        switch (contentType) {
            case WORKER:
                return getWorkerContentType(random);
            case MINER:
                return getMinerContentType(random);
            case WIZARD:
                return getWizardContentType(random);
            case WARRIOR:
                return getWarriorContentType(random);
            case ADVENTURER:
                return getAdventureContentType(random);
            case TREASURY:
                return ContentMaterials.OTHER; //TODO!!!
            default:
            case DOG://TODO
            case CAT://TODO
            case HORSE://TODO
            case JUNK:
            case OTHER:
                return ContentMaterials.OTHER;
        }
    }

    private static ContentMaterials getWarriorContentType(Random random) {
        int chance = random.nextInt(100);
        if (chance < 5) {
            return ContentMaterials.DIAMOND;//5%
        } else if (chance < 20) {
            return ContentMaterials.GOLDEN;//15%
        } else if (chance < 40) {
            return ContentMaterials.CHAINMAIL;//20% CHAINMAIL
        } else if (chance < 65) {
            return ContentMaterials.IRON;//25%
        } else {
            return ContentMaterials.OTHER;//35% LEATHER
        }
    }

    private static void fillWarriorGrave(Random random, List<ItemStack> itemList, ContentMaterials materials) {
        switch (materials) {
            case OTHER:
                if (random.nextBoolean()) {
                    itemList.add(new ItemStack(Items.leather_chestplate, 1, getRandomDamage(random, 30)));
                }
                if (random.nextBoolean()) {
                    itemList.add(new ItemStack(Items.leather_leggings, 1, getRandomDamage(random, 30)));
                }
                if (random.nextBoolean()) {
                    itemList.add(new ItemStack(Items.leather_helmet, 1, getRandomDamage(random, 30)));
                }
                if (random.nextBoolean()) {
                    itemList.add(new ItemStack(Items.leather_boots, 1, getRandomDamage(random, 30)));
                }
                break;
            case IRON:
                if (random.nextBoolean()) {
                    itemList.add(new ItemStack(Items.iron_chestplate, 1, getRandomDamage(random)));
                }
                if (random.nextBoolean()) {
                    itemList.add(new ItemStack(Items.iron_leggings, 1, getRandomDamage(random)));
                }
                if (random.nextBoolean()) {
                    itemList.add(new ItemStack(Items.iron_helmet, 1, getRandomDamage(random)));
                }
                if (random.nextBoolean()) {
                    itemList.add(new ItemStack(Items.iron_boots, 1, getRandomDamage(random)));
                }
                break;
            case CHAINMAIL:
                if (random.nextBoolean()) {
                    itemList.add(new ItemStack(Items.chainmail_chestplate, 1, getRandomDamage(random)));
                }
                if (random.nextBoolean()) {
                    itemList.add(new ItemStack(Items.chainmail_leggings, 1, getRandomDamage(random)));
                }
                if (random.nextBoolean()) {
                    itemList.add(new ItemStack(Items.chainmail_helmet, 1, getRandomDamage(random)));
                }
                if (random.nextBoolean()) {
                    itemList.add(new ItemStack(Items.chainmail_boots, 1, getRandomDamage(random)));
                }
                break;
            case GOLDEN:
                if (random.nextBoolean()) {
                    itemList.add(new ItemStack(Items.golden_chestplate, 1, getRandomDamage(random, 50)));
                }
                if (random.nextBoolean()) {
                    itemList.add(new ItemStack(Items.golden_leggings, 1, getRandomDamage(random, 50)));
                }
                if (random.nextBoolean()) {
                    itemList.add(new ItemStack(Items.golden_helmet, 1, getRandomDamage(random, 30)));
                }
                if (random.nextBoolean()) {
                    itemList.add(new ItemStack(Items.golden_boots, 1, getRandomDamage(random, 40)));
                }
                break;
            case DIAMOND:
                if (random.nextBoolean()) {
                    itemList.add(new ItemStack(Items.diamond_chestplate, 1, getRandomDamage(random)));
                }
                if (random.nextBoolean()) {
                    itemList.add(new ItemStack(Items.diamond_leggings, 1, getRandomDamage(random)));
                }
                if (random.nextBoolean()) {
                    itemList.add(new ItemStack(Items.diamond_helmet, 1, getRandomDamage(random)));
                }
                if (random.nextBoolean()) {
                    itemList.add(new ItemStack(Items.diamond_boots, 1, getRandomDamage(random)));
                }
                break;
        }

        if (random.nextInt(3) == 0) {
            itemList.add(new ItemStack(Items.bow, 1, getRandomDamage(random)));
            itemList.add(new ItemStack(Items.arrow, 10 + random.nextInt(54), 0));
        }
    }

    public static ItemStack getWarriorSword(ContentMaterials contentMaterial, Random random) {
        Item sword;
        switch (contentMaterial) {
            case IRON:
            case CHAINMAIL:
                sword = Items.iron_sword;
                break;
            case GOLDEN:
                sword = Items.golden_sword;
                break;
            case DIAMOND:
                sword = Items.diamond_sword;
                break;
            default:
            case OTHER:
                sword = random.nextBoolean() ? Items.stone_sword : Items.wooden_sword;
                break;
        }
        return new ItemStack(sword, 1, getRandomDamage(random, 30));
    }

    private static ContentMaterials getMinerContentType(Random random) {
        int chance = random.nextInt(100);
        if (chance < 10) {
            return ContentMaterials.DIAMOND;//10%
        } else if (chance < 30) {
            return ContentMaterials.GOLDEN;//20%
        } else if (chance < 60) {
            return ContentMaterials.IRON;//30%
        } else {
            return ContentMaterials.OTHER;//40%
        }
    }

    private static void fillMinerGrave(Random random, List<ItemStack> itemList, ContentMaterials materials) {
        switch (materials) {
            case IRON:
                itemList.add(new ItemStack(Items.iron_pickaxe, 1, getRandomDamage(random)));
                break;
            case GOLDEN:
                itemList.add(new ItemStack(Items.golden_pickaxe, 1, getRandomDamage(random, 15)));
                break;
            case DIAMOND:
                itemList.add(new ItemStack(Items.diamond_pickaxe, 1, getRandomDamage(random)));
                break;
            default:
                itemList.add(new ItemStack(Items.stone_pickaxe, 1, getRandomDamage(random, 30)));
                break;
        }

        switch (random.nextInt(10)) {
            case 0:
                itemList.add(new ItemStack(Items.diamond, 1 + random.nextInt(3), 0));
                break;
            case 1:
                itemList.add(new ItemStack(Items.emerald, 1 + random.nextInt(3), 0));
                break;
        }
        switch (random.nextInt(5)) {
            case 0:
                itemList.add(new ItemStack(Items.gold_ingot, 3 + random.nextInt(5), 0));
                break;
            case 1:
            case 2:
                itemList.add(new ItemStack(Items.iron_ingot, 3 + random.nextInt(5), 0));
                break;
        }
        switch (random.nextInt(5)) {
            case 0:
                itemList.add(new ItemStack(Items.redstone, 3 + random.nextInt(8), 0));
                break;
            case 1:
                itemList.add(new ItemStack(Items.dye, 3 + random.nextInt(8), 4));
                break;
        }
    }

    private static ContentMaterials getWizardContentType(Random random) {
        int chance = random.nextInt(10);
        if (chance < 1) {
            return ContentMaterials.REDSTONE;//10%
        } else if (chance < 2) {
            return ContentMaterials.QUARTZ;//10%
        } else if (chance < 4) {
            return ContentMaterials.LAPIS;//20%
        } else {
            return ContentMaterials.OTHER;//60%
        }
    }

    private static void fillWizardGrave(Random random, List<ItemStack> itemList, ContentMaterials contentMaterials) {
        switch (contentMaterials) {
            case REDSTONE: // enchanted book
                EnchantmentData data = new EnchantmentData(Enchantment.enchantmentsBookList[random.nextInt(Enchantment.enchantmentsBookList.length)], 1 + random.nextInt(5));
                ItemStack items = Items.enchanted_book.getEnchantedItemStack(data);
                itemList.add(items);
                break;
            case QUARTZ:
                itemList.add(new ItemStack(Items.potionitem, 1 + random.nextInt(5), POTION_LIST[random.nextInt(POTION_LIST.length)]));
                break;
            case LAPIS:
                itemList.add(new ItemStack(Items.book, 3 + random.nextInt(8), 0));
                break;
        }
        switch (random.nextInt(15)) {
            case 0:
                itemList.add(new ItemStack(Items.ender_pearl, 1, 0));
                break;
            case 1:
                itemList.add(new ItemStack(Items.blaze_powder, 1, 0));
                break;
            case 2:
                itemList.add(new ItemStack(Items.glowstone_dust, 3 + random.nextInt(8), 0));
                break;
        }
        switch (random.nextInt(6)) {
            case 0:
                itemList.add(new ItemStack(Items.magma_cream, 1, 0));
                break;
            case 1:
                itemList.add(new ItemStack(Items.gunpowder, 1, 0));
                break;
        }
        switch (random.nextInt(10)) {
            case 0:
                itemList.add(new ItemStack(Items.ghast_tear, 1, 0));
                break;
            case 1:
                itemList.add(new ItemStack(Items.nether_wart, 1, 0));
                break;
        }
        switch (random.nextInt(5)) {
            case 0:
                itemList.add(new ItemStack(Items.spider_eye, 1, 0));
                break;
            case 1:
                itemList.add(new ItemStack(Items.fermented_spider_eye, 1, 0));
                break;
        }
        switch (random.nextInt(8)) {
            case 0:
                itemList.add(new ItemStack(Items.golden_carrot, 1, 0));
                break;
            case 1:
                itemList.add(new ItemStack(Items.speckled_melon, 1, 0));
                break;
        }
    }

    private static ContentMaterials getWorkerContentType(Random random) {
        int chance = random.nextInt(100);
        if (chance < 10) {
            return ContentMaterials.DIAMOND;//10%
        } else if (chance < 30) {
            return ContentMaterials.GOLDEN;//20%
        } else if (chance < 60) {
            return ContentMaterials.IRON;//30%
        } else {
            return ContentMaterials.OTHER;//40%
        }
    }

    private static void fillWorkerGrave(Random random, List<ItemStack> itemList, ContentMaterials materials) {
        switch (materials) {
            case IRON:
                if (random.nextBoolean()) {
                    itemList.add(new ItemStack(Items.iron_axe, 1, getRandomDamage(random)));
                } else {
                    itemList.add(new ItemStack(Items.iron_shovel, 1, getRandomDamage(random)));
                }
                break;
            case GOLDEN:
                if (random.nextBoolean()) {
                    itemList.add(new ItemStack(Items.golden_axe, 1, getRandomDamage(random, 15)));
                } else {
                    itemList.add(new ItemStack(Items.golden_shovel, 1, getRandomDamage(random, 15)));
                }
                break;
            case DIAMOND:
                if (random.nextBoolean()) {
                    itemList.add(new ItemStack(Items.diamond_axe, 1, getRandomDamage(random)));
                } else {
                    itemList.add(new ItemStack(Items.diamond_shovel, 1, getRandomDamage(random)));
                }
                break;
        }

        if (random.nextInt(8) == 0) {
            itemList.add(new ItemStack(Items.saddle, 1, 0));
        }
    }

    private static ContentMaterials getAdventureContentType(Random random) {
        if (random.nextInt(10) <= 2) {
            return ContentMaterials.EMERALD;//20%
        } else {
            return ContentMaterials.OTHER;//80%
        }
    }

    private static void fillAdventureGrave(Random random, List<ItemStack> itemList, ContentMaterials contentMaterials) {
        switch (random.nextInt(8)) {
            case 0:
                itemList.add(new ItemStack(Items.compass, 1, 0));
                break;
            case 1:
                itemList.add(new ItemStack(Items.clock, 1, 0));
                break;
            case 2:
                itemList.add(new ItemStack(Items.map, 1, 0));
                break;
        }

        switch (random.nextInt(10)) {
            case 0:
                itemList.add(new ItemStack(Items.painting, 1 + random.nextInt(5), 0));
                break;
            case 1:
                itemList.add(getRandomRecord(random));
                break;
            case 2:
                itemList.add(new ItemStack(Items.writable_book, 1, 0));
                break;
        }

        if (random.nextInt(4) == 0) {
            itemList.add(new ItemStack(Items.stick, 3 + random.nextInt(9), 0));
        }

        if (random.nextInt(5) == 0) {
            itemList.add(new ItemStack(Items.cookie, 3 + random.nextInt(5), 0));
        }

        if (contentMaterials == ContentMaterials.EMERALD) {
            itemList.add(getRandomEgg(random));
        }
    }

    private static void fillPetGrave(Random random, List<ItemStack> itemList) {
        if (random.nextInt(10) == 0) {
            itemList.add(new ItemStack(Items.lead, 1, 0));
            //            if (Arrays.asList(GraveStoneHelper.DOGS_GRAVES).contains(tileEntity.getGraveType())) {
            ////                tileEntity.setGraveType(GraveStoneHelper.getRandomGrave(Arrays.asList(GraveStoneHelper.DOG_GOLDEN_GRAVES), random));//TODO
            //            } else if (Arrays.asList(GraveStoneHelper.CATS_GRAVES).contains(tileEntity.getGraveType())) {
            ////                tileEntity.setGraveType(GraveStoneHelper.getRandomGrave(Arrays.asList(GraveStoneHelper.CAT_GOLDEN_GRAVES), random));//TODO
            //            }
        }

        if (random.nextInt(10) == 0) {
            itemList.add(new ItemStack(Items.name_tag, 1, 0));
            //            if (Arrays.asList(GraveStoneHelper.DOGS_GRAVES).contains(tileEntity.getGraveType())) {
            ////                tileEntity.setGraveType(GraveStoneHelper.getRandomGrave(Arrays.asList(GraveStoneHelper.DOG_DIAMOND_GRAVES), random));//TODO
            //            } else if (Arrays.asList(GraveStoneHelper.CATS_GRAVES).contains(tileEntity.getGraveType())) {
            ////                tileEntity.setGraveType(GraveStoneHelper.getRandomGrave(Arrays.asList(GraveStoneHelper.CAT_DIAMOND_GRAVES), random));//TODO
            //            }
        }
    }

    private static int getRandomDamage(Random random) {
        return 20 + random.nextInt(100);
    }

    private static int getRandomDamage(Random random, int maxDamage) {
        return random.nextInt(maxDamage);
    }

    private static ItemStack getRandomRecord(Random random) {
        switch (random.nextInt(13)) {
            case 1:
                return new ItemStack(Items.record_cat, 1, 0);
            case 2:
                return new ItemStack(Items.record_blocks, 1, 0);
            case 3:
                return new ItemStack(Items.record_chirp, 1, 0);
            case 4:
                return new ItemStack(Items.record_far, 1, 0);
            case 5:
                return new ItemStack(Items.record_mall, 1, 0);
            case 6:
                return new ItemStack(Items.record_mellohi, 1, 0);
            case 7:
                return new ItemStack(Items.record_stal, 1, 0);
            case 8:
                return new ItemStack(Items.record_strad, 1, 0);
            case 9:
                return new ItemStack(Items.record_ward, 1, 0);
            case 10:
                return new ItemStack(Items.record_11, 1, 0);
            case 11:
                return new ItemStack(Items.record_wait, 1, 0);
            case 12:
                return new ItemStack(Items.record_13, 1, 0);
            case 0:
            default:
                return new ItemStack(Items.record_cat, 1, 0);
        }
    }

    private static final int EGG_PIG = 90;
    private static final int EGG_SHEEP = 91;
    private static final int EGG_COW = 92;
    private static final int EGG_CHICKEN = 93;
    private static final int EGG_SQUID = 94;
    private static final int EGG_WOLF = 95;
    private static final int EGG_MUSHROOM_COW = 96;
    private static final int EGG_CAT = 98;
    private static final int EGG_HORSE = 100;
    private static final int EGG_VILLAGER = 120;

    private static ItemStack getRandomEgg(Random random) {
        switch (random.nextInt(11)) {
            case 1:
                return new ItemStack(Items.spawn_egg, 1, EGG_PIG);
            case 2:
                return new ItemStack(Items.spawn_egg, 1, EGG_SHEEP);
            case 3:
                return new ItemStack(Items.spawn_egg, 1, EGG_COW);
            case 4:
                return new ItemStack(Items.spawn_egg, 1, EGG_CHICKEN);
            case 5:
                return new ItemStack(Items.spawn_egg, 1, EGG_SQUID);
            case 6:
                return new ItemStack(Items.spawn_egg, 1, EGG_WOLF);
            case 7:
                return new ItemStack(Items.spawn_egg, 1, EGG_MUSHROOM_COW);
            case 8:
                return new ItemStack(Items.spawn_egg, 1, EGG_CAT);
            case 9:
                return new ItemStack(Items.spawn_egg, 1, EGG_HORSE);
            case 10:
                return new ItemStack(Items.spawn_egg, 1, EGG_VILLAGER);
            case 0:
            default:
                return new ItemStack(Items.spawn_egg, 1, 120);
        }
    }

    public static List<ItemStack> getRandomGraveContent(Random random, GraveGenerationHelper.EnumGraveTypeByEntity graveTypeByEntity, GraveContentType contentType,
                                                        GraveCorpseContentType corpseType, ContentMaterials contentMaterials) {
        List<ItemStack> itemList = new ArrayList<>();
        if (corpseType == GraveInventoryHelper.GraveCorpseContentType.RANDOM) {
            corpseType = GraveInventoryHelper.getRandomCorpseContentType(contentMaterials, random);
        }
        switch (corpseType) {
            case CORPSE:
                GraveInventoryHelper.addCorpse(contentType, random, itemList);
                break;
            case BONES_AND_FLESH:
                GraveInventoryHelper.addBonesAndFlesh(random, itemList);
                break;
            case SKULL_BONES_AND_FLESH:
                GraveInventoryHelper.addSkull(random, itemList);
                GraveInventoryHelper.addBonesAndFlesh(random, itemList);
                break;
        }

        if (contentType != GraveContentType.JUNK) {
            if (graveTypeByEntity == GraveGenerationHelper.EnumGraveTypeByEntity.DOGS_GRAVES ||
                    graveTypeByEntity == GraveGenerationHelper.EnumGraveTypeByEntity.CATS_GRAVES ||
                    graveTypeByEntity == GraveGenerationHelper.EnumGraveTypeByEntity.HORSE_GRAVES) {
                GraveInventoryHelper.fillPetGrave(random, itemList);
            } else {
                switch (contentType) {
                    case WORKER:
                        GraveInventoryHelper.fillWorkerGrave(random, itemList, contentMaterials);
                        break;
                    case MINER:
                        GraveInventoryHelper.fillMinerGrave(random, itemList, contentMaterials);
                        break;
                    case WIZARD:
                        GraveInventoryHelper.fillWizardGrave(random, itemList, contentMaterials);
                        break;
                    case WARRIOR:
                        GraveInventoryHelper.fillWarriorGrave(random, itemList, contentMaterials);
                        break;
                    case ADVENTURER:
                        GraveInventoryHelper.fillAdventureGrave(random, itemList, contentMaterials);
                        break;
                    case TREASURY:
                        //TODO
                        break;
                }
            }
        }
        return itemList;
    }
}
