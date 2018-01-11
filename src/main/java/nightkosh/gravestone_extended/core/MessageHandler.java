package nightkosh.gravestone_extended.core;

import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;
import nightkosh.gravestone_extended.packets.*;

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
        networkWrapper.registerMessage(ChokeMessageToClient.class, ChokeMessageToClient.class, 4, Side.CLIENT);
    }
}
