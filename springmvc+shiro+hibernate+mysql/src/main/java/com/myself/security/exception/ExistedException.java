/**
 * <pre>
 * Copyright:		Copyright(C) 2011-2012, myself.com
 * Filename:		com.ygsoft.security.exception.ExistedException.java
 * Class:			ExistedException
 * Date:			2012-8-13
 * Author:			<a href="mailto:myself@gmail.com">myself</a>
 * Version          1.1.0
 * Description:		
 *
 * </pre>
 **/
 
package com.myself.security.exception;

/** 
 * 	
 * @author 	<a href="mailto:myself@gmail.com">myself</a>
 * Version  1.1.0
 * @since   2012-8-13 上午10:54:15 
 */

public class ExistedException extends ServiceException {

	/** 描述  */
	private static final long serialVersionUID = -598071452360556064L;

	public ExistedException() {
		super();
	}

	public ExistedException(String message) {
		super(message);
	}

	public ExistedException(Throwable cause) {
		super(cause);
	}

	public ExistedException(String message, Throwable cause) {
		super(message, cause);
	}
}
