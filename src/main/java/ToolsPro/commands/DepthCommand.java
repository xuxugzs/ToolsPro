package ToolsPro.commands;

import ToolsPro.ToolsPro;
import ToolsPro.util.Message;
import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;

/**
 * Created by Pub4Game on 19.12.2015.
 */
public class DepthCommand extends ToolsProCommand {

    private ToolsPro plugin;

    public DepthCommand(ToolsPro plugin) {
        super("depth", Message.CMD_DEPTH_DESCRIPTION, "/depth");
        this.setPermission("toolspro.commands.depth");
        this.plugin = plugin;
    }

    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        if (!sender.hasPermission(this.getPermission())) {
            sender.sendMessage(Message.YOU_DONT_HAVE_PERMISSION.getText('c'));
        } else {
            if (sender instanceof Player) {
                int pos = ((Player) sender).getFloorY() - 63;
                if (pos > 0) Message.CMD_DEPTH_ABOVE.print(sender, Math.abs(pos));
                else Message.CMD_DEPTH_BELOW.print(sender, Math.abs(pos));
            } else {
               return Message.NEED_PLAYER.print(sender, "prefix:&7[&aDepth&7]", 'c');
            }
        }
        return true;
    }
}
