package nightkosh.gravestone_extended.item.tools;

import net.minecraft.item.Item;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemStack;
import nightkosh.gravestone_extended.core.GSBlock;
import nightkosh.gravestone_extended.core.ModInfo;
import nightkosh.gravestone_extended.core.Tabs;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class ItemBoneHoe extends ItemHoe implements IBoneHoe {

    public ItemBoneHoe() {
        this(ToolMaterial.STONE);
        this.setUnlocalizedName("gravestone.bone_hoe");
        this.setRegistryName(ModInfo.ID, "gs_bone_hoe");
    }

    public ItemBoneHoe(ToolMaterial material) {
        super(material);
        this.setCreativeTab(Tabs.otherItemsTab);
    }

    @Override
    public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
        return super.getIsRepairable(toRepair, repair) || repair.getItem() == Item.getItemFromBlock(GSBlock.BONE_BLOCK);
    }
}
