package nightkosh.gravestone_extended.renderer.entity;

import nightkosh.gravestone_extended.core.Resources;
import nightkosh.gravestone_extended.entity.monster.crawler.EntitySkullCrawler;
import nightkosh.gravestone_extended.entity.monster.crawler.EntitySkullCrawler.SkullCrawlerType;
import nightkosh.gravestone_extended.models.entity.ModelSkullCrawler;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class RenderSkullCrawler extends RenderLiving {
    private SkullCrawlerType crawlerType;

    public RenderSkullCrawler(SkullCrawlerType crawlerType, RenderManager renderManager) {
        super(renderManager, new ModelSkullCrawler(), 0.2F);
        this.crawlerType = crawlerType;
    }

    protected float setSpiderDeathMaxRotation(EntitySkullCrawler entity) {
        return 180;
    }

    @Override
    protected float getDeathMaxRotation(EntityLivingBase entity) {
        return this.setSpiderDeathMaxRotation((EntitySkullCrawler) entity);
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity entity) {
        switch (crawlerType) {
            case WITHER:
                return Resources.WITHER_SKULL_CRAWLER;
            case ZOMBIE:
                return Resources.ZOMBIE_SKULL_CRAWLER;
            case STRAY:
                return Resources.STRAY_SKULL_CRAWLER;
            case HUSK:
                return Resources.HUSK_SKULL_CRAWLER;
            case PIGMAN:
                return Resources.PIGMAN_SKULL_CRAWLER;
            case SKELETON:
            default:
                return Resources.SKELETON_SKULL_CRAWLER;
        }
    }
}
