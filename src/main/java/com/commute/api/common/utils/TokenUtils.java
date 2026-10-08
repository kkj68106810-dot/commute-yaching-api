package com.commute.api.common.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;

/**
 * TokenUtils
 *
 * @author Administrator
 * @version 1.0.0
 * @since 2026/10/08
 */
@Slf4j
@Component
public class TokenUtils {

    private static SecretKey JWT_SECRET_KEY;

}
