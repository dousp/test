package com.dou.test.controller;

import com.dou.test.redis.conf.RedisUtil;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/redis")
public class DemoController {

    @Resource
    Environment environment;

    @Resource
    RedisUtil redisUtil;

    @GetMapping("/{key}/{val}")
    public Boolean set(@PathVariable String key, @PathVariable String val) {

        return redisUtil.set(key,val);
    }

    @GetMapping("/{key}")
    public Object getByKey(@PathVariable String key) {
        return redisUtil.get(key);
    }

    @GetMapping("/env")
    public Object getByKey() {
        String head = environment.getProperty("secret.property");

        String msg =
                environment.getProperty("spring.redis.host") + "/" +
                environment.getProperty("spring.redis.port") + "/" +
                environment.getProperty("spring.redis.password") ;

        return head  + "/" + msg;
    }
}
