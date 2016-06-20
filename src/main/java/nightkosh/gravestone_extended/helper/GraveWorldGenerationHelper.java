package nightkosh.gravestone_extended.helper;

import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import nightkosh.gravestone.api.grave.EnumGraveMaterial;
import nightkosh.gravestone.api.grave.EnumGraveType;
import nightkosh.gravestone.block.enums.EnumGraves;
import nightkosh.gravestone.helper.DeathTextHelper;
import nightkosh.gravestone.tileentity.GraveStoneDeathText;
import nightkosh.gravestone.tileentity.TileEntityGraveStone;

import java.util.List;
import java.util.Random;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class GraveWorldGenerationHelper extends GraveGenerationHelper {

    public static GraveGenerationInfo getGrave(World world, Random random, BlockPos pos, EnumGraveTypeByEntity graveTypeByEntity) {
        return getGrave(world, random, pos, graveTypeByEntity, null);
    }

    public static GraveGenerationInfo getGrave(World world, Random random, BlockPos pos, EnumGraveTypeByEntity graveTypeByEntity,
                                               GraveInventoryHelper.GraveContentType contentType) {
        //choose grave type
        switch (graveTypeByEntity) {
            case HUMAN_GRAVES:
                graveTypeByEntity = getRandomHumanGraveType(random);
                break;
            case PETS_GRAVES:
                graveTypeByEntity = getRandomPetGraveType(random);
                break;
            case ALL_GRAVES:
                graveTypeByEntity = getRandomGraveType(random);
                break;
        }


        EnumGraves grave = null;
        List<ItemStack> items;
        GraveStoneDeathText deathText;
        GraveInventoryHelper.GraveCorpseContentType corpseContentType = GraveInventoryHelper.GraveCorpseContentType.RANDOM;
        GraveInventoryHelper.IContentMaterials contentMaterials;
        EnumGraveType[] graveTypes;
        EnumGraveMaterial graveMaterial;

        deathText = DeathTextHelper.getRandomDeathTextAndNameForGrave(random, graveTypeByEntity);

        boolean isFireDamage = isFireDamage(deathText.getDeathText()) || isLavaDamage(deathText.getDeathText());
        if (isFireDamage || random.nextBoolean()) {
            if (isFireDamage) {
                grave = getGraveType(getDefaultGraveTypes(graveTypeByEntity), EnumGraveMaterial.OBSIDIAN);
            } else {
                grave = getGraveTypeByBiomes(world, pos, graveTypeByEntity, null);
            }
            if (contentType == null) {
                contentType = GraveInventoryHelper.GraveContentType.JUNK;
            }
            contentMaterials = null;
        } else {
            graveTypes = getDefaultGraveTypes(graveTypeByEntity);
            switch (graveTypeByEntity) {
                case PLAYER_GRAVES:
                    //TODO
                    break;
                case VILLAGERS_GRAVES:
                    //TODO
                    break;
                case DOGS_GRAVES:
                    //TODO
                    break;
                case CATS_GRAVES:
                    //TODO
                    break;
                case HORSE_GRAVES:
//                check explosion
//                check fire or lava
                    //TODO
                    break;
            }
            if (contentType == null) {
                contentType = GraveInventoryHelper.GraveContentType.JUNK;//TODO
            }
            contentMaterials = null;//TODO

            graveMaterial = EnumGraveMaterial.OBSIDIAN;//TODO !!!!!!!!!!!!!
            grave = getGraveType(graveTypes, graveMaterial);
        }

        ItemStack sword = null; //TODO

        items = GraveInventoryHelper.getRandomGraveContent(random, graveTypeByEntity, contentType, corpseContentType, contentMaterials);


        boolean enchanted = isMagicDamage(deathText.getDeathText());
        boolean mossy = isMossyGrave(world, pos, grave);
        ItemStack flower = getRandomFlower(world, pos, random, grave);

        return new GraveGenerationInfo(grave, enchanted, mossy, items, sword, deathText, flower);
    }


    private static boolean isFireDamage(String damageText) {
        return damageText.toLowerCase().contains("nFire");
    }

    private static boolean isLavaDamage(String damageText) {
        return damageText.toLowerCase().contains("lava");
    }

    private static boolean isMagicDamage(String damageText) {
        return damageText.toLowerCase().contains("magic");
    }

    private static ItemStack getRandomFlower(World world, BlockPos pos, Random random, EnumGraves grave) {//TODO ? - EnumGraves grave
        if (random.nextInt(4) == 0) {
            ItemStack flower = new ItemStack(GraveStoneHelper.FLOWERS.get(random.nextInt(GraveStoneHelper.FLOWERS.size())), 1);
            TileEntity te = world.getTileEntity(pos);
            if (te != null && te instanceof TileEntityGraveStone && GraveStoneHelper.canFlowerBePlaced(world, pos, flower, (TileEntityGraveStone) te)) {
                return flower;
            }
        }
        return null;
    }

    public static class GraveGenerationInfo {
        private EnumGraves grave;
        private boolean enchanted;
        private boolean mossy;
        private ItemStack sword;
        private ItemStack flower;
        private GraveStoneDeathText deathText;
        private List<ItemStack> items;

        public GraveGenerationInfo(EnumGraves grave, boolean enchanted, boolean mossy, List<ItemStack> items, ItemStack sword,
                                   GraveStoneDeathText deathText, ItemStack flower) {
            this.grave = grave;
            this.enchanted = enchanted;
            this.mossy = mossy;
            this.items = items;
            this.sword = sword;
            this.deathText = deathText;
            this.flower = flower;
        }

        public ItemStack getSword() {
            return sword;
        }

        public boolean isMossy() {
            return mossy;
        }

        public boolean isEnchanted() {
            return enchanted;
        }

        public EnumGraves getGrave() {
            return grave;
        }

        public ItemStack getFlower() {
            return flower;
        }

        public GraveStoneDeathText getDeathText() {
            return deathText;
        }

        public List<ItemStack> getItems() {
            return items;
        }
    }
}
