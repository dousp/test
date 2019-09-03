package com.dou.test.java8;

import com.dou.test.entity.Checker;
import com.dou.test.entity.Customer;
import com.dou.test.entity.Person;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLOutput;
import java.util.*;
import java.util.function.IntSupplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class StreamTest {

    private static List<Person> personList = Person.buildDemoList();

    public static void main(String[] args) {

        //  // 1. init
        //  String [] strArray = new String[] {"a", "b", "c"};
        //  Stream stream2 = Stream.of("a", "b", "c");
        //
        //  // 2. Arrays
        //  stream2 = Stream.of(strArray);
        //  stream2 = Arrays.stream(strArray);
        //  String[] strArray1 = (String[]) stream2.toArray(String[]::new);
        //
        //  // 3. Collections
        //  List<String> list1 = (List<String>) stream2.collect(toList());
        //  List<String> output = list1.stream().map(String::toUpperCase).collect(toList());
        //  output.forEach(System.out::println);
        //  List<String> list2 = (List<String>) stream2.collect(Collectors.toCollection(ArrayList::new));
        //  Set set1 = (Set) stream2.collect(Collectors.toSet());
        //  Stack stack1 = (Stack) stream2.collect(Collectors.toCollection(Stack::new));
        //
        //  // 4. range
        //  IntStream.of(1, 2, 3).forEach(System.out::println);
        //  IntStream.range(1, 3).forEach(System.out::println);
        //  IntStream.rangeClosed(1, 3).forEach(System.out::println);
        //
         // 5. joining
         // List<String> listJoining = Arrays.asList("A","B","C","D");
         // String result0=  listJoining.stream().collect(Collectors.joining());
         // String result1=  listJoining.stream().collect(Collectors.joining("-"));
         // String result2=  listJoining.stream().collect(Collectors.joining("-","[","]"));
         // System.out.println(result0);
         // System.out.println(result1);
         // System.out.println(result2);

        Checker c1 = new Checker();
        c1.setName("dd");
        Checker c2 = new Checker();
        c2.setName("dd2");
        Checker c3 = new Checker();
        c3.setName("dd3");
        List<Checker> checkerList = new ArrayList<>();
        checkerList.add(c1);
        checkerList.add(c2);
        checkerList.add(c3);

        Customer customer1 = new Customer();
        customer1.setName("dd2");
        Customer customer2 = new Customer();
        customer2.setName("dd3");
        List<Customer> customerList = new ArrayList<>();
        customerList.add(customer1);
        customerList.add(customer2);

        List<Checker> list = checkerList.stream()
                .map(s->customerList.stream()
                                .filter(e->s.getName().equals(e.getName()))
                                .findAny().map(m -> s).orElse(null))
                .filter(Objects::nonNull).collect(Collectors.toList());

        for (Checker checker : list) {
            System.out.println(checker.getName());
        }

        //
        //  // 6. limit skip
        //  List<Person> personList = personList.stream().filter(p -> p.getId() > 1000).skip(1).limit(1).collect(toList());
        //  personList.stream().forEach(person -> System.out.println(person.getName()));
        //  List<Integer> numbers1 = Arrays.asList(1, 2, 3);
        //  List<Integer> numbers2 = Arrays.asList(3, 4);
        //  List<int[]> pairs = numbers1.stream()
        //                  .flatMap( i -> numbers2.stream().map(j -> new int[]{i, j}) ).collect(toList());
        //  List<int[]> pairs2 = numbers1.stream()
        //                  .flatMap( i -> numbers2.stream().filter( j -> (i + j) % 3 == 0 ).map(j -> new int[]{i, j})).collect(toList());
        //
        //  // 7. flatMap
        //  List<String> words = Arrays.asList("Java 8", "Lambdas", "In", "Action");
        //  words.stream().map(s -> s.split("")).flatMap(Arrays::stream).distinct().collect(toList());
        //
        //  // 8. Stream生成斐波那契数列
        //  Stream.iterate(new int[]{0,1}, t ->new int[]{t[1], t[0] +t[1]}  ).limit(10).map(t -> t[0]).forEach(System.out::println);
        //
        //  // 9. anyMatch allMatch noneMatch
        // boolean Tag = personList.stream().anyMatch(Person::isFast);
        // System.out.println(Tag);
        //
        // // 10. findAny  findFirst
        // personList.stream().filter(Person::isFast).findAny().ifPresent(person -> System.out.println(person.getName()));
        //
        // // 11. reduce
        // int count = personList.stream().map(person -> 1).reduce(0, Integer::sum);
        //
        // // 12. sort
        // personList.stream().sorted(Comparator.comparing(Person::getAge)).forEach(person -> System.out.println(person.getName()));
        //
        // // 13.sorted
        // String traderStr =
        //         personList.stream()
        //                 .map(Person::getName)
        //                 .distinct()
        //                 .sorted()
        //                 .reduce("", (n1, n2) -> n1 + n2+ "->");
        // System.out.println(traderStr);
        //
        // // 14 .mapToInt
        // int ageSum = personList.stream()
        //         .mapToInt(Person::getAge)
        //         .sum();
        // System.out.println(ageSum);
        //
        // // 15. 文件stream
        // long uniqueWords = 0;
        // try (Stream<String> lines = Files.lines(Paths.get("src\\main\\resources\\read.txt"), Charset.defaultCharset())) {
        //     uniqueWords = lines.flatMap(line -> Arrays.stream(line.split(" "))).distinct().count();
        //     System.out.println(uniqueWords);
        // } catch (IOException e) {
        //     e.printStackTrace();
        //     System.out.println("IOException");
        // }
        //
        //  // 16. iterate
        // Stream.iterate(0, n -> n + 2).limit(10).forEach(System.out::println);
        // // 17. generate
        // Stream.generate(Math::random).limit(5).forEach(System.out::println);
        // 18 IntSupplier
        // IntSupplier intSupplier = new IntSupplier() {
        //     private int previous = 0;
        //     private int current = 1;
        //     @Override
        //     public int getAsInt() {
        //         int oldPrevious = this.previous;
        //         int nextValue = this.previous + this.current;
        //         this.previous = this.current;
        //         this.current = nextValue;
        //         return oldPrevious;
        //     }
        // };
        // IntStream.generate(intSupplier).limit(10).forEach(System.out::println);

        // 19.定义收集齐
        // Map<Boolean, List<Integer>> primeNumbersList = IntStream.rangeClosed(2, 100).boxed()
        //         .collect(new PrimeNumbersCollector());
        // primeNumbersList.forEach((aBoolean, integers) -> {
        //     System.out.println(aBoolean.toString()+":"+integers.size());
        // });

        // System.out.println(Math.sqrt(6));

    }
}
