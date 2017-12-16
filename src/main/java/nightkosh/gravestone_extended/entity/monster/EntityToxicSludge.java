package nightkosh.gravestone_extended.entity.monster;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;
import nightkosh.gravestone_extended.config.ExtendedConfig;
import nightkosh.gravestone_extended.core.GSBlock;
import nightkosh.gravestone_extended.core.GSItem;
import nightkosh.gravestone_extended.core.GSPotion;
import nightkosh.gravestone_extended.helper.StateHelper;

import javax.annotation.Nullable;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class EntityToxicSludge extends EntitySlime {

    public EntityToxicSludge(World world) {
        super(world);
    }

    public static boolean replaceBlock(World world, BlockPos pos) {
        IBlockState state = world.getBlockState(pos);
        Block block = state.getBlock();
        if (block == Blocks.GRASS || block == Blocks.GRASS_PATH || block == Blocks.FARMLAND || block == Blocks.MYCELIUM ||
                block == Blocks.DIRT && state.equals(StateHelper.PODZOL)) {
            world.setBlockState(pos, StateHelper.DIRT);
            return true;
        } else if (block == Blocks.STONE && state.equals(StateHelper.STONE) || block == Blocks.MOSSY_COBBLESTONE) {
            world.setBlockState(pos, StateHelper.COBBLESTONE);
            return true;
        } else if (block == Blocks.STONEBRICK && (state.equals(StateHelper.STONEBRICK) || state.equals(StateHelper.STONEBRICK_MOSSY))) {
            world.setBlockState(pos, StateHelper.STONEBRICK_CRACKED);
            return true;
        }
        return false;
    }

    @Override
    protected void doBlockCollisions() {
        if (ExtendedConfig.toxicSludgeAndWaterChangeBlocks) {
            AxisAlignedBB axisalignedbb = this.getEntityBoundingBox();
            BlockPos.PooledMutableBlockPos posPoolBottom = BlockPos.PooledMutableBlockPos.retain(axisalignedbb.minX - 0.001, axisalignedbb.minY - 0.001, axisalignedbb.minZ - 0.001);
            BlockPos.PooledMutableBlockPos posPoolTop = BlockPos.PooledMutableBlockPos.retain(axisalignedbb.maxX + 0.001, axisalignedbb.maxY + 0.001, axisalignedbb.maxZ + 0.001);
            BlockPos.PooledMutableBlockPos pool = BlockPos.PooledMutableBlockPos.retain();

            if (this.world.isAreaLoaded(posPoolBottom, posPoolTop)) {
                for (int x = posPoolBottom.getX(); x <= posPoolTop.getX(); ++x) {
                    for (int y = posPoolBottom.getY(); y <= posPoolTop.getY(); ++y) {
                        for (int z = posPoolBottom.getZ(); z <= posPoolTop.getZ(); ++z) {
                            pool.setPos(x, y, z);
                            replaceBlock(this.world, pool);
                        }
                    }
                }
            }

            posPoolBottom.release();
            posPoolTop.release();
            pool.release();
        }

        super.doBlockCollisions();
    }

    @Override
    protected boolean spawnCustomParticles() {
        int size = this.getSlimeSize();
        for (int j = 0; j < size * 8; ++j) {
            float f = this.rand.nextFloat() * ((float) Math.PI * 2F);
            float f1 = this.rand.nextFloat() * 0.5F + 0.5F;
            float f2 = MathHelper.sin(f) * size * 0.5F * f1;
            float f3 = MathHelper.cos(f) * size * 0.5F * f1;
            World world = this.world;
            double d0 = this.posX + (double) f2;
            double d1 = this.posZ + (double) f3;
            world.spawnParticle(EnumParticleTypes.SMOKE_LARGE, d0, this.getEntityBoundingBox().minY, d1, 0, 0, 0);
        }

        this.playSound(SoundEvents.BLOCK_LAVA_EXTINGUISH, this.getSoundVolume(), ((this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1) / 0.8F);
        return true;
    }

    @Override
    protected EntityToxicSludge createInstance() {
        return new EntityToxicSludge(this.world);
    }

    @Override
    public void onDeath(DamageSource source) {
        if (!this.world.isRemote && this.getSlimeSize() > 1) {
            IBlockState state = world.getBlockState(this.getPosition());
            if (state.getBlock().isReplaceable(this.world, this.getPosition())) {
                world.setBlockState(this.getPosition(), GSBlock.TOXIC_WATER.getDefaultState());
            }
        }
        super.onDeath(source);
    }

    @Override
    public void applyEntityCollision(Entity entity) {
        super.applyEntityCollision(entity);

        if (entity instanceof EntityLivingBase && !(entity instanceof EntitySlime)) {
            ((EntityLivingBase) entity).addPotionEffect(new PotionEffect(GSPotion.RUST, 100));
        }
    }

    protected boolean isValidLightLevel() {
        BlockPos blockpos = new BlockPos(this.posX, this.getEntityBoundingBox().minY, this.posZ);

        if (this.world.getLightFor(EnumSkyBlock.SKY, blockpos) > this.rand.nextInt(32)) {
            return false;
        } else {
            int i = this.world.getLightFromNeighbors(blockpos);

            if (this.world.isThundering()) {
                int j = this.world.getSkylightSubtracted();
                this.world.setSkylightSubtracted(10);
                i = this.world.getLightFromNeighbors(blockpos);
                this.world.setSkylightSubtracted(j);
            }

            return i <= this.rand.nextInt(8);
        }
    }

    @Override
    protected Item getDropItem() {
        return this.getSlimeSize() == 1 ? GSItem.TOXIC_SLIME : null;
    }

    @Nullable
    protected ResourceLocation getLootTable() {
        return null;
    }

    @Override
    public boolean getCanSpawnHere() {
        return this.world.getDifficulty() != EnumDifficulty.PEACEFUL && this.posY <= 30 && this.isValidLightLevel() && this.world.getBlockState((new BlockPos(this)).down()).canEntitySpawn(this);
    }
}
