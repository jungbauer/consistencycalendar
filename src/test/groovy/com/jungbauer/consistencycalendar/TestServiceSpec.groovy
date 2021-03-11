package com.jungbauer.consistencycalendar

import com.jungbauer.consistencycalendar.object.Test
import com.jungbauer.consistencycalendar.object.TestDisplay
import com.jungbauer.consistencycalendar.service.TestService
import spock.lang.Shared
import spock.lang.Specification

import java.time.LocalDate

class TestServiceSpec extends Specification {

    @Shared TestService testService
    @Shared Test baseTest
    @Shared LocalDate startDate
    @Shared LocalDate endDate

    def setupSpec() {
        testService = new TestService()

        startDate = new LocalDate(2021, 2, 1)
        endDate = new LocalDate(2021, 4, 30)

        baseTest = new Test()
        baseTest.startDate = startDate
        baseTest.endDate = endDate
        baseTest.yesList = List.of("2021-02-04","2021-02-05","2021-02-07",
                "2021-02-09","2021-02-10","2021-02-14",
                "2021-02-16","2021-02-18","2021-02-19",
                "2021-02-22","2021-03-01")
    }

    def "Test to TestDisplay conversion"() {
        TestDisplay td = testService.testToTestDisplay(baseTest)

        expect:
        td.startDate.isEqual(startDate)
        td.endDate.isEqual(endDate)
        td.months.size() == 2
    }
}
