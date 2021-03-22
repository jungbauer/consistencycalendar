package com.jungbauer.consistencycalendar

import com.jungbauer.consistencycalendar.database.Completion
import com.jungbauer.consistencycalendar.database.Habit
import com.jungbauer.consistencycalendar.object.HabitDisplay
import com.jungbauer.consistencycalendar.object.Month
import com.jungbauer.consistencycalendar.service.DisplayService
import spock.lang.Shared
import spock.lang.Specification

import java.time.LocalDate

class DisplayServiceSpec extends Specification {

    @Shared DisplayService displayService

    def setupSpec() {
        displayService = new DisplayService()
    }

    def "checkMonth()"() {
        Month month = new Month(LocalDate.of(2020, 5,1))
        LocalDate test1 = LocalDate.of(2020,5,4)
        LocalDate test2 = LocalDate.of(2020,6,4)

        expect:
        displayService.monthCheck(month, test1)
        !displayService.monthCheck(month, test2)
    }

    def "findMonth()"() {
        LocalDate test1 = LocalDate.of(2020, 3, 23)
        LocalDate test2 = LocalDate.of(2020, 4, 15)
        LocalDate test3 = LocalDate.of(2020, 7, 8)
        List<Month> months = new ArrayList<>()
        months.add(new Month(LocalDate.of(2020, 3, 1)))
        months.add(new Month(LocalDate.of(2020, 4, 1)))
        months.add(new Month(LocalDate.of(2020, 5, 1)))

        expect:
        displayService.findMonth(test1, months) == 0
        displayService.findMonth(test2, months) == 1
        displayService.findMonth(test3, months) == -1
    }

    def "createMonths()"() {
        List<String> stringDates = List.of("2021-04-04","2021-02-04","2021-02-05","2021-02-07",
                "2021-02-09","2021-02-10","2021-02-14",
                "2021-02-16","2021-02-18","2021-02-19",
                "2021-02-22","2021-03-01")
        List<LocalDate> completions = new ArrayList<>()

        for (String date : stringDates) {
            completions.add(LocalDate.parse(date))
        }

        List<Month> months = displayService.createMonths(completions)

        expect:
        stringDates.size() == 12
        completions.size() == 12
        months.size() == 3
        months.get(0).completions.size() == 10
        months.get(1).completions.size() == 1
        months.get(2).completions.size() == 1
    }

    def "createHabitDisplay()"() {
        Habit habit = new Habit()
        habit.setId(15)
        habit.setType("testType")
        habit.setTitle("Testing Stuff")
        habit.setNote("Notes go here")
        habit.setStartDate(LocalDate.of(2021,2,1))
        habit.setEndDate(LocalDate.of(2021,3,31))

        List<String> stringDates = List.of("2021-02-04","2021-02-05",
                "2021-02-07","2021-02-22","2021-03-01")
        List<Completion> completions = new ArrayList<>()

        for (String date : stringDates) {
            Completion completion = new Completion()
            completion.setDate(LocalDate.parse(date))
            completions.add(completion)
        }

        habit.setCompletions(completions)

        HabitDisplay hb = displayService.createHabitDisplay(habit)

        expect:
        hb.id == 15
        hb.type == "testType"
        hb.title == "Testing Stuff"
        hb.note == "Notes go here"
        hb.startDate.isEqual(LocalDate.of(2021,2,1))
        hb.endDate.isEqual(LocalDate.of(2021,3,31))
        hb.months.size() == 2
        hb.months[0].firstDay.isEqual(LocalDate.of(2021,2,1))
        hb.months[0].completions.size() == 4
        hb.months[1].firstDay.isEqual(LocalDate.of(2021,3,1))
        hb.months[1].completions.size() == 1
    }
}
