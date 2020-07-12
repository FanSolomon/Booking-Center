package com.fansolomon.bookingService.validate.code;

import org.springframework.security.core.AuthenticationException;

/**
 * @author Colin
 * @since 2020-07-12
 */
public class ValidateCodeException extends AuthenticationException {

	private static final long serialVersionUID = -8388793478134412617L;

	public ValidateCodeException(String msg) {
		super(msg);
	}

}
