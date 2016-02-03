package ToolsPro.commands;

import ToolsPro.ToolsPro;
import ToolsPro.util.Message;
import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;

/**
 * Created by Pub4Game on 19.12.2015.
 */
public class SetSpawnCommand extends ToolsProCommand {

    private ToolsPro plugin;

    public SetSpawnCommand(ToolsPro plugin) {
        super("setspawn", Message.CMD_SETSPAWN_DESCRIPTION, "/setspawn");
        this.setPermission("toolspro.commands.setspawn");
        this.plugin = plugin;
    }

    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        if (!sender.hasPermission(this.getPermission())) {
            sender.sendMessage(Message.YOU_DONT_HAVE_PERMISSION.getText('c'));
        } else {
            if (sender instanceof Player) {
                ((Player) sender).getLevel().setSpawnLocation(((Player) sender));
                ((Player) sender).getServer().setDefaultLevel(((Player) sender).getLevel());
                Message.CMD_SETSPAWN.print(sender, "prefix:&7[&aSpawn&7]", 'a');
                Message.CMD_SETSPAWN_LOG.log(((Player) sender).getName(),((Player) sender).getLocation());
            } else {
                return Message.NEED_PLAYER.print(sender, "prefix:&7[&aSpawn&7]", 'c');
            }
        }
        return true;
    }
}
