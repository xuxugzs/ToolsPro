package ToolsPro.commands;

import ToolsPro.ToolsPro;
import ToolsPro.util.Message;
import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;

/**
 * Created by Pub4Game on 19.12.2015.
 */
public class HealCommand extends Commands {

    private ToolsPro plugin;

    public HealCommand(ToolsPro plugin) {
        super("heal", Message.CMD_HEAL_DESCRIPTION, Message.CMD_HEAL_DESCRIPTION2.toString());
        this.setPermission("toolspro.commands.heal.use");
        this.plugin = plugin;
    }

    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        if (!sender.hasPermission(this.getPermission())) {
            Message.YOU_DONT_HAVE_PERMISSION.print(sender, 'c');
        } else {
            if (args.length != 0) {
                if (sender.hasPermission("toolspro.commands.heal.other")) {
                    Player p = this.plugin.getServer().getPlayer(args[0]);
                    if (p == null) {
                        p = this.plugin.sortedListPlayers(args[0]);
                        if (p == null) {
                            Message.UNKNOWN_PLAYER.print(sender, "prefix:&7[&aHeal&7]", 'c');
                            return true;
                        }
                    }
                    if (p.getGamemode() == 1 || p.getGamemode() == 3) {
                        Message.PLAYER_NOT_SURVIVAL_OR_ADVENTURE.print(sender, "prefix:&7[&aHeal&7]", 'c', 'b', p.getName());
                    } else if (p.getHealth() != 20) {
                        p.setHealth(20);
                        Message.CMD_HEAL_PLAYER.print(sender, "prefix:&7[&aHeal&7]", 'a', 'b', p.getName());
                        Message.CMD_HEAL_PLAYER_MESSAGE.print(p, "prefix:&7[&aHeal&7]", 'a');
                        this.plugin.info(sender, Message.CMD_HEAL_PLAYER_INFO.getText("prefix:&7[Heal]", '7', '7', sender.getName(), p.getName()));
                    } else {
                        Message.CMD_HEAL_PLAYER_MAX.print(sender, "prefix:&7[&aHeal&7]", 'a', 'b', p.getName());
                    }
                } else {
                    return Message.YOU_DONT_HAVE_PERMISSION.print(sender, 'c');
                }
            } else {
                if (sender instanceof Player) {
                    if (((Player) sender).getGamemode() == 1 || ((Player) sender).getGamemode() == 3) {
                        Message.YOU_NOT_SURVIVAL_OR_ADVENTURE.print(sender, "prefix:&7[&aHeal&7]", 'c');
                    } else if (((Player) sender).getHealth() != 20) {
                        ((Player) sender).setHealth(20);
                        Message.CMD_HEAL_SENDER.print(sender, "prefix:&7[&aHeal&7]", 'a');
                        this.plugin.info(sender, Message.CMD_HEAL_SENDER_INFO.getText("prefix:&7[Heal]", '7', '7', sender.getName()));
                    } else {
                        Message.CMD_HEAL_SENDER_MAX.print(sender, "prefix:&7[&aHeal&7]", 'a');
                    }
                } else {
                    return Message.NEED_PLAYER.print(sender, "prefix:&7[&aHeal&7]", 'c');
                }
            }
        }
        return true;
    }
}