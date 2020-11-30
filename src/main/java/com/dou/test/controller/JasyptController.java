package com.dou.test.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


@RestController
@RequestMapping("/jasypt")
public class JasyptController {

    @Value("${secret.property}")
    private String secret;
    @Value("${jasypt.encryptor.password}")
    private String passWord;

    @Resource
    Environment env;

    @GetMapping("/key")
    public String key() {
        System.out.println(env.getProperty("JASYPT_ENCRYPTOR_PASSWORD"));
        System.out.println(this.passWord);
        return this.secret;
    }

}
