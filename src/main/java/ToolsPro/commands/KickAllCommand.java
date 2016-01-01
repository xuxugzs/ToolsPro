package ToolsPro.commands;

import ToolsPro.ToolsPro;
import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.utils.TextFormat;

/**
 * Created by Pub4Game on 19.12.2015.
 */
public class KickAllCommand extends Command {

    private ToolsPro plugin;

    public KickAllCommand(ToolsPro plugin) {
        super("kickall", "Кикает всех игроков с сервера.", "/kickall");
        this.setPermission("toolspro.commands.kickall");
        this.plugin = plugin;
    }

    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        int count = this.plugin.getServer().getOnlinePlayers().size();
        if (!sender.hasPermission(this.getPermission())) {
            sender.sendMessage(this.getPermissionMessage());
        }else if ((count < 1) || (sender instanceof Player && count < 2)){
            sender.sendMessage(TextFormat.colorize("&7[&aKickAll&7] &cНа сервере нет ни одного игрока!"));
        }else{
            for (Player player : this.plugin.getServer().getOnlinePlayers().values()){
                if (player.equals(sender)) continue;
                String reason = TextFormat.colorize(args.length == 0 ? "&cВы были кикнуты с сервера!" : this.plugin.join (args));
                player.kick(reason, false);
            }
            sender.sendMessage(TextFormat.colorize("&7[&aKickAll&7] &aВсе игроки были кикнуты с сервера!"));
        }
        return true;
    }
}
