package com.jungbauer.consistencycalendar.pojo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Test {
    public String type;
    public String title;
    public LocalDate startDate;
    public LocalDate endDate;
    public String note;
    public List<String> yesList;

    public Test() {
        yesList = new ArrayList<>();
    }
}
