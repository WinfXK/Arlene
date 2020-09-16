package cn.winfxk.arlene.game;

import cn.winfxk.arlene.Activate;
import cn.winfxk.arlene.element.special.SpecialManage;
import cn.winfxk.arlene.entity.EntityManage;

/**
 * @Createdate 2020/09/16 14:24:47
 * @author Winfxk
 */
public class GameDataManage {
	private static GameDataManage manage;
	private EntityManage manage2;
	private SpecialManage manage3;

	public GameDataManage(Activate activate) {
		manage = this;
		manage2 = new EntityManage(activate);
	}

	/**
	 * 返回游戏数据管理器
	 * 
	 * @return
	 */
	public static GameDataManage getManage() {
		return manage;
	}

	/**
	 * 返回游戏实体管理器
	 * 
	 * @return
	 */
	public EntityManage getEntityManage() {
		return manage2;
	}

	/**
	 * 返回游戏特殊点管理器
	 * 
	 * @return
	 */
	public SpecialManage getSpecialManage() {
		return manage3;
	}
}
