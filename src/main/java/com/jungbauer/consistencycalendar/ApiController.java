package com.jungbauer.consistencycalendar;

import com.jungbauer.consistencycalendar.object.Test;
import com.jungbauer.consistencycalendar.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("api")
public class ApiController {

    private final TestService testService;
    private final UserRepository userRepository;

    @Autowired
    public ApiController(TestService testService, UserRepository userRepository){
        this.testService = testService;
        this.userRepository = userRepository;
    }

    @GetMapping("/test")
    public Test test() {
        return testService.readTestJson();
    }

    @GetMapping("/save")
    public User saveUser() {
        User user = new User();
        user.setName("Peter");
        user.setAge(12);
        user.setActive(true);
        user.setBirthDate(LocalDate.of(1975,2,17));

        user = userRepository.save(user);

        return user;
    }
}
