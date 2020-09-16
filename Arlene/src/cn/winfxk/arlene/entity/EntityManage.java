package cn.winfxk.arlene.entity;

import java.io.File;
import java.io.FilenameFilter;

import cn.winfxk.arlene.Activate;
import cn.winfxk.arlene.entity.mob.EntityBoss;
import cn.winfxk.arlene.entity.mob.EntityVex;
import cn.winfxk.arlene.entity.mob.EntityZombie;
import cn.winfxk.arlene.entity.mob.EntityZombiePigman;
import cn.winfxk.arlene.entity.mob.EntityZombieVillager;
import cn.winfxk.arlene.entity.passive.EntityCat;
import cn.winfxk.arlene.entity.passive.EntityCow;
import cn.winfxk.arlene.tool.Config;
import cn.winfxk.arlene.tool.MyMap;

/**
 * @Createdate 2020/09/16 14:17:09
 * @author Winfxk
 */
public class EntityManage implements FilenameFilter {
	private Activate ac;
	private final MyMap<String, EntityCreature> map = new MyMap<>();
	private static final MyMap<String, EntityCreature> BaseCreature = new MyMap<>();
	private static EntityManage manage;
	private static final File file = new File(Activate.getActivate().getPluginBase().getDataFolder(),
			Activate.NPCDirName);
	static {
		EntityCreature[] creatures = { new EntityBoss(null), new EntityVex(null), new EntityZombie(null),
				new EntityZombiePigman(null), new EntityZombieVillager(null), new EntityCat(null),
				new EntityCow(null) };
		for (EntityCreature creature : creatures)
			BaseCreature.put(creature.getName(), creature);
	}

	public EntityManage(Activate activate) {
		ac = activate;
		manage = this;
		reloadMap();
	}

	/**
	 * 加载用户定义数据
	 * 
	 * @return
	 */
	public MyMap<String, EntityCreature> reloadMap() {
		map.clear();
		EntityData data;
		for (File file : EntityManage.file.listFiles(this)) {
			data = new EntityData(new Config(file, Config.YAML));
			if (!BaseCreature.containsKey(data.getType())) {
				ac.getPluginBase().getLogger().error("无法加载NPC信息！");
				continue;
			}
			map.put(data.getKey(), BaseCreature.get(data.getType()).clone().setData(data));
		}
		return map;
	}

	/**
	 * 返回当前类API
	 * 
	 * @return
	 */
	public static EntityManage getManage() {
		return manage;
	}

	/**
	 * 返回所有的NPC数据
	 * 
	 * @return
	 */
	public MyMap<String, EntityCreature> getMap() {
		return map;
	}

	/**
	 * 返回当前支持的基本NPC数据
	 * 
	 * @return
	 */
	public static MyMap<String, EntityCreature> getBasecreature() {
		return BaseCreature;
	}

	/**
	 * 根据Key获取一个实体对象
	 * 
	 * @param Key
	 * @return
	 */
	public EntityCreature getEntity(String Key) {
		return map.get(Key).clone();
	}

	@Override
	public boolean accept(File dir, String name) {
		return new File(dir, name).isFile();
	}
}
