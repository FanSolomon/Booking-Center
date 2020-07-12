package com.fansolomon.bookingService.validate.code;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author Colin
 * @since 2020-07-12
 */
@Component
public class ValidateCodeProcessorHolder {

	/**
	 * 收集系统中所有{@link com.fansolomon.bookingService.validate.code.ValidateCodeProcessor}接口的实现
	 * 依赖查找！
	 * spring在启动的时候，会查找spring容器中所有的ValidateCodeProcessor实现
	 * 然后将相应的been以been的名字作为Map的key。
	 */
	@Autowired
	private Map<String, ValidateCodeProcessor> validateCodeProcessors;

	public ValidateCodeProcessor findValidateCodeProcessor(ValidateCodeType type) {
		return findValidateCodeProcessor(type.toString().toLowerCase());
	}

	public ValidateCodeProcessor findValidateCodeProcessor(String type) {
		String name = type.toLowerCase() + ValidateCodeProcessor.class.getSimpleName();
		ValidateCodeProcessor processor = validateCodeProcessors.get(name);
		if (processor == null) {
			throw new ValidateCodeException("验证码处理器" + name + "不存在");
		}
		return processor;
	}
}
