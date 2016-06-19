package nightkosh.gravestone_extended.helper;

import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
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


    public static void createPetGrave(Entity entity, LivingDeathEvent event, long spawnTime) {
        EntityTameable pet = (EntityTameable) entity;

        if (pet.isTamed()) {
            if (pet instanceof EntityWolf) {//TODO
                createGrave(entity, event, CorpseHelper.getCorpse(entity, EnumCorpse.DOG), EnumGraveTypeByEntity.DOGS_GRAVES, false, spawnTime);
            } else if (pet instanceof EntityOcelot) {
                createGrave(entity, event, CorpseHelper.getCorpse(entity, EnumCorpse.CAT), EnumGraveTypeByEntity.CATS_GRAVES, false, spawnTime);
            }
        }
    }


    public static void createHorseGrave(EntityHorse horse, LivingDeathEvent event, long spawnTime) {
            //items.addAll(CorpseHelper.getCorpse(horse, EnumCorpse.HORSE));//TODO
    }



//    private static EnumGraveMaterial getGraveMaterialByAge(Entity entity, int age, EnumGraveTypeByEntity graveTypeByEntity) {
//        return null;//TODO !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
//    }

}
