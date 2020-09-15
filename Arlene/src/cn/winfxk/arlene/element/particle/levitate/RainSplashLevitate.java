package cn.winfxk.arlene.element.particle.levitate;

import cn.nukkit.level.Position;
import cn.nukkit.level.particle.RainSplashParticle;
import cn.winfxk.arlene.tool.Tool;

/**
 * @Createdate 2020/09/15 14:08:23
 * @author Winfxk
 */
public class RainSplashLevitate extends Levitate {

	public RainSplashLevitate(Position position) {
		super(position);
	}

	public RainSplashLevitate() {
	}

	@Override
	public boolean Tick() {
		int While = Tool.getRand(10, 50);
		for (int i = 0; i < While; i++)
			level.addParticle(new RainSplashParticle(getVector3()));
		return true;
	}
}
