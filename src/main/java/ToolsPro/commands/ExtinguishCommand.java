package ToolsPro.commands;

import ToolsPro.ToolsPro;
import ToolsPro.util.Message;
import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;

/**
 * Created by Pub4Game on 19.12.2015.
 */
public class ExtinguishCommand extends ToolProCommand {

    private ToolsPro plugin;

    public ExtinguishCommand(ToolsPro plugin) {
        super("extinguish", Message.CMD_EXT_DESC, Message.CMD_EXT_DESC2.toString());
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
                Message.CMD_EXT_SELFOK.print(sender,"prefix:&7[&aExtinguish&7]",'a');
                //sender.sendMessage(TextFormat.colorize("&7[&aExtinguish&7] &aВы успешно потушили себя!"));
            } else {
                Message.NEEDPLAYER.print(sender,'c');
                //sender.sendMessage(TextFormat.colorize("&cПожалуйста, используйте эту команду только в игре!"));
            }
        }else{
            Player p = this.plugin.getServer().getPlayer(args[0]);
            if (p instanceof Player){
                p.extinguish();
                //p.sendMessage(TextFormat.colorize("&7[&aExtinguish&7] &aВас успешно потушили!"));
                Message.CMD_EXT_OKYOU.print(sender,"prefix:&7[&aExtinguish&7]",'a');
                //sender.sendMessage(TextFormat.colorize("&7[&aExtinguish&7] &aВы успешно потуишили игрока &b" + p.getName() + "&a!"));
                Message.CMD_EXT_OK.print(sender,"prefix:&7[&aExtinguish&7]",'a','b',p.getName());
            }else{
                Message.UNKNOWNPLAYER.print(sender,"&7[&aExtinguish&7]",'c');
                //sender.sendMessage(TextFormat.colorize("&7[&aExtinguish&7] &cТакого игрока нет на сервере!"));
            }
        }
        return true;
    }
}
