package ToolsPro.commands;

import ToolsPro.ToolsPro;
import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.event.entity.EntityDamageEvent;
import cn.nukkit.utils.TextFormat;

/**
 * Created by Pub4Game on 19.12.2015.
 */
public class TopCommand extends Command {

    private ToolsPro plugin;

    public TopCommand(ToolsPro plugin) {
        super("top", "Teleport to the highest block above you", "/top");
        this.setPermission("toolspro.commands.top");
        this.plugin = plugin;
    }

    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        if (!sender.hasPermission(this.getPermission())) {
            sender.sendMessage(this.getPermissionMessage());
        } else {
            if (sender instanceof Player) {
                sender.sendMessage(TextFormat.colorize("&aТелепортация..."));
                ((Player) sender).teleport(((Player) sender).add(0, ((Player) sender).getLevel().getHighestBlockAt((int)((Player) sender).getX(), (int)((Player) sender).getZ()) + 1));
            }else{
                sender.sendMessage(TextFormat.colorize("&cПожалуйста, используйте эту команду в игре!"));
            }
        }
        return true;
    }
}
