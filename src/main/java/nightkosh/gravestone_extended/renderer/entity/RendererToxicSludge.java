package nightkosh.gravestone_extended.renderer.entity;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelSlime;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderEntityItem;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import nightkosh.gravestone_extended.block.enums.EnumCorpse;
import nightkosh.gravestone_extended.core.Resources;
import nightkosh.gravestone_extended.entity.monster.EntityToxicSludge;
import nightkosh.gravestone_extended.renderer.entity.layers.LayerToxicSludgeGel;
import nightkosh.gravestone_extended.renderer.tileentity.CorpseRendererHelper;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class RendererToxicSludge extends RenderLiving<EntityToxicSludge> {

    public RendererToxicSludge(RenderManager renderManager) {
        super(renderManager, new ModelSlime(16), 0.25F);
        this.addLayer(new LayerToxicSludgeGel(this));
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityToxicSludge entity) {
        return Resources.TOXIC_SLUDGE;
    }

    @Override
    public void doRender(EntityToxicSludge sludge, double x, double y, double z, float entityYaw, float partialTicks) {
        if (sludge.getSlimeSize() > 1) {
            if (sludge.getSlimeSize() > 2) {
                if (!sludge.getItemStackFromSlot(EntityEquipmentSlot.MAINHAND).isEmpty()) {
                    CorpseRendererHelper.renderCorpseInsideSlime(EnumCorpse.getById((byte) sludge.getItemStackFromSlot(EntityEquipmentSlot.MAINHAND).getItemDamage()),
                            sludge.getItemStackFromSlot(EntityEquipmentSlot.MAINHAND).getTagCompound(), x, y, z);
                }
                renderItem(sludge.world, sludge.getItemStackFromSlot(EntityEquipmentSlot.HEAD), x + 0.5, y + 1, z + 0.5, entityYaw, partialTicks);
                renderItem(sludge.world, sludge.getItemStackFromSlot(EntityEquipmentSlot.CHEST), x - 0.5, y + 1, z - 0.5, entityYaw, partialTicks);
                renderItem(sludge.world, sludge.getItemStackFromSlot(EntityEquipmentSlot.LEGS), x + 0.5, y, z - 0.5, entityYaw, partialTicks);
                renderItem(sludge.world, sludge.getItemStackFromSlot(EntityEquipmentSlot.FEET), x - 0.5, y, z + 0.5, entityYaw, partialTicks);
            } else {
                renderItem(sludge.world, sludge.getItemStackFromSlot(EntityEquipmentSlot.MAINHAND), x - 0.25, y - 0.25, z + 0.25, entityYaw, partialTicks);
                renderItem(sludge.world, sludge.getItemStackFromSlot(EntityEquipmentSlot.HEAD), x + 0.25, y + 0.25, z + 0.25, entityYaw, partialTicks);
                renderItem(sludge.world, sludge.getItemStackFromSlot(EntityEquipmentSlot.CHEST), x - 0.25, y + 0.252, z - 0.25, entityYaw, partialTicks);
                renderItem(sludge.world, sludge.getItemStackFromSlot(EntityEquipmentSlot.LEGS), x + 0.25, y - 0.25, z - 0.25, entityYaw, partialTicks);
                renderItem(sludge.world, sludge.getItemStackFromSlot(EntityEquipmentSlot.FEET), x, y, z, entityYaw, partialTicks);
            }
        }
        this.shadowSize = 0.25F * (float) sludge.getSlimeSize();

        GlStateManager.color(1, 1, 1, 1);
        GlStateManager.enableNormalize();
        GlStateManager.enableBlend();
        GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
        super.doRender(sludge, x, y, z, entityYaw, partialTicks);

        GlStateManager.disableBlend();
        GlStateManager.disableNormalize();
    }

    @Override
    protected void preRenderCallback(EntityToxicSludge entity, float partialTickTime) {
        GlStateManager.scale(0.999F, 0.999F, 0.999F);
        float f1 = entity.getSlimeSize();
        float f2 = (entity.prevSquishFactor + (entity.squishFactor - entity.prevSquishFactor) * partialTickTime) / (f1 * 0.5F + 1.0F);
        float f3 = 1 / (f2 + 1);
        GlStateManager.scale(f3 * f1, 1 / f3 * f1, f3 * f1);
    }

    private void renderItem(World world, ItemStack stack, double x, double y, double z, float entityYaw, float partialTicks) {
        if (!stack.isEmpty()) {
            EntityItem entityItem = new EntityItem(world, x, y, z, stack);
            entityItem.hoverStart = 0;
            renderItem(entityItem, x, y, z, entityYaw, partialTicks);
        }
    }

    protected void renderItem(EntityItem entityItem, double x, double y, double z, float entityYaw, float partialTicks) {
        Render<EntityItem> render = Minecraft.getMinecraft().getRenderManager().getEntityRenderObject(entityItem);
        if (render != null && render instanceof RenderEntityItem) {
            GlStateManager.pushMatrix();

            RenderEntityItem renderItem = (RenderEntityItem) render;
            renderItem.doRender(entityItem, x, y, z, entityYaw, partialTicks);

            GlStateManager.disableRescaleNormal();
            GlStateManager.disableBlend();
            GlStateManager.popMatrix();
        }
    }
}
