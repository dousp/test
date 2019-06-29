package com.dou.test.entity.xml;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Getter
@Setter
@XmlRootElement(name = "CheckItem")
@XmlAccessorType(XmlAccessType.NONE)
public class ApplyCheckItem {

    /**
     *身份证号码，没有传空
     */
    @XmlElement(name = "CheckName")
    private String checkName;

    /**
     *指标ID ，默认1001
     */
    @XmlElement(name = "CheckValue")
    private String checkValue;


}