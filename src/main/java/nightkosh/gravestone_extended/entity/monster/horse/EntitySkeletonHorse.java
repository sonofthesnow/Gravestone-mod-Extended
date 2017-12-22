package nightkosh.gravestone_extended.entity.monster.horse;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAvoidEntity;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;
import net.minecraft.world.storage.loot.LootTableList;
import nightkosh.gravestone_extended.core.GSEntity;

import javax.annotation.Nullable;

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
    public double getMountedYOffset() {
        return super.getMountedYOffset() - 0.1875D;
    }

    @Override
    protected SoundEvent getAmbientSound() {
        super.getAmbientSound();
        return SoundEvents.ENTITY_SKELETON_HORSE_AMBIENT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        super.getDeathSound();
        return SoundEvents.ENTITY_SKELETON_HORSE_DEATH;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource) {
        super.getHurtSound(damageSource);
        return SoundEvents.ENTITY_SKELETON_HORSE_HURT;
    }

    @Nullable
    protected ResourceLocation getLootTable() {
        return LootTableList.ENTITIES_SKELETON_HORSE;
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(3);
    }

    @Override
    protected String getUndeadHorseTexture() {
        return "textures/entity/horse/horse_skeleton.png";
    }

    @Override
    public String getName() {
        if (this.hasCustomName()) {
            return this.getCustomNameTag();
        } else {
            return I18n.translateToLocal("entity." + GSEntity.MINECRAFT_SKELETON_HORSE_NAME + ".name");
        }
    }
}
