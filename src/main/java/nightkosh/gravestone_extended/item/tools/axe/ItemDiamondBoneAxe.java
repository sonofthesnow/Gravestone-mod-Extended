package nightkosh.gravestone_extended.item.tools.axe;

import net.minecraft.item.Item;
import nightkosh.gravestone_extended.core.ModInfo;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class ItemDiamondBoneAxe extends ItemBoneAxe {

    public ItemDiamondBoneAxe() {
        super(Item.ToolMaterial.DIAMOND);
        this.setUnlocalizedName("gravestone.bone_axe_diamond");
        this.setRegistryName(ModInfo.ID, "gs_bone_axe_diamond");
    }
}
