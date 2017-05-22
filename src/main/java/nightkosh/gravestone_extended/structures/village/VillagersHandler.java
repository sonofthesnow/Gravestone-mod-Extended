package nightkosh.gravestone_extended.structures.village;

import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.VillagerRegistry;
import nightkosh.gravestone_extended.core.Resources;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class VillagersHandler {

    public static final int UNDERTAKER_ID = 385; //TODO doesn't used !
    public static final String UNDERTAKER_NAME = "Undertaker";

    public static final VillagerRegistry.VillagerProfession UNDERTAKER_PROFESSION = new VillagerRegistry.VillagerProfession(UNDERTAKER_NAME, Resources.UNDERTAKER);
    public static final VillagerRegistry.VillagerCareer UNDERTAKER_CAREER = new VillagerRegistry.VillagerCareer(UNDERTAKER_PROFESSION, UNDERTAKER_NAME);

    public static void registerVillagers() {
        UNDERTAKER_CAREER.addTrade(1, new EntityVillager.ListItemForEmeralds(new ItemStack(Items.SKULL, 1), new EntityVillager.PriceInfo(1, 15)));// skeleton
        UNDERTAKER_CAREER.addTrade(1, new EntityVillager.ListItemForEmeralds(new ItemStack(Items.SKULL, 1, 2), new EntityVillager.PriceInfo(1, 15)));// zombie
        UNDERTAKER_CAREER.addTrade(2, new EntityVillager.ListItemForEmeralds(new ItemStack(Items.SKULL, 1, 3), new EntityVillager.PriceInfo(1, 20)));// steve
        UNDERTAKER_CAREER.addTrade(2, new EntityVillager.ListItemForEmeralds(new ItemStack(Items.SKULL, 1, 4), new EntityVillager.PriceInfo(1, 20)));// creeper
        UNDERTAKER_CAREER.addTrade(2, new EntityVillager.ListItemForEmeralds(new ItemStack(Items.SKULL, 1, 1), new EntityVillager.PriceInfo(1, 30)));// wither
        VillagerRegistry.instance().register(UNDERTAKER_PROFESSION);
    }
}
