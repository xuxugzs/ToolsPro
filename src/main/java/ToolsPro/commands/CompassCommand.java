package ToolsPro.commands;

import ToolsPro.ToolsPro;
import ToolsPro.util.Message;
import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;

/**
 * Created by Pub4Game on 19.12.2015.
 */
public class CompassCommand extends ToolProCommand {

    private ToolsPro plugin;

    public CompassCommand(ToolsPro plugin) {
        super("compass", Message.CMD_COMPASS_DESC, "/compass");
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
                    direction = Message.SOUTH.getText('e');//"&eюг";
                    break;
                case 1:
                    direction = Message.WEST.getText('d');//"&dзапад";
                    break;
                case 2:
                    direction = Message.NORTH.getText('b');//"&bсевер";
                    break;
                case 3:
                    direction = Message.EAST.getText('d');//"&bсевер";
                    break;
                default:
                    Message.CMD_COMPASS_WRONGDIR.print(sender,"prefix:&7[&aCompass&7]",'c');
                    //sender.sendMessage(TextFormat.colorize("&7[&aCompass&7] &cПростите, но нам не удаось определить ваше местонахождение"));
                    return true;
            }
            Message.CMD_COMPASS_VIEW.print(sender,"prefix:&7[&aCompass&7]",'a');
            //sender.sendMessage(TextFormat.colorize("&7[&aCompass&7] &aВы смотретие на " + direction));
        }else{
            Message.NEEDPLAYER.print(sender,"prefix:&7[&aCompass&7]", 'c');
            //sender.sendMessage(TextFormat.colorize("&7[&aCompass&7] &cПожалуйста, используйте эту команду только в игре!"));
        }
        return true;
    }
}
