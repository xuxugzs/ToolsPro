package ToolsPro.commands;

import ToolsPro.ToolsPro;
import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.entity.Effect;
import cn.nukkit.utils.TextFormat;

/**
 * Created by Pub4Game on 19.12.2015.
 */
public class SpeedCommand extends Command {

    private ToolsPro plugin;

    public SpeedCommand(ToolsPro plugin) {
        super("speed", "Позволяет управлять скоростью игрока.", "/speed или /speed <число>");
        this.setPermission("toolspro.commands.speed");
        this.plugin = plugin;
    }

    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        if (!sender.hasPermission(this.getPermission())) {
            sender.sendMessage(this.getPermissionMessage());
        }else if (sender instanceof Player) {
            if (args.length != 0){
                if (args[0].matches("^[1-9]+\\d*$")) {
                    ((Player) sender).addEffect(Effect.getEffect(Effect.SPEED).setAmplifier(Integer.parseInt(args[0])).setDuration(Integer.MAX_VALUE));
                    sender.sendMessage("&7[&aSpeed&7] &aВаша скорость была изменена на " + args[0]);
                }else{
                    sender.sendMessage(TextFormat.colorize("&7[&aSpeed&7] &cПожалуйста, введите верное число!"));
                }
            }else{
                ((Player) sender).removeEffect(Effect.SPEED);
                sender.sendMessage("&7[&aSpeed&7] &aВы успешно сбросили скорость на стандартную!");
            }
        }else{
            sender.sendMessage(TextFormat.colorize("&cПожалуйста, используйте эту команду только в игре!"));
        }
        return true;
    }
}
