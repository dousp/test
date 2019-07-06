package com.dou.test.controller;

import com.dou.test.entity.xml.TicketRequest;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/xml")
public class XmlController {


    @GetMapping("/{name}")
    public String hello(@PathVariable("name") String name) {
        System.out.println("hello");
        return name;
    }

    @PostMapping(value = "/test/xml", consumes = { MediaType.APPLICATION_XML_VALUE }, produces = MediaType.APPLICATION_XML_VALUE)
    public Object test(@RequestBody TicketRequest ticketRequest) {
        ticketRequest = new TicketRequest("dd",ticketRequest.getPrice()+3);
        return ticketRequest;
    }

    @PostMapping(value = "/test/json", consumes = { MediaType.APPLICATION_JSON_UTF8_VALUE }, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Object testJson(@RequestBody TicketRequest ticketRequest) {
        ticketRequest = new TicketRequest("dd",ticketRequest.getPrice()+3);
        return ticketRequest;
    }

}
