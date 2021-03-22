package com.jungbauer.consistencycalendar;

import com.jungbauer.consistencycalendar.database.Habit;
import com.jungbauer.consistencycalendar.database.HabitRepository;
import com.jungbauer.consistencycalendar.database.User;
import com.jungbauer.consistencycalendar.database.UserRepository;
import com.jungbauer.consistencycalendar.object.HabitDisplay;
import com.jungbauer.consistencycalendar.object.Test;
import com.jungbauer.consistencycalendar.service.DisplayService;
import com.jungbauer.consistencycalendar.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
public class HomeController {

    private final TestService testService;
    private final UserRepository userRepository;
    private final HabitRepository habitRepository;
    private final DisplayService displayService;

    @Autowired
    public HomeController(TestService testService, UserRepository userRepository,
                          HabitRepository habitRepository, DisplayService displayService) {

        this.testService = testService;
        this.userRepository = userRepository;
        this.habitRepository = habitRepository;
        this.displayService = displayService;
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
        List<HabitDisplay> habitDisplays = new ArrayList<>();

        for (Habit habit : habits) {
            habitDisplays.add(displayService.createHabitDisplay(habit));
        }

        model.put("habits", habitDisplays);

        return new ModelAndView("habits", model);
    }

    @GetMapping("/habit")
    public ModelAndView habit(Integer habitId) {
        Map<String, Object> model = new LinkedHashMap<>();
        Optional<Habit> habit = habitRepository.findById(habitId);

        if (habit.isPresent()) {
            model.put("habit", displayService.createHabitDisplay(habit.get()));
            return new ModelAndView("habit", model);
        }
        else {
            Map<String,String> errMap = new HashMap<>();
            errMap.put("status", "404");
            errMap.put("error", "ERROR: Can't find what you're looking for.");
            return new ModelAndView("error", errMap);
        }
    }
}
