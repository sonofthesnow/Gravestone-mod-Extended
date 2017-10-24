package nightkosh.gravestone_extended.entity.monster.horse;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.AbstractHorse;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.passive.HorseArmorType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import nightkosh.gravestone_extended.core.ModInfo;

import java.util.Iterator;
import java.util.UUID;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public abstract class EntityUndeadHorse extends AbstractHorse {

    protected static final UUID ARMOR_MODIFIER_UUID = UUID.randomUUID();
    protected static final DataParameter<Integer> HORSE_VARIANT = EntityDataManager.createKey(EntityUndeadHorse.class, DataSerializers.VARINT);
    protected static final DataParameter<Integer> HORSE_ARMOR = EntityDataManager.createKey(EntityUndeadHorse.class, DataSerializers.VARINT);

    protected final String[] horseTexturesArray = new String[2];
    protected String texturePrefix;

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

    protected void entityInit() {
        super.entityInit();
        this.dataManager.register(HORSE_VARIANT, 0);
        this.dataManager.register(HORSE_ARMOR, HorseArmorType.NONE.getOrdinal());
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

    public HorseArmorType getHorseArmorType() {
        return HorseArmorType.getByOrdinal(this.dataManager.get(HORSE_ARMOR));
    }

    public void onInventoryChanged(IInventory invBasic) {
        HorseArmorType horsearmortype = this.getHorseArmorType();
        super.onInventoryChanged(invBasic);
        HorseArmorType horsearmortype1 = this.getHorseArmorType();

        if (this.ticksExisted > 20 && horsearmortype != horsearmortype1 && horsearmortype1 != HorseArmorType.NONE) {
            this.playSound(SoundEvents.ENTITY_HORSE_ARMOR, 0.5F, 1.0F);
        }
    }

    public void setHorseArmorStack(ItemStack itemStackIn) {
        HorseArmorType horsearmortype = HorseArmorType.getByItemStack(itemStackIn);
        this.dataManager.set(HORSE_ARMOR, horsearmortype.getOrdinal());
        this.resetTexturePrefix();

        if (!this.world.isRemote) {
            this.getEntityAttribute(SharedMonsterAttributes.ARMOR).removeModifier(ARMOR_MODIFIER_UUID);
            int i = horsearmortype.getProtection();

            if (i != 0) {
                this.getEntityAttribute(SharedMonsterAttributes.ARMOR).applyModifier((new AttributeModifier(ARMOR_MODIFIER_UUID, "Horse armor bonus", (double) i, 0)).setSaved(false));
            }
        }
    }

    @SideOnly(Side.CLIENT)
    protected abstract String getUndeadHorseTexture();

    @SideOnly(Side.CLIENT)
    private void setHorseTexturePaths() {
        HorseArmorType horsearmortype = this.getHorseArmorType();
        this.horseTexturesArray[0] = getUndeadHorseTexture();
        this.horseTexturesArray[1] = horsearmortype.getTextureName();
        this.texturePrefix = ModInfo.ID + ":undead_horse/" + getUndeadHorseTexture() + horsearmortype.getHash();
    }

    public int getHorseVariant() {
        return this.dataManager.get(HORSE_VARIANT);
    }

    @SideOnly(Side.CLIENT)
    public String getHorseTexture() {
        if (this.texturePrefix == null) {
            this.setHorseTexturePaths();
        }

        return this.texturePrefix;
    }

    @SideOnly(Side.CLIENT)
    public String[] getVariantTexturePaths() {
        if (this.texturePrefix == null) {
            this.setHorseTexturePaths();
        }

        return this.horseTexturesArray;
    }

    public boolean hasArmor() {
        NBTTagCompound nbt = new NBTTagCompound();
        this.writeEntityToNBT(nbt);

        return nbt.hasKey("ArmorItem", 10);
    }

    @Override
    public boolean canBeLeashedTo(EntityPlayer player) {
        return !this.getLeashed();
    }

    @Override
    protected boolean canDespawn() {
        return !this.isTame() && !this.hasCustomName();
    }

    @Override
    public EnumCreatureAttribute getCreatureAttribute() {
        return EnumCreatureAttribute.UNDEAD;
    }

    public void setHorseVariant(int variant)
    {
        this.dataManager.set(HORSE_VARIANT, variant);
        this.resetTexturePrefix();
    }
    @Override
    public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, IEntityLivingData livingdata) {

        livingdata = super.onInitialSpawn(difficulty, livingdata);
        int i;

        if (livingdata instanceof EntityHorse.GroupData)
        {
            i = ((EntityHorse.GroupData)livingdata).variant;
        }
        else
        {
            i = this.rand.nextInt(7);
            livingdata = new EntityHorse.GroupData(i);
        }

        this.setHorseVariant(i | this.rand.nextInt(5) << 8);
        return livingdata;
    }

    public boolean processInteract(EntityPlayer player, EnumHand hand) {
        ItemStack itemstack = player.getHeldItem(hand);
        boolean stackNotEmpty = !itemstack.isEmpty();

        if (stackNotEmpty && itemstack.getItem() == Items.SPAWN_EGG) {
            return super.processInteract(player, hand);
        } else if (!this.isTame() && !this.isChild() && player.isSneaking()) {
            this.openGUI(player);
            return true;
        } else if (this.isBeingRidden()) {
            return super.processInteract(player, hand);
        } else if (stackNotEmpty) {
            boolean flag = false;
            float healedHealth = 0;
            short growth = 0;
            if (itemstack.getItem() == Items.BONE) {
                healedHealth = 2;
                growth = 20;
            } else if (itemstack.getItem() == Items.ROTTEN_FLESH) {
                healedHealth = 4;
                growth = 60;
            }

            if (this.getHealth() < this.getMaxHealth() && healedHealth > 0) {
                this.heal(healedHealth);
                flag = true;
            }

            if (this.isChild() && growth > 0) {
                this.addGrowth(growth);
                flag = true;
            }


            if (flag) {
                if (!player.capabilities.isCreativeMode) {
                    itemstack.shrink(1);
                }

                return true;
            } else {
                if (!this.isTame()) {
                    if (itemstack.interactWithEntity(player, this, hand)) {
                        return true;
                    }

                    this.setRearing(true);
                    return true;
                }
            }

            if (!this.isHorseSaddled() && itemstack.getItem() == Items.SADDLE) {
                this.openGUI(player);
                return true;
            }

            if (itemstack.interactWithEntity(player, this, hand)) {
                return true;
            }
        }

        this.mountTo(player);
        return true;
    }

//    @Override
//    public boolean processInteract(EntityPlayer player, EnumHand hand) {
//            boolean flag = false;
//            HorseArmorType horsearmortype = HorseArmorType.getByItemStack(stack);
//
//            if (horsearmortype != HorseArmorType.NONE) {
//                if (!this.isTame()) {
//                    this.setRearing(true);
//                    return true;
//                }
//
//                this.openGUI(player);
//                return true;
//            }
//
//
//        }
//
//        if (this.isRidable() && this.isBeingRidden()) {
//            if (stack != null && stack.interactWithEntity(player, this, hand)) {
//                return true;
//            } else if (this.isTame()) {
//                this.mountHorse(player);
//                return true;
//            }
//        } else {
//            return super.processInteract(player, hand, stack);
//        }
//    }
//
//    return false;
//}

    @Override
    public void onLivingUpdate() {
        if (this.getEntityWorld().isDaytime() && !this.getEntityWorld().isRemote) {
            float brightness = this.getBrightness(1);
            BlockPos blockpos = new BlockPos(this.posX, (double) Math.round(this.posY), this.posZ);
            if (brightness > 0.5 && this.rand.nextFloat() * 30 < (brightness - 0.4) * 2 && this.getEntityWorld().canSeeSky(blockpos) && !this.hasArmor()) {
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
        if (!this.getEntityWorld().isRemote) {
            player.startRiding(this);
        }
    }

    @Override
    protected boolean isMovementBlocked() {
        return this.isBeingRidden() && (this.isHorseSaddled() || this.getRidingEntity() != null && this.getRidingEntity().getControllingPassenger() instanceof EntityMob) ||
                this.isEatingHaystack() || this.isRearing();
    }

//    @SideOnly(Side.CLIENT)
//    private void setHorseTexturePaths() {
//        this.texturePrefix = "horse/";
//        HorseType horseType = this.getType();
//
//        this.texturePrefix = this.texturePrefix + horseType.ordinal() + "_";
//        HorseArmorType horsearmortype = this.getHorseArmorType();
//
//
////        this.horseTexturesArray[2] = horsearmortype.getTextureName();
////        this.texturePrefix = this.texturePrefix + horsearmortype.getHash();
////
////        int horseArmorTextureNum = this.getHorseArmorIndexSynced();
//        int horseArmorTextureNum = horsearmortype.ordinal();
//
//        if (horseArmorTextureNum >= horseArmorTextures.length) {
//            this.field_175508_bO = false;
//        } else {
//            this.variantTexturePaths = horseArmorTextures[horseArmorTextureNum];
//            this.texturePrefix = this.texturePrefix + horseArmorPrefix[horseArmorTextureNum];
//            this.field_175508_bO = true;
//        }
//    }
//
//    @Override
//    @SideOnly(Side.CLIENT)
//    public String getHorseTexture() {
//        if (this.texturePrefix == null) {
//            this.setHorseTexturePaths();
//        }
//
//        return this.texturePrefix;
//    }

//    @SideOnly(Side.CLIENT)
//    public String getArmorTexturePaths() {
//        if (this.texturePrefix == null) {
//            this.setHorseTexturePaths();
//        }
//
//        return this.variantTexturePaths;
//    }


//    ------------------------------


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
//            if (!this.getEntityWorld().isRemote) {
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
        if (this.getEntityWorld().isRemote && this.dataManager.isDirty()) {
            this.resetTexturePrefix();
        }
        super.onUpdate();
    }

    @Override
    public boolean getCanSpawnHere() {
        return this.getEntityWorld().getDifficulty() != EnumDifficulty.PEACEFUL && this.isValidLightLevel() &&
                this.getBlockPathWeight(new BlockPos(this.posX, this.getEntityBoundingBox().minY, this.posZ)) >= 0.0F;
    }

    protected boolean isValidLightLevel() {
        BlockPos blockpos = new BlockPos(this.posX, this.getEntityBoundingBox().minY, this.posZ);

        if (this.getEntityWorld().getLightFor(EnumSkyBlock.SKY, blockpos) > this.rand.nextInt(32)) {
            return false;
        } else {
            int i = this.getEntityWorld().getLightFromNeighbors(blockpos);

            if (this.getEntityWorld().isThundering()) {
                int j = this.getEntityWorld().getSkylightSubtracted();
                this.getEntityWorld().setSkylightSubtracted(10);
                i = this.getEntityWorld().getLightFromNeighbors(blockpos);
                this.getEntityWorld().setSkylightSubtracted(j);
            }

            return i <= this.rand.nextInt(8);
        }
    }
}
