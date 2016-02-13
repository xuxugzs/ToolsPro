package ToolsPro.listeners;

import ToolsPro.ToolsPro;
import ToolsPro.util.Message;
import cn.nukkit.Player;
import cn.nukkit.entity.Entity;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.EventPriority;
import cn.nukkit.event.Listener;
import cn.nukkit.event.entity.EntityDamageByEntityEvent;
import cn.nukkit.event.entity.EntityDamageEvent;

public class DamageListener implements Listener {

    ToolsPro plugin;

    public DamageListener(ToolsPro plugin) {
        this.plugin = plugin;
    }

    @EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = false)
    public void onEntityDamage(EntityDamageEvent event) {
        if (event instanceof EntityDamageByEntityEvent) {
            Entity damager = ((EntityDamageByEntityEvent) event).getDamager();
            Entity player = event.getEntity();
            if (damager instanceof Player && player instanceof Player) {
                if ((((Player) damager).getGamemode() == 1)) {
                    Message.BLOCK_DAMAGE_CREATIVE.print(((Player) damager), "prefix:&7[&aDamage&7]", 'c');
                    event.setCancelled();
                    return;
                }
                if (this.plugin.getPlayerFly((Player) damager)) {
                    Message.BLOCK_DAMAGE_FLY.print(((Player) damager), "prefix:&7[&aDamage&7]", 'c');
                    event.setCancelled();
                    return;
                }
                if (this.plugin.getPlayerGodMode((Player) damager)) {
                    Message.BLOCK_DAMAGE_GOD.print(((Player) damager), "prefix:&7[&aDamage&7]", 'c');
                    event.setCancelled();
                    return;
                }
                if (this.plugin.getPlayerVanish((Player) damager)) {
                    Message.BLOCK_DAMAGE_VANISH.print(((Player) damager), "prefix:&7[&aDamage&7]", 'c');
                    event.setCancelled();
                    return;
                }
            }
        }
    }
}