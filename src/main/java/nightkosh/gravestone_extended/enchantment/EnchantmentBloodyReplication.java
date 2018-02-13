package nightkosh.gravestone_extended.enchantment;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLog;
import net.minecraft.block.state.IBlockState;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import nightkosh.gravestone_extended.core.GSEnchantment;
import nightkosh.gravestone_extended.core.ModInfo;
import nightkosh.gravestone_extended.item.tools.axe.IBoneAxe;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class EnchantmentBloodyReplication extends EnchantmentBase {

    public EnchantmentBloodyReplication() {
        super(Rarity.VERY_RARE, EnumEnchantmentType.DIGGER, new EntityEquipmentSlot[]{EntityEquipmentSlot.MAINHAND});
        this.setName("bloody_replication");
        this.setRegistryName(ModInfo.ID, "gs_bloody_replication");
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
    public boolean canApply(ItemStack stack) {
        return super.canApply(stack) && stack.getItem() instanceof IBoneAxe;
    }

    public static boolean applyEffect(EntityLivingBase entity, IBlockState state, BlockPos pos) {
        if (!entity.isSneaking()) {
            ItemStack axe = entity.getHeldItemMainhand();
            if (axe.getItem() instanceof IBoneAxe) {
                if (!axe.isEmpty()) {
                    NBTTagList nbtList = axe.getEnchantmentTagList();
                    for (NBTBase nbt : nbtList) {
                        if (((NBTTagCompound) nbt).getInteger("id") == Enchantment.getEnchantmentID(GSEnchantment.BLOODY_REPLICATION)) {
                            Block block = state.getBlock();
                            int level = ((NBTTagCompound) nbt).getInteger("lvl");
                            if (!(level == 3 && entity.getHealth() < 0.3) && block instanceof BlockLog) {
                                block.dropBlockAsItem(entity.world, entity.getPosition(), state, 0);
                                float damage;
                                switch (level) {
                                    case 1:
                                        damage = 1.5F;
                                        break;
                                    case 2:
                                        damage = 1;
                                        break;
                                    case 3:
                                    default:
                                        damage = 0.25F;
                                        break;
                                }
                                entity.attackEntityFrom(DamageSource.MAGIC, damage);
                                axe.damageItem(1, entity);
                                return true;
                            } else {
                                return false;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }
}
