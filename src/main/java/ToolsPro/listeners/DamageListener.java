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
import cn.nukkit.utils.Config;

public class DamageListener implements Listener {

    ToolsPro plugin;

    public DamageListener(ToolsPro plugin) {
        this.plugin = plugin;
    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = false)
    public void onEntityDamage(EntityDamageEvent event) {
        if (event instanceof EntityDamageByEntityEvent) {
            Entity damager = ((EntityDamageByEntityEvent) event).getDamager();
            Entity player = event.getEntity();
            Config config = this.plugin.getConfig();
            boolean blockDamage = config.getBoolean("block-damage.enable");
            boolean blockDamageCreative = config.getBoolean("block-damage.creative");
            boolean blockDamageFly = config.getBoolean("block-damage.fly");
            boolean blockDamageGod = config.getBoolean("block-damage.god");
            boolean blockDamageVanish = config.getBoolean("block-damage.vanish");
            if (damager instanceof Player && player instanceof Player && blockDamage) {
                if ((((Player) damager).getGamemode() == 1) && !((Player) damager).hasPermission("toolspro.damage.creative") && blockDamageCreative) {
                    Message.BLOCK_DAMAGE_CREATIVE.print(((Player) damager), "prefix:&7[&aDamage&7]", 'c');
                    event.setCancelled();
                    return;
                }
                if (this.plugin.getPlayerFly((Player) damager) && !((Player) damager).hasPermission("toolspro.damage.fly") && blockDamageFly) {
                    Message.BLOCK_DAMAGE_FLY.print(((Player) damager), "prefix:&7[&aDamage&7]", 'c');
                    event.setCancelled();
                    return;
                }
                if (this.plugin.getPlayerGodMode((Player) damager) && !((Player) damager).hasPermission("toolspro.damage.god") && blockDamageGod) {
                    Message.BLOCK_DAMAGE_GOD.print(((Player) damager), "prefix:&7[&aDamage&7]", 'c');
                    event.setCancelled();
                    return;
                }
                if (this.plugin.getPlayerVanish((Player) damager) && !((Player) damager).hasPermission("toolspro.damage.vanish") && blockDamageVanish) {
                    Message.BLOCK_DAMAGE_VANISH.print(((Player) damager), "prefix:&7[&aDamage&7]", 'c');
                    event.setCancelled();
                }
            }
        }
    }
}