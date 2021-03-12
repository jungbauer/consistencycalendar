package com.jungbauer.consistencycalendar.service;

import com.jungbauer.consistencycalendar.database.Completion;
import com.jungbauer.consistencycalendar.database.Habit;
import com.jungbauer.consistencycalendar.object.HabitDisplay;
import com.jungbauer.consistencycalendar.object.Month;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DisplayService {

    public HabitDisplay createHabitDisplay(Habit habit) {
        HabitDisplay hd = new HabitDisplay();

        hd.id = habit.getId();
        hd.type = habit.getType();
        hd.title = habit.getTitle();
        hd.startDate = habit.getStartDate();
        hd.endDate = habit.getEndDate();
        hd.note = habit.getNote();

        List<LocalDate> checkins = habit.getCompletions().stream().map(Completion::getDate).collect(Collectors.toList());
        hd.months.addAll(createMonths(checkins));

        return hd;
    }

    private List<Month> createMonths(List<LocalDate> checkins) {
        List<Month> months = new ArrayList<>();

        for (LocalDate checkin : checkins) {
            // if months list empty add from first checkin
            if (months.isEmpty()) {
                Month newMonth = new Month(checkin);
                months.add(newMonth);
            }
            else {
                int pos = findMonth(checkin, months);
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

        months.sort(Comparator.comparing(Month::getFirstDay));
        return months;
    }

    private int findMonth(LocalDate date, List<Month> months) {
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

    private boolean monthCheck(Month item, LocalDate date) {
        LocalDate checkDate = LocalDate.of(date.getYear(), date.getMonth(), 1);
        return item.firstDay.isEqual(checkDate);
    }
}
