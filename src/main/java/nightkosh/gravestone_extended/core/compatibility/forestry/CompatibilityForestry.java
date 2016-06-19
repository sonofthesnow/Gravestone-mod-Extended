package nightkosh.gravestone_extended.core.compatibility.forestry;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class CompatibilityForestry {

    public static boolean isInstalled = false;

    public static net.minecraft.item.Item backpackItemT1;
    public static net.minecraft.item.Item backpackItemT2;

    public static final int DEFAULT_BEEKEEPER_ID = 80;
    public static final int DEFAULT_LUMBERJACK_ID = 81;

    private CompatibilityForestry() {

    }

//    public static int getApicultureVillagerID() {
//        if (isInstalled && ForestryAPI.forestryConstants != null) {
//            return ForestryAPI.forestryConstants.getApicultureVillagerID();
//        } else {
//            return DEFAULT_BEEKEEPER_ID;
//        }
//    }
//
//    public static int getArboricultureVillagerID() {
//        if (isInstalled && ForestryAPI.forestryConstants != null) {
//            return ForestryAPI.forestryConstants.getArboricultureVillagerID();
//        } else {
//            return DEFAULT_LUMBERJACK_ID;
//        }
//    }
//
//    public static void addBackpack() {
//        if (isInstalled && BackpackManager.backpackInterface != null) {
//            if (ExtendedConfig.enableForestryBackpacks) {
//                String backpackT1Name = "backpack.undertaker.t1";
//                backpackItemT1 = BackpackManager.backpackInterface.addBackpack(UndertakerBackpack.getInstance(), EnumBackpackType.T1);
//                backpackItemT1.setCreativeTab(Tabs.otherItemsTab);
//                backpackItemT1.setUnlocalizedName(backpackT1Name);
//                ExtendedItem.registryExternalItems(backpackItemT1, "GSUndertakerBackpackT1");
//
//                ItemStack backpackStackT1 = new ItemStack(backpackItemT1);
//                Recipes.addForestryBackpack(backpackStackT1, ExtendedItem.chisel);
//
//
//                String backpackT2Name = "backpack.undertaker.t2";
//                backpackItemT2 = BackpackManager.backpackInterface.addBackpack(UndertakerBackpack.getInstance(), EnumBackpackType.T2);
//                backpackItemT2.setCreativeTab(Tabs.otherItemsTab);
//                backpackItemT2.setUnlocalizedName(backpackT2Name);
//                ExtendedItem.registryExternalItems(backpackItemT2, "GSUndertakerBackpackT2");
//
//                net.minecraft.item.Item itemSilk = GameRegistry.findItem("Forestry", "craftingMaterial");
//                if (itemSilk != null) {
//                    ItemStack wovenSilk = new ItemStack(itemSilk, 1, 3);
//
//                    ItemStack backpackStackT2 = new ItemStack(backpackItemT2);
//                    RecipeManagers.carpenterManager.addRecipe(200, FluidRegistry.getFluidStack("water", 1000), null, backpackStackT2,
//                            new Object[]{
//                                    "sds", "sbs", "sss",
//                                    'd', Items.diamond, 'b', backpackItemT1, 's', wovenSilk
//                            }
//                    );
//                }
//            }
//        }
//    }
}