package nightkosh.gravestone_extended.tileentity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import nightkosh.gravestone.tileentity.TileEntityBase;
import nightkosh.gravestone_extended.block.enums.EnumSkullCandle;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class TileEntitySkullCandle extends TileEntityBase {

    private byte rotation;

    /**
     * Writes a tile entity to NBT.
     */
    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
        nbt = super.writeToNBT(nbt);
        nbt.setByte("Rotation", this.rotation);

        return nbt;
    }

    /**
     * Reads a tile entity from NBT.
     */
    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        this.rotation = nbt.getByte("Rotation");
    }

    @Override
    public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity packet) {
        readFromNBT(packet.getNbtCompound());
    }

    @Override
    public SPacketUpdateTileEntity getUpdatePacket() {
        return new SPacketUpdateTileEntity(this.pos, 4, this.getUpdateTag());
    }

    @Override
    public NBTTagCompound getUpdateTag() {
        return this.writeToNBT(new NBTTagCompound());
    }

    /**
     * Get the entity type for the skull
     */
    public byte getRotation() {
        return this.rotation;
    }


    public void setRotation(byte rotation) {
        this.rotation = rotation;
    }

    public static class Zombie extends TileEntitySkullCandle {
        @Override
        public int getBlockMetadata() {
            return EnumSkullCandle.ZOMBIE_SKULL.ordinal();
        }
    }
    public static class Wither extends TileEntitySkullCandle {
        @Override
        public int getBlockMetadata() {
            return EnumSkullCandle.WITHER_SKULL.ordinal();
        }
    }
}
