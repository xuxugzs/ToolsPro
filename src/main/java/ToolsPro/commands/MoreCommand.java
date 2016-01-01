package ToolsPro.commands;

import ToolsPro.ToolsPro;
import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.item.Item;
import cn.nukkit.utils.TextFormat;

/**
 * Created by Pub4Game on 19.12.2015.
 */
public class MoreCommand extends Command {

    private ToolsPro plugin;

    public MoreCommand(ToolsPro plugin) {
        super("more", "Дюпает определенный предмет.", "/more");
        this.setPermission("toolspro.commands.more");
        this.plugin = plugin;
    }

    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        if (!sender.hasPermission(this.getPermission())) {
            sender.sendMessage(this.getPermissionMessage());
        } else {
            Item item = ((Player) sender).getInventory().getItemInHand();
            if (item.getId() == Item.AIR) {
                sender.sendMessage(TextFormat.colorize("&cВы не можете использовать воздух!"));
                return false;
            }
            item.setCount(item.getMaxStackSize());
            ((Player) sender).getInventory().setItem(((Player) sender).getInventory().getHeldItemIndex(), item);
            sender.sendMessage(TextFormat.colorize("&bПредмет был успешно дюпнут " + item.getCount()));
        }
        return true;
    }
}
