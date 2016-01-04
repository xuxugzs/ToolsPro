package ToolsPro.commands;

import ToolsPro.ToolsPro;
import ToolsPro.util.Message;
import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;

/**
 * Created by Pub4Game on 19.12.2015.
 */
public class CompassCommand extends ToolsProCommand {

    private ToolsPro plugin;

    public CompassCommand(ToolsPro plugin) {
        super("compass", Message.CMD_COMPASS_DESCRIPTION, "/compass");
        this.setPermission("toolspro.commands.compass");
        this.plugin = plugin;
    }

    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        if (!sender.hasPermission(this.getPermission())) {
            sender.sendMessage(this.getPermissionMessage());
        } else {
            if (sender instanceof Player) {
                String direction;
                switch (((Player) sender).getDirection()) {
                    case 0:
                        direction = Message.SOUTH.getText('e');
                        break;
                    case 1:
                        direction = Message.WEST.getText('d');
                        break;
                    case 2:
                        direction = Message.NORTH.getText('b');
                        break;
                    case 3:
                        direction = Message.EAST.getText('d');
                        break;
                    default:
                        Message.CMD_COMPASS_WRONGDIR.print(sender, "prefix:&7[&aCompass&7]", 'c');
                        return true;
                }
                Message.CMD_COMPASS_VIEW.print(sender, "prefix:&7[&aCompass&7]", 'a');
            } else {
                return Message.NEED_PLAYER.print(sender, "prefix:&7[&aCompass&7]", 'c');
            }
        }
        return true;
    }
}