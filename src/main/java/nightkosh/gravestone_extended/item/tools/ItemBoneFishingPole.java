package nightkosh.gravestone_extended.item.tools;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFishingRod;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.stats.StatList;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import nightkosh.gravestone_extended.core.GSBlock;
import nightkosh.gravestone_extended.core.GSEnchantment;
import nightkosh.gravestone_extended.core.ModInfo;
import nightkosh.gravestone_extended.core.Tabs;
import nightkosh.gravestone_extended.entity.projectile.EntityBoneFishHook;
import nightkosh.gravestone_extended.entity.projectile.EntityObsidianFishHook;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class ItemBoneFishingPole extends ItemFishingRod implements IBoneFishingPole {

    public ItemBoneFishingPole() {
        this.setCreativeTab(Tabs.otherItemsTab);
        this.setUnlocalizedName("gravestone.bone_fishing_pole");
        this.setRegistryName(ModInfo.ID, "gs_bone_fishing_pole");
    }

    @Override
    public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
        return super.getIsRepairable(toRepair, repair) || repair.getItem() == Item.getItemFromBlock(GSBlock.BONE_BLOCK);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
        ItemStack stack = player.getHeldItem(hand);

        if (player.fishEntity != null) {
            int i = player.fishEntity.handleHookRetraction();
            stack.damageItem(i, player);
            player.swingArm(hand);
            world.playSound(null, player.posX, player.posY, player.posZ, SoundEvents.ENTITY_BOBBER_RETRIEVE, SoundCategory.NEUTRAL, 1, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
        } else {
            world.playSound(null, player.posX, player.posY, player.posZ, SoundEvents.ENTITY_BOBBER_THROW, SoundCategory.NEUTRAL, 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));

            if (!world.isRemote) {
                EntityBoneFishHook hook = getHook(world, player, stack);
                int speed = EnchantmentHelper.getFishingSpeedBonus(stack);
                if (speed > 0) {
                    hook.setLureSpeed(speed);
                }

                int luck = EnchantmentHelper.getFishingLuckBonus(stack);
                if (luck > 0) {
                    hook.setLuck(luck);
                }

                world.spawnEntity(hook);
            }

            player.swingArm(hand);
            player.addStat(StatList.getObjectUseStats(this));
        }

        return new ActionResult<>(EnumActionResult.SUCCESS, stack);
    }

    protected static EntityBoneFishHook getHook(World world, EntityPlayer player, ItemStack stack) {
        boolean hellishAngling = false;
        NBTTagList nbtList = stack.getEnchantmentTagList();
        for (NBTBase nbt : nbtList) {
            if (((NBTTagCompound) nbt).getInteger("id") == Enchantment.getEnchantmentID(GSEnchantment.HELLISH_ANGLING)) {
                hellishAngling = true;
                break;
            }
        }
        if (hellishAngling) {
            return new EntityObsidianFishHook(world, player);
        } else {
            return new EntityBoneFishHook(world, player);
        }
    }
}
