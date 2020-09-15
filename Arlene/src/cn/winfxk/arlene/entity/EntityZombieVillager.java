package cn.winfxk.arlene.entity;

import cn.nukkit.entity.EntitySmite;
import cn.nukkit.level.format.FullChunk;
import cn.nukkit.nbt.tag.CompoundTag;

/**
 * @Createdate 2020/09/15 17:00:43
 * @author PowerNukkit
 * @alter Winfxk
 */
public class EntityZombieVillager extends EntityMob implements EntitySmite {

	public static final int NETWORK_ID = 116;

	public EntityZombieVillager(FullChunk chunk, CompoundTag nbt) {
		super(chunk, nbt);
	}

	@Override
	public int getNetworkId() {
		return NETWORK_ID;
	}

	@Override
	protected void initEntity() {
		super.initEntity();
		this.setMaxHealth(100);
	}

	@Override
	public float getWidth() {
		return 0.6f;
	}

	@Override
	public float getHeight() {
		return 1.95f;
	}

	@Override
	public String getName() {
		return "Zombie Villager";
	}

	@Override
	public boolean isUndead() {
		return true;
	}
}
