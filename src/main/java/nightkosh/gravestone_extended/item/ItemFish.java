package nightkosh.gravestone_extended.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import nightkosh.gravestone_extended.core.ModInfo;
import nightkosh.gravestone_extended.core.Tabs;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class ItemFish extends Item {

    public ItemFish() {
        this.setUnlocalizedName("gravestone.fish");
        this.setRegistryName(ModInfo.ID, "gs_fish");
        this.setCreativeTab(Tabs.otherItemsTab);
    }

    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
        if (this.isInCreativeTab(tab)) {
            for (EnumFishType fish : EnumFishType.values()) {
                items.add(new ItemStack(this, 1, fish.ordinal()));
            }
        }
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        return "item.gravestone." + EnumFishType.values()[stack.getMetadata()].getName();
    }


    public static enum EnumFishType {
        BLUE_JELLYFISH("blue_jellyfish"),
        GREEN_JELLYFISH("green_jellyfish"),
        MAGMA_JELLYFISH("magma_jellyfish"),
        BONE_FISH("bone_fish");

        private String name;

        EnumFishType(String name) {
            this.name = name;
        }

        public String getName() {
            return this.name;
        }
    }
}
