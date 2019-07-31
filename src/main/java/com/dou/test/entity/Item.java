package com.dou.test.entity;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

/**
 * @author dsp
 * @date 2019-07-06
 */
@Document(collection = "item")
@Data
public class Item {

    @Id
    private ObjectId id;
    private String name;
    @Field("count")
    private Integer count;
    private Integer price;
    private List<Addr> addr;
}
