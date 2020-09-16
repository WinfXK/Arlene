package cn.winfxk.arlene.entity.mob;

import cn.winfxk.arlene.entity.EntityData;

/**
 * @Createdate 2020/09/15 17:00:43
 * @author PikyCZ
 * @alter Winfxk
 */
public class EntityVex extends EntityMob {

	public static final int NETWORK_ID = 105;

	public EntityVex(EntityData data) {
		super(data);
	}

	@Override
	public int getNetworkId() {
		return NETWORK_ID;
	}

	@Override
	protected void initEntity() {
		super.initEntity();
		this.setMaxHealth(50);
	}

	@Override
	public float getWidth() {
		return 0.4f;
	}

	@Override
	public float getHeight() {
		return 0.8f;
	}

	@Override
	public String getName() {
		return "Vex";
	}
}
