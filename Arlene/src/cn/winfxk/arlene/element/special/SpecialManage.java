package cn.winfxk.arlene.element.special;

import java.io.File;

import cn.winfxk.arlene.Activate;
import cn.winfxk.arlene.element.GameSpace;
import cn.winfxk.arlene.tool.MyMap;

/**
 * @Createdate 2020/09/16 15:40:15
 * @author Winfxk
 */
public class SpecialManage {
	private static SpecialManage manage;
	/**
	 * 箱子的配置文件文件夹
	 */
	private static final File Chest = new File(Activate.getActivate().getPluginBase().getDataFolder(),
			Activate.ChestDirName);
	/**
	 * 矿物点的配置文件夹
	 */
	private static final File Mineral = new File(Activate.getActivate().getPluginBase().getDataFolder(),
			Activate.MineralDirName);
	/**
	 * 传送点的配置文件夹
	 */
	private static final File Teleporter = new File(Activate.getActivate().getPluginBase().getDataFolder(),
			Activate.TeleporterDirName);
	/**
	 * 游戏区域的配置文件夹
	 */
	private static final File GameSpace = new File(Activate.getActivate().getPluginBase().getDataFolder(),
			Activate.GameSpaceDir);
	private MyMap<String, Chest> Chests = new MyMap<>();
	private MyMap<String, Mineral> Minerals = new MyMap<>();
	private MyMap<String, Teleporter> Teleporters = new MyMap<>();
	private MyMap<String, GameSpace> GameSpaces = new MyMap<>();

	public SpecialManage() {
		manage = this;
	}

	/**
	 * 返回箱子列表
	 * 
	 * @return
	 */
	public MyMap<String, Chest> getChests() {
		return Chests;
	}

	/**
	 * 返回游戏区域列表
	 * 
	 * @return
	 */
	public MyMap<String, GameSpace> getGameSpaces() {
		return GameSpaces;
	}

	/**
	 * 返回矿物点列表
	 * 
	 * @return
	 */
	public MyMap<String, Mineral> getMinerals() {
		return Minerals;
	}

	/**
	 * 返回传送点列表
	 * 
	 * @return
	 */
	public MyMap<String, Teleporter> getTeleporters() {
		return Teleporters;
	}
}
