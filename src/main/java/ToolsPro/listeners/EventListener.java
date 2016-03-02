package ToolsPro.listeners;

import ToolsPro.ToolsPro;
import ToolsPro.util.Message;
import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.block.Block;
import cn.nukkit.entity.Entity;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.EventPriority;
import cn.nukkit.event.Listener;
import cn.nukkit.event.entity.EntityDamageEvent;
import cn.nukkit.event.inventory.InventoryPickupItemEvent;
import cn.nukkit.event.player.*;
import cn.nukkit.utils.Config;

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
    public void onPlayerDropItemEvent(PlayerDropItemEvent event) {
        Config config = this.plugin.getConfig();
        boolean limitedCreative = config.getBoolean("limited-creative.enable");
        boolean dropItem = config.getBoolean("limited-creative.drop-item");
        Player player = event.getPlayer();
        if (player.isCreative() && limitedCreative && dropItem && !player.hasPermission("toolspro.limited-creative") || !player.hasPermission("toolspro.limited-creative.drop-item")) {
            event.setCancelled();
        }
    }

    @EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = false)
    public void onInventoryPickupItemEvent(InventoryPickupItemEvent event) {
        Config config = this.plugin.getConfig();
        boolean limitedCreative = config.getBoolean("limited-creative.enable");
        boolean pickupItem = config.getBoolean("limited-creative.pickup-item");
        for (Player player : event.getViewers()) {
            if (player.isCreative() && limitedCreative && pickupItem && !player.hasPermission("toolspro.limited-creative") || !player.hasPermission("toolspro.limited-creative.pickup-item")) {
                event.setCancelled();
            }
        }
    }

    @EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = false)
    public void onPlayerInteractEvent(PlayerInteractEvent event) {
        Config config = this.plugin.getConfig();
        boolean limitedCreative = config.getBoolean("limited-creative.enable");
        boolean useBlock = config.getBoolean("limited-creative.use-block.enable");
        Player player = event.getPlayer();
        if (player.isCreative() && limitedCreative && useBlock) {
            int block = event.getBlock().getId();
            boolean CHEST = config.getBoolean("limited-creative.use-block.chest");
            boolean WORKBENCH = config.getBoolean("limited-creative.use-block.workbench");
            boolean FURNACE = config.getBoolean("limited-creative.use-block.furnace");
            boolean TRAPPED_CHEST = config.getBoolean("limited-creative.use-block.trapped_chest");
            boolean BREWING_STAND_BLOCK = config.getBoolean("limited-creative.use-block.brewing_stand");
            boolean ENCHANT_TABLE = config.getBoolean("limited-creative.use-block.enchant_table");
            if (block == Block.CHEST && CHEST
                    || block == Block.WORKBENCH && WORKBENCH
                    || block == Block.FURNACE && FURNACE
                    || block == Block.BURNING_FURNACE && FURNACE
                    || block == Block.TRAPPED_CHEST && TRAPPED_CHEST
                    || block == Block.BREWING_STAND_BLOCK && BREWING_STAND_BLOCK
                    || block == Block.ENCHANT_TABLE && ENCHANT_TABLE
                    && !player.hasPermission("toolspro.limited-creative.use-block")) {
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
        if (!player.hasPermission("toolspro.savegamemode") && player.getGamemode() != 0 && JoinSurvival) {
            player.setGamemode(0);
            Message.LISTENER_JOIN_SURVIVAL.print(player, "prefix:&7[&aGM&7]", 'a');
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
        Player player = event.getEntity();
        if (this.plugin.getPlayerSaveInv(player)) {
            event.setKeepInventory(true);
            Message.LISTENER_SAVEINV_DEATH.print(player, "prefix:&7[&aSaveInv&7]", 'a');
        }
    }
}
