package ToolsPro.commands;

import ToolsPro.ToolsPro;
import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.entity.Effect;
import cn.nukkit.utils.TextFormat;

/**
 * Created by Pub4Game on 19.12.2015.
 */
public class ExtinguishCommand extends Command {

    private ToolsPro plugin;

    public ExtinguishCommand(ToolsPro plugin) {
        super("extinguish", "Позволяет управлять скоростью персонажа.", "/extinguish или /extinguish <ник>");
        this.setPermission("toolspro.commands.extinguish");
        this.setAliases(new String[] {"ext"});
        this.plugin = plugin;
    }

    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        if (!sender.hasPermission(this.getPermission())) {
            sender.sendMessage(this.getPermissionMessage());
        }else if(args.length == 0) {
            if (sender instanceof Player) {
                ((Player) sender).extinguish();
                sender.sendMessage(TextFormat.colorize("&7[&aExtinguish&7] &aВы успешно потушили себя!"));
            } else {
                sender.sendMessage(TextFormat.colorize("&cПожалуйста, используйте эту команду только в игре!"));
            }
        }else{
            Player p = this.plugin.getServer().getPlayer(args[0]);
            if (p instanceof Player){
                p.extinguish();
                p.sendMessage(TextFormat.colorize("&7[&aExtinguish&7] &aВас успешно потушили!"));
                sender.sendMessage(TextFormat.colorize("&7[&aExtinguish&7] &aВы успешно потуишили игрока &b" + p.getName() + "&a!"));
            }else{
                sender.sendMessage(TextFormat.colorize("&7[&aExtinguish&7] &cТакого игрока нет на сервере!"));
            }
        }
        return true;
    }
}
