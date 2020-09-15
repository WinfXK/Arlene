package cn.winfxk.arlene.element.particle.circle;

import cn.nukkit.level.Position;
import cn.nukkit.level.particle.Particle;
import cn.nukkit.level.particle.WaterDripParticle;
import cn.nukkit.math.Vector3;

/**
 * @Createdate 2020/09/15 15:03:49
 * @author Winfxk
 */
public class WaterDripCircle extends Circle {
	public WaterDripCircle() {
	}

	public WaterDripCircle(Position position) {
		super(position);
	}

	@Override
	public Particle getParticle(Vector3 vector3) {
		return new WaterDripParticle(vector3);
	}
}
