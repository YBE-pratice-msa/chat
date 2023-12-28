package com.example.mongo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class MongoDBTestService {
    private final MongoDBTestRepository mongoDBTestRepository;

    public String selectUser(String name) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            Object o = mongoDBTestRepository.findByName(name);

            if (mongoDBTestRepository.findByName(name) == null) {
                log.info("[Service] user name : {} not exist!!", name);
                return String.format("%s not exist", name);
            } else {
                return objectMapper.writeValueAsString(o);
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "error";
        }
    }

    public void saveUser(String name, int age) {
        MongoDBTestModel model = MongoDBTestModel.builder()
                .name(name).age(age).now(LocalDateTime.now().toString())
                .build();
        if (mongoDBTestRepository.findByName(name) != null) {
            log.info("[Service] user name : {} is already exist!", name);
        } else {
            mongoDBTestRepository.save(model);
        }
    }
}
