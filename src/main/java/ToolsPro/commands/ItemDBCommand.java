package ToolsPro.commands;

import ToolsPro.ToolsPro;
import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.event.entity.EntityDamageEvent;
import cn.nukkit.item.Item;
import cn.nukkit.utils.TextFormat;

import javax.xml.soap.Text;

/**
 * Created by Pub4Game on 19.12.2015.
 */
public class ItemDBCommand extends Command {

    private ToolsPro plugin;

    public ItemDBCommand(ToolsPro plugin) {
        super("itemdb", "Показывает информацию о предмете, который Вы держите в руке.", "/itemdb");
        this.setPermission("toolspro.commands.itemdb");
        this.plugin = plugin;
    }

    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        if (!sender.hasPermission(this.getPermission())) {
            sender.sendMessage(this.getPermissionMessage());
        } else {
            if (sender instanceof Player) {
                Item item = ((Player) sender).getInventory().getItemInHand();
                String m;
                m = "&bThis item " + (this.plugin.isRepairable(item) ? "has &c" + item.getDamage() + "&b" + " points of damage" : "metadata is &c" + item.getDamage());
                if(args.length >= 1){
                    switch(args[0].toLowerCase()){
                        case "name":
                            m = "&bThis item is named: &c" + item.getName();
                            break;
                        case "id":
                            m = "&bThis item ID is: &c" + item.getId();
                            break;
                        case "durability":
                        case "dura":
                        case "metadata":
                        case "meta":
                            m = "&bThis item " + (this.plugin.isRepairable(item) ? "has &c" + item.getDamage() + "&b" + " points of damage" : "metadata is &c" + item.getDamage());
                            break;
                    }
                }
                sender.sendMessage(TextFormat.colorize(m));
            }else{
                sender.sendMessage(TextFormat.colorize("&cПожалуйста, используйте эту команду в игре!"));
            }
        }
        return true;
    }
}
