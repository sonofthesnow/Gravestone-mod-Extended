package nightkosh.gravestone_extended.entity.projectile;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityFishHook;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.stats.StatList;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.*;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.storage.loot.LootContext;
import net.minecraft.world.storage.loot.LootTableList;
import net.minecraft.world.storage.loot.RandomValueRange;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.ItemFishedEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import nightkosh.gravestone_extended.core.GSBlock;
import nightkosh.gravestone_extended.core.GSItem;
import nightkosh.gravestone_extended.item.ItemFish;
import nightkosh.gravestone_extended.item.tools.IBoneFishingPole;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class EntityBoneFishHook extends EntityFishHook {

    protected static final DataParameter<Integer> DATA_HOOKED_ENTITY = EntityDataManager.createKey(EntityBoneFishHook.class, DataSerializers.VARINT);
    protected boolean inGround;
    protected int ticksInGround;
    protected int ticksInAir;
    protected int ticksCatchable;
    protected int ticksCaughtDelay;
    protected int ticksCatchableDelay;
    protected float fishApproachAngle;
    public Entity caughtEntity;
    protected State currentState = State.FLYING;
    protected int luck;
    protected int lureSpeed;

    @SideOnly(Side.CLIENT)
    public EntityBoneFishHook(World world) {
        this(world, world.getPlayerEntityByUUID(Minecraft.getMinecraft().getSession().getProfile().getId()));
    }

    @SideOnly(Side.CLIENT)
    public EntityBoneFishHook(World world, EntityPlayer player, double x, double y, double z) {
        super(world, player, x, y, z);
    }

    public EntityBoneFishHook(World world, EntityPlayer player) {
        super(world, player);
    }


    @Override
    public void setLureSpeed(int lureSpeed) {
        this.lureSpeed = lureSpeed;
    }

    @Override
    public void setLuck(int luck) {
        this.luck = luck;
    }

    @Override
    protected void entityInit() {
        this.getDataManager().register(DATA_HOOKED_ENTITY, 0);
    }

    @Override
    public void notifyDataManagerChange(DataParameter<?> key) {
        if (DATA_HOOKED_ENTITY.equals(key)) {
            int i = this.getDataManager().get(DATA_HOOKED_ENTITY);
            this.caughtEntity = i > 0 ? this.world.getEntityByID(i - 1) : null;
        }

        super.notifyDataManagerChange(key);
    }

    @Override
    public void onUpdate() {
        if (!this.world.isRemote) {
            this.setFlag(6, this.isGlowing());
        }

        this.onEntityUpdate();

        if (this.getAngler() == null) {
            this.setDead();
        } else if (this.world.isRemote || !this.shouldStopFishing()) {
            if (this.inGround) {
                this.ticksInGround++;

                if (this.ticksInGround >= 1200) {
                    this.setDead();
                    return;
                }
            }

            float f = 0;
            BlockPos pos = new BlockPos(this);
            IBlockState state = this.world.getBlockState(pos);
            boolean isInLava = state.getMaterial() == Material.LAVA;
            boolean isInWater = state.getMaterial() == Material.WATER;

            if (isInLava && !this.isImmuneToFire) {
                this.setDead();
            }
            if (isInWater || isInLava) {
                f = BlockLiquid.getBlockLiquidHeight(state, this.world, pos);
            }

            if (this.currentState == State.FLYING) {
                if (this.caughtEntity != null) {
                    this.motionX = 0;
                    this.motionY = 0;
                    this.motionZ = 0;
                    this.currentState = State.HOOKED_IN_ENTITY;
                    return;
                }

                if (f > 0) {
                    this.motionX *= 0.3;
                    this.motionY *= 0.2;
                    this.motionZ *= 0.3;
                    this.currentState = State.BOBBING;
                    return;
                }

                if (!this.world.isRemote) {
                    this.checkCollision();
                }

                if (!this.inGround && !this.onGround && !this.collidedHorizontally) {
                    this.ticksInAir++;
                } else {
                    this.ticksInAir = 0;
                    this.motionX = 0;
                    this.motionY = 0;
                    this.motionZ = 0;
                }
            } else {
                if (this.currentState == State.HOOKED_IN_ENTITY) {
                    if (this.caughtEntity != null) {
                        if (this.caughtEntity.isDead) {
                            this.caughtEntity = null;
                            this.currentState = State.FLYING;
                        } else {
                            this.posX = this.caughtEntity.posX;
                            double d2 = this.caughtEntity.height;
                            this.posY = this.caughtEntity.getEntityBoundingBox().minY + d2 * 0.8;
                            this.posZ = this.caughtEntity.posZ;
                            this.setPosition(this.posX, this.posY, this.posZ);
                        }
                    }

                    return;
                }

                if (this.currentState == State.BOBBING) {
                    this.motionX *= 0.9;
                    this.motionZ *= 0.9;
                    double d0 = this.posY + this.motionY - pos.getY() - f;

                    if (Math.abs(d0) < 0.01) {
                        d0 += Math.signum(d0) * 0.1;
                    }

                    this.motionY -= d0 * this.rand.nextFloat() * 0.2;

                    if (!this.world.isRemote && f > 0) {
                        this.catchingFish(pos);
                    }
                }
            }

            if (!isInWater && !isInLava) {
                this.motionY -= 0.03;
            }

            this.move(MoverType.SELF, this.motionX, this.motionY, this.motionZ);
            this.updateRotation();

            this.motionX *= 0.92;
            this.motionY *= 0.92;
            this.motionZ *= 0.92;
            this.setPosition(this.posX, this.posY, this.posZ);
        }
    }

    protected boolean shouldStopFishing() {
        ItemStack mainStack = this.getAngler().getHeldItemMainhand();
        ItemStack offStack = this.getAngler().getHeldItemOffhand();
        boolean flag = mainStack.getItem() instanceof IBoneFishingPole;
        boolean flag1 = offStack.getItem() instanceof IBoneFishingPole;

        if (!this.getAngler().isDead && this.getAngler().isEntityAlive() && (flag || flag1) && this.getDistanceSq(this.getAngler()) <= 1024) {
            return false;
        } else {
            this.setDead();
            return true;
        }
    }

    protected void updateRotation() {
        float f = MathHelper.sqrt(this.motionX * this.motionX + this.motionZ * this.motionZ);
        this.rotationYaw = (float) (MathHelper.atan2(this.motionX, this.motionZ) * (180 / Math.PI));

        for (this.rotationPitch = (float) (MathHelper.atan2(this.motionY, f) * (180 / Math.PI)); this.rotationPitch - this.prevRotationPitch < -180; this.prevRotationPitch -= 360) {
            ;
        }

        while (this.rotationPitch - this.prevRotationPitch >= 180) {
            this.prevRotationPitch += 360;
        }

        while (this.rotationYaw - this.prevRotationYaw < -180) {
            this.prevRotationYaw -= 360;
        }

        while (this.rotationYaw - this.prevRotationYaw >= 180) {
            this.prevRotationYaw += 360;
        }

        this.rotationPitch = this.prevRotationPitch + (this.rotationPitch - this.prevRotationPitch) * 0.2F;
        this.rotationYaw = this.prevRotationYaw + (this.rotationYaw - this.prevRotationYaw) * 0.2F;
    }

    protected void checkCollision() {
        Vec3d vec = new Vec3d(this.posX, this.posY, this.posZ);
        Vec3d vec2 = new Vec3d(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);
        RayTraceResult raytraceresult = this.world.rayTraceBlocks(vec, vec2, false, true, false);
        vec = new Vec3d(this.posX, this.posY, this.posZ);
        vec2 = new Vec3d(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);

        if (raytraceresult != null) {
            vec2 = new Vec3d(raytraceresult.hitVec.x, raytraceresult.hitVec.y, raytraceresult.hitVec.z);
        }

        Entity entity = null;
        List<Entity> list = this.world.getEntitiesWithinAABBExcludingEntity(this, this.getEntityBoundingBox().expand(this.motionX, this.motionY, this.motionZ).grow(1));
        double d0 = 0;

        for (Entity entity1 : list) {
            if (this.canBeHooked(entity1) && (entity1 != this.getAngler() || this.ticksInAir >= 5)) {
                AxisAlignedBB axisalignedbb = entity1.getEntityBoundingBox().grow(0.3);
                RayTraceResult result = axisalignedbb.calculateIntercept(vec, vec2);

                if (result != null) {
                    double distance = vec.squareDistanceTo(result.hitVec);

                    if (distance < d0 || d0 == 0) {
                        entity = entity1;
                        d0 = distance;
                    }
                }
            }
        }

        if (entity != null) {
            raytraceresult = new RayTraceResult(entity);
        }

        if (raytraceresult != null && raytraceresult.typeOfHit != RayTraceResult.Type.MISS) {
            if (raytraceresult.typeOfHit == RayTraceResult.Type.ENTITY) {
                this.caughtEntity = raytraceresult.entityHit;
                this.setHookedEntity();
            } else {
                this.inGround = true;
            }
        }
    }

    protected void setHookedEntity() {
        this.getDataManager().set(DATA_HOOKED_ENTITY, this.caughtEntity.getEntityId() + 1);
    }

    protected void catchingFish(BlockPos pos) {
        WorldServer worldserver = (WorldServer) this.world;
        int i = 1;
        BlockPos blockpos = pos.up();

        if (this.rand.nextFloat() < 0.25 && this.world.isRainingAt(blockpos)) {
            i++;
        }

        if (this.rand.nextFloat() < 0.5 && !this.world.canSeeSky(blockpos)) {
            i--;
        }

        if (this.ticksCatchable > 0) {
            this.ticksCatchable--;

            if (this.ticksCatchable <= 0) {
                this.ticksCaughtDelay = 0;
                this.ticksCatchableDelay = 0;
            } else {
                this.motionY -= 0.2 * this.rand.nextFloat() * this.rand.nextFloat();
            }
        } else if (this.ticksCatchableDelay > 0) {
            this.ticksCatchableDelay -= i;

            if (this.ticksCatchableDelay > 0) {
                this.fishApproachAngle = (float) (this.fishApproachAngle + this.rand.nextGaussian() * 4);
                float f = this.fishApproachAngle * 0.017453292F;
                float f1 = MathHelper.sin(f);
                float f2 = MathHelper.cos(f);
                double d0 = this.posX + f1 * this.ticksCatchableDelay * 0.1;
                double d1 = MathHelper.floor(this.getEntityBoundingBox().minY) + 1;
                double d2 = this.posZ + f2 * this.ticksCatchableDelay * 0.1;
                Block block = worldserver.getBlockState(new BlockPos(d0, d1 - 1, d2)).getBlock();

                //TODO toxic water
                //TODO lava
                if (block == Blocks.WATER || block == Blocks.FLOWING_WATER || block == Blocks.LAVA || block == Blocks.FLOWING_LAVA || block == GSBlock.TOXIC_WATER) {
                    if (this.rand.nextFloat() < 0.15) {
                        worldserver.spawnParticle(EnumParticleTypes.WATER_BUBBLE, d0, d1 - 0.1, d2, 1, f1, 0.1, f2, 0);
                    }

                    float f3 = f1 * 0.04F;
                    float f4 = f2 * 0.04F;
                    worldserver.spawnParticle(EnumParticleTypes.WATER_WAKE, d0, d1, d2, 0, f4, 0.01, -f3, 1);
                    worldserver.spawnParticle(EnumParticleTypes.WATER_WAKE, d0, d1, d2, 0, -f4, 0.01, f3, 1);
                }
            } else {
                this.motionY = -0.4 * MathHelper.nextFloat(this.rand, 0.6F, 1);
                this.playSound(SoundEvents.ENTITY_BOBBER_SPLASH, 0.25F, 1 + (this.rand.nextFloat() - this.rand.nextFloat()) * 0.4F);
                double d3 = this.getEntityBoundingBox().minY + 0.5;
                //TODO toxic water
                //TODO lava
                worldserver.spawnParticle(EnumParticleTypes.WATER_BUBBLE, this.posX, d3, this.posZ, (int) (1 + this.width * 20), this.width, 0, this.width, 0.2);
                worldserver.spawnParticle(EnumParticleTypes.WATER_WAKE, this.posX, d3, this.posZ, (int) (1 + this.width * 20), this.width, 0, this.width, 0.2);
                this.ticksCatchable = MathHelper.getInt(this.rand, 20, 40);
            }
        } else if (this.ticksCaughtDelay > 0) {
            this.ticksCaughtDelay -= i;
            float f5 = 0.15F;

            if (this.ticksCaughtDelay < 20) {
                f5 = f5 + (20 - this.ticksCaughtDelay) * 0.05F;
            } else if (this.ticksCaughtDelay < 40) {
                f5 = f5 + (40 - this.ticksCaughtDelay) * 0.02F;
            } else if (this.ticksCaughtDelay < 60) {
                f5 = f5 + (60 - this.ticksCaughtDelay) * 0.01F;
            }

            if (this.rand.nextFloat() < f5) {
                float f6 = MathHelper.nextFloat(this.rand, 0, 360) * 0.017453292F;
                float f7 = MathHelper.nextFloat(this.rand, 25, 60);
                double d4 = this.posX + MathHelper.sin(f6) * f7 * 0.1;
                double d5 = MathHelper.floor(this.getEntityBoundingBox().minY) + 1;
                double d6 = this.posZ + MathHelper.cos(f6) * f7 * 0.1;
                Block block1 = worldserver.getBlockState(new BlockPos((int) d4, (int) d5 - 1, (int) d6)).getBlock();


                if (block1 == Blocks.WATER || block1 == Blocks.FLOWING_WATER) {
                    worldserver.spawnParticle(EnumParticleTypes.WATER_SPLASH, d4, d5, d6, 2 + this.rand.nextInt(2), 0.1, 0, 0.1, 0);
                }
                if (block1 == GSBlock.TOXIC_WATER) {//TODO toxic water
                    worldserver.spawnParticle(EnumParticleTypes.WATER_SPLASH, d4, d5, d6, 2 + this.rand.nextInt(2), 0.1, 0, 0.1, 0);
                }
                if (block1 == Blocks.LAVA || block1 == Blocks.FLOWING_LAVA) { //TODO lava
                    worldserver.spawnParticle(EnumParticleTypes.WATER_SPLASH, d4, d5, d6, 2 + this.rand.nextInt(2), 0.1, 0, 0.1, 0);
                }
            }

            if (this.ticksCaughtDelay <= 0) {
                this.fishApproachAngle = MathHelper.nextFloat(this.rand, 0, 360);
                this.ticksCatchableDelay = MathHelper.getInt(this.rand, 20, 80);
            }
        } else {
            this.ticksCaughtDelay = MathHelper.getInt(this.rand, 100, 600);
            this.ticksCaughtDelay -= this.lureSpeed * 20 * 5;
        }
    }

    @Override
    public int handleHookRetraction() {
        if (!this.world.isRemote && this.getAngler() != null) {
            int i = 0;

            net.minecraftforge.event.entity.player.ItemFishedEvent event = null;
            if (this.caughtEntity != null) {
                this.bringInHookedEntity();
                this.world.setEntityState(this, (byte) 31);
                i = this.caughtEntity instanceof EntityItem ? 3 : 5;
            } else if (this.ticksCatchable > 0) {
                List<ItemStack> result = this.getCatch();
                event = new ItemFishedEvent(result, this.inGround ? 2 : 1, this);
                MinecraftForge.EVENT_BUS.post(event);

                if (event.isCanceled()) {
                    this.setDead();
                    return event.getRodDamage();
                }

                for (ItemStack stack : result) {
                    EntityItem entityitem = new EntityItem(this.world, this.posX, this.posY + 1.5, this.posZ, stack);// TODO + 1.5
                    double d0 = this.getAngler().posX - this.posX;
                    double d1 = this.getAngler().posY - this.posY;
                    double d2 = this.getAngler().posZ - this.posZ;
                    double d3 = MathHelper.sqrt(d0 * d0 + d1 * d1 + d2 * d2);

                    entityitem.motionX = d0 * 0.1;
                    entityitem.motionY = d1 * 0.1 + MathHelper.sqrt(d3) * 0.08;
                    entityitem.motionZ = d2 * 0.1;
                    this.world.spawnEntity(entityitem);
                    this.getAngler().world.spawnEntity(new EntityXPOrb(this.getAngler().world, this.getAngler().posX, this.getAngler().posY + 0.5, this.getAngler().posZ + 0.5, this.rand.nextInt(6) + 1));
                    Item item = stack.getItem();

                    if (item == Items.FISH || item == Items.COOKED_FISH) {
                        this.getAngler().addStat(StatList.FISH_CAUGHT, 1);
                    }
                }

                i = 1;
            }

            if (this.inGround) {
                i = 2;
            }

            this.setDead();
            return event == null ? i : event.getRodDamage();
        } else {
            return 0;
        }
    }

    protected List<ItemStack> getCatch() {
        BlockPos pos = new BlockPos(this);
        IBlockState state = this.world.getBlockState(pos);
        List<ItemStack> result = new ArrayList<>(1);
        Set<BiomeDictionary.Type> biomeTypesList = BiomeDictionary.getTypes(world.getBiome(pos));
        float luck = (this.luck + this.getAngler().getLuck()) * 1.5F;
        if (state.getMaterial() == Material.WATER) {
            if (state.getBlock() == GSBlock.TOXIC_WATER) {
                int chance = this.rand.nextInt(100) + Math.round(luck);
                List<ItemStack> tempList = new ArrayList<>();

                if (chance < 50) {
                    tempList.add(new ItemStack(Items.BONE));
                    tempList.add(new ItemStack(Items.ROTTEN_FLESH));
                    tempList.add(new ItemStack(Items.SPIDER_EYE));
                    tempList.add(new ItemStack(GSItem.FISH, 1, ItemFish.EnumFishType.GREEN_JELLYFISH.ordinal()));
                    tempList.add(new ItemStack(GSItem.FISH, 1, ItemFish.EnumFishType.BONE_FISH.ordinal()));
                } else if (chance < 80) {
                    tempList.add(new ItemStack(GSItem.FISH, 1, ItemFish.EnumFishType.SPOOKYFIN.ordinal()));
                } else if (chance < 95) {
                    tempList.add(new ItemStack(GSItem.FISH, 1, ItemFish.EnumFishType.CURSED_KOI.ordinal()));
                } else {
                    if (chance < 98) {
                        tempList.add(new ItemStack(Blocks.SKULL, 1, 0)); // SKELETON
                        tempList.add(new ItemStack(Blocks.SKULL, 1, 2)); // ZOMBIE
                    } else {
                        EnchantmentHelper.addRandomEnchantment(rand, new ItemStack(GSItem.ENCHANTED_SKULL, 1, 0), new RandomValueRange(30, 40).generateInt(rand), true);
                    }
                }
                result.add(tempList.get(this.rand.nextInt(tempList.size())));
            } else {
                LootContext.Builder lootContextBuilder = new LootContext.Builder((WorldServer) this.world);
                lootContextBuilder.withLuck(this.luck + this.getAngler().getLuck());

                int chance = this.rand.nextInt(100) + Math.round(luck);

                List<ItemStack> tempList = new ArrayList<>();
                if (chance < 10) {
                    result = this.world.getLootTableManager().getLootTableFromLocation(LootTableList.GAMEPLAY_FISHING_JUNK).generateLootForPools(this.rand, lootContextBuilder.build());
                } else if (chance < 90) {
//                    result = this.world.getLootTableManager().getLootTableFromLocation(LootTableList.GAMEPLAY_FISHING_FISH).generateLootForPools(this.rand, lootContextBuilder.build());
                    // 60 25 13 2
                    chance = this.rand.nextInt(100) + Math.round(luck);
                    if (chance < 50) {
                        tempList.add(new ItemStack(Items.FISH, 1, 0)); //cod
                        if (biomeTypesList.contains(BiomeDictionary.Type.OCEAN) || biomeTypesList.contains(BiomeDictionary.Type.BEACH)) {
                            tempList.add(new ItemStack(GSItem.FISH, 1, ItemFish.EnumFishType.BLUE_JELLYFISH.ordinal()));
                        } else if (biomeTypesList.contains(BiomeDictionary.Type.SANDY)) {
                            tempList.add(new ItemStack(GSItem.FISH, 1, ItemFish.EnumFishType.GOLDEN_KOI.ordinal()));
                        } else if (biomeTypesList.contains(BiomeDictionary.Type.SNOWY)) {
                            tempList.add(new ItemStack(GSItem.FISH, 1, ItemFish.EnumFishType.FROST_MINNOW.ordinal()));
                        } else if (biomeTypesList.contains(BiomeDictionary.Type.SWAMP)) {
                            tempList.add(new ItemStack(GSItem.FISH, 1, ItemFish.EnumFishType.MUD_TUNA.ordinal()));
                        } else if (biomeTypesList.contains(BiomeDictionary.Type.JUNGLE)) {
                            tempList.add(new ItemStack(GSItem.FISH, 1, ItemFish.EnumFishType.PIRANHA.ordinal()));
                        } else if (biomeTypesList.contains(BiomeDictionary.Type.END)) {
                            tempList = new ArrayList<>();
                            tempList.add(new ItemStack(GSItem.FISH, 1, ItemFish.EnumFishType.ENDERFIN.ordinal()));
                        }
                    } else if (chance < 80) {
                        tempList.add(new ItemStack(Items.FISH, 1, 1)); //salmon

                        if (biomeTypesList.contains(BiomeDictionary.Type.OCEAN) || biomeTypesList.contains(BiomeDictionary.Type.BEACH)) {
                            tempList.add(new ItemStack(Items.FISH, 1, 3)); //puffer
                        } else if (biomeTypesList.contains(BiomeDictionary.Type.END)) {
                            tempList = new ArrayList<>();
                            tempList.add(new ItemStack(GSItem.FISH, 1, ItemFish.EnumFishType.PEARL_BASS.ordinal()));
                        } else {
                            tempList.add(new ItemStack(GSItem.FISH, 1, ItemFish.EnumFishType.GOLDEN_KOI.ordinal()));
                            tempList.add(new ItemStack(GSItem.FISH, 1, ItemFish.EnumFishType.MUD_TUNA.ordinal()));
                        }
                    } else if (chance < 95) {
                        if (biomeTypesList.contains(BiomeDictionary.Type.OCEAN) || biomeTypesList.contains(BiomeDictionary.Type.BEACH)) {
                            tempList.add(new ItemStack(Items.FISH, 1, 2)); // clown
                        } else if (biomeTypesList.contains(BiomeDictionary.Type.END)) {
                            tempList.add(new ItemStack(GSItem.FISH, 1, ItemFish.EnumFishType.CHORUS_KOI.ordinal()));
                        } else {
                            tempList.add(new ItemStack(Items.FISH, 1, 3)); //puffer
                            tempList.add(new ItemStack(GSItem.FISH, 1, ItemFish.EnumFishType.BLUE_JELLYFISH.ordinal()));
                            tempList.add(new ItemStack(GSItem.FISH, 1, ItemFish.EnumFishType.FROST_MINNOW.ordinal()));
                            tempList.add(new ItemStack(GSItem.FISH, 1, ItemFish.EnumFishType.PIRANHA.ordinal()));
                        }
                    } else {
                        if (!world.canBlockSeeSky(pos)) {
                            if (pos.getY() < 50) {
                                tempList.add(new ItemStack(GSItem.FISH, 1, ItemFish.EnumFishType.SPECULAR_FISH.ordinal()));
                                if (pos.getY() < 40) {
                                    tempList.add(new ItemStack(GSItem.FISH, 1, ItemFish.EnumFishType.CAVEFISH.ordinal()));
                                    if (pos.getY() < 25) {
                                        // TODO Angler Fish
                                    }
                                }

                            } else {
                                tempList.add(new ItemStack(Items.FISH, 1, 2)); // clown
                            }
                        } else {
                            tempList.add(new ItemStack(Items.FISH, 1, 2)); // clown
                        }
                    }

                    result.add(tempList.get(this.rand.nextInt(tempList.size())));
                } else {
                    result = this.world.getLootTableManager().getLootTableFromLocation(LootTableList.GAMEPLAY_FISHING_TREASURE).generateLootForPools(this.rand, lootContextBuilder.build());
                }

            }
        } else if (state.getMaterial() == Material.LAVA) {
            List<ItemStack> tempList = new ArrayList<>();

            int chance = this.rand.nextInt(100) + Math.round(luck);
            if (!biomeTypesList.contains(BiomeDictionary.Type.NETHER)) {
                if (chance < 80) {
                    tempList.add(new ItemStack(GSItem.FISH, 1, ItemFish.EnumFishType.OBSIDIFISH.ordinal()));
                } else if (chance < 95) {
                    tempList.add(new ItemStack(GSItem.FISH, 1, ItemFish.EnumFishType.MAGMA_JELLYFISH.ordinal()));
                } else {
                    if (chance < 98) {
                        tempList.add(new ItemStack(Blocks.SKULL, 1, 1)); //WITHER SKULL
                    } else {
                        EnchantmentHelper.addRandomEnchantment(rand, new ItemStack(GSItem.ENCHANTED_SKULL, 1, 1), new RandomValueRange(40, 50).generateInt(rand), true);
                    }
                }
            } else {
                if (chance < 50) {
                    tempList.add(new ItemStack(GSItem.FISH, 1, ItemFish.EnumFishType.OBSIDIFISH.ordinal()));
                    tempList.add(new ItemStack(GSItem.FISH, 1, ItemFish.EnumFishType.NETHER_SALMON.ordinal()));
                } else if (chance < 80) {
                    tempList.add(new ItemStack(GSItem.FISH, 1, ItemFish.EnumFishType.MAGMA_JELLYFISH.ordinal()));
                    tempList.add(new ItemStack(GSItem.FISH, 1, ItemFish.EnumFishType.NETHER_SALMON.ordinal()));
                    tempList.add(new ItemStack(GSItem.FISH, 1, ItemFish.EnumFishType.QUARTZ_COD.ordinal()));
                } else if (chance < 95) {
                    tempList.add(new ItemStack(GSItem.FISH, 1, ItemFish.EnumFishType.FLAREFIN_KOI.ordinal()));
                    tempList.add(new ItemStack(GSItem.FISH, 1, ItemFish.EnumFishType.BLAZE_COD.ordinal()));
                } else {
                    if (chance < 98) {
                        tempList.add(new ItemStack(Blocks.SKULL, 1, 1)); //WITHER SKULL
                    } else {
                        EnchantmentHelper.addRandomEnchantment(rand, new ItemStack(GSItem.ENCHANTED_SKULL, 1, 1), new RandomValueRange(40, 50).generateInt(rand), true);
                    }
                }
            }

            result.add(tempList.get(this.rand.nextInt(tempList.size())));
        }

        return result;
    }

    static enum State {
        FLYING,
        HOOKED_IN_ENTITY,
        BOBBING
    }
}
