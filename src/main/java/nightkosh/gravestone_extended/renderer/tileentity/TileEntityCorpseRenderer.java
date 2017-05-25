package nightkosh.gravestone_extended.renderer.tileentity;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import nightkosh.gravestone_extended.block.enums.EnumCorpse;
import nightkosh.gravestone_extended.tileentity.TileEntityCorpse;
import org.lwjgl.opengl.GL11;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class TileEntityCorpseRenderer extends TileEntitySpecialRenderer {

    private static final TileEntityCorpse CORPSE_TE = new TileEntityCorpse();


    public static void renderCorpseOnAltar(ItemStack item, double x, double y, double z, float ticks) {
        if (item != null) {
            GL11.glPushMatrix();
            float time = Minecraft.getMinecraft().theWorld.getTotalWorldTime() + ticks;
            GL11.glTranslated(x + 0.5F, y + 1.2F, z + 0.5F);
            GL11.glRotatef(time % 360, 0, 1, 0);

            CorpseRendererHelper.renderCorpse(EnumCorpse.getById((byte) item.getItemDamage()), item.getTagCompound(), true);
            GL11.glPopMatrix();
        }
    }

    public void renderCorpse(TileEntityCorpse te, double x, double y, double z, float ticks) {
        if (te == null) {
            te = getDefaultTE();
        }

        GL11.glPushMatrix();

        GL11.glRotatef(-35, 0, 1, 0);
        GL11.glScalef(0.5F, 0.5F, 0.5F);
        GL11.glTranslated(x + 1.7F, y, z);
        CorpseRendererHelper.renderCorpse(EnumCorpse.getById((byte) te.getBlockMetadata()), null, false);
        GL11.glPopMatrix();
    }

    protected TileEntityCorpse getDefaultTE() {
        return CORPSE_TE;
    }

    @Override
    public void renderTileEntityAt(TileEntity te, double x, double y, double z, float partialTicks, int destroyStage) {
        renderCorpse((TileEntityCorpse) te, x, y, z, partialTicks);
    }

    public static class Steve extends TileEntityCorpseRenderer {
        private static final TileEntityCorpse CORPSE_TE = new TileEntityCorpse.Steve();

        @Override
        protected TileEntityCorpse getDefaultTE() {
            return CORPSE_TE;
        }
    }

    public static class Villager extends TileEntityCorpseRenderer {
        private static final TileEntityCorpse CORPSE_TE = new TileEntityCorpse.Villager();

        @Override
        protected TileEntityCorpse getDefaultTE() {
            return CORPSE_TE;
        }
    }

    public static class Dog extends TileEntityCorpseRenderer {
        private static final TileEntityCorpse CORPSE_TE = new TileEntityCorpse.Dog();

        @Override
        protected TileEntityCorpse getDefaultTE() {
            return CORPSE_TE;
        }
    }

    public static class Cat extends TileEntityCorpseRenderer {
        private static final TileEntityCorpse CORPSE_TE = new TileEntityCorpse.Cat();

        @Override
        protected TileEntityCorpse getDefaultTE() {
            return CORPSE_TE;
        }
    }

    public static class Horse extends TileEntityCorpseRenderer {
        private static final TileEntityCorpse CORPSE_TE = new TileEntityCorpse.Horse();

        @Override
        protected TileEntityCorpse getDefaultTE() {
            return CORPSE_TE;
        }
    }

    public static class Zombie extends TileEntityCorpseRenderer {
        private static final TileEntityCorpse CORPSE_TE = new TileEntityCorpse.Zombie();

        @Override
        protected TileEntityCorpse getDefaultTE() {
            return CORPSE_TE;
        }
    }

    public static class ZombieVillager extends TileEntityCorpseRenderer {
        private static final TileEntityCorpse CORPSE_TE = new TileEntityCorpse.ZombieVillager();

        @Override
        protected TileEntityCorpse getDefaultTE() {
            return CORPSE_TE;
        }
    }

    public static class Skeleton extends TileEntityCorpseRenderer {
        private static final TileEntityCorpse CORPSE_TE = new TileEntityCorpse.Skeleton();

        @Override
        protected TileEntityCorpse getDefaultTE() {
            return CORPSE_TE;
        }
    }

    public static class Witch extends TileEntityCorpseRenderer {
        private static final TileEntityCorpse CORPSE_TE = new TileEntityCorpse.Witch();

        @Override
        protected TileEntityCorpse getDefaultTE() {
            return CORPSE_TE;
        }
    }
}
