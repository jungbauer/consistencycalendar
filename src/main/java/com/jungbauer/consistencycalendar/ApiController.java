package com.jungbauer.consistencycalendar;

import com.jungbauer.consistencycalendar.pojo.Test;
import com.jungbauer.consistencycalendar.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
public class ApiController {

    private final TestService testService;

    @Autowired
    public ApiController(TestService testService){
        this.testService = testService;
    }

    @GetMapping("/test")
    public Test test() {
        return testService.readTestJson();
    }
}
