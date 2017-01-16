package nightkosh.gravestone_extended.helper;

import nightkosh.gravestone.api.GraveStoneAPI;
import nightkosh.gravestone_extended.item.corpse.CorpseHelper;
import nightkosh.gravestone_extended.block.enums.EnumCorpse;

import java.util.Random;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class GraveGenerationHelper extends nightkosh.gravestone.helper.GraveGenerationHelper {

    protected static final Random rand = new Random();

    protected static EnumGraveTypeByEntity getRandomGraveType(Random random) {
        if (random.nextInt(5) < 4) {
            return getRandomHumanGraveType(random);//20%
        } else {
            return getRandomPetGraveType(random);//80%
        }
    }

    protected static EnumGraveTypeByEntity getRandomHumanGraveType(Random random) {
        return random.nextBoolean() ? EnumGraveTypeByEntity.PLAYER_GRAVES : EnumGraveTypeByEntity.VILLAGERS_GRAVES;
    }

    protected static EnumGraveTypeByEntity getRandomPetGraveType(Random random) {
        if (random.nextInt(5) == 0) {
            return EnumGraveTypeByEntity.HORSE_GRAVES;//20%
        } else if (random.nextBoolean()) {
            return EnumGraveTypeByEntity.DOGS_GRAVES;//40%
        } else {
            return EnumGraveTypeByEntity.CATS_GRAVES;//40%
        }
    }

    public static void addMobsItemsHandlers() {
        GraveStoneAPI.graveGenerationAtDeath.addVillagerItemsHandler((villager, source) -> CorpseHelper.getCorpse(villager, EnumCorpse.VILLAGER));
        GraveStoneAPI.graveGenerationAtDeath.addDogItemsHandler((dog, source) -> CorpseHelper.getCorpse(dog, EnumCorpse.DOG));
        GraveStoneAPI.graveGenerationAtDeath.addCatItemsHandler((cat, source) -> CorpseHelper.getCorpse(cat, EnumCorpse.CAT));
        GraveStoneAPI.graveGenerationAtDeath.addHorseItemsHandler((horse, source) -> CorpseHelper.getCorpse(horse, EnumCorpse.HORSE));
    }
}
