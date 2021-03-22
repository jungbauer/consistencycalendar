package com.jungbauer.consistencycalendar

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
}
