package com.bosssoft.watcher.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtils {

    private final static DateTimeFormatter ofPattern = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static String retrieveDate() {
        LocalDateTime localDateTime = LocalDateTime.now();
        return ofPattern.format(localDateTime);
    }
}
