package cn.winfxk.arlene;

import java.time.Instant;

import cn.nukkit.plugin.PluginBase;

/**
 * @Createdate 2020/09/14 18:18:18
 * @author Winfxk
 */
public class Arlene extends PluginBase {
	protected Instant loadTime;
	private static Activate ac;
	protected static Arlene arlene;

	@Override
	public void onLoad() {
		arlene = this;
		getLogger().info(getName() + " start load..");
		if (!getDataFolder().exists())
			getDataFolder().mkdirs();
	}

	/**
	 * 返回插件数据中心</br>
	 * <b>PS: </b> 我不喜欢把太多数据放到插件主类，有意见你就去屎吧
	 *
	 * @return
	 */
	public static Activate getInstance() {
		return ac;
	}

	@Override
	public void onEnable() {
		loadTime = Instant.now();
		ac = new Activate(this);
	}

	public static Arlene getArlene() {
		return arlene;
	}

	@Override
	public void onDisable() {
		try {
			getLogger().info(ac.getMessage().getMessage("插件关闭"));
		} catch (Exception e) {
		}
	}
}
