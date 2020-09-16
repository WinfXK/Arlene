package cn.winfxk.arlene.entity;

import cn.nukkit.Server;
import cn.nukkit.level.Location;
import cn.winfxk.arlene.tool.Config;
import cn.winfxk.arlene.tool.MyMap;

/**
 * NPC的数据
 * 
 * @Createdate 2020/09/16 13:53:35
 * @author Winfxk
 */
public class EntityData extends Location {
	private static final Server server = Server.getInstance();
	private MyMap<String, Object> map;
	private Config config;
	private boolean Custom = true;
	private String LevelName;
	private String Type;
	private int MaxHealth;
	private float Size;
	private String Name;
	private String Key;

	public EntityData(MyMap<String, Object> map) {
		super(map.getDouble("X"), map.getDouble("Y"), map.getDouble("Z"));
		LevelName = map.getString("Level");
		level = server.getLevelByName(LevelName);
		Type = map.getString("Type");
		MaxHealth = map.getInt("MaxHealth", -1);
		Size = map.getFloat("Szie", -1);
		Name = map.getString("Name", null);
		if (config != null && config.file != null)
			Key = getNameNotEx(config.getFile().getName());
		else if (map.containsKey("Key"))
			Key = map.getString("Key", null);
	}

	public EntityData(Config config) {
		this(config.getAll());
		Custom = false;
	}

	/**
	 * 返回数据的Key
	 * 
	 * @return
	 */
	public String getKey() {
		return Key;
	}

	/**
	 * 返回去掉拓展名的文件名
	 * 
	 * @param string
	 * @return
	 */
	private String getNameNotEx(String string) {
		if (string == null || string.isEmpty() || !string.contains("."))
			return string;
		return string.substring(0, string.lastIndexOf("."));
	}

	/**
	 * 返回NPC的名称
	 * 
	 * @return
	 */
	public String getName() {
		return Name;
	}

	/**
	 * 返回NPC大小
	 * 
	 * @return
	 */
	public float getSize() {
		return Size;
	}

	/**
	 * 返回NPC类型
	 * 
	 * @return
	 */
	public String getType() {
		return Type;
	}

	/**
	 * 返回NPC可能的血量
	 * 
	 * @return
	 */
	public int getMaxHealth() {
		return MaxHealth;
	}

	/**
	 * 返回数据Map
	 * 
	 * @return
	 */
	public MyMap<String, Object> getMap() {
		return map;
	}

	/**
	 * 若是用户自定义数据则返回数据文件对象
	 * 
	 * @return
	 */
	public Config getConfig() {
		return config;
	}

	/**
	 * 返回是否是随机生成的数据
	 * 
	 * @return
	 */
	public boolean isCustom() {
		return Custom;
	}

	/**
	 * 返回地图名称
	 * 
	 * @return
	 */
	public String getLevelName() {
		return LevelName;
	}
}
