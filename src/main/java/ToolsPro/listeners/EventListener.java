package ToolsPro.listeners;

import ToolsPro.ToolsPro;
import cn.nukkit.Player;
import cn.nukkit.entity.Effect;
import cn.nukkit.entity.Entity;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.EventPriority;
import cn.nukkit.event.Listener;
import cn.nukkit.event.entity.EntityDamageEvent;
import cn.nukkit.event.player.*;
import cn.nukkit.utils.TextFormat;

public class EventListener implements Listener {

    ToolsPro plugin;

    public EventListener(ToolsPro plugin) {
        this.plugin = plugin;
    }

    @EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = false)
    public void onGodMode(EntityDamageEvent event) {
        Entity entity = event.getEntity();
        if (entity instanceof Player) {
            String name = ((Player) entity).getName();
            if (this.plugin.isGodMode(name.toLowerCase())) {
                event.setCancelled();
            }
        }
    }

    @EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = false)
    public void onPlayerJoin(PlayerJoinEvent event) {
        String name = event.getPlayer().getName();
        Player p = this.plugin.getServer().getPlayer(name);
        if (event.getPlayer().hasPermission("toolspro.inv.save")) {
            if (!(this.plugin.isSaveInv(name))) {
                this.plugin.setSaveInv(name);
                event.getPlayer().sendMessage(TextFormat.colorize("&7[&aSaveInv&7] &aСохранение инвентаря успешно включено!"));
            }
        }
        if (!(event.getPlayer().hasPermission("toolspro.savegamemode")) && (event.getPlayer().getGamemode() != 0)) {
            event.getPlayer().setGamemode(0);
            event.getPlayer().sendMessage(TextFormat.colorize("&7[&aGM&7] &aВаш игровой режим был изменен на выживание!"));
        }
    }

    @EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = false)
    public void onPlayerQuit(PlayerQuitEvent event) {
        String name = event.getPlayer().getName();
        Player p = this.plugin.getServer().getPlayer(name);
        if (p instanceof Player) {
            if (this.plugin.isGodMode(name)) this.plugin.removeGodMode(name);
            if (this.plugin.isSaveInv(name)) this.plugin.removeSaveInv(name);
            if (this.plugin.isHide(name)) {
                for (Effect effect : p.getEffects().values()) {
                    p.removeEffect(14);
                }
                this.plugin.removeHide(name);
            }
        }
    }

    @EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = false)
    public void onPlayerDeath(PlayerDeathEvent event) {
        Entity entity = event.getEntity();
        String name = event.getEntity().getName();
        if (entity instanceof Player) {
            if (event.getEntity().hasPermission("toolspro.inv.save")) {
                if (this.plugin.isSaveInv(name.toLowerCase())) {
                    event.setKeepInventory(true);
                    event.getEntity().sendMessage(TextFormat.colorize("&7[&aSaveInv&7] &aВсе ваши вещи были успешно сохранены!"));
                }
            }
            if (this.plugin.isHide(name)) {
                this.plugin.removeHide(name);
                event.getEntity().sendMessage(TextFormat.colorize("&7[&aVanish&7] &aНевидимость была успешно выключена!"));
            }
        }
    }
}
