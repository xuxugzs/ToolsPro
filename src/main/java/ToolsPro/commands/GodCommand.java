package ToolsPro.commands;

import ToolsPro.ToolsPro;
import ToolsPro.util.Message;
import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;
import cn.nukkit.utils.TextFormat;

/**
 * Created by Pub4Game on 19.12.2015.
 */
public class GodCommand extends ToolsProCommand {

    private ToolsPro plugin;

    public GodCommand(ToolsPro plugin) {
        super("god", Message.CMD_GOD_DESCRIPTION, Message.CMD_GOD_DESCRIPTION2.toString());
        this.setPermission("toolspro.commands.god");
        this.plugin = plugin;
    }

    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        if (!sender.hasPermission(this.getPermission())) {
            sender.sendMessage(this.getPermissionMessage());
        } else if (args.length != 0) {
            if (sender.hasPermission("toolspro.god.other")) {
                Player p = this.plugin.getServer().getPlayer(args[0]);
                if (p != null) {
                    if (this.plugin.isGodMode(args[0])) {
                        this.plugin.removeGodMode(args[0]);
                        sender.sendMessage(TextFormat.colorize("&7[&aGodMode&7] &aИгрок &b" + p.getName() + " &aснова смертный!"));
                        p.sendMessage(TextFormat.colorize("&7[&aGodMode&7] &aВы стали смертным!"));
                    } else if (p.getGamemode() != 0) {
                        sender.sendMessage(TextFormat.colorize("&7[&aGodMode&7] &cИгровой режим игрка &b" + p.getName() + " &cне выживание!"));
                    }else{
                        this.plugin.setGodMode(args[0]);
                        sender.sendMessage(TextFormat.colorize("&7[&aGodMode&7] &aИгрок &b" + p.getName() + " &aбессмертный!"));
                        p.sendMessage(TextFormat.colorize("&7[&aGodMode&7] &aВы стали бессметным!"));
                    }
                } else {
                    Message.UNKNOWN_PLAYER.print(sender, "prefix:&7[&aGodMode&7]", 'c');
                }
            } else {
                sender.sendMessage(this.getPermissionMessage());
            }
        } else if (sender instanceof Player) {
            if (this.plugin.isGodMode(sender.getName())) {
                this.plugin.removeGodMode(sender.getName());
                sender.sendMessage(TextFormat.colorize("&7[&aGodMode&7] &aВы успешно выключили режим бога!"));
            } else if (((Player) sender).getGamemode() != 0) {
                sender.sendMessage(TextFormat.colorize("&7[&aGodMode&7] &cВаш игровой режим не выживание!"));
            } else {
                this.plugin.setGodMode(sender.getName());
                sender.sendMessage(TextFormat.colorize("&7[&aGodMode&7] &aВы успешно включили режим бога!"));
            }
        } else {
            Message.NEED_PLAYER.print(sender, "prefix:&7[&aGodMode&7]", 'c');
        }
        return true;
    }
}
