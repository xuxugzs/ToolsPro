package ToolsPro.commands;

import ToolsPro.ToolsPro;
import ToolsPro.util.Message;
import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;

/**
 * Created by Pub4Game on 19.12.2015.
 */
public class ClearInventoryCommand extends ToolsProCommand {

    private ToolsPro plugin;

    public ClearInventoryCommand(ToolsPro plugin) {
        super("clearinventory", Message.CMD_CLEARINVENTORY_DESCRIPTION, Message.CMD_CLEARINVENTORY_DESCRIPTION2.toString());
        this.setPermission("toolspro.commands.clearinventory");
        this.setAliases(new String[]{"ci"});
        this.plugin = plugin;
    }

    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        if (!sender.hasPermission(this.getPermission())) {
            sender.sendMessage(this.getPermissionMessage());
        } else {
            if (args.length != 0) {
                if (sender.hasPermission("toolspro.clearinventory.other")) {
                    Player p = this.plugin.getServer().getPlayer(args[0]);
                    if (p != null) {
                        if (p.getGamemode() != 0) {
                            Message.PLAYER_NOT_SURVIVAL.print(sender, "prefix:&7[&aClearInv&7]", 'c', 'b');
                        } else {
                            p.getInventory().clearAll();
                            Message.CMD_CLEARINVENTORY_PLAYER.print(sender, "prefix:&7[&aClearInv&7]", 'a', 'b');
                            this.plugin.info(sender, Message.CMD_CLEARINVENTORY_PLAYER_INFO.getText("prefix:&7[ClearInv]", sender.getName(), p.getName()));
                        }
                    } else {
                        Message.UNKNOWN_PLAYER.print(sender, "prefix:&7[&aClearInv&7]", 'c');
                    }
                } else {
                    sender.sendMessage(this.getPermissionMessage());
                }
            } else {
                if (sender instanceof Player) {
                    if (((Player) sender).getGamemode() != 0) {
                        Message.YOU_NOT_SURVIVAL.print(sender, "prefix:&7[&aClearInv&7]", 'c');
                    } else {
                        ((Player) sender).getInventory().clearAll();
                        Message.CMD_CLEARINVENTORY_SENDER.print(sender, "prefix:&7[&aClearInv&7]", 'a');
                        this.plugin.info(sender, Message.CMD_CLEARINVENTORY_SENDER_INFO.getText("prefix:&7[ClearInv]"));
                    }
                } else {
                    return Message.NEED_PLAYER.print(sender, "prefix:&7[&aClearInv&7]", 'c');
                }
            }
        }
        return true;
    }
}