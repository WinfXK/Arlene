package cn.winfxk.arlene.entity;

import cn.nukkit.Player;
import cn.nukkit.nbt.tag.CompoundTag;
import cn.nukkit.nbt.tag.DoubleTag;
import cn.nukkit.nbt.tag.FloatTag;
import cn.nukkit.nbt.tag.ListTag;

/**
 * @author: MagicDroidX Nukkit Project
 * @alter Winfxk
 */
public abstract class EntityCreature extends cn.nukkit.entity.EntityCreature {
	protected Player hostility;
	protected EntityData data;

	public EntityCreature(EntityData data) {
		super(data.getChunk(), getDefaultNBT(data));
	}

	/**
	 * 获取默认NBT
	 * 
	 * @param datum
	 * @return
	 */
	public static CompoundTag getDefaultNBT(EntityData data) {
		return new CompoundTag()
				.putList(new ListTag<DoubleTag>("Pos").add(new DoubleTag("", data.x)).add(new DoubleTag("", data.y))
						.add(new DoubleTag("", data.z)))
				.putList(new ListTag<DoubleTag>("Motion").add(new DoubleTag("", 0)).add(new DoubleTag("", 0))
						.add(new DoubleTag("", 0)))
				.putList(new ListTag<FloatTag>("Rotation").add(new FloatTag("", (float) data.getYaw()))
						.add(new FloatTag("", (float) data.getPitch())))
				.putCompound("Skin", new CompoundTag());
	}

	/**
	 * 设置实体数据
	 * 
	 * @param data
	 */
	public EntityCreature setData(EntityData data) {
		this.data = data;
		return this;
	}

	@Override
	public EntityCreature clone() {
		return (EntityCreature) super.clone();
	}

	/**
	 * 设置NPC的敌对目标
	 * 
	 * @param hostility
	 */
	public void setHostility(Player hostility) {
		this.hostility = hostility;
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
