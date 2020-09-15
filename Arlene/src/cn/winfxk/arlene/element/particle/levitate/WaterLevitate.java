package cn.winfxk.arlene.element.particle.levitate;

import cn.nukkit.level.Position;
import cn.nukkit.level.particle.WaterParticle;
import cn.winfxk.arlene.tool.Tool;

/**
 * 水滴悬浮粒子效果
 * 
 * @Createdate 2020/09/15 14:01:33
 * @author Winfxk
 */
public class WaterLevitate extends Levitate {

	public WaterLevitate(Position position) {
		super(position);
	}

	public WaterLevitate() {
	}

	@Override
	public boolean Tick() {
		int While = Tool.getRand(10, 50);
		for (int i = 0; i < While; i++)
			level.addParticle(new WaterParticle(getVector3()));
		return true;
	}
}
