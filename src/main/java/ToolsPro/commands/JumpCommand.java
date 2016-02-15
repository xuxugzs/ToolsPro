package ToolsPro.commands;

import ToolsPro.ToolsPro;
import ToolsPro.util.Message;
import cn.nukkit.Player;
import cn.nukkit.block.Block;
import cn.nukkit.command.CommandSender;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Pub4Game on 19.12.2015.
 */
public class JumpCommand extends Commands {

    private ToolsPro plugin;
    private Map<Integer, Object> tblocks = new HashMap<Integer, Object>();

    public JumpCommand(ToolsPro plugin) {
        super("jump", Message.CMD_JUMP_DESCRIPTION, "/jump");
        this.setPermission("toolspro.commands.jump");
        this.plugin = plugin;
    }

    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        if (!sender.hasPermission(this.getPermission())) {
            Message.YOU_DONT_HAVE_PERMISSION.print(sender, 'c');
        } else {
            if (sender instanceof Player) {
                tblocks.put(0, this.plugin.NON_SOLID_BLOCKS);
                Block block = ((Player) sender).getTargetBlock(100, tblocks);
                if (block == null) {
                    Message.IS_NOT_A_REACHABLE_BLOCK.print(sender, "prefix:&7[&aJump&7]", 'c');
                    return false;
                }
                if (!((Player) sender).getLevel().getBlock(block.add(0, 2)).isSolid()) {
                    ((Player) sender).teleport(block.add(0, 1));
                    return true;
                }
                Integer side = ((Player) sender).getDirection();
                switch (side) {
                    case 0:
                    case 1:
                        side += 3;
                        break;
                    case 3:
                        side += 2;
                        break;
                    default:
                        break;
                }
                if (!block.getSide(side).isSolid()) {
                    ((Player) sender).teleport(block);
                }
            } else {
                return Message.NEED_PLAYER.print(sender, "prefix:&7[&aJump&7]", 'c');
            }
        }
        return true;
    }


}
