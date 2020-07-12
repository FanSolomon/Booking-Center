package com.fansolomon.bookingService.validate.code;

import com.fansolomon.bookingService.properties.SecurityProperties;
import com.fansolomon.bookingService.validate.code.image.ImageCodeGenerator;
import com.fansolomon.bookingService.validate.code.sms.DefaultSmsCodeSender;
import com.fansolomon.bookingService.validate.code.sms.SmsCodeSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Colin
 * @since 2020-07-12
 */
@Configuration
public class ValidateCodeBeanConfig {

	@Autowired
	private SecurityProperties securityProperties;
	
	/**
	 * spring容器中不存在名为imageValidateCodeGenerator的been时 初始化ValidateCodeGenerator这个been
	 * @return
	 */
	@Bean
	@ConditionalOnMissingBean(name = "imageValidateCodeGenerator")
	public ValidateCodeGenerator imageValidateCodeGenerator() {
		ImageCodeGenerator codeGenerator = new ImageCodeGenerator();
		codeGenerator.setSecurityProperties(securityProperties);
		return codeGenerator;
	}
	
	/**
	 * (SmsCodeSender.class)与上面name = 形式的效果一样
	 * @return
	 */
	@Bean
	@ConditionalOnMissingBean(SmsCodeSender.class)
	public SmsCodeSender smsCodeSender() {
		return new DefaultSmsCodeSender();
	}
}
