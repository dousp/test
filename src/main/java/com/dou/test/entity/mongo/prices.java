package com.dou.test.entity.mongo;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "prices")
@Data
public class prices {
    @Id
    private String id;
    @Field("price")
    private Integer price;
    @Field("status")
    private Integer status;
}
