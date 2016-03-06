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

    //Default (plugin) message
    TOOLSPRO_LOADED("ToolsPro успешно загружен!"),
    TOOLSPRO_DISABLED("ToolsPro успешно выключен!"),
    //Updater
    TOOLSPRO_UPDATER("У Вас установлена самая новая версия ToolsPro!"),
    TOOLSPRO_UPDATER_NEW_VERSION("Доступная новая версия плагина! ToolsPro v%1%"),
    TOOLSPRO_UPDATER_ERROR("При поиске обновлений плагина произошла ошибка!"),
    //Check Language
    TOOLSPRO_CHECK_LANGUAGE("Языковой файл %1% устарел, пожалуйста, обновите его."),
    //Check Config
    TOOLSPRO_CHECK_CONFIG("Конфигурация ToolsPro устарела, пожалуйста, удалите config.yml и перезапустите сервер!"),
    //Error
    YOU_DONT_HAVE_PERMISSION("У Вас недостаточно прав для использования этой команды!"),
    UNKNOWN_PLAYER("Такого игрока нет на сервере!"),
    YOU_NOT_SURVIVAL_OR_ADVENTURE("Ваш игровой режим не выживание или не режим приключений!"),
    PLAYER_NOT_SURVIVAL_OR_ADVENTURE("Игровой режим игрока %1% не выживание или не режим приключений!"),
    NEED_PLAYER("Пожалуйста, используйте эту команду в игре!"),
    NOT_NUMBER("Пожалуйста, введите верное число!"),
    NOT_TIME("Пожалуйста, укажите верное значение времени!"),
    BLOCKED_NICK("Пожалуйста, измените ник и перезайдите на сервер!"),
    IS_NOT_A_REACHABLE_BLOCK("К сожалению, мы не можем определить блок на который вы смотрите!"),

    //Antioch Command
    CMD_ANTIOCH_DESCRIPTION("Бросает гранату."),
    CMD_ANTIOCH_GRENADE("Вы бросили гранату!"),
    CMD_ANTIOCH_GRENADE_ERROR("Вы не можете бросить гранату, потому что рядом нет действительного блока!"),
    //Break Command
    CMD_BREAK_DESCRIPTION("Разрушает блок на который вы смотрите."),
    CMD_BREAK_NO_BREAK_BEDROCK("Вы не можете сломать бедрок!"),
    //Broadcast Command
    CMD_BROADCAST_DESCRIPTION("Отправляет объявление всем игрокам на сервере."),
    CMD_BROADCAST_DESCRIPTION2("/broadcast <сообщение>"),
    CMD_BROADCAST_USAGE("Используйте: /broadcast <ссобщение>"),
    //Burn Command
    CMD_BURN_DESCRIPTION("Поджигает определенного игрока"),
    CMD_BURN_DESCRIPTION2("/burn <ник> <время>"),
    CMD_BURN_USAGE("Используйте: /burn <ник> <время>"),
    CMD_BURN_PLAYER("Вы подожгли игрока %1%"),
    CMD_BURN_PLAYER_INFO("Игрок %1% поджег игрока %2%!"),
    //ClearChat Command
    CMD_CLEARCHAT_DESCRIPTION("Очищает чат от сообщений."),
    CMD_CLEARCHAT_DESCRIPTION2("/clearchat или /clearchat <сообение>"),
    //ClearHotBar Command
    CMD_CLEARHOTBAR_DESCRIPTION("Очищает хот-бар."),
    CMD_CLEARHOTBAR_DESCRIPTION2("/clearhotbar или /clearhotbar <ник>"),
    CMD_CLEARHOTBAR_SENDER("Ваш хот-бар был успешно очищен!"),
    CMD_CLEARHOTBAR_SENDER_INFO("Игрок %1% очистил себе хот бар!"),
    CMD_CLEARHOTBAR_PLAYER("Хот-бар игрока %1% очищен!"),
    CMD_CLEARHOTBAR_PLAYER_INFO("Игрок %1% очистил хот бар игроку %2%!"),
    //ClearInventory Command
    CMD_CLEARINVENTORY_DESCRIPTION("Очищает инвентарь."),
    CMD_CLEARINVENTORY_DESCRIPTION2("/clearinventory или /clearinventory <ник>"),
    CMD_CLEARINVENTORY_SENDER("Ваш инвентарь был успешно очищен!"),
    CMD_CLEARINVENTORY_SENDER_INFO("Игрок %1% очистил себе инвентарь!"),
    CMD_CLEARINVENTORY_PLAYER("Инвентарь игрока %1% очищен!"),
    CMD_CLEARINVENTORY_PLAYER_INFO("Игрок %1% очистил инвентарь игроку %2%!"),
    //Compass Command
    CMD_COMPASS_DESCRIPTION("Показывает ваше текущее направление."),
    CMD_COMPASS_WRONGDIR("Простите, но нам не удаось определить ваше местонахождение"),
    CMD_COMPASS_VIEW("Вы смотрите на %1%"),
    //Depth Command
    CMD_DEPTH_DESCRIPTION("Показывает ваше текущее направление."),
    CMD_DEPTH_ABOVE("Вы находитесь на высоте %1% выше уровня моря"),
    CMD_DEPTH_BELOW("Вы находитесь на высоте %1% ниже уровня моря"),
    //Extinguish Command
    CMD_EXTINGUISH_DESCRIPTION("Тушит горящего игрока."),
    CMD_EXTINGUISH_DESCRIPTION2("/extinguish или /extinguish <ник>"),
    CMD_EXTINGUISH_SENDER("Вы успешно потушили себя"),
    CMD_EXTINGUISH_SENDER_INFO("Игрок %1% потушил себя!"),
    CMD_EXTINGUISH_PLAYER("Вы успешно потуишили игрока %1%!"),
    CMD_EXTINGUISH_PLAYER_INFO("Игрок %1% потушил игрока %2%!"),
    CMD_EXTINGUISH_PLAYER_MESSAGE("Вас успешно потушили!"),
    //Fly Command
    CMD_FLY_DESCRIPTION("Включает/выключает полет"),
    CMD_FLY_DESCRIPTION2("/fly или /fly <ник>"),
    CMD_FLY_SENDER_ENABLE("Вы успешно включили режим полета!"),
    CMD_FLY_SENDER_DISABLE("Вы успешно выключили режим полета!"),
    CMD_FLY_SENDER_ENABLE_INFO("Игрок %1% включил себе флай!"),
    CMD_FLY_SENDER_DISABLE_INFO("Игрок %1% выключил себе флай!"),
    CMD_FLY_PLAYER_ENABLE("Флай игроку %1% успешно включен!"),
    CMD_FLY_PLAYER_DISABLE("Флай игроку %1% успешно выключен!"),
    CMD_FLY_PLAYER_ENABLE_INFO("Игрок %1% включил флай игроку %2%!"),
    CMD_FLY_PLAYER_DISABLE_INFO("Игрок %1% выключил флай игроку %2%!"),
    CMD_FLY_PLAYER_ENABLE_MESSAGE("Вам включили режим полета!"),
    CMD_FLY_PLAYER_DISABLE_MESSAGE("Вам выключили режим полета!"),
    //Gamemode Command
    CMD_GAMEMODE_DESCRIPTION("Изменияет игровой режим."),
    CMD_GAMEMODE_DESCRIPTION2("/gamemode <игрокой режим> <ник>"),
    CMD_GAMEMODE_USAGE("Пожалуйста, используйте %1% для просмотра всех игровых режимов"),
    CMD_GAMEMODE_SENDER_ALREADY_IN_GAMEMODE("Вы уже находитесь в %1%!"),
    CMD_GAMEMODE_SENDER_SUCCESSFULLY_CHANGED_GAMEMODE("Вы успешно изменили игровой режим на %1%!"),
    CMD_GAMEMODE_SENDER_SUCCESSFULLY_CHANGED_GAMEMODE_INFO("Игрок %1% изменил свой игровой режим на %2%!"),
    CMD_GAMEMODE_PLAYER_ALREADY_IN_GAMEMODE("Игрок %1% уже находится в %2%"),
    CMD_GAMEMODE_PLAYER_SUCCESSFULLY_CHANGED_GAMEMODE("Вы успешно изменили игровой режим игроку %1% на %2%!"),
    CMD_GAMEMODE_PLAYER_SUCCESSFULLY_CHANGED_GAMEMODE_MESSAGE("Ваш игровой режим успешно изменен на %1%!"),
    CMD_GAMEMODE_PLAYER_SUCCESSFULLY_CHANGED_GAMEMODE_INFO("Игрок %1% изменил игровой режим игрока %2% на %3%!"),
    CMD_GAMEMODE_HELP1("Список игровых режимов&7:"),
    CMD_GAMEMODE_HELP2("Изменяет игровой режим на выживание"),
    CMD_GAMEMODE_HELP3("Изменяет игровой режим на креатив"),
    CMD_GAMEMODE_HELP4("Изменяет игровой режим на режим приключений"),
    CMD_GAMEMODE_HELP5("Изменяет игровой режим на режим наблюдения"),
    //Gamemode Listener
    LISTENER_JOIN_SURVIVAL("Ваш игровой режим был изменен на выживание!"),
    //GetPos Command
    CMD_GETPOS_DESCRIPTION("Показывает вашу позицию."),
    CMD_GETPOS_DESCRIPTION2("/getpos или /getpos <ник>"),
    CMD_GETPOS_SENDER("Вы находитесь в мире: "),
    CMD_GETPOS_PLAYER(" находится в мире: "),
    CMD_GETPOS_COORDINATES("Координаты: "),
    //God Command
    CMD_GOD_DESCRIPTION("Включает/выключает режим бога."),
    CMD_GOD_DESCRIPTION2("/god или /god <ник>"),
    CMD_GOD_SENDER_ENABLE("Вы успешно включили режим бога!"),
    CMD_GOD_SENDER_DISABLE("Вы успешно выключили режим бога!"),
    CMD_GOD_SENDER_ENABLE_INFO("Игрок %1% включил себе режим бога!"),
    CMD_GOD_SENDER_DISABLE_INFO("Игрок %1% выключил себе режим бога!"),
    CMD_GOD_PLAYER_ENABLE("Режим бога игроку %1% успешно включен!"),
    CMD_GOD_PLAYER_DISABLE("Режим бога игроку %1% успешно выключен!"),
    CMD_GOD_PLAYER_ENABLE_INFO("Игрок %1% включил режим бога игроку %2%!"),
    CMD_GOD_PLAYER_DISABLE_INFO("Игрок %1% выключил режим бога игроку %2%!"),
    CMD_GOD_PLAYER_ENABLE_MESSAGE("Вам включили режим бога!"),
    CMD_GOD_PLAYER_DISABLE_MESSAGE("Вам выключили режим бога!"),
    //Heal Command
    CMD_HEAL_DESCRIPTION("Восстанавливает жизни."),
    CMD_HEAL_DESCRIPTION2("/heal или /heal <ник>"),
    CMD_HEAL_SENDER("Вы успешно вылечили себя!"),
    CMD_HEAL_SENDER_MAX("У Вас полные жизни, лечение не требуется!"),
    CMD_HEAL_SENDER_INFO("Игрок %1% вылечил себя!"),
    CMD_HEAL_PLAYER("Вы успешно вылечили игрока %1%!"),
    CMD_HEAL_PLAYER_MAX("У игрока %1% полные жизни, лечение не требуется!"),
    CMD_HEAL_PLAYER_INFO("Игрок %1% вылечил игрока %2%!"),
    CMD_HEAL_PLAYER_MESSAGE("Вас успешно вылечили!"),
    //ItemBan Command
    CMD_ITEMBAN_DESCRIPTION("Управляет списком заблокированных вещей."),
    CMD_ITEMBAN_USAGE("Используйте: /item <ban|unban> <ID>"),
    CMD_ITEMBAN_WRONGID("Пожалуйста, введите верный ID!"),
    CMD_ITEMBAN_ALREADYBAN("Предмет %1% (ID - %2%) уже заблокирован!"),
    CMD_ITEMBAN_ALREADYREMOVED("Предмет %1% (ID - %2%) не заблокирован!"),
    CMD_ITEMBAN_ADDED("Предмет %1% (ID - %2%) добавлен в список"),
    CMD_ITEMBAN_REMOVED("Предмет %1% (ID - %2%) удален из списка!"),
    //ItemBan Listener
    LISTENER_ITEMBAN_TOUCH("Вы не можете использовать этот предмет!"),
    LISTENER_ITEMBAN_TOUCH_BLOCK("Вы не можете взаимодействовать с этим блоком!"),
    LISTENER_ITEMBAN_EAT("Вы не можете это съесть!"),
    LISTENER_ITEMBAN_PLACE("Вы не можете ставить этот блок!"),
    LISTENER_ITEMBAN_BREAK("Вы не можете сломать этот блок!"),
    LISTENER_ITEMBAN_TIP("Этот предмет запрещен!"),
    //ItemDB Command
    CMD_ITEMDB_DESCRIPTION("Отображает информацию о предмете, который находится в ваших руках."),
    CMD_ITEMDB_DESCRIPTION2("/item db или /itemdb <name|id|metadata>"),
    CMD_ITEMDB_MESSAGE1("Этот предмет "),
    CMD_ITEMDB_MESSAGE2("имеет "),
    CMD_ITEMDB_REPAIRABLE(" очков урона"),
    CMD_ITEMDB_DATA("Значение meta-data у этого предмета: "),
    CMD_ITEMDB_NAMED("Название этого предмета: %1%"),
    CMD_ITEMDB_ID("Идентификатор этого предмета: %1%"),
    //Jump Command
    CMD_JUMP_DESCRIPTION("Телепортирует вас на тот блок, на который вы смотрите."),
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
    CMD_MUTE_DESCRIPTION("Блокирует чат игроку на определенное время."),
    CMD_MUTE_DESCRIPTION2("/mute <ник> <время> <seconds|minutes|hours|days>"),
    CMD_MUTE_USAGE("Используйте: /mute <ник> <время> <seconds|minutes|hours|days>"),
    CMD_MUTE_NO_MORE_30_DAY("Вы не можете замутить игрока больше чем на 30 дней!"),
    CMD_MUTE_SENDER("%1% был замучен на %2%"),
    CMD_MUTE_PLAYER_INFO("Игрок %1% замутил игрока %2% на %3%!"),
    CMD_MUTE_PLAYER_MESSAGE("Вы получили мут на %1% и теперь не можете писать в чат"),
    //Mute Listener
    LISTENER_MUTE_LINE1("Вы были замучены за нарушение правил чата!"),
    LISTENER_MUTE_LINE2("Размут через %1%"),
    //RealName Command
    CMD_REALNAME_DESCRIPTION("Показывает реальное имя игрока."),
    CMD_REALNAME_DESCRIPTION2("/realname <ник>"),
    CMD_REALNAME_USAGE("Используйте: /realname <ник>"),
    CMD_REALNAME("Реальное имя игрока %1%: %2%"),
    //Repair Command
    CMD_REPAIR_DESCRIPTION("Починит инструмент, броню, зачарованный предмет."),
    CMD_REPAIR_SUCCESSFULLY_REPAIRED("Предмет успешно отремонтирован!"),
    CMD_REPAIR_ALL_SUCCESSFULLY_REPAIRED("Все ваши предметы были успешно отремонтированы!"),
    CMD_REPAIR_SUCCESSFULLY_REPAIRED_INCLUDING_ARMOR("(Включая надетую броню)"),
    CMD_REPAIR_ERROR("Этот предмет нельзя отремонтировать!"),
    //ReturnOP Command
    CMD_RETURNOP_DESCRIPTION("Временно забирает права администратора."),
    CMD_RETURNOP_ENABLE("Вы успешно выключили права администратора"),
    CMD_RETURNOP_DISABLE("Вы успешно включили права администратора"),
    CMD_RETURNOP_ENABLE_INFO("Игрок %1% выключил себе права администратора"),
    CMD_RETURNOP_DISABLE_INFO("Игрок %1% включил себе права администратора"),
    //SaveInv Command
    CMD_SAVEINV_DESCRIPTION("Включает/выключает сохранение инвентаря."),
    CMD_SAVEINV_DESCRIPTION2("/saveinv или /saveinv <ник>"),
    CMD_SAVEINV_SENDER_ENABLE("Вы успешно включили сохранение инвентаря!"),
    CMD_SAVEINV_SENDER_DISABLE("Вы успешно выключили сохранение инвентаря!"),
    CMD_SAVEINV_SENDER_ENABLE_INFO("Игрок %1% включил себе сохранение инвентаря!"),
    CMD_SAVEINV_SENDER_DISABLE_INFO("Игрок %1% выключил себе сохранение инвентаря!"),
    CMD_SAVEINV_PLAYER_ENABLE("Вы успешно включили сохранение инвентаря игроку %1%!"),
    CMD_SAVEINV_PLAYER_DISABLE("Вы успешно выключили сохранение инвентаря игроку %1%!"),
    CMD_SAVEINV_PLAYER_ENABLE_INFO("Игрок %1% включил сохранение инвентаря игроку %2%!"),
    CMD_SAVEINV_PLAYER_DISABLE_INFO("Игрок %1% выключил сохранение инвентаря игроку %2%!"),
    CMD_SAVEINV_PLAYER_ENABLE_MESSAGE("Вам включили сохранение инвентаря!"),
    CMD_SAVEINV_PLAYER_DISABLE_MESSAGE("Вам выключили сохранение инвентаря!"),
    //SaveInv Listener
    LISTENER_SAVEINV_JOIN_TO_SERVER("Сохранение инвентаря успешно включено!"),
    LISTENER_SAVEINV_DEATH("Все ваши вещи были успешно сохранены!"),
    //SetSpawn Command
    CMD_SETSPAWN_DESCRIPTION("Устанавливает спавн в данной локации."),
    CMD_SETSPAWN("Точка спавна игроков успешно установлена!"),
    CMD_SETSPAWN_LOG("Игроком %1% задана новая точка спавна: %2% "),
    //SpawnAll Command
    CMD_SPAWNALL_DESCRIPTION("Телепортирует всех игроков на спавн."),
    CMD_SPAWNALL_NO_PLAYERS("На сервере нет ни одного игрока!"),
    CMD_SPAWNALL_PLAYER_TP_TO_SPAWN("Вы были телепортированы на спавн!"),
    //Spawn Command
    CMD_SPAWN_DESCRIPTION("Телепортирует на спавн."),
    CMD_SPAWN_COOLDOWN("Вы должны подождать %1% секунд(ы) перед использованием этой команды!"),
    CMD_SPAWN_TP_SENDER("Вы успешно телепортировали игрока %1% на спавн!"),
    CMD_SPAWN_TP_PLAYER_MESSAGE("Телепортация..."),
    //Speed Command
    CMD_SPEED_DESCRIPTION("Меняет скорость движения игрока."),
    CMD_SPEED_DESCRIPTION2("/speed или /speed <число>"),
    CMD_SPEED("Ваша скорость была изменена на %1%"),
    CMD_SPEED_NORMAL("Вы успешно сбросили скорость на стандартную!"),
    //Sudo Command
    CMD_SUDO_DESCRIPTION("Выполняет команду или отправляет сообщение за другого игрока."),
    CMD_SUDO_DESCRIPTION2("/sudo <ник> <команда> или /sudo <ник> <c:сообщение>"),
    CMD_SUDO_USAGE("Используйте: /sudo <ник> <команда> или /sudo <ник> <c:сообщение>"),
    CMD_SUDO_SEND_MESSAGE("Вы оправили сообщение за игрока %1%"),
    CMD_SUDO_USE_COMMAND("Вы использовали команду за игрока %1%"),
    CMD_SUDO_CANNOT_BE_SUDOED("Вы не можете выполнять команды или отправлять сообщения за игрока %1%!"),
    //Suicide Command
    CMD_SUICIDE_DESCRIPTION("Совершает самоубийство."),
    CMD_SUICIDE_MESSAGE("Вы покончили жизнь самоубийством!"),
    //ToolsPro Command
    CMD_TOOLSPRO_DESCRIPTION("Показывает информацию о плагине."),
    //Top Command
    CMD_TOP_DESCRIPTION("Телепортирует вас на самый верхний блок над вашей позицией."),
    CMD_TOP_TP_MESSAGE("Телепортация..."),
    //Tree Command
    CMD_TREE_DESCRIPTION("Генерирует дерево."),
    CMD_TREE_USAGE("Используйте: /tree <oak|spruce|birch|jungle|acacia|darkoak>"),
    //Unmute Command
    CMD_UNMUTE_DESCRIPTION("Убирает блокировку чата."),
    CMD_UNMUTE_DESCRIPTION2("/unmute <ник>"),
    CMD_UNMUTE_USAGE("Используйте: /unmute <ник>"),
    CMD_UNMUTE_SENDER("Вы успешно размутили игрока %1%!"),
    CMD_UNMUTE_PLAYER_INFO("Игрок Игрок %1% размутил игрока %2%!"),
    CMD_UNMUTE_PLAYER_MESSAGE("Вас размутили в чате и теперь можете писать в чат!"),
    CMD_UNMUTE_PLAYER_NOT_MUTED("Игрок %1% не был замучен!"),
    //Vanish Command
    CMD_VANISH_DESCRIPTION("Включает/выключает невидимость."),
    CMD_VANISH_DESCRIPTION2("/vanish или /vanish <ник>"),
    CMD_VANISH_SENDER_ENABLE("Вы успешно включили невидимость!"),
    CMD_VANISH_SENDER_DISABLE("Вы успешно выключили невидимость!"),
    CMD_VANISH_SENDER_ENABLE_INFO("Игрок %1% включил себе невидимость!"),
    CMD_VANISH_SENDER_DISABLE_INFO("Игрок %1% выключил себе невидимость!"),
    CMD_VANISH_PLAYER_ENABLE("Невидимость игроку %1% успешно включена!"),
    CMD_VANISH_PLAYER_DISABLE("Невидимость игроку %1% успешно выключена!"),
    CMD_VANISH_PLAYER_ENABLE_INFO("Игрок %1% включил невидимость игроку %2%!"),
    CMD_VANISH_PLAYER_DISABLE_INFO("Игрок %1% выключил невидимость игроку %2%!"),
    CMD_VANISH_PLAYER_ENABLE_MESSAGE("Вам включили невидимость!"),
    CMD_VANISH_PLAYER_DISABLE_MESSAGE("Вам выключили невидимость!"),
    //WhoIs Command
    CMD_WHOIS_ENABLED("Включен"),
    CMD_WHOIS_DISABLED("Выключен"),
    //World Command
    CMD_WORLD_DESCRIPTION("Позволяет работать с мирами."),
    CMD_WORLD_DESCRIPTION2("/world create <название мира> <old|infinite|flat> или /world tp <название мира> <ник>"),
    CMD_WORLD_USAGE("Используйте: /world create <название мира> <old|infinite|flat> или /world tp <название мира> <ник>"),
    CMD_WORLD_USAGE_TP("Используйте: /world tp <название мира> <ник>"),
    CMD_WORLD_USAGE_CREATE("Используйте: /world create <название мира> <old|infinite|flat>"),
    CMD_WORLD_TP("Телепортация..."),
    CMD_WORLD_TP_SENDER("Вы телепортировали игрока %1% в мир %2%"),
    CMD_WORLD_TP_PLAYER_MESSAGE("Вы были телепортированы в мир %1%"),
    CMD_WORLD_TP_SENDER_ALREADY_IN_THIS_WORLD("Вы уже находитесь в этом мире!"),
    CMD_WORLD_TP_PLAYER_ALREADY_IN_THIS_WORLD("Игрок уже находится в этом мире!"),
    CMD_WORLD_TP_SENDER_NO_PERMISSION("У Вас недостаточно прав для телепортации в этот мир!"),
    CMD_WORLD_TP_PLAYER_PERMISSION("У Вас недостаточно прав для телепортации других игроков в этот мир!"),
    CMD_WORLD_TP_NOT_FOUND("Мир с таким названием не найден!"),
    CMD_WORLD_TP_NOT_LOADED("Мир еще не загружен. Загрузка..."),
    CMD_WORLD_TP_ERROR_LOADING("Произошла ошибка при загрузке мира!"),
    CMD_WORLD_CREATE("Генерация мира: %1%"),
    CMD_WORLD_CREATE_ALREADY_EXISTS("Мир с таким названием уже существует!"),
    //Directions
    SOUTH("Юг"),
    NORTH("Север"),
    WEST("Запад"),
    EAST("Восток"),
    //Time
    SECONDS(" секунд"),
    MINUTES(" минут "),
    HOURS(" часов "),
    DAYS(" дней "),
    WEEKS(" недель "),
    //Damage Listener
    BLOCK_DAMAGE_CREATIVE("Вы не можете атаковать в креативе!"),
    BLOCK_DAMAGE_FLY("Вы не можете атаковать в режиме полета!"),
    BLOCK_DAMAGE_GOD("Вы не можете атаковать в режиме бога!"),
    BLOCK_DAMAGE_VANISH("Вы не можете атаковать в режиме невидимости!"),
    //Session
    JOIN_SESSION("У Вас включены следующие возможности: %1%"),
    JOIN_SESSION_FLY("полет"),
    JOIN_SESSION_GOD("режим бога"),
    JOIN_SESSION_SAVEINV("сохранение инвентаря"),
    JOIN_SESSION_VANISH("невидимость");

    private static boolean debugMode = false;
    private static String language = "english";
    private static char c1 = 'a';
    private static char c2 = '2';

    private static PluginBase plugin = null;
    private String message;
    private Character color1;
    private Character color2;

    Message(String msg) {
        message = msg;
        this.color1 = null;
        this.color2 = null;
    }

    Message(String msg, char color1, char color2) {
        this.message = msg;
        this.color1 = color1;
        this.color2 = color2;
    }

    Message(String msg, char color) {
        this(msg, color, color);
    }

    /**
     * This is my favorite debug routine :) I use it everywhere to print out variable values
     *
     * @param s - array of any object that you need to print out.
     *          Example:
     *          Message.BC ("variable 1:",var1,"variable 2:",var2)
     */
    public static void BC(Object... s) {
        if (!debugMode) return;
        if (s.length == 0) return;
        StringBuilder sb = new StringBuilder("&3[").append(plugin.getDescription().getName()).append("]&f ");
        for (Object str : s) sb.append(str.toString()).append(" ");
        plugin.getServer().broadcastMessage(TextFormat.colorize(sb.toString().trim()));
    }

    /**
     * Initialize current class, load messages, etc.
     * Call this file in onEnable method after initializing plugin configuration
     *
     * @param plg
     */
    public static void init(PluginBase plg) {
        plugin = plg;
        language = plg.getConfig().getString("general.language", "english");
        debugMode = plg.getConfig().getBoolean("general.debug-mode", false);
        initMessages();
        saveMessages();
        LNG_CONFIG.debug(Message.values().length, language, true, debugMode);
    }

    /**
     * Enable debugMode
     *
     * @param debug
     */
    public static void setDebugMode(boolean debug) {
        debugMode = debug;
    }

    private static boolean copyLanguage() {
        return plugin.saveResource("lang/" + language + ".lng", language + ".lng", false);
    }

    private static void initMessages() {
        copyLanguage();
        Config lng = null;
        try {
            lng = new Config(new File(plugin.getDataFolder() + File.separator + language + ".lng"), Config.YAML);
        } catch (Exception e) {
            LNG_LOAD_FAIL.log();
            if (debugMode) e.printStackTrace();
            return;
        }
        for (Message key : Message.values()) key.initMessage((String) lng.get(key.name().toLowerCase(), key.message));
    }

    private static void saveMessages() {
        Config lng = new Config(new File(plugin.getDataFolder() + File.separator + language + ".lng"), Config.YAML);
        for (Message key : Message.values())
            lng.set(key.name().toLowerCase(), key.message);
        try {
            lng.save();
        } catch (Exception e) {
            LNG_SAVE_FAIL.log();
            if (debugMode) e.printStackTrace();
            return;
        }
    }

    /**
     * Send message (formed using join method) to server log if debug mode is enabled
     *
     * @param s
     */
    public static boolean debugMessage(Object... s) {
        if (debugMode) plugin.getLogger().info(TextFormat.clean(join(s)));
        return true;
    }

    /**
     * Join object array to string (separated by space)
     *
     * @param s
     */
    public static String join(Object... s) {
        StringBuilder sb = new StringBuilder();
        for (Object o : s) {
            if (sb.length() > 0) sb.append(" ");
            sb.append(o.toString());
        }
        return sb.toString();
    }

    /**
     * Send current message to log files
     *
     * @param s
     * @return — always returns true.
     * Examples:
     * Message.ERROR_MESSAGE.log(variable1); // just print in log
     * return Message.ERROR_MESSAGE.log(variable1); // print in log and return value true
     */
    public boolean log(Object... s) {
        plugin.getLogger().info(getText(s));
        return true;
    }

    /**
     * Same as log, but will printout nothing if debug mode is disabled
     *
     * @param s
     * @return — always returns true.
     */
    public boolean debug(Object... s) {
        if (debugMode) plugin.getLogger().info(TextFormat.clean(getText(s)));
        return true;
    }

    /**
     * Show a message to player in center of screen (this routine unfinished yet)
     *
     * @param seconds — how much time (in seconds) to show message
     * @param sender  — Player
     * @param s
     * @return — always returns true.
     */
    public boolean tip(int seconds, CommandSender sender, Object... s) {
        if (sender == null) return Message.LNG_PRINT_FAIL.log(this.name());
        final Player player = sender instanceof Player ? (Player) sender : null;
        final String message = getText(s);
        if (player == null) sender.sendMessage(message);
        else for (int i = 0; i < seconds; i++)
            Server.getInstance().getScheduler().scheduleDelayedTask(new Runnable() {
                public void run() {
                    if (player.isOnline()) player.sendTip(message);
                }
            }, 20 * i);
        return true;
    }

    /**
     * Show a message to player in center of screen
     *
     * @param sender — Player
     * @param s
     * @return — always returns true.
     */
    public boolean tip(CommandSender sender, Object... s) {
        if (sender == null) return Message.LNG_PRINT_FAIL.log(this.name());
        Player player = sender instanceof Player ? (Player) sender : null;
        String message = getText(s);
        if (player == null) sender.sendMessage(message);
        else player.sendTip(message);
        return true;
    }

    /**
     * Send message to Player or to ConsoleSender
     *
     * @param sender
     * @param s
     * @return — always returns true.
     */
    public boolean print(CommandSender sender, Object... s) {
        if (sender == null) return Message.LNG_PRINT_FAIL.log(this.name());
        sender.sendMessage(getText(s));
        return true;
    }

    /**
     * Send message to all players or to players with defined permission
     *
     * @param permission
     * @param s
     * @return — always returns true.
     * <p>
     * Examples:
     * Message.MSG_BROADCAST.broadcast ("pluginname.broadcast"); // send message to all players with permission "pluginname.broadcast"
     * Message.MSG_BROADCAST.broadcast (null); // send message to all players
     */
    public boolean broadcast(String permission, Object... s) {
        for (Player player : plugin.getServer().getOnlinePlayers().values()) {
            if (permission == null || player.hasPermission(permission)) print(player, s);
        }
        return true;
    }

    /**
     * Get formated text.
     *
     * @param keys * Keys - are parameters for message and control-codes.
     *             Parameters will be shown in position in original message according for position.
     *             This keys are used in every method that prints or sends message.
     *             <p>
     *             Example:
     *             <p>
     *             EXAMPLE_MESSAGE ("Message with parameters: %1%, %2% and %3%");
     *             Message.EXAMPLE_MESSAGE.getText("one","two","three"); //will return text "Message with parameters: one, two and three"
     *             <p>
     *             * Color codes
     *             You can use two colors to define color of message, just use character symbol related for color.
     *             <p>
     *             Message.EXAMPLE_MESSAGE.getText("one","two","three",'c','4');  // this message will be red, but word one, two, three - dark red
     *             <p>
     *             * Control codes
     *             Control codes are text parameteres, that will be ignored and don't shown as ordinary parameter
     *             - "SKIPCOLOR" - use this to disable colorizing of parameters
     *             - "NOCOLOR" (or "NOCOLORS") - return uncolored text, clear all colors in text
     *             - "FULLFLOAT" - show full float number, by default it limit by two symbols after point (0.15 instead of 0.1483294829)
     * @return
     */
    public String getText(Object... keys) {
        char[] colors = new char[]{color1 == null ? c1 : color1, color2 == null ? c2 : color2};
        if (keys.length == 0) return TextFormat.colorize("&" + colors[0] + this.message);
        String str = this.message;
        boolean noColors = false;
        boolean skipDefaultColors = false;
        boolean fullFloat = false;
        String prefix = "";
        int count = 1;
        int c = 0;
        DecimalFormat fmt = new DecimalFormat("####0.##");
        for (int i = 0; i < keys.length; i++) {
            String s = keys[i].toString();
            if (c < 2 && keys[i] instanceof Character) {
                colors[c] = (Character) keys[i];
                c++;
                continue;
            } else if (s.startsWith("prefix:")) {
                prefix = s.replace("prefix:", "");
                continue;
            } else if (s.equals("SKIPCOLOR")) {
                skipDefaultColors = true;
                continue;
            } else if (s.equals("NOCOLORS") || s.equals("NOCOLOR")) {
                noColors = true;
                continue;
            } else if (s.equals("FULLFLOAT")) {
                fullFloat = true;
                continue;
            } else if (keys[i] instanceof Location) {
                Location loc = (Location) keys[i];
                if (fullFloat)
                    s = loc.getLevel().getName() + "[" + loc.getX() + ", " + loc.getY() + ", " + loc.getZ() + "]";
                else
                    s = loc.getLevel().getName() + "[" + fmt.format(loc.getX()) + ", " + fmt.format(loc.getY()) + ", " + fmt.format(loc.getZ()) + "]";
            } else if (keys[i] instanceof Double || keys[i] instanceof Float) {
                if (!fullFloat) s = fmt.format((Double) keys[i]);
            }

            String from = (new StringBuilder("%").append(count).append("%")).toString();
            String to = skipDefaultColors ? s : (new StringBuilder("&").append(colors[1]).append(s).append("&").append(colors[0])).toString();
            str = str.replace(from, to);
            count++;
        }
        str = TextFormat.colorize(prefix.isEmpty() ? "&" + colors[0] + str : prefix + " " + "&" + colors[0] + str);
        if (noColors) str = TextFormat.clean(str);
        return str;
    }

    private void initMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return this.getText("NOCOLOR");
    }
}