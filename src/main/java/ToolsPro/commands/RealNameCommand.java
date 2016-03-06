package ToolsPro.commands;

import ToolsPro.ToolsPro;
import ToolsPro.util.Message;
import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;

/**
 * Created by Pub4Game on 07.02.2016.
 */
public class RealNameCommand extends Commands {

    private ToolsPro plugin;

    public RealNameCommand(ToolsPro plugin) {
        super("realname", Message.CMD_REALNAME_DESCRIPTION, Message.CMD_REALNAME_DESCRIPTION2.toString());
        this.setPermission("toolspro.commands.realname");
        this.plugin = plugin;
    }

    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        if (!sender.hasPermission(this.getPermission())) {
            Message.YOU_DONT_HAVE_PERMISSION.print(sender, 'c');
        } else {
            if (args.length > 0) {
                Player p = this.plugin.getServer().getPlayer(args[0]);
                if (p == null) {
                    p = this.plugin.sortedListPlayers(args[0]);
                    if (p == null) {
                        Message.UNKNOWN_PLAYER.print(sender, "prefix:&7[&aRealName&7]", 'c');
                        return true;
                    }
                }
                Message.CMD_REALNAME.print(sender, "prefix:&7[&aRealName&7]", 'a', 'b', p.getDisplayName().endsWith("s") ? p.getDisplayName().substring(0, p.getDisplayName().length() - 1) + "'s" : p.getDisplayName() + "'s", p.getName());
            } else {
                return Message.CMD_REALNAME_USAGE.print(sender, "prefix:&7[&aRealName&7]", 'c');
            }
        }
        return true;
    }
}
