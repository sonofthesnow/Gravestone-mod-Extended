package nightkosh.gravestone_extended.tileentity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import nightkosh.gravestone_extended.block.enums.EnumExecution;
import nightkosh.gravestone_extended.block.enums.EnumHangedMobs;

import java.util.Random;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class TileEntityExecution extends TileEntity {

    protected EnumExecution executionType = EnumExecution.GALLOWS;
    private EnumHangedMobs hangedMob = EnumHangedMobs.NONE;
    private int hangedVillagerProfession = 0;

    @Override
    public void readFromNBT(NBTTagCompound nbtTag) {
        super.readFromNBT(nbtTag);

        executionType = EnumExecution.getById((int) nbtTag.getByte("Type"));

        hangedMob = EnumHangedMobs.getById(nbtTag.getByte("HangedMob"));
        hangedVillagerProfession = nbtTag.getInteger("HangedVillagerProfession");
    }

    @Override
    public void writeToNBT(NBTTagCompound nbtTag) {
        super.writeToNBT(nbtTag);

        nbtTag.setByte("Type", (byte) executionType.ordinal());

        nbtTag.setByte("HangedMob", (byte) hangedMob.ordinal());
        nbtTag.setInteger("HangedVillagerProfession", hangedVillagerProfession);
    }

    public EnumExecution getExecutionType() {
        return executionType;
    }

    public int getExecutionTypeNum() {
        return executionType.ordinal();
    }

    public void setExecutionType(EnumExecution executionType) {
        this.executionType = executionType;
    }

    public void setExecutionType(int executionType) {
        this.executionType = EnumExecution.getById(executionType);
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

    public static class Gibbet extends TileEntityExecution {}
    public static class Stocks extends TileEntityExecution {}
    public static class BurningStake extends TileEntityExecution {}
}
