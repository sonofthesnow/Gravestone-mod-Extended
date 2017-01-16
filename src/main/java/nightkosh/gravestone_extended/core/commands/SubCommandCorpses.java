package nightkosh.gravestone_extended.core.commands;

import com.mojang.authlib.Agent;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.ProfileLookupCallback;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.EnumChatFormatting;
import nightkosh.gravestone.core.commands.Command;
import nightkosh.gravestone.core.commands.ISubCommand;
import nightkosh.gravestone.inventory.GraveInventory;
import nightkosh.gravestone_extended.core.logger.GSLogger;
import nightkosh.gravestone_extended.item.corpse.CorpseHelper;
import org.apache.commons.lang3.StringUtils;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class SubCommandCorpses implements ISubCommand {

    public static final String COMMAND_NAME = "getCorpse";
    public static final String COMMAND_USAGE = Command.MAIN_COMMAND_NAME + COMMAND_NAME + " <player name> ";

    @Override
    public String getCommandName() {
        return COMMAND_NAME;
    }

    @Override
    public String getCommandUsage() {
        return COMMAND_USAGE;
    }

    @Override
    public void execute(ICommandSender sender, String[] args) throws CommandException {
        GSLogger.logInfo("Corpse generation command received");

        if (args.length >= 1) {
            String playerName = args[1];
            if (StringUtils.isNotBlank(playerName)) {
                GameProfile profile = MinecraftServer.getServer().getPlayerProfileCache().getGameProfileForUsername(playerName);
                if (profile == null) {
                    String[] profArr = new String[1];
                    profArr[0] = playerName;
                    MinecraftServer.getServer().getGameProfileRepository().findProfilesByNames(profArr, Agent.MINECRAFT, new ProfileLookupCallback() {

                        @Override
                        public void onProfileLookupSucceeded(GameProfile profile) {
                            GraveInventory.dropItem(CorpseHelper.getDefaultPlayerCorpse(profile), sender.getEntityWorld(), sender.getPosition());
                        }

                        @Override
                        public void onProfileLookupFailed(GameProfile gameProfile, Exception e) {
                            sender.addChatMessage(new ChatComponentTranslation("commands.corpse_search_failed").setChatStyle(new ChatStyle().setColor(EnumChatFormatting.RED)));
                        }
                    });
                } else {
                    GraveInventory.dropItem(CorpseHelper.getDefaultPlayerCorpse(profile), sender.getEntityWorld(), sender.getPosition());
                }
            }
        } else {
            sender.addChatMessage(new ChatComponentTranslation("commands.not_enough_parameters").setChatStyle(new ChatStyle().setColor(EnumChatFormatting.RED)));
        }

    }
}
