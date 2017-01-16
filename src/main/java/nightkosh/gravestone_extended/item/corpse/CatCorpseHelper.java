package nightkosh.gravestone_extended.item.corpse;

import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import nightkosh.gravestone_extended.ModGravestoneExtended;
import nightkosh.gravestone_extended.block.enums.EnumCorpse;
import nightkosh.gravestone_extended.core.GSBlock;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class CatCorpseHelper extends CorpseHelper {

    private CatCorpseHelper() {
    }

    private static final int CORPSE_TYPE = EnumCorpse.CAT.ordinal();

    public static enum EnumCatType {
        OCELOT,
        BLACK,
        RED,
        SIAMESE
    }

    public static ItemStack getRandomCorpse(Random random) {
        return getDefaultCatCorpse(EnumCatType.values()[random.nextInt(EnumCatType.values().length)]);
    }

    public static List<ItemStack> getDefaultCorpses() {
        List<ItemStack> list = new ArrayList<>();

        list.add(getDefaultCatCorpse(EnumCatType.OCELOT));
        list.add(getDefaultCatCorpse(EnumCatType.BLACK));
        list.add(getDefaultCatCorpse(EnumCatType.RED));
        list.add(getDefaultCatCorpse(EnumCatType.SIAMESE));
        return list;
    }

    private static ItemStack getDefaultCatCorpse(EnumCatType catType) {
        ItemStack corpse = new ItemStack(GSBlock.corpse, 1, CORPSE_TYPE);
        NBTTagCompound nbtTag = new NBTTagCompound();

        nbtTag.setByte("CatType", (byte) catType.ordinal());

        corpse.setTagCompound(nbtTag);
        return corpse;
    }

    public static void setNbt(EntityOcelot cat, NBTTagCompound nbt) {
        setName(cat, nbt);
        nbt.setByte("CatType", (byte) cat.getTameSkin());
    }

    public static byte getCatType(NBTTagCompound nbtTag) {
        return nbtTag.getByte("CatType");
    }

    public static void spawnCat(World world, int x, int y, int z, NBTTagCompound nbtTag, EntityPlayer player) {
        EntityOcelot cat = new EntityOcelot(world);
        setMobName(cat, nbtTag);

        cat.setTamed(true);
        cat.setTameSkin(getCatType(nbtTag));
        cat.setOwnerId(player.getUniqueID().toString());
        world.setEntityState(cat, (byte) 7);
        spawnMob(cat, world, x, y, z);
    }

    public static void addInfo(List list, NBTTagCompound nbtTag) {
        addNameInfo(list, nbtTag);
        if (hasType(nbtTag)) {
            list.add(getType(nbtTag));
        }
    }

    private static boolean hasType(NBTTagCompound nbtTag) {
        return nbtTag.hasKey("CatType");
    }

    private static String getType(NBTTagCompound nbtTag) {
        return ModGravestoneExtended.proxy.getLocalizedString("item.corpse.cat_type") + " " +
                ModGravestoneExtended.proxy.getLocalizedString(getCatType(nbtTag.getByte("CatType")));
    }

    private static String getCatType(int type) {
        switch (type) {
            case 0:
                return "item.corpse.cat_type.ocelot";
            case 1:
                return "item.corpse.cat_type.black";
            case 2:
                return "item.corpse.cat_type.red";
            case 3:
                return "item.corpse.cat_type.siamese";
            default:
                return "item.corpse.cat_type.unknown";
        }
    }
}
