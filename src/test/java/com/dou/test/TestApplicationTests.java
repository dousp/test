package com.dou.test;

import com.dou.test.entity.Addr;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class TestApplicationTests {


    @Test
    public void contextLoads() {

        List<Addr> list = null;

        list.forEach(e -> System.out.println(e.getArea()));

        // for (Addr addr : list) {
        //     System.out.println(addr.getArea());
        // }

    }

}
