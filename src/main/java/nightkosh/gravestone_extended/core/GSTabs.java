package nightkosh.gravestone_extended.core;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import nightkosh.gravestone_extended.block.enums.EnumMemorials;
import nightkosh.gravestone_extended.item.corpse.ZombieCorpseHelper;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class GSTabs extends nightkosh.gravestone.core.Tabs {

    public static CreativeTabs memorialsTab;
    public static CreativeTabs corpseTab;
    public static CreativeTabs otherItemsTab;

    public static void registration() {
        memorialsTab = new CreativeTabs("tabGSMemorials") {
            @Override
            public ItemStack getIconItemStack() {
                return new ItemStack(GSBlock.MEMORIAL, 1, EnumMemorials.STONE_CREEPER_STATUE.ordinal());
            }

            @Override
            @SideOnly(Side.CLIENT)
            public ItemStack getTabIconItem() {
                return new ItemStack(GSBlock.MEMORIAL, 1, EnumMemorials.STONE_CREEPER_STATUE.ordinal());
            }
        };

        corpseTab = new CreativeTabs("tabGSCorpse") {
            @Override
            public ItemStack getIconItemStack() {
                return ZombieCorpseHelper.getDefaultCorpse();
            }

            @Override
            @SideOnly(Side.CLIENT)
            public ItemStack getTabIconItem() {
                return ZombieCorpseHelper.getDefaultCorpse();
            }
        };

        otherItemsTab = new CreativeTabs("tabGSOther") {
            @Override
            public ItemStack getIconItemStack() {
                return new ItemStack(GSBlock.SKULL_CANDLE, 1, 1);
            }

            @Override
            @SideOnly(Side.CLIENT)
            public ItemStack getTabIconItem() {
                return new ItemStack(GSBlock.SKULL_CANDLE, 1, 1);
            }
        };
    }
}
