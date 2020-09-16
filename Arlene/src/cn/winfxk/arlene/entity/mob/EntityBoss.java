package cn.winfxk.arlene.entity.mob;

import cn.nukkit.entity.EntitySmite;
import cn.winfxk.arlene.entity.EntityData;

/**
 * Created by Dr. Nick Doran on 4/23/2017.
 * 
 * @alter Winfxk
 * @author PowerNukkit
 */
public class EntityBoss extends EntityMob implements EntitySmite {
	public static final int NETWORK_ID = 32;

	@Override
	public int getNetworkId() {
		return NETWORK_ID;
	}

	public EntityBoss(EntityData data) {
		super(data);
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
		return "BOSS";
	}

	@Override
	public boolean isUndead() {
		return true;
	}
}
