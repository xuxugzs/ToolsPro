package ToolsPro.commands;

import ToolsPro.ToolsPro;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.item.Item;
import cn.nukkit.utils.Config;
import cn.nukkit.utils.TextFormat;

import java.io.File;

/**
 * Created by Pub4Game on 19.12.2015.
 */
public class ItemBanCommand extends Command {

    private ToolsPro plugin;

    public ItemBanCommand(ToolsPro plugin) {
        super("item", "Управляет списком заблокированных вещей.", "/item ban/unban <ID>");
        this.setPermission("toolspro.commands.item");
        this.plugin = plugin;
    }

    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        if (!sender.hasPermission(this.getPermission())) {
            sender.sendMessage(this.getPermissionMessage());
        }else if (args.length == 2) {
            Config item = new Config(new File(this.plugin.getDataFolder(), "item.yml"), Config.YAML);
            Item items = Item.fromString(args[1]);
            String ItemName = items.getName();
            int ItemID = items.getId();
            if (!args[1].matches("^[1-9]+\\d*$") || args[1].length() > 3 || ItemName == "Unknown") {
                sender.sendMessage(TextFormat.colorize("&7[&aBanItem&7] &cПожалуйста, введите верный ID!"));
            }else{
                switch (args[0]) {
                    case "ban":
                        if (item.exists(ItemName)) {
                            sender.sendMessage(TextFormat.colorize("&7[&aBanItem&7] &cПредет &9" + ItemName + " (ID - " + ItemID + ") &cуже заблокирован!"));
                        }else{
                            item.set(ItemName, ItemID);
                            item.save();
                            sender.sendMessage(TextFormat.colorize("&7[&aBanItem&7] &aПредет &9" + ItemName + " (ID - " + ItemID + ") &aдобавлен в список!"));
                        }
                        return true;
                    case "unban":
                        if (!(item.exists(ItemName))) {
                            sender.sendMessage(TextFormat.colorize("&7[&aBanItem&7] &cПредет &9" + ItemName + " (ID - " + ItemID + ") &cне был заблокирован!"));
                        }else{
                            item.remove(ItemName);
                            item.save();
                            sender.sendMessage(TextFormat.colorize("&7[&aBanItem&7] &aПредет &9" + ItemName + " (ID - " + ItemID + ") &aудален из списка!"));
                        }
                        return true;
                    default:
                        sender.sendMessage(TextFormat.colorize("&7[&aBanItem&7] &cИспользуйте /item <ban|unban> <ID>"));
                        return true;
                }
            }
        }else{
            sender.sendMessage(TextFormat.colorize("&7[&aBanItem&7] &cИспользуйте /item <ban|unban> <ID>"));
        }
        return true;
    }
}
