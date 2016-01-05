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
import cn.nukkit.utils.Config;

import java.io.File;

public class ItemBanListener implements Listener {

    ToolsPro plugin;

    public ItemBanListener(ToolsPro plugin) {
        this.plugin = plugin;
    }

    @EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = false)
    public void onTouch(PlayerInteractEvent event) {
        Config item = new Config(new File(this.plugin.getDataFolder(), "item.yml"), Config.YAML);
        if (item.exists(event.getItem().getName()) && !event.getPlayer().hasPermission("toolspro.itemban")) {
            Message.LISTENER_ITEMBAN_TOUCH.print(((Player) event.getPlayer()), "prefix:&7[&aItemban&7]", 'c');
            event.setCancelled();
        }
    }

    @EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = false)
    public void onEat(PlayerItemConsumeEvent event) {
        Config item = new Config(new File(this.plugin.getDataFolder(), "item.yml"), Config.YAML);
        if (item.exists(event.getItem().getName()) && !event.getPlayer().hasPermission("toolspro.itemban")) {
            Message.LISTENER_ITEMBAN_EAT.print(((Player) event.getPlayer()), "prefix:&7[&aItemban&7]", 'c');
            event.setCancelled();
        }
    }

    @EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = false)
    public void onBlockPlace(BlockPlaceEvent event) {
        Config item = new Config(new File(this.plugin.getDataFolder(), "item.yml"), Config.YAML);
        if (item.exists(event.getBlock().getName()) && !event.getPlayer().hasPermission("toolspro.itemban")) {
            Message.LISTENER_ITEMBAN_PLACE.print(((Player) event.getPlayer()), "prefix:&7[&aItemban&7]", 'c');
            event.setCancelled();
        }
    }

    @EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = false)
    public void onBlockBreak(BlockBreakEvent event) {
        Config item = new Config(new File(this.plugin.getDataFolder(), "item.yml"), Config.YAML);
        if (item.exists(event.getBlock().getName()) && !event.getPlayer().hasPermission("toolspro.itemban")) {
            Message.LISTENER_ITEMBAN_BREAK.print(((Player) event.getPlayer()), "prefix:&7[&aItemban&7]", 'c');
            event.setCancelled();
        }
    }
}