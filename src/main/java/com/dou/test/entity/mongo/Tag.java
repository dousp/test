package com.dou.test.entity.mongo;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Document(collection = "tag")
@Data
public class Tag {
    @Id
    private String id;
    @Field("type")
    private String type;
    @Field("type_id")
    private String typeId;
    @Field("indexs")
    private List<Index> indexs;

}
