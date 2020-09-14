package cn.winfxk.arlene.game.data.particle.levitate;

import cn.nukkit.level.Position;
import cn.nukkit.level.particle.LavaParticle;
import cn.winfxk.arlene.tool.Tool;

/**
 * 仅生成岩浆粒子
 * 
 * @Createdate 2020/09/15 02:04:26
 * @author Winfxk
 */
public class LavaLevitate extends Levitate {

	public LavaLevitate(Position position) {
		super(position);
	}

	@Override
	public boolean Tick() {
		int While = Tool.getRand(10, 50);
		for (int i = 0; i < While; i++)
			position.level.addParticle(new LavaParticle(getVector3()));
		return true;
	}
}
