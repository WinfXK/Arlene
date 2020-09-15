package cn.winfxk.arlene.element.particle.levitate;

import cn.nukkit.level.Position;
import cn.nukkit.level.particle.AngryVillagerParticle;
import cn.nukkit.level.particle.BlockForceFieldParticle;
import cn.nukkit.level.particle.BoneMealParticle;
import cn.nukkit.level.particle.BubbleParticle;
import cn.nukkit.level.particle.CloudParticle;
import cn.nukkit.level.particle.CriticalParticle;
import cn.nukkit.level.particle.EnchantParticle;
import cn.nukkit.level.particle.EnchantmentTableParticle;
import cn.nukkit.level.particle.HappyVillagerParticle;
import cn.nukkit.level.particle.HeartParticle;
import cn.nukkit.level.particle.InkParticle;
import cn.nukkit.level.particle.InstantEnchantParticle;
import cn.nukkit.level.particle.InstantSpellParticle;
import cn.nukkit.level.particle.LavaDripParticle;
import cn.nukkit.level.particle.LavaParticle;
import cn.nukkit.level.particle.Particle;
import cn.nukkit.level.particle.RainSplashParticle;
import cn.nukkit.level.particle.RedstoneParticle;
import cn.nukkit.level.particle.SmokeParticle;
import cn.nukkit.level.particle.SpellParticle;
import cn.nukkit.level.particle.SporeParticle;
import cn.nukkit.level.particle.WaterDripParticle;
import cn.nukkit.level.particle.WaterParticle;
import cn.nukkit.math.Vector3;
import cn.winfxk.arlene.element.particle.BaseParticle;
import cn.winfxk.arlene.tool.Tool;

/**
 * 仅在目标上方生成粒子
 * 
 * @Createdate 2020/09/15 01:47:23
 * @author Winfxk
 */
public class Levitate extends BaseParticle {
	private static final Vector3 pos = new Vector3(0, 0, 0);
	private static final Particle[] Particle = { new AngryVillagerParticle(pos), new BlockForceFieldParticle(pos),
			new BoneMealParticle(pos), new BubbleParticle(pos), new CloudParticle(pos), new CriticalParticle(pos),
			new EnchantmentTableParticle(pos), new EnchantParticle(pos), new HappyVillagerParticle(pos),
			new HeartParticle(pos), new InkParticle(pos), new InstantEnchantParticle(pos),
			new InstantSpellParticle(pos), new LavaDripParticle(pos), new LavaParticle(pos),
			new RainSplashParticle(pos), new RedstoneParticle(pos), new SmokeParticle(pos), new SpellParticle(pos),
			new SporeParticle(pos), new WaterDripParticle(pos), new WaterParticle(pos) };

	public Levitate() {
	}

	/**
	 * 随机返回一个粒子
	 * 
	 * @return
	 */
	public static Particle getParticle() {
		Particle particle = Particle[Tool.getRand(0, Particle.length - 1)];
		return (cn.nukkit.level.particle.Particle) particle.clone();
	}

	/**
	 * 随机返回一个粒子
	 * 
	 * @param pos
	 * @return
	 */
	public static Particle getParticle(Vector3 pos) {
		Particle particle = Particle[Tool.getRand(0, Particle.length - 1)];
		particle = (cn.nukkit.level.particle.Particle) particle.clone();
		particle.setComponents(pos.x, pos.y, pos.z);
		return particle;
	}

	public Levitate(Position position) {
		super(position);
	}

	@Override
	public boolean Tick() {
		int While = Tool.getRand(10, 50);
		for (int i = 0; i < While; i++)
			level.addParticle(getParticle(getVector3()));
		return true;
	}

	/**
	 * 返回一个点内的随机坐标
	 * 
	 * @return
	 */
	public Vector3 getVector3() {
		return new Vector3(((int) x) + ((double) Tool.getRand(0, 9)) / 10,
				((int) y) + ((double) Tool.getRand(0, 9)) / 10, ((int) z) + ((double) Tool.getRand(0, 9)) / 10);
	}
}
