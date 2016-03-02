package ToolsPro.commands;

import ToolsPro.util.Message;
import cn.nukkit.command.Command;

public abstract class Commands extends Command {
    public Commands(String cmd, Message description, String cmd2) {
        super(cmd, description.toString(), cmd2);
    }
}
