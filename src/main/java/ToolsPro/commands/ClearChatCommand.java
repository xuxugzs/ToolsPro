package ToolsPro.commands;

import ToolsPro.ToolsPro;
import ToolsPro.util.Message;
import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;
import cn.nukkit.utils.TextFormat;

/**
 * Created by Pub4Game on 14.02.2016.
 */
public class ClearChatCommand extends Commands {

    private ToolsPro plugin;

    public ClearChatCommand(ToolsPro plugin) {
        super("clearchat", Message.CMD_CLEARCHAT_DESCRIPTION, Message.CMD_CLEARCHAT_DESCRIPTION2.toString());
        this.setPermission("toolspro.commands.clearchat");
        this.setAliases(new String[]{"cc", "cls", "clearscreen"});
        this.plugin = plugin;
    }

    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        if (!sender.hasPermission(this.getPermission())) {
            Message.YOU_DONT_HAVE_PERMISSION.print(sender, 'c');
        } else {
            if (sender instanceof Player) {
                for (int i = 0; i < 25; i++) sender.sendMessage(" ");
                if (args.length > 0) {
                    String msg = "";
                    for (int i = 0; i < args.length; i++) {
                        msg += args[i] + " ";
                    }
                    sender.sendMessage(TextFormat.colorize(msg));
                }
            } else {
                return Message.NEED_PLAYER.print(sender, "prefix:&7[&aClearChat&7]", 'c');
            }
        }
        return true;
    }
}
