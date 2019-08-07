package com.dou.test.controller;

import com.dou.test.cxf.client.LisWsClient;
import com.dou.test.entity.xml.TicketRequest;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;

@RestController
@RequestMapping("/xml")
public class XmlController {

    @Resource
    LisWsClient lisWsClient;

    @GetMapping("/{name}")
    public String hello(@PathVariable("name") String name) {
        System.out.println("hello");
        return name;
    }

    // @PostMapping(value = "/test/xml", consumes = { MediaType.APPLICATION_XML_VALUE }, produces = MediaType.APPLICATION_XML_VALUE)
    @PostMapping(value = "/test/xml",  produces = MediaType.APPLICATION_XML_VALUE)
    public Object test(@RequestBody TicketRequest ticketRequest) {
        ticketRequest = new TicketRequest("dd",ticketRequest.getPrice()+3);
        return ticketRequest;
    }

    // @PostMapping(value = "/test/json", consumes = { MediaType.APPLICATION_JSON_UTF8_VALUE }, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @PostMapping(value = "/test/json",  produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Object testJson(@RequestBody TicketRequest ticketRequest) {
        ticketRequest = new TicketRequest("dd",ticketRequest.getPrice()+3);
        return ticketRequest;
    }

    @GetMapping("/cxf/test")
    public String cxfTest(){
        String _send_userName = "szkw";
        String _send_passWord = "42516543215";
        String _send_messagename = "PISLoadData";
        String _send_parameter = "<request><FilialeCode>84</FilialeCode></request>";
        Object[] objects;
        String msg = "";
        try {
            objects = lisWsClient.cxfClient().invoke("Send",_send_userName,_send_passWord,_send_messagename,_send_parameter);
            msg = (String) objects[0];
        } catch (Exception e) {
            e.printStackTrace();
        }
        return msg;
    }

}
