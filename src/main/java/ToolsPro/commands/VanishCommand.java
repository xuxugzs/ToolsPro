package ToolsPro.commands;

import ToolsPro.ToolsPro;
import ToolsPro.util.Message;
import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;
import cn.nukkit.entity.Effect;
import cn.nukkit.inventory.Inventory;
import cn.nukkit.utils.TextFormat;

/**
 * Created by Pub4Game on 19.12.2015.
 */
public class VanishCommand extends ToolsProCommand {

    private ToolsPro plugin;

    public VanishCommand(ToolsPro plugin) {
        super("vanish", Message.CMD_VANISH_DESCRIPTION, Message.CMD_VANISH_DESCRIPTION2.toString());
        this.setPermission("toolspro.commands.vanish");
        this.setAliases(new String[]{"v"});
        this.plugin = plugin;
    }

    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        if (!sender.hasPermission(this.getPermission())) {
            sender.sendMessage(this.getPermissionMessage());
        } else if (args.length != 0) {
            if (sender.hasPermission("toolspro.vanish.other")) {
                Player p = this.plugin.getServer().getPlayer(args[0]);
                if (p instanceof Player) {
                    if (this.plugin.isHide(p.getName())) {
                        this.plugin.removeHide(p.getName());
                        p.showPlayer(this.plugin.getServer().getPlayer(p.getName()));
                        sender.sendMessage(TextFormat.colorize("&7[&aVanish&7] &aИгрок &b" + p.getName() + " &aснова видимый!"));
                        p.sendMessage(TextFormat.colorize("&7[&aVanish&7] &aВы стали видимыми!"));
                        this.plugin.info(p, "&7[&aVanish&7] " + sender.getName() + " выключил невидимость " + p.getName() + "!");
                    } else {
                        this.plugin.setHide(p.getName());
                        p.hidePlayer(this.plugin.getServer().getPlayer(p.getName()));
                        sender.sendMessage(TextFormat.colorize("&7[&aVanish&7] &aИгрок &b" + p.getName() + " &aневидимый!"));
                        p.sendMessage(TextFormat.colorize("&7[&aVanish&7] &aВы стали невидимыми!"));
                        this.plugin.info(p, "&7[Vanish] " + sender.getName() + " включил невидимость " + p.getName() + "!");
                    }
                } else {
                    sender.sendMessage(TextFormat.colorize("&7[&aVanish&7] &cТакого игрока нет на сервере!"));
                }
            } else {
                sender.sendMessage(TextFormat.colorize("&7[&aVanish&7] &cУ Вас нет прав, чтобы делать игроков невидимыми!"));
            }
        } else if (sender instanceof Player) {
            if (this.plugin.isHide(sender.getName())) {
                this.plugin.removeHide(sender.getName());
                ((Player) sender).showPlayer(this.plugin.getServer().getPlayer(sender.getName()));
                sender.sendMessage(TextFormat.colorize("&7[&aVanish&7] &aВы успешно выключили невидимость!"));
                this.plugin.info(sender, "&7[Vanish] " + sender.getName() + " выключил невидимость!");
            } else {
                this.plugin.setHide(sender.getName());
                ((Player) sender).hidePlayer(this.plugin.getServer().getPlayer(sender.getName()));
                sender.sendMessage(TextFormat.colorize("&7[&aVanish&7] &aВы успешно включили невидимость!"));
                this.plugin.info(sender, "&7[Vanish] " + sender.getName() + " включил невидимость!");
            }
        } else {
            Message.NEED_PLAYER.print(sender, "prefix:&7[&aVanish&7]", 'c');
        }
        return true;
    }
}

