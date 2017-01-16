package nightkosh.gravestone_extended.tileentity;

import net.minecraft.tileentity.TileEntity;
import nightkosh.gravestone_extended.block.enums.EnumCorpse;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class TileEntityCorpse extends TileEntity {


    public static class Steve extends TileEntityCorpse {
        @Override
        public int getBlockMetadata() {
            return EnumCorpse.STEVE.ordinal();
        }
    }

    public static class Villager extends TileEntityCorpse {
        @Override
        public int getBlockMetadata() {
            return EnumCorpse.VILLAGER.ordinal();
        }
    }

    public static class Dog extends TileEntityCorpse {
        @Override
        public int getBlockMetadata() {
            return EnumCorpse.DOG.ordinal();
        }
    }

    public static class Cat extends TileEntityCorpse {
        @Override
        public int getBlockMetadata() {
            return EnumCorpse.CAT.ordinal();
        }
    }

    public static class Horse extends TileEntityCorpse {
        @Override
        public int getBlockMetadata() {
            return EnumCorpse.HORSE.ordinal();
        }
    }

    public static class Zombie extends TileEntityCorpse {
        @Override
        public int getBlockMetadata() {
            return EnumCorpse.ZOMBIE.ordinal();
        }
    }

    public static class ZombieVillager extends TileEntityCorpse {
        @Override
        public int getBlockMetadata() {
            return EnumCorpse.ZOMBIE_VILLAGER.ordinal();
        }
    }

    public static class ZombiePigmen extends TileEntityCorpse {
        @Override
        public int getBlockMetadata() {
            return EnumCorpse.ZOMBIE_PIGMEN.ordinal();
        }
    }

    public static class Skeleton extends TileEntityCorpse {
        @Override
        public int getBlockMetadata() {
            return EnumCorpse.SKELETON.ordinal();
        }
    }

    public static class WitherSkeleton extends TileEntityCorpse {
        @Override
        public int getBlockMetadata() {
            return EnumCorpse.WITHER_SKELETON.ordinal();
        }
    }

    public static class Witch extends TileEntityCorpse {
        @Override
        public int getBlockMetadata() {
            return EnumCorpse.WITCH.ordinal();
        }
    }
}