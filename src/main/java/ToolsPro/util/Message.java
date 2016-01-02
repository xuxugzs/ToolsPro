package ToolsPro.util;

import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.command.CommandSender;
import cn.nukkit.level.Location;
import cn.nukkit.plugin.PluginBase;
import cn.nukkit.utils.Config;
import cn.nukkit.utils.TextFormat;

import java.io.File;
import java.text.DecimalFormat;


public enum Message {

	//Default (lang) messages
	LNG_LOAD_FAIL ("Failed to load languages from file. Default message used"),
	LNG_SAVE_FAIL ("Failed to save lang file"),
	LNG_PRINT_FAIL ("Failed to print message %1%. Sender object is null."),
	LNG_CONFIG ("[MESSAGES] Messages: %1% Language: %2% Save translate file: %1% Debug mode: %3%"),
    ENABLED ("enabled"),
    DISABLED ("disabled"),
	WORD_UNKNOWN ("Unknown"),


 	TPRO_LOADED("ToolsPro успешно загружен!"),
    TPRO_DISABLED("ToolsPro успешно выключен!"),
    UNKNOWNPLAYER("Такого игрока нет на сервере!"),
    NEEDPLAYER("Пожалуйста, используйте эту команду в игре!"),
    YOU_NOT_SURV("Ваш игрокой режим не выживание!"),
    NOT_SURV("Игровой режим игрока %1% не выживание!"),
	CMD_BREAK_DESC("Ломает блок, который находится перед Вами.."),
    CMD_BROADCAST_DESC("Отправляет сообщение в чат"),
    CMD_BROADCAST_USAGE("Используйте: /broadcast <текст>"),
    CMD_BURN_DESC("Поджигает определенного игрока"),
    CMD_BURN_PLYR("Вы подожгли игрока &b%1%"), CMD_ITEM_DESC("Управляет списком заблокированных вещей"),
    CMD_ITEM_WRONGID("Пожалуйста, введите верный ID!"),
    CMD_ITEM_ALREADYBAN("Предмет %1% (ID - %2%) уже заблокирован!"), CMD_ITEM_ADDED("Предмет %1% (ID - %2%) добавлен в список"),
    CMD_ITEM_BAN("Предмет %1% (ID - %2%) заблокирован!"),
    CMD_ITEM_REMOVED("Предмет %1% (ID - %2%) удален из списка!"), CMD_ITEM_USAGE("Используйте /item <ban|unban> <ID>"),
    CMD_CLRHOT_DESC("Очищает хот-бар"), CMD_CLRHOT_DESC2("/clearhotbar или /clearhotbar <ник>"),
    CMD_CLRHOT_CLEAR("Хот бар игрока %1% очищен!"),
    CMD_CLRHOT_CLEAR_LOG("%1% очистил хот бар игроку %2%!"), CMD_CLRHOT_CLS("Ваш хот бар был успешно очищен!"), CMD_CLRHOT_CLS_LOG("%1% очистил себе хот бар!"),
    CMD_CI_DESC("Очищает инвентарь"), CMD_CI_DESC2("/clearinventory или /clearinventory <ник>"),
    CMD_CI_INVCLEAR("Инвентарь %1% очищен!"),
    CMD_CI_YINVCLEAR("Ваш инвентарь был успешно очищен!"), CMD_COMPASS_DESC("Показывает название стороны света в которую вы смотрите"),
    SOUTH("Юг"),NORTH("Север"),WEST("Запад"),EAST("Восток"),
    CMD_COMPASS_WRONGDIR("Простите, но нам не удаось определить ваше местонахождение"),
    CMD_COMPASS_VIEW("Вы смотрите на %1%"),
    CMD_DEPTH_DESC("Показывает Ваше местонахождение относительно океана"),
    CMD_DEPTH_ABOVE("Вы находитесь на высоте %1% выше уровня моря"),
    CMD_DEPTH_BELOW("Вы находитесь на высоте %1% ниже уровня моря"),
    CMD_EXT_DESC("Позволяет управлять скоростью персонажа"),
    CMD_EXT_DESC2("/extinguish или /extinguish <ник>"),
    CMD_EXT_SELFOK("Вы успешно потушили себя"),
    CMD_EXT_OKYOU("Вас успешно потушили!"), CMD_EXT_OK("Вы успешно потуишили игрока %1%!"),
    CMD_FLY_DESC("Включает/выключает полет"),
    CMD_FLY_DESC2("/fly или /fly <ник>"),
    CMD_FLY_PLR_DISABLED("Флай игроку %1% успешно выключен!"),
    CMD_FLY_PLR_ENABLED("Флай игроку %1% успешно включен!"),
    CMD_FLY_PLRY_DISABLED("Вам выключили режим полета!"),
    CMD_FLY_PLRY_ENABLED("Вам включили режим полета!"),
    CMD_FLY_ENABLED("Вы успешно включили режим полета!"),
    CMD_FLY_DISABLED("Вы успешно выключили режим полета!");






	private static PluginBase plugin = null;
	private static boolean debugMode = false;
	private static String language = "russian";
	//private static boolean languageSave=false;
	private static char c1 = 'a';
	private static char c2 = '2';



	/**
	 * This is my favorite debug routine :) I use it everywhere to print out variable values
	 * @param s - array of any object that you need to print out.
	 * Example:
	 * Message.BC ("variable 1:",var1,"variable 2:",var2)
	 */
	public static void BC (Object... s){
		if (!debugMode) return;
		if (s.length==0) return;


		StringBuilder sb = new StringBuilder("&3[").append(plugin.getDescription().getName()).append("]&f ");
		for (Object str : s)
			sb.append(str.toString()).append(" ");
		plugin.getServer().broadcastMessage(TextFormat.colorize(sb.toString().trim()));
	}



	/**
	 * Send current message to log files
	 * @param s
	 * @return вЂ” always returns true.
	 * Examples:
	 * Message.ERROR_MESSAGE.log(variable1); // just print in log
	 * return Message.ERROR_MESSAGE.log(variable1); // print in log and return value true
	 */
	public boolean log(Object... s){
		plugin.getLogger().info(getText (s));
		return true;
	}

	/**
	 * Same as log, but will printout nothing if debug mode is disabled
	 * @param s
	 * @return
	 */
	public boolean debug(Object... s){
		if (debugMode) plugin.getLogger().info(TextFormat.clean(getText (s)));
		return true;
	}

	public boolean tip (int seconds, CommandSender sender, Object... s){
		if (sender == null) return Message.LNG_PRINT_FAIL.log(this.name());
		final Player player = sender instanceof Player ? (Player) sender : null;
		final String message = getText(s);
		if (player==null) sender.sendMessage(message);
		else for (int i=0;i<seconds;i++) Server.getInstance().getScheduler().scheduleDelayedTask(new Runnable() {
			public void run() {
				if (player.isOnline()) player.sendTip(message);
			}
		},20*i);
		return true;
	}

	public boolean tip (CommandSender sender, Object... s){
		if (sender == null) return Message.LNG_PRINT_FAIL.log(this.name());
		Player player = sender instanceof Player ? (Player) sender : null;
		String message = getText(s);
		if (player==null) sender.sendMessage(message);
		else player.sendTip(message);
		return true;
	}

	/**
	 * Send message to Player or to ConsoleSender
	 * @param sender
	 * @param s
	 * @return
	 */
	public boolean print (CommandSender sender, Object... s){
		if (sender == null) return Message.LNG_PRINT_FAIL.log(this.name());
		sender.sendMessage(getText(s));
		return true;
	}

	/**
	 * Send message to all players or to players with defined permission
	 * @param permission
	 * @param s
	 * @return
	 *
	 * Examples:
	 * Message.MSG_BROADCAST.broadcast ("pluginname.broadcast"); // send message to all players with permission "pluginname.broadcast"
	 * Message.MSG_BROADCAST.broadcast (null); // send message to all players
	 */
	public boolean broadcast (String permission, Object... s){
		for (Player player : plugin.getServer().getOnlinePlayers().values()){
			if (permission==null || player.hasPermission(permission)) print (player,s);
		}
		return true;
	}


	/**
	 * Get formated text.
	 * @param keys
	 * @return
	 */
	public String getText (Object... keys){
		if (keys.length ==0) return TextFormat.colorize("&"+c1+this.message);
		String str = this.message;
		boolean noColors = false;
		boolean skipDefaultColors = false;
		boolean fullFloat = false;
        String prefix="";
		int count=1;
		char [] colors = new char[]{c1,c2};
		int c = 0;
		DecimalFormat fmt = new DecimalFormat("####0.##");
		for (int i = 0; i<keys.length; i++){
			String s = keys[i].toString();
			if (c<2&&keys[i] instanceof Character){
				colors[c] = (Character) keys[i];
				c++;
				continue;
            } else if (s.startsWith("prefix:")) {
                prefix = s.replace("prefix:","");
				continue;
			} else if (s.equals("SKIPCOLOR")) {
				skipDefaultColors = true;
				continue;
			} else if (s.equals("NOCOLORS")||s.equals("NOCOLOR")) {
				noColors = true;
				continue;
			} else if (s.equals("FULLFLOAT")) {
				fullFloat = true;
				continue;
			} else if (keys[i] instanceof Location) {
				Location loc = (Location) keys[i];
				if (fullFloat) s = loc.getLevel().getName()+"["+loc.getX()+", "+loc.getY()+", "+loc.getZ()+"]";
				else s = loc.getLevel().getName()+"["+fmt.format(loc.getX())+", "+fmt.format(loc.getY())+", "+fmt.format(loc.getZ())+"]";
			} else if (keys[i] instanceof Double || keys[i] instanceof Float) {
				if (!fullFloat) s = fmt.format((Double) keys[i]);
			}

			String from = (new StringBuilder("%").append(count).append("%")).toString();
			String to = skipDefaultColors ? s :(new StringBuilder("&").append(colors[1]).append(s).append("&").append(colors[0])).toString();
			str = str.replace(from, to);
			count++;
		}
		str = TextFormat.colorize(prefix.isEmpty() ?"" : prefix+" " +"&"+colors[0]+str);
		if (noColors) str = TextFormat.clean(str);
		return str;
	}

	public void initMessage (String message){
		this.message = message;
	}

	private String message;
	Message(String msg){
		message = msg;
	}


	///////////////////////////////////////////////////////////////////////////////////////////////
	public static void init(PluginBase plg){
		plugin = plg;
        plugin.getDataFolder().mkdirs();
		language = plg.getConfig().getNested("general.language","english    ");
		plg.getConfig().setNested("general.language", language);
		debugMode = plg.getConfig().getNested("general.debug-mode",false);
		plg.getConfig().setNested("general.debug-mode",debugMode);
		plg.saveConfig();
		initMessages();
		saveMessages();
		LNG_CONFIG.debug(Message.values().length,language,true,debugMode);
	}

	public static void setDebugMode (boolean debug){
		debugMode = debug;
	}

	private static boolean copyLanguage(){
		return plugin.saveResource("lang/" +language+".lng",language+".lng",false);
	}

	private static void initMessages(){
		copyLanguage();

		Config lng = null;
		try {
			File f = new File (plugin.getDataFolder()+File.separator+language+".lng");
			lng = new Config(f,Config.YAML);
		} catch (Exception e){
			LNG_LOAD_FAIL.log();
			if (debugMode) e.printStackTrace();
			return;
		}
		for (Message key : Message.values())
			key.initMessage((String) lng.get(key.name().toLowerCase(), key.message));
	}

	private static void saveMessages(){
		File f = new File (plugin.getDataFolder()+File.separator+language+".lng");
		Config lng = new Config(f,Config.YAML);
		for (Message key : Message.values())
			lng.set(key.name().toLowerCase(), key.message);
		try {
			lng.save();
		} catch (Exception e){
			LNG_SAVE_FAIL.log();
			if (debugMode) e.printStackTrace();
			return;
		}
	}

	public static boolean debugMessage (Object... s){
		if (debugMode) plugin.getLogger().info(TextFormat.clean(join (s)));
		return true;

	}

	public static String join (Object... s){
		StringBuilder sb = new StringBuilder();
		for (Object o : s){
			if (sb.length()>0) sb.append(" ");
			sb.append(o.toString());
		}
		return sb.toString();
	}

    @Override
    public String toString(){
        return this.getText("NOCOLOR");
    }
}