package nightkosh.gravestone_extended.core;

import nightkosh.gravestone_extended.packets.AltarMessageToClient;
import nightkosh.gravestone_extended.packets.AltarMessageToServer;
import nightkosh.gravestone_extended.packets.ChiselMessageToServer;
import nightkosh.gravestone_extended.packets.GraveDeathMessageToServer;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class MessageHandler {
    public static final SimpleNetworkWrapper networkWrapper = NetworkRegistry.INSTANCE.newSimpleChannel(ModInfo.ID.toLowerCase());

    public static void init() {
        networkWrapper.registerMessage(GraveDeathMessageToServer.class, GraveDeathMessageToServer.class, 0, Side.SERVER);
        networkWrapper.registerMessage(AltarMessageToServer.class, AltarMessageToServer.class, 1, Side.SERVER);
        networkWrapper.registerMessage(AltarMessageToClient.class, AltarMessageToClient.class, 2, Side.CLIENT);
        networkWrapper.registerMessage(ChiselMessageToServer.class, ChiselMessageToServer.class, 3, Side.SERVER);
    }
}
