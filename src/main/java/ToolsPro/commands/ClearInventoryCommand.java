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
            Message.YOU_DONT_HAVE_PERMISSION.print(sender, 'c');
        } else {
            if (args.length != 0) {
                if (sender.hasPermission("toolspro.commands.clearinventory.other")) {
                    Player p = this.plugin.getServer().getPlayer(args[0]);
                    if (p != null) {
                        p.getInventory().clearAll();
                        Message.CMD_CLEARINVENTORY_PLAYER.print(sender, "prefix:&7[&aClearInv&7]", 'a', 'b', p.getName());
                        this.plugin.info(sender, Message.CMD_CLEARINVENTORY_PLAYER_INFO.getText("prefix:&7[ClearInv]", '7', '7', sender.getName(), p.getName()));
                    } else {
                        Message.UNKNOWN_PLAYER.print(sender, "prefix:&7[&aClearInv&7]", 'c');
                    }
                } else {
                    return Message.YOU_DONT_HAVE_PERMISSION.print(sender, 'c');
                }
            } else {
                if (sender instanceof Player) {
                    ((Player) sender).getInventory().clearAll();
                    Message.CMD_CLEARINVENTORY_SENDER.print(sender, "prefix:&7[&aClearInv&7]", 'a');
                    this.plugin.info(sender, Message.CMD_CLEARINVENTORY_SENDER_INFO.getText("prefix:&7[ClearInv]", '7', '7', sender.getName()));
                } else {
                    return Message.NEED_PLAYER.print(sender, "prefix:&7[&aClearInv&7]", 'c');
                }
            }
        }
        return true;
    }
}