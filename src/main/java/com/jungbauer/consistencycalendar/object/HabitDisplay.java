package com.jungbauer.consistencycalendar.object;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class HabitDisplay {
    public Integer id;
    public String type;
    public String title;
    public LocalDate startDate;
    public LocalDate endDate;
    public String note;
    public List<Month> months;
    public LocalDate mostRecentCompletion;

    public HabitDisplay() {
        months = new ArrayList<>();
    }

    public boolean recentTodayCheck() {
        if (LocalDate.now().isEqual(mostRecentCompletion)) return true;
        return LocalDate.now().isBefore(mostRecentCompletion);
    }
}
