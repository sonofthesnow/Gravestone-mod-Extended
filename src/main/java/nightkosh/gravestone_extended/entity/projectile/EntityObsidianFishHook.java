package nightkosh.gravestone_extended.entity.projectile;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraft.world.storage.loot.RandomValueRange;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import nightkosh.gravestone_extended.core.GSItem;
import nightkosh.gravestone_extended.entity.item.EntityFireproofItem;
import nightkosh.gravestone_extended.item.ItemFish;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class EntityObsidianFishHook extends EntityBoneFishHook {

    public EntityObsidianFishHook(World world) {
        super(world);
        this.isImmuneToFire = true;
    }

    @SideOnly(Side.CLIENT)
    public EntityObsidianFishHook(World world, EntityPlayer player, double x, double y, double z) {
        super(world, player, x, y, z);
        this.isImmuneToFire = true;
    }

    public EntityObsidianFishHook(World world, EntityPlayer player) {
        super(world, player);
        this.isImmuneToFire = true;
    }

    @Override
    protected EntityItem getCatchEntityItem(ItemStack stack) {
        return new EntityFireproofItem(this.world, this.posX, this.posY + 0.5, this.posZ, stack);
    }

    @Override
    public boolean isInWater() {
        return super.isInWater() || this.isInLava();
    }

    @Override
    protected List<ItemStack> getLavaCatch(Set<BiomeDictionary.Type> biomeTypesList) {
        List<ItemStack> tempList = new ArrayList<>();

        int chance = this.rand.nextInt(100) + Math.round(luck);
        if (!biomeTypesList.contains(BiomeDictionary.Type.NETHER)) {
            if (chance < 80) {
                tempList.add(new ItemStack(GSItem.FISH, 1, ItemFish.EnumFishType.OBSIDIFISH.ordinal()));
            } else if (chance < 95) {
                tempList.add(new ItemStack(GSItem.FISH, 1, ItemFish.EnumFishType.MAGMA_JELLYFISH.ordinal()));
            } else {
                if (chance < 98) {
                    tempList.add(new ItemStack(Blocks.SKULL, 1, 1)); //WITHER SKULL
                } else {
                    EnchantmentHelper.addRandomEnchantment(rand, new ItemStack(GSItem.ENCHANTED_SKULL, 1, 1), new RandomValueRange(40, 50).generateInt(rand), true);
                }
            }
        } else {
            if (chance < 40) {
                tempList.add(new ItemStack(GSItem.FISH, 1, ItemFish.EnumFishType.NETHER_SALMON.ordinal()));
            } else if (chance < 80) {
                tempList.add(new ItemStack(GSItem.FISH, 1, ItemFish.EnumFishType.MAGMA_JELLYFISH.ordinal()));
                tempList.add(new ItemStack(GSItem.FISH, 1, ItemFish.EnumFishType.QUARTZ_COD.ordinal()));
                tempList.add(new ItemStack(GSItem.FISH, 1, ItemFish.EnumFishType.WITHERED_CRUCIAN.ordinal()));
            } else if (chance < 95) {
                tempList.add(new ItemStack(GSItem.FISH, 1, ItemFish.EnumFishType.FLAREFIN_KOI.ordinal()));
                tempList.add(new ItemStack(GSItem.FISH, 1, ItemFish.EnumFishType.BLAZE_COD.ordinal()));
            } else {
                if (chance < 98) {
                    tempList.add(new ItemStack(Blocks.SKULL, 1, 1)); //WITHER SKULL
                } else {
                    EnchantmentHelper.addRandomEnchantment(rand, new ItemStack(GSItem.ENCHANTED_SKULL, 1, 1), new RandomValueRange(40, 50).generateInt(rand), true);
                }
            }
        }
        return tempList;
    }
}
