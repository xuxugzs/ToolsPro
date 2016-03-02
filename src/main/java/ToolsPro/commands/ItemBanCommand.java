package ToolsPro.commands;

import ToolsPro.ToolsPro;
import ToolsPro.util.Message;
import cn.nukkit.command.CommandSender;
import cn.nukkit.item.Item;

/**
 * Created by Pub4Game on 19.12.2015.
 */
public class ItemBanCommand extends Commands {

    private ToolsPro plugin;

    public ItemBanCommand(ToolsPro plugin) {
        super("itemban", Message.CMD_ITEMBAN_DESCRIPTION, "/itemban ban/unban <ID>");
        this.setPermission("toolspro.commands.itemban");
        this.plugin = plugin;
    }

    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        if (!sender.hasPermission(this.getPermission())) {
            Message.YOU_DONT_HAVE_PERMISSION.print(sender, 'c');
        } else {
            if (args.length > 1) {
                Item items = Item.fromString(args[1]);
                String ItemName = items.getName();
                int ItemID = items.getId();
                if (!args[1].matches("^[1-9]+\\d*$") || args[1].length() > 3) {
                    Message.CMD_ITEMBAN_WRONGID.print(sender, "prefix:&7[&aItemBan&7]", 'c');
                } else {
                    switch (args[0]) {
                        case "ban":
                            if (this.plugin.getItemBan(ItemID)) {
                                Message.CMD_ITEMBAN_ALREADYBAN.print(sender, "prefix:&7[&aItemBan&7]", 'c', '9', ItemName, ItemID);
                            } else {
                                this.plugin.setItemBan(ItemID);
                                Message.CMD_ITEMBAN_ADDED.print(sender, "prefix:&7[&aItemBan&7]", 'a', '9', ItemName, ItemID);
                            }
                            return true;
                        case "unban":
                            if (!this.plugin.getItemBan(ItemID)) {
                                Message.CMD_ITEMBAN_ALREADYREMOVED.print(sender, "prefix:&7[&aItemBan&7]", 'c', '9', ItemName, ItemID);
                            } else {
                                this.plugin.removeItemBan(ItemID);
                                Message.CMD_ITEMBAN_REMOVED.print(sender, "prefix:&7[&aItemBan&7]", 'a', '9', ItemName, ItemID);
                            }
                            return true;
                        default:
                            return Message.CMD_ITEMBAN_USAGE.print(sender, "prefix:&7[&aItemBan&7]", 'c');
                    }
                }
            } else {
                return Message.CMD_ITEMBAN_USAGE.print(sender, "prefix:&7[&aItemBan&7]", 'c');
            }
        }
        return true;
    }
}
