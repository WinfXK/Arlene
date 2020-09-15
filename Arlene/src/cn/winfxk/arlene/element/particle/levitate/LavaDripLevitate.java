package cn.winfxk.arlene.element.particle.levitate;

import cn.nukkit.level.Position;
import cn.nukkit.level.particle.LavaDripParticle;
import cn.winfxk.arlene.tool.Tool;

/**
 * @Createdate 2020/09/15 14:30:43
 * @author Winfxk
 */
public class LavaDripLevitate extends Levitate {
	public LavaDripLevitate() {
	}

	public LavaDripLevitate(Position position) {
		super(position);
	}

	@Override
	public boolean Tick() {
		int While = Tool.getRand(10, 50);
		for (int i = 0; i < While; i++)
			level.addParticle(new LavaDripParticle(getVector3()));
		return true;
	}
}
