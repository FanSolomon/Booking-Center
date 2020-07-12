package com.fansolomon.bookingService.validate.code.sms;

/**
 * @author Colin
 * @since 2020-07-12
 */
public interface SmsCodeSender {
	void send(String mobile, String code);
}
