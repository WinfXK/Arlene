package cn.winfxk.arlene.element.particle.circle;

import cn.nukkit.level.Position;
import cn.nukkit.level.particle.LavaDripParticle;
import cn.nukkit.level.particle.Particle;
import cn.nukkit.math.Vector3;

/**
 * @Createdate 2020/09/15 15:01:53
 * @author Winfxk
 */
public class LavaDripCircle extends Circle {
	public LavaDripCircle() {
	}

	public LavaDripCircle(Position position) {
		super(position);
	}

	@Override
	public Particle getParticle(Vector3 vector3) {
		return new LavaDripParticle(vector3);
	}
}
