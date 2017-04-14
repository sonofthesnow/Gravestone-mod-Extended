package nightkosh.gravestone_extended.structures.catacombs;

import nightkosh.gravestone_extended.block.BlockBoneBlock;
import nightkosh.gravestone_extended.block.enums.EnumBoneBlock;
import nightkosh.gravestone_extended.core.GSBlock;
import net.minecraft.init.Blocks;
import net.minecraft.world.gen.structure.StructureComponent;

import java.util.Random;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class CatacombsBoneBlocks extends StructureComponent.BlockSelector {

    public CatacombsBoneBlocks() {
    }

    /**
     * Picks Block Ids and Metadata (Silverfish)
     */
    @Override
    public void selectBlocks(Random random, int par2, int par3, int par4, boolean flag) {
        if (flag) {
            if (random.nextInt(5) == 0) {
                if (random.nextInt(100) < 60) {
                    this.blockstate = GSBlock.boneBlock.getDefaultState().withProperty(BlockBoneBlock.VARIANT, EnumBoneBlock.CRAWLER_SKULL_BONE_BLOCK);
                } else {
                    this.blockstate = GSBlock.boneBlock.getDefaultState().withProperty(BlockBoneBlock.VARIANT, EnumBoneBlock.SKULL_BONE_BLOCK);
                }
            } else {
                if (random.nextInt(100) < 60) {
                    this.blockstate = GSBlock.boneBlock.getDefaultState().withProperty(BlockBoneBlock.VARIANT, EnumBoneBlock.CRAWLER_BONE_BLOCK);
                } else {
                    this.blockstate = GSBlock.boneBlock.getDefaultState();
                }
            }
        } else {
            this.blockstate = Blocks.AIR.getDefaultState();
        }
    }
}
