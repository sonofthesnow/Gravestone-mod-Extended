package nightkosh.gravestone_extended.helper;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

import java.util.Random;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class EggHelper {
    private static final String EGG_PIG_ID = "Pig";
    private static final String EGG_SHEEP_ID = "Sheep";
    private static final String EGG_COW_ID = "Cow";
    private static final String EGG_CHICKEN_ID = "Chicken";
    private static final String EGG_SQUID_ID = "Squid";
    private static final String EGG_WOLF_ID = "Wolf";
    private static final String EGG_MUSHROOM_COW_ID = "MushroomCow";
    private static final String EGG_CAT_ID = "Ozelot";
    private static final String EGG_HORSE_ID = "EntityHorse";
    private static final String EGG_RABBIT_ID = "Rabbit";
    private static final String EGG_VILLAGER_ID = "Villager";

    public static final ItemStack EGG_PIG = applyEntityIdToItemStack(EGG_PIG_ID);
    public static final ItemStack EGG_SHEEP = applyEntityIdToItemStack(EGG_SHEEP_ID);
    public static final ItemStack EGG_COW = applyEntityIdToItemStack(EGG_COW_ID);
    public static final ItemStack EGG_CHICKEN = applyEntityIdToItemStack(EGG_CHICKEN_ID);
    public static final ItemStack EGG_SQUID = applyEntityIdToItemStack(EGG_SQUID_ID);
    public static final ItemStack EGG_WOLF = applyEntityIdToItemStack(EGG_WOLF_ID);
    public static final ItemStack EGG_MUSHROOM_COW = applyEntityIdToItemStack(EGG_MUSHROOM_COW_ID);
    public static final ItemStack EGG_CAT = applyEntityIdToItemStack(EGG_CAT_ID);
    public static final ItemStack EGG_HORSE = applyEntityIdToItemStack(EGG_HORSE_ID);
    public static final ItemStack EGG_RABBIT = applyEntityIdToItemStack(EGG_RABBIT_ID);
    public static final ItemStack EGG_VILLAGER = applyEntityIdToItemStack(EGG_VILLAGER_ID);

    private static ItemStack applyEntityIdToItemStack(String entityId) {
        ItemStack egg = new ItemStack(Items.SPAWN_EGG, 1);
        NBTTagCompound nbttagcompound = egg.hasTagCompound() ? egg.getTagCompound() : new NBTTagCompound();
        NBTTagCompound entityTag = new NBTTagCompound();
        entityTag.setString("id", entityId);
        nbttagcompound.setTag("EntityTag", entityTag);
        egg.setTagCompound(nbttagcompound);
        return egg;
    }

    public static ItemStack getRandomEgg(Random random) {
        switch (random.nextInt(11)) {
            case 1:
                return EGG_PIG.copy();
            case 2:
                return EGG_SHEEP.copy();
            case 3:
                return EGG_COW.copy();
            case 4:
                return EGG_CHICKEN.copy();
            case 5:
                return EGG_SQUID.copy();
            case 6:
                return EGG_VILLAGER.copy();
            case 7:
                return EGG_MUSHROOM_COW.copy();
            case 8:
                return EGG_CAT.copy();
            case 9:
                return EGG_HORSE.copy();
            case 10:
                return EGG_RABBIT.copy();
            case 0:
            default:
                return EGG_WOLF.copy();
        }
    }
}
