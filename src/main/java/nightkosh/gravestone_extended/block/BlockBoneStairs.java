package nightkosh.gravestone_extended.block;

import net.minecraft.block.BlockStairs;
import net.minecraft.block.state.BlockStateContainer;
import nightkosh.gravestone_extended.core.GSBlock;
import nightkosh.gravestone_extended.core.ModInfo;
import nightkosh.gravestone_extended.core.Tabs;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class BlockBoneStairs extends BlockStairs {

    public BlockBoneStairs() {
        super(new BlockStateContainer(GSBlock.BONE_BLOCK).getBaseState());
        this.setUnlocalizedName("bone_stairs");
        this.setCreativeTab(Tabs.otherItemsTab);
        this.setHarvestLevel("pickaxe", 0);
        this.setRegistryName(ModInfo.ID, "GSBoneStairs");
    }
}
