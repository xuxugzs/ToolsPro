package ToolsPro.commands;

import ToolsPro.ToolsPro;
import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.utils.TextFormat;

/**
 * Created by Pub4Game on 19.12.2015.
 */
public class CompassCommand extends Command {

    private ToolsPro plugin;

    public CompassCommand(ToolsPro plugin) {
        super("compass", "Показывает название стороны света в которую вы смотрите.", "/compass");
        this.setPermission("toolspro.commands.compass");
        this.plugin = plugin;
    }

    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        if (!sender.hasPermission(this.getPermission())) {
            sender.sendMessage(this.getPermissionMessage());
        }else if (sender instanceof  Player){
            String direction;
            switch (((Player) sender).getDirection()) {
                case 0:
                    direction = "&eюг";
                    break;
                case 1:
                    direction = "&dзапад";
                    break;
                case 2:
                    direction = "&bсевер";
                    break;
                case 3:
                    direction = "&bсевер";
                    break;
                default:
                    sender.sendMessage(TextFormat.colorize("&7[&aCompass&7] &cПростите, но нам не удаось определить ваше местонахождение"));
                    return false;
            }
            sender.sendMessage(TextFormat.colorize("&7[&aCompass&7] &aВы смотретие на " + direction));
        }else{
            sender.sendMessage(TextFormat.colorize("&7[&aCompass&7] &cПожалуйста, используйте эту команду только в игре!"));
        }
        return true;
    }
}
