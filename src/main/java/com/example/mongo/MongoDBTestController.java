package com.example.mongo;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/mongo")
public class MongoDBTestController {
    private final MongoDBTestService mongoDBTestService;

    @GetMapping( "/find")
    public String findUserData(@RequestParam String name) {
        return mongoDBTestService.selectUser(name);
    }

    @GetMapping( "/save")
    public String saveUserData(
            @RequestParam String name, @RequestParam int age
    ) {
        mongoDBTestService.saveUser(name, age);
        return mongoDBTestService.selectUser(name);
    }

}
