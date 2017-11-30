package nightkosh.gravestone_extended.core.event;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import nightkosh.gravestone_extended.config.ExtendedConfig;
import nightkosh.gravestone_extended.core.GSBlock;
import nightkosh.gravestone_extended.core.GSEnchantment;
import nightkosh.gravestone_extended.core.MobSpawn;
import nightkosh.gravestone_extended.entity.monster.crawler.EntitySkullCrawler;
import nightkosh.gravestone_extended.entity.monster.crawler.EntityWitherSkullCrawler;
import nightkosh.gravestone_extended.entity.monster.crawler.EntityZombieSkullCrawler;
import nightkosh.gravestone_extended.item.tools.IBoneSword;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class GSEventsHandler {

    // Hopefully ensure we capture items before other things do (set to high so other mods can run before if they have more specialness
    @SubscribeEvent(priority = EventPriority.HIGH)
    public void onEntityLivingDeath(LivingDeathEvent event) {
        if (FMLCommonHandler.instance().getEffectiveSide().isServer()) {
            if (ExtendedConfig.spawnSkullCrawlersAtMobsDeath) {
                if (event.getEntity() instanceof EntitySkeleton) {
                    EntitySkullCrawler crawler;
                    if (MobSpawn.isWitherSkeleton((EntitySkeleton) event.getEntity())) {
                        crawler = new EntityWitherSkullCrawler(event.getEntity().getEntityWorld());
                    } else {
                        crawler = new EntitySkullCrawler(event.getEntity().getEntityWorld());
                    }
                    MobSpawn.spawnCrawler(event.getEntity(), crawler);
                } else if (event.getEntity() instanceof EntityZombie) {
                    MobSpawn.spawnCrawler(event.getEntity(), new EntityZombieSkullCrawler(event.getEntity().getEntityWorld()));
                }
            }
            if (event.getEntity() instanceof EntityCreeper && ((EntityCreeper) event.getEntity()).getPowered()) {
                // drop creeper statue if entity is a charged creeper
                GSBlock.MEMORIAL.dropCreeperMemorial(event.getEntity().getEntityWorld(), new BlockPos(event.getEntity()));
            }
        }
    }

    @SubscribeEvent(priority = EventPriority.HIGH)
    public void onEntityLivingDamage(LivingDamageEvent event) {
        if (FMLCommonHandler.instance().getEffectiveSide().isServer()) {
            Entity entity = event.getSource().getTrueSource();
            if (entity instanceof EntityLivingBase && event.getEntity() instanceof EntityLivingBase) {
                EntityLivingBase attacker = (EntityLivingBase) entity;
                ItemStack itemMainHand = attacker.getHeldItemMainhand();
                ItemStack itemOffHand = attacker.getHeldItemOffhand();
                if (itemMainHand.getItem() instanceof IBoneSword) {
                    applyEnchantments(attacker, (EntityLivingBase) event.getEntity(), itemMainHand, event.getAmount());
                } else if (itemOffHand.getItem() instanceof IBoneSword) {
                    applyEnchantments(attacker, (EntityLivingBase) event.getEntity(), itemOffHand, event.getAmount());
                }
            }
        }
    }

    private static void applyEnchantments(EntityLivingBase attacker, EntityLivingBase target, ItemStack weapon, float damage) {
        NBTTagList nbtList = weapon.getEnchantmentTagList();
        for (NBTBase nbt : nbtList) {
            if (((NBTTagCompound) nbt).getInteger("id") == Enchantment.getEnchantmentID(GSEnchantment.VAMPIRIC_TOUCH)) {
                float healed = damage * 0.2F;
                if (healed < 0.5) {
                    healed = 0.5F;
                }
                attacker.heal(healed);
            } else if (((NBTTagCompound) nbt).getInteger("id") == Enchantment.getEnchantmentID(GSEnchantment.NECROTIC_CORROSION)) {
                short level = ((NBTTagCompound) nbt).getShort("lvl");
                float additionalDamage = damage * 0.1F * level;
                if (additionalDamage < 0.5) {
                    additionalDamage = 0.5F;
                }
                float health = target.getHealth();
                if (health > additionalDamage + 0.5) {
                    target.setHealth(health - additionalDamage);
                } else if (health > 0.5) {
                    target.setHealth(0.5F);
                }
            }
        }
    }
}
