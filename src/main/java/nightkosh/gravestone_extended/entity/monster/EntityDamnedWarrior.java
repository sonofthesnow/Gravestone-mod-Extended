package nightkosh.gravestone_extended.entity.monster;

import net.minecraft.block.Block;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class EntityDamnedWarrior extends EntityMob {

    public EntityDamnedWarrior(World world) {
        super(world);


//        this.tasks.addTask(2, new EntityAIRestrictSun(this));
//        this.tasks.addTask(5, new EntityAIWander(this, 1));
//        this.tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 10));
//        this.tasks.addTask(7, new EntityAILookIdle(this));
//
//
//        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false, new Class[0]));
//        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
//        this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityIronGolem.class, true));
//
//        if (world != null && !world.isRemote) {
//            this.tasks.addTask(4, new EntityAIAttackOnCollide(this, EntityPlayer.class, 1.2D, false));
//        }
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.25D);
    }

    @Override
    protected String getLivingSound() {
        return "mob.skeleton.say";
    }

    @Override
    protected String getHurtSound() {
        return "mob.skeleton.hurt";
    }

    @Override
    protected String getDeathSound() {
        return "mob.skeleton.death";
    }

    @Override
    protected void playStepSound(BlockPos pos, Block blockIn) {
        this.playSound("mob.skeleton.step", 0.15F, 1);
    }

    @Override
    public EnumCreatureAttribute getCreatureAttribute() {
        return EnumCreatureAttribute.UNDEAD;
    }

    @Override
    protected void dropFewItems(boolean wasRecentlyHit, int lootingModifier) {
        int l = this.rand.nextInt(3 + lootingModifier);

        for (int j1 = 0; j1 < l; ++j1) {
            this.dropItem(Items.bone, 1);
        }
    }

    @Override
    protected void addRandomDrop() {
        this.entityDropItem(new ItemStack(Items.skull, 1, 1), 0.0F);
    }

    @Override
    public float getEyeHeight() {
        return 2.74F;
    }
}
