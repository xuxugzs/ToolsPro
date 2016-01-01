package ToolsPro.commands;

import ToolsPro.ToolsPro;
import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.utils.TextFormat;

/**
 * Created by Pub4Game on 19.12.2015.
 */
public class ClearInventoryCommand extends Command {

    private ToolsPro plugin;

    public ClearInventoryCommand(ToolsPro plugin) {
        super("clearinventory", "Очищает инвентарь.", "/clearinventory или /clearinventory <ник>");
        this.setPermission("toolspro.commands.clearinventory");
        this.setAliases(new String[]{"ci"});
        this.plugin = plugin;
    }

    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        if (!sender.hasPermission(this.getPermission())) {
            sender.sendMessage(this.getPermissionMessage());
        }else if (args.length != 0) {
            if (sender.hasPermission("toolspro.clearinventory.other")){
                Player p = this.plugin.getServer().getPlayer(args[0]);
                if (p instanceof Player){
                    if (p.getGamemode() != 0){
                        sender.sendMessage(TextFormat.colorize("&7[&aClearInv&7] &cИгровой режим игрока &b" + p.getName() + " &cне выживание!"));
                    } else {
                        p.getInventory().clearAll();
                        sender.sendMessage(TextFormat.colorize("&7[&aClearInv&7] &aИнвентарь &b" + p.getName() + " &aочищен!"));
                    }
                }else{
                    sender.sendMessage(TextFormat.colorize("&7[&aClearInv&7] &cТакого игрока нет на сервере"));
                }
            }else{
                sender.sendMessage(this.getPermissionMessage());
            }
        }else if (sender instanceof Player){
            if(((Player) sender).getGamemode() != 0){
                sender.sendMessage(TextFormat.colorize("&7[&aClearInv&7] &cВаш игрокой режим не выживание!"));
            }else{
                ((Player) sender).getInventory().clearAll();
                sender.sendMessage(TextFormat.colorize("&7[&aClearInv&7] &aВаш инвентарь был успешно очищен!"));
            }
        }else{
            sender.sendMessage(TextFormat.colorize("&7[&aClearInv&7] &cПожалуйста, используйте эту команду только в игре!"));
        }
        return true;
    }
}