package ToolsPro.commands;

import ToolsPro.ToolsPro;
import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.utils.TextFormat;

/**
 * Created by Pub4Game on 19.12.2015.
 */
public class FlyCommand extends Command {

    private ToolsPro plugin;

    public FlyCommand(ToolsPro plugin) {
        super("fly", "Включает/выключает полет.", "/fly или /fly <ник>");
        this.setPermission("toolspro.commands.fly");
        this.plugin = plugin;
    }

    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        if (!sender.hasPermission(this.getPermission())) {
            sender.sendMessage(this.getPermissionMessage());
        }else if (args.length != 0) {
            if (sender.hasPermission("toolspro.fly.other")) {
                Player p = this.plugin.getServer().getPlayer(args[0]);
                if (p instanceof Player) {
                    if (p.getGamemode() != 0) {
                        sender.sendMessage(TextFormat.colorize("&7[&aFly&7] &cВаш игровой режим не выживание!"));
                    } else if (p.getAllowFlight()) {
                            p.setAllowFlight(false);
                            sender.sendMessage(TextFormat.colorize("&7[&aFly&7] &aФлай игроку &b" + p.getName() + " &aуспешно выключен!"));
                            p.sendMessage(TextFormat.colorize("&7[&aFly&7] &aВам выключили режим полета!"));
                        } else {
                            p.setAllowFlight(true);
                            sender.sendMessage(TextFormat.colorize("&7[&aFly&7] &aФлай игроку &b" + p.getName() + " &aуспешно включен!"));
                            p.sendMessage(TextFormat.colorize("&7[&aFly&7] &aВам включили режим полета!"));
                        }
                }else{
                    sender.sendMessage(TextFormat.colorize("&7[&aFly&7] &cТакого игрока нет на сервере!"));
                }
            }else{
                sender.sendMessage(this.getPermissionMessage());
            }
        }else if (sender instanceof Player) {
            if (((Player) sender).getGamemode() != 0) {
                sender.sendMessage(TextFormat.colorize("&7[&aFly&7] &cВаш игровой режим не выживание!"));
            }else if (((Player) sender).getAllowFlight()) {
                ((Player) sender).setAllowFlight(false);
                sender.sendMessage(TextFormat.colorize("&7[&aFly&7] &aВы успешно выключили режим полета!"));
            }else{
                ((Player) sender).setAllowFlight(true);
                sender.sendMessage(TextFormat.colorize("&7[&aFly&7] &aВы успешно включили режим полета!"));
            }
        }else{
            sender.sendMessage(TextFormat.colorize("&7[&aFly&7] &cПожалуйста, используйте эту команду в игре!"));
        }
        return true;
    }
}

