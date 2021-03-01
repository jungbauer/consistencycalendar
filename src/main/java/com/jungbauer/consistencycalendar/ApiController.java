package com.jungbauer.consistencycalendar;

import com.google.gson.Gson;
import com.jungbauer.consistencycalendar.pojo.Test;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;

@RestController
@RequestMapping("api")
public class ApiController {

    @GetMapping("/test")
    public Test test() {
        Gson gson = new Gson();
        Test test = new Test();

        try {
            Reader reader = Files.newBufferedReader(Paths.get("testData/test.json"));
            test = gson.fromJson(reader,Test.class);
            reader.close();
        } catch (Exception e) {
            // dump it
            e.printStackTrace();
        }
        
        return test;
    }
}
