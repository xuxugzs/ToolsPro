package ToolsPro.commands;

import ToolsPro.ToolsPro;
import ToolsPro.util.Message;
import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;

/**
 * Created by Pub4Game on 19.12.2015.
 */
public class GamemodeCommand extends ToolsProCommand {

    private ToolsPro plugin;

    public GamemodeCommand(ToolsPro plugin) {
        super("gamemode", Message.CMD_GAMEMODE_DESCRIPTION, Message.CMD_GAMEMODE_DESCRIPTION2.toString());
        this.setPermission("toolspro.commands.gamemode");
        this.setAliases(new String[]{"gm"});
        this.plugin = plugin;
    }

    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        if (!sender.hasPermission(this.getPermission())) {
            sender.sendMessage(Message.YOU_DONT_HAVE_PERMISSION.getText('c'));
        } else {
            if (args.length != 0) {
                switch (args[0]) {
                    case "0":
                        if (args.length == 1) {
                            if (sender instanceof Player) {
                                if (((Player) sender).getGamemode() == 0) {
                                    Message.CMD_GAMEMODE_SENDER_ALREADY_IN_SURVIVAL_MODE.print(sender, "prefix:&7[&aGamemode&7]", 'c');
                                    return false;
                                }
                                ((Player) sender).setGamemode(0);
                                Message.CMD_GAMEMODE_SENDER_SUCCESSFULLY_CHANGED_TO_SURVIVAL.print(sender, "prefix:&7[&aGamemode&7]", 'a');
                                this.plugin.info(sender, Message.CMD_GAMEMODE_SENDER_SUCCESSFULLY_CHANGED_TO_SURVIVAL_INFO.getText("prefix:&7[Gamemode]", '7', '7', sender.getName()));
                            }else{
                                Message.NEED_PLAYER.print(sender, "prefix:&7[&aGamemode&7]", 'c');
                            }
                        }else{
                            if (sender.hasPermission("toolspro.gamemode.other")) {
                                Player p = this.plugin.getServer().getPlayer(args[1]);
                                if (p != null){
                                    if (p.getGamemode() == 0){
                                        Message.CMD_GAMEMODE_PLAYER_ALREADY_IN_SURVIVAL_MODE.print(sender, "prefix:&7[&aGamemode&7]", 'c', 'b', p.getName());
                                        return true;
                                    }
                                    p.setGamemode(0);
                                    Message.CMD_GAMEMODE_PLAYER_SUCCESSFULLY_CHANGED_TO_SURVIVAL.print(sender, "prefix:&7[&aGamemode&7]", 'a', 'b', p.getName());
                                    Message.CMD_GAMEMODE_PLAYER_SUCCESSFULLY_CHANGED_TO_SURVIVAL_MESSAGE.print(p, "prefix:&7[&aGamemode&7]", 'a');
                                    this.plugin.info(sender, Message.CMD_GAMEMODE_PLAYER_SUCCESSFULLY_CHANGED_TO_SURVIVAL_INFO.getText("prefix:&7[Gamemode]", '7', '7', sender.getName(), p.getName()));
                                }else{
                                    Message.UNKNOWN_PLAYER.print(sender, "prefix:&7[&aGamemode&7]", 'c');
                                }
                            } else {
                                sender.sendMessage(this.getPermissionMessage());
                            }
                        }
                        return true;
                    case "1":
                        if (args.length == 1) {
                            if (sender instanceof Player) {
                                if (((Player) sender).getGamemode() == 1) {
                                    Message.CMD_GAMEMODE_SENDER_ALREADY_IN_CREATIVE_MODE.print(sender, "prefix:&7[&aGamemode&7]", 'c');
                                    return false;
                                }
                                ((Player) sender).setGamemode(1);
                                Message.CMD_GAMEMODE_SENDER_SUCCESSFULLY_CHANGED_TO_CREATIVE.print(sender, "prefix:&7[&aGamemode&7]", 'a');
                                this.plugin.info(sender, Message.CMD_GAMEMODE_SENDER_SUCCESSFULLY_CHANGED_TO_CREATIVE_INFO.getText("prefix:&7[Gamemode]", '7', '7', sender.getName()));
                            }else{
                                return Message.NEED_PLAYER.print(sender, "prefix:&7[&aGamemode&7]", 'c');
                            }
                        }else{
                            if (sender.hasPermission("toolspro.gamemode.other")) {
                                Player p = this.plugin.getServer().getPlayer(args[1]);
                                if (p != null){
                                    if (p.getGamemode() == 1){
                                        return Message.CMD_GAMEMODE_PLAYER_ALREADY_IN_CREATIVE_MODE.print(sender, "prefix:&7[&aGamemode&7]", 'c', 'b', p.getName());
                                    }
                                    p.setGamemode(1);
                                    Message.CMD_GAMEMODE_PLAYER_SUCCESSFULLY_CHANGED_TO_CREATIVE.print(sender, "prefix:&7[&aGamemode&7]", 'a', 'b', p.getName());
                                    Message.CMD_GAMEMODE_PLAYER_SUCCESSFULLY_CHANGED_TO_CREATIVE_MESSAGE.print(p, "prefix:&7[&aGamemode&7]", 'a');
                                    this.plugin.info(sender, Message.CMD_GAMEMODE_PLAYER_SUCCESSFULLY_CHANGED_TO_CREATIVE_INFO.getText("prefix:&7[Gamemode]", '7', '7', sender.getName(), p.getName()));
                                }else{
                                    Message.UNKNOWN_PLAYER.print(sender, "prefix:&7[&aGamemode&7]", 'c');
                                }
                            } else {
                                sender.sendMessage(this.getPermissionMessage());
                            }
                        }
                        return true;
                    case "2":
                        if (args.length == 1) {
                            if (sender instanceof Player){
                                if (((Player) sender).getGamemode() == 2) {
                                    Message.CMD_GAMEMODE_SENDER_ALREADY_IN_ADVENTURE_MODE.print(sender, "prefix:&7[&aGamemode&7]", 'c');
                                    return false;
                                }
                                ((Player) sender).setGamemode(2);
                                Message.CMD_GAMEMODE_SENDER_SUCCESSFULLY_CHANGED_TO_ADVENTURE.print(sender, "prefix:&7[&aGamemode&7]", 'a');
                                this.plugin.info(sender, Message.CMD_GAMEMODE_SENDER_SUCCESSFULLY_CHANGED_TO_ADVENTURE_INFO.getText("prefix:&7[Gamemode]", '7', '7', sender.getName()));
                            }else{
                                return Message.NEED_PLAYER.print(sender, "prefix:&7[&aGamemode&7]", 'c');
                            }
                        }else{
                            if (sender.hasPermission("toolspro.gamemode.other")) {
                                Player p = this.plugin.getServer().getPlayer(args[1]);
                                if (p != null){
                                    if (p.getGamemode() == 2){
                                        return Message.CMD_GAMEMODE_PLAYER_ALREADY_IN_ADVENTURE_MODE.print(sender, "prefix:&7[&aGamemode&7]", 'c', 'b', p.getName());
                                    }
                                    p.setGamemode(2);
                                    Message.CMD_GAMEMODE_PLAYER_SUCCESSFULLY_CHANGED_TO_ADVENTURE.print(sender, "prefix:&7[&aGamemode&7]", 'a', 'b', p.getName());
                                    Message.CMD_GAMEMODE_PLAYER_SUCCESSFULLY_CHANGED_TO_ADVENTURE_MESSAGE.print(p, "prefix:&7[&aGamemode&7]", 'a');
                                    this.plugin.info(sender, Message.CMD_GAMEMODE_PLAYER_SUCCESSFULLY_CHANGED_TO_ADVENTURE_INFO.getText("prefix:&7[Gamemode]", '7', '7', sender.getName(), p.getName()));
                                }else{
                                    Message.UNKNOWN_PLAYER.print(sender, "prefix:&7[&aGamemode&7]", 'c');
                                }
                            } else {
                                sender.sendMessage(this.getPermissionMessage());
                            }
                        }
                        return true;
                    case "3":
                        if (args.length == 1) {
                            if (sender instanceof Player){
                                if (((Player) sender).getGamemode() == 3) {
                                    Message.CMD_GAMEMODE_SENDER_ALREADY_IN_SPECTATOR_MODE.print(sender, "prefix:&7[&aGamemode&7]", 'c');
                                    return false;
                                }
                                ((Player) sender).setGamemode(3);
                                Message.CMD_GAMEMODE_SENDER_SUCCESSFULLY_CHANGED_TO_SPECTATOR.print(sender, "prefix:&7[&aGamemode&7]", 'a');
                                this.plugin.info(sender, Message.CMD_GAMEMODE_SENDER_SUCCESSFULLY_CHANGED_TO_SPECTATOR_INFO.getText("prefix:&7[Gamemode]", '7', '7', sender.getName()));
                            }else{
                                return Message.NEED_PLAYER.print(sender, "prefix:&7[&aGamemode&7]", 'c');
                            }
                        }else{
                            if (sender.hasPermission("toolspro.gamemode.other")) {
                                Player p = this.plugin.getServer().getPlayer(args[1]);
                                if (p != null){
                                    if (p.getGamemode() == 3){
                                        return Message.CMD_GAMEMODE_PLAYER_ALREADY_IN_SPECTATOR_MODE.print(sender, "prefix:&7[&aGamemode&7]", 'c', 'b', p.getName());
                                    }
                                    p.setGamemode(3);
                                    Message.CMD_GAMEMODE_PLAYER_SUCCESSFULLY_CHANGED_TO_SPECTATOR.print(sender, "prefix:&7[&aGamemode&7]", 'a', 'b', p.getName());
                                    Message.CMD_GAMEMODE_PLAYER_SUCCESSFULLY_CHANGED_TO_SPECTATOR_MESSAGE.print(p, "prefix:&7[&aGamemode&7]", 'a');
                                    this.plugin.info(sender, Message.CMD_GAMEMODE_PLAYER_SUCCESSFULLY_CHANGED_TO_SPECTATOR_INFO.getText("prefix:&7[Gamemode]", '7', '7', sender.getName(), p.getName()));
                                }else{
                                    Message.UNKNOWN_PLAYER.print(sender, "prefix:&7[&aGamemode&7]", 'c');
                                }
                            } else {
                                sender.sendMessage(this.getPermissionMessage());
                            }
                        }
                        return true;
                    case "help":
                        Message.CMD_GAMEMODE_HELP1.print(sender,"prefix:&7[&aGamemode&7]",'9');
                        Message.CMD_GAMEMODE_HELP2.print(sender,"prefix:&e/gm 0 &7-",'a');
                        Message.CMD_GAMEMODE_HELP3.print(sender,"prefix:&e/gm 1 &7-",'a');
                        if (sender.hasPermission("toolspro.gamemode.other")) {
                            Message.CMD_GAMEMODE_HELP4.print(sender,"prefix:&e/gm 2 &7-",'a');
                            Message.CMD_GAMEMODE_HELP4.print(sender,"prefix:&e/gm 3 &7-",'a');
                        }
                        return true;
                    default:
                        return Message.CMD_GAMEMODE_USAGE.print(sender,"prefix:&7[&aGamemode&7]",'a','e',"/gm help");
                }
            } else {
                return Message.CMD_GAMEMODE_USAGE.print(sender,"prefix:&7[&aGamemode&7]",'a','e',"/gm help");
            }
        }
        return true;
    }
}