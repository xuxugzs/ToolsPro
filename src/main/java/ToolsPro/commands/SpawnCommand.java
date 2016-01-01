package ToolsPro.commands;

import ToolsPro.ToolsPro;
import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.entity.Effect;
import cn.nukkit.level.Location;
import cn.nukkit.utils.TextFormat;

/**
 * Created by Pub4Game on 19.12.2015.
 */
public class SpawnCommand extends Command {

    private ToolsPro plugin;

    public SpawnCommand(ToolsPro plugin) {
        super("spawn", "Телепортирует на спавн.", "/spawn");
        this.setPermission("toolspro.commands.spawn");
        this.plugin = plugin;
    }

    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        if (!sender.hasPermission(this.getPermission())) {
            sender.sendMessage(this.getPermissionMessage());
        }else if (sender instanceof Player) {
            if(args.length != 0){
                Player p = this.plugin.getServer().getPlayer(args[0]);
                if(!sender.hasPermission("toolspro.spawn.other")){
                    sender.sendMessage(this.getPermissionMessage());
                    return false;
                }else if(!(p instanceof Player)){
                    sender.sendMessage(TextFormat.colorize("&7[&aSpawn&7] &cТакого игрока нет на сервере"));
                    return false;
                }
            }
            ((Player) sender).teleport(Location.fromObject(this.plugin.getServer().getDefaultLevel().getSpawnLocation(), this.plugin.getServer().getDefaultLevel()));
            ((Player) sender).sendMessage(TextFormat.colorize("&7[&aSpawn&7] Телепортация..."));
        }else{
            sender.sendMessage(TextFormat.colorize("&7[&aSpawn&7] &cПожалуйста, используйте эту команду только в игре!"));
        }
        return true;
    }
}
