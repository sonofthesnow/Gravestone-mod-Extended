package nightkosh.gravestone_extended.entity.monster.horse;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAvoidEntity;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.entity.passive.HorseType;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;
import nightkosh.gravestone_extended.core.Entity;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class EntitySkeletonHorse extends EntityUndeadHorse {

    public EntitySkeletonHorse(World worldIn) {
        super(worldIn);

        this.tasks.addTask(3, new EntityAIAvoidEntity(this, EntityWolf.class, 6, 1, 1.2));
    }

    @Override
    public HorseType getUndeadHorseType() {
        return HorseType.SKELETON;
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(3);
    }

    @Override
    public String getName() {
        if (this.hasCustomName()) {
            return this.getCustomNameTag();
        } else {
            return I18n.translateToLocal("entity." + Entity.MINECRAFT_SKELETON_HORSE_NAME + ".name");
        }
    }
}
