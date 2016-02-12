package ToolsPro.commands;

import ToolsPro.ToolsPro;
import ToolsPro.util.Message;
import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;

/**
 * Created by Pub4Game on 19.12.2015.
 */
public class SaveInvCommand extends Commands {

    private ToolsPro plugin;

    public SaveInvCommand(ToolsPro plugin) {
        super("saveinv", Message.CMD_SAVEINV_DESCRIPTION, Message.CMD_SAVEINV_DESCRIPTION2.toString());
        this.setPermission("toolspro.commands.saveinv.use");
        this.plugin = plugin;
    }

    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        if (!sender.hasPermission(this.getPermission())) {
            Message.YOU_DONT_HAVE_PERMISSION.print(sender, 'c');
        } else {
            if (args.length != 0) {
                if (sender.hasPermission("toolspro.commands.saveinv.other")) {
                    Player p = this.plugin.getServer().getPlayer(args[0]);
                        if (p != null) {
                            if (p.getGamemode() == 1 || p.getGamemode() == 3) {
                                Message.PLAYER_NOT_SURVIVAL_OR_ADVENTURE.print(sender, "prefix:&7[&aSaveInv&7]", 'c', 'b', p.getName());
                            } else if (this.plugin.getPlayerSaveInv(p)) {
                                this.plugin.removePlayerSaveInv(p);
                                Message.CMD_SAVEINV_PLAYER_DISABLE.print(sender, "prefix:&7[&aSaveInv&7]", 'a', 'b', p.getName());
                                Message.CMD_SAVEINV_PLAYER_DISABLE_MESSAGE.print(p, "prefix:&7[&aSaveInv&7]", 'a');
                                this.plugin.info(sender, Message.CMD_SAVEINV_PLAYER_DISABLE_INFO.getText("prefix:&7[SaveInv]", '7', '7', sender.getName(), p.getName()));
                            } else {
                                this.plugin.setPlayerSaveInv(p);
                                Message.CMD_SAVEINV_PLAYER_ENABLE.print(sender, "prefix:&7[&aSaveInv&7]", 'a', 'b', p.getName());
                                Message.CMD_SAVEINV_PLAYER_ENABLE_MESSAGE.print(p, "prefix:&7[&aSaveInv&7]", 'a');
                                this.plugin.info(sender, Message.CMD_SAVEINV_PLAYER_ENABLE_INFO.getText("prefix:&7[SaveInv]", '7', '7', sender.getName(), p.getName()));
                            }
                        } else {
                            Message.UNKNOWN_PLAYER.print(sender, "prefix:&7[&aSaveInv&7]", 'c');
                    }
                } else {
                    return Message.YOU_DONT_HAVE_PERMISSION.print(sender, 'c');
                }
            } else {
                if (sender instanceof Player){
                    if (((Player) sender).getGamemode() == 1 || ((Player) sender).getGamemode() == 3) {
                        Message.YOU_NOT_SURVIVAL_OR_ADVENTURE.print(sender, "prefix:&7[&aSaveInv&7]", 'c');
                    } else if (this.plugin.getPlayerSaveInv((Player) sender)) {
                        this.plugin.removePlayerSaveInv((Player) sender);
                        Message.CMD_SAVEINV_SENDER_DISABLE.print(sender, "prefix:&7[&aSaveInv&7]", 'a');
                        this.plugin.info(sender, Message.CMD_SAVEINV_SENDER_DISABLE_INFO.getText("prefix:&7[SaveInv]", '7', '7', sender.getName()));
                    } else {
                        this.plugin.setPlayerSaveInv((Player) sender);
                        Message.CMD_SAVEINV_SENDER_ENABLE.print(sender, "prefix:&7[&aSaveInv&7]", 'a');
                        this.plugin.info(sender, Message.CMD_SAVEINV_SENDER_ENABLE_INFO.getText("prefix:&7[SaveInv]", '7', '7', sender.getName()));
                    }
                } else {
                    return Message.NEED_PLAYER.print(sender, "prefix:&7[&aSaveInv&7]", 'c');
                }
            }
        }
        return true;
    }
}
