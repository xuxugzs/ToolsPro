package ToolsPro.commands;

import ToolsPro.ToolsPro;
import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.utils.TextFormat;

/**
 * Created by Pub4Game on 19.12.2015.
 */
public class SaveInvCommand extends Command {

    private ToolsPro plugin;

    public SaveInvCommand(ToolsPro plugin) {
        super("saveinv", "Включает/выключает сохранение инвентаря.", "/saveinv или /saveinv <ник>");
        this.setPermission("toolspro.commands.saveinv");
        this.plugin = plugin;
    }

    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        if (!sender.hasPermission(this.getPermission())) {
            sender.sendMessage(this.getPermissionMessage());
        }else if (args.length != 0) {
            if (sender.hasPermission("toolspro.saveinv.other")){
                Player p = this.plugin.getServer().getPlayer(args[0]);
                if (p instanceof Player){
                    if (this.plugin.isSaveInv(args[0])){
                        this.plugin.removeSaveInv(args[0]);
                        sender.sendMessage(TextFormat.colorize("&7[&aSaveInv&7] &aВы успешно выключили сохранение инвентаря игроку &b" + p.getName() + "&a!"));
                        p.sendMessage(TextFormat.colorize("&7[&aSaveInv&7] &aВам выключили сохранение инвентаря!"));
                        this.plugin.info(p, "&7[SaveInv] " + sender.getName() + " выключил сохранение инвентаря " + p.getName());
                    }else{
                        if (p.getGamemode() != 0){
                            sender.sendMessage(TextFormat.colorize("&7[&aSaveInv&7] &cИгровой режим игрока &b" + p.getName() + " &cне выживание!"));
                        }else{
                            this.plugin.setSaveInv(args[0]);
                            p.sendMessage(TextFormat.colorize("&7[&aSaveInv&7] &aВам включили сохранение инвентаря!"));
                            sender.sendMessage(TextFormat.colorize("&7[&aSaveInv&7] &aВы успешно включили сохранение инвентаря игроку &b" + p.getName() + "&a!"));
                            this.plugin.info(p, "&7[SaveInv] " + sender.getName() + " включил сохранение инвентаря " + p.getName());
                        }
                    }
                }else{
                    sender.sendMessage(TextFormat.colorize("&7[&aSaveInv&7] &cТакого игрока нет на сервере!"));
                }
            }else{
                sender.sendMessage(this.getPermissionMessage());
            }
        }else if (sender instanceof Player){
                if (this.plugin.isSaveInv(sender.getName())){
                    this.plugin.removeSaveInv(sender.getName());
                    sender.sendMessage(TextFormat.colorize("&7[&aSaveInv&7] &aВы успешно выключили сохранение инвентаря!"));
                    this.plugin.info(sender, "&7[SaveInv] " + sender.getName() + " выключил сохранение инвентаря!");
                }else{
                    if (((Player) sender).getGamemode() != 0){
                        sender.sendMessage(TextFormat.colorize("&7[&aSaveInv&7] &cВаш игровой режим не выживание!"));
                    }else{
                        this.plugin.setSaveInv(sender.getName());
                        sender.sendMessage(TextFormat.colorize("&7[&aSaveInv&7] &aВы успешно включили сохранение инвентаря!"));
                        this.plugin.info(sender, "&7[SaveInv] " + sender.getName() + " включил сохранение инвентаря!");
                    }
                }
        }else{
            sender.sendMessage(TextFormat.colorize("&cПожалуйста, используйте эту команду в игре!"));
        }
        return true;
    }
}
