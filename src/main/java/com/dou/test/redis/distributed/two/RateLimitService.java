package com.dou.test.redis.distributed.two;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.connection.RedisServerCommands;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Collections;

/**
 * @author dsp
 * @date 2019-08-20
 * code from: https://blog.csdn.net/z69183787/article/details/81533396
 */
@Component
public class RateLimitService {

    @Resource
    StringRedisTemplate stringRedisTemplate;

    @Qualifier("getRedisScript")
    @Resource
    RedisScript<Long> ratelimitLua;

    @Qualifier("getInitRedisScript")
    @Resource
    RedisScript<Long> ratelimitInitLua;

    public static final String RATE_LIMIT_KEY = "ratelimit:";

    public enum Token {
        SUCCESS,
        FAILED;
        public boolean isSuccess(){
            return this.equals(SUCCESS);
        }
        public boolean isFailed(){
            return this.equals(FAILED);
        }
    }

    public Token initToken(String key, long currTime, int currToken, int maxToken, int rate, String appName){
        Long currMillSecond = stringRedisTemplate.execute(RedisServerCommands::time);
        if(null == currMillSecond){
            throw new RuntimeException("RateLimitService.initToken无法初始化");
        }
        /*
         redis.pcall("HMSET",KEYS[1],
         "last_mill_second",ARGV[1],
         "curr_permits",ARGV[2],
         "max_burst",ARGV[3],
         "rate",ARGV[4],
         "app",ARGV[5])
         */
        Long acquire = stringRedisTemplate.execute(
                ratelimitInitLua,
                Collections.singletonList(getKey(key)),
                currTime, currToken, maxToken, rate, appName);
        if(null == acquire){
            return Token.FAILED;
        }
        Token token;
        if (acquire == 1 || acquire == 0) {
            token = Token.SUCCESS;
        } else {
            token = Token.FAILED;
        }
        return token;
    }

    /**
     * 获得key操作
     * @param key
     * @return Token
     */
    public Token acquireToken(String key) {
        return acquireToken(key, 1);
    }

    public Token acquireToken(String key, Integer permits) {

        Long currMillSecond = stringRedisTemplate.execute(RedisServerCommands::time);
        if(null ==currMillSecond){
            throw new RuntimeException("RateLimitService.acquireToken无法获取Redis时间");
        }
        Long acquired = stringRedisTemplate.execute(ratelimitLua, Collections.singletonList(getKey(key)), permits.toString(), currMillSecond.toString());
        if(null ==acquired){
            throw new RuntimeException("RateLimitService.acquireToken无法获取锁，执行异常");
        }
        Token token;
        if (acquired == 1) {
            token = Token.SUCCESS;
        } else {
            token = Token.FAILED;
        }
        return token;
    }

    public String getKey(String key) {
        return RATE_LIMIT_KEY + key;
    }
}
