package com.dou.test.java8;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Stream;

public class StreamTest {

    public static void main(String[] args) {

        // 1. init
        // String [] strArray = new String[] {"a", "b", "c"};
        // Stream stream2 = Stream.of("a", "b", "c");

        // 2. Arrays
        // stream2 = Stream.of(strArray);
        // stream2 = Arrays.stream(strArray);
        // String[] strArray1 = (String[]) stream2.toArray(String[]::new);

        // 3. Collections
        // List<String> list1 = (List<String>) stream2.collect(Collectors.toList());
        // List<String> output = list1.stream().map(String::toUpperCase).collect(Collectors.toList());
        // output.forEach(System.out::println);
        // List<String> list2 = (List<String>) stream2.collect(Collectors.toCollection(ArrayList::new));
        // Set set1 = (Set) stream2.collect(Collectors.toSet());
        // Stack stack1 = (Stack) stream2.collect(Collectors.toCollection(Stack::new));


        // IntStream.of(1, 2, 3).forEach(System.out::println);
        // IntStream.range(1, 3).forEach(System.out::println);
        // IntStream.rangeClosed(1, 3).forEach(System.out::println);

        // joining
        // List<String> listJoining = Arrays.asList("A","B","C","D");
        // String result0=  listJoining.stream().collect(Collectors.joining());
        // String result1=  listJoining.stream().collect(Collectors.joining("-"));
        // String result2=  listJoining.stream().collect(Collectors.joining("-","[","]"));
        // System.out.println(result0);
        // System.out.println(result1);
        // System.out.println(result2);

        // limit skip
        // List<Person> personList = Person.buildDemoList().stream().filter(p -> p.getId() > 1000).skip(1).limit(1).collect(Collectors.toList());
        // personList.stream().forEach(person -> System.out.println(person.getName()));


        // for (String s : "hello".split("")) {
        //     System.out.println(s);
        // }

        // List<String> words = Arrays.asList("Java 8", "Lambdas", "In", "Action");
        // words.stream().map(s -> s.split("")).flatMap(Arrays::stream).distinct().collect(Collectors.toList());

        // long uniqueWords = 0;
        // try (Stream<String> lines = Files.lines(Paths.get("src\\main\\resources\\read.txt"), Charset.defaultCharset())) {
        //     uniqueWords = lines.flatMap(line -> Arrays.stream(line.split(" "))).distinct().count();
        //     System.out.println(uniqueWords);
        // } catch (IOException e) {
        //     e.printStackTrace();
        //     System.out.println("IOException");
        // }

        Stream.iterate(new int[]{0,1}, t ->new int[]{t[1], t[0] +t[1]}  ).limit(10).map(t -> t[0]).forEach(System.out::println);

    }
}
