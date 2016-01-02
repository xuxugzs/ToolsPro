package ToolsPro.commands;

import ToolsPro.util.Message;
import cn.nukkit.command.Command;

public abstract class ToolsProCommand extends Command {
    public ToolsProCommand(String cmd, Message description, String cmd2){
        super(cmd, description.toString(), cmd2);
    }
}
