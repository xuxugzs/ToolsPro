package ToolsPro.commands;

import ToolsPro.ToolsPro;
import ToolsPro.util.Message;
import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.utils.TextFormat;

/**
 * Created by Pub4Game on 19.12.2015.
 */
public class HealthCommand extends ToolsProCommand {

    private ToolsPro plugin;

    public HealthCommand(ToolsPro plugin) {
        super("health", Message.CMD_HEALTH_DESCRIPTION, Message.CMD_HEALTH_DESCRIPTION2.toString());
        this.setPermission("toolspro.commands.health");
        this.plugin = plugin;
    }

    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        if (!sender.hasPermission(this.getPermission())) {
            sender.sendMessage(this.getPermissionMessage());
        }else if (args.length != 0) {
            if (sender.hasPermission("toolspro.health.other")) {
                Player p = this.plugin.getServer().getPlayer(args[0]);
                if (p != null) {
                    if (p.getGamemode() != 0) {
                        Message.PLAYER_NOT_SURVIVAL.print(sender, "prefix:&7[&aHealth&7]", 'c', 'b');
                    } else if (p.getHealth() != 20) {
                        p.setHealth(20);
                        Message.CMD_HEALTH_PLAYER.print(sender, "prefix:&7[&aHealth&7]", 'a', 'b', p.getName());
                        Message.CMD_HEALTH_PLAYER_MESSAGE.print(p, "prefix:&7[&aHealth&7]", 'a');
                    } else {
                        sender.sendMessage(TextFormat.colorize("&7[&aHealth&7] &cУ игрока &b" + p.getName() + " &cполные жизни, лечение не требуется!"));
                    }
                } else {
                    Message.UNKNOWN_PLAYER.print(sender, "prefix:&7[&aHealth&7]", 'c');
                }
            } else {
                sender.sendMessage(this.getPermissionMessage());
            }
        } else {
            if (sender instanceof Player) {
                if (((Player) sender).getGamemode() != 0) {
                    Message.YOU_NOT_SURVIVAL.print(sender, "prefix:&7[&aHealth&7]", 'c');
                } else if (((Player) sender).getHealth() != 20) {
                    ((Player) sender).setHealth(20);
                    Message.CMD_HEALTH_SENDER.print(sender, "prefix:&7[&aHealth&7]", 'a');
                } else {
                    Message.CMD_HEALTH_SENDER_MAX.print(sender, "prefix:&7[&aHealth&7]", 'a');
                }
            } else {
                Message.NEED_PLAYER.print(sender, "prefix:&7[&aHealth&7]", 'c');
            }
        }
        return true;
    }
}