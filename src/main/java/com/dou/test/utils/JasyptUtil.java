package com.dou.test.utils;

import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;

/**
 * @author dsp
 * @date 2019-07-06
 */
public class JasyptUtil {

    // @Value("${jasypt.encryptor.password}")
    // private String passWord;

    public static StringEncryptor stringEncryptor() {
        PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
        SimpleStringPBEConfig config = new SimpleStringPBEConfig();
        config.setPassword(System.getenv("jasypt.encryptor.password"));
        config.setAlgorithm("PBEWITHHMACSHA512ANDAES_256");
        config.setKeyObtentionIterations("1000");
        config.setPoolSize("1");
        config.setProviderName("SunJCE");
        config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator");
        config.setIvGeneratorClassName("org.jasypt.salt.RandomIVGenerator");
        config.setStringOutputType("base64");
        encryptor.setConfig(config);
        return encryptor;
    }

    public static String encrypt(String string){
        return stringEncryptor().encrypt(string);
    }

    public static String decrypt(String string){
        return stringEncryptor().decrypt(string);
    }

    public static String mqAddr(){
        return decrypt("SrfiYztMDAVftKTO3Mdgq735pJgBwPFVHf4GdJPDlbGdmJ9erKqmWpGiJli2D7R7");
    }

    public static void main(String[] args) {
        // String str = encrypt("123");
        // System.out.println(str);
        // System.out.println(decrypt(str));
        System.out.println(decrypt("SrfiYztMDAVftKTO3Mdgq735pJgBwPFVHf4GdJPDlbGdmJ9erKqmWpGiJli2D7R7"));
    }


}
