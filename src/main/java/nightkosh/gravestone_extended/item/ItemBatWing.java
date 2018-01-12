package nightkosh.gravestone_extended.item;

import net.minecraft.item.Item;
import nightkosh.gravestone_extended.core.GSTabs;
import nightkosh.gravestone_extended.core.ModInfo;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class ItemBatWing extends Item {

    public ItemBatWing() {
        this.setUnlocalizedName("gravestone.bat_wing");
        this.setRegistryName(ModInfo.ID, "gs_bat_wing");
        this.setCreativeTab(GSTabs.otherItemsTab);
    }
}
