package com.dou.test.redis.distributed.two;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.script.DefaultRedisScript;

/**
 * @author dsp
 * @date 2019-08-20
 */
@Configuration
public class RateLimitConf {

    @Bean("ratelimitLua")
    public DefaultRedisScript getRedisScript() {
        DefaultRedisScript redisScript = new DefaultRedisScript();
        redisScript.setLocation(new ClassPathResource("lua/ratelimit.lua"));
        redisScript.setResultType(java.lang.Long.class);
        return redisScript;
    }
    @Bean("ratelimitInitLua")
    public DefaultRedisScript getInitRedisScript() {
        DefaultRedisScript redisScript = new DefaultRedisScript();
        redisScript.setLocation(new ClassPathResource("lua/ratelimitInit.lua"));
        redisScript.setResultType(java.lang.Long.class);
        return redisScript;
    }
}
