package cn.winfxk.arlene;

import java.io.File;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.nukkit.Player;
import cn.winfxk.arlene.form.FormBase;
import cn.winfxk.arlene.tool.Config;
import cn.winfxk.arlene.tool.MyConfig;

/**
 * @author Winfxk
 */
public class MyPlayer {
	private static Activate ac = Activate.getActivate();
	public transient Config config;
	private Player player;
	public transient int ID = 0;
	private Map<String, Instant> instant = new HashMap<>();
	public transient FormBase form;
	public static final File PlayerDir = new File(ac.getPluginBase().getDataFolder(), Activate.PlayerDataDirName);

	/**
	 * 记录存储玩家的一些数据
	 *
	 * @param player
	 */
	public MyPlayer(Player player) {
		this.player = player;
		config = getConfig(getName());
		config = ac.resCheck.Check(this);
		config.set("name", player.getName());
		config.save();
	}

	/**
	 * 显示一个UI
	 * 
	 * @param form
	 * @return
	 */
	public boolean showForm(FormBase form) {
		if (!instant.containsKey(form.getClass().getSimpleName())
				|| Duration.between(instant.get(form.getClass().getSimpleName()), Instant.now()).toMillis() > 500) {
			instant.put(form.getClass().getSimpleName(), Instant.now());
			this.form = form;
			return form.makeMain();
		}
		return false;
	}

	public Player getPlayer() {
		return player;
	}

	/**
	 * 判断玩家是否是管理员
	 * 
	 * @return
	 */
	public boolean isAdmin() {
		return ac.isAdmin(player);
	}

	public MyPlayer setPlayer(Player player) {
		this.player = player;
		return this;
	}

	/**
	 * 获取逗比玩家的金币数量
	 *
	 * @return
	 */
	public double getMoney() {
		return ac.getEconomy().getMoney(player);
	}

	/**
	 * 获取逗比玩家的金币数量
	 *
	 * @return
	 */
	public static double getMoney(String player) {
		return Activate.getActivate().getEconomy().getMoney(player);
	}

	public Config getConfig() {
		return config;
	}

	/**
	 * 得到一个玩家的配置文件对象
	 *
	 * @param player 玩家名称
	 * @return
	 */
	public static Config getConfig(String player) {
		File file = getFile(player);
		Config config = new MyConfig(file);
		return config;
	}

	public String getName() {
		return player.getName();
	}

	/**
	 * 得到一个玩家配置文件的文件对象
	 *
	 * @return
	 */
	public File getFile() {
		return new File(PlayerDir, player.getName() + ".yml");
	}

	/**
	 * 得到一个玩家配置文件的文件对象
	 *
	 * @param player 玩家名称
	 * @return
	 */
	public static File getFile(String player) {
		return new File(PlayerDir, player + ".yml");
	}

	/**
	 * 判断玩家的配置文件是否存在
	 *
	 * @param player
	 * @return
	 */
	public static boolean isPlayer(String player) {
		File file = getFile(player);
		return file.exists();
	}

	/**
	 * 将一条信息打包给玩家
	 *
	 * @param player
	 * @param Message
	 * @return 若玩家在线将返回True，否则返回False
	 */
	public static boolean sendMessage(String player, String Message) {
		if (player == null || Message == null || player.isEmpty() || Message.isEmpty() || !isPlayer(player))
			return false;
		if (Activate.getActivate().isPlayers(player)) {
			Activate.getActivate().getPlayers(player).player.sendMessage(Message);
			return true;
		}
		Config config = getConfig(player);
		Object obj = config.get("Message");
		List<Object> list = obj == null || !(obj instanceof List) ? new ArrayList<>() : (ArrayList<Object>) obj;
		list.add(Message);
		config.set("Message", list);
		config.save();
		return false;
	}
}
