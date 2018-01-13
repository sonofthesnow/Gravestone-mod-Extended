package nightkosh.gravestone_extended.entity.monster;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAIWanderAvoidWater;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import nightkosh.gravestone_extended.core.GSSound;
import nightkosh.gravestone_extended.core.Resources;
import nightkosh.gravestone_extended.entity.monster.pet.EntityUndeadDog;
import nightkosh.gravestone_extended.entity.monster.pet.EnumUndeadMobType;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class EntityBarghest extends EntityUndeadDog {
    public EntityBarghest(World world) {
        super(world);
        this.setSize(1.2F, 1.7F);
        this.setMobType(EnumUndeadMobType.GHOST);
    }

    @Override
    public ResourceLocation getTexture() {
        return Resources.BARGHEST;
    }

    @Override
    protected void initEntityAI() {
        this.tasks.addTask(2, new EntityAIAttackMelee(this, 1, false));
        this.tasks.addTask(5, new EntityAIMoveTowardsRestriction(this, 1));
        this.tasks.addTask(8, new EntityAIWanderAvoidWater(this, 1));
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(20);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.45);
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(4);
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(40);
    }

    @Override
    public EnumCreatureAttribute getCreatureAttribute() {
        return EnumCreatureAttribute.UNDEAD;
    }

    @Override
    public void onLivingUpdate() {
        if (this.world.isRemote) {
            for (int i = 0; i < 10; i++) {
                double x = 1.5 - this.rand.nextDouble() * 3;
                double y = 0.5 + this.rand.nextDouble() * 1.5;
                double z = 1.5 - this.rand.nextDouble() * 3;
                this.world.spawnParticle(EnumParticleTypes.SUSPENDED_DEPTH,
//                this.world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL,
                        this.posX + x,
                        this.posY + y,
                        this.posZ + z,
                        0, 0, 0);
            }
        }

        super.onLivingUpdate();
    }

    @Override
    public boolean attackEntityAsMob(Entity entity) {
        if (super.attackEntityAsMob(entity)) {
            if (entity instanceof EntityPlayer) {
                ((EntityPlayer) entity).addPotionEffect(new PotionEffect(Potion.getPotionFromResourceLocation("blindness"), 200));
            }
            return true;
        } else {
            return false;
        }
    }

    @Override
    protected void playStepSound(BlockPos pos, Block blockIn) {
        this.playSound(SoundEvents.ENTITY_WOLF_STEP, 0.15F, 1);
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return GSSound.ENTITY_BARGHEST_GROWL;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource) {
        return SoundEvents.ENTITY_WOLF_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_WOLF_DEATH;
    }

    @Override
    protected float getSoundVolume() {
        return 0.4F;
    }
}
