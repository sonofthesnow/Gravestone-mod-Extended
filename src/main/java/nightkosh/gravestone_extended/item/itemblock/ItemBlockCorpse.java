package nightkosh.gravestone_extended.item.itemblock;

import com.mojang.authlib.GameProfile;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.world.World;
import nightkosh.gravestone_extended.block.enums.EnumCorpse;
import nightkosh.gravestone_extended.item.corpse.CorpseHelper;

import java.util.List;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class ItemBlockCorpse extends ItemBlock {

    public ItemBlockCorpse(Block block) {
        super(block);
        setUnlocalizedName("Corpse");
    }

    @Override
    public void onCreated(ItemStack stack, World world, EntityPlayer player) {
        if (stack.getTagCompound() == null) {
            stack.setTagCompound(new NBTTagCompound());
        }
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4) {
        if (stack.getTagCompound() == null) {
            stack.setTagCompound(new NBTTagCompound());
        } else {
            CorpseHelper.addInfo(stack.getItemDamage(), list, stack.getTagCompound());
        }
    }

    @Override
    public int getMetadata(int metadata) {
        return metadata;
    }

    @Override
    public String getItemStackDisplayName(ItemStack itemStack) {
        if (itemStack.getItemDamage() == EnumCorpse.STEVE.ordinal()) {
            NBTTagCompound nbt = itemStack.getTagCompound();
            if (nbt != null && nbt.hasKey("Owner", 10)) {
                GameProfile playerProfile = NBTUtil.readGameProfileFromNBT(nbt.getCompoundTag("Owner"));
                if (playerProfile != null) {
                    return EnumCorpse.getPlayerUnLocalizedName() + " - " + playerProfile.getName();
                }
            }
        }
        return EnumCorpse.getById((byte) itemStack.getItemDamage()).getUnLocalizedName();
    }
}
