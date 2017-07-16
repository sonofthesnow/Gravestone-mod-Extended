package nightkosh.gravestone_extended.entity.monster.horse;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIMoveThroughVillage;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import nightkosh.gravestone_extended.core.Entity;
import nightkosh.gravestone_extended.entity.ai.EntityAINearestAttackableHorse;
import nightkosh.gravestone_extended.entity.monster.pet.EntityZombieCat;
import nightkosh.gravestone_extended.entity.monster.pet.EntityZombieDog;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class EntityZombieHorse extends EntityUndeadHorse {
    public EntityZombieHorse(World worldIn) {
        super(worldIn);

        this.tasks.addTask(4, new EntityAIMoveTowardsRestriction(this, 1));
        this.tasks.addTask(3, new EntityAIAttackMelee(this, 1, true));
//        this.tasks.addTask(3, new EntityAIAttackOnCollide(this, EntityWolf.class, 1, true));
//        this.tasks.addTask(3, new EntityAIAttackOnCollide(this, EntityOcelot.class, 1, true));
//        this.tasks.addTask(4, new EntityAIAttackLivingHorse(this, 1, false));
        this.tasks.addTask(5, new EntityAIMoveThroughVillage(this, 1, false));

        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityVillager.class, false));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityWolf.class, false));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityOcelot.class, false));
        this.targetTasks.addTask(4, new EntityAINearestAttackableHorse(this, false));
    }

    @Override
    public HorseType getUndeadHorseType() {
        return HorseType.ZOMBIE;
    }

    @Override
    public void onKillEntity(EntityLivingBase entityLiving) {
        super.onKillEntity(entityLiving);

        if (this.getEntityWorld().getDifficulty() == EnumDifficulty.NORMAL || this.getEntityWorld().getDifficulty() == EnumDifficulty.HARD) {
            spawnZombieMob(entityLiving);
        }
    }

    protected void spawnZombieMob(EntityLivingBase entityLivingBase) {
        if (entityLivingBase instanceof EntityLiving) {
            EntityLiving entity = (EntityLiving) entityLivingBase;
            EntityLiving zombie = null;
            if (entity instanceof EntityVillager) {
                EntityVillager villager = (EntityVillager) entityLivingBase;
                EntityZombie entityZombie = new EntityZombie(this.getEntityWorld());
                entityZombie.copyLocationAndAnglesFrom(entity);
                this.getEntityWorld().removeEntity(entity);
                entityZombie.onInitialSpawn(this.getEntityWorld().getDifficultyForLocation(new BlockPos(this)), null);

                entityZombie.setVillagerType(villager.getProfessionForge());
                entityZombie.setChild(entityLivingBase.isChild());
                entityZombie.setNoAI(villager.isAIDisabled());

                if (villager.hasCustomName()) {
                    entityZombie.setCustomNameTag(villager.getCustomNameTag());
                    entityZombie.setAlwaysRenderNameTag(villager.getAlwaysRenderNameTag());
                }

                this.getEntityWorld().spawnEntity(entityZombie);
                this.getEntityWorld().playEvent(null, 1026, new BlockPos(this), 0);
                zombie = entityZombie;
            } else if (entity instanceof EntityWolf) {
                EntityZombieDog zombieDog = new EntityZombieDog(this.getEntityWorld(), false);
                zombieDog.copyLocationAndAnglesFrom(entity);

                this.getEntityWorld().removeEntity(entity);
                this.getEntityWorld().spawnEntity(zombieDog);
                this.getEntityWorld().playEvent(null, 1026, new BlockPos(this), 0);

                zombie = zombieDog;
            } else if (entity instanceof EntityOcelot) {
                EntityZombieCat zombieCat = new EntityZombieCat(this.getEntityWorld(), false);
                zombieCat.copyLocationAndAnglesFrom(entity);
                if (((EntityOcelot) entity).isTamed()) {
                    zombieCat.setSkin(((EntityOcelot) entity).getTameSkin());
                } else {
                    zombieCat.setSkin(0);
                }
                this.getEntityWorld().removeEntity(entity);

                zombieCat.onInitialSpawn(this.getEntityWorld().getDifficultyForLocation(new BlockPos(this)), null);
                this.getEntityWorld().spawnEntity(zombieCat);
                this.getEntityWorld().playEvent(null, 1026, new BlockPos(this), 0);

                zombie = zombieCat;
            } else if (entity instanceof EntityHorse) {
                EntityZombieHorse zombieHorse = new EntityZombieHorse(this.getEntityWorld());
                zombieHorse.copyLocationAndAnglesFrom(entity);
                zombieHorse.setGrowingAge(((EntityHorse) entity).getGrowingAge());

                this.getEntityWorld().removeEntity(entity);
                this.getEntityWorld().spawnEntity(zombieHorse);
                this.getEntityWorld().playEvent(null, 1026, new BlockPos(this), 0);

                zombie = zombieHorse;
            }
            if (zombie != null && entity.hasCustomName()) {
                zombie.setCustomNameTag(entity.getCustomNameTag());
            }
        }
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(5);
    }

    @Override
    public String getName() {
        if (this.hasCustomName()) {
            return this.getCustomNameTag();
        } else {
            return I18n.translateToLocal("entity." + Entity.MINECRAFT_ZOMBIE_HORSE_NAME + ".name");
        }
    }
}
