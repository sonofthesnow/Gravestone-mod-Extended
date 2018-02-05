package nightkosh.gravestone_extended.item;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class ItemEnderSkull extends ItemImpSkull {

    public ItemEnderSkull() {
        this.setUnlocalizedName("gravestone.ender_skull");
    }

    @Override
    protected String getItemRegistryName() {
        return "gs_ender_skull";
    }
}
