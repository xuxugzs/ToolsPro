package ToolsPro.commands;

import ToolsPro.ToolsPro;
import ToolsPro.util.Message;
import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;
import cn.nukkit.potion.Effect;
import cn.nukkit.inventory.Inventory;
import cn.nukkit.utils.TextFormat;

/**
 * Created by Pub4Game on 19.12.2015.
 */
public class VanishCommand extends ToolsProCommand {

    private ToolsPro plugin;

    public VanishCommand(ToolsPro plugin) {
        super("vanish", Message.CMD_VANISH_DESCRIPTION, Message.CMD_VANISH_DESCRIPTION2.toString());
        this.setPermission("toolspro.commands.vanish");
        this.setAliases(new String[]{"v"});
        this.plugin = plugin;
    }

    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        if (!sender.hasPermission(this.getPermission())) {
            sender.sendMessage(Message.YOU_DONT_HAVE_PERMISSION.getText('c'));
        } else {
            if (args.length != 0) {
                if (sender.hasPermission("toolspro.vanish.other")) {
                    Player p = this.plugin.getServer().getPlayer(args[0]);
                    if (p instanceof Player) {
                        if (this.plugin.isHide(p.getName())) {
                            this.plugin.removeHide(p.getName());
                            ((Player) p).removeEffect(14);
                            sender.sendMessage(TextFormat.colorize("&7[&aVanish&7] &aИгрок &b" + p.getName() + " &aснова видимый!"));
                            p.sendMessage(TextFormat.colorize("&7[&aVanish&7] &aВы стали видимыми!"));
                            this.plugin.info(p, "&7[&aVanish&7] " + sender.getName() + " выключил невидимость " + p.getName() + "!");
                        } else {
                            this.plugin.setHide(p.getName());
                            p.addEffect(Effect.getEffect(14).setVisible(false).setDuration(Integer.MAX_VALUE).setAmplifier(1));
                            Inventory inv = p.getInventory();
                            for (int i = 0; i < p.getInventory().getHotbarSize(); i++) {
                                p.getInventory().setHotbarSlotIndex(i, -1);
                            }
                            p.getInventory().sendContents(p);
                            sender.sendMessage(TextFormat.colorize("&7[&aVanish&7] &aИгрок &b" + p.getName() + " &aневидимый!"));
                            p.sendMessage(TextFormat.colorize("&7[&aVanish&7] &aВы стали невидимыми!"));
                            this.plugin.info(p, "&7[Vanish] " + sender.getName() + " включил невидимость " + p.getName() + "!");
                        }
                    } else {
                        sender.sendMessage(TextFormat.colorize("&7[&aVanish&7] &cТакого игрока нет на сервере!"));
                    }
                } else {
                    sender.sendMessage(this.getPermissionMessage());
                }
            } else {
                if (sender instanceof Player) {
                    if (this.plugin.isHide(sender.getName())) {
                        this.plugin.removeHide(sender.getName());
                        ((Player) sender).removeEffect(14);
                        sender.sendMessage(TextFormat.colorize("&7[&aVanish&7] &aВы успешно выключили невидимость!"));
                        this.plugin.info(sender, "&7[Vanish] " + sender.getName() + " выключил невидимость!");
                    } else {
                        this.plugin.setHide(sender.getName());
                        ((Player) sender).addEffect(Effect.getEffect(14).setVisible(false).setDuration(Integer.MAX_VALUE).setAmplifier(1));
                        for (int i = 0; i < ((Player) sender).getInventory().getHotbarSize(); i++) {
                            ((Player) sender).getInventory().setHotbarSlotIndex(i, -1);
                        }
                        ((Player) sender).getInventory().sendContents(this.plugin.getServer().getPlayer(sender.getName()));
                        sender.sendMessage(TextFormat.colorize("&7[&aVanish&7] &aВы успешно включили невидимость!"));
                        this.plugin.info(sender, "&7[Vanish] " + sender.getName() + " включил невидимость!");
                    }
                } else {
                   return Message.NEED_PLAYER.print(sender, "prefix:&7[&aVanish&7]", 'c');
                }
            }
        }
        return true;
    }
}

