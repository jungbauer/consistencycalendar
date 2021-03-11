package com.jungbauer.consistencycalendar

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification


@SpringBootTest
class ApplicationTests extends Specification {

    @Autowired (required = false)
    private ApiController apiController

    def "when context is loaded then all expected beans are created"() {
        expect: "the WebController is created"
        apiController
    }
}