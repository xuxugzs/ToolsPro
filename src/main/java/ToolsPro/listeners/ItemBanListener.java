package ToolsPro.listeners;

import ToolsPro.ToolsPro;
import ToolsPro.util.Message;
import cn.nukkit.Player;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.EventPriority;
import cn.nukkit.event.Listener;
import cn.nukkit.event.block.BlockBreakEvent;
import cn.nukkit.event.block.BlockPlaceEvent;
import cn.nukkit.event.player.PlayerInteractEvent;
import cn.nukkit.event.player.PlayerItemConsumeEvent;
import cn.nukkit.event.player.PlayerItemHeldEvent;

public class ItemBanListener implements Listener {

    ToolsPro plugin;

    public ItemBanListener(ToolsPro plugin) {
        this.plugin = plugin;
    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = false)
    public void onTouch(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        int block = event.getItem().getId();
        if (this.checkItemBan(player, block)) {
            Message.LISTENER_ITEMBAN_TOUCH.print(player, "prefix:&7[&aItemban&7]", 'c');
            event.setCancelled();
        } else if (this.checkItemBan(player, block)) {
            Message.LISTENER_ITEMBAN_TOUCH_BLOCK.print(player, "prefix:&7[&aItemban&7]", 'c');
            event.setCancelled();
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = false)
    public void onEat(PlayerItemConsumeEvent event) {
        Player player = event.getPlayer();
        if (this.checkItemBan(player, event.getItem().getId())) {
            Message.LISTENER_ITEMBAN_EAT.print(player, "prefix:&7[&aItemban&7]", 'c');
            event.setCancelled();
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = false)
    public void onBlockPlace(BlockPlaceEvent event) {
        Player player = event.getPlayer();
        if (this.checkItemBan(player, event.getBlock().getId())) {
            Message.LISTENER_ITEMBAN_PLACE.print(player, "prefix:&7[&aItemban&7]", 'c');
            event.setCancelled();
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = false)
    public void onBlockBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        if (this.checkItemBan(player, event.getBlock().getId())) {
            Message.LISTENER_ITEMBAN_BREAK.print(player, "prefix:&7[&aItemban&7]", 'c');
            event.setCancelled();
        }
    }

    @EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = false)
    public void onPlayerItemHeldEvent(PlayerItemHeldEvent event) {
        Player player = event.getPlayer();
        if (this.checkItemBan(player, event.getItem().getId())) {
            player.sendPopup(Message.LISTENER_ITEMBAN_TIP.getText('c'));
        }
    }

    private boolean checkItemBan(Player player, int ID) {
        return this.plugin.getItemBan(ID) && !player.hasPermission("toolspro.itemban") || this.plugin.getItemBan(ID) && !player.hasPermission("toolspro.itemban." + ID);
    }
}