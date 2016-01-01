package ToolsPro.commands;

import ToolsPro.ToolsPro;
import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.utils.TextFormat;

/**
 * Created by Pub4Game on 19.12.2015.
 */
public class HealthCommand extends Command {

    private ToolsPro plugin;

    public HealthCommand(ToolsPro plugin) {
        super("health", "Восстанавливает жизни.", "/health или /health <ник>");
        this.setPermission("toolspro.commands.health");
        this.plugin = plugin;
    }

    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        if (!sender.hasPermission(this.getPermission())) {
            sender.sendMessage(this.getPermissionMessage());
        }else if (args.length != 0) {
            if (sender.hasPermission("toolspro.health.other")) {
                Player p = this.plugin.getServer().getPlayer(args[0]);
                if (p instanceof Player) {
                    if (p.getGamemode() != 0) {
                        sender.sendMessage(TextFormat.colorize("&7[&aHealth&7] &cИгровой режим игрока &b" + p.getName() + "&c не выживание!"));
                    } else if (p.getHealth() != 20) {
                        p.setHealth(20);
                        sender.sendMessage(TextFormat.colorize("&7[&aHealth&7] &aВы вылечили игрока &b" + p.getName() + "&a!"));
                        p.sendMessage(TextFormat.colorize("&7[&aHealth&7] &aВас успешно вылечили!"));
                    }else{
                        sender.sendMessage(TextFormat.colorize("&7[&aHealth&7] &cУ игрока &b" + p.getName() + " &cполные жизни, лечение не требуется!"));
                    }
                } else {
                    sender.sendMessage(TextFormat.colorize("&7[&aHealth&7] &cТакого игрока нет на сервере!"));
                }
            } else {
                sender.sendMessage(this.getPermissionMessage());
            }
        } else {
            if (sender instanceof Player) {
                if (((Player) sender).getGamemode() != 0) {
                    sender.sendMessage(TextFormat.colorize("&7[&aFly&7] &cВаш игровой режим не выживание!"));
                }else if (((Player) sender).getHealth() != 20) {
                    ((Player) sender).setHealth(20);
                    sender.sendMessage(TextFormat.colorize("&7[&aHealth&7] &aВы успешно вылечили себя!"));
                }else{
                    sender.sendMessage(TextFormat.colorize("&7[&aHealth&7] &cУ Вас полные жизни, лечение не требуется!"));
                }
            } else {
                sender.sendMessage(TextFormat.colorize("&cПожалуйста, используйте эту команду только в игре!"));
            }
        }
        return true;
    }
}