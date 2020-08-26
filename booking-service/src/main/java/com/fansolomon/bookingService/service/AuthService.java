package com.fansolomon.bookingService.service;

import com.fansolomon.bookingService.entity.dto.ResultDTO;
import com.fansolomon.bookingService.entity.param.LoginParam;

import java.util.Map;

public interface AuthService {

    ResultDTO<Map<String, String>> login(LoginParam loginParam);

    ResultDTO register(LoginParam loginParam);

    ResultDTO<Map<String, String>> refreshToken(String token);
}
