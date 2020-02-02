package com.dou.test.controller;

import com.dou.test.redis.conf.RedisUtil;
import com.dou.test.redis.distributed.one.RateLimit;
import com.dou.test.redis.distributed.two.RateLimitService;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
@RequestMapping("/rate")
public class RateLimitController {

    @Resource
    Environment environment;
    @Resource
    RedisUtil redisUtil;
    @Resource
    RateLimit rateLimit;
    @Resource
    RateLimitService rateLimitService;

    @GetMapping("/test")
    public Boolean test() {
        String key = "test_rateLimit_key";
        int max = 10;  // 令牌桶大小
        int rate = 10; // 令牌每秒恢复速度
        Boolean tag = rateLimit.rateLimit(key, max, rate);
        if(tag){
            System.out.println(System.currentTimeMillis());
        }
        return tag;
    }

}
