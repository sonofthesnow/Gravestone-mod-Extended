package nightkosh.gravestone_extended.core;

import net.minecraftforge.fml.common.registry.GameRegistry;
import nightkosh.gravestone_extended.ModGravestoneExtended;
import nightkosh.gravestone_extended.item.ItemGSChisel;
import nightkosh.gravestone_extended.item.ItemGSCorpse;
import nightkosh.gravestone_extended.item.ItemGSMonsterPlacer;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class ExtendedItem {

    public static net.minecraft.item.Item chisel;
    public static net.minecraft.item.Item corpse;
    public static net.minecraft.item.Item spawnEgg;

    private ExtendedItem() {
    }

    public static void registration() {
        // chisel
        chisel = new ItemGSChisel();
        GameRegistry.registerItem(chisel, "GSChisel");

        corpse = new ItemGSCorpse();
        GameRegistry.registerItem(corpse, "GSCorpse");

        spawnEgg = new ItemGSMonsterPlacer();
        GameRegistry.registerItem(spawnEgg, "GSSpawnEgg");

        ModGravestoneExtended.proxy.registerItemsModels();
    }

    public static void registryExternalItems(net.minecraft.item.Item item, String name) {
        GameRegistry.registerItem(item, name);
    }
}
