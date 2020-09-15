package cn.winfxk.arlene.element.particle.circle;

import cn.nukkit.level.Position;
import cn.nukkit.level.particle.HappyVillagerParticle;
import cn.nukkit.level.particle.Particle;
import cn.nukkit.math.Vector3;

/**
 * @Createdate 2020/09/15 15:06:03
 * @author Winfxk
 */
public class HappyVillagerCircle extends Circle {
	public HappyVillagerCircle() {
	}

	public HappyVillagerCircle(Position position) {
		super(position);
	}

	@Override
	public Particle getParticle(Vector3 vector3) {
		return new HappyVillagerParticle(vector3);
	}
}
