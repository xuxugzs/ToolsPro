package ToolsPro.commands;

import ToolsPro.ToolsPro;
import ToolsPro.util.Message;
import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;
import cn.nukkit.item.Item;

/**
 * Created by Pub4Game on 19.12.2015.
 */
public class RepairCommand extends Commands {

    private ToolsPro plugin;

    public RepairCommand(ToolsPro plugin) {
        super("repair", Message.CMD_REPAIR_DESCRIPTION, "/repair <all|hand>");
        this.setPermission("toolspro.commands.repair.use");
        this.plugin = plugin;
    }

    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        if (!sender.hasPermission(this.getPermission())) {
            Message.YOU_DONT_HAVE_PERMISSION.print(sender, 'c');
        } else {
            if (sender instanceof Player) {
                if (args.length != 0) {
                    switch (args[0]) {
                        case "all":
                            if (sender.hasPermission("toolspro.commands.repair.all")) {
                                for (Item item : ((Player) sender).getInventory().getContents().values()) {
                                    if (this.plugin.isRepairable(item)) {
                                        item.setDamage(0);
                                        ((Player) sender).getInventory().setItemInHand(item);
                                    }
                                }
                            } else {
                                return Message.YOU_DONT_HAVE_PERMISSION.print(sender, 'c');
                            }
                            Message.CMD_REPAIR_ALL_SUCCESSFULLY_REPAIRED.print(sender, "prefix:&7[&aRepair&7]", 'a');
                            if (sender.hasPermission("toolspro.commands.repair.armor")) {
                                for (Item item : ((Player) sender).getInventory().getArmorContents()) {
                                    if (this.plugin.isRepairable(item)) {
                                        item.setDamage(0);
                                        ((Player) sender).getInventory().setItemInHand(item);
                                    }
                                }
                                Message.CMD_REPAIR_SUCCESSFULLY_REPAIRED_INCLUDING_ARMOR.print(sender, "prefix:&7[&aRepair&7]", 'a');
                            }
                            return true;
                        case "hand":
                        default:
                            if (!this.plugin.isRepairable(((Player) sender).getInventory().getItemInHand())) {
                                Message.CMD_REPAIR_ERROR.print(sender, "prefix:&7[&aRepair&7]", 'a');
                                return false;
                            }
                            Item fixedItem = ((Player) sender).getInventory().getItemInHand();
                            fixedItem.setDamage(0);
                            ((Player) sender).getInventory().setItemInHand(fixedItem);
                            Message.CMD_REPAIR_SUCCESSFULLY_REPAIRED.print(sender, "prefix:&7[&aRepair&7]", 'a');
                            return true;
                    }
                } else {
                    if (!this.plugin.isRepairable(((Player) sender).getInventory().getItemInHand())) {
                        Message.CMD_REPAIR_ERROR.print(sender, "prefix:&7[&aRepair&7]", 'a');
                        return false;
                    }
                    Item fixedItem = ((Player) sender).getInventory().getItemInHand();
                    fixedItem.setDamage(0);
                    ((Player) sender).getInventory().setItemInHand(fixedItem);
                    Message.CMD_REPAIR_SUCCESSFULLY_REPAIRED.print(sender, "prefix:&7[&aRepair&7]", 'a');
                    return true;
                }
            } else {
               return Message.NEED_PLAYER.print(sender, "prefix:&7[&aRepair&7]", 'c');
            }
        }
        return true;
    }
}
