package nightkosh.gravestone_extended.entity.ai;

import net.minecraft.entity.ai.EntityAIBase;
import nightkosh.gravestone_extended.entity.monster.EntityBarghest;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class AIBarghestInvisible extends EntityAIBase {

    protected EntityBarghest mob;

    public AIBarghestInvisible(EntityBarghest mob) {
        this.mob = mob;
    }

    @Override
    public boolean shouldExecute() {
        return this.mob.getAttackTarget() == null && this.mob.getRevengeTarget() == null;
    }

    @Override
    public boolean shouldContinueExecuting() {
        if (this.shouldExecute()) {
            this.mob.setBarghestInvisible(true);
            return true;
        } else {
            this.mob.setBarghestInvisible(false);
            return false;
        }
    }
}
