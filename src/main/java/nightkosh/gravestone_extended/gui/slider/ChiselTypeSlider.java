package nightkosh.gravestone_extended.gui.slider;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import nightkosh.gravestone.api.grave.EnumGraveMaterial;
import nightkosh.gravestone.api.grave.EnumGraveType;
import nightkosh.gravestone.block.enums.EnumGraves;
import nightkosh.gravestone_extended.ModGravestoneExtended;
import nightkosh.gravestone_extended.gui.GSChiselCraftingGui;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
@SideOnly(Side.CLIENT)
public class ChiselTypeSlider extends AbstractSlider {
    GSChiselCraftingGui gui;

    public ChiselTypeSlider(int id, int xPos, int yPos, int width, int height, double currentVal, GSChiselCraftingGui gui) {
        super(id, xPos, yPos, width, height, "", "", 0, EnumGraveType.values().length - 2, currentVal, false, false, (slider) -> {

            gui.setType(EnumGraveType.values()[slider.getValueInt()]);
            gui.sendMessage();
        });

        this.gui = gui;
    }

    @Override
    public String getString() {
        int num = this.getValueInt() * EnumGraveMaterial.values().length;
        if (this.gui != null) {
            num += this.gui.getMaterial().ordinal();
        }
        return ModGravestoneExtended.proxy.getLocalizedString(EnumGraves.getById(num).getUnLocalizedName() + ".name");
    }
}