package cn.winfxk.arlene.game.data.particle.levitate;

import cn.nukkit.level.Position;
import cn.nukkit.math.Vector3;
import cn.winfxk.arlene.game.data.particle.BaseParticle;
import cn.winfxk.arlene.tool.Tool;

/**
 * 仅在目标上方生成粒子
 * 
 * @Createdate 2020/09/15 01:47:23
 * @author Winfxk
 */
public class Levitate extends BaseParticle {
	public static final String Key = "Levitate";

	public Levitate(Position position) {
		super(position);
	}

	@Override
	public boolean Tick() {
		int While = Tool.getRand(10, 50);
		for (int i = 0; i < While; i++)
			position.level.addParticle(getParticle(getVector3()));
		return true;
	}

	/**
	 * 返回一个点内的随机坐标
	 * 
	 * @return
	 */
	public Vector3 getVector3() {
		return new Vector3(((int) position.x) + ((double) Tool.getRand(0, 9)) / 10,
				((int) position.y) + ((double) Tool.getRand(0, 9)) / 10,
				((int) position.z) + ((double) Tool.getRand(0, 9)) / 10);
	}

	@Override
	public String getKey() {
		return Key;
	}
}
