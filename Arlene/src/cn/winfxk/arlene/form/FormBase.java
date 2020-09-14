package cn.winfxk.arlene.form;

import java.util.ArrayList;
import java.util.List;

import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.form.response.FormResponse;
import cn.nukkit.form.response.FormResponseCustom;
import cn.nukkit.form.response.FormResponseModal;
import cn.nukkit.form.response.FormResponseSimple;
import cn.winfxk.arlene.Activate;
import cn.winfxk.arlene.Message;
import cn.winfxk.arlene.MyPlayer;
import cn.winfxk.arlene.tool.ItemList;
import cn.winfxk.arlene.tool.Tool;

/**
 * 抽象UI类
 * 
 * @Createdate 2020/07/06 21:55:19
 * @author Winfxk
 */
public abstract class FormBase implements Cloneable {
	private FormBase form;
	protected Player player;
	private String Son, Name;
	protected FormBase upform;
	protected MyPlayer myPlayer;
	private String[] Key = {};
	private Object[] Data = {};
	protected static final String FormKey = "Form";
	protected List<String> listKey = new ArrayList<>();
	protected static Server server = Server.getInstance();
	protected static Activate ac = Activate.getActivate();
	protected static Message msg = Activate.getActivate().getMessage();
	protected static ItemList itemList = Activate.getActivate().getItemList();
	protected static final String Title = "Title", Content = "Content", Close = "Close", Back = "Back";
	public static final String[] BaseKey = { "{Player}", "{Money}", "{isBuy}", "{BabyName}", "{BabyHealth}",
			"{BabyMaxHealth}", "{Skin}", "{Dirthday}", "{BabyAge}", "{Growth}", "{LevelName}", "{Level}", "{Breast}",
			"{MaxBreast}", "{Masturbation}", "{MaxMasturbation}", "{BreastExp}", "{MasturbationExp}", "{Score}",
			"{Ancestry}", "{AncestryLevel}", "{Consecutive}", "{MasturbationCount}", "{BreastCount}", "{MeLevel}",
			"{MinLevel}", "{MaxLevel}", "{Exp}", "{MaxExp}", "{Evolve}" };
	public static final String[] ItemBaseKeys = { "{Schedule}", "{ItemName}", "{ItemCount}", "{ItemMaxCount}", "{ID}",
			"{Damage}" };

	public FormBase(Player player, FormBase upform) {
		this.player = player;
		if (upform != null)
			try {
				this.upform = upform.clone();
			} catch (CloneNotSupportedException e) {
				throw new FormException("无法克隆页面对象");
			}
		myPlayer = ac.getPlayers(player);
		Son = Name = getClass().getSimpleName();
	}

	/**
	 * 返回权不足的文本
	 * 
	 * @return
	 */
	public String getNotPermission() {
		return msg.getMessage("权限不足", this);
	}

	/**
	 * 发送消息给玩家并且返回结果
	 * 
	 * @param string
	 * @return
	 */
	public boolean sendMessage(String string) {
		player.sendMessage(string);
		return true;
	}

	/**
	 * 判断是否有上一页 有的话返回上一页，没有则关闭界面
	 * 
	 * @return
	 */
	public boolean isBack() {
		return upform == null ? true : setForm(upform).make();
	}

	/**
	 * 返回一个“返回”或：“关闭”按钮的文本内容
	 * 
	 * @return
	 */
	public String getBack() {
		return msg.getSon(FormKey, upform == null ? Close : Back);
	}

	/**
	 * 返回界面标题
	 * 
	 * @return
	 */
	public String getTitle() {
		return getString(Title);
	}

	/**
	 * 返回界面内容
	 * 
	 * @return
	 */
	public String getContent() {
		return getString(Content);
	}

	/**
	 * 获取界面文本数据
	 * 
	 * @param Key
	 * @return
	 */
	public String getString(String Key) {
		return msg.getSun(FormKey, Son, Key, this);
	}

	/**
	 * 获取界面文本数据
	 * 
	 * @param Key
	 * @return
	 */
	public String getString(String Key, String[] Keys, Object[] Data) {
		return msg.getSun(FormKey, Son, Key, Tool.Arrays(Keys, getKey()), Tool.Arrays(Data, getData()), player);
	}

	/**
	 * 当界面被关闭时调用
	 * 
	 * @throws FormException 可能出现的异常
	 */
	public void wasClosed() throws FormException {
	}

	/**
	 * 获取UI名称
	 * 
	 * @param son
	 */
	public String getName() {
		return Name;
	}

	/**
	 * 获取UI键
	 * 
	 * @param son
	 */
	public String getSon() {
		return Son;
	}

	/**
	 * 设置UI名称
	 * 
	 * @param son
	 */
	public void setName(String name) {
		Name = name;
	}

	/**
	 * 设置UI键
	 * 
	 * @param son
	 */
	public void setSon(String son) {
		Son = son;
	}

	/**
	 * 设置数据
	 *
	 * @param objects 要设置的Msg数据
	 */
	public void setD(Object... objects) {
		Data = objects;
	}

	/**
	 * 设置表
	 *
	 * @param strings 要设置的Msg键
	 */
	public void setK(String... strings) {
		Key = strings;
	}

	/**
	 * 设置下一个想要跳转的界面
	 * 
	 * @param form
	 * @return
	 */
	protected FormBase setForm(FormBase form) {
		this.form = form;
		return this;
	}

	/**
	 * 开始显示已经设置的界面
	 * 
	 * @return
	 */
	public boolean make() {
		if (form == null)
			throw new FormException("The interface is null, unable to display normally! Please contact Winfxk.");
		return (myPlayer.form = form).makeMain();
	}

	/**
	 * 将基础数据转换为Modal
	 * 
	 * @param data 基础数据
	 * @return
	 */
	protected FormResponseModal getModal(FormResponse data) {
		return (FormResponseModal) data;
	}

	/**
	 * 将基础数据转换为Simple
	 * 
	 * @param data 基础数据
	 * @return
	 */
	protected FormResponseSimple getSimple(FormResponse data) {
		return (FormResponseSimple) data;
	}

	/**
	 * 将基础数据转换为Custom
	 * 
	 * @param data 基础数据
	 * @return
	 */
	protected FormResponseCustom getCustom(FormResponse data) {
		return (FormResponseCustom) data;
	}

	/**
	 * 开始创建界面
	 * 
	 * @return
	 */
	public abstract boolean makeMain();

	/**
	 * 处理返回的数据
	 * 
	 * @param data
	 * @return
	 */
	public abstract boolean disMain(FormResponse data);

	/**
	 * 返回UI-ID
	 * 
	 * @return
	 */
	protected int getID() {
		myPlayer.ID = myPlayer.ID == 0 ? 1 : myPlayer.ID == 1 ? 2 : 0;
		return ac.getFormID().getID(myPlayer.ID);
	}

	/**
	 * 返回语言文本的值
	 * 
	 * @return
	 */
	public Object[] getData() {
		return Data;
	}

	/**
	 * 返回语言文本的Key
	 * 
	 * @return
	 */
	public String[] getKey() {
		return Key;
	}

	/**
	 * 返回打开UI的玩家对象
	 * 
	 * @return
	 */
	public Player getPlayer() {
		return player;
	}

	/**
	 * 返回可能存在的上一个界面
	 * 
	 * @return
	 */
	public FormBase getUpform() {
		return upform;
	}

	/**
	 * 返回打开UI的玩家数据对象
	 * 
	 * @return
	 */
	public MyPlayer getMyPlayer() {
		return myPlayer;
	}

	@Override
	public FormBase clone() throws CloneNotSupportedException {
		FormBase base = (FormBase) super.clone();
		base.listKey.clear();
		return base;
	}

	@Override
	public String toString() {
		return player.getName() + "=>" + getName();
	}
}
