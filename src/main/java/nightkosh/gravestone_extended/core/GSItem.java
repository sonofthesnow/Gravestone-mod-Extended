package nightkosh.gravestone_extended.core;

import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.IForgeRegistry;
import nightkosh.gravestone_extended.item.ItemChisel;
import nightkosh.gravestone_extended.item.ItemGSMonsterPlacer;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
@GameRegistry.ObjectHolder(ModInfo.ID)
public class GSItem {

    public static final Item CHISEL = new ItemChisel();
    public static final Item SPAWN_EGG = new ItemGSMonsterPlacer();

    @Mod.EventBusSubscriber(modid = ModInfo.ID)
    public static class RegistrationHandler {

        @SubscribeEvent
        public static void registerItems(final RegistryEvent.Register<Item> event) {
            final IForgeRegistry<Item> registry = event.getRegistry();
            registry.registerAll(CHISEL, SPAWN_EGG);
        }
    }
}
