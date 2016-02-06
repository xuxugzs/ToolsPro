package ToolsPro.commands;

import ToolsPro.ToolsPro;
import ToolsPro.util.Message;
import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;

/**
 * Created by Pub4Game on 19.12.2015.
 */
public class NukeCommand extends ToolsProCommand {

    private ToolsPro plugin;

    public NukeCommand(ToolsPro plugin) {
        super("nuke", Message.CMD_SPEED_DESCRIPTION, Message.CMD_SPEED_DESCRIPTION2.toString());
        this.setPermission("toolspro.commands.nuke");
        this.plugin = plugin;
    }

    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        if (!sender.hasPermission(this.getPermission())) {
            sender.sendMessage(Message.YOU_DONT_HAVE_PERMISSION.getText('c'));
        } else {
            Player player;
            if (args.length != 0){
                player = this.plugin.getServer().getPlayer(args[0]);
                if (player.hasPermission("toolspro.nuke.other")) {
                    if (player == null){
                        return Message.UNKNOWN_PLAYER.print(sender, "prefix:&7[&aNuke&7]", 'c');
                    }
                } else {
                    sender.sendMessage(Message.YOU_DONT_HAVE_PERMISSION.getText('c'));
                }
            } else {
                player = this.plugin.getServer().getPlayer(sender.getName());
                if (player == null){
                    return Message.NEED_PLAYER.print(sender, "prefix:&7[&aNuke&7]", 'c');
                }
            }
            this.plugin.nuke(player);
        }
        return true;
    }
}
