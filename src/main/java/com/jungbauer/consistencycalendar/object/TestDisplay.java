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

    public void addCheckin(LocalDate checkin) {
        // if months list empty add from first checkin
        if (months.isEmpty()) {
            Month newMonth = new Month(checkin);
            newMonth.checkins.add(checkin);
            months.add(newMonth);
        }
        else {
            int pos = findMonth(checkin);
            // if doesn't contain month add directly
            if (pos == -1) {
                Month newMonth = new Month(checkin);
                newMonth.checkins.add(checkin);
                months.add(newMonth);
            }
            else {
                // add checkin to the correct month
                months.get(pos).checkins.add(checkin);
            }
        }
    }

    private boolean containsMonth(LocalDate date) {
        return findMonth(date) != -1;
    }

    private boolean monthCheck(Month item, LocalDate date) {
        LocalDate checkDate = LocalDate.of(date.getYear(), date.getMonth(), 1);
        return item.firstDay.isEqual(checkDate);
    }

    private int findMonth(LocalDate date) {
        int i = 0;
        int pos = -1;
        boolean found = false;
        while(i < months.size() && !found){
            found = monthCheck(months.get(i), date);
            if (found) pos = i;
            i++;
        }
        return pos;
    }
}
