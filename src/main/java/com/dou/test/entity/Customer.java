package com.dou.test.entity;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * @author dsp
 * @date 2019-07-06
 */
@Document(collection = "customer")
@Data
public class Customer {

    @Id
    private String id;
    private String name;
    private String birthday;
    private String mobile;
    private Integer sex;
    private Integer married;
    @Field("company_id")
    private String companyId;
    @Field("company_name")
    private String companyName;
    private Cart cart;

}
