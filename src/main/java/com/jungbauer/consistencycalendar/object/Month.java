package com.jungbauer.consistencycalendar.object;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Month {
    public LocalDate firstDay;
    public List<LocalDate> completions;

    public Month() {
        completions = new ArrayList<>();
    }

    public Month(LocalDate date) {
        completions = new ArrayList<>();
        completions.add(date);
        firstDay = LocalDate.of(date.getYear(), date.getMonth(), 1);
    }

    public LocalDate getFirstDay() {
        return firstDay;
    }

    @Override
    public String toString() {
        return "Month{" +
                "firstDay=" + firstDay +
                ", completions=" + completions +
                '}';
    }
}
