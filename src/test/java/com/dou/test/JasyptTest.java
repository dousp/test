package com.dou.test;

import org.jasypt.encryption.StringEncryptor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JasyptTest {

    @Autowired
    Environment environment;

    @Autowired
    StringEncryptor encryptorBean;

    static {
        // System.setProperty("jasypt.encryptor.password", "xxxxxx");
    }

    @Test
    public void encryptProperty() {

        /**
         * must set jasypt.encryptor.password in your system env
         */
        String RedisIP = "";
        String RedisPW = "";
        String RedisPort = "";

        String encryptedRedisIP = encryptorBean.encrypt(RedisIP);
        String encryptedRedisPW = encryptorBean.encrypt(RedisPW);
        String encryptedRedisPort = encryptorBean.encrypt(RedisPort);
        System.out.println("encryptedRedisIP:->"+encryptedRedisIP);
        System.out.println("encryptedRedisPW:->"+encryptedRedisPW);
        System.out.println("encryptedRedisPort:->"+encryptedRedisPort);

        // Assert.assertEquals("douspeng", environment.getProperty("secret.property"));

        // String message = "douspeng";
        // // pF9AnGzNIEAyxOouFsx5k5YJfW4xGKFgYfdnC2JGEWR53T9v+nDOhuvgVgTJy4x3
        // String encrypted = encryptorBean.encrypt(message);
        // System.out.println("Encrypted melloware message: " + encrypted);
        // String decrypted = encryptorBean.decrypt(encrypted);
        // System.out.println("decrypted: " + decrypted);
        // Assert.assertEquals(message, decrypted);
        // System.out.println();
    }
}
