package cn.winfxk.arlene.entity;

import cn.nukkit.entity.EntitySmite;
import cn.nukkit.level.format.FullChunk;
import cn.nukkit.nbt.tag.CompoundTag;

/**
 * Created by Dr. Nick Doran on 4/23/2017.
 * 
 * @Createdate 2020/09/15 17:15:17
 * @alter Winfxk
 */
public class EntityBoss extends EntityMob implements EntitySmite {

	public static final int NETWORK_ID = 32;

	@Override
	public int getNetworkId() {
		return NETWORK_ID;
	}

	public EntityBoss(FullChunk chunk, CompoundTag nbt) {
		super(chunk, nbt);
	}

	@Override
	protected void initEntity() {
		super.initEntity();
		setScale(2);
		this.setMaxHealth(500);
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
		return "Zombie";
	}

	@Override
	public boolean isUndead() {
		return true;
	}
}
