package nightkosh.gravestone_extended.tileentity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import nightkosh.gravestone.tileentity.TileEntityBase;
import nightkosh.gravestone_extended.block.enums.EnumPileOfBones;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class TileEntityPileOfBones extends TileEntityBase {

    private byte direction = 0;

    public byte getDirection() {
        return direction;
    }

    public void setDirection(byte direction) {
        this.direction = direction;
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);

        direction = nbt.getByte("Direction");
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
        nbt = super.writeToNBT(nbt);

        nbt.setByte("Direction", direction);

        return nbt;
    }

    @Override
    public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity packet) {
        readFromNBT(packet.getNbtCompound());
    }

    @Override
    public SPacketUpdateTileEntity getUpdatePacket() {
        return new SPacketUpdateTileEntity(this.pos, 1, this.getUpdateTag());
    }

    @Override
    public NBTTagCompound getUpdateTag() {
        return this.writeToNBT(new NBTTagCompound());
    }

    public static class Skull extends TileEntityPileOfBones {
        @Override
        public int getBlockMetadata() {
            return EnumPileOfBones.PILE_OF_BONES_WITH_SKULL.ordinal();
        }
    }
    public static class Crawler extends TileEntityPileOfBones {
        @Override
        public int getBlockMetadata() {
            return EnumPileOfBones.PILE_OF_BONES_WITH_SKULL_CRAWLER.ordinal();
        }
    }
}
