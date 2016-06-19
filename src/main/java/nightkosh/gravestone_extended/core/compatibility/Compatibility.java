package nightkosh.gravestone_extended.core.compatibility;

import net.minecraftforge.fml.common.Loader;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class Compatibility {

    private static Compatibility instance;

    private Compatibility() {
        instance = this;
    }

    public static Compatibility getInstance() {
        return (instance == null) ? new Compatibility() : instance;
    }

    public void checkMods() {
        if (Loader.isModLoaded("MoCreatures")) {
            CompatibilityMoCreatures.isInstalled = true;
            CompatibilityMoCreatures.addMobs();
        }

        if (Loader.isModLoaded("Thaumcraft")) {
            CompatibilityThaumcraft.addReciepes();
            CompatibilityThaumcraft.addAspects();
            CompatibilityThaumcraft.addSwords();
        }

//        if (Loader.isModLoaded("Forestry")) {
//            CompatibilityForestry.isInstalled = true;
//            CompatibilityForestry.addBackpack();
//        }

        if (Loader.isModLoaded("SophisticatedWolves")) {
            CompatibilitySophisticatedWolves.isInstalled = true;
        }


        if (Loader.isModLoaded("antiqueatlas")) {
            CompatibilityAntiqueAtlas.isInstalled = true;
        }
    }
}
