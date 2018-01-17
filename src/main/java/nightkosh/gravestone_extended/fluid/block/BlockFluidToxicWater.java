package nightkosh.gravestone_extended.fluid.block;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityFishHook;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidClassic;
import nightkosh.gravestone_extended.config.ExtendedConfig;
import nightkosh.gravestone_extended.core.GSPotion;
import nightkosh.gravestone_extended.core.GSSound;
import nightkosh.gravestone_extended.core.GSTabs;
import nightkosh.gravestone_extended.core.ModInfo;
import nightkosh.gravestone_extended.entity.monster.EntityToxicSludge;
import nightkosh.gravestone_extended.entity.projectile.EntityBoneFishHook;
import nightkosh.gravestone_extended.fluid.FluidToxicWater;
import nightkosh.gravestone_extended.item.armor.IBoneArmor;

import java.util.Random;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class BlockFluidToxicWater extends BlockFluidClassic {

    public BlockFluidToxicWater() {
        super(FluidToxicWater.INSTANCE, Material.WATER);
        this.setUnlocalizedName("gs_toxic_water");
        this.setCreativeTab(GSTabs.otherItemsTab);
        this.setRegistryName(ModInfo.ID, FluidToxicWater.INSTANCE.getName());
        this.setTickRandomly(true);
    }

    @Override
    public void onEntityCollidedWithBlock(World world, BlockPos pos, IBlockState state, Entity entity) {
        super.onEntityCollidedWithBlock(world, pos, state, entity);
        boolean dealDamage = false;
        if (entity instanceof EntityLivingBase) {
            if (!(entity instanceof EntitySlime)) {
                EntityLivingBase baseEntity = (EntityLivingBase) entity;
                baseEntity.addPotionEffect(new PotionEffect(GSPotion.RUST, 100));
                if (baseEntity.getCreatureAttribute() != EnumCreatureAttribute.UNDEAD) {
                    for (PotionEffect potion : baseEntity.getActivePotionEffects()) {
                        if (Potion.getIdFromPotion(potion.getPotion()) == Potion.getIdFromPotion(GSPotion.BONE_SKIN)) {
                            return;
                        }
                    }

                    if (entity instanceof EntityPlayer) {
                        Iterable<ItemStack> equipment = entity.getArmorInventoryList();
                        int armorParts = 0;
                        if (equipment != null) {
                            for (ItemStack stack : equipment) {
                                if (!stack.isEmpty() && stack.isItemStackDamageable() && stack.getItem() instanceof IBoneArmor) {
                                    armorParts++;
                                }
                            }
                        }
                        dealDamage = armorParts != 4;
                    } else {
                        dealDamage = true;
                    }
                }
            }
        } else if (!(entity instanceof EntityItem) && !(entity instanceof EntityBoneFishHook)) {
            dealDamage = true;
            if (entity instanceof EntityFishHook) {
                meltEffect(world, pos);
                if (entity.ticksExisted > 20) {
                    entity.setDead();
                }
                return;
            }
        }
        if (dealDamage) {
            entity.attackEntityFrom(DamageSource.MAGIC, 1);
            //TODO no particles for mobs!!??
            meltEffect(world, pos);
        }
    }

    public void meltEffect(World world, BlockPos pos) {
        world.spawnParticle(EnumParticleTypes.SMOKE_LARGE, pos.getX(), pos.getY() + 1, pos.getZ(), 0, 0, 0);
        world.playSound(null, pos.getX(), pos.getY() + 1, pos.getZ(), SoundEvents.BLOCK_LAVA_EXTINGUISH, SoundCategory.BLOCKS, 0.4F, ((world.rand.nextFloat() - world.rand.nextFloat()) * 0.2F + 1) / 0.8F);
    }

    public void melt(World world, BlockPos liquidPos, BlockPos replacedPos) {
        if (EntityToxicSludge.replaceBlock(world, replacedPos)) {
//        for (int j = 0; j < 8; ++j) {
//            float f = world.rand.nextFloat() * ((float) Math.PI * 2F);
//            float f1 = world.rand.nextFloat() * 0.5F + 0.5F;
//            float f2 = MathHelper.sin(f) * 1 * 0.5F * f1;
//            float f3 = MathHelper.cos(f) * 1 * 0.5F * f1;
//            double d0 = replacedPos.getX() + (double) f2;
//            double d1 = replacedPos.getZ() + (double) f3;
//            world.spawnParticle(EnumParticleTypes.SMOKE_LARGE, d0, replacedPos.getY(), d1, 0, 0, 0);
//        }
//        this.playSound(SoundEvents.BLOCK_LAVA_EXTINGUISH, this.getSoundVolume(), ((this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F) / 0.8F);
//

//            world.spawnParticle(EnumParticleTypes.SMOKE_LARGE, replacedPos.getX(), replacedPos.getY() + 1, replacedPos.getZ(), 0, 0, 0);
//            world.playSound(replacedPos.getX(), replacedPos.getY() + 1, replacedPos.getZ(), SoundEvents.BLOCK_LAVA_EXTINGUISH, SoundCategory.BLOCKS, 0.2F + world.rand.nextFloat() * 0.2F, 0.9F + world.rand.nextFloat() * 0.15F, false);
//
//
            meltEffect(world, replacedPos.up());
        }
    }

    @Override
    public void onBlockAdded(World world, BlockPos pos, IBlockState state) {
        super.onBlockAdded(world, pos, state);

        if (ExtendedConfig.toxicSludgeAndWaterChangeBlocks) {
            melt(world, pos, pos.down());
            melt(world, pos, pos.east());
            melt(world, pos, pos.west());
            melt(world, pos, pos.south());
            melt(world, pos, pos.north());

            //TODO particles !!!!!!!!!!!!
            world.spawnParticle(EnumParticleTypes.SMOKE_LARGE, pos.getX(), pos.getY() + 1, pos.getZ(), 0, 0, 0);
            world.playSound(pos.getX(), pos.getY() + 1, pos.getZ(), SoundEvents.BLOCK_LAVA_EXTINGUISH, SoundCategory.BLOCKS, 0.2F + world.rand.nextFloat() * 0.2F, 0.9F + world.rand.nextFloat() * 0.15F, false);
        }
    }

    @Override
    public void randomDisplayTick(IBlockState state, World world, BlockPos pos, Random rand) {
        super.randomDisplayTick(state, world, pos, rand);
        double x = (double) pos.getX();
        double y = (double) pos.getY();
        double z = (double) pos.getZ();

        if (world.getBlockState(pos.up()).getMaterial() == Material.AIR && !world.getBlockState(pos.up()).isOpaqueCube()) {

            if (rand.nextInt(300) == 0) {
                double d8 = x + (double) rand.nextFloat();
                double d4 = y + state.getBoundingBox(world, pos).maxY;
                double d6 = z + (double) rand.nextFloat();
                world.spawnParticle(EnumParticleTypes.SMOKE_LARGE, d8, d4, d6, 0, 0, 0);
                world.playSound(d8, d4, d6, SoundEvents.BLOCK_LAVA_EXTINGUISH, SoundCategory.BLOCKS, 0.2F + rand.nextFloat() * 0.2F, 0.9F + rand.nextFloat() * 0.15F, false);
            }

            if (rand.nextInt(100) == 0) {
                world.playSound(x, y, z, GSSound.BUBBLING, SoundCategory.BLOCKS, 0.2F + rand.nextFloat() * 0.2F, 0.9F + rand.nextFloat() * 0.15F, false);
            }
        }

        if (rand.nextInt(50) == 0 && world.getBlockState(pos.down()).isTopSolid()) {
            Material material = world.getBlockState(pos.down(2)).getMaterial();

            if (!material.blocksMovement() && !material.isLiquid()) {
                double d3 = x + rand.nextFloat();
                double d5 = y - 1.05;
                double d7 = z + rand.nextFloat();

                world.spawnParticle(EnumParticleTypes.DRIP_WATER, d3, d5, d7, 0, 0, 0);
                world.playSound(d3, d5, d7, GSSound.DROP_OF_ACID, SoundCategory.BLOCKS, 0.2F + rand.nextFloat() * 0.2F, 0.9F + rand.nextFloat() * 0.15F, false);
            }
        }
    }
}
