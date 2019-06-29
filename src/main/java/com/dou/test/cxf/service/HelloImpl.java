package com.dou.test.cxf.service;

import org.springframework.stereotype.Component;

import javax.jws.WebService;

/**
 * @author dsp
 * @create 2019-06-25
 * @Description todo
 */
@Component("HelloImpl")
@WebService(
        //【对外发布的服务名 】：需要见名知意
        serviceName="helloService",
        //【名称空间】：【实现类包名的反缀】
        targetNamespace="http://hello.ws.interaction.chawran.com",
        //【服务接口全路径】  【接口的包名】
        endpointInterface = "com.dou.test.cxf.service.Hello")
public class HelloImpl implements Hello {

    @Override
    public String sayHello(String name) {
        System.out.println("=======================>" + name);
        return "hello ： " + name;
    }
}
