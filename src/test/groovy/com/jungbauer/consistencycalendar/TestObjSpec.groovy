package com.jungbauer.consistencycalendar

import com.jungbauer.consistencycalendar.object.Test
import spock.lang.Specification

class TestObjSpec extends Specification {

    def "Test object creation"() {
        Test test = new Test()

        expect:
        test.yesList.empty
    }
}
