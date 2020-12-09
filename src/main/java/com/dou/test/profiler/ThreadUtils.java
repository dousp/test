package com.dou.test.profiler;

import org.apache.commons.lang3.RandomUtils;

public class ThreadUtils {

    private ThreadUtils() {
    }

    public static void randomSleep() {
        try {
            Thread.sleep(RandomUtils.nextInt(100, 200));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
