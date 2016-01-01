package ToolsPro.commands;

import ToolsPro.ToolsPro;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.utils.TextFormat;

/**
 * Created by Pub4Game on 19.12.2015.
 */
public class BroadcastCommand extends Command {

    private ToolsPro plugin;

    public BroadcastCommand(ToolsPro plugin) {
        super("broadcast", "Отправляет сообщение в чат.", "/broadcast <сообщение>");
        this.setPermission("toolspro.commands.broadcast");
        this.setAliases(new String[]{"bc"});
        this.plugin = plugin;
    }

    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        if (!sender.hasPermission(this.getPermission())) {
            sender.sendMessage(this.getPermissionMessage());
        }else if (args.length != 0) {
            sender.getServer().broadcastMessage(TextFormat.colorize("&d[Broadcast] &r" + this.plugin.join (args)));
        }else{
            sender.sendMessage(TextFormat.colorize("&7[&aBroadcast&7] &cИспользуйте: /broadcast <текст>"));
        }
        return true;
    }
}
