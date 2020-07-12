package com.fansolomon.bookingService.validate.code;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * @author Colin
 * @since 2020-07-12
 */
public interface ValidateCodeGenerator {

	ValidateCode generate(ServletWebRequest request);
}
