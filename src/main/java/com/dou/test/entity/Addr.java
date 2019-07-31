package com.dou.test.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author dsp
 * @date 2019-07-09
 */
@Data
@Document(collection = "addr")
public class Addr {
    @Id
    private String id;
    private String area;
    private Checker checker;
}
