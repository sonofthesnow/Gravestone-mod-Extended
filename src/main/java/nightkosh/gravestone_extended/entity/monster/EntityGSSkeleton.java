package nightkosh.gravestone_extended.entity.monster;

import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.init.Items;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import nightkosh.gravestone_extended.core.Entity;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class EntityGSSkeleton extends EntitySkeleton {
    public EntityGSSkeleton(World worldIn) {
        super(worldIn);
    }

    @Override
    public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, IEntityLivingData livingData) {
        livingData = super.onInitialSpawn(difficulty, livingData);

        if (this.rand.nextBoolean()) {
            this.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, new ItemStack(Items.STONE_SWORD, 1, 0));
        } else {
            this.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, new ItemStack(Items.BOW, 1, 0));
        }

        return livingData;
    }

    @Override
    public String getName() {
        if (this.hasCustomName()) {
            return this.getCustomNameTag();
        } else {
            return I18n.translateToLocal("entity." + Entity.MINECRAFT_SKELETON_ID + ".name");
        }
    }
}
