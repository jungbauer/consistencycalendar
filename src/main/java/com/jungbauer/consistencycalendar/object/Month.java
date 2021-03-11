package com.jungbauer.consistencycalendar.object;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Month {
    public LocalDate firstDay;
    public List<String> checkins; //todo this should be a List<LocalDate>

    public Month() {
        checkins = new ArrayList<>();
    }

    public Month(LocalDate date) {
        checkins = new ArrayList<>();
        firstDay = LocalDate.of(date.getYear(), date.getMonth(), 1);
    }

    @Override
    public String toString() {
        return "Month{" +
                "firstDay=" + firstDay +
                ", checkins=" + checkins +
                '}';
    }
}
