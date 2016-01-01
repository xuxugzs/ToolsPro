package ToolsPro.commands;

import ToolsPro.ToolsPro;
import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.entity.Effect;
import cn.nukkit.utils.TextFormat;

/**
 * Created by Pub4Game on 19.12.2015.
 */
public class DepthCommand extends Command {

    private ToolsPro plugin;

    public DepthCommand(ToolsPro plugin) {
        super("depth", "Показывает Ваше местонахождение относительно океана.", "/depth");
        this.setPermission("toolspro.commands.depth");
        this.plugin = plugin;
    }

    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        if (!sender.hasPermission(this.getPermission())) {
            sender.sendMessage(this.getPermissionMessage());
        }else if (sender instanceof Player) {
            int pos = ((Player) sender).getFloorY();
            sender.sendMessage(TextFormat.colorize("You're " + ((pos - 63) == 0 ? "at" : (Math.abs(pos) + " meters " + (pos > 0 ? "above" : "below"))) + " the sea level."));
        }else{
            sender.sendMessage(TextFormat.colorize("&cПожалуйста, используйте эту команду только в игре!"));
        }
        return true;
    }
}
