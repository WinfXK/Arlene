package cn.winfxk.arlene.entity.passive;

import cn.nukkit.item.Item;
import cn.winfxk.arlene.entity.EntityData;

/**
 * @author: BeYkeRYkt Nukkit Project
 * @alter Winfxk
 */
public class EntityCow extends EntityAnimal {
	public static final int NETWORK_ID = 11;

	public EntityCow(EntityData data) {
		super(data);
	}

	@Override
	public float getWidth() {
		if (this.isBaby()) {
			return 0.45f;
		}
		return 0.9f;
	}

	@Override
	public float getHeight() {
		if (this.isBaby()) {
			return 0.7f;
		}
		return 1.4f;
	}

	@Override
	public String getName() {
		return "Cow";
	}

	@Override
	public Item[] getDrops() {
		return new Item[] { Item.get(Item.LEATHER), Item.get(((this.isOnFire()) ? Item.COOKED_BEEF : Item.RAW_BEEF)) };
	}

	@Override
	public int getNetworkId() {
		return NETWORK_ID;
	}

	@Override
	protected void initEntity() {
		super.initEntity();
		this.setMaxHealth(10);
	}
}
