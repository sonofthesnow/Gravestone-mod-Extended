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

    public static final String MOD_NAME = ModInfo.ID.toLowerCase();

    // MODEL RESOURCES
    public static final ModelResourceLocation chiselModel = new ModelResourceLocation(ModInfo.ID + ":tools/" + GSItem.CHISEL.getRegistryName().getResourcePath(), "inventory");

    public static final ModelResourceLocation BONE_SWORD = new ModelResourceLocation(ModInfo.ID + ":weapon/" + GSItem.BONE_SWORD.getRegistryName().getResourcePath(), "inventory");
    public static final ModelResourceLocation BONE_SWORD_IRON = new ModelResourceLocation(ModInfo.ID + ":weapon/" + GSItem.BONE_SWORD_IRON.getRegistryName().getResourcePath(), "inventory");
    public static final ModelResourceLocation BONE_SWORD_GOLDEN = new ModelResourceLocation(ModInfo.ID + ":weapon/" + GSItem.BONE_SWORD_GOLDEN.getRegistryName().getResourcePath(), "inventory");
    public static final ModelResourceLocation BONE_SWORD_DIAMOND = new ModelResourceLocation(ModInfo.ID + ":weapon/" + GSItem.BONE_SWORD_DIAMOND.getRegistryName().getResourcePath(), "inventory");
    public static final ModelResourceLocation BONE_SHIELD = new ModelResourceLocation(ModInfo.ID + ":weapon/" + GSItem.BONE_SHIELD.getRegistryName().getResourcePath(), "inventory");

    public static final ModelResourceLocation BONE_HOE = new ModelResourceLocation(ModInfo.ID + ":tools/" + GSItem.BONE_HOE.getRegistryName().getResourcePath(), "inventory");
    public static final ModelResourceLocation BONE_HOE_IRON = new ModelResourceLocation(ModInfo.ID + ":tools/" + GSItem.BONE_HOE_IRON.getRegistryName().getResourcePath(), "inventory");
    public static final ModelResourceLocation BONE_HOE_GOLDEN = new ModelResourceLocation(ModInfo.ID + ":tools/" + GSItem.BONE_HOE_GOLDEN.getRegistryName().getResourcePath(), "inventory");
    public static final ModelResourceLocation BONE_HOE_DIAMOND = new ModelResourceLocation(ModInfo.ID + ":tools/" + GSItem.BONE_HOE_DIAMOND.getRegistryName().getResourcePath(), "inventory");

    public static final ModelResourceLocation CORPSE = new ModelResourceLocation(MOD_NAME + ":gscorpse", "inventory");

    public static final ModelResourceLocation spawnEggModel = new ModelResourceLocation(GSItem.SPAWN_EGG.getRegistryName(), "inventory");

    public static final ModelResourceLocation TOXIC_SLIME = new ModelResourceLocation(GSItem.TOXIC_SLIME.getRegistryName(), "inventory");

    public static final ModelResourceLocation memorialModel = new ModelResourceLocation(MOD_NAME + ":gsmemorial", "inventory");
    public static final ModelResourceLocation executionModel = new ModelResourceLocation(MOD_NAME + ":gsexecution", "inventory");
    public static final ModelResourceLocation spawnerModel = new ModelResourceLocation(MOD_NAME + ":gsspawner", "inventory");
    //trap
    public static final ModelResourceLocation nightStoneModel = new ModelResourceLocation(MOD_NAME + ":gstrap_night_stone", "inventory");
    public static final ModelResourceLocation thunderStoneModel = new ModelResourceLocation(MOD_NAME + ":gstrap_thunder_stone", "inventory");

    public static final ModelResourceLocation pileOfBonesModel = new ModelResourceLocation(MOD_NAME + ":gspileofbones", "inventory");
    //bone blocks
    public static final ModelResourceLocation boneBlockModel = new ModelResourceLocation(MOD_NAME + ":gsboneblock", "inventory");
    public static final ModelResourceLocation boneBlockWithSkullModel = new ModelResourceLocation(MOD_NAME + ":gsboneblock_with_skull", "inventory");

    public static final ModelResourceLocation boneSlabModel = new ModelResourceLocation(MOD_NAME + ":gsboneslab", "inventory");
    public static final ModelResourceLocation boneStairsModel = new ModelResourceLocation(MOD_NAME + ":gsbonestairs", "inventory");

    public static final ModelResourceLocation hauntedChestModel = new ModelResourceLocation(MOD_NAME + ":gshauntedchest", "inventory");
    public static final ModelResourceLocation candleModel = new ModelResourceLocation(MOD_NAME + ":gscandle", "inventory");
    public static final ModelResourceLocation skullCandleModel = new ModelResourceLocation(MOD_NAME + ":gsskullcandle", "inventory");
    public static final ModelResourceLocation altarModel = new ModelResourceLocation(MOD_NAME + ":gsaltar", "inventory");
}
