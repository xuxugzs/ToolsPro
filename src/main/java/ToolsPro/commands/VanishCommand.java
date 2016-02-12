package ToolsPro.commands;

import ToolsPro.ToolsPro;
import ToolsPro.util.Message;
import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;

/**
 * Created by Pub4Game on 19.12.2015.
 */
public class VanishCommand extends Commands {

    private ToolsPro plugin;

    public VanishCommand(ToolsPro plugin) {
        super("vanish", Message.CMD_VANISH_DESCRIPTION, Message.CMD_VANISH_DESCRIPTION2.toString());
        this.setPermission("toolspro.commands.vanish.use");
        this.setAliases(new String[]{"v"});
        this.plugin = plugin;
    }

    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        if (!sender.hasPermission(this.getPermission())) {
            Message.YOU_DONT_HAVE_PERMISSION.print(sender, 'c');
        } else {
            if (args.length != 0) {
                if (sender.hasPermission("toolspro.commands.vanish.other")) {
                    Player p = this.plugin.getServer().getPlayer(args[0]);
                    if (p != null) {
                        if (this.plugin.getPlayerVanish(p)) {
                            this.plugin.removePlayerVanish(p);
                            Message.CMD_VANISH_PLAYER_DISABLE.print(sender, "prefix:&7[&aVanish&7]", 'a', 'b', p.getName());
                            Message.CMD_VANISH_PLAYER_DISABLE_MESSAGE.print(p, "prefix:&7[&aVanish&7]", 'a');
                            this.plugin.info(sender, Message.CMD_VANISH_PLAYER_DISABLE_INFO.getText("prefix:&7[Vanish]", '7', '7', sender.getName(), p.getName()));
                        } else {
                            this.plugin.setPlayerVanish(p);
                            Message.CMD_VANISH_PLAYER_ENABLE.print(sender, "prefix:&7[&aVanish&7]", 'a', 'b', p.getName());
                            Message.CMD_VANISH_PLAYER_ENABLE_MESSAGE.print(p, "prefix:&7[&aVanish&7]", 'a');
                            this.plugin.info(sender, Message.CMD_VANISH_PLAYER_ENABLE_INFO.getText("prefix:&7[Vanish]", '7', '7', sender.getName(), p.getName()));
                        }
                    } else {
                        Message.UNKNOWN_PLAYER.print(sender, "prefix:&7[&aVanish&7]", 'c');
                    }
                } else {
                    return Message.YOU_DONT_HAVE_PERMISSION.print(sender, 'c');
                }
            } else {
                if (sender instanceof Player) {
                    if (this.plugin.getPlayerVanish((Player) sender)) {
                        this.plugin.removePlayerVanish((Player) sender);
                        Message.CMD_VANISH_SENDER_DISABLE.print(sender, "prefix:&7[&aVanish&7]", 'a');
                        this.plugin.info(sender, Message.CMD_VANISH_SENDER_DISABLE_INFO.getText("prefix:&7[Vanish]", '7', '7', sender.getName()));
                    } else {
                        this.plugin.setPlayerVanish((Player) sender);
                        Message.CMD_VANISH_SENDER_ENABLE.print(sender, "prefix:&7[&aVanish&7]", 'a');
                        this.plugin.info(sender, Message.CMD_VANISH_SENDER_ENABLE_INFO.getText("prefix:&7[Vanish]", '7', '7', sender.getName()));
                    }
                } else {
                   return Message.NEED_PLAYER.print(sender, "prefix:&7[&aVanish&7]", 'c');
                }
            }
        }
        return true;
    }
}

