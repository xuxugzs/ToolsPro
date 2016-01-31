package ToolsPro.commands;

import ToolsPro.ToolsPro;
import ToolsPro.util.Message;
import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;

/**
 * Created by Pub4Game on 19.12.2015.
 */
public class ExtinguishCommand extends ToolsProCommand {

    private ToolsPro plugin;

    public ExtinguishCommand(ToolsPro plugin) {
        super("extinguish", Message.CMD_EXTINGUISH_DESCRIPTION, Message.CMD_EXTINGUISH_DESCRIPTION2.toString());
        this.setPermission("toolspro.commands.extinguish");
        this.setAliases(new String[] {"ext"});
        this.plugin = plugin;
    }

    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        if (!sender.hasPermission(this.getPermission())) {
            sender.sendMessage(Message.YOU_DONT_HAVE_PERMISSION.getText('c'));
        } else {
            if (args.length != 0) {
                Player p = this.plugin.getServer().getPlayer(args[0]);
                if (p != null) {
                    p.extinguish();
                    Message.CMD_EXTINGUISH_PLAYER.print(sender, "prefix:&7[&aExtinguish&7]", 'a', 'b', p.getName());
                    Message.CMD_EXTINGUISH_PLAYER_MESSAGE.print(p, "prefix:&7[&aExtinguish&7]", 'a');
                    this.plugin.info(sender, Message.CMD_EXTINGUISH_PLAYER_INFO.getText("prefix:&7[Extinguish]", sender.getName(), p.getName()));
                } else {
                    Message.UNKNOWN_PLAYER.print(sender, "prefix:&7[&aExtinguish&7]", 'c');
                }
            } else {
                if (sender instanceof Player) {
                    ((Player) sender).extinguish();
                    Message.CMD_EXTINGUISH_SENDER.print(sender, "prefix:&7[&aExtinguish&7]", 'a');
                    this.plugin.info(sender, Message.CMD_EXTINGUISH_SENDER_INFO.getText("prefix:&7[Extinguish]"));
                } else {
                    return Message.NEED_PLAYER.print(sender, "prefix:&7[&aExtinguish&7]", 'c');
                }
            }
        }
        return true;
    }
}
