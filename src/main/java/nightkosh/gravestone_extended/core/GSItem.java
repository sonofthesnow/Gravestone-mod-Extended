package nightkosh.gravestone_extended.core;

import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;
import nightkosh.gravestone_extended.item.ItemChisel;
import nightkosh.gravestone_extended.item.ItemGSMonsterPlacer;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class GSItem {

    public static final Item CHISEL = new ItemChisel();
    public static final Item SPAWN_EGG = new ItemGSMonsterPlacer();

    public static void registration() {
        GameRegistry.register(CHISEL);
        GameRegistry.register(SPAWN_EGG);
    }
}
