package nightkosh.gravestone_extended.core.event;

import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import nightkosh.gravestone_extended.core.TimeHelper;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class TickEventHandler {

    private static short ticCount = 0;

    private static short fogTicCount = 0;
    public static final short MAX_FOG_TICK_COUNT = 100;

    public static short getFogTicCount() {
        return fogTicCount;
    }

    @SubscribeEvent
    public void worldTick(TickEvent.WorldTickEvent event) {
        if (event.phase == TickEvent.Phase.END) {
            ticCount++;

            if (ticCount >= 500) {
                TimeHelper.updateIsGraveSpawnTime(event.world);
                ticCount = 0;
            }
        }
    }



    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public void playerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase == TickEvent.Phase.END) {
            if (event.player.equals(Minecraft.getMinecraft().player)) {
                fogTicCount++;
                if (fogTicCount > MAX_FOG_TICK_COUNT) {
                    fogTicCount = 0;
                    RenderEventHandler.resetAmountOfFogSources(event.player.getEntityWorld());
                }
            }
        }
    }
}
