package nightkosh.gravestone_extended.structures.catacombs;

import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureComponent;
import nightkosh.gravestone_extended.config.ExtendedConfig;
import nightkosh.gravestone_extended.core.GSBlock;
import nightkosh.gravestone_extended.core.logger.GSLogger;
import nightkosh.gravestone_extended.structures.catacombs.components.CatacombsBaseComponent;
import nightkosh.gravestone_extended.structures.catacombs.components.Stairs;
import nightkosh.gravestone_extended.structures.catacombs.components.Treasury;
import nightkosh.gravestone_extended.structures.catacombs.components.WitherHall;

import java.util.*;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class CatacombsLevel {

    private static final StructureComponent.BlockSelector catacombsStoneBlocks = new CatacombsStonesBlocks();
    private static final StructureComponent.BlockSelector catacombsBoneBlocks = new CatacombsBoneBlocks();
    private final int level;
    private Random random;
    private World world;
    private int totalComponentsCount;
    private int componentsCount;
    public static final int DEFAULT_MIN_ROOMS_COUNT_AT_1_LEVEL = 30;
    public static final int DEFAULT_MAX_ROOMS_COUNT_AT_1_LEVEL = 60;
    public static final int DEFAULT_MIN_ROOMS_COUNT_AT_2_LEVEL = 60;
    public static final int DEFAULT_MAX_ROOMS_COUNT_AT_2_LEVEL = 120;
    public static final int DEFAULT_MIN_ROOMS_COUNT_AT_3_LEVEL = 90;
    public static final int DEFAULT_MAX_ROOMS_COUNT_AT_3_LEVEL = 180;
    public static final int DEFAULT_MIN_ROOMS_COUNT_AT_4_LEVEL = 160;
    public static final int DEFAULT_MAX_ROOMS_COUNT_AT_4_LEVEL = 320;
    private List<CatacombsBaseComponent> levelComponents = new ArrayList<>();
    private List<CatacombsBaseComponent> endComponents = new ArrayList<>();

    public CatacombsLevel(LinkedList<CatacombsBaseComponent> startComponents, int level, World world, Random random) {
        levelComponents = startComponents;
        this.random = random;
        this.world = world;
        this.level = level;
        GSLogger.logInfo("Catacombs generation - start generation of " + this.level + " level");

        switch (this.level) {
            case 1:
                totalComponentsCount = ExtendedConfig.catacombsMinRoomsCountAt1Level + random.nextInt(ExtendedConfig.catacombsMaxRoomsCountAt1Level - ExtendedConfig.catacombsMinRoomsCountAt1Level);
                break;
            case 2:
                totalComponentsCount = ExtendedConfig.catacombsMinRoomsCountAt2Level + random.nextInt(ExtendedConfig.catacombsMaxRoomsCountAt2Level - ExtendedConfig.catacombsMinRoomsCountAt2Level);
                break;
            case 3:
                totalComponentsCount = ExtendedConfig.catacombsMinRoomsCountAt3Level + random.nextInt(ExtendedConfig.catacombsMaxRoomsCountAt3Level - ExtendedConfig.catacombsMinRoomsCountAt3Level);
                break;
            case 4:
                totalComponentsCount = ExtendedConfig.catacombsMinRoomsCountAt4Level + random.nextInt(ExtendedConfig.catacombsMaxRoomsCountAt4Level - ExtendedConfig.catacombsMinRoomsCountAt4Level);
                break;
        }

        componentsCount = 0;
        prepareLevel(levelComponents);
        generateLevel();

        GSLogger.logInfo("Catacombs generation - " + this.level + " level was successfully generated");
    }

    /**
     * Return StructureGSCemeteryCatacombsStones instance
     */
    public static StructureComponent.BlockSelector getCatacombsStones(int level) {
        return (level < 3) ? catacombsStoneBlocks : catacombsBoneBlocks;
    }

    public static net.minecraft.block.Block getCatacombsStairsByLevelId(int level) {
        return (level < 3) ? Blocks.stone_brick_stairs : GSBlock.boneStairs;
    }

    /*
     * Prepare Level pieces
     */
    public final void prepareLevel(List<CatacombsBaseComponent> currentComponents) {
        List<CatacombsBaseComponent> newComponents = new ArrayList<>();
        CatacombsBaseComponent[] components = new CatacombsBaseComponent[0];
        components = currentComponents.toArray(components);
        int resultComponentsCount = 0;

        if (totalComponentsCount > componentsCount) {
            for (CatacombsBaseComponent component : components) {
                for (CatacombsBaseComponent.Exit exit : component.getExitList()) {
                    EnumFacing direction;
                    switch (exit.getSide()) {
                        default:
                        case FRONT:
                            direction = component.getDirection();
                            break;
                        case LEFT:
                            direction = component.getLeftDirection();
                            break;
                        case RIGHT:
                            direction = component.getRightDirection();
                            break;
                    }
                    resultComponentsCount += addComponent(newComponents, component, direction, exit, exit.getSide());
                }
            }
        } else if (endComponents.isEmpty()) {
            createEnd(currentComponents, level);
        }

        componentsCount += resultComponentsCount;

        if (resultComponentsCount != 0) {
            prepareLevel(newComponents);
        }
    }

    /*
     * Create and add new component if it available
     */
    private int addComponent(List<CatacombsBaseComponent> newComponents, CatacombsBaseComponent component, EnumFacing direction, CatacombsBaseComponent.Exit exit, CatacombsBaseComponent.ComponentSide componentSide) {
        CatacombsBaseComponent newComponent = tryCreateComponent(component, direction, exit, componentSide);

        if (newComponent != null) {
            newComponents.add(newComponent);
            return 1;
        } else {
            return 0;
        }
    }

    /*
     * Create level end components
     */
    private void createEnd(List<CatacombsBaseComponent> currentComponents, int level) {
        List<CatacombsBaseComponent> components = currentComponents;
        CatacombsBaseComponent component, newComponent;
        Class componentClass;
        int roomsCount = components.size();
        int ends = (roomsCount < 2) ? 1 : 1 + random.nextInt(components.size() - 1);
        int endsCount = 0;

        if (level == 4) {
            componentClass = WitherHall.class;
            ends = 1;
        } else {
            componentClass = Stairs.class;
        }

        for (int i = 0; i < ends; i++) {
            if (endsCount < ends || components.size() > 0) {
                int j = random.nextInt(components.size());
                component = components.get(j);
                newComponent = tryCreateComponent(component, componentClass, component.getDirection(), level, CatacombsBaseComponent.ComponentSide.FRONT);//TODO

                if (newComponent == null) {
                    newComponent = tryCreateComponent(component, componentClass, component.getLeftDirection(), level, CatacombsBaseComponent.ComponentSide.LEFT);//TODO

                    if (newComponent == null) {
                        newComponent = tryCreateComponent(component, componentClass, component.getRightDirection(), level, CatacombsBaseComponent.ComponentSide.RIGHT);//TODO

                        if (newComponent != null) {
                            levelComponents.add(newComponent);
                            endComponents.add(newComponent);
                            endsCount++;
                            components.remove(j);
                        } else {
                            components.remove(j);
                        }
                    } else {
                        levelComponents.add(newComponent);
                        endComponents.add(newComponent);
                        endsCount++;
                        components.remove(j);
                    }
                } else {
                    levelComponents.add(newComponent);
                    endComponents.add(newComponent);
                    endsCount++;
                    components.remove(j);
                }
            } else {
                break;
            }
        }

        if (endsCount == 0) {
            component = currentComponents.get(random.nextInt(components.size()));
            newComponent = CatacombsComponentsFactory.createComponent(component, random, component.getDirection(), level, componentClass, CatacombsBaseComponent.ComponentSide.FRONT);//TODO
            levelComponents.add(newComponent);
            endComponents.add(newComponent);
        }
    }

    private CatacombsBaseComponent tryCreateComponent(CatacombsBaseComponent component, Class componentClass, EnumFacing direction, int level, CatacombsBaseComponent.Exit exit) {
        if (componentsCount < 30 && componentClass == Treasury.class) {
            componentClass = CatacombsComponentsFactory.getCorridorType(random);
        }

        CatacombsBaseComponent newComponent = CatacombsComponentsFactory.createComponent(component, random, direction, level, componentClass, exit);

        if (canBePlaced(newComponent)) {
            levelComponents.add(newComponent);
            return newComponent;
        } else {
            return null;
        }
    }

    private CatacombsBaseComponent tryCreateComponent(CatacombsBaseComponent component, EnumFacing direction, CatacombsBaseComponent.Exit exit, CatacombsBaseComponent.ComponentSide componentSide) {
        return tryCreateComponent(component, CatacombsComponentsFactory.getNextComponent(component.getClass(), componentSide, random, level), direction, level, exit);
    }

    /**
     * Check is this place availiable for this component
     */
    private boolean canBePlaced(CatacombsBaseComponent component) {
        Iterator<CatacombsBaseComponent> it = levelComponents.iterator();
        CatacombsBaseComponent xz;

        while (it.hasNext()) {
            xz = it.next();

            if (component.canBePlacedHere(xz.getBoundingBox())) {
                return false;
            }
        }

        return true;
    }

    /**
     * Generate level
     */
    public final void generateLevel() {
        CatacombsBaseComponent component;
        Iterator<CatacombsBaseComponent> it = levelComponents.iterator();

        while (it.hasNext()) {
            component = it.next();
            component.addComponentParts(world, random);
        }
    }

    /**
     * Return end parts of level
     */
    public List<CatacombsBaseComponent> getEndParts() {
        return endComponents;
    }
}
