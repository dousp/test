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
    private boolean isFast;

    public static List<Person> buildDemoList(){
        List<Person> list = new ArrayList<>();
        list.add(Person.builder().id(1000).name("李XX").cardId("123").age(23).email("123@qq.com").isFast(true).addr("通州").build());
        list.add(Person.builder().id(1001).name("张XX").cardId("456").age(43).email("456@qq.com").isFast(false).addr("昌平").build());
        list.add(Person.builder().id(1002).name("王XX").cardId("789").age(53).email("789@qq.com").isFast(true).addr("海淀").build());
        list.add(Person.builder().id(1002).name("赵XX").cardId("990").age(66).email("220@qq.com").isFast(false).addr("朝阳").build());
        list.add(Person.builder().id(1002).name("钱XX").cardId("990").age(76).email("340@qq.com").isFast(true).addr("石景山").build());
        list.add(Person.builder().id(1002).name("孙XX").cardId("990").age(86).email("670@qq.com").isFast(false).addr("大兴").build());
        return list;
    }

    public static void main(String[] args) {
        buildDemoList().stream().forEach(person -> System.out.println(person.getId()));
    }
}
