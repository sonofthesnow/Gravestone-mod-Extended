package nightkosh.gravestone_extended.entity.monster;

import com.google.common.base.Optional;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.passive.HorseArmorType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.Iterator;
import java.util.UUID;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public abstract class EntityUndeadHorse extends EntityHorse {

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

    public static enum EnumHorseType {
        ZOMBIE_HORSE_TYPE(3),
        SKELETON_HORSE_TYPE(4);

        private int id;

        EnumHorseType(int id) {
            this.id = id;
        }

        public int getId() {
            return this.id;
        }
    }

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

//        this.tasks.addTask(2, new EntityAIAttackOnCollide(this, EntityPlayer.class, 1, false));

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
        int i = 0;

        if (entity instanceof EntityLivingBase) {
            f += EnchantmentHelper.func_152377_a(this.getHeldItem(EnumHand.MAIN_HAND), ((EntityLivingBase) entity).getCreatureAttribute());
            i += EnchantmentHelper.getKnockbackModifier(this);
        }

        boolean flag = entity.attackEntityFrom(DamageSource.causeMobDamage(this), f);

        if (flag) {
            if (i > 0) {
                entity.addVelocity((double) (-MathHelper.sin(this.rotationYaw * (float) Math.PI / 180F) * (float) i * 0.5F), 0.1, (double) (MathHelper.cos(this.rotationYaw * (float) Math.PI / 180F) * (float) i * 0.5F));
                this.motionX *= 0.6;
                this.motionZ *= 0.6;
            }

            int j = EnchantmentHelper.getFireAspectModifier(this);

            if (j > 0) {
                entity.setFire(j * 4);
            }

            this.applyEnchantments(this, entity);
        }

        return flag;
    }

    @Override
    public boolean isUndead() {
        return true;
    }

    @Override
    public boolean isSterile() {
        return true;
    }

    @Override
    public boolean canWearArmor() {
        return true;
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

    public abstract EnumHorseType getUndeadHorseType();

    public void setHorseType(EnumHorseType horseType) {
        super.setHorseType(horseType.getId());
    }

    @Override
    protected boolean canDespawn() {
        return !this.isTame();
    }

    @Override
    public EnumCreatureAttribute getCreatureAttribute() {
        return EnumCreatureAttribute.UNDEAD;
    }

    @Override
    public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, IEntityLivingData livingData) {
        Object entityLivingData = super.onInitialSpawn(difficulty, livingData);
        int horseType = getUndeadHorseType().getId();

        if (entityLivingData instanceof EntityHorse.GroupData) {
            ((EntityHorse.GroupData) entityLivingData).horseType = horseType;
        } else {
            entityLivingData = new EntityHorse.GroupData(horseType, 0);
        }

        this.setHorseType(horseType);
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
                if (this.canWearArmor()) {
                    byte armorType = -1;
                    if (stack.getItem() == Items.IRON_HORSE_ARMOR) {
                        armorType = 1;
                    } else if (stack.getItem() == Items.GOLDEN_HORSE_ARMOR) {
                        armorType = 2;
                    } else if (stack.getItem() == Items.DIAMOND_HORSE_ARMOR) {
                        armorType = 3;
                    }

                    if (armorType >= 0) {
                        if (!this.isTame()) {
                            this.makeHorseRearWithSound();
                            return true;
                        }

                        this.openGUI(player);
                        return true;
                    }
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
        return this.getRidingEntity().getControllingPassenger() != null && this.isHorseSaddled() || this.isEatingHaystack() || this.isRearing();
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean func_175507_cI() {
        return this.field_175508_bO;
    }

    @SideOnly(Side.CLIENT)
    private void setHorseTexturePaths() {
        this.texturePrefix = "horse/";
        int horseType = this.getHorseType();

        this.texturePrefix = this.texturePrefix + horseType + "_";
        int horseArmorTextureNum = this.getHorseArmorIndexSynced();

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

    private static final UUID ARMOR_MODIFIER_UUID = UUID.fromString("556E1665-8B10-40C8-8F9D-CF9B1667F295");
    private static final DataParameter<Integer> HORSE_TYPE = EntityDataManager.createKey(EntityHorse.class, DataSerializers.VARINT);
    private static final DataParameter<Integer> HORSE_VARIANT = EntityDataManager.createKey(EntityHorse.class, DataSerializers.VARINT);
    private static final DataParameter<Optional<UUID>> OWNER_UNIQUE_ID = EntityDataManager.createKey(EntityHorse.class, DataSerializers.OPTIONAL_UNIQUE_ID);
    private static final DataParameter<Integer> HORSE_ARMOR = EntityDataManager.createKey(EntityHorse.class, DataSerializers.VARINT);

    @Override
    public void setHorseType(int horseType) {
        this.dataManager.set(HORSE_TYPE, Integer.valueOf(horseType));
        this.resetTexturePrefix();
    }

    @Override
    public void setHorseVariant(int horseVariant) {
        this.dataManager.set(HORSE_VARIANT, Integer.valueOf(horseVariant));
        this.resetTexturePrefix();
    }

    @Override
    public void setHorseArmorStack(ItemStack itemStack) {
        HorseArmorType horsearmortype = HorseArmorType.getByItemStack(itemStack);
        this.dataManager.set(HORSE_ARMOR, Integer.valueOf(horsearmortype.getOrdinal()));
        this.resetTexturePrefix();

        if (!this.worldObj.isRemote) {
            this.getEntityAttribute(SharedMonsterAttributes.ARMOR).removeModifier(ARMOR_MODIFIER_UUID);
            int i = horsearmortype.getProtection();

            if (i != 0) {
                this.getEntityAttribute(SharedMonsterAttributes.ARMOR).applyModifier((new AttributeModifier(ARMOR_MODIFIER_UUID, "Horse armor bonus", (double) i, 0)).setSaved(false));
            }
        }
    }

    protected void resetTexturePrefix() {
        this.texturePrefix = null;
    }

    public int getHorseArmorIndex(ItemStack itemStack) {
        if (itemStack == null) {
            return 0;
        } else {
            Item item = itemStack.getItem();
            return item == Items.IRON_HORSE_ARMOR ? 1 : (item == Items.GOLDEN_HORSE_ARMOR ? 2 : (item == Items.DIAMOND_HORSE_ARMOR ? 3 : 0));
        }
    }

    @Override
    public void onUpdate() {
        if (this.worldObj.isRemote && this.dataManager.isDirty()) {
            this.resetTexturePrefix();
        }
        super.onUpdate();
    }
}
