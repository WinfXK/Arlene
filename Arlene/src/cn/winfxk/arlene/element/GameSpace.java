package cn.winfxk.arlene.element;

import java.util.ArrayList;
import java.util.List;

import cn.nukkit.level.Position;
import cn.winfxk.arlene.element.special.Teleporter;
import cn.winfxk.arlene.tool.Config;

/**
 * @Createdate 2020/09/14 19:09:42
 * @author Winfxk
 */
public class GameSpace {
	private Config config;
	private Position pos1, pos2;
	private List<Teleporter> teleporters = new ArrayList<>();
	private int Chest;
	

	public GameSpace(Config config) {
	}

}
