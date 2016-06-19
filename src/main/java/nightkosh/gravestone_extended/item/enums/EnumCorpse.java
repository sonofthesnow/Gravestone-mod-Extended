package nightkosh.gravestone_extended.item.enums;

import nightkosh.gravestone_extended.ModGravestoneExtended;
import nightkosh.gravestone_extended.block.enums.IBlockEnum;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public enum EnumCorpse implements IBlockEnum {
    VILLAGER("item.corpse.villager"),
    DOG("item.corpse.dog"),
    CAT("item.corpse.cat"),
    HORSE("item.corpse.horse");
    private String name;

    private EnumCorpse(String name) {
        this.name = name;
    }

    @Override
    public String getUnLocalizedName() {
        return ModGravestoneExtended.proxy.getLocalizedString(this.name);
    }

    public static EnumCorpse getById(byte id) {
        if (id < values().length) {
            return values()[id];
        }
        return VILLAGER;
    }
}
