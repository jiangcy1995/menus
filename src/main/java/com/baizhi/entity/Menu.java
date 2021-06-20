package com.baizhi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
//创建文档指定索引和类型
@Document(indexName = "menus",type = "menu")
public class Menu implements Serializable {
    @Id
    private String id;
    //设置属性对应的类型和使用的分词器
    @Field(type = FieldType.Text,analyzer = "ik_max_word")
    private String name;
    @Field(type = FieldType.Text)
    private String photo;
    @Field(type = FieldType.Date)
    private Date createDate;
    @Field(type = FieldType.Keyword)
    private String createPerson;
    //设置属性对应的类型和使用的分词器
    @Field(type = FieldType.Text,analyzer = "ik_max_word")
    private String digest;
    @Field(type = FieldType.Text)
    private String step;









}
