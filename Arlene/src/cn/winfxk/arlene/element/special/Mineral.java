package cn.winfxk.arlene.element.special;

import java.util.ArrayList;
import java.util.List;

import cn.nukkit.block.Block;
import cn.nukkit.item.Item;
import cn.winfxk.arlene.entity.EntityMob;
import cn.winfxk.arlene.tool.Config;
import cn.winfxk.arlene.tool.MyMap;

/**
 * @Createdate 2020/09/15 16:52:17
 * @author Winfxk
 */
public class Mineral extends MyPosition {
	private Block block;
	private List<EntityMob> Mobs = new ArrayList<>();
	private List<Item> Drops = new ArrayList<>();

	public Mineral(MyMap<String, Object> map) {
		super(map);
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
	public List<EntityMob> getMobs() {
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
