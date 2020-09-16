package cn.winfxk.arlene.entity.passive;

import cn.nukkit.Player;
import cn.nukkit.entity.Entity;
import cn.nukkit.entity.EntityAgeable;
import cn.nukkit.entity.EntityNameable;
import cn.nukkit.item.Item;
import cn.nukkit.math.Vector3;
import cn.winfxk.arlene.entity.EntityCreature;
import cn.winfxk.arlene.entity.EntityData;

/**
 * @author: MagicDroidX Nukkit Project
 * @alter Winfxk
 */
public abstract class EntityAnimal extends EntityCreature implements EntityAgeable, EntityNameable {
	public EntityAnimal(EntityData data) {
		super(data);
	}

	@Override
	public boolean isBaby() {
		return this.getDataFlag(DATA_FLAGS, Entity.DATA_FLAG_BABY);
	}

	public boolean isBreedingItem(Item item) {
		return item.getId() == Item.WHEAT; // default
	}

	@Override
	public boolean onInteract(Player player, Item item, Vector3 clickedPos) {
		return EntityNameable.super.onInteract(player, item, clickedPos)
				|| EntityAnimal.super.onInteract(player, item, clickedPos);
	}
}
