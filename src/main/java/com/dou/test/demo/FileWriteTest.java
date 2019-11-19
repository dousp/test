package com.dou.test.demo;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author dsp
 * @date 2019-10-12
 */
public class FileWriteTest {
    public static void main(String[] args) {
        try (Stream<String> lines = Files.lines(Paths.get("src\\main\\resources\\123.json"), Charset.defaultCharset())) {
            List<String> list= lines.filter(s -> !(s.trim().startsWith("/*"))).map(e->e.trim().equals("")? ",": e).collect(Collectors.toList());
            // List<String> list= lines.filter(s -> s.contains("exam_item_code")).map(e->e.trim().split(":")[1].trim()).distinct().collect(Collectors.toList());
            Path path = Paths.get("src\\main\\resources\\123-1.json");
            Files.write(path, list, StandardCharsets.UTF_8);

            // String list= lines.filter(s -> s.contains("exam_item_code")).map(e->e.trim().split(":")[1].trim()).distinct().collect(Collectors.joining());
            // Path path = Paths.get("src\\main\\resources\\result_list2.txt");
            // Files.write(path, list.getBytes());

            // 追加
            // Files.write(path, list, StandardCharsets.UTF_8, StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("IOException");
        }
    }
}
