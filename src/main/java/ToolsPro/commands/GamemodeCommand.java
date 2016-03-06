package ToolsPro.commands;

import ToolsPro.ToolsPro;
import ToolsPro.util.Message;
import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.command.CommandSender;

/**
 * Created by Pub4Game on 19.12.2015.
 */
public class GamemodeCommand extends Commands {

    private ToolsPro plugin;

    public GamemodeCommand(ToolsPro plugin) {
        super("gamemode", Message.CMD_GAMEMODE_DESCRIPTION, Message.CMD_GAMEMODE_DESCRIPTION2.toString());
        this.setPermission("toolspro.commands.gamemode.use");
        this.setAliases(new String[]{"gm", "gms", "gmc", "gma", "gmt", "survival", "creative", "adventure", "spectator", "viewer"});
        this.plugin = plugin;
    }

    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        if (!sender.hasPermission(this.getPermission())) {
            Message.YOU_DONT_HAVE_PERMISSION.print(sender, 'c');
        } else {
            boolean command = commandLabel.equalsIgnoreCase("gamemode") || commandLabel.equalsIgnoreCase("gm");
            if (args.length >= 1 || !command) {
                int gm;
                if (command) {
                    if (args[0].matches("^[0-9]+\\d*$")) {
                        switch (Integer.parseInt(args[0])) {
                            case Player.SURVIVAL:
                            case Player.CREATIVE:
                            case Player.ADVENTURE:
                            case Player.SPECTATOR:
                                gm = Integer.parseInt(args[0]);
                                break;
                            default:
                                Message.CMD_GAMEMODE_USAGE.print(sender, "prefix:&7[&aGamemode&7]", 'a', 'e', "/gamemode help");
                                return false;
                        }
                    } else {
                        switch (args[0].toLowerCase()) {
                            case "survival":
                            case "s":
                                gm = Player.SURVIVAL;
                                break;
                            case "creative":
                            case "c":
                                gm = Player.CREATIVE;
                                break;
                            case "adventure":
                            case "a":
                                gm = Player.ADVENTURE;
                                break;
                            case "spectator":
                            case "viewer":
                            case "view":
                            case "v":
                            case "t":
                                gm = Player.SPECTATOR;
                                break;
                            case "help":
                                Message.CMD_GAMEMODE_HELP1.print(sender, "prefix:&7[&aGamemode&7]", '9');
                                Message.CMD_GAMEMODE_HELP2.print(sender, "prefix:&e/gamemode 0 &7-", 'a');
                                Message.CMD_GAMEMODE_HELP3.print(sender, "prefix:&e/gamemode 1 &7-", 'a');
                                Message.CMD_GAMEMODE_HELP4.print(sender, "prefix:&e/gamemode 2 &7-", 'a');
                                Message.CMD_GAMEMODE_HELP5.print(sender, "prefix:&e/gamemode 3 &7-", 'a');
                                return false;
                            default:
                                Message.CMD_GAMEMODE_USAGE.print(sender, "prefix:&7[&aGamemode&7]", 'a', 'e', "/gamemode help");
                                return false;
                        }
                    }
                } else {
                    switch (commandLabel) {
                        case "survival":
                        case "gms":
                            gm = Player.SURVIVAL;
                            break;
                        case "creative":
                        case "gmc":
                            gm = Player.CREATIVE;
                            break;
                        case "adventure":
                        case "gma":
                            gm = Player.ADVENTURE;
                            break;
                        case "spectator":
                        case "viewer":
                        case "gmt":
                            gm = Player.SPECTATOR;
                            break;
                        default:
                            return false;
                    }
                }
                String gmstring = Server.getGamemodeString(gm);
                if (args.length >= 2 || args.length > 0 && !command) {
                    if (sender.hasPermission("toolspro.commands.gamemode.other")) {
                        Player p;
                        if (args.length >= 2) {
                            p = this.plugin.getServer().getPlayer(args[1]);
                        } else {
                            p = this.plugin.getServer().getPlayer(args[0]);
                        }
                        if (p == null) {
                            p = this.plugin.sortedListPlayers(args[0]);
                            if (p == null) {
                                Message.UNKNOWN_PLAYER.print(sender, "prefix:&7[&aGamemode&7]", 'c');
                                return true;
                            }
                        }
                            if (p.getGamemode() != gm) {
                                p.setGamemode(gm);
                                Message.CMD_GAMEMODE_PLAYER_SUCCESSFULLY_CHANGED_GAMEMODE.print(sender, 'a', 'b', p.getName(), gmstring);
                                Message.CMD_GAMEMODE_PLAYER_SUCCESSFULLY_CHANGED_GAMEMODE_MESSAGE.print(p, 'a', 'b', gmstring);
                                this.plugin.info(p, Message.CMD_GAMEMODE_PLAYER_SUCCESSFULLY_CHANGED_GAMEMODE_INFO.getText("prefix:&7[Gamemode]", '7', '7', sender.getName(), p.getName(), gmstring));
                            } else {
                                Message.CMD_GAMEMODE_PLAYER_ALREADY_IN_GAMEMODE.print(sender, 'c', 'b', p.getName(), gmstring);
                            }

                    } else {
                        return Message.YOU_DONT_HAVE_PERMISSION.print(sender, 'c');
                    }
                } else {
                    if (sender instanceof Player) {
                        if (((Player) sender).getGamemode() != gm) {
                            ((Player) sender).setGamemode(gm);
                            Message.CMD_GAMEMODE_SENDER_SUCCESSFULLY_CHANGED_GAMEMODE.print(sender, 'a', 'b', gmstring);
                            this.plugin.info(sender, Message.CMD_GAMEMODE_SENDER_SUCCESSFULLY_CHANGED_GAMEMODE_INFO.getText("prefix:&7[Gamemode]", '7', '7', sender.getName(), gmstring));
                        } else {
                            Message.CMD_GAMEMODE_SENDER_ALREADY_IN_GAMEMODE.print(sender, 'c', 'b', gmstring);
                        }
                    } else {
                        return Message.NEED_PLAYER.print(sender, "prefix:&7[&aGamemode&7]", 'c');
                    }
                }
            } else {
                return Message.CMD_GAMEMODE_USAGE.print(sender, "prefix:&7[&aGamemode&7]", 'a', 'e', "/gamemode help");
            }
        }
        return true;
    }
}