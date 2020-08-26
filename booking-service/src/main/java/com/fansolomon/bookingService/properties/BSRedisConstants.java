package com.fansolomon.bookingService.properties;

/**
 * @author colin
 * @since 2020-08-26
 */
public interface BSRedisConstants {

    String BOOKING_SERVICE_PREFIX = "bs:";

    String PART_ACCESS_TOKEN = BOOKING_SERVICE_PREFIX + "partAccessToken:";

    String REFRESH_TOKEN = BOOKING_SERVICE_PREFIX + "refreshToken:";

    String IS_CHECK_ACCESS_TOKEN = BOOKING_SERVICE_PREFIX + "isCheckAccessToken";
}
