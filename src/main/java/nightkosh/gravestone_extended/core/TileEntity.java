package nightkosh.gravestone_extended.core;

import nightkosh.gravestone.core.GSTileEntity;
import nightkosh.gravestone_extended.tileentity.*;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class TileEntity extends GSTileEntity{

    public static void registration() {
        GameRegistry.registerTileEntity(TileEntityGSMemorial.class, "Memorial");

        GameRegistry.registerTileEntity(TileEntityGSSpawner.class, "GS Spawner");

        GameRegistry.registerTileEntity(TileEntityGSHauntedChest.class, "GSHaunted Chest");

        GameRegistry.registerTileEntity(TileEntityGSCandle.class, "GSTECandle");

        GameRegistry.registerTileEntity(TileEntityGSSkullCandle.class, "GSSkull Candle");

        GameRegistry.registerTileEntity(TileEntityGSPileOfBones.class, "GSTEPileOfBones");

        GameRegistry.registerTileEntity(TileEntityGSAltar.class, "GSAltarTE");
    }
}
