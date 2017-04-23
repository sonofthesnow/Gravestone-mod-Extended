package nightkosh.gravestone_extended.entity.ai;

import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.passive.EntityHorse;
import nightkosh.gravestone_extended.entity.monster.EntityUndeadHorse;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class EntityAIAttackLivingHorse extends EntityAIAttackMelee {

    protected EntityCreature attacker;

    public EntityAIAttackLivingHorse(EntityCreature creature, double speedTowardsTarget, boolean longMemory) {
//        super(creature, EntityHorse.class, speedTowardsTarget, longMemory);
        super(creature, speedTowardsTarget, longMemory);
        attacker = creature;
    }

    public boolean shouldExecute() {
        EntityLivingBase entity = this.attacker.getAttackTarget();
        if (entity != null && entity instanceof EntityHorse && !(entity instanceof EntityUndeadHorse)) {
            EntityHorse horse = (EntityHorse) entity;
            if (!horse.getType().isUndead()) {
                return super.shouldExecute();
            }
        }
        return false;
    }
}
