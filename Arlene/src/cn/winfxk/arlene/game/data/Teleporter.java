package cn.winfxk.arlene.game.data;

import cn.nukkit.level.Position;
import cn.winfxk.arlene.tool.Config;

/**
 * @Createdate 2020/09/15 00:28:23
 * @author Winfxk
 */
public class Teleporter extends MyPosition {
	private boolean Start;
	private Position End;
	public Teleporter(Config config) {
		super(config);
		Start = config.getBoolean("Start");
		if (!Start)
			End = new Position(config.getDouble("X"), config.getDouble("Y"), config.getDouble("Z"),
					server.getLevelByName(config.getString("Level")));
	}

	/**
	 * 若这个点是起点，那么将会返回终点坐标，否则为Null
	 * 
	 * @return
	 */
	public Position getEnd() {
		return End;
	}

	/**
	 * 判断是否为进入点，若这个值为true 进入后会传送到另一个点
	 * 
	 * @return
	 */
	public boolean isStart() {
		return Start;
	}
}
