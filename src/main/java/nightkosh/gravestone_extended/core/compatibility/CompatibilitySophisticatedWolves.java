package nightkosh.gravestone_extended.core.compatibility;

import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import sophisticated_wolves.api.EnumWolfSpecies;
import sophisticated_wolves.api.ISophisticatedWolf;
import sophisticated_wolves.api.SophisticatedWolvesAPI;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class CompatibilitySophisticatedWolves {
    //TODO !!!
    protected static boolean isInstalled = false;

    private CompatibilitySophisticatedWolves() {
    }

    public static boolean isSophisticated(EntityWolf dog) {
        return dog instanceof ISophisticatedWolf;
    }

    public static boolean isSophisticated(NBTTagCompound nbtTag) {
        return nbtTag.hasKey("Species");
    }

    public static int getSpecies(EntityWolf dog) {
        return ((ISophisticatedWolf) dog).getSpecies().ordinal();
    }

    public static EntityWolf getWolf(World world, NBTTagCompound nbtTag) {
        EntityWolf wolf = SophisticatedWolvesAPI.entityHandler.getNewSophisticatedWolf(world);
        ((ISophisticatedWolf) wolf).updateSpecies(EnumWolfSpecies.getSpeciesByNum(nbtTag.getInteger("Species")));
        return wolf;
    }


    public static boolean isInstalled() {
        return isInstalled;
    }
}
