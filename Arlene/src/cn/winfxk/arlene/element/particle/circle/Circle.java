package cn.winfxk.arlene.element.particle.circle;

import cn.nukkit.level.Position;
import cn.nukkit.level.particle.Particle;
import cn.nukkit.math.Vector3;
import cn.winfxk.arlene.element.particle.BaseParticle;

/**
 * @Createdate 2020/09/15 14:42:20
 * @author Winfxk
 */
public abstract class Circle extends BaseParticle {
	private static final double R = 1d;
	private double angle = 1d;

	public Circle() {
	}

	public Circle(Position position) {
		super(position);
	}

	@Override
	public boolean Tick() {
		double[] d = getCircle();
		level.addParticle(getParticle(new Vector3(d[0], y, d[1])));
		return true;
	}

	public abstract Particle getParticle(Vector3 vector3);

	/**
	 * 求出圆坐标
	 * 
	 * @return
	 */
	public double[] getCircle() {
		angle += 10;
		angle = angle >= 360d ? 0 : angle;
		return getCircle(angle);
	}

	/**
	 * 求出圆坐标
	 * 
	 * @param angle 圆的角度
	 * @return
	 */
	public double[] getCircle(double angle) {
		double x = this.x + R * Math.cos(angle * Math.PI / 180);
		double y = this.z + R * Math.sin(angle * Math.PI / 180);
		return new double[] { x, y };
	}
}
