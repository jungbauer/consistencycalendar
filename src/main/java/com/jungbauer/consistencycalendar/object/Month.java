package com.jungbauer.consistencycalendar.object;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Month {
    public LocalDate firstDay;
    public List<String> checkins;

    public Month() {
        checkins = new ArrayList<>();
    }
}
