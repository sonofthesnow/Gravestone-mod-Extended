package nightkosh.gravestone_extended.core;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
@SideOnly(Side.CLIENT)
public class ResourcesModels extends Resources {

    // MODEL RESOURCES
    public static final ModelResourceLocation chiselModel = new ModelResourceLocation(GSItem.CHISEL.getRegistryName(), "inventory");

    public static final ModelResourceLocation CORPSE = new ModelResourceLocation(MOD_NAME + ":GSCorpse", "inventory");

    public static final ModelResourceLocation spawnEggModel = new ModelResourceLocation(GSItem.SPAWN_EGG.getRegistryName(), "inventory");

    public static final ModelResourceLocation memorialModel = new ModelResourceLocation(MOD_NAME + ":GSMemorial", "inventory");
    public static final ModelResourceLocation executionModel = new ModelResourceLocation(MOD_NAME + ":GSExecution", "inventory");
    public static final ModelResourceLocation spawnerModel = new ModelResourceLocation(MOD_NAME + ":GSSpawner", "inventory");
    //trap
    public static final ModelResourceLocation nightStoneModel = new ModelResourceLocation(MOD_NAME + ":GSTrap_night_stone", "inventory");
    public static final ModelResourceLocation thunderStoneModel = new ModelResourceLocation(MOD_NAME + ":GSTrap_thunder_stone", "inventory");

    public static final ModelResourceLocation pileOfBonesModel = new ModelResourceLocation(MOD_NAME + ":GSPileOfBones", "inventory");
    //bone blocks
    public static final ModelResourceLocation boneBlockModel = new ModelResourceLocation(MOD_NAME + ":GSBoneBlock", "inventory");
    public static final ModelResourceLocation boneBlockWithSkullModel = new ModelResourceLocation(MOD_NAME + ":GSBoneBlock_with_skull", "inventory");

    public static final ModelResourceLocation boneSlabModel = new ModelResourceLocation(MOD_NAME + ":GSBoneSlab", "inventory");
    public static final ModelResourceLocation boneStairsModel = new ModelResourceLocation(MOD_NAME + ":GSBoneStairs", "inventory");

    public static final ModelResourceLocation hauntedChestModel = new ModelResourceLocation(MOD_NAME + ":GSHauntedChest", "inventory");
    public static final ModelResourceLocation candleModel = new ModelResourceLocation(MOD_NAME + ":GSCandle", "inventory");
    public static final ModelResourceLocation skullCandleModel = new ModelResourceLocation(MOD_NAME + ":GSSkullCandle", "inventory");
    public static final ModelResourceLocation altarModel = new ModelResourceLocation(MOD_NAME + ":GSAltar", "inventory");
}
