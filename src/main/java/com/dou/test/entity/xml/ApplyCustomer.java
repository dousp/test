package com.dou.test.entity.xml;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.*;
import java.util.List;

/**
 *
 */
@Getter
@Setter
@XmlRootElement(name = "Customer")
@XmlAccessorType(XmlAccessType.NONE)
public class ApplyCustomer {

    @XmlElement(name = "Name")
    private String name = "";

    @XmlElement(name = "CrdId")
    private String cardId;

    @XmlElementWrapper(name = "CheckItems")
    @XmlElement(name = "CheckItem")
    private List<ApplyCheckItem> checkItems;

}
