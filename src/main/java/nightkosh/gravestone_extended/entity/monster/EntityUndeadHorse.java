package nightkosh.gravestone_extended.entity.monster;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.passive.HorseArmorType;
import net.minecraft.entity.passive.HorseType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.Iterator;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public abstract class EntityUndeadHorse extends EntityHorse {

//    protected static final UUID ARMOR_MODIFIER_UUID = UUID.randomUUID();
//    protected static final DataParameter<Integer> HORSE_TYPE = EntityDataManager.createKey(EntityUndeadHorse.class, DataSerializers.VARINT);
//    protected static final DataParameter<Integer> HORSE_VARIANT = EntityDataManager.createKey(EntityUndeadHorse.class, DataSerializers.VARINT);
//    protected static final DataParameter<Integer> HORSE_ARMOR = EntityDataManager.createKey(EntityUndeadHorse.class, DataSerializers.VARINT);

    protected String texturePrefix;
    protected String variantTexturePaths;
    protected static final String[] horseArmorTextures = new String[]{
            null,
            "textures/entity/horse/armor/horse_armor_iron.png",
            "textures/entity/horse/armor/horse_armor_gold.png",
            "textures/entity/horse/armor/horse_armor_diamond.png"
    };
    protected static final String[] horseArmorPrefix = new String[]{"", "meo", "goo", "dio"};


    protected boolean field_175508_bO = false;

    public EntityUndeadHorse(World worldIn) {
        super(worldIn);

        Iterator taskIterator = this.tasks.taskEntries.iterator();
        while (taskIterator.hasNext()) {
            boolean removeTask = false;
            EntityAIBase task = ((EntityAITasks.EntityAITaskEntry) taskIterator.next()).action;
            if (task instanceof EntityAIPanic) {
                removeTask = true;
            } else if (task instanceof EntityAIRunAroundLikeCrazy) {
                removeTask = true;
            } else if (task instanceof EntityAIMate) {
                removeTask = true;
            } else if (task instanceof EntityAIFollowParent) {
                removeTask = true;
            }

            if (removeTask) {
                taskIterator.remove();
            }
        }

        this.tasks.addTask(2, new EntityAIAttackMelee(this, 1, false));

        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();

        this.getAttributeMap().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE);
    }

    @Override
    public boolean attackEntityAsMob(Entity entity) {
        float f = (float) this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getAttributeValue();

        boolean flag = entity.attackEntityFrom(DamageSource.causeMobDamage(this), f);

        if (flag) {
            this.applyEnchantments(this, entity);
        }

        return flag;
    }

//    @Override
//    public boolean isUndead() {
//        return true;
//    }

//    @Override
//    public boolean isSterile() {
//        return true;
//    }

//    @Override
//    public boolean canWearArmor() {
//        return true;
//    }

    public boolean hasArmor() {
        NBTTagCompound nbt = new NBTTagCompound();
        this.writeEntityToNBT(nbt);

        return nbt.hasKey("ArmorItem", 10);
    }

    @Override
    public boolean canBeLeashedTo(EntityPlayer player) {
        return !this.getLeashed();
    }

    public abstract HorseType getUndeadHorseType();

    @Override
    protected boolean canDespawn() {
        return !this.isTame() && !this.hasCustomName();
    }

    @Override
    public EnumCreatureAttribute getCreatureAttribute() {
        return EnumCreatureAttribute.UNDEAD;
    }

    @Override
    public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, IEntityLivingData livingData) {
        Object entityLivingData = super.onInitialSpawn(difficulty, livingData);
        HorseType horseType = getUndeadHorseType();

        if (livingData != null && entityLivingData instanceof EntityHorse.GroupData) {
            horseType = ((EntityHorse.GroupData) livingData).horseType;
        } else {
            entityLivingData = new EntityHorse.GroupData(horseType, 0);
        }

        this.setType(horseType);
        return (IEntityLivingData) entityLivingData;
    }

    @Override
    public boolean processInteract(EntityPlayer player, EnumHand hand, @Nullable ItemStack stack) {
        if (stack != null && stack.getItem() == Items.SPAWN_EGG) {
            return super.processInteract(player, hand, stack);
        } else if (this.isTame() && this.isAdultHorse() && player.isSneaking()) {
            this.openGUI(player);
            return true;
        } else if (this.isRidable() && this.isBeingRidden()) {
            return super.processInteract(player, hand, stack);
        } else {
            if (stack != null) {
                boolean flag = false;
                HorseArmorType horsearmortype = HorseArmorType.getByItemStack(stack);

                if (horsearmortype != HorseArmorType.NONE) {
                    if (!this.isTame()) {
                        this.makeHorseRearWithSound();
                        return true;
                    }

                    this.openGUI(player);
                    return true;
                }

                float healedHealth = 0;
                short growth = 0;
                if (stack.getItem() == Items.BONE) {
                    healedHealth = 2;
                    growth = 20;
                } else if (stack.getItem() == Items.ROTTEN_FLESH) {
                    healedHealth = 4;
                    growth = 60;
                }

                if (this.getHealth() < this.getMaxHealth() && healedHealth > 0) {
                    this.heal(healedHealth);
                    flag = true;
                }

                if (!this.isAdultHorse() && growth > 0) {
                    this.addGrowth(growth);
                    flag = true;
                }

                if (flag) {
                    if (!player.capabilities.isCreativeMode && --stack.stackSize == 0) {
                        player.inventory.setInventorySlotContents(player.inventory.currentItem, (ItemStack) null);
                    }

                    return true;
                } else {
                    if (!this.isTame()) {
                        if (stack.interactWithEntity(player, this, hand)) {
                            return true;
                        }

                        this.makeHorseRearWithSound();
                        return true;
                    }

                    if (this.isRidable() && !this.isHorseSaddled() && stack.getItem() == Items.SADDLE) {
                        this.openGUI(player);
                        return true;
                    }
                }
            }

            if (this.isRidable() && this.isBeingRidden()) {
                if (stack != null && stack.interactWithEntity(player, this, hand)) {
                    return true;
                } else if (this.isTame()) {
                    this.mountHorse(player);
                    return true;
                }
            } else {
                return super.processInteract(player, hand, stack);
            }
        }
        return false;
    }

    @Override
    public void onLivingUpdate() {
        if (this.worldObj.isDaytime() && !this.worldObj.isRemote) {
            float brightness = this.getBrightness(1);
            BlockPos blockpos = new BlockPos(this.posX, (double) Math.round(this.posY), this.posZ);
            if (brightness > 0.5 && this.rand.nextFloat() * 30 < (brightness - 0.4) * 2 && this.worldObj.canSeeSky(blockpos) && !this.hasArmor()) {
                this.setFire(8);
            }
        }

        super.onLivingUpdate();
    }

    protected void mountHorse(EntityPlayer player) {
        player.rotationYaw = this.rotationYaw;
        player.rotationPitch = this.rotationPitch;
        this.setEatingHaystack(false);
        this.setRearing(false);
        if (!this.worldObj.isRemote) {
            player.startRiding(this);
        }
    }

    @Override
    protected boolean isMovementBlocked() {
        return this.isBeingRidden() && (this.isHorseSaddled() || this.getRidingEntity() != null && this.getRidingEntity().getControllingPassenger() instanceof EntityMob) ||
                this.isEatingHaystack() || this.isRearing();
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean hasTexture() {
        return this.field_175508_bO;
    }

    @SideOnly(Side.CLIENT)
    private void setHorseTexturePaths() {
        this.texturePrefix = "horse/";
        HorseType horseType = this.getType();

        this.texturePrefix = this.texturePrefix + horseType.ordinal() + "_";
        HorseArmorType horsearmortype = this.getHorseArmorType();


//        this.horseTexturesArray[2] = horsearmortype.getTextureName();
//        this.texturePrefix = this.texturePrefix + horsearmortype.getHash();
//
//        int horseArmorTextureNum = this.getHorseArmorIndexSynced();
        int horseArmorTextureNum = horsearmortype.ordinal();

        if (horseArmorTextureNum >= horseArmorTextures.length) {
            this.field_175508_bO = false;
        } else {
            this.variantTexturePaths = horseArmorTextures[horseArmorTextureNum];
            this.texturePrefix = this.texturePrefix + horseArmorPrefix[horseArmorTextureNum];
            this.field_175508_bO = true;
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public String getHorseTexture() {
        if (this.texturePrefix == null) {
            this.setHorseTexturePaths();
        }

        return this.texturePrefix;
    }

    @SideOnly(Side.CLIENT)
    public String getArmorTexturePaths() {
        if (this.texturePrefix == null) {
            this.setHorseTexturePaths();
        }

        return this.variantTexturePaths;
    }

//    @Override
//    public void setType(HorseType horseType) {
//        this.dataManager.set(HORSE_TYPE, Integer.valueOf(horseType.ordinal()));
//        this.resetTexturePrefix();
//    }

//    @Override
//    public void setHorseVariant(int horseVariant) {
//        this.dataManager.set(HORSE_VARIANT, Integer.valueOf(horseVariant));
//        this.resetTexturePrefix();
//    }

//    @Override
//    public void setHorseArmorStack(ItemStack itemStack) {
//        HorseArmorType horsearmortype = HorseArmorType.getByItemStack(itemStack);
//        if (horsearmortype != null) {
//            this.dataManager.set(HORSE_ARMOR, Integer.valueOf(horsearmortype.getOrdinal()));
//            this.resetTexturePrefix();
//
//            if (!this.worldObj.isRemote) {
//                this.getEntityAttribute(SharedMonsterAttributes.ARMOR).removeModifier(ARMOR_MODIFIER_UUID);
//                int i = horsearmortype.getProtection();
//
//                if (i != 0) {
//                    this.getEntityAttribute(SharedMonsterAttributes.ARMOR).applyModifier((new AttributeModifier(ARMOR_MODIFIER_UUID, "Horse armor bonus", (double) i, 0)).setSaved(false));
//                }
//            }
//        }
//    }

    protected void resetTexturePrefix() {
        this.texturePrefix = null;
    }

    @Override
    public void onUpdate() {
        if (this.worldObj.isRemote && this.dataManager.isDirty()) {
            this.resetTexturePrefix();
        }
        super.onUpdate();
    }

    @Override
    public boolean getCanSpawnHere() {
        return this.worldObj.getDifficulty() != EnumDifficulty.PEACEFUL && this.isValidLightLevel() &&
                this.getBlockPathWeight(new BlockPos(this.posX, this.getEntityBoundingBox().minY, this.posZ)) >= 0.0F;
    }

    protected boolean isValidLightLevel() {
        BlockPos blockpos = new BlockPos(this.posX, this.getEntityBoundingBox().minY, this.posZ);

        if (this.worldObj.getLightFor(EnumSkyBlock.SKY, blockpos) > this.rand.nextInt(32)) {
            return false;
        } else {
            int i = this.worldObj.getLightFromNeighbors(blockpos);

            if (this.worldObj.isThundering()) {
                int j = this.worldObj.getSkylightSubtracted();
                this.worldObj.setSkylightSubtracted(10);
                i = this.worldObj.getLightFromNeighbors(blockpos);
                this.worldObj.setSkylightSubtracted(j);
            }

            return i <= this.rand.nextInt(8);
        }
    }
}
