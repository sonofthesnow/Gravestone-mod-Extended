package nightkosh.gravestone_extended.renderer.entity;

import com.google.common.collect.Maps;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelHorse;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.LayeredTexture;
import net.minecraft.util.ResourceLocation;
import nightkosh.gravestone_extended.entity.monster.horse.EntityUndeadHorse;

import javax.annotation.Nullable;
import java.util.Map;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class RenderUndeadHorse extends RenderLiving<EntityUndeadHorse> {

    protected static final Map<String, ResourceLocation> TEXTURES_MAP = Maps.newHashMap();

    public RenderUndeadHorse(RenderManager renderManager) {
        super(renderManager, new ModelHorse(), 1);
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(EntityUndeadHorse horse) {
        String s = horse.getHorseTexture();
        ResourceLocation resourcelocation = TEXTURES_MAP.get(s);

        if (resourcelocation == null) {
            resourcelocation = new ResourceLocation(s);
            Minecraft.getMinecraft().getTextureManager().loadTexture(resourcelocation, new LayeredTexture(horse.getVariantTexturePaths()));
            TEXTURES_MAP.put(s, resourcelocation);
        }

        return resourcelocation;
    }
}
