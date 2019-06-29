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

    @PostMapping(value = "/test", consumes = { MediaType.APPLICATION_XML_VALUE }, produces = MediaType.APPLICATION_XML_VALUE)
    public Object test(@RequestBody TicketRequest ticketRequest) {
        System.out.println(ticketRequest.getName() + "==" + ticketRequest.getPrice());

        ticketRequest = new TicketRequest("dd",123);
        return ticketRequest;
    }



}
