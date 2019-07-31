package com.dou.test.entity.mongo;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "index")
@Data
public class Index {
    @Id
    private String id;
    @Field("code")
    private String code;
    @Field("name")
    private String name;
}
