package ToolsPro;

import ToolsPro.commands.*;
import ToolsPro.listeners.*;
import ToolsPro.util.Message;
import cn.nukkit.Player;
import cn.nukkit.block.Block;
import cn.nukkit.command.CommandSender;
import cn.nukkit.item.Armor;
import cn.nukkit.item.Item;
import cn.nukkit.item.Tool;
import cn.nukkit.plugin.PluginBase;
import cn.nukkit.utils.TextFormat;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ToolsPro extends PluginBase {

    private static ToolsPro instance;

    public static ToolsPro getPlugin() {
        return instance;
    }

    Set<String> GodPlayers = new HashSet<String>();
    Set<String> SaveInvPlayers = new HashSet<String>();
    Set<String> HidePlayers = new HashSet<String>();
    public List<String> forbiddenNames;

    @Override
    public void onEnable() {
        instance = this;
        this.saveResource("config.yml", false);
        this.reloadConfig();
        loadCfg();
        Message.init(this);
        this.getServer().getCommandMap().register("break", new BreakCommand(this));
        this.getServer().getCommandMap().register("broadcast", new BroadcastCommand(this));
        this.getServer().getCommandMap().register("burn", new BurnCommand(this));
        this.getServer().getCommandMap().register("clearhotbar", new ClearHotBarCommand(this));
        this.getServer().getCommandMap().register("clearinventory", new ClearInventoryCommand(this));
        this.getServer().getCommandMap().register("compass", new CompassCommand(this));
        this.getServer().getCommandMap().register("depth", new DepthCommand(this));
        this.getServer().getCommandMap().register("extinguish", new ExtinguishCommand(this));
        this.getServer().getCommandMap().register("fly", new FlyCommand(this));
        this.getServer().getCommandMap().register("gm", new GMCommand(this));
        this.getServer().getCommandMap().register("god", new GodCommand(this));
        this.getServer().getCommandMap().register("health", new HealthCommand(this));
        this.getServer().getCommandMap().register("itemban", new ItemBanCommand(this));
        this.getServer().getCommandMap().register("itemdb", new ItemDBCommand(this));
        this.getServer().getCommandMap().register("kickall", new KickAllCommand(this));
        this.getServer().getCommandMap().register("more", new MoreCommand(this));
        this.getServer().getCommandMap().register("mute", new MuteCommand(this));
        this.getServer().getCommandMap().register("repair", new RepairCommand(this));
        this.getServer().getCommandMap().register("saveinv", new SaveInvCommand(this));
        this.getServer().getCommandMap().register("setspawn", new SetSpawnCommand(this));
        this.getServer().getCommandMap().register("spawnall", new SpawnAllCommand(this));
        this.getServer().getCommandMap().register("spawn", new SpawnCommand(this));
        this.getServer().getCommandMap().register("speed", new SpeedCommand(this));
        this.getServer().getCommandMap().register("suicide", new SuicideCommand(this));
        this.getServer().getCommandMap().register("top", new TopCommand(this));
        this.getServer().getCommandMap().register("unmute", new UnmuteCommand(this));
        this.getServer().getCommandMap().register("vanish", new VanishCommand(this));
        this.getServer().getPluginManager().registerEvents(new PlayerAttackListener(this), this);
        this.getServer().getPluginManager().registerEvents(new PlayerNameListener(this), this);
        this.getServer().getPluginManager().registerEvents(new EventListener(this), this);
        this.getServer().getPluginManager().registerEvents(new ItemBanListener(this), this);
        this.getServer().getPluginManager().registerEvents(new MuteListener(this), this);
        Message.TOOLSPRO_LOADED.log();
    }


    @Override
    public void onDisable() {
        Message.TOOLSPRO_DISABLED.log('c');
    }

    private void loadCfg(){
        //Сюда можно будет добавлять остальные считывания переменных из конфига
        try {
            this.forbiddenNames = this.getConfig().getNestedAs("fordbidden-player-names", List.class);
        } catch (Exception e){
            this.forbiddenNames = new ArrayList<String>();
        }
    }

    public boolean isRepairable(Item item) {
        return item instanceof Tool || item instanceof Armor;
    }

    public boolean isHide(String name) {
        return HidePlayers.contains(name.toLowerCase());
    }

    public void setHide(String name) {
        HidePlayers.add(name.toLowerCase());
    }

    public void removeHide(String name) {
        if (HidePlayers.contains(name.toLowerCase())) HidePlayers.remove(name.toLowerCase());
    }

    public boolean isSaveInv(String name) {
        return SaveInvPlayers.contains(name.toLowerCase());
    }

    public void setSaveInv(String name) {
        SaveInvPlayers.add(name.toLowerCase());
    }

    public void removeSaveInv(String name) {
        if (SaveInvPlayers.contains(name.toLowerCase())) SaveInvPlayers.remove(name.toLowerCase());
    }

    public boolean isGodMode(String name) {
        return GodPlayers.contains(name.toLowerCase());
    }

    public void setGodMode(String name) {
        GodPlayers.add(name.toLowerCase());
    }

    public void removeGodMode(String name) {
        if (GodPlayers.contains(name.toLowerCase())) GodPlayers.remove(name.toLowerCase());
    }

    public void info(CommandSender sender, String message) {
        for (Player player : this.getServer().getOnlinePlayers().values()) {
            if (player.hasPermission("toolspro.notice") && player != sender) {
                player.sendMessage(TextFormat.GRAY + (message));
            }
        }
    }

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }

    public String join(String [] ln) {
        StringBuilder sb = null;
        for (String str : ln){
            if (sb == null) sb = new StringBuilder(str);
            else sb.append(" ").append(str);
        }
        return sb.toString();
    }

    public static final int [] NON_SOLID_BLOCKS = {Block.SAPLING, Block.WATER, Block.STILL_WATER, Block.LAVA, Block.STILL_LAVA, Block.COBWEB, Block.TALL_GRASS, Block.BUSH, Block.DANDELION,
            Block.POPPY, Block.BROWN_MUSHROOM, Block.RED_MUSHROOM, Block.TORCH, Block.FIRE, Block.WHEAT_BLOCK, Block.SIGN_POST, Block.WALL_SIGN, Block.SUGARCANE_BLOCK,
            Block.PUMPKIN_STEM, Block.MELON_STEM, Block.VINE, Block.CARROT_BLOCK, Block.POTATO_BLOCK, Block.DOUBLE_PLANT};
}
