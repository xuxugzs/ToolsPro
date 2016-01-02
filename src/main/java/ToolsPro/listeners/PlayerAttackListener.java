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
import cn.nukkit.utils.TextFormat;

public class PlayerAttackListener implements Listener {

    ToolsPro plugin;

    public PlayerAttackListener(ToolsPro plugin) {
        this.plugin = plugin;
    }

    @EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = false)
    public void onEntityDamage(EntityDamageEvent event) {
        if (event instanceof EntityDamageByEntityEvent) {
            Entity player = ((EntityDamageByEntityEvent) event).getDamager();
            if (player instanceof Player) {
                if ((((Player) player).getGamemode() == 1)) {
                    Message.BLOCK_DAMAGE_CREATIVE.print(((Player) player), "prefix:&7[&aDamage&7]", 'c');
                    event.setCancelled();
                    return;
                }
                if ((((Player) player).getAllowFlight() == true)) {
                    Message.BLOCK_DAMAGE_FLY.print(((Player) player), "prefix:&7[&aDamage&7]", 'c');
                    event.setCancelled();
                    return;
                }
                if ((this.plugin.isGodMode(((Player) player).getName()))) {
                    Message.BLOCK_DAMAGE_GOD.print(((Player) player), "prefix:&7[&aDamage&7]", 'c');
                    event.setCancelled();
                    return;
                }
                if (this.plugin.isHide(((Player) player).getName())) {
                    Message.BLOCK_DAMAGE_VANSIH.print(((Player) player), "prefix:&7[&aDamage&7]", 'c');
                    event.setCancelled();
                    return;
                }
            }
        }
    }
}