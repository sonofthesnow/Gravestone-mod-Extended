package nightkosh.gravestone_extended.core;

import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;
import nightkosh.gravestone_extended.ModGravestoneExtended;
import nightkosh.gravestone_extended.item.ItemChisel;
import nightkosh.gravestone_extended.item.ItemCorpse;
import nightkosh.gravestone_extended.item.ItemGSMonsterPlacer;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class GSItem {

    public static Item chisel;
    public static Item corpse;
    public static Item spawnEgg;


    public static final String CHISEL = "GSChisel";
    public static final String SPAWN_EGG = "GSSpawnEgg";

    public static void registration() {
        // chisel
        chisel = new ItemChisel();
        GameRegistry.registerItem(chisel, CHISEL);
//TODO
        corpse = new ItemCorpse();
        GameRegistry.registerItem(corpse, "GSCorpse");

        spawnEgg = new ItemGSMonsterPlacer();
        GameRegistry.registerItem(spawnEgg, SPAWN_EGG);

        ModGravestoneExtended.proxy.registerItemsModels();
    }

    public static void registryExternalItems(Item item, String name) {
        GameRegistry.registerItem(item, name);
    }
}
