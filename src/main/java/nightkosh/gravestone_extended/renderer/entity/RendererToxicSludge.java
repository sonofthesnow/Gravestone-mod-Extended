package nightkosh.gravestone_extended.renderer.entity;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderEntityItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderSlime;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import nightkosh.gravestone_extended.block.enums.EnumCorpse;
import nightkosh.gravestone_extended.core.Resources;
import nightkosh.gravestone_extended.entity.monster.EntityToxicSludge;
import nightkosh.gravestone_extended.renderer.tileentity.CorpseRendererHelper;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class RendererToxicSludge extends RenderSlime {
    public RendererToxicSludge(RenderManager renderManager) {
        super(renderManager);
    }

    protected ResourceLocation getEntityTexture(EntitySlime entity) {
        return Resources.TOXIC_SLUDGE;
    }

    @Override
    public void doRender(EntitySlime entity, double x, double y, double z, float entityYaw, float partialTicks) {
        EntityToxicSludge sludge = ((EntityToxicSludge) entity);
        if (sludge.getSlimeSize() > 1) {
            if (sludge.getSlimeSize() > 2) {
                if (!sludge.getItemStackFromSlot(EntityEquipmentSlot.MAINHAND).isEmpty()) {
                    CorpseRendererHelper.renderCorpseInsideSlime(EnumCorpse.getById((byte) sludge.getItemStackFromSlot(EntityEquipmentSlot.MAINHAND).getItemDamage()),
                            sludge.getItemStackFromSlot(EntityEquipmentSlot.MAINHAND).getTagCompound(), x, y, z);
                }
                renderItem(entity.world, sludge.getItemStackFromSlot(EntityEquipmentSlot.HEAD), x + 0.5, y + 1, z + 0.5, entityYaw, partialTicks);
                renderItem(entity.world, sludge.getItemStackFromSlot(EntityEquipmentSlot.CHEST), x - 0.5, y + 1, z - 0.5, entityYaw, partialTicks);
                renderItem(entity.world, sludge.getItemStackFromSlot(EntityEquipmentSlot.LEGS), x + 0.5, y, z - 0.5, entityYaw, partialTicks);
                renderItem(entity.world, sludge.getItemStackFromSlot(EntityEquipmentSlot.FEET), x - 0.5, y, z + 0.5, entityYaw, partialTicks);
            } else {
                renderItem(entity.world, sludge.getItemStackFromSlot(EntityEquipmentSlot.MAINHAND), x - 0.25, y - 0.25, z + 0.25, entityYaw, partialTicks);
                renderItem(entity.world, sludge.getItemStackFromSlot(EntityEquipmentSlot.HEAD), x + 0.25, y + 0.25, z + 0.25, entityYaw, partialTicks);
                renderItem(entity.world, sludge.getItemStackFromSlot(EntityEquipmentSlot.CHEST), x - 0.25, y + 0.252, z - 0.25, entityYaw, partialTicks);
                renderItem(entity.world, sludge.getItemStackFromSlot(EntityEquipmentSlot.LEGS), x + 0.25, y - 0.25, z - 0.25, entityYaw, partialTicks);
                renderItem(entity.world, sludge.getItemStackFromSlot(EntityEquipmentSlot.FEET), x, y, z, entityYaw, partialTicks);
            }
        }
        super.doRender(entity, x, y, z, entityYaw, partialTicks);
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
