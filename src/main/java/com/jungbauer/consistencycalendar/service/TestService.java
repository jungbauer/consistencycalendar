package com.jungbauer.consistencycalendar.service;

import com.google.gson.Gson;
import com.jungbauer.consistencycalendar.pojo.Test;
import org.springframework.stereotype.Service;

import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;

@Service
public class TestService {

    public Test readTestJson() {
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
