package com.dou.test.java8;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class listRemoveTest {

    public static void main(String[] args) {

        /*String s1 = "10^9/L\n";
        // System.out.println(s1.replaceAll("[\\t\\n\\r]", ""));
        String s2 = "1\n\r\t" +
                "2";
        System.out.println(s2);
        // \t为制表符 \n为换行 \r为回车
        System.out.println(s2.replaceAll("[\\t\\n\\r]", ""));*/

        // List<User> users = new ArrayList<User>();
        // users.add(new User("liu1",21));
        // users.add(new User("liu2",22));
        // users.add(new User("liu2",23));
        // users.add(new User("liu4",24));

        // 错误1 ConcurrentModificationException
        // Iterator<User> iterator = users.iterator();
        // while(iterator.hasNext()) {
        //     User user = iterator.next();
        //     if(user.getName().equals("liu2")) {
        //         users.remove(user);
        //     }
        //     System.out.println(user);
        // }

        // 错误2 ConcurrentModificationException
        // for (User user : users) {
        //     if(user.getName().equals("liu2")) {
        //         users.remove(user);
        //     }
        //     System.out.println(user);
        // }

        // 错误3 不抛异常，但可能会漏删
        // for (int i = 0; i < users.size(); i++) {
        //     if(users.get(i).getName().equals("liu2")){
        //         users.remove(i);
        //     }
        // }
        // for (User user : users) {
        //     System.out.println(user.getName());
        //     System.out.println(user.getAge());
        // }

        // List<User> users2 = new ArrayList<User>();
        // users2.add(new User("liu1",24));
        // users2.add(new User("liu2",24));
        // users2.add(new User("liu3",24));
        // users2.add(new User("liu4",24));
        //
        // Iterator<User> iterator = users2.iterator();
        // while (iterator.hasNext()) {
        //     User user = iterator.next();
        //     // 还有一种更坑的场景，当删除集合的倒数第二个元素时，程序不会抛出任何异常，
        //     // 只是结果与预期的不相符，如果在应用过程中不认真观察，很难发现该错误！
        //     // 遍历过程删除了倒数第二个元素，那么最后一个元素就永远遍历不到了
        //     if (user.getName().equals("liu3")) {
        //         users2.remove(user);
        //     }
        //     System.out.println(user);
        // }
        // System.out.println(users2);


        // 使用地带其的remove
        List<String> list = new ArrayList<>();
        list.add("aa");
        list.add("bb");
        list.add("cc");
        Iterator<String> it = list.iterator();
        while(it.hasNext()){
            String str = (String)it.next();
            if("aa".equals(str)){
                it.remove();
            }
            if("bb".equals(str)){
                it.remove();
            }
        }
        System.out.println(list);
    }

    public static class User{

        private String name;
        private int age;

        public User(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public User() {
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        @Override
        public String toString() {
            return "User{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }
    }
}
