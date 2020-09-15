package cn.winfxk.arlene;

import java.io.File;
import java.time.Duration;
import java.time.Instant;
import java.util.LinkedHashMap;
import java.util.Map;

import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.command.CommandSender;
import cn.nukkit.plugin.Plugin;
import cn.nukkit.plugin.PluginBase;
import cn.nukkit.utils.Utils;
import cn.winfxk.arlene.money.EconomyAPI;
import cn.winfxk.arlene.money.MyEconomy;
import cn.winfxk.arlene.tool.Config;
import cn.winfxk.arlene.tool.ItemList;
import cn.winfxk.arlene.tool.MyMap;
import cn.winfxk.arlene.tool.Tool;

/**
 * @author Winfxk
 */
public class Activate {
	public Arlene mis;
	public ResCheck resCheck;
	public final static String[] FormIDs = { /* 0 */"主页", /* 1 */"备用主页", /* 2 */ "备用页" };
	public final static String MessageFileName = "Message.yml", ConfigFileName = "Config.yml",
			FormIDFileName = "FormID.yml", PlayerDataDirName = "Players", ItemListFileName = "ItemList.yml",
			GameSpaceDir = "GameSpace", TeleporterDirName = GameSpaceDir + "/Teleporter",
			ChestDirName = GameSpaceDir + "/Chest", GameSpaceConfigName = "GameSpace.yml",
			MineralDirName = GameSpaceDir + "/Mineral", NPCDirName = GameSpaceDir + "/Entity";
	private MyEconomy economy;
	private ItemList itemList;
	private static Activate activate;
	private transient MyMap<String, MyPlayer> Players;
	protected FormID FormID;
	protected Message message;
	protected Config config, CommandConfig, ItemListConfig;
	/**
	 * 默认要加载的配置文件，这些文件将会被用于与插件自带数据匹配
	 */
	protected static final String[] loadFile = { ConfigFileName };
	/**
	 * 插件基础配置文件
	 */
	protected static final String[] defaultFile = { MessageFileName, ItemListFileName };
	protected static final String[] Mkdir = { PlayerDataDirName, GameSpaceDir, TeleporterDirName, ChestDirName,
			MineralDirName };

	/**
	 * 插件数据的集合类
	 *
	 * @param kis
	 */
	public Activate(Arlene kis) {
		activate = this;
		mis = kis;
		FormID = new FormID();
		Players = new MyMap<>();
		if ((resCheck = new ResCheck(this).start()) == null)
			return;
		Plugin plugin = Server.getInstance().getPluginManager().getPlugin(EconomyAPI.Name);
		if (plugin != null)
			economy = new EconomyAPI(this);
		ItemListConfig = new Config(new File(mis.getDataFolder(), ItemListFileName), Config.YAML);
		itemList = new ItemList(this);
		kis.getServer().getPluginManager().registerEvents(new PlayerEvent(this), kis);
		kis.getLogger().info(message.getMessage("插件启动", "{loadTime}",
				(float) Duration.between(mis.loadTime, Instant.now()).toMillis() + "ms") + "-Alpha");
	}

	/**
	 * 返回物品列表
	 * 
	 * @return
	 */
	public ItemList getItemList() {
		return itemList;
	}

	/**
	 * 返回物品列表文件
	 * 
	 * @return
	 */
	public Config getItemListConfig() {
		return ItemListConfig;
	}

	/**
	 * 返回一个默认的玩家数据
	 *
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> getPlayerConfig() throws Exception {
		return ResCheck.yaml.loadAs(Utils.readFile(getClass().getResourceAsStream("/resources/player.yml")), Map.class);
	}

	/**
	 * 得到默认经济插件
	 *
	 * @reaturn
	 */
	public MyEconomy getEconomy() {
		return economy;
	}

	/**
	 * 得到插件名称
	 *
	 * @return
	 */
	public String getName() {
		return mis.getName();
	}

	/**
	 * 得到插件主类
	 *
	 * @return
	 */
	public PluginBase getPluginBase() {
		return mis;
	}

	/**
	 * 删除玩家数据
	 *
	 * @param player
	 */
	public void removePlayers(Player player) {
		removePlayers(player.getName());
	}

	/**
	 * 删除玩家数据
	 *
	 * @param player
	 */
	public void removePlayers(String player) {
		if (Players.containsKey(player)) {
			Config config = Players.get(player).getConfig();
			config.set("QuitTime", Tool.getDate() + " " + Tool.getTime());
			config.save();
			Players.remove(player);
		}
	}

	/**
	 * 设置玩家数据
	 *
	 * @param player
	 * @return
	 */
	public MyPlayer setPlayers(Player player, MyPlayer myPlayer) {
		if (!Players.containsKey(player.getName()))
			Players.put(player.getName(), myPlayer);
		myPlayer = Players.get(player.getName());
		myPlayer.setPlayer(player);
		return myPlayer;
	}

	/**
	 * 设置玩家数据
	 *
	 * @param player
	 * @return
	 */
	public MyPlayer getPlayers(Player player) {
		return isPlayers(player.getName()) ? Players.get(player.getName()) : null;
	}

	/**
	 * 设置玩家数据
	 *
	 * @param player
	 * @return
	 */
	public MyPlayer getPlayers(String player) {
		return isPlayers(player) ? Players.get(player) : null;
	}

	/**
	 * 玩家数据是否存在
	 *
	 * @param player
	 * @return
	 */
	public boolean isPlayers(Player player) {
		return Players.containsKey(player.getName());
	}

	/**
	 * 玩家数据是否存在
	 *
	 * @param player
	 * @return
	 */
	public boolean isPlayers(String player) {
		return Players.containsKey(player);
	}

	/**
	 * 得到玩家数据
	 *
	 * @return
	 */
	public LinkedHashMap<String, MyPlayer> getPlayers() {
		return new LinkedHashMap<>(Players);
	}

	/**
	 * 得到ID类
	 *
	 * @return
	 */
	public FormID getFormID() {
		return FormID;
	}

	/**
	 * 得到语言类
	 *
	 * @return
	 */
	public Message getMessage() {
		return message;
	}

	/**
	 * 对外接口
	 *
	 * @return
	 */
	public static Activate getActivate() {
		return activate;
	}

	/**
	 * 返回EconomyAPI货币的名称
	 *
	 * @return
	 */
	public String getMoneyName() {
		return economy == null ? config.getString("货币名称") : economy.getMoneyName();
	}

	/**
	 * 得到MostBrain主配置文件
	 *
	 * @return
	 */
	public Config getConfig() {
		return config;
	}

	/**
	 * 判断玩家是否是管理员
	 * 
	 * @param player
	 * @return
	 */
	public boolean isAdmin(Player player) {
		return isAdmin(player.getName());
	}

	/**
	 * 判断玩家是否是管理员
	 * 
	 * @param player
	 * @return
	 */
	public boolean isAdmin(CommandSender player) {
		return isAdmin(player.getName());
	}

	/**
	 * 判断玩家是否是管理员
	 * 
	 * @param player
	 * @return
	 */
	public boolean isAdmin(String player) {
		if (config.getBoolean("astrictAdmin"))
			return config.getStringList("Admin").contains(player);
		return config.getStringList("Admin").contains(player) || Server.getInstance().isOp(player);
	}
}
