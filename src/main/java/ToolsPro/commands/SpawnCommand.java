package ToolsPro.commands;

import ToolsPro.ToolsPro;
import ToolsPro.util.Message;
import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.entity.Effect;
import cn.nukkit.level.Location;
import cn.nukkit.utils.TextFormat;

/**
 * Created by Pub4Game on 19.12.2015.
 */
public class SpawnCommand extends ToolsProCommand {

    private ToolsPro plugin;

    public SpawnCommand(ToolsPro plugin) {
        super("spawn", Message.CMD_SPAWN_DESCRIPTION, "/spawn");
        this.setPermission("toolspro.commands.spawn");
        this.plugin = plugin;
    }

    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        if (!sender.hasPermission(this.getPermission())) {
            sender.sendMessage(this.getPermissionMessage());
        }else if (args.length != 0){
            Player p = this.plugin.getServer().getPlayer(args[0]);
            if(!sender.hasPermission("toolspro.spawn.other")){
                sender.sendMessage(this.getPermissionMessage());
            }else if (p != null){
                ((Player) p).teleport(Location.fromObject(this.plugin.getServer().getDefaultLevel().getSpawnLocation(), this.plugin.getServer().getDefaultLevel()));
                Message.CMD_SPAWN_TP_PLAYER_MESSAGE.print(p, "prefix:&7[&aSpawn&7]", 'c');
                Message.CMD_SPAWN_TP_SENDER_MESSAGE.print(sender, "prefix:&7[&aSpawn&7]", 'c');
            }else{
                Message.UNKNOWN_PLAYER.print(sender, "prefix:&7[&aSpawn&7]", 'c');
            }
        }
        if (sender instanceof Player) {
            ((Player) sender).teleport(Location.fromObject(this.plugin.getServer().getDefaultLevel().getSpawnLocation(), this.plugin.getServer().getDefaultLevel()));
            Message.CMD_SPAWN_TP_PLAYER_MESSAGE.print(sender, "prefix:&7[&aSpawn&7]", 'c');
        }else{
            Message.NEED_PLAYER.print(sender, "prefix:&7[&aSpawn&7]", 'c');
        }
        return true;
    }
}
