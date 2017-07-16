package nightkosh.gravestone_extended.core.commands;

import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import nightkosh.gravestone.api.grave.EnumGraveMaterial;
import nightkosh.gravestone.core.commands.Command;
import nightkosh.gravestone.core.commands.ISubCommand;
import nightkosh.gravestone_extended.block.enums.EnumMemorials;
import nightkosh.gravestone_extended.core.GSBlock;
import nightkosh.gravestone_extended.core.logger.GSLogger;
import nightkosh.gravestone_extended.helper.GameProfileHelper;
import org.apache.commons.lang3.StringUtils;

import static nightkosh.gravestone_extended.block.enums.EnumMemorials.EnumMemorialType;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class SubCommandStatue implements ISubCommand {

    public static final String COMMAND_NAME = "getStatue";
    public static final String COMMAND_USAGE = Command.MAIN_COMMAND_NAME + COMMAND_NAME + " <player name> <material>";

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
        GSLogger.logInfo("Player statue command received");

        if (args.length >= 1) {
            EnumMemorials memorialType = EnumMemorials.STONE_STEVE_STATUE;
            if (StringUtils.isNotBlank(args[2])) {
                try {
                    memorialType = EnumMemorials.getByTypeAndMaterial(EnumMemorialType.STEVE_STATUE, EnumGraveMaterial.valueOf(args[2].toUpperCase()));
                } catch (Exception e) {
                    sender.sendMessage(new TextComponentTranslation("commands.unknown_material").setStyle(new Style().setColor(TextFormatting.RED)));
                }
            }
            GameProfileHelper.dropItem(minecraftServer, sender, args[1], GSBlock.MEMORIAL, memorialType.ordinal());
        } else {
            sender.sendMessage(new TextComponentTranslation("commands.not_enough_parameters").setStyle(new Style().setColor(TextFormatting.RED)));
        }
    }
}
