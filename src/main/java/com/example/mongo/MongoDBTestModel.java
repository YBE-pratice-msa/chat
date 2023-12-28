package com.example.mongo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Document(collation = "user")
@AllArgsConstructor
public class MongoDBTestModel {

    private String name;
    private int age;
}
