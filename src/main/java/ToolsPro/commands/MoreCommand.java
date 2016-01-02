package ToolsPro.commands;

import ToolsPro.ToolsPro;
import ToolsPro.util.Message;
import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;
import cn.nukkit.item.Item;

/**
 * Created by Pub4Game on 19.12.2015.
 */
public class MoreCommand extends ToolsProCommand {

    private ToolsPro plugin;

    public MoreCommand(ToolsPro plugin) {
        super("more", Message.CMD_MORE_DESCRIPTION, "/more");
        this.setPermission("toolspro.commands.more");
        this.plugin = plugin;
    }

    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        if (!sender.hasPermission(this.getPermission())) {
            sender.sendMessage(this.getPermissionMessage());
        } else {
            if (sender instanceof Player) {
                Item item = ((Player) sender).getInventory().getItemInHand();
                if (item.getId() == Item.AIR) {
                    Message.CMD_MORE_NO_AIR.print(sender, "prefix:&7[&aMore&7]", 'c');
                    return false;
                }
                item.setCount(item.getMaxStackSize());
                ((Player) sender).getInventory().setItem(((Player) sender).getInventory().getHeldItemIndex(), item);
                Message.CMD_MORE_SUCCESSFULLY.print(sender, "prefix:&7[&aMore&7]", item.getCount(), 'a', 'b');
            }else{
                Message.NEED_PLAYER.print(sender, "prefix:&7[&aMore&7]", 'c');
            }
        }
        return true;
    }
}
