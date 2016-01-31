package ToolsPro.commands;

import ToolsPro.ToolsPro;
import ToolsPro.util.Message;
import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;
import cn.nukkit.potion.Effect;

/**
 * Created by Pub4Game on 19.12.2015.
 */
public class SpeedCommand extends ToolsProCommand {

    private ToolsPro plugin;

    public SpeedCommand(ToolsPro plugin) {
        super("speed", Message.CMD_SPEED_DESCRIPTION, Message.CMD_SPEED_DESCRIPTION2.toString());
        this.setPermission("toolspro.commands.speed");
        this.plugin = plugin;
    }

    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        if (!sender.hasPermission(this.getPermission())) {
            sender.sendMessage(this.getPermissionMessage());
        } else {
            if (sender instanceof Player) {
                if (args.length != 0) {
                    if (args[0].matches("^[1-9]+\\d*$")) {
                        ((Player) sender).addEffect(Effect.getEffect(Effect.SPEED).setAmplifier(Integer.parseInt(args[0])).setDuration(Integer.MAX_VALUE));
                        Message.CMD_SPEED.print(sender, "prefix:&7[&aSpeed&7]", 'a', '9', args[0]);
                    } else {
                        Message.NOT_NUMBER.print(sender, "prefix:&7[&aSpeed&7]", 'c');
                    }
                } else {
                    ((Player) sender).removeEffect(Effect.SPEED);
                    Message.CMD_SPEED_NORMAL.print(sender, "prefix:&7[&aSpeed&7]", 'a');
                }
            } else {
                return Message.NEED_PLAYER.print(sender, "prefix:&7[&aSpeed&7]", 'c');
            }
        }
        return true;
    }
}
