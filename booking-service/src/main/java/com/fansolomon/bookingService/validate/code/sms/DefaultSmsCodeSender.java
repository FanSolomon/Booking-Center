package com.fansolomon.bookingService.validate.code.sms;

/**
 * @author Colin
 * @since 2020-07-12
 */
public class DefaultSmsCodeSender implements SmsCodeSender {

	@Override
	public void send(String mobile, String code) {
		System.out.println("向手机"+mobile+"发送短信验证码"+code);
	}

}
