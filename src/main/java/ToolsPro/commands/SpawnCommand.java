package ToolsPro.commands;

import ToolsPro.ToolsPro;
import ToolsPro.util.Message;
import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;
import cn.nukkit.level.Location;

import java.util.HashMap;

/**
 * Created by Pub4Game on 19.12.2015.
 */
public class SpawnCommand extends Commands {

    private ToolsPro plugin;
    private HashMap<String, Long> cooldown = new HashMap<>();

    public SpawnCommand(ToolsPro plugin) {
        super("spawn", Message.CMD_SPAWN_DESCRIPTION, "/spawn");
        this.setPermission("toolspro.commands.spawn.use");
        this.plugin = plugin;
    }

    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        if (!sender.hasPermission(this.getPermission())) {
            Message.YOU_DONT_HAVE_PERMISSION.print(sender, 'c');
        } else {
            if (args.length != 0) {
                Player p = this.plugin.getServer().getPlayer(args[0]);
                if (!sender.hasPermission("toolspro.commands.spawn.other")) {
                    return Message.YOU_DONT_HAVE_PERMISSION.print(sender, 'c');
                } else if (p != null) {
                    if (this.spawnCooldown((Player) sender)) return true;
                    p.teleport(Location.fromObject(this.plugin.getServer().getDefaultLevel().getSpawnLocation(), this.plugin.getServer().getDefaultLevel()));
                    Message.CMD_SPAWN_TP_PLAYER_MESSAGE.print(p, "prefix:&7[&aSpawn&7]", 'a');
                    Message.CMD_SPAWN_TP_SENDER.print(sender, "prefix:&7[&aSpawn&7]", 'a', 'b', p.getName());
                } else {
                    Message.UNKNOWN_PLAYER.print(sender, "prefix:&7[&aSpawn&7]", 'c');
                }
            } else {
                if (sender instanceof Player) {
                    if (this.spawnCooldown((Player) sender)) return true;
                    ((Player) sender).teleport(Location.fromObject(this.plugin.getServer().getDefaultLevel().getSpawnLocation(), this.plugin.getServer().getDefaultLevel()));
                    Message.CMD_SPAWN_TP_PLAYER_MESSAGE.print(sender, "prefix:&7[&aSpawn&7]", 'a');
                } else {
                    return Message.NEED_PLAYER.print(sender, "prefix:&7[&aSpawn&7]", 'c');
                }
            }
        }
        return true;
    }

    private boolean spawnCooldown(Player player) {
        if (cooldown.containsKey(player.getName().toLowerCase()) && !player.hasPermission("toolspro.commands.spawn.cooldown")) {
            long time = System.currentTimeMillis() - cooldown.get(player.getName().toLowerCase());
            long cooldownTime = this.plugin.getConfig().get("spawn.cooldown", 5) * 1000;
            if (time < cooldownTime) {
                Message.CMD_SPAWN_COOLDOWN.print(player, "prefix:&7[&aSpawn&7]", 'c', 'b', (cooldownTime - time) / 1000);
                return true;
            }
        }
        cooldown.put(player.getName().toLowerCase(), System.currentTimeMillis());
        return false;
    }
}
