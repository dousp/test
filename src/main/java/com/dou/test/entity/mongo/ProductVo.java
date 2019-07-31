package com.dou.test.entity.mongo;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;
@Data
public class ProductVo {
    private String id;
    @Field("name")
    private String name;
    @Field("price")
    private Integer price;
    @Field("type")
    private String type;
    @Field("type_id")
    private String typeId;
    @Field("indexs")
    private List<Index> indexs;

}
