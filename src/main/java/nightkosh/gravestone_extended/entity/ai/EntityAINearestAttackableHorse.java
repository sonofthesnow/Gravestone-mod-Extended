package nightkosh.gravestone_extended.entity.ai;

import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.passive.EntitySkeletonHorse;
import net.minecraft.entity.passive.EntityZombieHorse;
import nightkosh.gravestone_extended.entity.monster.horse.EntityUndeadHorse;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class EntityAINearestAttackableHorse extends EntityAINearestAttackableTarget {

    protected EntityCreature attacker;

    public EntityAINearestAttackableHorse(EntityCreature creature, boolean checkSight) {
        super(creature, EntityHorse.class, checkSight);
        attacker = creature;
    }

    public boolean shouldExecute() {
        EntityLivingBase entity = this.attacker.getAttackTarget();
        if (entity != null && !(entity instanceof EntityUndeadHorse) && !(entity instanceof EntityZombieHorse) && !(entity instanceof EntitySkeletonHorse)) {
            return super.shouldExecute();
        }
        return false;
    }
}
