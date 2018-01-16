package nightkosh.gravestone_extended.core.compatibility;

import net.minecraftforge.fml.common.Loader;
import nightkosh.gravestone_extended.core.compatibility.forestry.CompatibilityForestry;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class Compatibility {

    public static final Compatibility INSTANCE = new Compatibility();

    public static final String FORESTRY_ID = "forestry";

    public static boolean sophisticatedWolvesInstalled;
    public static boolean forestryInstalled;

    public void checkMods() {
        if (Loader.isModLoaded("mocreatures")) {
            CompatibilityMoCreatures.isInstalled = true;
            CompatibilityMoCreatures.addMobs();
        }

        if (Loader.isModLoaded("thaumcraft")) {
            CompatibilityThaumcraft.addReciepes();
            CompatibilityThaumcraft.addAspects();
            CompatibilityThaumcraft.addSwords();
        }

        if (Loader.isModLoaded(FORESTRY_ID)) {
            forestryInstalled = true;//TODO
            CompatibilityForestry.isInstalled = true;
        }

        if (Loader.isModLoaded("sophisticatedwolves")) {
            sophisticatedWolvesInstalled = true;//TODO
            CompatibilitySophisticatedWolves.isInstalled = true;
        }

        if (Loader.isModLoaded(CompatibilityInfernalMobs.ID)) {
            CompatibilityInfernalMobs.isInstalled = true;
            CompatibilityInfernalMobs.disableInfernalMobs();
        }


        if (Loader.isModLoaded("antiqueatlas")) {
            CompatibilityAntiqueAtlas.isInstalled = true;
        }
    }
}
