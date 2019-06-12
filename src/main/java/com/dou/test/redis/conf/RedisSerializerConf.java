package com.dou.test.redis.conf;

import com.alibaba.fastjson.support.spring.GenericFastJsonRedisSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;

/**
 * @author dsp
 * 参考官方： https://github.com/alibaba/fastjson/wiki/%E5%9C%A8-Spring-%E4%B8%AD%E9%9B%86%E6%88%90-Fastjson#%E5%9C%A8-spring-data-redis-%E4%B8%AD%E9%9B%86%E6%88%90-fastjson
 */
@Configuration
public class RedisSerializerConf {

    @Bean
    public RedisSerializer genericFastJsonRedisSerializer() {
        return new GenericFastJsonRedisSerializer();
    }

    @Bean
    public RedisTemplate redisTemplate(RedisConnectionFactory redisConnectionFactory, RedisSerializer genericFastJsonRedisSerializer) {
        RedisTemplate redisTemplate = new RedisTemplate();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        //设置默认的Serialize，包含 keySerializer & valueSerializer
        redisTemplate.setDefaultSerializer(genericFastJsonRedisSerializer);
        //redisTemplate.setKeySerializer(genericFastJsonRedisSerializer);//单独设置keySerializer
        //redisTemplate.setValueSerializer(genericFastJsonRedisSerializer);//单独设置valueSerializer
        return redisTemplate;
    }

    @Bean
    public StringRedisTemplate stringRedisTemplate(RedisConnectionFactory redisConnectionFactory, RedisSerializer genericFastJsonRedisSerializer) {
        StringRedisTemplate stringRedisTemplate = new StringRedisTemplate();
        stringRedisTemplate.setConnectionFactory(redisConnectionFactory);
        //设置默认的Serialize，包含 keySerializer & valueSerializer
        stringRedisTemplate.setDefaultSerializer(genericFastJsonRedisSerializer);
        //redisTemplate.setKeySerializer(genericFastJsonRedisSerializer);//单独设置keySerializer
        //redisTemplate.setValueSerializer(genericFastJsonRedisSerializer);//单独设置valueSerializer
        return stringRedisTemplate;
    }
}
