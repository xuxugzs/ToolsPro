package ToolsPro.commands;

import ToolsPro.ToolsPro;
import ToolsPro.util.Message;
import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;

/**
 * Created by Pub4Game on 19.12.2015.
 */
public class FlyCommand extends ToolsProCommand {

    private ToolsPro plugin;

    public FlyCommand(ToolsPro plugin) {
        super("fly", Message.CMD_FLY_DESCRIPTION, Message.CMD_FLY_DESCRIPTION2.toString());
        this.setPermission("toolspro.commands.fly");
        this.plugin = plugin;
    }

    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        if (!sender.hasPermission(this.getPermission())) {
            sender.sendMessage(this.getPermissionMessage());
        } else if (args.length != 0) {
            if (sender.hasPermission("toolspro.fly.other")) {
                Player p = this.plugin.getServer().getPlayer(args[0]);
                if (p != null) {
                    if (p.getGamemode() != 0) {
                        Message.PLAYER_NOT_SURVIVAL.print(sender, "prefix:&7[&aFly&7]", 'c', 'b');
                    } else if (p.getAllowFlight()) {
                        p.setAllowFlight(false);
                        Message.CMD_FLY_PLAYER_DISABLED.print(sender, "prefix:&7[&aFly&7]", 'a', 'b', p.getName());
                        Message.CMD_FLY_PLAYER_MESSAGE_DISABLED.print(sender, "prefix:&7[&aFly&7]", 'a');
                    } else {
                        p.setAllowFlight(true);
                        Message.CMD_FLY_PLAYER_ENABLED.print(sender, "prefix:&7[&aFly&7]", 'a', 'b', p.getName());
                        Message.CMD_FLY_PLAYER_MESSAGE_ENABLED.print(sender, "prefix:&7[&aFly&7]", 'a');
                    }
                } else {
                    Message.NEED_PLAYER.print(sender,"prefix:&7[&aFly&7]",'c');
                }
            } else {
                sender.sendMessage(this.getPermissionMessage());
            }
        }else if (sender instanceof Player) {
            if (((Player) sender).getGamemode() != 0) {
                Message.YOU_NOT_SURVIVAL.print(sender, "prefix:&7[&aFly&7]", 'c');
            } else if (((Player) sender).getAllowFlight()) {
                ((Player) sender).setAllowFlight(false);
                Message.CMD_FLY_SENDER_DISABLED.print(sender, "prefix:&7[&aFly&7]", 'a');
            } else {
                ((Player) sender).setAllowFlight(true);
                Message.CMD_FLY_SENDER_ENABLED.print(sender, "prefix:&7[&aFly&7]", 'a');
            }
        } else {
            Message.NEED_PLAYER.print(sender, "prefix:&7[&aFly&7]", 'c');
        }
        return true;
    }
}

