package nightkosh.gravestone_extended.fluid;

import net.minecraftforge.fluids.Fluid;
import nightkosh.gravestone_extended.core.Resources;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class FluidToxicWater extends Fluid {
    public static final FluidToxicWater INSTANCE = new FluidToxicWater();

    public FluidToxicWater() {
        super("gs_toxic_water", Resources.TOXIC_WATER_STILL, Resources.TOXIC_WATER_FLOW);
    }

    @Override
    public int getColor() {
        return 0x43F42F;
    }
}
