package ToolsPro.commands;

import ToolsPro.ToolsPro;
import ToolsPro.util.Message;
import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;
import cn.nukkit.event.player.PlayerChatEvent;

/**
 * Created by Pub4Game on 03.02.2016.
 */
public class SudoCommand extends Commands {

    private ToolsPro plugin;

    public SudoCommand(ToolsPro plugin) {
        super("sudo", Message.CMD_SUDO_DESCRIPTION, Message.CMD_SUDO_DESCRIPTION2.toString());
        this.setPermission("toolspro.commands.sudo.use");
        this.plugin = plugin;
    }

    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        if (!sender.hasPermission(this.getPermission())) {
            sender.sendMessage(Message.YOU_DONT_HAVE_PERMISSION.getText('c'));
        } else {
            if (args.length >= 2) {
                Player p = this.plugin.getServer().getPlayer(args[0]);
                if (p != null) {
                    if (p.hasPermission("toolspro.commands.sudo.exempt")) {
                            String sudo = "";
                            for (int i = 1; i < args.length; i++) {
                                sudo += args[i] + " ";
                            }
                            if (sudo.length() > 0) {
                                sudo = sudo.substring(0, sudo.length() - 1);
                            }
                            if (sudo.substring(0, 2).equals("c:")) {
                                Message.CMD_SUDO_SEND_MESSAGE.print(sender, "prefix:&7[&aSudo&7]", 'a', 'b', p.getDisplayName());
                                PlayerChatEvent ev = new PlayerChatEvent(p, sudo.substring(2));
                                this.plugin.getServer().getPluginManager().callEvent(ev);
                                if (!ev.isCancelled()) {
                                    this.plugin.getServer().broadcastMessage(String.format(ev.getFormat(), ev.getPlayer().getDisplayName(), ev.getMessage()), ev.getRecipients());
                                }
                            } else {
                                Message.CMD_SUDO_USE_COMMAND.print(sender, "prefix:&7[&aSudo&7]", 'a', 'b', p.getDisplayName());
                                this.plugin.getServer().dispatchCommand(p, sudo);
                            }
                        } else {
                            Message.CMD_SUDO_CANNOT_BE_SUDOED.print(sender, "prefix:&7[&aSudo&7]", 'c', 'b', p.getName());
                        }
                    } else {
                        Message.UNKNOWN_PLAYER.print(sender, "prefix:&7[&aSudo&7]", 'c');
                    }
                } else {
                   return Message.CMD_SUDO_USAGE.print(sender, "prefix:&7[&aSudo&7]", 'c');
                }
            }
        return true;
    }
}
