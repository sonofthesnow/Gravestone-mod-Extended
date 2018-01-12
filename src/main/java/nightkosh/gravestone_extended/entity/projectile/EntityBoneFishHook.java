package nightkosh.gravestone_extended.entity.projectile;

import net.minecraft.client.Minecraft;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraft.world.storage.loot.RandomValueRange;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import nightkosh.gravestone_extended.core.GSItem;
import nightkosh.gravestone_extended.item.ItemFish;
import nightkosh.gravestone_extended.item.tools.IBoneFishingPole;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class EntityBoneFishHook extends EntityCustomFishHook {

    public EntityBoneFishHook(World world) {
        this(world, world.getPlayerEntityByUUID(Minecraft.getMinecraft().getSession().getProfile().getId()));
    }

    @SideOnly(Side.CLIENT)
    public EntityBoneFishHook(World world, EntityPlayer player, double x, double y, double z) {
        super(world, player, x, y, z);
    }

    public EntityBoneFishHook(World world, EntityPlayer player) {
        super(world, player);
    }

    @Override
    protected boolean isFishingPoleStack(ItemStack stack) {
        return stack.getItem() instanceof IBoneFishingPole;
    }

    protected List<ItemStack> getToxicWaterCatch(Set<BiomeDictionary.Type> biomeTypesList) {
        List<ItemStack> tempList = new ArrayList<>();
        int chance = this.rand.nextInt(100) + Math.round(luck);

        if (chance < 50) {
            tempList.add(new ItemStack(Items.BONE));
            tempList.add(new ItemStack(Items.ROTTEN_FLESH));
            tempList.add(new ItemStack(Items.SPIDER_EYE));
            tempList.add(new ItemStack(GSItem.FISH, 1, ItemFish.EnumFishType.GREEN_JELLYFISH.ordinal()));
            tempList.add(new ItemStack(GSItem.FISH, 1, ItemFish.EnumFishType.BONE_FISH.ordinal()));
        } else if (chance < 80) {
            tempList.add(new ItemStack(GSItem.FISH, 1, ItemFish.EnumFishType.SPOOKYFIN.ordinal()));
        } else if (chance < 95) {
            tempList.add(new ItemStack(GSItem.FISH, 1, ItemFish.EnumFishType.CURSED_KOI.ordinal()));
        } else {
            if (chance < 98) {
                tempList.add(new ItemStack(Blocks.SKULL, 1, 0)); // SKELETON
                tempList.add(new ItemStack(Blocks.SKULL, 1, 2)); // ZOMBIE
            } else {
                EnchantmentHelper.addRandomEnchantment(rand, new ItemStack(GSItem.ENCHANTED_SKULL, 1, 0), new RandomValueRange(30, 40).generateInt(rand), true);
            }
        }
        return tempList;
    }
}
