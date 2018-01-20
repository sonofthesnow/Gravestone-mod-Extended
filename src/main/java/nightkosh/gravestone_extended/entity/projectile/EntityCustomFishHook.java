package nightkosh.gravestone_extended.entity.projectile;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
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
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.storage.loot.LootContext;
import net.minecraft.world.storage.loot.LootTableList;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.ItemFishedEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import nightkosh.gravestone_extended.core.GSBlock;
import nightkosh.gravestone_extended.core.GSItem;
import nightkosh.gravestone_extended.item.ItemFish;

import java.util.*;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class EntityCustomFishHook extends EntityFishHook {

    protected static final DataParameter<Integer> PLAYER_ID = EntityDataManager.createKey(EntityCustomFishHook.class, DataSerializers.VARINT);
    protected boolean inGround;
    protected int ticksInGround;
    protected int ticksInAir;
    protected int ticksCatchable;
    protected int ticksCaughtDelay;
    protected int ticksCatchableDelay;
    protected float fishApproachAngle;

    public EntityCustomFishHook(World world) {
        this(world, world.getPlayerEntityByUUID(Minecraft.getMinecraft().getSession().getProfile().getId()));
    }

    @SideOnly(Side.CLIENT)
    public EntityCustomFishHook(World world, EntityPlayer player, double x, double y, double z) {
        super(world, player, x, y, z);
    }

    public EntityCustomFishHook(World world, EntityPlayer player) {
        super(world, player);
    }

    @Override
    protected void entityInit() {
        super.entityInit();
        this.dataManager.register(PLAYER_ID, this.getAngler() == null ? 0 : this.getAngler().getEntityId());
    }

    @Override
    public void notifyDataManagerChange(DataParameter<?> key) {
        if (PLAYER_ID.equals(key)) {
            if (this.world.isRemote) {
                Entity entity = world.getEntityByID(this.dataManager.get(PLAYER_ID));
                if (entity instanceof EntityPlayer) {
                    EntityPlayer player = (EntityPlayer) entity;
                    if (player != null) {
                        this.getAngler().fishEntity = null;
                        this.angler = player;
                        this.angler.fishEntity = this;
                    }
                }
            }
        }

        super.notifyDataManagerChange(key);
    }

    @Override
    public void onEntityUpdate() {
        super.onEntityUpdate();

        if (!this.world.isRemote && this.getAngler() != null) {
            this.getDataManager().set(PLAYER_ID, this.getAngler().getEntityId());
        }
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
        boolean flag = isFishingPoleStack(mainStack);
        boolean flag1 = isFishingPoleStack(offStack);

        if (!this.getAngler().isDead && this.getAngler().isEntityAlive() && (flag || flag1) && this.getDistanceSq(this.getAngler()) <= 1024) {
            return false;
        } else {
            this.setDead();
            return true;
        }
    }

    protected boolean isFishingPoleStack(ItemStack stack) {
        return stack.getItem() == Items.FISHING_ROD;
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

            int minY = MathHelper.floor(this.getEntityBoundingBox().minY);
            Block liquidBlock = worldserver.getBlockState(new BlockPos(this.posX, minY, this.posZ)).getBlock();
            if (this.ticksCatchableDelay > 0) {
                this.fishApproachAngle = (float) (this.fishApproachAngle + this.rand.nextGaussian() * 4);
                float angle = this.fishApproachAngle * 0.0175F;
                float sin = MathHelper.sin(angle);
                float cos = MathHelper.cos(angle);
                double xPos = this.posX + sin * this.ticksCatchableDelay * 0.1;
                double yPos = minY + 1;
                double zPos = this.posZ + cos * this.ticksCatchableDelay * 0.1;

                if (MATERIAL_SET.contains(worldserver.getBlockState(new BlockPos(xPos, minY, zPos)).getMaterial())) {
                    if (this.rand.nextFloat() < 0.15) {
                        BUBBLE_PARTICLES.getOrDefault(liquidBlock, EntityCustomFishHook::spawnWaterBubbleParticles).spawn(worldserver, xPos, yPos - 0.1, zPos, 1, sin, 0.1, cos, 0);
                    }

                    float zOffset = sin * 0.04F;
                    float xOffset = cos * 0.04F;
                    WAKE_PARTICLES.getOrDefault(liquidBlock, EntityCustomFishHook::spawnWaterWakeParticles).spawn(worldserver, xPos, yPos, zPos, 0, xOffset, 0.01, -zOffset, 1);
                    WAKE_PARTICLES.getOrDefault(liquidBlock, EntityCustomFishHook::spawnWaterWakeParticles).spawn(worldserver, xPos, yPos, zPos, 0, -xOffset, 0.01, zOffset, 1);
                }
            } else {
                this.motionY = -0.4 * MathHelper.nextFloat(this.rand, 0.6F, 1);
                this.playSound(SoundEvents.ENTITY_BOBBER_SPLASH, 0.25F, 1 + (this.rand.nextFloat() - this.rand.nextFloat()) * 0.4F);
                double d3 = this.getEntityBoundingBox().minY + 0.5;

                BUBBLE_PARTICLES.getOrDefault(liquidBlock, EntityCustomFishHook::spawnWaterBubbleParticles).spawn(worldserver, this.posX, d3, this.posZ, (int) (1 + this.width * 20), this.width, 0, this.width, 0.2);
                WAKE_PARTICLES.getOrDefault(liquidBlock, EntityCustomFishHook::spawnWaterWakeParticles).spawn(worldserver, this.posX, d3, this.posZ, (int) (1 + this.width * 20), this.width, 0, this.width, 0.2);
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
                int minY = MathHelper.floor(this.getEntityBoundingBox().minY);
                double xPos = this.posX + MathHelper.sin(f6) * f7 * 0.1;
                double yPos = minY + 1;
                double zPos = this.posZ + MathHelper.cos(f6) * f7 * 0.1;

                if (MATERIAL_SET.contains(worldserver.getBlockState(new BlockPos(xPos, minY, zPos)).getMaterial())) {
                    SPLASH_PARTICLES.getOrDefault(worldserver.getBlockState(new BlockPos(this.posX, minY, this.posZ)), EntityCustomFishHook::spawnWaterSplashParticles)
                            .spawn(worldserver, this.rand, xPos, yPos, zPos);
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

    @FunctionalInterface
    interface ISpawnSplashParticles {
        public void spawn(WorldServer world, Random rand, double x, double y, double z);
    }

    @FunctionalInterface
    interface ISpawnBubbleParticles {
        public void spawn(WorldServer world, double x, double y, double z, int num, double xOffset, double yOffset, double zOffset, double speed);
    }

    @FunctionalInterface
    interface ISpawnWakeParticles {
        public void spawn(WorldServer world, double x, double y, double z, int num, double xOffset, double yOffset, double zOffset, double speed);
    }

    protected static final Set<Material> MATERIAL_SET = new HashSet<>();
    protected static final Map<Block, ISpawnSplashParticles> SPLASH_PARTICLES = new HashMap<>();
    protected static final Map<Block, ISpawnBubbleParticles> BUBBLE_PARTICLES = new HashMap<>();
    protected static final Map<Block, ISpawnWakeParticles> WAKE_PARTICLES = new HashMap<>();

    static {
        MATERIAL_SET.addAll(Arrays.asList(Material.WATER, Material.LAVA));

        SPLASH_PARTICLES.put(Blocks.WATER, EntityCustomFishHook::spawnWaterSplashParticles);
        SPLASH_PARTICLES.put(Blocks.FLOWING_WATER, EntityCustomFishHook::spawnWaterSplashParticles);
        SPLASH_PARTICLES.put(Blocks.LAVA, EntityCustomFishHook::spawnLavaSplashParticles);
        SPLASH_PARTICLES.put(Blocks.FLOWING_LAVA, EntityCustomFishHook::spawnLavaSplashParticles);

        BUBBLE_PARTICLES.put(Blocks.WATER, EntityCustomFishHook::spawnWaterBubbleParticles);
        BUBBLE_PARTICLES.put(Blocks.FLOWING_WATER, EntityCustomFishHook::spawnWaterBubbleParticles);
        BUBBLE_PARTICLES.put(Blocks.LAVA, EntityCustomFishHook::spawnLavaBubbleParticles);
        BUBBLE_PARTICLES.put(Blocks.FLOWING_LAVA, EntityCustomFishHook::spawnLavaBubbleParticles);

        WAKE_PARTICLES.put(Blocks.WATER, EntityCustomFishHook::spawnWaterWakeParticles);
        WAKE_PARTICLES.put(Blocks.FLOWING_WATER, EntityCustomFishHook::spawnWaterWakeParticles);
        WAKE_PARTICLES.put(Blocks.LAVA, EntityCustomFishHook::spawnLavaWakeParticles);
        WAKE_PARTICLES.put(Blocks.FLOWING_LAVA, EntityCustomFishHook::spawnLavaWakeParticles);
    }

    protected static void spawnWaterSplashParticles(WorldServer world, Random rand, double x, double y, double z) {
        world.spawnParticle(EnumParticleTypes.WATER_SPLASH, x, y, z, 2 + rand.nextInt(2), 0.1, 0, 0.1, 0);
    }

    protected static void spawnLavaSplashParticles(WorldServer world, Random rand, double x, double y, double z) {
        int num = 2 + rand.nextInt(2);
        world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, x, y, z, num, 0.1, 0, 0.1, 0);
        world.spawnParticle(EnumParticleTypes.LAVA, x, y, z, num, 0.1, 0, 0.1, 0);
    }

    protected static void spawnWaterBubbleParticles(WorldServer world, double x, double y, double z, int num, double xOffset, double yOffset, double zOffset, double speed) {
        world.spawnParticle(EnumParticleTypes.WATER_BUBBLE, x, y, z, num, xOffset, yOffset, zOffset, speed);
    }

    protected static void spawnLavaBubbleParticles(WorldServer world, double x, double y, double z, int num, double xOffset, double yOffset, double zOffset, double speed) {
        world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, x, y, z, num, xOffset, yOffset, zOffset, speed);
    }

    protected static void spawnWaterWakeParticles(WorldServer world, double x, double y, double z, int num, double xOffset, double yOffset, double zOffset, double speed) {
        world.spawnParticle(EnumParticleTypes.WATER_WAKE, x, y, z, num, xOffset, yOffset, zOffset, speed);
    }

    protected static void spawnLavaWakeParticles(WorldServer world, double x, double y, double z, int num, double xOffset, double yOffset, double zOffset, double speed) {
        world.spawnParticle(EnumParticleTypes.SMOKE_LARGE, x, y, z, num, xOffset, yOffset, zOffset, speed);
        world.spawnParticle(EnumParticleTypes.LAVA, x, y, z, num, xOffset, yOffset, zOffset, speed);
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
                    EntityItem entityitem = getCatchEntityItem(stack);
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

    protected EntityItem getCatchEntityItem(ItemStack stack) {
        return new EntityItem(this.world, this.posX, this.posY + 0.5, this.posZ, stack);
    }

    protected List<ItemStack> getCatch() {
        BlockPos pos = new BlockPos(this);
        IBlockState state = this.world.getBlockState(pos);
        List<ItemStack> result = new ArrayList<>(1);
        Set<BiomeDictionary.Type> biomeTypesList = BiomeDictionary.getTypes(world.getBiome(pos));
        float luck = (this.luck + this.getAngler().getLuck()) * 1.5F;
        if (state.getMaterial() == Material.WATER) {
            if (state.getBlock() == GSBlock.TOXIC_WATER) {
                List<ItemStack> tempList = getToxicWaterCatch(biomeTypesList);
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
                        tier1(tempList, biomeTypesList);
                    } else if (chance < 80) {
                        tier2(tempList, biomeTypesList);
                    } else if (chance < 95) {
                        tier3(tempList, biomeTypesList);
                    } else {
                        if (!world.canBlockSeeSky(pos)) {
                            if (pos.getY() < 50) {
                                tempList.add(new ItemStack(GSItem.FISH, 1, ItemFish.EnumFishType.SPECULAR_FISH.ordinal()));
                                if (pos.getY() < 40) {
                                    tempList.add(new ItemStack(GSItem.FISH, 1, ItemFish.EnumFishType.CAVEFISH.ordinal()));
                                    if (pos.getY() < 25) {
                                        tempList.add(new ItemStack(GSItem.FISH, 1, ItemFish.EnumFishType.ANGLER_FISH.ordinal()));
                                    }
                                }

                            } else {
                                tier4(tempList, biomeTypesList);
                            }
                        } else {
                            tier4(tempList, biomeTypesList);
                        }
                    }

                    result.add(tempList.get(this.rand.nextInt(tempList.size())));
                } else {
                    result = this.world.getLootTableManager().getLootTableFromLocation(LootTableList.GAMEPLAY_FISHING_TREASURE).generateLootForPools(this.rand, lootContextBuilder.build());
                }

            }
        } else if (state.getMaterial() == Material.LAVA) {
            List<ItemStack> tempList = getLavaCatch(biomeTypesList);
            result.add(tempList.get(this.rand.nextInt(tempList.size())));
        }

        return result;
    }

    protected List<ItemStack> getToxicWaterCatch(Set<BiomeDictionary.Type> biomeTypesList) {
        return new ArrayList<>();
    }

    protected List<ItemStack> getLavaCatch(Set<BiomeDictionary.Type> biomeTypesList) {
        return new ArrayList<>();
    }

    protected void tier1(List<ItemStack> tempList, Set<BiomeDictionary.Type> biomeTypesList) {
        if (biomeTypesList.contains(BiomeDictionary.Type.OCEAN) || biomeTypesList.contains(BiomeDictionary.Type.BEACH)) {
            tempList.add(new ItemStack(GSItem.FISH, 1, ItemFish.EnumFishType.BLUE_JELLYFISH.ordinal()));
            tempList.add(new ItemStack(GSItem.FISH, 1, ItemFish.EnumFishType.ANGELFISH.ordinal()));
            tempList.add(new ItemStack(GSItem.FISH, 1, ItemFish.EnumFishType.SQUID.ordinal()));
        }
        if (biomeTypesList.contains(BiomeDictionary.Type.END)) {
            tempList.add(new ItemStack(GSItem.FISH, 1, ItemFish.EnumFishType.ENDERFIN.ordinal()));
        }
        if (tempList.isEmpty()) {
            tempList.add(new ItemStack(Items.FISH, 1, 0)); //cod
            tempList.add(new ItemStack(GSItem.FISH, 1, ItemFish.EnumFishType.SQUID.ordinal()));
        }
    }

    protected void tier2(List<ItemStack> tempList, Set<BiomeDictionary.Type> biomeTypesList) {
        if (biomeTypesList.contains(BiomeDictionary.Type.OCEAN) || biomeTypesList.contains(BiomeDictionary.Type.BEACH)) {
            tempList.add(new ItemStack(Items.FISH, 1, 3)); //puffer
        }
        if (biomeTypesList.contains(BiomeDictionary.Type.END)) {
            tempList.add(new ItemStack(GSItem.FISH, 1, ItemFish.EnumFishType.PEARL_BASS.ordinal()));
        }
        if (biomeTypesList.contains(BiomeDictionary.Type.SANDY)) {
            tempList.add(new ItemStack(GSItem.FISH, 1, ItemFish.EnumFishType.SANDY_BASS.ordinal()));
        }
        if (biomeTypesList.contains(BiomeDictionary.Type.SNOWY)) {
            tempList.add(new ItemStack(GSItem.FISH, 1, ItemFish.EnumFishType.SNOWY_CRUCIAN.ordinal()));
        }
        if (biomeTypesList.contains(BiomeDictionary.Type.SWAMP)) {
            tempList.add(new ItemStack(GSItem.FISH, 1, ItemFish.EnumFishType.RUFFE.ordinal()));
        }
        if (biomeTypesList.contains(BiomeDictionary.Type.JUNGLE)) {
            tempList.add(new ItemStack(GSItem.FISH, 1, ItemFish.EnumFishType.PIRANHA.ordinal()));
        }
        if (tempList.isEmpty()) {
            tempList.add(new ItemStack(Items.FISH, 1, 1)); //salmon
        }
    }

    protected void tier3(List<ItemStack> tempList, Set<BiomeDictionary.Type> biomeTypesList) {
        if (biomeTypesList.contains(BiomeDictionary.Type.OCEAN) || biomeTypesList.contains(BiomeDictionary.Type.BEACH)) {
            tempList.add(new ItemStack(Items.FISH, 1, 2)); // clown
        }
        if (biomeTypesList.contains(BiomeDictionary.Type.END)) {
            tempList.add(new ItemStack(GSItem.FISH, 1, ItemFish.EnumFishType.CHORUS_KOI.ordinal()));
        }
        if (biomeTypesList.contains(BiomeDictionary.Type.SANDY)) {
            tempList.add(new ItemStack(GSItem.FISH, 1, ItemFish.EnumFishType.GOLDEN_KOI.ordinal()));
        }
        if (biomeTypesList.contains(BiomeDictionary.Type.SNOWY)) {
            tempList.add(new ItemStack(GSItem.FISH, 1, ItemFish.EnumFishType.FROST_MINNOW.ordinal()));
        }
        if (biomeTypesList.contains(BiomeDictionary.Type.SWAMP)) {
            tempList.add(new ItemStack(GSItem.FISH, 1, ItemFish.EnumFishType.MUD_TUNA.ordinal()));
        }
        if (biomeTypesList.contains(BiomeDictionary.Type.JUNGLE)) {
            tempList.add(new ItemStack(GSItem.FISH, 1, ItemFish.EnumFishType.SPARKLING_EEL.ordinal()));
        }
        if (tempList.isEmpty()) {
            tempList.add(new ItemStack(GSItem.FISH, 1, ItemFish.EnumFishType.EXPLOSIVE_CRUCIAN.ordinal()));
        }
    }

    protected void tier4(List<ItemStack> tempList, Set<BiomeDictionary.Type> biomeTypesList) {
        if (biomeTypesList.contains(BiomeDictionary.Type.OCEAN) || biomeTypesList.contains(BiomeDictionary.Type.BEACH)) {
            tempList.add(new ItemStack(GSItem.FISH, 1, ItemFish.EnumFishType.SPONGE_EATER.ordinal()));
        } else {
            tier3(tempList, biomeTypesList);
        }
    }
}
