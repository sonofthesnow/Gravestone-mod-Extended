package nightkosh.gravestone_extended.tileentity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import nightkosh.gravestone.tileentity.TileEntityBase;
import nightkosh.gravestone_extended.block.enums.EnumExecution;
import nightkosh.gravestone_extended.block.enums.EnumHangedMobs;

import java.util.Random;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class TileEntityExecution extends TileEntityBase {

    private byte direction = 0;
    private EnumHangedMobs hangedMob = EnumHangedMobs.NONE;
    private int hangedVillagerProfession = 0;

    @Override
    public void readFromNBT(NBTTagCompound nbtTag) {
        super.readFromNBT(nbtTag);

        direction = nbtTag.getByte("Direction");

        hangedMob = EnumHangedMobs.getById(nbtTag.getByte("HangedMob"));
        hangedVillagerProfession = nbtTag.getInteger("HangedVillagerProfession");
    }

    @Override
    public void writeToNBT(NBTTagCompound nbtTag) {
        super.writeToNBT(nbtTag);

        nbtTag.setByte("Direction", direction);

        nbtTag.setByte("HangedMob", (byte) hangedMob.ordinal());
        nbtTag.setInteger("HangedVillagerProfession", hangedVillagerProfession);
    }

    public void setRandomMob(Random random) {
        hangedMob = EnumHangedMobs.values()[random.nextInt(EnumHangedMobs.values().length)];
    }

    public int getHangedVillagerProfession() {
        return hangedVillagerProfession;
    }

    public void setHangedVillagerProfession(int hangedVillagerProfession) {
        this.hangedVillagerProfession = hangedVillagerProfession;
    }

    public EnumHangedMobs getHangedMob() {
        return hangedMob;
    }

    public void setHangedMob(EnumHangedMobs hangedMob) {
        this.hangedMob = hangedMob;
    }

    public byte getDirection() {
        return direction;
    }

    public void setDirection(byte direction) {
        this.direction = direction;
    }

    @Override
    public Packet getDescriptionPacket() {
        NBTTagCompound nbt = new NBTTagCompound();
        this.writeToNBT(nbt);
        return new S35PacketUpdateTileEntity(this.pos, 4, nbt);
    }

    @Override
    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity packet) {
        readFromNBT(packet.getNbtCompound());
    }

    public static class Gibbet extends TileEntityExecution {
        @Override
        public int getBlockMetadata() {
            return EnumExecution.GIBBET.ordinal();
        }
    }

    public static class Stocks extends TileEntityExecution {
        @Override
        public int getBlockMetadata() {
            return EnumExecution.STOCKS.ordinal();
        }
    }

    public static class BurningStake extends TileEntityExecution {
        @Override
        public int getBlockMetadata() {
            return EnumExecution.BURNING_STAKE.ordinal();
        }
    }
}
