package nightkosh.gravestone_extended.core;

import nightkosh.gravestone_extended.potion.CursePotion;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class Potion {

    public static CursePotion curse;

    public static void init() {
        curse = new CursePotion();
    }
}
