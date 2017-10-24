package nightkosh.gravestone_extended.entity.ai;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import nightkosh.gravestone_extended.entity.monster.horse.EntityUndeadHorse;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class EntityUndeadHorseAINearestAttackableTarget<T extends EntityLivingBase> extends EntityAINearestAttackableTarget {

    protected EntityUndeadHorse horse;

    public EntityUndeadHorseAINearestAttackableTarget(EntityUndeadHorse horse, Class<T> classTarget, boolean checkSight) {
        super(horse, classTarget, checkSight);
        this.horse = horse;
    }

    public boolean shouldExecute() {
        return !horse.isTame() && super.shouldExecute();
    }
}
