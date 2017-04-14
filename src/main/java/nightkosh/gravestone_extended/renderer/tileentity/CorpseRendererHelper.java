package nightkosh.gravestone_extended.renderer.tileentity;

import com.google.common.collect.Maps;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelHorse;
import net.minecraft.client.model.ModelOcelot;
import net.minecraft.client.model.ModelWolf;
import net.minecraft.client.renderer.texture.LayeredTexture;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.VillagerRegistry;
import nightkosh.gravestone_extended.block.enums.EnumCorpse;
import nightkosh.gravestone_extended.core.Resources;
import nightkosh.gravestone_extended.helper.GameProfileHelper;
import nightkosh.gravestone_extended.item.corpse.CatCorpseHelper;
import nightkosh.gravestone_extended.item.corpse.HorseCorpseHelper;
import nightkosh.gravestone_extended.item.corpse.VillagerCorpseHelper;
import nightkosh.gravestone_extended.models.block.execution.*;
import org.lwjgl.opengl.GL11;

import java.util.Map;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class CorpseRendererHelper {

    private static final Map horsesTexturesMap = Maps.newHashMap();
    private static final ModelWolf dogModel = new ModelWolf();
    private static final ModelOcelot catModel = new ModelOcelot();
    private static final ModelHorse horseModel = new ModelHorse();
    static {
        dogModel.isChild = false;
        catModel.isChild = false;
    }

    private static final ModelHangedBiped bipedModel = new ModelHangedBiped(false, true);
    private static final ModelHangedBiped zombieModel = new ModelHangedBiped(false, true);
    private static final ModelHangedBiped zombiePigmenModel = new ModelHangedBiped(false, true);
    private static final ModelHangedSkeleton skeletonModel = new ModelHangedSkeleton(false);
    private static final ModelHangedSkeleton witherSkeletonModel = new ModelHangedSkeleton(false, true);
    private static final ModelHangedVillager villagerModel = new ModelHangedVillager(false);
    private static final ModelHangedZombieVillager zombieVillagerModel = new ModelHangedZombieVillager(false);
    private static final ModelHangedWitch witchModel = new ModelHangedWitch(false);

    private static final ModelHangedVillager villagerInStocksModel = new ModelHangedVillager(true);
    private static final ModelHangedZombieVillager zombieVillagerInStocksModel = new ModelHangedZombieVillager(true);
    private static final ModelHangedWitch witchInStocksModel = new ModelHangedWitch(true);

    private static final Entity modelEntity = new Entity(null) {
        @Override
        protected void entityInit() {
        }

        @Override
        protected void readEntityFromNBT(NBTTagCompound tagCompund) {
        }

        @Override
        protected void writeEntityToNBT(NBTTagCompound tagCompound) {
        }
    };

    static {
        zombieModel.isChild = false;
        zombieVillagerModel.isChild = false;
        zombiePigmenModel.isChild = false;
        skeletonModel.isChild = false;
        witherSkeletonModel.isChild = false;
    }

    private static EntityWolf dog;
    private static EntityHorse horse;

    public static void renderCorpse(EnumCorpse corpseType, NBTTagCompound nbt, boolean atAltar) {
        renderCorpse(corpseType, nbt, atAltar, false);
    }

    public static void renderCorpse(EnumCorpse corpseType, NBTTagCompound nbt, boolean atAltar, boolean isExecuted) {
        renderCorpse(corpseType, nbt, atAltar, isExecuted, false);
    }

    public static void renderCorpse(EnumCorpse corpseType, NBTTagCompound nbt, boolean atAltar, boolean isExecuted, boolean isInStocks) {
        if (!isExecuted) {
            GL11.glRotatef(180, 1, 0, 0);
        }

        float xz = 0.0625F;
        switch (corpseType) {
            case STEVE:
                if (atAltar) {
                    GL11.glTranslatef(0, -1.2F, 0);
                } else if (!isExecuted) {
                    GL11.glTranslatef(0, -1.3F, -0.85F);
                }

                GameProfileHelper.bindPlayerTexture(nbt);
                zombieModel.renderAll(isInStocks);
                break;
            case VILLAGER:
                if (atAltar) {
                    GL11.glTranslatef(0, -1.2F, 0);
                } else if (!isExecuted) {
                    GL11.glTranslatef(0, -1.3F, -0.85F);
                }
                int profession = (nbt == null) ? 0 : VillagerCorpseHelper.getVillagerType(nbt);
                bindVillagerTexture(profession);
                if (isInStocks) {
                    villagerInStocksModel.renderAll();
                } else {
                    villagerModel.renderAll();
                }
                break;
            case DOG:
                if (atAltar) {
                    GL11.glTranslatef(0, -1.2F, 0);
                } else if (!isExecuted) {
                    GL11.glTranslatef(0, -2, -0.85F);
                }
                Minecraft.getMinecraft().renderEngine.bindTexture(Resources.WOLF);
                if (dog == null) {
                    dog = new EntityWolf(Minecraft.getMinecraft().theWorld);
                }
                dogModel.setLivingAnimations(dog, 0, 0, 0);
                dogModel.render(null, xz, xz, xz, xz, xz, xz);
                break;
            case CAT:
                if (atAltar) {
                    GL11.glTranslatef(0, -1.2F, 0);
                } else if (!isExecuted) {
                    GL11.glTranslatef(0, -2F, -0.85F);
                }
                int catType = (nbt == null) ? 0 : CatCorpseHelper.getCatType(nbt);
                bindCatTexture(catType);
                catModel.render(null, xz, xz, xz, xz, xz, xz);
                break;
            case HORSE:
                if (atAltar) {
                    GL11.glTranslatef(0, -1.3F, 0);
                } else if (!isExecuted) {
                    GL11.glTranslatef(0, -1.5F, -0.85F);
                }
                if (horse == null) {
                    horse = new EntityHorse(Minecraft.getMinecraft().theWorld);
                }
                horse.setHorseType((nbt == null) ? 0 : HorseCorpseHelper.getHorseType(nbt));
                horse.setHorseVariant((nbt == null) ? 0 : HorseCorpseHelper.getHorseVariant(nbt));

                bindHorseTexture((nbt == null) ? 0 : HorseCorpseHelper.getHorseType(nbt));

                horseModel.setLivingAnimations(horse, 0, 0, 0);
                horseModel.render(horse, xz, xz, xz, xz, xz, xz);
                break;
            case ZOMBIE:
                if (atAltar) {
                    GL11.glTranslatef(0, -1.2F, 0);
                } else if (!isExecuted) {
                    GL11.glTranslatef(0, -1.3F, -0.85F);
                }
                Minecraft.getMinecraft().renderEngine.bindTexture(Resources.ZOMBIE);
                zombieModel.renderAll(isInStocks);
                break;
            case ZOMBIE_VILLAGER:
                if (atAltar) {
                    GL11.glTranslatef(0, -1.2F, 0);
                } else if (!isExecuted) {
                    GL11.glTranslatef(0, -1.3F, -0.85F);
                }
                Minecraft.getMinecraft().renderEngine.bindTexture(Resources.ZOMBIE_VILLAGER);
                if (isInStocks) {
                    zombieVillagerInStocksModel.renderAll();
                } else {
                    zombieVillagerModel.renderAll();
                }
                break;
            case ZOMBIE_PIGMEN:
                if (atAltar) {
                    GL11.glTranslatef(0, -1.2F, 0);
                } else if (!isExecuted) {
                    GL11.glTranslatef(0, -1.3F, -0.85F);
                }
                Minecraft.getMinecraft().renderEngine.bindTexture(Resources.ZOMBIE_PIGMAN);
                zombiePigmenModel.renderAll(isInStocks);
                break;
            case SKELETON:
                if (atAltar) {
                    GL11.glTranslatef(0, -1.2F, 0);
                } else if (!isExecuted) {
                    GL11.glTranslatef(0, -1.3F, -0.85F);
                }
                Minecraft.getMinecraft().renderEngine.bindTexture(Resources.SKELETON);
                skeletonModel.renderAll(isInStocks);
                break;
            case WITHER_SKELETON:
                if (atAltar) {
                    GL11.glTranslatef(0, -1.2F, 0);
                } else if (!isExecuted) {
                    GL11.glTranslatef(0, -1.3F, -0.85F);
                }
                Minecraft.getMinecraft().renderEngine.bindTexture(Resources.WITHER_SKELETON);
                witherSkeletonModel.renderAll(isInStocks);
                break;
            case WITCH:
                if (atAltar) {
                    GL11.glTranslatef(0, -1.2F, 0);
                } else if (!isExecuted) {
                    GL11.glTranslatef(0, -1.3F, -0.85F);
                }
                Minecraft.getMinecraft().renderEngine.bindTexture(Resources.WITCH);
                if (isInStocks) {
                    witchInStocksModel.renderAll();
                } else {
                    witchModel.renderAll();
                }
                break;
        }
    }

    private static void bindVillagerTexture(int profession) {
        switch (profession) {
            case 0:
                Minecraft.getMinecraft().renderEngine.bindTexture(Resources.VILLAGER_FARMER);
                break;
            case 1:
                Minecraft.getMinecraft().renderEngine.bindTexture(Resources.VILLAGER_LIBRARIAN);
                break;
            case 2:
                Minecraft.getMinecraft().renderEngine.bindTexture(Resources.VILLAGER_PRIEST);
                break;
            case 3:
                Minecraft.getMinecraft().renderEngine.bindTexture(Resources.VILLAGER_SMITH);
                break;
            case 4:
                Minecraft.getMinecraft().renderEngine.bindTexture(Resources.VILLAGER_BUTCHER);
                break;
            default:
//                Minecraft.getMinecraft().renderEngine.bindTexture(VillagerRegistry.getVillagerSkin(profession, Resources.VILLAGER));
                Minecraft.getMinecraft().renderEngine.bindTexture(VillagerRegistry.getRegistry().getKey(profession));//.getSkin()
                break;
        }
    }

    private static void bindCatTexture(int catType) {
        switch (catType) {
            case 0:
            default:
                Minecraft.getMinecraft().renderEngine.bindTexture(Resources.OCELOT);
                break;
            case 1:
                Minecraft.getMinecraft().renderEngine.bindTexture(Resources.BLACK_CAT);
                break;
            case 2:
                Minecraft.getMinecraft().renderEngine.bindTexture(Resources.RED_CAT);
                break;
            case 3:
                Minecraft.getMinecraft().renderEngine.bindTexture(Resources.SIAMESE_CAT);
                break;
        }
    }

    private static void bindHorseTexture(int horseType) {
        switch (horseType) {
            case 0:
                String horseTexturePath = horse.getHorseTexture();
                ResourceLocation horseResourceLocation = (ResourceLocation) horsesTexturesMap.get(horseTexturePath);
                if (horseResourceLocation == null) {
                    horseResourceLocation = new ResourceLocation(horseTexturePath);
                    Minecraft.getMinecraft().getTextureManager().loadTexture(horseResourceLocation, new LayeredTexture(horse.getVariantTexturePaths()));
                    horsesTexturesMap.put(horseTexturePath, horseResourceLocation);
                }
                Minecraft.getMinecraft().renderEngine.bindTexture(horseResourceLocation);
                break;
            case 1:
                Minecraft.getMinecraft().renderEngine.bindTexture(Resources.DONKEY);
                break;
            case 2:
                Minecraft.getMinecraft().renderEngine.bindTexture(Resources.MULE);
                break;
            case 3:
                Minecraft.getMinecraft().renderEngine.bindTexture(Resources.ZOMBIE_HORSE);
                break;
            case 4:
                Minecraft.getMinecraft().renderEngine.bindTexture(Resources.SKELETON_HORSE);
                break;
        }
    }
}
