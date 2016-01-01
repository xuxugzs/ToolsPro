package ToolsPro.commands;

import ToolsPro.ToolsPro;
import ToolsPro.util.Message;
import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;

/**
 * Created by Pub4Game on 19.12.2015.
 */
public class FlyCommand extends ToolsProCommand {

    private ToolsPro plugin;

    public FlyCommand(ToolsPro plugin) {
        super("fly", Message.CMD_FLY_DESC, Message.CMD_FLY_DESC2.toString());
        this.setPermission("toolspro.commands.fly");
        this.plugin = plugin;
    }

    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        if (!sender.hasPermission(this.getPermission())) {
            sender.sendMessage(this.getPermissionMessage());
        }else if (args.length != 0) {
            if (sender.hasPermission("toolspro.fly.other")) {
                Player p = this.plugin.getServer().getPlayer(args[0]);
                if (p!=null) {
                    if (p.getGamemode() != 0) {
                        Message.YOU_NOT_SURV.print(sender,"prefix:&7[&aFly&7]",'c');
                        //sender.sendMessage(TextFormat.colorize("&7[&aFly&7] &cВаш игровой режим не выживание!"));
                    } else if (p.getAllowFlight()) {
                        p.setAllowFlight(false);
                        Message.CMD_FLY_PLR_DISABLED.print(sender,"prefix:&7[&aFly&7]",'a','b',p.getName());
                        //sender.sendMessage(TextFormat.colorize("&7[&aFly&7] &aФлай игроку &b" + p.getName() + " &aуспешно выключен!"));
                        Message.CMD_FLY_PLRY_DISABLED.print(sender,"prefix:&7[&aFly&7]",'a');
                        //p.sendMessage(TextFormat.colorize("&7[&aFly&7] &aВам выключили режим полета!"));
                    } else {
                        p.setAllowFlight(true);
                        Message.CMD_FLY_PLR_ENABLED.print(sender,"prefix:&7[&aFly&7]",'a','b',p.getName());
                        //sender.sendMessage(TextFormat.colorize("&7[&aFly&7] &aФлай игроку &b" + p.getName() + " &aуспешно включен!"));
                        //p.sendMessage(TextFormat.colorize("&7[&aFly&7] &aВам включили режим полета!"));
                        Message.CMD_FLY_PLRY_ENABLED.print(sender,"prefix:&7[&aFly&7]",'a');
                    }
                }else{
                    Message.NEEDPLAYER.print(sender,"prefix:&7[&aFly&7]",'c');
                    //sender.sendMessage(TextFormat.colorize("&7[&aFly&7] &cТакого игрока нет на сервере!"));
                }
            }else{
                sender.sendMessage(this.getPermissionMessage());
            }
        }else if (sender instanceof Player) {
            if (((Player) sender).getGamemode() != 0) {
                Message.YOU_NOT_SURV.print(sender,"prefix:&7[&aFly&7]",'c');
                //sender.sendMessage(TextFormat.colorize("&7[&aFly&7] &cВаш игровой режим не выживание!"));
            }else if (((Player) sender).getAllowFlight()) {
                ((Player) sender).setAllowFlight(false);
                Message.CMD_FLY_DISABLED.print(sender,"prefix:&7[&aFly&7]",'a');
                //sender.sendMessage(TextFormat.colorize("&7[&aFly&7] &aВы успешно выключили режим полета!"));
            }else{
                ((Player) sender).setAllowFlight(true);
                Message.CMD_FLY_ENABLED.print(sender,"prefix:&7[&aFly&7]",'a');
                //sender.sendMessage(TextFormat.colorize("&7[&aFly&7] &aВы успешно включили режим полета!"));
            }
        }else{
            Message.NEEDPLAYER.print(sender,"prefix:&7[&aFly&7]",'c');
            //sender.sendMessage(TextFormat.colorize("&7[&aFly&7] &cПожалуйста, используйте эту команду в игре!"));
        }
        return true;
    }
}

