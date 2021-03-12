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

    public HabitDisplay() {
        months = new ArrayList<>();
    }
}
