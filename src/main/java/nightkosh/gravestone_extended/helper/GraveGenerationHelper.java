package nightkosh.gravestone_extended.helper;

import nightkosh.gravestone.api.GraveStoneAPI;
import nightkosh.gravestone_extended.item.corpse.CorpseHelper;
import nightkosh.gravestone_extended.item.enums.EnumCorpse;

import java.util.Random;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class GraveGenerationHelper extends nightkosh.gravestone.helper.GraveGenerationHelper{

    protected static final Random rand = new Random();

    protected static EnumGraveTypeByEntity getRandomGraveType(Random random) {
        if (random.nextInt(5) < 4) {
            return getRandomHumanGraveType(random);
        } else {
            return getRandomPetGraveType(random);
        }
    }

    protected static EnumGraveTypeByEntity getRandomHumanGraveType(Random random) {
        return random.nextBoolean() ? EnumGraveTypeByEntity.PLAYER_GRAVES : EnumGraveTypeByEntity.VILLAGERS_GRAVES;
    }

    protected static EnumGraveTypeByEntity getRandomPetGraveType(Random random) {
        switch (random.nextInt(3)) {
            case 0:
            default:
                return EnumGraveTypeByEntity.DOGS_GRAVES;
            case 1:
                return EnumGraveTypeByEntity.CATS_GRAVES;
            case 2:
                return EnumGraveTypeByEntity.HORSE_GRAVES;
        }
    }

    public static void addMobsItemsHandlers() {
        GraveStoneAPI.graveGenerationAtDeath.addVillagerItemsHandler((villager, source) -> CorpseHelper.getCorpse(villager, EnumCorpse.VILLAGER));
        GraveStoneAPI.graveGenerationAtDeath.addDogItemsHandler((dog, source) -> CorpseHelper.getCorpse(dog, EnumCorpse.DOG));
        GraveStoneAPI.graveGenerationAtDeath.addCatItemsHandler((cat, source) -> CorpseHelper.getCorpse(cat, EnumCorpse.CAT));
        GraveStoneAPI.graveGenerationAtDeath.addHorseItemsHandler((horse, source) -> CorpseHelper.getCorpse(horse, EnumCorpse.HORSE));
    }
}
