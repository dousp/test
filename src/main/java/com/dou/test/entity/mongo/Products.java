package com.dou.test.entity.mongo;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Document(collection = "products")
@Data
public class Products {
    @Id
    private String id;
    @Field("name")
    private String name;
    @Field("price")
    private Integer price;
    @Field("tags")
    private List<Tag> tags;

}
