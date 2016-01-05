package ToolsPro.listeners;

import ToolsPro.ToolsPro;
import ToolsPro.util.Message;
import cn.nukkit.Player;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.EventPriority;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerChatEvent;
import cn.nukkit.math.NukkitMath;
import cn.nukkit.utils.Config;

import java.io.File;

public class MuteListener implements Listener {

    ToolsPro plugin;

    public MuteListener(ToolsPro plugin) {
        this.plugin = plugin;
    }

    @EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = false)
    public void onChat(PlayerChatEvent event) {
        Config mute = new Config(new File(this.plugin.getDataFolder(), "mute.yml"), Config.YAML);
        Player player = event.getPlayer();
        if (mute.exists(player.getName().toLowerCase())) {
            if (mute.get(player.getName().toLowerCase(), System.currentTimeMillis()) >= System.currentTimeMillis()) {
                long time = (mute.get(player.getName().toLowerCase(), System.currentTimeMillis()) - System.currentTimeMillis()) / 1000;
                int seconds = NukkitMath.floorDouble(time % 60);
                int minutes = NukkitMath.floorDouble((time % 3600) / 60);
                int hours = NukkitMath.floorDouble(time % (3600 * 24) / 3600);
                int days = NukkitMath.floorDouble(time / (3600 * 24));
                String timemute = days + Message.DAYS.getText('c') +
                        hours + Message.HOURS.getText('c') +
                        minutes + Message.MINUTES.getText('c') +
                        seconds + Message.SECONDS.getText('c');
                Message.LISTENER_MUTE_LINE1.print(player, "prefix:&7[&aMute&7]", 'c');
                Message.LISTENER_MUTE_LINE2.print(player, "prefix:&7[&aMute&7]", 'c', '4', timemute);
                event.setCancelled();
            } else {
                mute.remove(player.getName().toLowerCase());
                mute.save();
            }
        }
    }
}