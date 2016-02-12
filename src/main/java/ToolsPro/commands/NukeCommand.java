package ToolsPro.commands;

import ToolsPro.ToolsPro;
import ToolsPro.util.Message;
import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;

/**
 * Created by Pub4Game on 19.12.2015.
 */
public class NukeCommand extends Commands {

    private ToolsPro plugin;

    public NukeCommand(ToolsPro plugin) {
        super("nuke", Message.CMD_SPEED_DESCRIPTION, Message.CMD_SPEED_DESCRIPTION2.toString());
        this.setPermission("toolspro.commands.nuke.use");
        this.plugin = plugin;
    }

    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        if (!sender.hasPermission(this.getPermission())) {
            Message.YOU_DONT_HAVE_PERMISSION.print(sender, 'c');
        } else {
            Player p;
            if (args.length != 0){
                p = this.plugin.getServer().getPlayer(args[0]);
                if (p.hasPermission("toolspro.commands.nuke.other")) {
                    if (p == null){
                        return Message.UNKNOWN_PLAYER.print(sender, "prefix:&7[&aNuke&7]", 'c');
                    }
                } else {
                    return Message.YOU_DONT_HAVE_PERMISSION.print(sender, 'c');
                }
            } else {
                p = this.plugin.getServer().getPlayer(sender.getName());
                if (p == null){
                    return Message.NEED_PLAYER.print(sender, "prefix:&7[&aNuke&7]", 'c');
                }
            }
            this.plugin.nuke(p);
        }
        return true;
    }
}
