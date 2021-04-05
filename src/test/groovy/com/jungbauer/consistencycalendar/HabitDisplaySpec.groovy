package com.jungbauer.consistencycalendar

import com.jungbauer.consistencycalendar.object.HabitDisplay
import spock.lang.Specification

import java.time.LocalDate

class HabitDisplaySpec extends Specification {

    def "recentTodayCheck()"() {
        HabitDisplay hbOld = new HabitDisplay()
        HabitDisplay hbToday = new HabitDisplay()
        HabitDisplay hbFuture = new HabitDisplay()

        hbOld.mostRecentCompletion = LocalDate.of(1985, 9, 23)
        hbToday.mostRecentCompletion = LocalDate.now()
        hbFuture.mostRecentCompletion = LocalDate.of(2120, 9, 23)

        expect:
        !hbOld.recentTodayCheck()
        hbToday.recentTodayCheck()
        hbFuture.recentTodayCheck()
    }
}
