package ToolsPro.commands;

import ToolsPro.ToolsPro;
import ToolsPro.util.Message;
import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;
import cn.nukkit.math.NukkitMath;
import cn.nukkit.utils.Config;
import cn.nukkit.utils.TextFormat;

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
        }else {
            if (args.length < 2){
                sender.sendMessage(TextFormat.colorize("&7[&aMute&7] &cИспользуйте /mute <ник> <время> <seconds|minutes|hours|days>"));
            }else{
                Config mute = new Config(new File(this.plugin.getDataFolder(), "mute.yml"), Config.YAML);
                Player p = this.plugin.getServer().getPlayer(args[0]);
                if (!args[1].matches("^[1-9]+\\d*$")){
                    sender.sendMessage(TextFormat.colorize("&7[&aMute&7] &cПожалуйста, введите верное число!"));
                }else{
                    Double times = this.plugin.round(Integer.parseInt(args[1]), 2);
                    Double timings;
                    if (args.length == 3){
                        if (args[2].equalsIgnoreCase("seconds")){
                            timings = times;
                        }else if (args[2].equalsIgnoreCase("minutes")){
                            timings = times * 60;
                        }else if (args[2].equalsIgnoreCase("hours")){
                            timings = times * 3600;
                        }else if (args[2].equalsIgnoreCase("days")){
                            timings = times * 86400;
                        }else{
                            sender.sendMessage(TextFormat.colorize("&7[&aMute&7] &cУкажите верное значение времени!"));
                            return true;
                        }
                    }else{
                        timings = times * 60;
                    }
                    if (timings > (30 * 86400)){
                        sender.sendMessage(TextFormat.colorize("&7[&aMute&7] &cВы не можете замутить игрока больше чем на 30 дней!"));
                    }else{
                        mute.set(args[0].toLowerCase(), System.currentTimeMillis() + Math.round(timings * 1000d));
                        mute.save();
                        int seconds = NukkitMath.floorDouble(timings % 60);
                        int minutes = NukkitMath.floorDouble((timings % 3600) / 60);
                        int hours = NukkitMath.floorDouble(timings % (3600 * 24) / 3600);
                        int days = NukkitMath.floorDouble(timings / (3600 * 24));
                        String timemute = days + " days " +
                                hours + " hours " +
                                minutes + " minutes " +
                                seconds + " seconds";
                        if (p instanceof Player){
                            p.sendMessage(TextFormat.colorize("&7[&aMute&7] &cВы получили мут на " + timemute + " и теперь не можете писать в чат"));
                        }
                        sender.sendMessage(TextFormat.colorize("&7[&aMute&7] &b" + p.getName() + " &cбыл замучен на " + timemute));
                        this.plugin.info(p, "&7[Mute] " + sender.getName() + " замутил игрока " + p.getName() + " на " + timemute + "!");
                    }
                }
            }
        }
        return true;
    }
}

