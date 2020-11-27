package com.dou.test;

import com.dou.test.redis.distributed.one.RateLimit;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author dsp
 * @date 2019-08-19
 */
@SpringBootTest
@Slf4j
public class RedisLimitTest {

    @Resource
    RateLimit rateLimit;

    @Test
    public void rateLimitTest() throws InterruptedException {
        String key = "test_rateLimit_key";
        int max = 10;  //令牌桶大小
        int rate = 10; //令牌每秒恢复速度

        AtomicInteger successCount = new AtomicInteger(0);
        Executor executor = Executors.newFixedThreadPool(10);
        CountDownLatch countDownLatch = new CountDownLatch(30);

        for (int i = 0; i < 30; i++) {
            executor.execute(() -> {
                boolean isAllow = rateLimit.rateLimit(key, max, rate);
                if (isAllow) {
                    successCount.addAndGet(1);
                }
                log.info(Boolean.toString(isAllow));
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        log.info("请求成功{}次", successCount.get());
    }

    @Test
    public void scriptTest() throws IOException {
        ClassPathResource cpr = new ClassPathResource("lua/ratelimit.lua");
        System.out.println(cpr.exists());
        System.out.println(cpr.getDescription());
        System.out.println(cpr.getFilename());
        System.out.println(cpr.getFile().toString());

    }
}
