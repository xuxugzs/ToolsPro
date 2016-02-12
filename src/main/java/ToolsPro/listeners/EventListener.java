package ToolsPro.listeners;

import ToolsPro.ToolsPro;
import ToolsPro.util.Message;
import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.entity.Entity;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.EventPriority;
import cn.nukkit.event.Listener;
import cn.nukkit.event.entity.EntityDamageEvent;
import cn.nukkit.event.player.PlayerDeathEvent;
import cn.nukkit.event.player.PlayerJoinEvent;
import cn.nukkit.event.player.PlayerPreLoginEvent;
import cn.nukkit.event.player.PlayerQuitEvent;

public class EventListener implements Listener {

    ToolsPro plugin;

    public EventListener(ToolsPro plugin) {
        this.plugin = plugin;
    }

    @EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = false)
    public void onGodMode(EntityDamageEvent event) {
        Entity entity = event.getEntity();
        if (entity instanceof Player) {
            if (this.plugin.getPlayerGodMode((Player) entity)) {
                event.setCancelled();
            }
        }
    }

    @EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = false)
    public void onPreLogin(PlayerPreLoginEvent event) {
        String name = event.getPlayer().getName();
        for (String s : this.plugin.forbiddenNames) {
            if (s.equalsIgnoreCase(name)) {
                event.setKickMessage(Message.BLOCKED_NICK.getText('c'));
                event.setCancelled();
            }
        }
    }

    @EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = false)
    public void onPlayerJoin(PlayerJoinEvent event) {
        boolean JoinSurvival = this.plugin.getConfig().getBoolean("JoinSurvival", false);
        Player player = event.getPlayer();
        this.plugin.joinSession(player);
        if (!player.hasPermission("toolspro.savegamemode") && player.getGamemode() != 0 && JoinSurvival == true) {
            player.setGamemode(0);
            Message.LISTENER_JOIN_SURVIVAL.print(((Player) player), "prefix:&7[&aGM&7]", 'a');
        }
        for (Player p : Server.getInstance().getOnlinePlayers().values()) {
            if (this.plugin.getPlayerVanish(player)) p.hidePlayer(player);
            if (this.plugin.getPlayerVanish(p)) player.hidePlayer(p);
        }
    }


    @EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = false)
    public void onPlayerQuit(PlayerQuitEvent event) {
        String name = event.getPlayer().getName();
        Player p = this.plugin.getServer().getPlayer(name);
        this.plugin.quitSession(p);
    }

    @EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = false)
    public void onPlayerDeath(PlayerDeathEvent event) {
        Entity entity = event.getEntity();
        if (entity instanceof Player) {
            if (this.plugin.getPlayerSaveInv((Player) entity)) {
                event.setKeepInventory(true);
                Message.LISTENER_SAVEINV_DEATH.print(((Player) entity), "prefix:&7[&aSaveInv&7]", 'a');
            }
        }
    }
}
