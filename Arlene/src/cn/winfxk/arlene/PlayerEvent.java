package cn.winfxk.arlene;

import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

import cn.nukkit.Player;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerFormRespondedEvent;
import cn.nukkit.event.player.PlayerJoinEvent;
import cn.nukkit.event.player.PlayerQuitEvent;
import cn.nukkit.form.response.FormResponse;
import cn.nukkit.form.response.FormResponseCustom;
import cn.nukkit.form.response.FormResponseModal;
import cn.nukkit.form.response.FormResponseSimple;

/**
 * 事件处理器
 * 
 * @Createdate 2020/07/07 10:19:27
 * @author Winfxk
 */
public class PlayerEvent implements Listener {
	private Activate ac;
	private Message msg;
	private Map<Player, Instant> instant = new HashMap<>();

	public PlayerEvent(Activate ac) {
		msg = ac.getMessage();
		this.ac = ac;
	}

	/**
	 * 表单数据接收事件
	 *
	 * @param e
	 */
	@EventHandler
	public void onFormResponded(PlayerFormRespondedEvent e) {
		Player player = e.getPlayer();
		try {
			if (player == null)
				return;
			MyPlayer myPlayer = ac.getPlayers(player.getName());
			if (myPlayer == null || myPlayer.form == null)
				return;
			FormResponse data = e.getResponse();
			if (data != null && (data instanceof FormResponseCustom || data instanceof FormResponseModal
					|| data instanceof FormResponseSimple)) {
				FormID f = ac.getFormID();
				int ID = e.getFormID();
				if (ID == myPlayer.ID || ID == f.getID(0) || ID == f.getID(1) || ID == f.getID(2))
					if (e.wasClosed())
						myPlayer.form.wasClosed();
					else
						myPlayer.form.disMain(data);
			}
		} catch (Exception e2) {
			e2.printStackTrace();
			if (player != null)
				player.sendMessage(msg.getMessage("数据处理错误", new String[] { "{Player}", "{Money}", "{Error}" },
						new Object[] { player.getName(), MyPlayer.getMoney(player.getName()), e2.getMessage() }));
		}
	}

	/**
	 * 加入多人运动事件
	 * 
	 * @param e
	 * @throws IOException
	 */
	@EventHandler
	public void onJoin(PlayerJoinEvent e) throws IOException {
		Player player = e.getPlayer();
		if (ac.isPlayers(player))
			ac.getPlayers(player).setPlayer(player);
		else
			ac.setPlayers(player, new MyPlayer(player));
	}

	/**
	 * 滚蛋事件
	 * 
	 * @param e
	 */
	@EventHandler
	public void onQuit(PlayerQuitEvent e) {
		Player player = e.getPlayer();
		if (!ac.isPlayers(player))
			return;
		ac.removePlayers(player);
	}

	/**
	 * 判断是否间隔够高
	 * 
	 * @param player
	 * @return
	 */
	private boolean isTime(Player player) {
		return !instant.containsKey(player) || Duration.between(instant.get(player), Instant.now()).toMillis() > 500;
	}
}
