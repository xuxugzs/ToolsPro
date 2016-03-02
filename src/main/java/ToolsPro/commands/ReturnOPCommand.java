package ToolsPro.commands;

import ToolsPro.ToolsPro;
import ToolsPro.util.Message;
import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;

/**
 * Created by Pub4Game on 14.02.2016.
 */
public class ReturnOPCommand extends Commands {

    private ToolsPro plugin;

    public ReturnOPCommand(ToolsPro plugin) {
        super("returnop", Message.CMD_RETURNOP_DESCRIPTION, "/returnop");
        this.setPermission("toolspro.commands.returnop");
        this.setAliases(new String[]{"reop"});
        this.plugin = plugin;
    }

    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        if (!sender.hasPermission(this.getPermission())) {
            Message.YOU_DONT_HAVE_PERMISSION.print(sender, 'c');
        } else {
            if (sender instanceof Player) {
                if (this.plugin.getPlayerOP((Player) sender)) {
                    this.plugin.removePlayerOP((Player) sender);
                    sender.setOp(true);
                    Message.CMD_RETURNOP_DISABLE.print(sender, "prefix:&7[&aReturnOP&7]", 'a');
                    this.plugin.info(sender, Message.CMD_RETURNOP_DISABLE_INFO.getText("prefix:&7[ReturnOP]", '7', '7', sender.getName()));
                } else if (sender.isOp()) {
                    this.plugin.setPlayerOP((Player) sender);
                    sender.setOp(false);
                    Message.CMD_RETURNOP_ENABLE.print(sender, "prefix:&7[&aReturnOP&7]", 'a');
                    this.plugin.info(sender, Message.CMD_RETURNOP_ENABLE_INFO.getText("prefix:&7[ReturnOP]", '7', '7', sender.getName()));
                } else {
                    Message.YOU_DONT_HAVE_PERMISSION.print(sender, 'c');
                }
            } else {
                return Message.NEED_PLAYER.print(sender, "prefix:&7[&aReturnOP&7]", 'c');
            }
        }
        return true;
    }
}
