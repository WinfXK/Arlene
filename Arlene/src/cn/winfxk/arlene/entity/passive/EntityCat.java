package cn.winfxk.arlene.entity.passive;

import cn.winfxk.arlene.entity.EntityData;

/**
 * @alter Winfxk
 * @Createdate 2020/09/16 14:12:50
 * @author PowerNukkit
 */
public class EntityCat extends EntityAnimal {

	public static final int NETWORK_ID = 75;

	public EntityCat(EntityData data) {
		super(data);
	}

	@Override
	public int getNetworkId() {
		return NETWORK_ID;
	}

	@Override
	public float getWidth() {
		if (this.isBaby()) {
			return 0.3f;
		}
		return 0.6f;
	}

	@Override
	public float getHeight() {
		if (this.isBaby()) {
			return 0.35f;
		}
		return 0.7f;
	}

	@Override
	public String getName() {
		return "Cat";
	}

	@Override
	public void initEntity() {
		super.initEntity();
		this.setMaxHealth(10);
	}
}
