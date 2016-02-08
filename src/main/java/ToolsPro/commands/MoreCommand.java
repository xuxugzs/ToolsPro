package ToolsPro.commands;

import ToolsPro.ToolsPro;
import ToolsPro.util.Message;
import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;
import cn.nukkit.item.Item;

/**
 * Created by Pub4Game on 19.12.2015.
 */
public class MoreCommand extends Commands {

    private ToolsPro plugin;

    public MoreCommand(ToolsPro plugin) {
        super("more", Message.CMD_MORE_DESCRIPTION, "/more");
        this.setPermission("toolspro.commands.more.use");
        this.plugin = plugin;
    }

    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        if (!sender.hasPermission(this.getPermission())) {
            sender.sendMessage(Message.YOU_DONT_HAVE_PERMISSION.getText('c'));
        } else {
            if (sender instanceof Player) {
                if (((Player) sender).getGamemode() == 1 || ((Player) sender).getGamemode() == 3) {
                    Message.YOU_NOT_SURVIVAL_OR_ADVENTURE.print(sender, "prefix:&7[&aMore&7]", 'c');
                } else {
                    Item item = ((Player) sender).getInventory().getItemInHand();
                    if (item.getId() == Item.AIR) {
                        Message.CMD_MORE_NO_AIR.print(sender, "prefix:&7[&aMore&7]", 'c');
                        return false;
                    }
                    item.setCount(sender.hasPermission("toolspro.commands.more.oversizedstacks") ? (int) this.plugin.getConfig().get("oversized-stacks") : item.getMaxStackSize());
                    ((Player) sender).getInventory().setItem(((Player) sender).getInventory().getHeldItemIndex(), item);
                    Message.CMD_MORE_SUCCESSFULLY.print(sender, "prefix:&7[&aMore&7]", item.getCount(), 'a', 'b');
                }
            } else {
                return Message.NEED_PLAYER.print(sender, "prefix:&7[&aMore&7]", 'c');
            }
        }
        return true;
    }
}
