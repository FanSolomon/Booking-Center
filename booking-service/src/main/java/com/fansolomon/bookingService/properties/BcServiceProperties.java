package com.fansolomon.bookingService.properties;

import lombok.Data;

/**
 * @author Colin
 * @since 2020-07-12
 */
@Data
public class BcServiceProperties {
	
	private LoginType loginType = LoginType.JSON;
	
	private int rememberMeSeconds = 3600;
	
}
