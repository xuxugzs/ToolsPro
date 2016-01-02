package ToolsPro.commands;

import ToolsPro.ToolsPro;
import ToolsPro.util.Message;
import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;
import cn.nukkit.event.entity.EntityDamageEvent;

/**
 * Created by Pub4Game on 19.12.2015.
 */
public class SuicideCommand extends ToolsProCommand {

    private ToolsPro plugin;

    public SuicideCommand(ToolsPro plugin) {
        super("suicide", Message.CMD_SUICIDE_DESCRIPTION, "/suicide");
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
                Message.CMD_SUICIDE_MESSAGE.print(sender, "prefix:&7[&aSuicide&7]", 'a');
            } else {
                Message.NEED_PLAYER.print(sender, "prefix:&7[&aSuicide&7]", 'c');
            }
        }
        return true;
    }
}
