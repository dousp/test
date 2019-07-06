package com.dou.test.entity.xml;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.*;

/**
 * @author dsp
 * @create 2019-06-21
 * @Description todo
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "request")
@JacksonXmlRootElement(localName = "ticket")
public class TicketRequest {

    @JacksonXmlProperty(localName = "name")
    @XmlElement(name = "name")
    private String name;

    @JacksonXmlProperty(localName = "price")
    @XmlElement(name = "price")
    private int price;

}
