package nightkosh.gravestone_extended.item;

import net.minecraft.item.Item;
import nightkosh.gravestone_extended.core.ModInfo;
import nightkosh.gravestone_extended.core.Tabs;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class ItemToxicSlime extends Item {

    public ItemToxicSlime() {
        this.setUnlocalizedName("gravestone.toxic_slime");
        this.setRegistryName(ModInfo.ID, "gs_toxic_slime");
        this.setCreativeTab(Tabs.otherItemsTab);
    }
}
