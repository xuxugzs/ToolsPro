package ToolsPro.commands;

import ToolsPro.ToolsPro;
import ToolsPro.util.Message;
import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;
import cn.nukkit.utils.TextFormat;

/**
 * Created by Pub4Game on 19.12.2015.
 */
public class GMCommand extends ToolsProCommand {

    private ToolsPro plugin;

    public GMCommand(ToolsPro plugin) {
        super("gm", Message.CMD_GAMEMODE_DESCRIPTION, Message.CMD_GAMEMODE_DESCRIPTION2.toString());
        this.setPermission("toolspro.commands.gamemode");
        this.plugin = plugin;
    }

    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        if (!sender.hasPermission(this.getPermission())) {
            sender.sendMessage(this.getPermissionMessage());
        }else if (sender instanceof Player) {
            if (args.length != 0) {
                switch (args[0]) {
                    case "0":
                        if (((Player) sender).getGamemode() == 0) {
                            sender.sendMessage(TextFormat.colorize("&7[&aGM&7] &cВы уже в выживании!"));
                            return false;
                        }
                        this.plugin.info(sender, "&7[GM] " + sender.getName() + " изменил свой игровой режим на выживание!");
                        sender.sendMessage(TextFormat.colorize("&7[&aGM&7] &aВы успешно изменили игровой режим на выживание!"));
                        ((Player) sender).setGamemode(0);
                        return true;
                    case "1":
                        if (((Player) sender).getGamemode() == 1) {
                            sender.sendMessage(TextFormat.colorize("&7[&aGM&7] &cВы уже в креативе!"));
                            return false;
                        }
                        this.plugin.info(sender, "&7[GM] " + sender.getName() + " изменил свой игровой режим на креатив!");
                        sender.sendMessage(TextFormat.colorize("&7[&aGM&7] &aВы успешно изменили игровой режим на креатив!"));
                        ((Player) sender).setGamemode(1);
                        return true;
                    case "2":
                        if (sender.hasPermission("toolspro.gamemode.other")) {
                            if (((Player) sender).getGamemode() == 2) {
                                sender.sendMessage(TextFormat.colorize("&7[&aGM&7] &cВы уже в режиме приключения!"));
                                return false;
                            }
                            this.plugin.info(sender, "&7[GM] " + sender.getName() + " изменил свой игровой режим на режим приключений!");
                            sender.sendMessage(TextFormat.colorize("&7[&aGM&7] &aВы успешно изменили игровой режим на режим приключения!"));
                            ((Player) sender).setGamemode(2);
                        } else {
                            sender.sendMessage(this.getPermissionMessage());
                        }
                        return true;
                    case "3":
                        if (sender.hasPermission("toolspro.gamemode.other")) {
                            if (((Player) sender).getGamemode() == 3) {
                                sender.sendMessage(TextFormat.colorize("&7[&aGM&7] &cВы уже в режиме наблюдения!"));
                                return false;
                            }
                            this.plugin.info(sender, "&7[GM] " + sender.getName() + " изменил свой игровой режим на режим наблюдения!");
                            sender.sendMessage(TextFormat.colorize("&7[&aGM&7] &aВы успешно изменили игровой режим на режим наблюдения!"));
                            ((Player) sender).setGamemode(3);
                        } else {
                            sender.sendMessage(this.getPermissionMessage());
                        }
                        return true;
                    case "help":
                        sender.sendMessage(TextFormat.colorize("&7[&aGM&7] &9Список игровых режимов&7:"));
                        sender.sendMessage(TextFormat.colorize("&e/gm 0 &7- &aИзменяет ваш игровой режим на выживание"));
                        sender.sendMessage(TextFormat.colorize("&e/gm 1 &7- &aИзменяет ваш игровой режим на креатив"));
                        if (sender.hasPermission("toolspro.gamemode.other")) {
                            sender.sendMessage(TextFormat.colorize("&e/gm 2 &7- &aИзменяет ваш игровой режим на режим приключений"));
                            sender.sendMessage(TextFormat.colorize("&e/gm 3 &7- &aИзменяет ваш игровой режим на режим наблюдения"));
                        }
                        return true;
                    default:
                        sender.sendMessage(TextFormat.colorize("&7[&aGM&7] Пожалуйста, используйте &e/gm help &aдля просмотра всех игровых режимов"));
                        return true;
                }
            }else{
                sender.sendMessage(TextFormat.colorize("&7[&aGM&7] Пожалуйста, используйте &e/gm help &aдля просмотра всех игровых режимов"));
            }
        }else{
            Message.NEED_PLAYER.print(sender, "prefix:&7[&aGM&7]", 'c');
        }
        return true;
    }
}
