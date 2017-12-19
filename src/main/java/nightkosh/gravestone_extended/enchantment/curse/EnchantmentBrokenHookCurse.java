package nightkosh.gravestone_extended.enchantment.curse;

import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.inventory.EntityEquipmentSlot;
import nightkosh.gravestone_extended.core.ModInfo;

import java.util.Random;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class EnchantmentBrokenHookCurse extends EnchantmentCurse {
    private static final Random RANDOM = new Random();

    public EnchantmentBrokenHookCurse() {
        super(EnumEnchantmentType.FISHING_ROD, new EntityEquipmentSlot[] {EntityEquipmentSlot.MAINHAND});
        this.setName("broken_hook_curse");
        this.setRegistryName(ModInfo.ID, "gs_broken_hook_curse");
    }

    public static boolean cancelFishing() {
        return RANDOM.nextInt(10) < 3;
    }
}
