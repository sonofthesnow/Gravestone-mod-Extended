package nightkosh.gravestone_extended.core;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.gen.structure.MapGenStructureIO;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.common.registry.VillagerRegistry;
import nightkosh.gravestone_extended.config.ExtendedConfig;
import nightkosh.gravestone_extended.core.logger.GSLogger;
import nightkosh.gravestone_extended.structures.GraveStoneWorldGenerator;
import nightkosh.gravestone_extended.structures.village.memorial.ComponentVillageMemorial;
import nightkosh.gravestone_extended.structures.village.memorial.VillageHandlerMemorial;
import nightkosh.gravestone_extended.structures.village.undertaker.ComponentVillageUndertaker;
import nightkosh.gravestone_extended.structures.village.undertaker.VillageHandlerGSUndertaker;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class Structures {

    public static final Block[] VALUABLE_BLOCKS = {
            Blocks.gold_block, Blocks.lapis_block, Blocks.redstone_block,
            Blocks.gold_block, Blocks.lapis_block, Blocks.redstone_block,
            Blocks.diamond_block, Blocks.emerald_block
    };
    private static Structures instance;

    private Structures() {
        generateStructures();
    }

    public static Structures getInstance() {
        if (instance == null) {
            return new Structures();
        } else {
            return instance;
        }
    }

    public static void preInit() {
        // register memorials
        if (ExtendedConfig.generateVillageMemorials) {
            try {
                MapGenStructureIO.registerStructureComponent(ComponentVillageMemorial.class, "GSVillageMemorial");
            } catch (Throwable e) {
                GSLogger.logError("Can not register ComponentGSVillageMemorial");
                e.printStackTrace();
            }
        }

        // register Undertaker
        if (ExtendedConfig.generateUndertaker) {
            try {
                MapGenStructureIO.registerStructureComponent(ComponentVillageUndertaker.class, "GSUndertakerHouse");
            } catch (Throwable e) {
                GSLogger.logError("Can not register ComponentGSVillageUndertaker");
                e.printStackTrace();
            }
        }
    }

    private void generateStructures() {
        // register memorials
        if (ExtendedConfig.generateVillageMemorials) {
            VillageHandlerMemorial villageMemorialHandler = new VillageHandlerMemorial();
            VillagerRegistry.instance().registerVillageCreationHandler(villageMemorialHandler);
        }

        // register Undertaker
        if (ExtendedConfig.generateUndertaker) {
            VillageHandlerGSUndertaker villageUndertakerHandler = new VillageHandlerGSUndertaker();
            VillagerRegistry.instance().registerVillageCreationHandler(villageUndertakerHandler);
        }

        // structure generator
        GameRegistry.registerWorldGenerator(new GraveStoneWorldGenerator(), 50);
    }
}
