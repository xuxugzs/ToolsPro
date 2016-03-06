package ToolsPro.commands;

import ToolsPro.ToolsPro;
import ToolsPro.util.Message;
import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;

/**
 * Created by Pub4Game on 19.12.2015.
 */
public class GodCommand extends Commands {

    private ToolsPro plugin;

    public GodCommand(ToolsPro plugin) {
        super("god", Message.CMD_GOD_DESCRIPTION, Message.CMD_GOD_DESCRIPTION2.toString());
        this.setPermission("toolspro.commands.god.use");
        this.plugin = plugin;
    }

    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        if (!sender.hasPermission(this.getPermission())) {
            Message.YOU_DONT_HAVE_PERMISSION.print(sender, 'c');
        } else {
            if (args.length != 0) {
                if (sender.hasPermission("toolspro.commands.god.other")) {
                    Player p = this.plugin.getServer().getPlayer(args[0]);
                    if (p == null) {
                        p = this.plugin.sortedListPlayers(args[0]);
                        if (p == null) {
                            Message.UNKNOWN_PLAYER.print(sender, "prefix:&7[&aGodMode&7]", 'c');
                            return true;
                        }
                    }
                    if (p.getGamemode() == 1 || p.getGamemode() == 3) {
                        Message.PLAYER_NOT_SURVIVAL_OR_ADVENTURE.print(sender, "prefix:&7[&aGodMode&7]", 'c', 'b', p.getName());
                    } else if (this.plugin.getPlayerGodMode(p)) {
                        this.plugin.removePlayerGodMode(p);
                        Message.CMD_GOD_PLAYER_DISABLE.print(sender, "prefix:&7[&aGodMode&7]", 'a', 'b', p.getName());
                        Message.CMD_GOD_PLAYER_DISABLE_MESSAGE.print(p, "prefix:&7[&aGodMode&7]", 'a');
                        this.plugin.info(sender, Message.CMD_GOD_PLAYER_DISABLE_INFO.getText("prefix:&7[GodMode]", '7', '7', sender.getName(), p.getName()));
                    } else {
                        this.plugin.setPlayerGodMode(p);
                        Message.CMD_GOD_PLAYER_ENABLE.print(sender, "prefix:&7[&aGodMode&7]", 'a', 'b', p.getName());
                        Message.CMD_GOD_PLAYER_ENABLE_MESSAGE.print(p, "prefix:&7[&aGodMode&7]", 'a');
                        this.plugin.info(sender, Message.CMD_GOD_PLAYER_ENABLE_INFO.getText("prefix:&7[GodMode]", '7', '7', sender.getName(), p.getName()));
                    }
                } else {
                    return Message.YOU_DONT_HAVE_PERMISSION.print(sender, 'c');
                }
            } else {
                if (sender instanceof Player) {
                    if (((Player) sender).getGamemode() == 1 || ((Player) sender).getGamemode() == 3) {
                        Message.YOU_NOT_SURVIVAL_OR_ADVENTURE.print(sender, "prefix:&7[&aGodMode&7]", 'c');
                    } else if (this.plugin.getPlayerGodMode((Player) sender)) {
                        this.plugin.removePlayerGodMode((Player) sender);
                        Message.CMD_GOD_SENDER_DISABLE.print(sender, "prefix:&7[&aGodMode&7]", 'a');
                        this.plugin.info(sender, Message.CMD_GOD_SENDER_DISABLE_INFO.getText("prefix:&7[GodMode]", '7', '7', sender.getName()));
                    } else {
                        this.plugin.setPlayerGodMode((Player) sender);
                        Message.CMD_GOD_SENDER_ENABLE.print(sender, "prefix:&7[&aGodMode&7]", 'a');
                        this.plugin.info(sender, Message.CMD_GOD_SENDER_ENABLE_INFO.getText("prefix:&7[GodMode]", '7', '7', sender.getName()));
                    }
                } else {
                    return Message.NEED_PLAYER.print(sender, "prefix:&7[&aGodMode&7]", 'c');
                }
            }
        }
        return true;
    }
}
