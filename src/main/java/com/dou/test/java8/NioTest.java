package com.dou.test.java8;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Stream;

/**
 * @author dsp
 * @date 2019-07-03
 */
public class NioTest {
    public static void main(String[] args) {
        long uniqueWords = 0;
        try (Stream<String> lines = Files.lines(Paths.get("src\\main\\resources\\read.txt"), Charset.defaultCharset())) {
            uniqueWords = lines.flatMap(line -> Arrays.stream(line.split(" "))).distinct().count();
            System.out.println(uniqueWords);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("IOException");
        }
    }
}
