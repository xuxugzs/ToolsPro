package ToolsPro.commands;

import ToolsPro.ToolsPro;
import ToolsPro.util.Message;
import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;
import cn.nukkit.utils.Config;
import cn.nukkit.utils.TextFormat;

import java.io.File;

/**
 * Created by Pub4Game on 21.12.2015.
 */
public class UnmuteCommand extends Commands {

    private ToolsPro plugin;

    public UnmuteCommand(ToolsPro plugin) {
        super("unmute", Message.CMD_UNMUTE_DESCRIPTION, Message.CMD_UNMUTE_DESCRIPTION2.toString());
        this.setPermission("toolspro.commands.unmute");
        this.plugin = plugin;
    }

    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        if (!sender.hasPermission(this.getPermission())) {
            sender.sendMessage(Message.YOU_DONT_HAVE_PERMISSION.getText('c'));
        } else {
            if (args.length != 0) {
                Player p = this.plugin.getServer().getPlayer(args[0]);
                if (!this.plugin.existsPlayerMute(args[0])) {
                    Message.CMD_UNMUTE_PLAYER_NOT_MUTED.print(sender, "prefix:&7[&aMute&7]", 'a', 'b', args[0]);
                } else {
                    this.plugin.removePlayerMute(args[0]);
                    if (p instanceof Player) {
                        Message.CMD_UNMUTE_PLAYER_MESSAGE.print(p, "prefix:&7[&aMute&7]", 'c');
                    }
                    Message.CMD_UNMUTE_SENDER.print(sender, "prefix:&7[&aHealth&7]", 'a', 'b', args[0]);
                    this.plugin.info(sender, Message.CMD_UNMUTE_PLAYER_INFO.getText("prefix:&7[Mute]", '7', '7', sender.getName(), args[0]));
                }
            } else {
                return Message.CMD_UNMUTE_USAGE.print(sender, "prefix:&7[&aMute&7]", 'c');
            }
        }
        return true;
    }
}