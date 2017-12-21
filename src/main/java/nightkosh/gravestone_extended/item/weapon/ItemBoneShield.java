package nightkosh.gravestone_extended.item.weapon;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemShield;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import nightkosh.gravestone_extended.core.GSBlock;
import nightkosh.gravestone_extended.core.ModInfo;
import nightkosh.gravestone_extended.core.Tabs;

import javax.annotation.Nullable;
import java.util.List;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class ItemBoneShield extends ItemShield implements IBoneShiled {

    public ItemBoneShield() {
        this.setUnlocalizedName("gravestone.bone_shield");
        this.setRegistryName(ModInfo.ID, "gs_bone_shield");
        this.setCreativeTab(Tabs.otherItemsTab);
        this.setMaxDamage(750);
    }

    @Override
    public String getItemStackDisplayName(ItemStack stack) {
        return I18n.translateToLocal("item.gravestone.bone_shield.name");
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
    }

    @Override
    public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
        return repair.getItem() == Item.getItemFromBlock(GSBlock.BONE_BLOCK);
    }

    public boolean isShield(ItemStack stack, @Nullable EntityLivingBase entity) {
        return true;
    }

    public void damageShield(ItemStack shield, EntityPlayer player, float damage) {
        ItemStack shieldCopy = shield.copy();
        int intDamage = MathHelper.ceil(damage);
        shield.damageItem(intDamage, player);

        if (shield.isEmpty()) {
            EnumHand enumhand = player.getActiveHand();
            net.minecraftforge.event.ForgeEventFactory.onPlayerDestroyItem(player, shieldCopy, enumhand);

            if (enumhand == EnumHand.MAIN_HAND) {
                player.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, ItemStack.EMPTY);
            } else {
                player.setItemStackToSlot(EntityEquipmentSlot.OFFHAND, ItemStack.EMPTY);
            }

            shield = ItemStack.EMPTY;
            player.playSound(SoundEvents.ITEM_SHIELD_BREAK, 0.8F, 0.8F + player.world.rand.nextFloat() * 0.4F);
        }
    }
}
