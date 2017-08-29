package nightkosh.gravestone_extended.core;

import nightkosh.gravestone.core.GSTileEntity;
import nightkosh.gravestone_extended.ModGravestoneExtended;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class TileEntity extends GSTileEntity{

    public static void registration() {
        ModGravestoneExtended.proxy.registerTERenderers();
    }
}
