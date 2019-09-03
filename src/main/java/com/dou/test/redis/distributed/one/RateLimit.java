package com.dou.test.redis.distributed.one;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author dsp
 * @date 2019-08-19
 * code from：https://www.infoq.cn/article/Qg2tX8fyw5Vt-f3HH673
 */
@Slf4j
@Component
public class RateLimit {

    @Resource
    StringRedisTemplate stringRedisTemplate;

    /**
     * @param key 限流接口的 ID
     * @param max 令牌桶的最大大小
     * @param rate 每秒钟恢复的令牌数量
     * @return 是否通过了限流
     */
    public boolean rateLimit(String key, int max, int rate) {
        List<String> keyList = new ArrayList<>(1);
        keyList.add(key);
        return "1".equals(stringRedisTemplate
                .execute(new RateLimitScript(),
                        keyList,
                        Integer.toString(max),
                        Integer.toString(rate),
                        Long.toString(System.currentTimeMillis())));
    }

}
