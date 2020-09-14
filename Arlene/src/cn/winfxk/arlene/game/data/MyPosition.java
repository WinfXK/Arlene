package cn.winfxk.arlene.game.data;

import cn.nukkit.Server;
import cn.nukkit.level.Position;
import cn.winfxk.arlene.game.data.particle.BaseParticle;
import cn.winfxk.arlene.tool.Config;

/**
 * @Createdate 2020/09/15 00:11:30
 * @author Winfxk
 */
public abstract class MyPosition extends Position {
	protected String Key;
	protected static final Server server = Server.getInstance();
	protected Config config;
	protected boolean Particle;
	protected String ParticleType;
	protected BaseParticle ParticleTick;

	public MyPosition(Config config) {
		super(config.getDouble("X"), config.getDouble("Y"), config.getDouble("Z"),
				server.getLevelByName(config.getString("Level")));
		if (level == null)
			throw new GameDateException("无法获取地图配置信息！ Key： " + getKey() + "  Name：" + getName());
		Key = config.getString("Key");
		Key = Key == null || Key.isEmpty() ? config.getFile().getName() : Key;
		Particle = config.getBoolean("Particle");
		ParticleType = config.getString("ParticleType");
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
