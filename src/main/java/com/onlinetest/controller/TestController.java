package com.onlinetest.controller;

import com.onlinetest.model.Test;
import com.onlinetest.repository.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tests")
public class TestController {
    @Autowired
    private TestRepository testRepo;

    @PostMapping
    public Test addTest(@RequestBody Test test) {
        return testRepo.save(test);
    }

    @GetMapping
    public List<Test> getAllTests() {
        return testRepo.findAll();
    }
}
