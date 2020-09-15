package cn.winfxk.arlene.entity;

import cn.nukkit.Player;
import cn.nukkit.entity.EntityCreature;
import cn.nukkit.entity.EntityNameable;
import cn.nukkit.item.Item;
import cn.nukkit.level.format.FullChunk;
import cn.nukkit.math.Vector3;
import cn.nukkit.nbt.tag.CompoundTag;

/**
 * @Createdate 2020/09/15 17:15:17
 * @alter Winfxk
 * @author: MagicDroidX Nukkit Project
 */
public abstract class EntityMob extends EntityCreature implements EntityNameable {
	/**
	 * NPC正在攻击的目标
	 */
	protected Player hostility;

	public EntityMob(FullChunk chunk, CompoundTag nbt) {
		super(chunk, nbt);
	}

	@Override
	public boolean onInteract(Player player, Item item, Vector3 clickedPos) {
		return EntityNameable.super.onInteract(player, item, clickedPos)
				|| EntityMob.super.onInteract(player, item, clickedPos);
	}

	/**
	 * 返回NPC正在攻击的目标
	 * 
	 * @return
	 */
	public Player getHostility() {
		return hostility;
	}

	/**
	 * 返回血量进度条
	 * 
	 * @return
	 */
	public String getHe() {
		String string = "", back = "";
		double bfb = getMaxHealth() / 20;
		for (double i = 0; i < getHealth(); i += bfb)
			string += "|";
		while ((string.length() + back.length()) < 20)
			back += "|";
		return "§9" + string + "§4" + back;
	}
}
