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
        if (name.equalsIgnoreCase("steve")) {
            event.setKickMessage(Message.LSTN_STEVE_KICK.getText('c'));
            //event.setKickMessage(TextFormat.colorize("&cПожалуйста, измените ник и перезайдите на сервер!"));
            event.setCancelled();
        }
    }
}