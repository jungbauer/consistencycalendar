package com.jungbauer.consistencycalendar.object;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Month {
    public LocalDate firstDay;
    public List<LocalDate> checkins;

    public Month() {
        checkins = new ArrayList<>();
    }

    public Month(LocalDate date) {
        checkins = new ArrayList<>();
        checkins.add(date);
        firstDay = LocalDate.of(date.getYear(), date.getMonth(), 1);
    }

    public LocalDate getFirstDay() {
        return firstDay;
    }

    @Override
    public String toString() {
        return "Month{" +
                "firstDay=" + firstDay +
                ", checkins=" + checkins +
                '}';
    }
}
