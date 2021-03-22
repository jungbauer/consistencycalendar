package com.jungbauer.consistencycalendar

import com.jungbauer.consistencycalendar.object.Month
import spock.lang.Specification

import java.time.LocalDate

class MonthSpec extends Specification {

    def "default constructor"() {
        Month month = new Month()

        expect:
        month.completions.size() == 0
        month.getFirstDay() == null
    }

    def "LocalDate constructor"() {
        Month mon1 = new Month(LocalDate.of(2020, 4, 1))
        Month mon2 = new Month(LocalDate.of(2020, 8, 31))

        expect:
        mon1.completions.size() == 1
        mon2.completions.size() == 1
        mon1.getFirstDay().isEqual(LocalDate.of(2020, 4, 1))
        mon2.getFirstDay().isEqual(LocalDate.of(2020, 8, 1))
        !mon2.getFirstDay().isEqual(LocalDate.of(2020, 8, 31))
    }
}
