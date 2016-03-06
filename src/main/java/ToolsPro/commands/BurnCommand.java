package ToolsPro.commands;

import ToolsPro.ToolsPro;
import ToolsPro.util.Message;
import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;

/**
 * Created by Pub4Game on 19.12.2015.
 */
public class BurnCommand extends Commands {

    private ToolsPro plugin;

    public BurnCommand(ToolsPro plugin) {
        super("burn", Message.CMD_BURN_DESCRIPTION, Message.CMD_BURN_DESCRIPTION2.toString());
        this.setPermission("toolspro.commands.burn");
        this.plugin = plugin;
    }

    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        if (!sender.hasPermission(this.getPermission())) {
            Message.YOU_DONT_HAVE_PERMISSION.print(sender, 'c');
        } else {
            if (args.length == 2) {
                Player p = this.plugin.getServer().getPlayer(args[0]);
                if (p == null) {
                    p = this.plugin.sortedListPlayers(args[0]);
                    if (p == null) {
                        Message.UNKNOWN_PLAYER.print(sender, "prefix:&7[&aBurn&7]", 'c');
                        return true;
                    }
                }
                if (args[1].matches("^[1-9]+\\d*$")) {
                    p.setOnFire(Integer.parseInt(args[1]));
                    Message.CMD_BURN_PLAYER.print(sender, 'a', 'b', p.getName(), "prefix:&7[&aBurn&7]");
                    this.plugin.info(sender, Message.CMD_BURN_PLAYER_INFO.getText("prefix:&7[Burn]", '7', '7', sender.getName(), p.getName()));
                } else {
                    Message.NOT_NUMBER.print(sender, "prefix:&7[&aBurn&7]", 'c');
                }
            } else {
                Message.CMD_BURN_USAGE.print(sender, "prefix:&7[&aBurn&7]", 'c');
            }
        }
        return true;
    }
}
