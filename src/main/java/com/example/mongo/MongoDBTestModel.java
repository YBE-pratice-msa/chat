package com.example.mongo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Document(collection = "user")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MongoDBTestModel {
    @Id
    private String name;
    private int age;
}
