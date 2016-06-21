package nightkosh.gravestone_extended.tileentity;

import net.minecraft.util.ITickable;
import nightkosh.gravestone.tileentity.ISpawnerEntity;
import nightkosh.gravestone.tileentity.TileEntityBase;
import nightkosh.gravestone_extended.block.enums.EnumSpawner;
import nightkosh.gravestone_extended.entity.helper.EntityGroupOfGravesMobSpawnerHelper;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class TileEntitySpawner extends TileEntityBase implements ITickable, ISpawnerEntity {

    protected GSMobSpawner spawner;

    public TileEntitySpawner() {
        spawner = new GSMobSpawner(this);
    }

    /**
     * Allows the entity to update its state. Overridden in most subclasses,
     * e.g. the mob spawner uses this to count ticks and creates a new spawn
     * inside its implementation.
     */
    @Override
    public void update() {
        spawner.update();
    }

    @Override
    public boolean haveSpawnerHelper() {
        return false;
    }

    @Override
    public EntityGroupOfGravesMobSpawnerHelper getSpawnerHelper() {
        return null;
    }

    public static class Skeleton extends TileEntitySpawner {
        @Override
        public int getBlockMetadata() {
            return EnumSpawner.SKELETON_SPAWNER.ordinal();
        }
    }

    public static class Zombie extends TileEntitySpawner {
        @Override
        public int getBlockMetadata() {
            return EnumSpawner.ZOMBIE_SPAWNER.ordinal();
        }
    }
}
