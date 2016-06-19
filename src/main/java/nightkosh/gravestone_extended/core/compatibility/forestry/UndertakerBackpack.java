package nightkosh.gravestone_extended.core.compatibility.forestry;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class UndertakerBackpack{}
//        implements IBackpackDefinition {
//    protected List<ItemStack> items = new ArrayList<ItemStack>(45);
//
//    protected static final List allowedBlocks = Arrays.asList(
//            Block.graveStone,
//            Block.memorial,
//            Block.candle,
//            Block.skullCandle
//    );
//    protected static final List allowedItems = Arrays.asList(
//            ExtendedItem.chisel,
//            ExtendedItem.corpse,
//            Items.skull
//    );
//
//    private static UndertakerBackpack instance;
//
//    public static UndertakerBackpack getInstance() {
//        if (instance == null)
//            instance = new UndertakerBackpack();
//        return instance;
//    }
//
//    @Override
//    public String getKey() {
//        return "UNDERTAKER";
//    }
//
//    @Override
//    public String getName() {
//        return null;
//    }
//
//    @Override
//    public String getName(ItemStack backpack) {
//        return ModGravestoneExtended.proxy.getLocalizedString(backpack.getItem().getUnlocalizedName());
//    }
//
//    @Override
//    public int getPrimaryColour() {
//        return 1842478;
//    }
//
//    @Override
//    public int getSecondaryColour() {
//        return 3552587;
//    }
//
//    @Override
//    public void addValidItem(ItemStack item) {
//        if (item != null) {
//            this.items.add(item);
//        }
//    }
//
//    @Override
//    public void addValidItems(List<ItemStack> validItems) {
//        for (ItemStack stack : validItems) {
//            addValidItem(stack);
//        }
//    }
//
//    @Override
//    public boolean isValidItem(ItemStack itemstack) {
//        return allowedBlocks.contains(net.minecraft.block.Block.getBlockFromItem(itemstack.getItem())) ||
//                allowedItems.contains(itemstack.getItem());
//    }
//
//    @Override
//    public boolean isValidItem(EntityPlayer player, ItemStack itemstack) {
//        return isValidItem(itemstack);
//    }
//}