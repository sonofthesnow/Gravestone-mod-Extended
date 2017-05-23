package nightkosh.gravestone_extended.entity.monster.pet;

import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public abstract class EntityUndeadDog extends EntityUndeadPet {

    private static final DataParameter<Float> DATA_HEALTH_ID = EntityDataManager.createKey(EntityUndeadDog.class, DataSerializers.FLOAT);
    private static final DataParameter<Boolean> BEGGING = EntityDataManager.<Boolean>createKey(EntityUndeadDog.class, DataSerializers.BOOLEAN);
    protected float headRotationCourse;
    protected float headRotationCourseOld;

    public EntityUndeadDog(World world) {
        super(world);
    }

    @Override
    protected void updateAITasks() {
        this.dataManager.set(DATA_HEALTH_ID, Float.valueOf(this.getHealth()));
    }

    @Override
    protected void entityInit() {
        super.entityInit();
        this.dataManager.register(DATA_HEALTH_ID, Float.valueOf(this.getHealth()));
        this.dataManager.register(BEGGING, Boolean.valueOf(false));
    }

    /**
     * Called to update the entity's position/logic.
     */
    @Override
    public void onUpdate() {
        super.onUpdate();
        this.headRotationCourseOld = this.headRotationCourse;

        if (this.isBegging()) {
            this.headRotationCourse += (1 - this.headRotationCourse) * 0.4F;
        } else {
            this.headRotationCourse += (0 - this.headRotationCourse) * 0.4F;
        }
    }

    @SideOnly(Side.CLIENT)
    public float getInterestedAngle(float par1) {
        return (this.headRotationCourseOld + (this.headRotationCourse - this.headRotationCourseOld) * par1) * 0.15F * (float) Math.PI;
    }

    @Override
    public float getEyeHeight() {
        return this.height * 0.8F;
    }

    @SideOnly(Side.CLIENT)
    public float getTailRotation() {
        return (0.55F - (20 - this.dataManager.get(DATA_HEALTH_ID)) * 0.02F) * (float) Math.PI;
    }

    public boolean isBegging() {
        return this.dataManager.get(BEGGING).booleanValue();
    }
}
