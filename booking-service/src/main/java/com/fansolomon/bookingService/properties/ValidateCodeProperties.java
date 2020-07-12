package com.fansolomon.bookingService.properties;

import lombok.Data;

/**
 * @author Colin
 * @since 2020-07-12
 */
@Data
public class ValidateCodeProperties {
	
	private ImageCodeProperties image = new ImageCodeProperties();
	
	private SmsCodeProperties sms = new SmsCodeProperties();
	
}
