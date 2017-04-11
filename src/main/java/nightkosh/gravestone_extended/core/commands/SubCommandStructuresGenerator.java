package nightkosh.gravestone_extended.core.commands;

import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import nightkosh.gravestone.core.commands.Command;
import nightkosh.gravestone.core.commands.ISubCommand;
import nightkosh.gravestone_extended.core.logger.GSLogger;
import nightkosh.gravestone_extended.structures.GSStructureGenerator;
import nightkosh.gravestone_extended.structures.catacombs.CatacombsGenerator;
import nightkosh.gravestone_extended.structures.graves.OpenedGraveGenerator;
import nightkosh.gravestone_extended.structures.graves.SingleGraveGenerator;
import nightkosh.gravestone_extended.structures.memorials.MemorialGenerator;
import nightkosh.gravestone_extended.structures.village.undertaker.VillageCemeteryGenerator;
import nightkosh.gravestone_extended.structures.village.undertaker.VillageUndertakerGenerator;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class SubCommandStructuresGenerator implements ISubCommand {

    public static final String COMMAND_NAME = "generate";
    public static final String COMMAND_USAGE = Command.MAIN_COMMAND_NAME + COMMAND_NAME + " <structure name> <x coordinate> <z coordinate> <direction>";


    @Override
    public String getCommandName() {
        return COMMAND_NAME;
    }

    @Override
    public String getCommandUsage() {
        return COMMAND_USAGE;
    }

    @Override
    public void execute(MinecraftServer minecraftServer, ICommandSender sender, String[] args) throws CommandException {
        GSLogger.logInfo("Structure generation command received");

        if (args.length >= 2) {
            String structureName = args[1];
            int x = sender.getPosition().getX();
            int z = sender.getPosition().getZ();
            EnumFacing facing = EnumFacing.NORTH;
            try {
                if (args.length >= 3) {
                    x = Integer.parseInt(args[2]);
                    if (args.length >= 4) {
                        z = Integer.parseInt(args[3]);
                        if (args.length >= 5) {
                            facing = EnumFacing.byName(args[4]);
                            if (facing == null) {
                                sender.addChatMessage(new TextComponentTranslation("commands.direction_error").setStyle(new Style().setColor(TextFormatting.RED)));
                                return;
                            }
                        }
                    }
                }
            } catch (NumberFormatException e) {
                sender.addChatMessage(new TextComponentTranslation("commands.coordinate_error").setStyle(new Style().setColor(TextFormatting.RED)));
                return;
            }

            switch (structureName) {
                case "catacombs":
                    generateStructure(sender, sender.getEntityWorld(), x, z, facing, CatacombsGenerator.getInstance());
                    break;
                case "memorial":
                    generateStructure(sender, sender.getEntityWorld(), x, z, facing, MemorialGenerator.getInstance());
                    break;
                case "grave":
                    generateStructure(sender, sender.getEntityWorld(), x, z, facing, SingleGraveGenerator.getInstance());
                    break;
                case "opened_grave":
                    generateStructure(sender, sender.getEntityWorld(), x, z, facing, OpenedGraveGenerator.getInstance());
                    break;
                case "cemetery":
                    generateStructure(sender, sender.getEntityWorld(), x, z, facing, VillageCemeteryGenerator.getInstance());
                    break;
                case "undertaker":
                    generateStructure(sender, sender.getEntityWorld(), x, z, facing, VillageUndertakerGenerator.getInstance());
                    break;
                default:
                    sender.addChatMessage(new TextComponentTranslation("commands.generate.unknown_structure").setStyle(new Style().setColor(TextFormatting.RED)));
                    break;
            }
        } else {
            sender.addChatMessage(new TextComponentTranslation("commands.not_enough_parameters").setStyle(new Style().setColor(TextFormatting.RED)));
        }

    }

    private static void generateStructure(ICommandSender sender, World world, int x, int z, EnumFacing facing, GSStructureGenerator structure) {
        structure.generate(world, world.rand, x, z, facing, 0, true);
    }
}
