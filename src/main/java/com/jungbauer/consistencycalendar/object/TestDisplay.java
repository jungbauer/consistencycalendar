package com.jungbauer.consistencycalendar.object;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TestDisplay {
    public LocalDate startDate;
    public LocalDate endDate;
    public List<Month> months;

    public TestDisplay() {
        months = new ArrayList<>();
    }
}
