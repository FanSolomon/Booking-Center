package com.fansolomon.bookingService.validate.code.sms;

import com.fansolomon.bookingService.properties.BcServiceConstants;
import com.fansolomon.bookingService.validate.code.ValidateCode;
import com.fansolomon.bookingService.validate.code.impl.AbstractValidateCodeProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * @author Colin
 * @since 2020-07-12
 */
@Component("smsValidateCodeProcessor")
public class SmsCodeProcessor extends AbstractValidateCodeProcessor<ValidateCode> {

	/**
	 * 短信验证码发送器
	 */
	@Autowired
	private SmsCodeSender smsCodeSender;
	
	@Override
	protected void send(ServletWebRequest request, ValidateCode validateCode) throws Exception {
		String paramName = BcServiceConstants.DEFAULT_PARAMETER_NAME_MOBILE;
		//getRequiredStringParameter表示请求中必须有paramName的参数
		String mobile = ServletRequestUtils.getRequiredStringParameter(request.getRequest(), paramName);
		smsCodeSender.send(mobile, validateCode.getCode());
	}
}
