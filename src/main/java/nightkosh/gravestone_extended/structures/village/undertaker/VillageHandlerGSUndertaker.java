package nightkosh.gravestone_extended.structures.village.undertaker;

import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.gen.structure.StructureVillagePieces;
import net.minecraftforge.fml.common.registry.VillagerRegistry;

import java.util.List;
import java.util.Random;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class VillageHandlerGSUndertaker implements VillagerRegistry.IVillageCreationHandler {

    @Override
    public StructureVillagePieces.PieceWeight getVillagePieceWeight(Random random, int size) {
        return new StructureVillagePieces.PieceWeight(ComponentVillageUndertaker.class, 3, MathHelper.getRandomIntegerInRange(random, 0, 1));
    }

    @Override
    public Class getComponentClass() {
        return ComponentVillageUndertaker.class;
    }

    @Override
    public StructureVillagePieces.Village buildComponent(StructureVillagePieces.PieceWeight villagePiece, StructureVillagePieces.Start startPiece, List pieces, Random random, int p1, int p2, int p3, EnumFacing facing, int p5) {
        return ComponentVillageUndertaker.buildComponent(startPiece, pieces, random, p1, p2, p3, facing, p5);
    }
}
