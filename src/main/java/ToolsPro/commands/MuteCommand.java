package ToolsPro.commands;

import ToolsPro.ToolsPro;
import ToolsPro.util.Message;
import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;
import cn.nukkit.math.NukkitMath;
import cn.nukkit.utils.Config;

import java.io.File;

/**
 * Created by Pub4Game on 21.12.2015.
 */

public class MuteCommand extends ToolsProCommand {

    private ToolsPro plugin;

    public MuteCommand(ToolsPro plugin) {
        super("mute", Message.CMD_MUTE_DESCRIPTION, Message.CMD_MUTE_DESCRIPTION2.toString());
        this.setPermission("toolspro.commands.mute");
        this.plugin = plugin;
    }

    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        if (!sender.hasPermission(this.getPermission())) {
            sender.sendMessage(this.getPermissionMessage());
        } else {
            if (args.length < 2) {
                Message.CMD_MUTE_USAGE.print(sender, "prefix:&7[&aMute&7]", 'c');
            } else {
                Config mute = new Config(new File(this.plugin.getDataFolder(), "mute.yml"), Config.YAML);
                Player p = this.plugin.getServer().getPlayer(args[0]);
                if (!args[1].matches("^[1-9]+\\d*$")) {
                    Message.NOT_NUMBER.print(sender, "prefix:&7[&aMute&7]", 'c');
                } else {
                    Double times = this.plugin.round(Integer.parseInt(args[1]), 2);
                    Double timings;
                    if (args.length == 3) {
                        if (args[2].matches("(?i)seconds|sec|s")) {
                            timings = times;
                        } else if (args[2].matches("(?i)minutes|min|m")) {
                            timings = times * 60;
                        } else if (args[2].matches("(?i)hours|hour|h")) {
                            timings = times * 3600;
                        } else if (args[2].matches("(?i)days|day|d")) {
                            timings = times * 86400;
                        } else {
                            Message.NOT_TIME.print(sender, "prefix:&7[&aMute&7]", 'c');
                            return true;
                        }
                    } else {
                        timings = times * 60;
                    }
                    if (timings > (30 * 86400)) {
                        Message.CMD_MUTE_NO_MORE_30_DAY.print(sender, "prefix:[&aMute&7]", 'c');
                    } else {
                        mute.set(args[0].toLowerCase(), System.currentTimeMillis() + Math.round(timings * 1000d));
                        mute.save();
                        int seconds = NukkitMath.floorDouble(timings % 60);
                        int minutes = NukkitMath.floorDouble((timings % 3600) / 60);
                        int hours = NukkitMath.floorDouble(timings % (3600 * 24) / 3600);
                        int days = NukkitMath.floorDouble(timings / (3600 * 24));
                        String timemute = days + Message.DAYS.getText('c') +
                                hours + Message.HOURS.getText('c') +
                                minutes + Message.MINUTES.getText('c') +
                                seconds + Message.SECONDS.getText('c');
                        if (p instanceof Player) {
                            Message.CMD_MUTE_PLAYER_MESSAGE.print(p, "prefix:&7[&aMute&7]", 'c', timemute);
                        }
                        Message.CMD_MUTE_SENDER.print(sender, "prefix:&7[&aMute&7]", 'a', 'b', args[0].toLowerCase(), timemute);
                        this.plugin.info(sender, Message.CMD_MUTE_PLAYER_INFO.getText("prefix:&7[Mute]", sender.getName(), args[0].toLowerCase(), timemute));
                    }
                }
            }
        }
        return true;
    }
}

