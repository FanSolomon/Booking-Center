package com.fansolomon.bookingService.validate.code.image;

import com.fansolomon.bookingService.validate.code.ValidateCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;

/**
 * @author Colin
 * @since 2020-07-12
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ImageCode extends ValidateCode {

	private BufferedImage image;
	
	public ImageCode(BufferedImage image, String code, int expireSec) {
		super(code, expireSec);
		this.image = image;
	}
	
	public ImageCode(BufferedImage image, String code, LocalDateTime expireTime){
		super(code, expireTime);
		this.image = image;
	}
	
}
