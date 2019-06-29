package com.dou.test.entity.xml;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author dsp
 * @create 2019-06-21
 * @Description todo
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
// @XmlAccessorType(XmlAccessType.FIELD)
// @XmlRootElement(name = "Ticket")
public class TicketRequest {

    // @XmlElement(name = "name")
    private String name;

    // @XmlElement(name = "price")
    private int price;

}
