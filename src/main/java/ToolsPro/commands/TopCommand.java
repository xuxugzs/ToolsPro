package ToolsPro.commands;

import ToolsPro.ToolsPro;
import ToolsPro.util.Message;
import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;

/**
 * Created by Pub4Game on 19.12.2015.
 */
public class TopCommand extends Commands {

    private ToolsPro plugin;

    public TopCommand(ToolsPro plugin) {
        super("top", Message.CMD_TOP_DESCRIPTION, "/top");
        this.setPermission("toolspro.commands.top");
        this.plugin = plugin;
    }

    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        if (!sender.hasPermission(this.getPermission())) {
            Message.YOU_DONT_HAVE_PERMISSION.print(sender, 'c');
        } else {
            if (sender instanceof Player) {
                Message.CMD_TOP_TP_MESSAGE.print(sender, 'a');
                ((Player) sender).teleport(((Player) sender).add(0, ((Player) sender).getLevel().getHighestBlockAt(((Player) sender).getFloorX(), ((Player) sender).getFloorZ()) + 1));
            } else {
                return Message.NEED_PLAYER.print(sender, "prefix:&7[&aTop&7]", 'c');
            }
        }
        return true;
    }
}
