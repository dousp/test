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
        // String RedisIP = "";
        // String RedisPW = "";
        // String RedisPort = "";

        // String encryptedRedisIP = encryptorBean.encrypt(RedisIP);
        // String encryptedRedisPW = encryptorBean.encrypt(RedisPW);
        // String encryptedRedisPort = encryptorBean.encrypt(RedisPort);
        // System.out.println("encryptedRedisIP:->"+encryptedRedisIP);
        // System.out.println("encryptedRedisPW:->"+encryptedRedisPW);
        // System.out.println("encryptedRedisPort:->"+encryptedRedisPort);

        // System.out.println(encryptorBean.encrypt("47.94.0.34:9876"));
        // System.out.println(encryptorBean.decrypt("tjVfDITYYVe7uJnI9knJ8Qo1Me5oZmHVhVLBBUwNwh0ZBMGxk9b6B806RtcmGhwN"));
        // System.out.println(encryptorBean.decrypt("VqC8xtO6XZaslRRr09j3B6bfrcUHLgXdi9+wEGBCukp+mLvbIr1iZRo2xMrM4i11/JJO3gw58L6KSOpOc1h133YP6qJh/P4v0gFCzLGmv40="));

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
