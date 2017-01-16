package nightkosh.gravestone_extended.block.enums;

import net.minecraft.util.IStringSerializable;
import nightkosh.gravestone.block.enums.IBlockEnum;
import nightkosh.gravestone_extended.ModGravestoneExtended;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public enum EnumCorpse implements IBlockEnum, IStringSerializable {
    STEVE("item.corpse.steve", "corpse_steve"),
    VILLAGER("item.corpse.villager", "corpse_villager"),
    DOG("item.corpse.dog", "corpse_dog", false),
    CAT("item.corpse.cat", "corpse_cat", false),
    HORSE("item.corpse.horse", "corpse_horse", false),
    ZOMBIE("item.corpse.zombie", "corpse_zombie"),
    ZOMBIE_VILLAGER("item.corpse.zombie_villager", "corpse_zombie_villager"),
    ZOMBIE_PIGMEN("item.corpse.zombie_pigmen", "corpse_zombie_pigmen"),
    SKELETON("item.corpse.skeleton", "corpse_skeleton"),
    WITHER_SKELETON("item.corpse.wither_skeleton", "corpse_wither_skeleton"),
    WITCH("item.corpse.witch", "corpse_witch");

    private String name;
    private String blockModelName;
    private boolean canBeHanged;

    private EnumCorpse(String name, String blockModelName) {
        this(name, blockModelName, true);
    }

    private EnumCorpse(String name, String blockModelName, boolean canBeHanged) {
        this.name = name;
        this.blockModelName = blockModelName;
        this.canBeHanged = canBeHanged;
    }

    @Override
    public String getUnLocalizedName() {
        return ModGravestoneExtended.proxy.getLocalizedString(this.name);
    }

    public static String getPlayerUnLocalizedName() {
        return ModGravestoneExtended.proxy.getLocalizedString("item.corpse.player");
    }

    @Override
    public String getName() {
        return blockModelName;
    }

    public boolean canBeHanged() {
        return this.canBeHanged;
    }

    public static EnumCorpse getById(byte id) {
        if (id < values().length) {
            return values()[id];
        }
        return VILLAGER;
    }
}
