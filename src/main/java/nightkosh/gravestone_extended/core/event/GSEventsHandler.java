package nightkosh.gravestone_extended.core.event;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityPotion;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.potion.PotionType;
import net.minecraft.potion.PotionUtils;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.event.entity.ProjectileImpactEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import nightkosh.gravestone.tileentity.TileEntityGraveStone;
import nightkosh.gravestone_extended.config.ExtendedConfig;
import nightkosh.gravestone_extended.core.GSBlock;
import nightkosh.gravestone_extended.core.GSEnchantment;
import nightkosh.gravestone_extended.core.GSPotion;
import nightkosh.gravestone_extended.core.MobSpawn;
import nightkosh.gravestone_extended.entity.monster.crawler.EntitySkullCrawler;
import nightkosh.gravestone_extended.entity.monster.crawler.EntityWitherSkullCrawler;
import nightkosh.gravestone_extended.entity.monster.crawler.EntityZombieSkullCrawler;
import nightkosh.gravestone_extended.item.tools.IBoneShiled;
import nightkosh.gravestone_extended.item.tools.IBoneSword;
import nightkosh.gravestone_extended.item.tools.ItemBoneShield;

import java.util.Map;

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

    @SubscribeEvent
    public void livingAttackEvent(LivingAttackEvent event) {
        if (event.getEntityLiving() instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) event.getEntityLiving();
            ItemStack activeStack = player.getActiveItemStack();
            if (!activeStack.isEmpty() && activeStack.getItem() instanceof IBoneShiled) {
                if (event.getAmount() >= 3) {
                    float amount = event.getAmount();
                    ((ItemBoneShield) activeStack.getItem()).damageShield(activeStack, player, amount);
                }
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

    @SubscribeEvent(priority = EventPriority.HIGH)
    public void onPotionImpact(ProjectileImpactEvent.Throwable event) {
        if (FMLCommonHandler.instance().getEffectiveSide().isServer()) {
            if (event.getThrowable() instanceof EntityPotion) {
                EntityPotion entityPotion = (EntityPotion) event.getThrowable();
                ItemStack potionStack = entityPotion.getPotion();
                PotionType potionType = PotionUtils.getPotionFromItem(potionStack);

                if (potionType == GSPotion.PURIFICATION_TYPE) {
                    int range = 5;
                    Map<BlockPos, TileEntity> teMap = entityPotion.world.getChunkFromBlockCoords(entityPotion.getPosition()).getTileEntityMap();
                    teMap.putAll(entityPotion.world.getChunkFromBlockCoords(new BlockPos(entityPotion.posX + range, entityPotion.posY, entityPotion.posZ)).getTileEntityMap());
                    teMap.putAll(entityPotion.world.getChunkFromBlockCoords(new BlockPos(entityPotion.posX - range, entityPotion.posY, entityPotion.posZ)).getTileEntityMap());
                    teMap.putAll(entityPotion.world.getChunkFromBlockCoords(new BlockPos(entityPotion.posX, entityPotion.posY, entityPotion.posZ + range)).getTileEntityMap());
                    teMap.putAll(entityPotion.world.getChunkFromBlockCoords(new BlockPos(entityPotion.posX, entityPotion.posY, entityPotion.posZ - range)).getTileEntityMap());
                    teMap.putAll(entityPotion.world.getChunkFromBlockCoords(new BlockPos(entityPotion.posX + range, entityPotion.posY, entityPotion.posZ + range)).getTileEntityMap());
                    teMap.putAll(entityPotion.world.getChunkFromBlockCoords(new BlockPos(entityPotion.posX + range, entityPotion.posY, entityPotion.posZ - range)).getTileEntityMap());
                    teMap.putAll(entityPotion.world.getChunkFromBlockCoords(new BlockPos(entityPotion.posX - range, entityPotion.posY, entityPotion.posZ + range)).getTileEntityMap());
                    teMap.putAll(entityPotion.world.getChunkFromBlockCoords(new BlockPos(entityPotion.posX - range, entityPotion.posY, entityPotion.posZ - range)).getTileEntityMap());

                    for (Map.Entry<BlockPos, TileEntity> teEntry : teMap.entrySet()) {
                        if (teEntry != null && teEntry.getValue() instanceof TileEntityGraveStone &&
                                (teEntry.getKey().getX() >= entityPotion.posX - range && teEntry.getKey().getX() <= entityPotion.posX + range &&
                                        teEntry.getKey().getZ() >= entityPotion.posZ - range && teEntry.getKey().getZ() <= entityPotion.posZ + range &&
                                        teEntry.getKey().getY() >= entityPotion.posY - range && teEntry.getKey().getY() <= entityPotion.posY + range)) {
                            ((TileEntityGraveStone) teEntry.getValue()).setPurified(true);
                        }
                    }
                }
            }
        }
    }
}
