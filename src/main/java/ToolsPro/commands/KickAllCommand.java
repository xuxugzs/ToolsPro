package ToolsPro.commands;

import ToolsPro.ToolsPro;
import ToolsPro.util.Message;
import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;
import cn.nukkit.utils.TextFormat;

/**
 * Created by Pub4Game on 19.12.2015.
 */
public class KickAllCommand extends ToolsProCommand {

    private ToolsPro plugin;

    public KickAllCommand(ToolsPro plugin) {
        super("kickall", Message.CMD_KICKALL_DESCRIPTION, Message.CMD_KICKALL_DESCRIPTION2.toString());
        this.setPermission("toolspro.commands.kickall");
        this.plugin = plugin;
    }

    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        int count = this.plugin.getServer().getOnlinePlayers().size();
        if (!sender.hasPermission(this.getPermission())) {
            sender.sendMessage(this.getPermissionMessage());
        }else if ((count < 1) || (sender instanceof Player && count < 2)){
            Message.CMD_KICKALL_NO_PLAYERS.print(sender, "prefix:&7[&aKickAll&7]", 'c');
        }else{
            for (Player player : this.plugin.getServer().getOnlinePlayers().values()){
                if (player.equals(sender)) continue;
                String reason = args.length == 0 ? Message.CMD_KICKALL_NO_REASON.toString() : TextFormat.colorize(this.plugin.join (args));
                player.kick(reason, false);
            }
            Message.CMD_KICKALL_ALL_SUCCESSFULLY_KICKED.print(sender, "prefix:&7[&aKickAll&7]", 'c');
        }
        return true;
    }
}
