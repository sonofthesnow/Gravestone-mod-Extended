package nightkosh.gravestone_extended.tileentity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.IChatComponent;
import nightkosh.gravestone.tileentity.TileEntityBase;
import nightkosh.gravestone_extended.block.enums.EnumCorpse;
import nightkosh.gravestone_extended.block.enums.EnumExecution;
import nightkosh.gravestone_extended.item.corpse.CorpseHelper;
import nightkosh.gravestone_extended.item.corpse.VillagerCorpseHelper;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class TileEntityExecution extends TileEntityBase implements IInventory {

    private byte direction = 0;
    private ItemStack corpse;
    private EnumCorpse corpseType = null;
    private int hangedVillagerProfession = 0;

    @Override
    public void readFromNBT(NBTTagCompound nbtTag) {
        super.readFromNBT(nbtTag);

        direction = nbtTag.getByte("Direction");

        if (nbtTag.hasKey("Corpse")) {
            corpse = ItemStack.loadItemStackFromNBT(nbtTag.getCompoundTag("Corpse"));

            updateCorpseInfo();
        }
    }

    @Override
    public void writeToNBT(NBTTagCompound nbtTag) {
        super.writeToNBT(nbtTag);

        nbtTag.setByte("Direction", direction);

        if (corpse != null) {
            nbtTag.setTag("Corpse", corpse.writeToNBT(new NBTTagCompound()));
        }

    }

//    public void setRandomMob(Random random) {
////        hangedMob = EnumHangedMobs.values()[random.nextInt(EnumHangedMobs.values().length)];
//    }

    public EnumCorpse getCorpseType() {
        return corpseType;
    }

    public int getHangedVillagerProfession() {
        return hangedVillagerProfession;
    }

    public byte getDirection() {
        return direction;
    }

    public void setDirection(byte direction) {
        this.direction = direction;
    }

    public ItemStack getCorpse() {
        return corpse;
    }

    public void setCorpse(ItemStack corpse) {
        this.corpse = corpse;
        this.updateCorpseInfo();
    }

    private void updateCorpseInfo() {
        if (corpse == null) {
            this.corpseType = null;
            this.hangedVillagerProfession = 0;
        } else {
            this.corpseType = CorpseHelper.getTypeByCorpse(corpse);
            this.hangedVillagerProfession = VillagerCorpseHelper.getVillagerType(corpse.getTagCompound());
        }
    }

    @Override
    public boolean receiveClientEvent(int par1, int par2) {
        return true;
    }

    @Override
    public Packet getDescriptionPacket() {
        NBTTagCompound nbt = new NBTTagCompound();
        this.writeToNBT(nbt);
        return new S35PacketUpdateTileEntity(this.pos, 1, nbt);
    }

    @Override
    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity packet) {
        readFromNBT(packet.getNbtCompound());
    }

    @Override
    public int getSizeInventory() {
        return 1;
    }

    @Override
    public ItemStack getStackInSlot(int index) {
        return corpse;
    }

    @Override
    public ItemStack decrStackSize(int index, int count) {
        ItemStack stack = getStackInSlot(index);
        if (stack != null) {
            if (stack.stackSize <= count) {
                setInventorySlotContents(index, null);
            } else {
                stack = stack.splitStack(count);
                if (stack.stackSize == 0) {
                    setInventorySlotContents(index, null);
                }
            }
        }
        return stack;
    }

    @Override
    public ItemStack removeStackFromSlot(int index) {
        ItemStack stack = getStackInSlot(index);
        if (stack != null) {
            setInventorySlotContents(index, null);
        }
        return stack;
    }

    @Override
    public void setInventorySlotContents(int index, ItemStack stack) {
        corpse = stack;
        this.updateCorpseInfo();
    }

    @Override
    public int getInventoryStackLimit() {
        return 1;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer player) {
        return worldObj.getTileEntity(this.pos) == this &&
                player.getDistanceSq(new BlockPos(this.pos.getX() + 0.5, this.pos.getY() + 0.5, this.pos.getZ() + 0.5)) < 64;
    }

    @Override
    public void openInventory(EntityPlayer player) {

    }

    @Override
    public void closeInventory(EntityPlayer player) {

    }

    @Override
    public boolean isItemValidForSlot(int index, ItemStack stack) {
        return false;
    }

    @Override
    public int getField(int id) {
        return 0;
    }

    @Override
    public void setField(int id, int value) {

    }

    @Override
    public int getFieldCount() {
        return 0;
    }

    @Override
    public void clear() {

    }

    @Override
    public String getName() {
        return "";
    }

    @Override
    public boolean hasCustomName() {
        return false;
    }

    @Override
    public IChatComponent getDisplayName() {
        return null;
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
