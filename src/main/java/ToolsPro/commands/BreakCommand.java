package ToolsPro.commands;

import ToolsPro.ToolsPro;
import ToolsPro.util.Message;
import cn.nukkit.Player;
import cn.nukkit.block.BlockAir;
import cn.nukkit.block.Block;
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
            sender.sendMessage(Message.YOU_DONT_HAVE_PERMISSION.getText('c'));
        } else {
            /*
            if (sender instanceof Player) {
                Block block = ((Player) sender).getTargetBlock(100, Block.AIR);
                if (block == null) {
                     Message.IS_NOT_A_REACHABLE_BLOCK.print(sender, "prefix:&7[&aBreak&7]", 'c');
                    return false;
                } else if (block.getId() == Block.BEDROCK && !sender.hasPermission("toolspro.break.bedrock")) {
                    Message.CMD_BREAK_NO_BREAK_BEDROCK.print(sender, 'c');
                    return false;
                }
                ((Player) sender).getLevel().setBlock(block, new BlockAir(), true, true);
            } else {
                return Message.NEED_PLAYER.print(sender, 'c');
            }
            */
        }
        return true;
    }
}
