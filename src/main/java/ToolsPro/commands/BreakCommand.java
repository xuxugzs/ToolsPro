package ToolsPro.commands;

import ToolsPro.ToolsPro;
import ToolsPro.util.Message;
import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;

/**
 * Created by Pub4Game on 19.12.2015.
 */
public class BreakCommand extends ToolsProCommand {

    private ToolsPro plugin;

    public BreakCommand(ToolsPro plugin) {
        super("break", Message.CMD_BREAK_DESCRIPTION, "/break");
        this.setPermission("toolspro.commands.break");
        this.plugin = plugin;
    }

    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        if (!sender.hasPermission(this.getPermission())) {
            sender.sendMessage(this.getPermissionMessage());
        } else if (sender instanceof Player) {
            /*Block block = ((Player) sender).getTargetBlock(100, Block.AIR);
            if (block == null) {
                sender.sendMessage(TextFormat.RED + "There isn't a reachable block");
                return false;
            } else if (block.getId() == Block.BEDROCK && !sender.hasPermission("toolspro.break.bedrock")) {
                sender.sendMessage(TextFormat.RED + "You can't break bedrock");
                return false;
            }
            ((Player) sender).getLevel().setBlock(block, new Air(), true, true);
            */
        } else {
            return Message.NEED_PLAYER.print(sender, 'c');
        }
        return true;
    }
}
