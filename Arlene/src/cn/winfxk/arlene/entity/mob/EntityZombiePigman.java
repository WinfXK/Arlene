package cn.winfxk.arlene.entity.mob;

import cn.nukkit.entity.EntitySmite;
import cn.winfxk.arlene.entity.EntityData;

/**
 * @Createdate 2020/09/15 17:00:43
 * @author PikyCZ
 * @alter Winfxk
 */
public class EntityZombiePigman extends EntityMob implements EntitySmite {

	public static final int NETWORK_ID = 36;

	@Override
	public int getNetworkId() {
		return NETWORK_ID;
	}

	public EntityZombiePigman(EntityData data) {
		super(data);
	}

	@Override
	protected void initEntity() {
		super.initEntity();
		this.setMaxHealth(250);
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
		return "ZombiePigman";
	}

	@Override
	public boolean isUndead() {
		return true;
	}
}
