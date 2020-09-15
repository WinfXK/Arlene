package cn.winfxk.arlene.element.particle.levitate;

import cn.nukkit.level.Position;
import cn.nukkit.level.particle.SpellParticle;
import cn.winfxk.arlene.tool.Tool;

/**
 * @Createdate 2020/09/15 14:04:38
 * @author Winfxk
 */
public class SpellLevitate extends Levitate {

	public SpellLevitate(Position position) {
		super(position);
	}

	public SpellLevitate() {
	}

	@Override
	public boolean Tick() {
		int While = Tool.getRand(1, 5);
		for (int i = 0; i < While; i++)
			level.addParticle(
					new SpellParticle(getVector3(), Tool.getRand(0, 255), Tool.getRand(0, 255), Tool.getRand(0, 255)));
		return true;
	}
}
