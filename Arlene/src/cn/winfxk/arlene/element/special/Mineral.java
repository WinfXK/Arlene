package cn.winfxk.arlene.element.special;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import cn.nukkit.block.Block;
import cn.nukkit.item.Item;
import cn.winfxk.arlene.entity.EntityCreature;
import cn.winfxk.arlene.tool.Config;
import cn.winfxk.arlene.tool.MyMap;
import cn.winfxk.arlene.tool.Tool;

/**
 * @Createdate 2020/09/15 16:52:17
 * @author Winfxk
 */
public class Mineral extends MyPosition {
	private Block block;
	private List<EntityCreature> Mobs = new ArrayList<>();
	private List<Item> Drops = new ArrayList<>();

	public Mineral(MyMap<String, Object> map) {
		super(map);
		Object object = map.get("Block");
		if (!(object instanceof Map)) {
			String[] string = Tool.objToString(object).split(":");
			block = Block.get(Tool.ObjToInt(string[0]), string.length > 1 ? Tool.ObjToInt(string[1]) : 0, this, 0);
		} else {
			Item item = Tool.loadItem((Map<String, Object>) object);
			block = item.getBlock();
		}
		if (block == null || block.getId() == 0)
			throw new ArithmeticException("无法解析资源点信息！");
		object = map.get("Entitys");
		EntityCreature creature;
		if (object instanceof Map) {
			Map<String, Object> map2 = (Map<String, Object>) object;
			for (Object obj : map2.values()) {
				creature = ac.getGameDataManage().getEntityManage().getEntity(Tool.objToString(obj));
				if (creature != null)
					Mobs.add(creature);
			}
		} else if (object instanceof List) {
			List<Object> list = (List<Object>) object;
			for (Object obj : list) {
				creature = ac.getGameDataManage().getEntityManage().getEntity(Tool.objToString(obj));
				if (creature != null)
					Mobs.add(creature);
			}
		}
		object = map.get("Drops");
		Item item;
		String[] string;
		if (object instanceof List) {
			List<Object> list = (List<Object>) object;
			for (Object obj : list) {
				string = Tool.objToString(obj).split(":");
				item = new Item(Tool.ObjToInt(string[0]), string.length > 1 ? Tool.ObjToInt(string[1]) : 0,
						string.length > 2 ? Tool.ObjToInt(string[2], 1) : 1);
				if (item.getId() == 0)
					ac.getPluginBase().getLogger().error("无法获取掉落物对象！");
				else
					Drops.add(item);
			}
		} else if (object instanceof Map) {
			Map<String, Object> map2 = (Map<String, Object>) object;
			for (Object obj : map2.values()) {
				if (obj instanceof Map) {
					item = Tool.loadItem((Map<String, Object>) obj);
				} else {
					string = Tool.objToString(obj).split(":");
					item = new Item(Tool.ObjToInt(string[0]), string.length > 1 ? Tool.ObjToInt(string[1]) : 0,
							string.length > 2 ? Tool.ObjToInt(string[2], 1) : 1);
				}
				if (item.getId() == 0)
					ac.getPluginBase().getLogger().error("无法获取掉落物对象！");
				else
					Drops.add(item);
			}
		}
	}

	public Mineral(Config config) {
		super(config);
	}

	/**
	 * 返回这个方块的对象
	 * 
	 * @return
	 */
	public Block getBlock() {
		return block;
	}

	/**
	 * 返回挖掘这个方块时会刷新的怪
	 * 
	 * @return
	 */
	public List<EntityCreature> getMobs() {
		return Mobs;
	}

	/**
	 * 返回挖掘这个方块会掉落的物品
	 * 
	 * @return
	 */
	public List<Item> getDrops() {
		return Drops;
	}
}
