package nightkosh.gravestone_extended.core.event;

import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.util.BlockPos;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import nightkosh.gravestone_extended.config.ExtendedConfig;
import nightkosh.gravestone_extended.core.Block;
import nightkosh.gravestone_extended.core.MobSpawn;
import nightkosh.gravestone_extended.entity.monster.EntitySkullCrawler;
import nightkosh.gravestone_extended.entity.monster.EntityWitherSkullCrawler;
import nightkosh.gravestone_extended.entity.monster.EntityZombieSkullCrawler;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class GSEventsHandler {

    // Hopefully ensure we capture items before other things do (set to high so other mods can run before if they have more specialness
    @SubscribeEvent(priority = EventPriority.HIGH)
    public void onEntityLivingDeath(LivingDeathEvent event) {
        if (FMLCommonHandler.instance().getEffectiveSide().isServer()) {
            if (ExtendedConfig.spawnSkullCrawlersAtMobsDeath) {
                if (event.entity instanceof EntitySkeleton) {
                    EntitySkullCrawler crawler;
                    if (MobSpawn.isWitherSkeleton((EntitySkeleton) event.entity)) {
                        crawler = new EntityWitherSkullCrawler(event.entity.worldObj);
                    } else {
                        crawler = new EntitySkullCrawler(event.entity.worldObj);
                    }
                    MobSpawn.spawnCrawler(event.entity, crawler);
                } else if (event.entity instanceof EntityZombie) {
                    MobSpawn.spawnCrawler(event.entity, new EntityZombieSkullCrawler(event.entity.worldObj));
                }
            }
            if (event.entity instanceof EntityCreeper && ((EntityCreeper) event.entity).getPowered()) {
                // drop creeper statue if entity is a charged creeper
                Block.memorial.dropCreeperMemorial(event.entity.worldObj, new BlockPos(event.entity));
            }
        }
    }
}
