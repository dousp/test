package com.dou.test;

import com.dou.test.entity.Addr;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestApplicationTests {


    @Test
    public void contextLoads() {

        List<Addr> list = null;

        list.forEach(e-> System.out.println(e.getArea()));

        // for (Addr addr : list) {
        //     System.out.println(addr.getArea());
        // }

    }

}
