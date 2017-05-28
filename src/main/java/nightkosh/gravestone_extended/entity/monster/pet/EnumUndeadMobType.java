package nightkosh.gravestone_extended.entity.monster.pet;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public enum EnumUndeadMobType {
    OTHER,
    ZOMBIE,
    SKELETON,
    GHOST,
    HUSK,
    STRAY,
    WITHER,
    PIGMAN;

    public static EnumUndeadMobType getById(int id) {
        if (id < EnumUndeadMobType.values().length) {
            return EnumUndeadMobType.values()[id];
        } else {
            return OTHER;
        }
    }

    public boolean sunLightProtected() {
        return this == HUSK || fireProtected();
    }

    public boolean fireProtected() {
        return this == WITHER;
    }
}
