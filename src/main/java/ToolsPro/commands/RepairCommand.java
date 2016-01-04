package ToolsPro.commands;

import ToolsPro.ToolsPro;
import ToolsPro.util.Message;
import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;
import cn.nukkit.item.Item;
import cn.nukkit.utils.TextFormat;

import java.util.Map;

/**
 * Created by Pub4Game on 19.12.2015.
 */
public class RepairCommand extends ToolsProCommand {

    private ToolsPro plugin;

    public RepairCommand(ToolsPro plugin) {
        super("repair", Message.CMD_REPAIR_DESCRIPTION, "/repair");
        this.setPermission("toolspro.commands.repair");
        this.plugin = plugin;
    }

    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        if (!sender.hasPermission(this.getPermission())) {
            sender.sendMessage(this.getPermissionMessage());
        } else {
            if (sender instanceof Player) {
                switch (args[0]) {
                    case "all":
                        if (!sender.hasPermission("toolspro.repair.all")) {
                            sender.sendMessage(TextFormat.RED + this.getPermissionMessage());
                            return false;
                        }
                        for (Item item : ((Player) sender).getInventory().getContents().values()) {
                            if (this.plugin.isRepairable(item)) {
                                item.setDamage(0);
                                ((Player) sender).getInventory().setItemInHand(item);
                            }
                        }
                        sender.sendMessage("All the tools on your inventory were repaired!");
                        if (sender.hasPermission("toolspro.repair.armor")) {
                            for (Item item : ((Player) sender).getInventory().getArmorContents()) {
                                if (this.plugin.isRepairable(item)) {
                                    item.setDamage(0);
                                    ((Player) sender).getInventory().setItemInHand(item);
                                }
                            }
                            sender.sendMessage("(Including the equipped Armor)");
                        }
                        return true;
                    case "hand":
                        if (!this.plugin.isRepairable(((Player) sender).getInventory().getItemInHand())) {
                            sender.sendMessage("[Error] This item can't be repaired!");
                            return false;
                        }
                        Item fixedItem = ((Player) sender).getInventory().getItemInHand();
                        fixedItem.setDamage(0);
                        ((Player) sender).getInventory().setItemInHand(fixedItem);
                        sender.sendMessage("Item successfully repaired!");
                        return true;
                }
            } else {
               return Message.NEED_PLAYER.print(sender, "prefix:&7[&aRepair&7]", 'c');
            }
        }
        return true;
    }
}
