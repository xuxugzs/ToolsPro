package ToolsPro.commands;

import ToolsPro.ToolsPro;
import ToolsPro.util.Message;
import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;

/**
 * Created by Pub4Game on 19.12.2015.
 */
public class ClearInventoryCommand extends Commands {

    private ToolsPro plugin;

    public ClearInventoryCommand(ToolsPro plugin) {
        super("clearinventory", Message.CMD_CLEARINVENTORY_DESCRIPTION, Message.CMD_CLEARINVENTORY_DESCRIPTION2.toString());
        this.setPermission("toolspro.commands.clearinventory.use");
        this.setAliases(new String[]{"ci"});
        this.plugin = plugin;
    }

    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        if (!sender.hasPermission(this.getPermission())) {
            sender.sendMessage(Message.YOU_DONT_HAVE_PERMISSION.getText('c'));
        } else {
            if (args.length != 0) {
                if (sender.hasPermission("toolspro.commands.clearinventory.other")) {
                    Player p = this.plugin.getServer().getPlayer(args[0]);
                    if (p != null) {
                        if (p.getGamemode() == 1 || p.getGamemode() == 3) {
                            Message.PLAYER_NOT_SURVIVAL_OR_ADVENTURE.print(sender, "prefix:&7[&aClearInv&7]", 'c', 'b');
                        } else {
                            p.getInventory().clearAll();
                            Message.CMD_CLEARINVENTORY_PLAYER.print(sender, "prefix:&7[&aClearInv&7]", 'a', 'b');
                            this.plugin.info(sender, Message.CMD_CLEARINVENTORY_PLAYER_INFO.getText("prefix:&7[ClearInv]", '7', '7', sender.getName(), p.getName()));
                        }
                    } else {
                        Message.UNKNOWN_PLAYER.print(sender, "prefix:&7[&aClearInv&7]", 'c');
                    }
                } else {
                    sender.sendMessage(Message.YOU_DONT_HAVE_PERMISSION.getText('c'));
                }
            } else {
                if (sender instanceof Player) {
                    if (((Player) sender).getGamemode() == 1 || ((Player) sender).getGamemode() == 3) {
                        Message.YOU_NOT_SURVIVAL_OR_ADVENTURE.print(sender, "prefix:&7[&aClearInv&7]", 'c');
                    } else {
                        ((Player) sender).getInventory().clearAll();
                        Message.CMD_CLEARINVENTORY_SENDER.print(sender, "prefix:&7[&aClearInv&7]", 'a');
                        this.plugin.info(sender, Message.CMD_CLEARINVENTORY_SENDER_INFO.getText("prefix:&7[ClearInv]", '7', '7', sender.getName()));
                    }
                } else {
                    return Message.NEED_PLAYER.print(sender, "prefix:&7[&aClearInv&7]", 'c');
                }
            }
        }
        return true;
    }
}