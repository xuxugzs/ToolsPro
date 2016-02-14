package ToolsPro.commands;

import ToolsPro.ToolsPro;
import ToolsPro.util.Message;
import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;

/**
 * Created by Pub4Game on 6.02.2016.
 */
public class AntiochCommand extends Commands {

    private ToolsPro plugin;

    public AntiochCommand(ToolsPro plugin) {
        super("antioch", Message.CMD_ANTIOCH_DESCRIPTION, "/antioch");
        this.setPermission("toolspro.commands.antioch");
        this.plugin = plugin;
    }

    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        if (!sender.hasPermission(this.getPermission())) {
            Message.YOU_DONT_HAVE_PERMISSION.print(sender, 'c');
        } else {
            if (sender instanceof Player) {
                if (this.plugin.antioch((Player) sender)) {
                    Message.CMD_ANTIOCH_GRENADE.print(sender, "prefix:&7[&aAntioch&7]", 'a');
                } else {
                    Message.CMD_ANTIOCH_GRENADE_ERROR.print(sender, "prefix:&7[&aAntioch&7]", 'c');
                }
            } else {
                return Message.NEED_PLAYER.print(sender, "prefix:&7[&aAntioch&7]", 'c');
            }
        }
        return true;
    }
}
