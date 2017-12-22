package nightkosh.gravestone_extended.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.DamageSource;
import nightkosh.gravestone_extended.core.GSEnchantment;
import nightkosh.gravestone_extended.core.ModInfo;
import nightkosh.gravestone_extended.item.weapon.IBoneShiled;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class EnchantmentPainMirror extends EnchantmentBase {

    public EnchantmentPainMirror() {
        super(Rarity.VERY_RARE, EnumEnchantmentType.ALL, HAND_SLOTS);
        this.setName("pain_mirror");
        this.setRegistryName(ModInfo.ID, "gs_pain_mirror");
    }

    @Override
    public int getMinEnchantability(int enchantmentLevel) {
        return 5 + (enchantmentLevel - 1) * 8;
    }

    @Override
    public int getMaxEnchantability(int enchantmentLevel) {
        return super.getMinEnchantability(enchantmentLevel) + 50;
    }

    @Override
    public int getMaxLevel() {
        return 3;
    }

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack) {
        return this.canApply(stack);
    }

    @Override
    public boolean canApply(ItemStack stack) {
        return super.canApply(stack) && stack.getItem() instanceof IBoneShiled;
    }

    public static void applyEnchantmentEffect(EntityPlayer player, Entity attacker, ItemStack stack, float amount) {
        NBTTagList nbtList = stack.getEnchantmentTagList();
        for (NBTBase nbt : nbtList) {
            if (((NBTTagCompound) nbt).getInteger("id") == Enchantment.getEnchantmentID(GSEnchantment.PAIN_MIRROR)) {
                if (player.world.rand.nextInt(100) <= 10 * ((NBTTagCompound) nbt).getShort("lvl")) {
                    if (attacker instanceof EntityLivingBase) {
                        attacker.attackEntityFrom(DamageSource.MAGIC, amount);
                    }
                }
            }
        }
    }
}
