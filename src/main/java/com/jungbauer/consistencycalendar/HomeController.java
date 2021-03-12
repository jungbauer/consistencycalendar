package com.jungbauer.consistencycalendar;

import com.jungbauer.consistencycalendar.object.Test;
import com.jungbauer.consistencycalendar.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Controller
public class HomeController {

    private final TestService testService;
    private final UserRepository userRepository;
    private final HabitRepository habitRepository;

    @Autowired
    public HomeController(TestService testService, UserRepository userRepository,
                          HabitRepository habitRepository) {

        this.testService = testService;
        this.userRepository = userRepository;
        this.habitRepository = habitRepository;
    }

    @GetMapping("/")
    public ModelAndView index() {
        Map<String,String> map = new HashMap<>();
        map.put("name", "FRED");
        return new ModelAndView("index", map);
    }

    @GetMapping("/display")
    public ModelAndView display() {
        Map<String, Object> model = new LinkedHashMap<>();
        String title = "DISPLAY";
        model.put("title", title);

        Test test = testService.readTestJson();
        model.put("test",test);
        model.put("display", testService.testToTestDisplay(test));

        return new ModelAndView("display", model);
    }

    @GetMapping("/users")
    public ModelAndView users() {
        Map<String, Object> model = new LinkedHashMap<>();

        List<User> users = userRepository.findAll();
        model.put("users", users);

        return new ModelAndView("users", model);
    }

    @GetMapping("/habits")
    public ModelAndView habits() {
        Map<String, Object> model = new LinkedHashMap<>();

        List<Habit> habits = habitRepository.findAll();
        model.put("habits", habits);

        return new ModelAndView("habits", model);
    }
}
