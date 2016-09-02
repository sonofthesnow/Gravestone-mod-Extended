package nightkosh.gravestone_extended.block.enums;

import net.minecraft.util.ResourceLocation;
import nightkosh.gravestone.block.enums.IBlockEnum;
import nightkosh.gravestone_extended.core.Resources;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public enum EnumExecution implements IBlockEnum {

    GALLOWS("block.execution.gallows", Resources.GIBBET),
    GIBBET("block.execution.gibbet", Resources.GIBBET),
    STOCKS("block.execution.stocks", Resources.STOCKS),
    BURNING_STAKE("block.execution.burning_stake", Resources.BURNING_STAKE);

    private String name;
    private ResourceLocation texture;

    private EnumExecution(String name, ResourceLocation texture) {
        this.name = name;
        this.texture = texture;
    }

    @Override
    public String getUnLocalizedName() {
        return this.name;
    }

    public ResourceLocation getTexture() {
        return this.texture;
    }

    public static EnumExecution getById(int id) {
        if (id < values().length) {
            return values()[id];
        }
        return GIBBET;
    }
}
