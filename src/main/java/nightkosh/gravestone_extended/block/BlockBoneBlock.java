package nightkosh.gravestone_extended.block;

import nightkosh.gravestone_extended.block.enums.EnumBoneBlock;
import nightkosh.gravestone_extended.config.ExtendedConfig;
import nightkosh.gravestone_extended.core.GSBlock;
import nightkosh.gravestone_extended.core.Tabs;
import nightkosh.gravestone_extended.entity.monster.EntitySkullCrawler;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class BlockBoneBlock extends net.minecraft.block.Block {

    public static final PropertyEnum VARIANT = PropertyEnum.create("variant", EnumBoneBlock.class);

    public BlockBoneBlock() {
        super(Material.rock);
        this.setStepSound(net.minecraft.block.Block.soundTypeStone);
        this.setHardness(2);
        this.setResistance(2);
        this.setCreativeTab(Tabs.otherItemsTab);
        this.setHarvestLevel("pickaxe", 0);
    }

    /**
     * Returns a list of blocks with the same ID, but different meta (eg: wood returns 4 blocks)
     */
    @Override
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item item, CreativeTabs tab, List list) {
        for (byte meta = 0; meta < EnumBoneBlock.values().length; meta++) {
            list.add(new ItemStack(item, 1, meta));
        }
    }

    /**
     * Determines the damage on the item the block drops. Used in cloth and wood.
     */
    @Override
    public int damageDropped(IBlockState state) {
        int metadata = ((Enum) state.getValue(VARIANT)).ordinal();
        if (isSkullCrawlerBlock(state)) {
            metadata -= 2;
        }
        return metadata;
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        return this.getDefaultState().withProperty(VARIANT, EnumBoneBlock.getById((byte) meta));
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return ((EnumBoneBlock) state.getValue(VARIANT)).ordinal();
    }

    @Override
    protected BlockState createBlockState() {
        return new BlockState(this, new IProperty[]{VARIANT});
    }

    public boolean isSkullCrawlerBlock(IBlockState state) {
        EnumBoneBlock variant = (EnumBoneBlock) state.getValue(VARIANT);
        return variant == EnumBoneBlock.CRAWLER_BONE_BLOCK || variant == EnumBoneBlock.CRAWLER_SKULL_BONE_BLOCK;
    }

    public static boolean canContainCrawler(IBlockState state) {
        return state.equals(GSBlock.boneBlock.getDefaultState().withProperty(BlockBoneBlock.VARIANT, EnumBoneBlock.BONE_BLOCK)) ||
                state.equals(GSBlock.boneBlock.getDefaultState().withProperty(BlockBoneBlock.VARIANT, EnumBoneBlock.SKULL_BONE_BLOCK));
    }

    public static IBlockState getCrawlerBlockState(IBlockState state) {
        EnumBoneBlock variant = (EnumBoneBlock) state.getValue(VARIANT);
        if (variant == EnumBoneBlock.BONE_BLOCK) {
            return state.withProperty(BlockBoneBlock.VARIANT, EnumBoneBlock.CRAWLER_BONE_BLOCK);
        } else {
            return state.withProperty(BlockBoneBlock.VARIANT, EnumBoneBlock.CRAWLER_SKULL_BONE_BLOCK);
        }
    }

    /**
     * Called right before the block is destroyed by a player. Args: world, x, y, z, metaData
     */
    @Override
    public void onBlockDestroyedByPlayer(World world, BlockPos pos, IBlockState state) {
        if (!world.isRemote && isSkullCrawlerBlock(state) && ExtendedConfig.spawnSkullCrawlersAtBoneBlockDestruction) {
            EntitySkullCrawler skullCrawler = new EntitySkullCrawler(world);
            skullCrawler.setLocationAndAngles(pos.getX() + 0.5D, pos.getY(), pos.getZ() + 0.5D, 0, 0);
            world.spawnEntityInWorld(skullCrawler);
            skullCrawler.spawnExplosionParticle();
        }

        super.onBlockDestroyedByPlayer(world, pos, state);
    }
}
