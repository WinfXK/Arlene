package cn.winfxk.arlene.element;

import cn.winfxk.arlene.ArleneException;

/**
 * @Createdate 2020/09/15 00:14:19
 * @author Winfxk
 */
public class GameDateException extends ArleneException {
	private static final long serialVersionUID = -8002965194485409393L;

	public GameDateException() {
	}

	public GameDateException(String Message) {
		super(Message);
	}
}
