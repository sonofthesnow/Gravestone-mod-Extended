package nightkosh.gravestone_extended.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import nightkosh.gravestone_extended.core.GSTabs;
import nightkosh.gravestone_extended.core.ModInfo;

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
        this.setCreativeTab(GSTabs.otherItemsTab);
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
        BONE_FISH("bone_fish"),
        MUD_TUNA("mud_tuna"),
        FROST_MINNOW("frost_minnow"),
        PIRANHA("piranha"),
        GOLDEN_KOI("golden_koi"),
        SPECULAR_FISH("specular_fish"),
        CAVEFISH("cavefish"),
        CURSED_KOI("cursed_koi"),
        SPOOKYFIN("spookyfin"),
        OBSIDIFISH("obsidifish"),
        NETHER_SALMON("nether_salmon"),
        QUARTZ_COD("quartz_cod"),
        FLAREFIN_KOI("flarefin_koi"),
        BLAZE_COD("blaze_cod"),
        ENDERFIN("enderfin"),
        PEARL_BASS("pearl_bass"),
        CHORUS_KOI("chorus_koi");

        private String name;

        EnumFishType(String name) {
            this.name = name;
        }

        public String getName() {
            return this.name;
        }
    }
}
