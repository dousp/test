package com.dou.test.java8;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamTest {

    public static void main(String[] args) {

        // 1. init
        String [] strArray = new String[] {"a", "b", "c"};
        Stream stream2 = Stream.of("a", "b", "c");

        // 2. Arrays
        stream2 = Stream.of(strArray);
        stream2 = Arrays.stream(strArray);
        String[] strArray1 = (String[]) stream2.toArray(String[]::new);
        // 3. Collections
        List<String> list1 = (List<String>) stream2.collect(Collectors.toList());
        List<String> output = list1.stream().map(String::toUpperCase).collect(Collectors.toList());
        output.forEach(System.out::println);
        List<String> list2 = (List<String>) stream2.collect(Collectors.toCollection(ArrayList::new));
        Set set1 = (Set) stream2.collect(Collectors.toSet());
        Stack stack1 = (Stack) stream2.collect(Collectors.toCollection(Stack::new));


        IntStream.of(1, 2, 3).forEach(System.out::println);
        IntStream.range(1, 3).forEach(System.out::println);
        IntStream.rangeClosed(1, 3).forEach(System.out::println);

        // joining
        List<String> listJoining = Arrays.asList("A","B","C","D");
        String result0=  listJoining.stream().collect(Collectors.joining());
        String result1=  listJoining.stream().collect(Collectors.joining("-"));
        String result2=  listJoining.stream().collect(Collectors.joining("-","[","]"));
        System.out.println(result0);
        System.out.println(result1);
        System.out.println(result2);
    }
}
