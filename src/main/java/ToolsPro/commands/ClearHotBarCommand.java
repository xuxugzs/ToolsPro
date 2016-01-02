package ToolsPro.commands;

import ToolsPro.ToolsPro;
import ToolsPro.util.Message;
import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;

/**
 * Created by Pub4Game on 19.12.2015.
 */
public class ClearHotBarCommand extends ToolsProCommand {

    private ToolsPro plugin;

    public ClearHotBarCommand(ToolsPro plugin) {
        super("clearhotbar", Message.CMD_CLEARHOTBAR_DESCRIPTION, Message.CMD_CLEARHOTBAR_DESCRIPTION2.toString());
        this.setPermission("toolspro.commands.clearhotbar");
        this.setAliases(new String[]{"chb"});
        this.plugin = plugin;
    }

    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        if (!sender.hasPermission(this.getPermission())) {
            sender.sendMessage(this.getPermissionMessage());
        }else if (args.length != 0){
            if (sender.hasPermission("toolspro.clearhotbar.other")){
                Player p = this.plugin.getServer().getPlayer(args[0]);
                if (p == null){
                    Message.UNKNOWN_PLAYER.print(sender, "prefix:&7[&aClearHotBar&7]", 'c');
                    //sender.sendMessage(TextFormat.colorize("&7[&aClearHotBar&7] &cТакого игрока нет на сервере!"));
                }else{
                    for (int i = 0; i<p.getInventory().getHotbarSize(); i++){
                        p.getInventory().setHotbarSlotIndex(i, -1);
                    }
                    Message.CMD_CLEARHOTBAR_PLAYER_CLEAR.print(sender, "prefix:&7[&aClearHotBar&7]", 'a', 'b', p.getName());
                    Message.CMD_CLEARHOTBAR_PLAYER_CLEAR_LOG.log("prefix:&7[ClearHotBar]", sender.getName(), p.getName());
                    //this.plugin.info(sender, "&7[ClearHotBar] " + sender.getName() +  " очистил хот бар игроку " + p.getName() + "!");
                }
            }else{
                sender.sendMessage(this.getPermissionMessage());
            }
        }else if (!(sender instanceof Player)){
            Message.NEED_PLAYER.print(sender, 'c');
        }else{
            for (int i = 0; i<((Player) sender).getInventory().getHotbarSize(); i++){
                ((Player) sender).getInventory().setHotbarSlotIndex(i, -1);
            }
            ((Player) sender).getInventory().sendContents(this.plugin.getServer().getPlayer(sender.getName()));

            //sender.sendMessage(TextFormat.colorize("&7[&aClearHotBar&7] &aВаш хот бар был успешно очищен!"));
            Message.CMD_CLEARHOTBAR_SENDER_CLEAR.print(sender, "prefix:&7[&aClearHotBar&7]", 'a');
            Message.CMD_CLEARHOTBAR_SENDER_CLEAR_LOG.log("prefix:&7[ClearHotBar]");
            //this.plugin.info(sender, "&7[ClearHotBar] " + sender.getName() + " очистил себе хот бар!");
        }
        return true;
    }

}
