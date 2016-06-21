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
        GameRegistry.registerTileEntity(TileEntityMemorial.class, "Memorial");

        GameRegistry.registerTileEntity(TileEntitySpawner.class, "GS Spawner");

        GameRegistry.registerTileEntity(TileEntityHauntedChest.class, "GSHaunted Chest");

        GameRegistry.registerTileEntity(TileEntityCandle.class, "GSTECandle");

        GameRegistry.registerTileEntity(TileEntitySkullCandle.class, "GSSkull Candle");

        GameRegistry.registerTileEntity(TileEntityPileOfBones.class, "GSTEPileOfBones");

        GameRegistry.registerTileEntity(TileEntityAltar.class, "GSAltarTE");
    }
}
