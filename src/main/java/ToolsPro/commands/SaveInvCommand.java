package ToolsPro.commands;

import ToolsPro.ToolsPro;
import ToolsPro.util.Message;
import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;

/**
 * Created by Pub4Game on 19.12.2015.
 */
public class SaveInvCommand extends ToolsProCommand {

    private ToolsPro plugin;

    public SaveInvCommand(ToolsPro plugin) {
        super("saveinv", Message.CMD_SAVEINV_DESCRIPTION, Message.CMD_SAVEINV_DESCRIPTION2.toString());
        this.setPermission("toolspro.commands.saveinv");
        this.plugin = plugin;
    }

    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        if (!sender.hasPermission(this.getPermission())) {
            sender.sendMessage(this.getPermissionMessage());
        } else {
            if (args.length != 0) {
                if (sender.hasPermission("toolspro.saveinv.other")) {
                    Player p = this.plugin.getServer().getPlayer(args[0]);
                        if (p != null) {
                            if (p.getGamemode() != 0) {
                                Message.PLAYER_NOT_SURVIVAL.print(sender, "prefix:&7[&aSaveInv&7]", 'c', 'b', p.getName());
                            } else {
                                if (this.plugin.isSaveInv(args[0])) {
                                    this.plugin.removeSaveInv(args[0]);
                                    Message.CMD_SAVEINV_PLAYER_DISABLE.print(sender, "prefix:&7[&aSaveInv&7]", 'a', 'b', p.getName());
                                    Message.CMD_SAVEINV_PLAYER_DISABLE_MESSAGE.print(p, "prefix:&7[&aSaveInv&7]", 'a');
                                    this.plugin.info(sender, Message.CMD_SAVEINV_PLAYER_DISABLE_INFO.getText("prefix:&7[SaveInv]", sender.getName(), p.getName()));
                                } else {
                                    this.plugin.setSaveInv(args[0]);
                                    Message.CMD_SAVEINV_PLAYER_ENABLE.print(sender, "prefix:&7[&aSaveInv&7]", 'a', 'b', p.getName());
                                    Message.CMD_SAVEINV_PLAYER_ENABLE_MESSAGE.print(p, "prefix:&7[&aSaveInv&7]", 'a');
                                    this.plugin.info(sender, Message.CMD_SAVEINV_PLAYER_ENABLE_INFO.getText("prefix:&7[SaveInv]", sender.getName(), p.getName()));
                                }
                            }
                        } else {
                            Message.UNKNOWN_PLAYER.print(sender, "prefix:&7[&aSaveInv&7]", 'c');
                    }
                } else {
                    sender.sendMessage(this.getPermissionMessage());
                }
            } else {
                if (sender instanceof Player){
                    if (((Player) sender).getGamemode() != 0) {
                        Message.YOU_NOT_SURVIVAL.print(sender, "prefix:&7[&aSaveInv&7]", 'c');
                    } else {
                        if (this.plugin.isSaveInv(sender.getName())) {
                            this.plugin.removeSaveInv(sender.getName());
                            Message.CMD_SAVEINV_SENDER_DISABLE.print(sender, "prefix:&7[&aSaveInv&7]", 'a');
                            this.plugin.info(sender, Message.CMD_SAVEINV_SENDER_DISABLE_INFO.getText("prefix:&7[SaveInv]"));
                        } else {
                            this.plugin.setSaveInv(sender.getName());
                            Message.CMD_SAVEINV_SENDER_ENABLE.print(sender, "prefix:&7[&aSaveInv&7]", 'a');
                            this.plugin.info(sender, Message.CMD_SAVEINV_SENDER_ENABLE_INFO.getText("prefix:&7[SaveInv]"));
                        }
                    }
                } else {
                    return Message.NEED_PLAYER.print(sender, "prefix:&7[&aSaveInv&7]", 'c');
                }
            }
        }
        return true;
    }
}
