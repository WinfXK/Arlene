package cn.winfxk.arlene.form;

import cn.winfxk.arlene.ArleneException;

/**
 * Form异常
 * 
 * @author Winfxk
 */
public class FormException extends ArleneException {
	public FormException() {
		super("An error has occurred in the Form interaction!");
	}

	/**
	 *
	 */
	private static final long serialVersionUID = -2361922630328521067L;

	public FormException(String string) {
		super(string);
	}
}
