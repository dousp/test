package com.dou.test.cxf.conf;

import com.dou.test.cxf.service.HelloImpl;
import org.apache.cxf.Bus;
import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.xml.ws.Endpoint;

/**
 *
 * @author dsp
 * @date 2019-06-25
 */
@Configuration
public class CxfConfig {

    @Bean(name = Bus.DEFAULT_BUS_ID)
    public SpringBus springBus() {
        return new SpringBus();
    }

    @Bean
    public Endpoint hello() {
        // 绑定要发布的服务实现类
        EndpointImpl endpoint = new EndpointImpl(springBus(), new HelloImpl());
        // 接口访问地址
        endpoint.publish("/hello");
        return endpoint;
    }
}
