package ToolsPro.commands;

import ToolsPro.ToolsPro;
import ToolsPro.util.Message;
import cn.nukkit.command.CommandSender;
import cn.nukkit.utils.TextFormat;

/**
 * Created by Pub4Game on 06.02.2015.
 */
public class ToolsProCommand extends Commands {

    private ToolsPro plugin;

    public ToolsProCommand(ToolsPro plugin) {
        super("toolspro", Message.CMD_TOOLSPRO_DESCRIPTION, "/toolspro");
        this.setPermission("toolspro.commands.toolspro");
        this.plugin = plugin;
    }

    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        if (!sender.hasPermission(this.getPermission())) {
            Message.YOU_DONT_HAVE_PERMISSION.print(sender, 'c');
        } else {
            sender.sendMessage(TextFormat.colorize("&7[&aInfo&7] &a" + this.plugin.getToolsProName() + " &bv" + this.plugin.getToolsProVersion()));
            sender.sendMessage(TextFormat.colorize("&eAuthor: &c" + this.plugin.getToolsProAuthors()));
            sender.sendMessage(TextFormat.colorize("&dWebSite: &7" + this.plugin.getToolsProWebSite()));
            sender.sendMessage(TextFormat.colorize("&9VK: &bhttps://vk.com/goganselot"));
        }
        return true;
    }
}
