package cn.winfxk.arlene;

/**
 * @author Winfxk
 */
public class ArleneException extends RuntimeException {
	public ArleneException() {
		this("An unknown error occurred!Please send the error log to Winfxk@qq.com");
	}

	public ArleneException(String Message) {
		super(Message);
	}

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
}
