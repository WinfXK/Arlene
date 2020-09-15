package cn.winfxk.arlene.element.special;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import cn.nukkit.item.Item;
import cn.winfxk.arlene.tool.Config;
import cn.winfxk.arlene.tool.MyMap;
import cn.winfxk.arlene.tool.Tool;

/**
 * 箱子点
 * 
 * @Createdate 2020/09/15 15:51:51
 * @author Winfxk
 */
public class Chest extends MyPosition {
	private List<Item> items = new ArrayList<>();

	public Chest(Config config) {
		this(config.getAll());
		Custom = true;
	}

	public Chest(MyMap<String, Object> data) {
		super(data);
		MyMap<String, Object> map = data.getMap("Items", new MyMap<>());
		Item item;
		String[] string;
		for (Object object : map.values()) {
			if (!(object instanceof Map)) {
				string = Tool.objToString(object).split(":");
				item = new Item(Tool.ObjToInt(string[0]), string.length > 1 ? Tool.ObjToInt(string[1]) : 0,
						string.length > 2 ? Tool.ObjToInt(string[1], 1) : 1);
			} else
				item = Tool.loadItem((Map<String, Object>) object);
			if (item != null && item.getId() != 0)
				items.add(item);
		}
	}

	/**
	 * 返回箱子里面拥有的物品列表
	 * 
	 * @return
	 */
	public List<Item> getItems() {
		return items;
	}
}
