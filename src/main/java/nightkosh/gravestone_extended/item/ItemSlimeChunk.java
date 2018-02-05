package nightkosh.gravestone_extended.item;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class ItemSlimeChunk extends ItemImpSkull {

    public ItemSlimeChunk() {
        this.setUnlocalizedName("gravestone.slime_chunk");
    }

    @Override
    protected String getItemRegistryName() {
        return "gs_slime_chunk";
    }
}
