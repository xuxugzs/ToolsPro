package ToolsPro.listeners;

import ToolsPro.ToolsPro;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.EventPriority;
import cn.nukkit.event.Listener;
import cn.nukkit.event.TextContainer;
import cn.nukkit.event.player.*;

public class MessageListener implements Listener {

    ToolsPro plugin;

    public MessageListener(ToolsPro plugin) {
        this.plugin = plugin;
    }

    @EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = false)
    public void onJoin(PlayerJoinEvent event){
        event.setJoinMessage(new TextContainer(""));
    }

    @EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = false)
    public void onQuit(PlayerQuitEvent event){
        event.setQuitMessage(new TextContainer(""));
    }
}