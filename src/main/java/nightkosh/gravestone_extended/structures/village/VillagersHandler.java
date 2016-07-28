package nightkosh.gravestone_extended.structures.village;

import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraftforge.fml.common.registry.VillagerRegistry;
import nightkosh.gravestone_extended.core.Resources;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class VillagersHandler {


    public static final int UNDERTAKER_ID = 385;
    public static final String UNDERTAKER_NAME = "Undertaker";

    public static final VillagerRegistry.VillagerProfession UNDERTAKER_PROFESSION = new VillagerRegistry.VillagerProfession(UNDERTAKER_NAME, Resources.UNDERTAKER);
    public static final VillagerRegistry.VillagerCareer UNDERTAKER_CAREER = new VillagerRegistry.VillagerCareer(UNDERTAKER_PROFESSION, UNDERTAKER_NAME);

    public static void registerVillagers() {
        VillagerRegistry.instance().register(UNDERTAKER_PROFESSION);
        //add a trade for the career, using an addTrade(ITradeList trade) method in VillagerCareer
//        UNDERTAKER_CAREER.addTrade(new EmeraldForItems(GSItem.chisel, new PriceInfo(1, 1)));
//        UNDERTAKER_CAREER.addTrade(new ListItemForEmeralds(new ItemStack(GSBlock.candle, 10), new PriceInfo(1, 1)));
//        // skulls
//        UNDERTAKER_CAREER.addTrade(new ListItemForEmeralds(new ItemStack(Items.skull, 1), new PriceInfo(1, 15)));// skeleton
//        UNDERTAKER_CAREER.addTrade(new ListItemForEmeralds(new ItemStack(Items.skull, 1, 2), new PriceInfo(1, 15)));// zombie
//        UNDERTAKER_CAREER.addTrade(new ListItemForEmeralds(new ItemStack(Items.skull, 1, 3), new PriceInfo(1, 20)));// steve
//        UNDERTAKER_CAREER.addTrade(new ListItemForEmeralds(new ItemStack(Items.skull, 1, 4), new PriceInfo(1, 20)));// creeper
//        UNDERTAKER_CAREER.addTrade(new ListItemForEmeralds(new ItemStack(Items.skull, 1, 1), new PriceInfo(1, 30)));// wither
    }

    public static void atVillagerSpawn(Entity entity) {
        if (entity instanceof EntityVillager) {
//            EntityVillager villager = (EntityVillager) entity;
//
//            /*
//             * This VillagerProfession.getId() should probably check a value set while regsitering the profession
//             * with VillagerRegistry.instance().register(PROFESSION);
//             */
//            villager.setProfession(UNDERTAKER_PROFESSION.getId());
//
//            /**
//             * If this Method isn't called, it should randomly select a career from the list of careers for that
//             * profession, every time new VillagerRegistry.VillagerCareer(PROFESSION, "Derpington"); is called,
//             * it should add that new VillageCareer to a list for PROFESSION
//             */
//            villager.setCareer(UNDERTAKER_CAREER);
        }
    }
}
