package cn.winfxk.arlene.element.particle;

import cn.nukkit.level.Position;
import cn.winfxk.arlene.element.particle.circle.HappyVillagerCircle;
import cn.winfxk.arlene.element.particle.circle.LavaDripCircle;
import cn.winfxk.arlene.element.particle.circle.WaterDripCircle;
import cn.winfxk.arlene.element.particle.levitate.LavaDripLevitate;
import cn.winfxk.arlene.element.particle.levitate.LavaLevitate;
import cn.winfxk.arlene.element.particle.levitate.RainSplashLevitate;
import cn.winfxk.arlene.element.particle.levitate.SpellLevitate;
import cn.winfxk.arlene.element.particle.levitate.WaterDripLevitate;
import cn.winfxk.arlene.element.particle.levitate.WaterLevitate;
import cn.winfxk.arlene.element.special.MyPosition;
import cn.winfxk.arlene.tool.MyMap;

/**
 * 基础粒子显示类型
 * 
 * @Createdate 2020/09/15 01:37:42
 * @author Winfxk
 */
public abstract class BaseParticle extends Position {
	public static final MyMap<String, BaseParticle> map = new MyMap<>();
	{
		BaseParticle[] particles = { new HappyVillagerCircle(), new LavaDripCircle(), new WaterDripCircle(),
				new LavaDripLevitate(), new LavaLevitate(), new RainSplashLevitate(), new SpellLevitate(),
				new WaterDripLevitate(), new WaterLevitate() };
		for (BaseParticle particle : particles)
			map.put(particle.getKey(), particle);
	}

	/**
	 * 用于方便构造
	 */
	public BaseParticle() {
	}

	/**
	 * 每Tick会调用
	 * 
	 * @return
	 */
	public abstract boolean Tick();

	/**
	 * 一般构造类
	 * 
	 * @param position
	 */
	public BaseParticle(Position position) {
		super(position.x, position.y, position.z, position.level);
	}

	/**
	 * 更改坐标
	 * 
	 * @param position
	 * @return
	 */
	public BaseParticle setPosition(Position position) {
		this.x = position.x;
		this.y = position.y;
		this.z = position.z;
		this.level = position.level;
		return this;
	}

	/**
	 * 获取Key
	 * 
	 * @return
	 */
	public String getKey() {
		return getClass().getSimpleName();
	}

	/**
	 * 克隆当前类
	 */
	@Override
	public BaseParticle clone() {
		return (BaseParticle) super.clone();
	}

	/**
	 * 根据坐标点返回一个粒子效果对象
	 * 
	 * @param position
	 * @return
	 */
	public static BaseParticle getParticle(MyPosition position) {
		if (!map.containsKey(position.getParticleType()))
			return null;
		BaseParticle particle = map.get(position.getParticleType()).clone();
		return particle == null ? null : particle.setPosition(position);
	}
}
