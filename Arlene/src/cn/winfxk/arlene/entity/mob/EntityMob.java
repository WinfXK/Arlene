package cn.winfxk.arlene.entity.mob;

import cn.nukkit.Player;
import cn.nukkit.entity.EntityNameable;
import cn.nukkit.item.Item;
import cn.nukkit.math.Vector3;
import cn.winfxk.arlene.entity.EntityCreature;
import cn.winfxk.arlene.entity.EntityData;

/**
 * @Createdate 2020/09/15 17:15:17
 * @alter Winfxk
 * @author: MagicDroidX Nukkit Project
 */
public abstract class EntityMob extends EntityCreature implements EntityNameable {

	public EntityMob(EntityData data) {
		super(data);
	}

	@Override
	public boolean onInteract(Player player, Item item, Vector3 clickedPos) {
		return EntityNameable.super.onInteract(player, item, clickedPos)
				|| EntityMob.super.onInteract(player, item, clickedPos);
	}
}
