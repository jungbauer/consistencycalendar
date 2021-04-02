package com.jungbauer.consistencycalendar;

import com.jungbauer.consistencycalendar.database.Completion;
import com.jungbauer.consistencycalendar.database.CompletionRepository;
import com.jungbauer.consistencycalendar.database.Habit;
import com.jungbauer.consistencycalendar.database.HabitRepository;
import com.jungbauer.consistencycalendar.database.User;
import com.jungbauer.consistencycalendar.database.UserRepository;
import com.jungbauer.consistencycalendar.object.Test;
import com.jungbauer.consistencycalendar.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api")
public class ApiController {

    private final TestService testService;
    private final UserRepository userRepository;
    private final HabitRepository habitRepository;
    private final CompletionRepository completionRepository;

    @Autowired
    public ApiController(TestService testService, UserRepository userRepository, HabitRepository habitRepository,
                         CompletionRepository completionRepository){
        this.testService = testService;
        this.userRepository = userRepository;
        this.habitRepository = habitRepository;
        this.completionRepository = completionRepository;
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

    @GetMapping(value="/todaycomp")
    public String todayCompletion(Integer habitId) {
        Optional<Habit> habit = habitRepository.findById(habitId);

        if (habit.isPresent()) {
            LocalDate now = LocalDate.now();
            String returnDate = now.toString();

            if (!completionRepository.existsByHabitAndDate(habit.get(), now)) {
                Completion compToday = new Completion();
                compToday.setDate(now);
                compToday.setHabit(habit.get());
                returnDate = completionRepository.save(compToday).getDate().toString();
            }

            return returnDate;
        }
        else {
            return "Completion error";
        }
    }
}
