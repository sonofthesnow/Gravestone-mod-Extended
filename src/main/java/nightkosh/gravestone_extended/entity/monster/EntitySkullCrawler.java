package nightkosh.gravestone_extended.entity.monster;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import nightkosh.gravestone_extended.core.GSBlock;
import nightkosh.gravestone_extended.entity.ai.AIHideInBones;
import nightkosh.gravestone_extended.entity.ai.AIHideInPilesOfBones;
import nightkosh.gravestone_extended.entity.ai.AISummonSkullCrawler;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class EntitySkullCrawler extends EntityMob {
    private static final DataParameter<Byte> CLIMBING = EntityDataManager.createKey(EntitySkullCrawler.class, DataSerializers.BYTE);

    private AISummonSkullCrawler summonAI;
    private AIHideInBones hideInBonesAI;

    protected WallSide wallSide;

    public EntitySkullCrawler(World world) {
        super(world);
        this.setSize(0.8F, 0.8F);

        this.tasks.addTask(1, new EntityAISwimming(this));
        this.tasks.addTask(3, this.summonAI = new AISummonSkullCrawler(this));
        this.tasks.addTask(4, new EntityAIAttackMelee(this, 1, false));
        this.tasks.addTask(5, this.hideInBonesAI = new AIHideInBones(this));
        this.tasks.addTask(6, new AIHideInPilesOfBones(this));
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true, new Class[0]));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
    }

    @Override
    public float getEyeHeight() {
        return 0.5F;
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(12);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.4D);
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(1.5);
    }

    /**
     * returns if this entity triggers Block.onEntityWalking on the blocks they
     * walk on. used for spiders and wolves to prevent them from trampling crops
     */
    @Override
    protected boolean canTriggerWalking() {
        return false;
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.ENTITY_SKELETON_AMBIENT;
    }

    @Override
    protected SoundEvent getHurtSound() {
        return SoundEvents.ENTITY_SKELETON_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_SKELETON_DEATH;
    }

    @Override
    protected void playStepSound(BlockPos pos, net.minecraft.block.Block block) {
        this.playSound(SoundEvents.ENTITY_SPIDER_STEP, 0.15F, 1);
    }

    /**
     * Called when the entity is attacked.
     */
    @Override
    public boolean attackEntityFrom(DamageSource source, float par2) {
        if (this.isEntityInvulnerable(source)) {
            return false;
        } else {
            if ((source instanceof EntityDamageSource || source == DamageSource.magic)) {
                summonAI.resetSummonColdown();
            }

            return super.attackEntityFrom(source, par2);
        }
    }

    @Override
    protected void entityInit() {
        super.entityInit();
        this.dataManager.register(CLIMBING, Byte.valueOf((byte) 0));
    }

    /**
     * Called to update the entity's position/logic.
     */
    @Override
    public void onUpdate() {
        super.onUpdate();

        if (!this.worldObj.isRemote) {
            this.setBesideClimbableBlock(this.isCollidedHorizontally);
        }
    }

    @Override
    protected boolean isValidLightLevel() {
        return true;
    }

    @Override
    public boolean getCanSpawnHere() {
        if (super.getCanSpawnHere()) {
            EntityPlayer entityplayer = this.worldObj.getClosestPlayerToEntity(this, 5);
            return entityplayer == null;
        } else {
            return false;
        }
    }

    /**
     * Takes a coordinate in and returns a weight to determine how likely this
     * creature will try to path to the block. Args: x, y, z
     */
    @Override
    public float getBlockPathWeight(BlockPos pos) {
        return this.worldObj.getBlockState(new BlockPos(pos.down())).getBlock().equals(GSBlock.boneBlock) ? 10 : super.getBlockPathWeight(pos);
    }

    /**
     * Returns the item ID for the item the mob drops on death.
     */
    @Override
    protected Item getDropItem() {
        return Items.BONE;
    }

    protected ItemStack getRareDrop() {
        return new ItemStack(Items.SKULL, 1, 0);
    }

    @Override
    protected void dropFewItems(boolean p_70628_1_, int lootAttr) {
        super.dropFewItems(p_70628_1_, lootAttr);

        if (p_70628_1_ && (this.rand.nextInt(100) <= (lootAttr * 10))) {
            this.entityDropItem(getRareDrop(), 0);
        }
    }

    /**
     * returns true if this entity is by a ladder, false otherwise
     */
    @Override
    public boolean isOnLadder() {
        return this.isBesideClimbableBlock();
    }

    /**
     * Sets the Entity inside a web block.
     */
    @Override
    public void setInWeb() {
    }

    @Override
    public EnumCreatureAttribute getCreatureAttribute() {
        return EnumCreatureAttribute.UNDEAD;
    }

    /**
     * Returns true if the WatchableObject (Byte) is 0x01 otherwise returns
     * false. The WatchableObject is updated using setBesideClimableBlock.
     */
    public boolean isBesideClimbableBlock() {
        return (this.dataManager.get(CLIMBING).byteValue() & 1) != 0;
    }

    /**
     * Updates the WatchableObject (Byte) created in entityInit(), setting it to
     * 0x01 if par1 is true or 0x00 if it is false.
     */
    public void setBesideClimbableBlock(boolean par1) {
        byte b0 = this.dataManager.get(CLIMBING).byteValue();

        if (par1) {
            b0 = (byte) (b0 | 1);
        } else {
            b0 &= -2;
        }

        this.dataManager.set(CLIMBING, Byte.valueOf(b0));
//        switch (getHorizontalFacing()) {
//            case WEST:
//                if (worldObj.isSideSolid(getPosition().south(), EnumFacing.NORTH)) {
//                    wallSide = WallSide.LEFT;
//                } else if (worldObj.isSideSolid(getPosition().north(), EnumFacing.SOUTH)) {
//                    wallSide = WallSide.RIGHT;
//                } else {
//                    wallSide = WallSide.FORWARD;
//                }
//                break;
//            case NORTH:
//                if (worldObj.isSideSolid(getPosition().west(), EnumFacing.EAST)) {
//                    wallSide = WallSide.LEFT;
//                } else if (worldObj.isSideSolid(getPosition().east(), EnumFacing.WEST)) {
//                    wallSide = WallSide.RIGHT;
//                } else {
//                    wallSide = WallSide.FORWARD;
//                }
//                break;
//            case EAST:
//                if (worldObj.isSideSolid(getPosition().north(), EnumFacing.SOUTH)) {
//                    wallSide = WallSide.LEFT;
//                } else if (worldObj.isSideSolid(getPosition().south(), EnumFacing.NORTH)) {
//                    wallSide = WallSide.RIGHT;
//                } else {
//                    wallSide = WallSide.FORWARD;
//                }
//                break;
//            case SOUTH:
//                if (worldObj.isSideSolid(getPosition().east(), EnumFacing.WEST)) {
//                    wallSide = WallSide.LEFT;
//                } else if (worldObj.isSideSolid(getPosition().west(), EnumFacing.EAST)) {
//                    wallSide = WallSide.RIGHT;
//                } else {
//                    wallSide = WallSide.FORWARD;
//                }
//                break;
//        }
    }

    @Override
    public boolean attackEntityAsMob(Entity entity) {
        if (super.attackEntityAsMob(entity)) {
            if (entity instanceof EntityLivingBase) {
                ((EntityLivingBase) entity).addPotionEffect(getPotionEffect());
            }
            return true;
        } else {
            return false;
        }
    }

    protected PotionEffect getPotionEffect() {
        return new PotionEffect(MobEffects.SLOWNESS, 200);
    }

    /**
     * Called frequently so the entity can update its state every tick as
     * required. For example, zombies and skeletons use this to react to
     * sunlight and start to burn.
     */
    @Override
    public void onLivingUpdate() {
        if (this.worldObj.isDaytime() && !this.worldObj.isRemote) {
            float f = this.getBrightness(1);

            if (!this.isImmuneToFire && f > 0 && this.rand.nextFloat() * 30 < (f - 0.4F) * 2
                    && this.worldObj.canBlockSeeSky(new BlockPos(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posY), MathHelper.floor_double(this.posZ)))) {
                this.setFire(8);
            }
        }

        super.onLivingUpdate();
    }

    public boolean canHideInBones() {
        return true;
    }

    public AIHideInBones getHideInBonesAI() {
        return hideInBonesAI;
    }

    public WallSide getWallSide() {
        return wallSide;
    }

    public enum SkullCrawlerType {
        skeleton,
        wither,
        zombie
    }

    public enum WallSide {
        FORWARD,
        LEFT,
        RIGHT,
        BEHIND
    }
}
