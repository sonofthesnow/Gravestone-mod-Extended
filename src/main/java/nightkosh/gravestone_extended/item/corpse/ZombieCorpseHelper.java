package nightkosh.gravestone_extended.item.corpse;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import nightkosh.gravestone_extended.block.enums.EnumCorpse;
import nightkosh.gravestone_extended.core.GSBlock;
import nightkosh.gravestone_extended.entity.monster.pet.EnumUndeadMobType;

import java.util.ArrayList;
import java.util.List;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class ZombieCorpseHelper extends CorpseHelper {

    public static ItemStack getDefaultCorpse() {
        return createCorpse(EnumUndeadMobType.ZOMBIE);
    }

    public static List<ItemStack> getDefaultCorpses() {
        List<ItemStack> list = new ArrayList<>();
        list.add(createCorpse(EnumUndeadMobType.ZOMBIE));
        list.add(createCorpse(EnumUndeadMobType.HUSK));
        list.add(createCorpse(EnumUndeadMobType.PIGMAN));

        return list;
    }

    private static ItemStack createCorpse(EnumUndeadMobType mobType) {
        ItemStack corpse = new ItemStack(GSBlock.CORPSE, 1, EnumCorpse.ZOMBIE.ordinal());
        NBTTagCompound nbtTag = new NBTTagCompound();
        nbtTag.setByte("MobType", (byte) mobType.ordinal());
        corpse.setTagCompound(nbtTag);

        return corpse;
    }
}
