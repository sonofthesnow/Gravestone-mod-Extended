package nightkosh.gravestone_extended.tileentity;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;

import java.util.Random;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class TileEntityAltar extends TileEntity implements IInventory {
    private ItemStack corpse = null;

    public boolean hasCorpse() {
        return corpse != null;
    }

    public ItemStack getCorpse() {
        return this.corpse;
    }

    public void setCorpse(ItemStack corpse) {
        this.corpse = corpse;
    }

    public void dropCorpse() {
        if (corpse != null) {
            Random random = new Random();
            float x = random.nextFloat() * 0.8F + 0.1F;
            float y = random.nextFloat() * 0.8F + 1.1F;
            EntityItem entityItem;

            for (float z = random.nextFloat() * 0.8F + 0.1F; corpse.getCount() > 0; this.getWorld().spawnEntity(entityItem)) {
                int stackSize = random.nextInt(21) + 10;

                if (stackSize > corpse.getCount()) {
                    stackSize = corpse.getCount();
                }

                corpse.setCount(corpse.getCount() - stackSize);
                entityItem = new EntityItem(this.getWorld(), this.pos.getX() + x, this.pos.getY() + y, this.pos.getZ() + z,
                        new ItemStack(corpse.getItem(), stackSize, corpse.getItemDamage()));
                entityItem.motionX = random.nextGaussian() * 0.05;
                entityItem.motionY = random.nextGaussian() * 0.15;
                entityItem.motionZ = random.nextGaussian() * 0.05;

                if (corpse.hasTagCompound()) {
                    entityItem.getEntityItem().setTagCompound(corpse.getTagCompound().copy());
                }
            }
            corpse = null;
        }
    }

    @Override
    public void readFromNBT(NBTTagCompound nbtTag) {
        super.readFromNBT(nbtTag);

        if (nbtTag.hasKey("Corpse")) {
            corpse = new ItemStack(nbtTag.getCompoundTag("Corpse"));
        }
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound nbtTag) {
        nbtTag = super.writeToNBT(nbtTag);

        if (corpse != null) {
            nbtTag.setTag("Corpse", corpse.writeToNBT(new NBTTagCompound()));
        }

        return nbtTag;
    }

    /**
     * Called when you receive a TileEntityData packet for the location this
     * TileEntity is currently in. On the client, the NetworkManager will always
     * be the remote server. On the server, it will be whomever is responsible for
     * sending the packet.
     *
     * @param net    The NetworkManager the packet originated from
     * @param packet The data packet
     */
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

    @Override
    public void openInventory(EntityPlayer player) {

    }

    @Override
    public void closeInventory(EntityPlayer player) {

    }

    @Override
    public boolean isItemValidForSlot(int p_94041_1_, ItemStack p_94041_2_) {
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
    public ITextComponent getDisplayName() {
        return null;
    }

    @Override
    public int getSizeInventory() {
        return 1;
    }

    @Override
    public boolean isEmpty() {
        return corpse == null || corpse == ItemStack.EMPTY;
    }

    @Override
    public ItemStack getStackInSlot(int slot) {
        return corpse;
    }

    @Override
    public void setInventorySlotContents(int slot, ItemStack stack) {
        corpse = stack;
    }

    @Override
    public ItemStack decrStackSize(int slot, int amt) {
        ItemStack stack = getStackInSlot(slot);
        if (stack != null) {
            if (stack.getCount() <= amt) {
                setInventorySlotContents(slot, null);
            } else {
                stack = stack.splitStack(amt);
                if (stack.isEmpty()) {
                    setInventorySlotContents(slot, null);
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
    public int getInventoryStackLimit() {
        return 1;
    }

    @Override
    public boolean isUsableByPlayer(EntityPlayer player) {
        return this.getWorld().getTileEntity(this.pos) == this &&
                player.getDistanceSq(new BlockPos(this.pos.getX() + 0.5, this.pos.getY() + 0.5, this.pos.getZ() + 0.5)) < 64;
    }
}
