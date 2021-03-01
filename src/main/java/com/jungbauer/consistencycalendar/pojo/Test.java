package com.jungbauer.consistencycalendar.pojo;

import java.util.ArrayList;
import java.util.List;

public class Test {
    public String type;
    public String title;
    public String startDate;
    public String endDate;
    public String note;
    public List<String> yesList;

    public Test() {
        yesList = new ArrayList<>();
    }
}
