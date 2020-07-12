package com.fansolomon.bookingService.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author Colin
 * @since 2020-07-12
 */
//这个类 可以读取所有以fansolomon.security开头的配置项
@Component
@ConfigurationProperties(prefix = "fansolomon.security")
@Data
public class SecurityProperties {

	//注意：此处的变量名 bcService 一定要与配置文件中fansolomon.security后面的那个字符串一致！
	private BcServiceProperties bcService = new BcServiceProperties();
	
	private ValidateCodeProperties code = new ValidateCodeProperties();
}
