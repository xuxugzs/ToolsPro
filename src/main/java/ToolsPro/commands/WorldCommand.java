package ToolsPro.commands;

import ToolsPro.ToolsPro;
import ToolsPro.util.Message;
import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;
import cn.nukkit.level.generator.Generator;

/**
 * Created by Pub4Game on 04.02.2016.
 */

public class WorldCommand extends ToolsProCommand {

    private ToolsPro plugin;

    public WorldCommand(ToolsPro plugin) {
        super("world", Message.CMD_WORLD_DESCRIPTION, Message.CMD_WORLD_DESCRIPTION.toString());
        this.setPermission("toolspro.commands.world");
        this.plugin = plugin;
    }

    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        if (!sender.hasPermission(this.getPermission())) {
            sender.sendMessage(Message.YOU_DONT_HAVE_PERMISSION.getText('c'));
        } else {
            if (args.length >= 2) {
                switch (args[0]) {
                    case "tp":
                        if (args.length <= 2) {
                            if (sender instanceof Player) {
                                if (!sender.hasPermission("toolspro.worlds.*") && !sender.hasPermission("toolspro.worlds." + args[1].toLowerCase())) {
                                    Message.CMD_WORLD_TP_NO_PERMISSION.print(sender, "prefix:&7[&aWorld&7]", 'c');
                                    return false;
                                }
                                if (!sender.getServer().isLevelGenerated(args[1])) {
                                    Message.CMD_WORLD_TP_NOT_FOUND.print(sender, "prefix:&7[&aWorld&7]", 'c');
                                } else if (!sender.getServer().isLevelLoaded(args[1])) {
                                    Message.CMD_WORLD_TP_NOT_LOADED.print(sender, "prefix:&7[&aWorld&7]", 'c');
                                    if (!sender.getServer().loadLevel(args[1])) {
                                        Message.CMD_WORLD_TP_ERROR_LOADING.print(sender, "prefix:&7[&aWorld&7]", 'c');
                                    }
                                }
                                ((Player) sender).teleport(this.plugin.getServer().getLevelByName(args[1]).getSafeSpawn());
                                Message.CMD_WORLD_TP.print(sender, "prefix:&7[&aWorld&7]", 'a');
                            } else {
                                return Message.NEED_PLAYER.print(sender, "prefix:&7[&aWorld&7]", 'c');
                            }
                        } else {
                            Player p = this.plugin.getServer().getPlayer(args[2]);

                        }
                        return true;
                    case "create":
                        if (!sender.getServer().isLevelGenerated(args[1])) {
                            long seed = System.currentTimeMillis();
                            Class generator;
                            if (args.length <= 2){
                                generator = Generator.getGenerator(Generator.TYPE_INFINITE);
                            } else {
                                int type;
                                switch (args[2].toLowerCase()) {
                                    case "old":
                                        type = Generator.TYPE_OLD;
                                        break;
                                    case "infinite":
                                    default:
                                        type = Generator.TYPE_INFINITE;
                                        break;
                                    case "flat":
                                        type = Generator.TYPE_FLAT;
                                        break;
                                }
                                generator = Generator.getGenerator(type);
                            }
                            sender.getServer().generateLevel(args[1], seed == 0 ? System.currentTimeMillis() : seed, generator);
                            Message.CMD_WORLD_CREATE.print(sender, "prefix:&7[&aWorld&7]", 'a', args[1]);
                        } else {
                            Message.CMD_WORLD_CREATE_ALREADY_EXISTS.print(sender, "prefix:&7[&aWorld&7]", 'c');
                        }
                        return true;
                }
            } else {
                return Message.CMD_WORLD_USAGE.print(sender, "prefix:&7[&aWorld&7]", 'c');
            }
        }
        return true;
    }
}
