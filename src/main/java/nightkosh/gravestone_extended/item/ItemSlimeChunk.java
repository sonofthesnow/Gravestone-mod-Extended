package nightkosh.gravestone_extended.item;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import nightkosh.gravestone_extended.core.GSItem;

import javax.annotation.Nullable;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class ItemSlimeChunk extends ItemImpSkull {

    public ItemSlimeChunk() {
        this.setUnlocalizedName("gravestone.slime_chunk");
    }

    @Override
    protected String getItemRegistryName() {
        return "gs_slime_chunk";
    }

    @Override
    protected String getPropertyName() {
        return "size";
    }

    protected IItemPropertyGetter getPropertyGetter() {
        return new IItemPropertyGetter() {
            long lastUpdateTick;
            byte tick = 0;
            byte size = 0;

            @SideOnly(Side.CLIENT)
            public float apply(ItemStack stack, @Nullable World world, @Nullable EntityLivingBase entity) {
                if (entity == null || stack.isOnItemFrame()) {
                    return 0;
                } else {
                    if (world == null) {
                        world = entity.world;
                    }

                    Chunk chunk = world.getChunkFromBlockCoords(entity.getPosition());
                    if (world.provider.isSurfaceWorld() && chunk.getRandomWithSeed(987234911L).nextInt(10) == 0) {
                        if (world.getTotalWorldTime() != this.lastUpdateTick) {
                            this.lastUpdateTick = world.getTotalWorldTime();
                            tick++;
                            if (tick == 2) {
                                tick = 0;
                                size++;
                                if (size == 8) {
                                    size = 0;
                                    if (entity.getHeldItemMainhand().getItem() == GSItem.SLIME_CHUNK ||
                                            entity.getHeldItemOffhand().getItem() == GSItem.SLIME_CHUNK) {
                                        entity.playSound(SoundEvents.ENTITY_SLIME_JUMP, 0.2F, (entity.getRNG().nextFloat() - entity.getRNG().nextFloat()) * 0.2F + 1);
                                    }
                                }
                            }
                        }
                    } else {
                        tick = 0;
                        size = 0;
                    }

                    return size;
                }
            }
        };
    }
}
