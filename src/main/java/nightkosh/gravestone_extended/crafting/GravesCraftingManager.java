package nightkosh.gravestone_extended.crafting;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import nightkosh.gravestone.api.grave.EnumGraveMaterial;
import nightkosh.gravestone.api.grave.EnumGraveType;
import nightkosh.gravestone.block.enums.EnumGraves;
import nightkosh.gravestone_extended.block.enums.EnumMemorials;
import nightkosh.gravestone_extended.config.ExtendedConfig;
import nightkosh.gravestone_extended.core.Block;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class GravesCraftingManager {


    private static final GravesCraftingManager instance = new GravesCraftingManager();
    private static final List<GravestoneRecipe> recipes = new ArrayList<>();//TODO set recipes count

    private GravesCraftingManager() {
        addGravesRecipesForAllMaterials(EnumGraveType.VERTICAL_PLATE);
        addGravesRecipesForAllMaterials(EnumGraveType.CROSS);
        addGravesRecipesForAllMaterials(EnumGraveType.OBELISK);
        addGravesRecipesForAllMaterials(EnumGraveType.HORIZONTAL_PLATE);
        addGravesRecipesForAllMaterials(EnumGraveType.DOG_STATUE);
        addGravesRecipesForAllMaterials(EnumGraveType.CAT_STATUE);
        addGravesRecipesForAllMaterials(EnumGraveType.HORSE_STATUE);
        //TODO EnumGraves.EnumGraveType.SWORD

        // Memorials
        addMemorialsRecipesForAllMaterials(EnumMemorials.EnumMemorialType.CROSS, 6);
        addMemorialsRecipesForAllMaterials(EnumMemorials.EnumMemorialType.OBELISK, 6);
        addMemorialsRecipesForAllMaterials(EnumMemorials.EnumMemorialType.STEVE_STATUE, 3);
        addMemorialsRecipesForAllMaterials(EnumMemorials.EnumMemorialType.VILLAGER_STATUE, 3);
        addMemorialsRecipesForAllMaterials(EnumMemorials.EnumMemorialType.ANGEL_STATUE, 3);
        addMemorialsRecipesForAllMaterials(EnumMemorials.EnumMemorialType.DOG_STATUE, 2);
        addMemorialsRecipesForAllMaterials(EnumMemorials.EnumMemorialType.CAT_STATUE, 2);
        // CREEPER_STATUE
        if (ExtendedConfig.craftableCreeperStatues) {
            addMemorialsRecipesForAllMaterials(EnumMemorials.EnumMemorialType.CREEPER_STATUE, 3);
        }
    }

    private static void addGravesRecipesForAllMaterials(EnumGraveType graveType) {
        for (int i = 0; i <= EnumGraveMaterial.ICE.ordinal(); i++) {
            recipes.add(new GravestoneRecipe(true, graveType, EnumGraveMaterial.values()[i],
                    new ArrayList<ItemStack>(Arrays.asList(new ItemStack(EnumGraveMaterial.values()[i].getBlock(), 1))),
                    getStackWithNTB(Block.graveStone, EnumGraves.getByTypeAndMaterial(graveType, EnumGraveMaterial.values()[i]).ordinal())));
        }
    }

    private static void addMemorialsRecipesForAllMaterials(EnumMemorials.EnumMemorialType memorialType, int amountOfBlocks) {
        for (int i = 0; i <= EnumGraveMaterial.ICE.ordinal(); i++) {
            recipes.add(new GravestoneRecipe(true, memorialType, EnumGraveMaterial.values()[i],
                    new ArrayList<ItemStack>(Arrays.asList(new ItemStack(EnumGraveMaterial.values()[i].getBlock(), amountOfBlocks))),
                    getStackWithNTB(Block.memorial, EnumMemorials.getByTypeAndMaterial(memorialType, EnumGraveMaterial.values()[i]).ordinal())));
        }
    }

    public List<ItemStack> findMatchingRecipe(boolean isGravestone, EnumGraveType graveType, EnumGraveMaterial material, boolean isEnchanted, boolean isMossy) {
        for (GravestoneRecipe recipe : recipes) {
            if (recipe.match(isGravestone, graveType, material, isEnchanted, isMossy)) {
                return recipe.getRequiredItems(isEnchanted, isMossy);
            }
        }
        return null;
    }

    public ItemStack findMatchingRecipe(List<ItemStack> requiredItems, boolean isGravestone, EnumGraveType graveType, EnumGraveMaterial material, boolean isEnchanted, boolean isMossy) {
        for (GravestoneRecipe recipe : recipes) {
            if (recipe.match(isGravestone, graveType, material, isEnchanted, isMossy, requiredItems)) {
                return recipe.getResultItem(requiredItems);
            }
        }
        return null;
    }

    public List<GravestoneRecipe> getRecipes() {
        return recipes;
    }

    private static ItemStack getStackWithNTB(net.minecraft.block.Block block, int graveType) {
        ItemStack stack = new ItemStack(block, 1, 0);
        NBTTagCompound nbt = new NBTTagCompound();
        nbt.setInteger("Type", graveType);
        stack.setTagCompound(nbt);
        return stack;
    }

    public static GravesCraftingManager getInstance() {
        return instance;
    }
}
