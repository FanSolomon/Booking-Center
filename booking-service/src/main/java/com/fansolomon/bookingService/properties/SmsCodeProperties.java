package com.fansolomon.bookingService.properties;

import lombok.Data;

/**
 * @author Colin
 * @since 2020-07-12
 */
@Data
public class SmsCodeProperties {

	private int length = 6;
	
	private int expireIn = 60;
	
	private String url;
	
}
