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
	LNG_LOAD_FAIL("Failed to load languages from file. Default message used"),
	LNG_SAVE_FAIL("Failed to save lang file"),
	LNG_PRINT_FAIL("Failed to print message %1%. Sender object is null."),
	LNG_CONFIG("[MESSAGES] Messages: %1% Language: %2% Save translate file: %1% Debug mode: %3%"),
    ENABLED("enabled"),
    DISABLED("disabled"),
	WORD_UNKNOWN("Unknown"),

 	TOOLSPRO_LOADED("ToolsPro успешно загружен!"),
    TOOLSPRO_DISABLED("ToolsPro успешно выключен!"),
    UNKNOWN_PLAYER("Такого игрока нет на сервере!"),
    NEED_PLAYER("Пожалуйста, используйте эту команду в игре!"),
    YOU_NOT_SURVIVAL("Ваш игрокой режим не выживание!"),
    PLAYER_NOT_SURVIVAL("Игровой режим игрока %1% не выживание!"),
    NOT_NUMBER("Пожалуйста, используйте число!"),

	//Break Command
	CMD_BREAK_DESCRIPTION("Разрушает блок на который вы смотрите."),
	//Broadcast Command
    CMD_BROADCAST_DESCRIPTION("Отправляет объявление всем игрокам на сервере."),
    CMD_BROADCAST_DESCRIPTION2("/broadcast <сообщение>"),
    CMD_BROADCAST_USAGE("Используйте: /broadcast <текст>"),
	//Burn Command
    CMD_BURN_DESCRIPTION("Поджигает определенного игрока"),
    CMD_BURN_DESCRIPTION2("/burn <ник> <время>"),
    CMD_BURN_PLAYER("Вы подожгли игрока %1%"),
    CMD_BURN_USAGE("Используйте: /burn <ник> <время>"),
    //ClearHotBar Command
    CMD_CLEARHOTBAR_DESCRIPTION("Очищает хот-бар."),
    CMD_CLEARHOTBAR_DESCRIPTION2("/clearhotbar или /clearhotbar <ник>"),
    CMD_CLEARHOTBAR_PLAYER_CLEAR("Хот-бар игрока %1% очищен!"),
    CMD_CLEARHOTBAR_PLAYER_CLEAR_LOG("%1% очистил хот бар игроку %2%!"),
    CMD_CLEARHOTBAR_SENDER_CLEAR("Ваш хот-бар был успешно очищен!"),
    CMD_CLEARHOTBAR_SENDER_CLEAR_LOG("%1% очистил себе хот бар!"),
    //ClearInventory Command
    CMD_CLEARINVENTORY_DESCRIPTION("Очищает инвентарь."),
    CMD_CLEARINVENTORY_DESCRIPTION2("/clearinventory или /clearinventory <ник>"),
    CMD_CLEARINVENTORY_PLAYER_CLEAR("Инвентарь %1% очищен!"),
    CMD_CLEARINVENTORY_SENDER_CLEAR("Ваш инвентарь был успешно очищен!"),
    //Compass Command
    CMD_COMPASS_DESCRIPTION("Показывает ваше текущее направление."),
    CMD_COMPASS_WRONGDIR("Простите, но нам не удаось определить ваше местонахождение"),
    CMD_COMPASS_VIEW("Вы смотрите на %1%"),
    //Depth Command
    CMD_DEPTH_DESCRIPTION("Показывает ваше текущее направление."),
    CMD_DEPTH_ABOVE("Вы находитесь на высоте %1% выше уровня моря"),
    CMD_DEPTH_BELOW("Вы находитесь на высоте %1% ниже уровня моря"),
    //Extinguish Command
    CMD_EXTHINGUISH_DESCRIPTION("Тушит горящего игрока."),
    CMD_EXTHINGUISH_DESCRIPTION2("/extinguish или /extinguish <ник>"),
    CMD_EXTHINGUISH_SENDER("Вы успешно потушили себя"),
    CMD_EXTHINGUISH_PLAYER_MESSAGE("Вас успешно потушили!"),
    CMD_EXTHINGUISH_PLAYER("Вы успешно потуишили игрока %1%!"),
    //Fly Command
    CMD_FLY_DESCRIPTION("Включает/выключает полет"),
    CMD_FLY_DESCRIPTION2("/fly или /fly <ник>"),
    CMD_FLY_PLAYER_ENABLED("Флай игроку %1% успешно включен!"),
    CMD_FLY_PLAYER_DISABLED("Флай игроку %1% успешно выключен!"),
    CMD_FLY_PLAYER_MESSAGE_ENABLED("Вам включили режим полета!"),
    CMD_FLY_PLAYER_MESSAGE_DISABLED("Вам выключили режим полета!"),
    CMD_FLY_SENDER_ENABLED("Вы успешно включили режим полета!"),
    CMD_FLY_SENDER_DISABLED("Вы успешно выключили режим полета!"),
    //Gamemode Command
    CMD_GAMEMODE_DESCRIPTION("Изменияет игровой режим."),
	CMD_GAMEMODE_DESCRIPTION2("/gm <игрокой режим>"),
    //God Command
    CMD_GOD_DESCRIPTION("Включает/выключает режим бога"),
	CMD_GOD_DESCRIPTION2("/god или /god <ник>"),
    //Health Command
    CMD_HEALTH_DESCRIPTION("Восстанавливает жизни."),
	CMD_HEALTH_DESCRIPTION2("/health или /health <ник>"),
	CMD_HEALTH_SENDER("Вы успешно вылечили себя!"),
	CMD_HEALTH_SENDER_MAX("У Вас полные жизни, лечение не требуется!"),
	CMD_HEALTH_PLAYER("Вы успешно вылечили игрока %1%!"),
	CMD_HEALTH_PLAYER_MESSAGE("Вас успешно вылечили!"),
    //ItemBan Command
    CMD_ITEMBAN_DESCRIPTION("Управляет списком заблокированных вещей"),
    CMD_ITEMBAN_WRONGID("Пожалуйста, введите верный ID!"),
    CMD_ITEMBAN_ALREADYBAN("Предмет %1% (ID - %2%) уже заблокирован!"),
    CMD_ITEMBAN_ADDED("Предмет %1% (ID - %2%) добавлен в список"),
    CMD_ITEMBAN_BAN("Предмет %1% (ID - %2%) заблокирован!"),
    CMD_ITEMBAN_REMOVED("Предмет %1% (ID - %2%) удален из списка!"),
    CMD_ITEMBAN_USAGE("Используйте /item <ban|unban> <ID>"),
    //ItemDB Command
    CMD_ITEMDB_DESCRIPTION(""),
    //Jump Command
    CMD_JUMP_DESCRIPTION("Телепортирует вас на место которое вы укажите"),
    //KickAll Command
    CMD_KICKALL_DESCRIPTION("Кикнает всех игроков с сервера."),
    CMD_KICKALL_DESCRIPTION2("/kickall или /kickall <причина>"),
    CMD_KICKALL_NO_PLAYERS("На сервере нет ни одного игрока!"),
    CMD_KICKALL_NO_REASON("Вы были кикнуты с сервера!"),
    CMD_KICKALL_ALL_SUCCESSFULLY_KICKED("Все игроки были кикнуты с сервера!"),
    //More Command
    CMD_MORE_DESCRIPTION("Увеличивает предмет в руке до стака."),
    CMD_MORE_NO_AIR("Вы не можете использовать воздух!"),
    CMD_MORE_SUCCESSFULLY("Вы успешно увеличили предмет в руке до"),
    //Mute Command
    CMD_MUTE_DESCRIPTION(""),
	CMD_MUTE_DESCRIPTION2("/mute <ник> <время> <seconds|minutes|hours|days>"),
    //Repair Command
    CMD_REPAIR_DESCRIPTION("Починит инструмент, броню, зачарованный предмет."),
    //SaveInv Command
    CMD_SAVEINV_DESCRIPTION("Включает/выключает сохранение инвентаря."),
	CMD_SAVEINV_DESCRIPTION2("/saveinv или /saveinv <ник>"),
    //SetSpawn Command
    CMD_SETSPAWN_DESCRIPTION("Устанавливает спавн в данной локации"),
	CMD_SETSPAWN("Точка спавна игроков успешно установлена!"),
	CMD_SETSPAWN_LOG(""),
    //SpawnAll Command
    CMD_SPAWNALL_DESCRIPTION("Телепортирует всех игроков на спавн."),
	CMD_SPAWNALL_NO_PLAYERS("На сервере нет ни одного игрока!"),
    CMD_SPAWNALL_PLAYER_TP_TO_SPAWN("Вы были телепортированы на спавн!"),
    //Spawn Command
    CMD_SPAWN_DESCRIPTION("Телепортирует на спавн."),
	CMD_SPAWN_TP_PLAYER_MESSAGE("Телепортация..."),
	CMD_SPAWN_TP_SENDER_MESSAGE("Вы успешно телепортировали игрока %1% на спавн!"),
    //Speed Command
    CMD_SPEED_DESCRIPTION("Меняет скорость движения игрока."),
	CMD_SPEED_DESCRIPTION2("/speed или /speed <число>"),
    CMD_SPEED_NORMAL("Вы успешно сбросили скорость на стандартную!"),
    //Suicide Command
    CMD_SUICIDE_DESCRIPTION("Совершает самоубийство."),
    CMD_SUICIDE_MESSAGE("Вы покончили жизнь самоубийством!"),
    //Top Command
    CMD_TOP_DESCRIPTION("Телепортирует вас на самый верхний блок над вашей позицией"),
    CMD_TOP_TP_MESSAGE("Телепортация..."),
    //Unmute Command
    CMD_UNMUTE_DESCRIPTION(""),
	CMD_UNMUTE_DESCRIPTION2("/unmute <ник>"),
    //Vanish Command
    CMD_VANISH_DESCRIPTION(""),
	CMD_VANISH_DESCRIPTION2("/vanish или /vanish <ник>"),
    //Directions
    SOUTH("Юг"),
    NORTH("Север"),
    WEST("Запад"),
    EAST("Восток");

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
		language = plg.getConfig().getNested("general.language", "english");
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
