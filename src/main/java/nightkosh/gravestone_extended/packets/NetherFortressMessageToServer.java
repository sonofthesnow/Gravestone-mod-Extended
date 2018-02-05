package nightkosh.gravestone_extended.packets;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import nightkosh.gravestone_extended.core.MessageHandler;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class NetherFortressMessageToServer implements IMessage, IMessageHandler<NetherFortressMessageToServer, IMessage> {

    private int playerID;
    private int dimensionID;

    public NetherFortressMessageToServer() {

    }

    public NetherFortressMessageToServer(EntityPlayer player) {
        this.playerID = player.getEntityId();
        this.dimensionID = player.getEntityWorld().provider.getDimension();
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        this.playerID = buf.readInt();
        this.dimensionID = buf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(this.playerID);
        buf.writeInt(this.dimensionID);
    }

    @Override
    public IMessage onMessage(NetherFortressMessageToServer message, MessageContext ctx) {
        if (ctx.side.isServer()) {
            WorldServer world = DimensionManager.getWorld(message.dimensionID);
            if (world == null || ((ctx.getServerHandler().player != null) && (ctx.getServerHandler().player.getEntityId() != message.playerID))) {
                return null;
            }
            EntityPlayerMP player = (EntityPlayerMP) world.getEntityByID(message.playerID);
            BlockPos pos = world.getChunkProvider().getNearestStructurePos(world, "Fortress", new BlockPos(player), false);
            if (pos != null) {
                MessageHandler.networkWrapper.sendTo(new NetherFortressMessageToClient(pos), player);
            }
        }
        return null;
    }
}