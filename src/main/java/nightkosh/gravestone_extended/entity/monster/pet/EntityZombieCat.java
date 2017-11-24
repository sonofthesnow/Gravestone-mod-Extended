package nightkosh.gravestone_extended.entity.monster.pet;

import net.minecraft.block.Block;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import nightkosh.gravestone_extended.core.Resources;
import nightkosh.gravestone_extended.entity.ai.EntityAINearestAttackableHorse;

import javax.annotation.Nullable;
import java.util.Random;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class EntityZombieCat extends EntityUndeadCat {

    private static final byte CAT_TYPES = 4;

    public EntityZombieCat(World world) {
        this(world, false);
    }

    public EntityZombieCat(World world, boolean isHusk) {
        super(world);

        this.setMobType(isHusk ? EnumUndeadMobType.HUSK : EnumUndeadMobType.ZOMBIE);

        this.tasks.addTask(2, new EntityAIAttackMelee(this, 1, false));
        this.tasks.addTask(4, new EntityAIMoveTowardsRestriction(this, 1));
        this.tasks.addTask(6, new EntityAIWander(this, 1));
//        this.tasks.addTask(3, new EntityAIAttackOnCollide(this, EntityVillager.class, 1, true));
//        this.tasks.addTask(3, new EntityAIAttackOnCollide(this, EntityWolf.class, 1, true));
//        this.tasks.addTask(3, new EntityAIAttackOnCollide(this, EntityOcelot.class, 1, true));
//        this.tasks.addTask(4, new EntityAIAttackLivingHorse(this, 1, false));
        this.tasks.addTask(5, new EntityAIMoveThroughVillage(this, 1, false));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityVillager.class, false));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityWolf.class, false));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityOcelot.class, false));
        this.targetTasks.addTask(4, new EntityAINearestAttackableTarget(this, EntityChicken.class, false));
        this.targetTasks.addTask(4, new EntityAINearestAttackableHorse(this, false));
    }

    @Override
    @Nullable
    public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, @Nullable IEntityLivingData livingdata) {
        livingdata = super.onInitialSpawn(difficulty, livingdata);

        Biome biome = this.getEntityWorld().getBiome(new BlockPos(this));
        if ((BiomeDictionary.hasType(biome, BiomeDictionary.Type.DRY) ||
                BiomeDictionary.hasType(biome, BiomeDictionary.Type.SAVANNA)) &&
                this.getRNG().nextInt(5) > 0) {
            this.setMobType(EnumUndeadMobType.HUSK);
        }

        this.setSkin(new Random().nextInt(CAT_TYPES));

        return livingdata;
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(10);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.5D);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public ResourceLocation getTexture() {
        if (this.dataManager.get(MOB_TYPE) == EnumUndeadMobType.HUSK.ordinal()) {
            switch (this.getSkin()) {
                case 1:
                    return Resources.GREEN_ZOMBIE_CAT_BLACK;
                case 2:
                    return Resources.GREEN_ZOMBIE_CAT_RED;
                case 3:
                    return Resources.GREEN_ZOMBIE_CAT_SIAMESE;
                case 0:
                default:
                    return Resources.GREEN_ZOMBIE_OZELOT;
            }
        } else {
            switch (this.getSkin()) {
                case 1:
                    return Resources.ZOMBIE_CAT_BLACK;
                case 2:
                    return Resources.ZOMBIE_CAT_RED;
                case 3:
                    return Resources.ZOMBIE_CAT_SIAMESE;
                case 0:
                default:
                    return Resources.ZOMBIE_OZELOT;
            }
        }
    }

    /**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
    @Override
    public void writeEntityToNBT(NBTTagCompound nbt) {
        super.writeEntityToNBT(nbt);

        nbt.setInteger("ZombieCatType", this.getSkin());
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    @Override
    public void readEntityFromNBT(NBTTagCompound nbt) {
        super.readEntityFromNBT(nbt);

        this.setSkin(nbt.getInteger("ZombieCatType"));
    }

    /**
     * Returns the sound this mob makes while it's alive.
     */
    @Override
    protected SoundEvent getAmbientSound() {
        return (this.rand.nextInt(4) == 0) ? SoundEvents.ENTITY_CAT_PURREOW : SoundEvents.ENTITY_CAT_AMBIENT;
    }

    /**
     * Returns the sound this mob makes when it is hurt.
     */
    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource) {
        return SoundEvents.ENTITY_CAT_HURT;
    }

    /**
     * Returns the sound this mob makes on death.
     */
    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_CAT_DEATH;
    }

    /**
     * Plays step sound at given x, y, z for the entity
     */
    @Override
    protected void playStepSound(BlockPos pos, Block block) {
    }

    /**
     * Returns the volume for the sounds this mob makes.
     */
    @Override
    protected float getSoundVolume() {
        return 0.4F;
    }

    /**
     * Returns the item ID for the item the mob drops on death.
     */
    @Override
    protected Item getDropItem() {
        return Items.ROTTEN_FLESH;
    }

    public int getSkin() {
        return this.dataManager.get(OCELOT_VARIANT).intValue();
    }

    public void setSkin(int skinId) {
        this.dataManager.set(OCELOT_VARIANT, Integer.valueOf(skinId));
    }
}
