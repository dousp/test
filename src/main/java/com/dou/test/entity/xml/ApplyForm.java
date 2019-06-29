package com.dou.test.entity.xml;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.*;
import java.util.List;

/**
 * @author dsp
 * @create 2019-06-24
 * @Description 申请单请求
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement(name = "request")
@XmlAccessorType(XmlAccessType.NONE)
public class ApplyForm {

    /**
     * 本院编码
     */
    @XmlElement(name = "FilialeCode")
    private String filialeCode;

    /**
     * 用户数据集合
     */
    @XmlElementWrapper(name = "Customers")
    @XmlElement(name = "Customer")
    private List<ApplyCustomer> customers;

}
