package com.dou.test.entity;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@ToString
@EqualsAndHashCode
public class Person {

    private long id;
    private String cardId;
    private String name;
    private int age;
    private String addr;
    private String email;

    public static List<Person> buildDemoList(){
        List<Person> list = new ArrayList<>();
        list.add(Person.builder().id(1000).name("李").cardId("123").age(23).email("123@qq.com").addr("tongzhou").build());
        list.add(Person.builder().id(1001).name("张").cardId("456").age(43).email("456@qq.com").addr("changping").build());
        list.add(Person.builder().id(1002).name("王").cardId("789").age(53).email("789@qq.com").addr("haidian").build());
        list.add(Person.builder().id(1002).name("赵").cardId("990").age(66).email("990@qq.com").addr("tongzhou").build());
        return list;
    }

    public static void main(String[] args) {
        buildDemoList().stream().forEach(person -> System.out.println(person.getId()));
    }
}
