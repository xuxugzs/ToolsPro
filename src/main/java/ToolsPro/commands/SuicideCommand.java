package ToolsPro.commands;

import ToolsPro.ToolsPro;
import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.event.entity.EntityDamageEvent;
import cn.nukkit.utils.TextFormat;

/**
 * Created by Pub4Game on 19.12.2015.
 */
public class SuicideCommand extends Command {

    private ToolsPro plugin;

    public SuicideCommand(ToolsPro plugin) {
        super("suicide", "Вы закончите жизнь самоубийством.", "/suicide");
        this.setPermission("toolspro.commands.suicide");
        this.plugin = plugin;
    }

    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        if (!sender.hasPermission(this.getPermission())) {
            sender.sendMessage(this.getPermissionMessage());
        } else {
            if (sender instanceof Player) {
                EntityDamageEvent ev = new EntityDamageEvent(((Player) sender), EntityDamageEvent.CAUSE_SUICIDE, ((Player) sender).getHealth());
                sender.getServer().getPluginManager().callEvent(ev);
                if (ev.isCancelled()) {
                    return true;
                }
                ((Player) sender).setLastDamageCause(ev);
                ((Player) sender).setHealth(0);
                sender.sendMessage(TextFormat.colorize("&7[&aSuicide&7] &aВы покончили жизнь самоубийством!"));
            }else{
                sender.sendMessage(TextFormat.colorize("&cПожалуйста, используйте эту команду в игре!"));
            }
        }
        return true;
    }
}
