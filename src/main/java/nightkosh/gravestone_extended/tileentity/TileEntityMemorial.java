package nightkosh.gravestone_extended.tileentity;

import com.google.common.collect.Iterables;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.StringUtils;
import net.minecraft.world.World;
import nightkosh.gravestone.tileentity.GraveStoneDeathText;
import nightkosh.gravestone.tileentity.TileEntityGrave;
import nightkosh.gravestone_extended.block.enums.EnumHangedMobs;
import nightkosh.gravestone_extended.block.enums.EnumMemorials;

import java.util.Random;
import java.util.UUID;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class TileEntityMemorial extends TileEntityGrave {

    private GameProfile playerProfile = null;
    private EnumHangedMobs hangedMob = EnumHangedMobs.NONE;
    private int hangedVillagerProfession = 0;

    public TileEntityMemorial() {
        super();
    }

    public TileEntityMemorial(World world) {
        this();
        this.worldObj = world;
    }

    /**
     * Called when a client event is received with the event number and
     * argument, see World.sendClientEvent
     */
    @Override
    public boolean receiveClientEvent(int par1, int par2) {
        return true;
    }

    /**
     * Reads a tile entity from NBT.
     */
    @Override
    public void readFromNBT(NBTTagCompound nbtTag) {
        super.readFromNBT(nbtTag);
        // death text
        deathText.readText(nbtTag);

        hangedMob = EnumHangedMobs.getById(nbtTag.getByte("HangedMob"));
        hangedVillagerProfession = nbtTag.getInteger("HangedVillagerProfession");


        if (nbtTag.hasKey("Owner", 10)) {
            this.playerProfile = NBTUtil.readGameProfileFromNBT(nbtTag.getCompoundTag("Owner"));
        } else if (nbtTag.hasKey("ExtraType", 8)) {
            String s = nbtTag.getString("ExtraType");

            if (!StringUtils.isNullOrEmpty(s)) {
                this.playerProfile = new GameProfile((UUID) null, s);
                this.updatePlayerProfile();
            }
        }
    }

    /**
     * Writes a tile entity to NBT.
     */
    @Override
    public void writeToNBT(NBTTagCompound nbtTag) {
        super.writeToNBT(nbtTag);
        // death text
        deathText.saveText(nbtTag);

        nbtTag.setByte("HangedMob", (byte) hangedMob.ordinal());
        nbtTag.setInteger("HangedVillagerProfession", hangedVillagerProfession);

        if (this.playerProfile != null) {
            NBTTagCompound nbtTagCompound = new NBTTagCompound();
            NBTUtil.writeGameProfile(nbtTagCompound, this.playerProfile);
            nbtTag.setTag("Owner", nbtTagCompound);
        }
    }

    public void setMemorialContent(Random random) {
        //TODO ?????
//        deathText = DeathTextHelper.getRandomDeathTextAndNameForMemorial(random, EnumMemorials.getById(graveType));
    }

    public void setRandomMob(Random random) {
        hangedMob = EnumHangedMobs.values()[random.nextInt(EnumHangedMobs.values().length)];
    }

    @Override
    public GraveStoneDeathText getDeathTextComponent() {
        return deathText;
    }

    public EnumMemorials getMemorialType() {
        return EnumMemorials.getById(graveType);
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

    public GameProfile getPlayerProfile() {
        return this.playerProfile;
    }

    public void setPlayerProfile(GameProfile playerProfile) {
        this.playerProfile = playerProfile;
        this.updatePlayerProfile();
    }

    private void updatePlayerProfile() {
        this.playerProfile = updateGameprofile(this.playerProfile);
        this.markDirty();
    }

    public static GameProfile updateGameprofile(GameProfile input) {
        if (input != null && !StringUtils.isNullOrEmpty(input.getName())) {
            if (input.isComplete() && input.getProperties().containsKey("textures")) {
                return input;
            } else if (MinecraftServer.getServer() == null) {
                return input;
            } else {
                GameProfile gameprofile1 = MinecraftServer.getServer().getPlayerProfileCache().getGameProfileForUsername(input.getName());

                if (gameprofile1 == null) {
                    return input;
                } else {
                    Property property = (Property) Iterables.getFirst(gameprofile1.getProperties().get("textures"), (Object) null);

                    if (property == null) {
                        gameprofile1 = MinecraftServer.getServer().getMinecraftSessionService().fillProfileProperties(gameprofile1, true);
                    }

                    return gameprofile1;
                }
            }
        } else {
            return input;
        }
    }

    public static class Obelisk extends TileEntityMemorial {}
    public static class CelticCross extends TileEntityMemorial {}
    public static class SteveStatue extends TileEntityMemorial {}
    public static class VillagerStatue extends TileEntityMemorial {}
    public static class AngelStatue extends TileEntityMemorial {}
    public static class DogStatue extends TileEntityMemorial {}
    public static class CatStatue extends TileEntityMemorial {}
    public static class CreeperStatue extends TileEntityMemorial {}
}
