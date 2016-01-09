package ToolsPro.listeners;

import ToolsPro.ToolsPro;
import ToolsPro.util.Message;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.EventPriority;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerPreLoginEvent;

public class PlayerNameListener implements Listener {

    ToolsPro plugin;

    public PlayerNameListener(ToolsPro plugin) {
        this.plugin = plugin;
    }

    @EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = false)
    public void onPreLogin(PlayerPreLoginEvent event) {
        String name = event.getPlayer().getName();
        for (String s : plugin.forbiddenNames) {
            if (s.equalsIgnoreCase(name)) {
                event.setKickMessage(Message.BLOCKED_NICK.getText('c'));
                event.setCancelled();
            }
        }
    }
}