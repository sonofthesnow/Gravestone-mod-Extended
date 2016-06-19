package nightkosh.gravestone_extended.block;

import nightkosh.gravestone_extended.core.Block;
import nightkosh.gravestone_extended.core.Tabs;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.state.BlockState;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class BlockBoneStairs extends BlockStairs {

    public BlockBoneStairs() {
        super(new BlockState(Block.boneBlock).getBaseState());
        this.setUnlocalizedName("bone_stairs");
        this.setCreativeTab(Tabs.otherItemsTab);
        this.setHarvestLevel("pickaxe", 0);
    }
}
