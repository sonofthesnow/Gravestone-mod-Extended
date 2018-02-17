package nightkosh.gravestone_extended.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockPortal;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import nightkosh.gravestone_extended.core.GSTabs;
import nightkosh.gravestone_extended.core.ModInfo;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class BlockCatacombsPortal extends BlockPortal {

    public BlockCatacombsPortal() {
        super();
        this.setHardness(Float.MAX_VALUE);
        this.setResistance(Float.MAX_VALUE);
        this.setUnlocalizedName("catacombs_portal");
        this.setRegistryName(ModInfo.ID, "gs_catacombs_portal");
        this.setCreativeTab(GSTabs.otherItemsTab);
    }

    @Override
    public void neighborChanged(IBlockState state, World world, BlockPos pos, Block block, BlockPos fromPos) {
    }
}
