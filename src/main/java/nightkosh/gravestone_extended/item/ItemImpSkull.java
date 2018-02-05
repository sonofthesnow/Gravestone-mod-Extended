package nightkosh.gravestone_extended.item;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import nightkosh.gravestone_extended.core.GSTabs;
import nightkosh.gravestone_extended.core.ModInfo;

import javax.annotation.Nullable;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class ItemImpSkull extends Item {

    public ItemImpSkull() {
        this.setUnlocalizedName("gravestone.imp_skull");
        this.setRegistryName(ModInfo.ID, getItemRegistryName());
        this.setCreativeTab(GSTabs.otherItemsTab);

        this.addPropertyOverride(new ResourceLocation(getPropertyName()), getPropertyGetter());
    }

    @Nullable
    @Override
    public EntityEquipmentSlot getEquipmentSlot(ItemStack stack) {
        return EntityEquipmentSlot.HEAD;
    }

    protected String getItemRegistryName() {
        return "gs_imp_skull";
    }

    protected String getPropertyName() {
        return "angle";
    }

    protected IItemPropertyGetter getPropertyGetter() {
        return new IItemPropertyGetter() {
            @SideOnly(Side.CLIENT)
            double rotation;
            @SideOnly(Side.CLIENT)
            double rota;
            @SideOnly(Side.CLIENT)
            long lastUpdateTick;

            @SideOnly(Side.CLIENT)
            public float apply(ItemStack stack, @Nullable World world, @Nullable EntityLivingBase entity) {
                if (entity == null || stack.isOnItemFrame()) {
                    return 0;
                } else {
                    if (world == null) {
                        world = entity.world;
                    }

                    double d0;

                    if (isCorrectDimension(world)) {
                        double rotationYaw = MathHelper.positiveModulo(entity.rotationYaw / 360D, 1);
                        double d2 = this.getSpawnToAngle(world, entity) / (Math.PI * 2);
                        d0 = 0.5 - (rotationYaw - 0.25 - d2);
                    } else {
                        d0 = Math.random();
                    }

                    return MathHelper.positiveModulo((float) this.wobble(world, d0), 1);
                }
            }

            @SideOnly(Side.CLIENT)
            private double wobble(World world, double d0) {
                if (world.getTotalWorldTime() != this.lastUpdateTick) {
                    this.lastUpdateTick = world.getTotalWorldTime();
                    d0 = MathHelper.positiveModulo(d0 - this.rotation + 0.5, 1) - 0.5;
                    this.rota += d0 * 0.1;
                    this.rota *= 0.8;
                    this.rotation = MathHelper.positiveModulo(this.rotation + this.rota, 1);
                }

                return this.rotation;
            }

            @SideOnly(Side.CLIENT)
            private double getSpawnToAngle(World world, Entity entity) {
                BlockPos pos = getPos(world);
                return Math.atan2(pos.getZ() - entity.posZ, pos.getX() - entity.posX);
            }
        };
    }

    protected boolean isCorrectDimension(World world) {
        return world.provider.isNether();
    }

    protected BlockPos getPos(World world) {
        //BlockPos pos = world.getChunkProvider().getNearestStructurePos(world, "Fortress", new BlockPos(entity), false);
        return world.getSpawnPoint();
    }
}
