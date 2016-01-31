package ToolsPro.commands;

import ToolsPro.ToolsPro;
import ToolsPro.util.Message;
import cn.nukkit.command.CommandSender;
import cn.nukkit.utils.TextFormat;

/**
 * Created by Pub4Game on 19.12.2015.
 */
public class BroadcastCommand extends ToolsProCommand {

    private ToolsPro plugin;

    public BroadcastCommand(ToolsPro plugin) {
        super("broadcast", Message.CMD_BROADCAST_DESCRIPTION, Message.CMD_BROADCAST_DESCRIPTION2.toString());
        this.setPermission("toolspro.commands.broadcast");
        this.setAliases(new String[]{"bc"});
        this.plugin = plugin;
    }

    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        if (!sender.hasPermission(this.getPermission())) {
            sender.sendMessage(Message.YOU_DONT_HAVE_PERMISSION.getText('c'));
        } else if (args.length != 0) {
            sender.getServer().broadcastMessage(TextFormat.colorize("&d[Broadcast] &r" + this.plugin.join(args)));
        } else {
            return Message.CMD_BROADCAST_USAGE.print(sender, "prefix:&7[&aBroadcast&7]", 'c');
        }
        return true;
    }
}
