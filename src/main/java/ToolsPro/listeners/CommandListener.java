package ToolsPro.listeners;

import ToolsPro.ToolsPro;
import cn.nukkit.Player;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.EventPriority;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerCommandPreprocessEvent;

public class CommandListener implements Listener {

    ToolsPro plugin;

    public CommandListener(ToolsPro plugin) {
        this.plugin = plugin;
    }

    @EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = false)
    public void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent event) {
        Player player = event.getPlayer();
        String[] cmd = event.getMessage().split(" ");
        Player p = this.plugin.getServer().getPlayer(cmd[1]);
        boolean TellNotice = this.plugin.getConfig().getBoolean("TellNotice", false);
        if (cmd[0].equalsIgnoreCase("/tell") && cmd[1] != null && TellNotice == true){ // :D
            String msg = "";
            for (int i = 2; i < cmd.length; i++) {
                msg += cmd[i] + " ";
            }
            if (msg.length() > 0) {
                msg = msg.substring(0, msg.length() - 1);
            }
            this.plugin.info(player, "[" + player.getName() + " -> " + p.getName() + "] " + msg);
        }
    }
}
