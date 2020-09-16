package cn.winfxk.arlene.element.special;

import cn.nukkit.Server;
import cn.nukkit.level.Position;
import cn.winfxk.arlene.Activate;
import cn.winfxk.arlene.element.GameDateException;
import cn.winfxk.arlene.element.particle.BaseParticle;
import cn.winfxk.arlene.tool.Config;
import cn.winfxk.arlene.tool.MyMap;

/**
 * @Createdate 2020/09/15 00:11:30
 * @author Winfxk
 */
public abstract class MyPosition extends Position {
	protected String Key;
	protected static Activate ac = Activate.getActivate();
	protected static final Server server = Server.getInstance();
	protected Config config;
	protected boolean Particle;
	protected String ParticleType;
	protected BaseParticle ParticleTick;
	protected MyMap<String, Object> map;
	protected boolean Custom = true;

	/**
	 * 随机生成的数据点
	 * 
	 * @param map
	 */
	public MyPosition(MyMap<String, Object> map) {
		super(map.getDouble("X"), map.getDouble("Y"), map.getDouble("Z"),
				server.getLevelByName(map.getString("Level")));
		if (level == null)
			throw new GameDateException("无法获取地图配置信息！ Key： " + getKey() + "  Name：" + getName());
		Key = map.getString("Key");
		Particle = map.getBoolean("Particle");
		ParticleType = map.getString("ParticleType");
		this.map = map;
	}

	/**
	 * 用户自定义生成的数据点
	 * 
	 * @param config
	 */
	public MyPosition(Config config) {
		this(config.getAll());
		this.config = config;
		Custom = false;
	}

	/**
	 * 判断这个点是否是自定义生成的
	 * 
	 * @return
	 */
	public boolean isCustom() {
		return Custom;
	}

	/**
	 * 返回粒子效果渲染对象
	 * 
	 * @return
	 */
	public BaseParticle getParticleTick() {
		return ParticleTick;
	}

	/**
	 * 返回粒子效果类型
	 * 
	 * @return
	 */
	public String getParticleType() {
		return ParticleType;
	}

	/**
	 * 是否启用粒子效果
	 * 
	 * @return
	 */
	public boolean isParticle() {
		return Particle;
	}

	/**
	 * 返回当前坐标的配置文件
	 * 
	 * @return
	 */
	public Config getConfig() {
		return config;
	}

	@Override
	public MyPosition clone() {
		return (MyPosition) super.clone();
	}

	/**
	 * 返回坐标Key
	 * 
	 * @return
	 */
	public String getKey() {
		return Key;
	}

	/**
	 * 返回坐标名称
	 * 
	 * @return
	 */
	private String getName() {
		return getClass().getSimpleName();
	}
}
