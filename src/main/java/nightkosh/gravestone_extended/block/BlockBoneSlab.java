package nightkosh.gravestone_extended.block;

import net.minecraft.block.BlockSlab;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import nightkosh.gravestone_extended.core.ModInfo;
import nightkosh.gravestone_extended.core.Tabs;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class BlockBoneSlab extends BlockSlab {

    public BlockBoneSlab() {
        super(Material.ROCK);
        this.setSoundType(SoundType.STONE);
        this.setUnlocalizedName("bone_slab");
        this.setHardness(2);
        this.setResistance(2);
        this.setCreativeTab(Tabs.otherItemsTab);
        this.setHarvestLevel("pickaxe", 0);
        this.setRegistryName(ModInfo.ID, "gsboneslab");
    }

    @Override
    public String getUnlocalizedName(int meta) {
        return super.getUnlocalizedName() + "." + getUnlocalizedName();
    }

    @Override
    public boolean isDouble() {
        return false;
    }

    @Override
    public IProperty getVariantProperty() {
        return null;
    }

    @Override
    public Comparable<?> getTypeForItem(ItemStack stack) {
        return BlockSlab.EnumBlockHalf.values()[stack.getMetadata()];
    }

    public int getMetaFromState(IBlockState state) {
        return ((Enum) state.getValue(HALF)).ordinal();
    }

    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, new IProperty[]{HALF});
    }
}
