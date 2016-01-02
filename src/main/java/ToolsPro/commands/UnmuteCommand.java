package ToolsPro.commands;

import ToolsPro.ToolsPro;
import ToolsPro.util.Message;
import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;
import cn.nukkit.utils.Config;
import cn.nukkit.utils.TextFormat;

import java.io.File;

/**
 * Created by Pub4Game on 21.12.2015.
 */
public class UnmuteCommand extends ToolsProCommand {

    private ToolsPro plugin;

    public UnmuteCommand(ToolsPro plugin) {
        super("unmute", Message.CMD_UNMUTE_DESCRIPTION, Message.CMD_UNMUTE_DESCRIPTION2.toString());
        this.setPermission("toolspro.commands.unmute");
        this.plugin = plugin;
    }

    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        if (!sender.hasPermission(this.getPermission())) {
            sender.sendMessage(this.getPermissionMessage());
        } else if (args.length != 0) {
            Config mute = new Config(new File(this.plugin.getDataFolder(), "mute.yml"), Config.YAML);
            Player p = this.plugin.getServer().getPlayer(args[0]);
            if (!mute.exists(args[0].toLowerCase())) {
                sender.sendMessage(TextFormat.colorize("&7[&aMute&7] &cУ игрока &b" + p.getName() + " &cнет мута!"));
            } else {
                mute.remove(args[0].toLowerCase());
                mute.save();
                if (p instanceof Player) {
                    p.sendMessage(TextFormat.colorize("&7[&aMute&7] &aВас размутили в чате и теперь можете писать в чат"));
                }
                sender.sendMessage(TextFormat.colorize("&7[&aMute&7] &aВы успешно размутили игрока &b" + p.getName()));
                this.plugin.info(p, "&7[Mute] " + sender.getName() + " размутил игрока " + p.getName() + "!");
            }
        } else {
            sender.sendMessage(TextFormat.colorize("&7[&aMute&7] &cИспользуйте /unmute <ник>"));
        }
        return true;
    }
}