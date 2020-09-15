package cn.winfxk.arlene.element.particle.levitate;

import cn.nukkit.level.Position;
import cn.nukkit.level.particle.WaterDripParticle;
import cn.winfxk.arlene.tool.Tool;

/**
 * @Createdate 2020/09/15 15:02:55
 * @author Winfxk
 */
public class WaterDripLevitate extends Levitate {

	public WaterDripLevitate(Position position) {
		super(position);
	}

	public WaterDripLevitate() {
	}

	@Override
	public boolean Tick() {
		int While = Tool.getRand(10, 50);
		for (int i = 0; i < While; i++)
			level.addParticle(new WaterDripParticle(getVector3()));
		return true;
	}
}
