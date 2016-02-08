package ToolsPro;

import ToolsPro.commands.*;
import ToolsPro.listeners.*;
import ToolsPro.util.*;
import ToolsPro.listeners.EventListener;
import cn.nukkit.Player;
import cn.nukkit.block.Block;
import cn.nukkit.command.CommandSender;
import cn.nukkit.entity.Entity;
import cn.nukkit.entity.item.EntityPrimedTNT;
import cn.nukkit.item.ItemArmor;
import cn.nukkit.item.Item;
import cn.nukkit.item.ItemTool;
import cn.nukkit.level.Level;
import cn.nukkit.level.Position;
import cn.nukkit.math.NukkitRandom;
import cn.nukkit.math.Vector3;
import cn.nukkit.nbt.tag.CompoundTag;
import cn.nukkit.nbt.tag.DoubleTag;
import cn.nukkit.nbt.tag.FloatTag;
import cn.nukkit.nbt.tag.ListTag;
import cn.nukkit.plugin.PluginBase;
import cn.nukkit.utils.Config;
import cn.nukkit.utils.TextFormat;

import java.io.File;
import java.util.*;

public class ToolsPro extends PluginBase {

    private static ToolsPro instance;
    public List<String> forbiddenNames;
    private Map<Integer, Object> tblocks = new HashMap<Integer, Object>();
    private static int LANG_VERSION = 1;
    private static int CONFIG_VERSION = 1;
    public Config mute;
    public static ToolsPro getPlugin() {
        return instance;
    }

    Set<String> GodPlayers = new HashSet<String>();
    Set<String> SaveInvPlayers = new HashSet<String>();
    Set<String> VanishPlayers = new HashSet<String>();

    @Override
    public void onEnable() {
        instance = this;
        this.loadConfig();
        Message.init(this);
        this.checkConfig();
        this.checkLanguage();
        this.checkVersion();
        this.registerCommands();
        this.registerEvents();
        Message.TOOLSPRO_LOADED.log();
    }

    @Override
    public void onDisable() {
        Message.TOOLSPRO_DISABLED.log('c');
    }

    private void registerCommands() {
        this.getServer().getCommandMap().register("antioch", new AntiochCommand(this));
        this.getServer().getCommandMap().register("break", new BreakCommand(this));
        this.getServer().getCommandMap().register("broadcast", new BroadcastCommand(this));
        this.getServer().getCommandMap().register("burn", new BurnCommand(this));
        this.getServer().getCommandMap().register("clearhotbar", new ClearHotBarCommand(this));
        this.getServer().getCommandMap().register("clearinventory", new ClearInventoryCommand(this));
        this.getServer().getCommandMap().register("compass", new CompassCommand(this));
        this.getServer().getCommandMap().register("depth", new DepthCommand(this));
        this.getServer().getCommandMap().register("extinguish", new ExtinguishCommand(this));
        this.getServer().getCommandMap().register("fly", new FlyCommand(this));
        this.getServer().getCommandMap().register("gamemode", new GamemodeCommand(this));
        this.getServer().getCommandMap().register("getpos", new GetPosCommand(this));
        this.getServer().getCommandMap().register("god", new GodCommand(this));
        this.getServer().getCommandMap().register("heal", new HealCommand(this));
        this.getServer().getCommandMap().register("itemban", new ItemBanCommand(this));
        this.getServer().getCommandMap().register("itemdb", new ItemDBCommand(this));
        this.getServer().getCommandMap().register("jump", new JumpCommand(this));
        this.getServer().getCommandMap().register("kickall", new KickAllCommand(this));
        this.getServer().getCommandMap().register("more", new MoreCommand(this));
        this.getServer().getCommandMap().register("mute", new MuteCommand(this));
        this.getServer().getCommandMap().register("realname", new RealNameCommand(this));
        this.getServer().getCommandMap().register("nuke", new NukeCommand(this));
        this.getServer().getCommandMap().register("repair", new RepairCommand(this));
        this.getServer().getCommandMap().register("saveinv", new SaveInvCommand(this));
        this.getServer().getCommandMap().register("setspawn", new SetSpawnCommand(this));
        this.getServer().getCommandMap().register("spawnall", new SpawnAllCommand(this));
        this.getServer().getCommandMap().register("spawn", new SpawnCommand(this));
        this.getServer().getCommandMap().register("speed", new SpeedCommand(this));
        this.getServer().getCommandMap().register("sudo", new SudoCommand(this));
        this.getServer().getCommandMap().register("suicide", new SuicideCommand(this));
        this.getServer().getCommandMap().register("toolspro", new ToolsProCommand(this));
        this.getServer().getCommandMap().register("top", new TopCommand(this));
        this.getServer().getCommandMap().register("tree", new TreeCommand(this));
        this.getServer().getCommandMap().register("unmute", new UnmuteCommand(this));
        //this.getServer().getCommandMap().register("vanish", new VanishCommand(this));
        //this.getServer().getCommandMap().register("whois", new WhoIsCommand(this));
        this.getServer().getCommandMap().register("world", new WorldCommand(this));
    }

    private void registerEvents() {
        this.getServer().getPluginManager().registerEvents(new DamageListener(this), this);
        this.getServer().getPluginManager().registerEvents(new EventListener(this), this);
        this.getServer().getPluginManager().registerEvents(new ItemBanListener(this), this);
        this.getServer().getPluginManager().registerEvents(new MuteListener(this), this);
    }

    private void checkLanguage() {
        String DefaultLang = this.getConfig().getString("general.language", "english");
        Config lang = new Config(new File(this.getDataFolder(), DefaultLang + ".lng"), Config.YAML);
        if (!DefaultLang.equalsIgnoreCase("russian") && lang.getInt("version") != LANG_VERSION) {
            Message.TOOLSPRO_CHECK_LANGUAGE.log('c', 'b', DefaultLang + ".lng");
        }
    }

    private void checkConfig() {
        int config = this.getConfig().getInt("general.config-version");
        if (config != CONFIG_VERSION) {
            Message.TOOLSPRO_CHECK_CONFIG.log('c');
        }
    }

    private void loadConfig() {
        this.saveDefaultConfig();
        this.mute = new Config(new File(this.getDataFolder(), "mute.yml"));
        try {
            this.forbiddenNames = this.getConfig().getList("forbidden-player-names");
        } catch (Exception e) {
            this.forbiddenNames = new ArrayList<String>();
        }
    }

    private void checkVersion() {
        boolean checkVersion = this.getConfig().getBoolean("updater.enabled", false);
        if (checkVersion == true) {
            String version = HttpRequest.sendGet("http://play.surfacecraft.ru/ToolsPro/updater.txt", "");
            if (!version.equals("") && version.equals(this.getToolsProVersion())) {
                Message.TOOLSPRO_UPDATER.log();
            } else if (!version.equals("") && !version.equals(this.getToolsProVersion())) {
                Message.TOOLSPRO_UPDATER_NEW_VERSION.log('b', 'b', version);
            } else {
                Message.TOOLSPRO_UPDATER_ERROR.log('c');
            }
        }
    }

    public String getToolsProName() {
        return this.getDescription().getName();
    }

    public String getToolsProVersion() {
        return this.getDescription().getVersion();
    }

    public List getToolsProAuthors() {
        return this.getDescription().getAuthors();
    }

    public String getToolsProWebSite() {
        return this.getDescription().getWebsite();
    }

    public boolean isRepairable(Item item) {
        return item instanceof ItemTool || item instanceof ItemArmor;
    }

    public boolean getPlayerMute(String name) {
        return mute.get(name.toLowerCase(), System.currentTimeMillis()) >= System.currentTimeMillis();
    }

    public boolean existsPlayerMute(String name) {
        return mute.exists(name.toLowerCase());
    }

    public void setPlayerMute(String name, double timings) {
        mute.set(name, System.currentTimeMillis() + Math.round(timings * 1000d));
        mute.save();
    }

    public void removePlayerMute(String name) {
        mute.remove(name.toLowerCase());
        mute.save();
    }

    public boolean getPlayerVanish(String name) {
        return VanishPlayers.contains(name.toLowerCase());
    }

    public void setPlayerVanish(String name) {
        VanishPlayers.add(name.toLowerCase());
        Player player = this.getServer().getPlayer(name);
        for (Player p : this.getServer().getOnlinePlayers().values()){
            p.hidePlayer(player);
        }
    }

    public void removePlayerVanish(String name) {
        if (VanishPlayers.contains(name.toLowerCase())) {
            VanishPlayers.remove(name.toLowerCase());
            Player player = this.getServer().getPlayer(name);
            for (Player p : this.getServer().getOnlinePlayers().values()){
                p.showPlayer(player);
            }
        }
    }

    public boolean getPlayerSaveInv(String name) {
        return SaveInvPlayers.contains(name.toLowerCase());
    }

    public void setPlayerSaveInv(String name) {
        SaveInvPlayers.add(name.toLowerCase());
    }

    public void removePlayerSaveInv(String name) {
        if (SaveInvPlayers.contains(name.toLowerCase())) SaveInvPlayers.remove(name.toLowerCase());
    }

    public boolean getPlayerGodMode(String name) {
        return GodPlayers.contains(name.toLowerCase());
    }

    public void setPlayerGodMode(String name) {
        GodPlayers.add(name.toLowerCase());
    }

    public void removePlayerGodMode(String name) {
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
        for (String str : ln) {
            if (sb == null) sb = new StringBuilder(str);
            else sb.append(" ").append(str);
        }
        return sb.toString();
    }

    public static final int [] NON_SOLID_BLOCKS = {Block.SAPLING, Block.WATER, Block.STILL_WATER, Block.LAVA, Block.STILL_LAVA, Block.COBWEB, Block.TALL_GRASS, Block.BUSH, Block.DANDELION,
            Block.POPPY, Block.BROWN_MUSHROOM, Block.RED_MUSHROOM, Block.TORCH, Block.FIRE, Block.WHEAT_BLOCK, Block.SIGN_POST, Block.WALL_SIGN, Block.SUGARCANE_BLOCK,
            Block.PUMPKIN_STEM, Block.MELON_STEM, Block.VINE, Block.CARROT_BLOCK, Block.POTATO_BLOCK, Block.DOUBLE_PLANT};

    public static final int [] ANTIOCH_BLOCKS = {Block.AIR, Block.WATER, Block.STILL_WATER, Block.LAVA, Block.STILL_LAVA};

    public boolean antioch(Player player) {
        tblocks.put(0, ANTIOCH_BLOCKS);
        Block block = player.getTargetBlock(100, tblocks);
        if (block == null) {
            return false;
        }
        this.createTNT(block.add(0, 1), player.getLevel());
        return true;
    }

    public void nuke(Player player) {
        for (int x = -10; x <= 10; x += 5) {
            for (int z = -10; z <= 10; z += 5) {
                this.createTNT(player.add(x, 0, z), player.getLevel());
            }
        }
    }

    public void createTNT(Vector3 pos, Level level) {
        if (level == null) {
            if (pos instanceof Position) {
                level = ((Position) pos).getLevel();
            } else {
                return;
            }
        }
        double mot = (new NukkitRandom()).nextSignedFloat() * Math.PI * 2;
        CompoundTag nbt = new CompoundTag()
                .putList(new ListTag<DoubleTag>("Pos")
                        .add(new DoubleTag("", pos.getFloorX() + 0.5))
                        .add(new DoubleTag("", pos.getFloorY()))
                        .add(new DoubleTag("", pos.getFloorZ() + 0.5)))
                .putList(new ListTag<DoubleTag>("Motion")
                        .add(new DoubleTag("", -Math.sin(mot) * 0.02))
                        .add(new DoubleTag("", 0.2))
                        .add(new DoubleTag("", -Math.cos(mot) * 0.02)))
                .putList(new ListTag<FloatTag>("Rotation")
                        .add(new FloatTag("", 0))
                        .add(new FloatTag("", 0)))
                .putByte("Fuse", (byte) 80);
        Entity tnt = new EntityPrimedTNT(level.getChunk(pos.getFloorX() >> 4, pos.getFloorZ() >> 4), nbt);
        tnt.spawnToAll();
    }
}