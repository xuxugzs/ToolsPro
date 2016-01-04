package ToolsPro.commands;

import ToolsPro.ToolsPro;
import ToolsPro.util.Message;
import cn.nukkit.command.CommandSender;
import cn.nukkit.item.Item;
import cn.nukkit.utils.Config;

import java.io.File;

/**
 * Created by Pub4Game on 19.12.2015.
 */
public class ItemBanCommand extends ToolsProCommand {

    private ToolsPro plugin;

    public ItemBanCommand(ToolsPro plugin) {
        super("item", Message.CMD_ITEMBAN_DESCRIPTION, "/item ban/unban <ID>");
        this.setPermission("toolspro.commands.item");
        this.plugin = plugin;
    }

    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        if (!sender.hasPermission(this.getPermission())) {
            sender.sendMessage(this.getPermissionMessage());
        } else {
            if (args.length == 2) {
                Config item = new Config(new File(this.plugin.getDataFolder(), "item.yml"), Config.YAML);
                Item items = Item.fromString(args[1]);
                String ItemName = items.getName();
                int ItemID = items.getId();
                if (!args[1].matches("^[1-9]+\\d*$") || args[1].length() > 3 || ItemName == "Unknown") {
                    Message.CMD_ITEMBAN_WRONGID.print(sender, "prefix:&7[&aBanItem&7]", 'c');
                } else {
                    switch (args[0]) {
                        case "ban":
                            if (item.exists(ItemName)) {
                                Message.CMD_ITEMBAN_ALREADYBAN.print(sender, "prefix:&7[&aBanItem&7]", 'c', '9', ItemName, ItemID);
                            } else {
                                item.set(ItemName, ItemID);
                                item.save();
                                Message.CMD_ITEMBAN_ADDED.print(sender, "prefix:&7[&aBanItem&7]", 'a', '9', ItemName, ItemID);
                            }
                            return true;
                        case "unban":
                            if (!(item.exists(ItemName))) {
                                Message.CMD_ITEMBAN_BAN.print(sender, "prefix:&7[&aBanItem&7]", 'c', '9', ItemName, ItemID);
                            } else {
                                item.remove(ItemName);
                                item.save();
                                Message.CMD_ITEMBAN_REMOVED.print(sender, "prefix:&7[&aBanItem&7]", 'a', '9', ItemName, ItemID);
                            }
                            return true;
                        default:
                            return Message.CMD_ITEMBAN_USAGE.print(sender, "prefix:&7[&aBanItem&7]", 'c');
                    }
                }
            } else {
                return Message.CMD_ITEMBAN_USAGE.print(sender, "prefix:&7[&aBanItem&7]", 'c');
            }
        }
        return true;
    }
}
