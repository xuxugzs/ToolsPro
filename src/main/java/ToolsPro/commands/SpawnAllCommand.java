package ToolsPro.commands;

import ToolsPro.ToolsPro;
import ToolsPro.util.Message;
import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;
import cn.nukkit.level.Location;

/**
 * Created by Pub4Game on 19.12.2015.
 */
public class SpawnAllCommand extends ToolsProCommand {

    private ToolsPro plugin;

    public SpawnAllCommand(ToolsPro plugin) {
        super("spawnall", Message.CMD_SPAWNALL_DESCRIPTION, "/spawnall");
        this.setPermission("toolspro.commands.spawnall");
        this.plugin = plugin;
    }

    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        int count = this.plugin.getServer().getOnlinePlayers().size();
        if (!sender.hasPermission(this.getPermission())) {
            sender.sendMessage(this.getPermissionMessage());
        }else if ((count < 1) || (sender instanceof Player && count < 2)){
            Message.CMD_SPAWNALL_NO_PLAYERS.print(sender, "prefix:&7[&aSpawn&7]", 'c');
        }else{
            for (Player player : this.plugin.getServer().getOnlinePlayers().values()){
                if (player.equals(sender)) continue;
                player.teleport(Location.fromObject(this.plugin.getServer().getDefaultLevel().getSpawnLocation(), this.plugin.getServer().getDefaultLevel()));
                Message.CMD_SPAWNALL_PLAYER_TP_TO_SPAWN.print(sender, "prefix:&7[&aSpawn&7]", 'c');
            }
        }
        return true;
    }
}
