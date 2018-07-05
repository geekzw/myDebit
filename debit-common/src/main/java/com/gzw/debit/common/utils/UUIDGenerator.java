package com.gzw.debit.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;
import java.util.UUID;

/**
 * Created by qinyisheng on 16/10/28.
 */
public class UUIDGenerator {

    private static final Logger LOGGER = LoggerFactory.getLogger(UUIDGenerator.class);

    public static String getUUID() {
        UUID firstUUID = UUID.randomUUID();
        UUID secondUUID = UUID.randomUUID();
        Random random = new Random();
        int index = random.nextInt(20);
        String str = firstUUID.toString().substring(0, index) + secondUUID.toString().substring(index);
        str = str.replace("-", "").toUpperCase();

//        LOGGER.info("UUID {}", str);
        return str;
    }
}
