package com.jungbauer.consistencycalendar.service;

import com.google.gson.*;
import com.jungbauer.consistencycalendar.pojo.Test;
import org.springframework.stereotype.Service;

import java.io.Reader;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Service
public class TestService {

    private Gson gson;

    public TestService() {
        this.gson = new GsonBuilder()
//                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                .registerTypeAdapter(LocalDate.class, new LocalDateDeserializer())
                .setPrettyPrinting()
                .create();
    }

    public Test readTestJson() {
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

// currently unsure about the serializer code
//class LocalDateAdapter implements JsonSerializer<LocalDate> {
//
//    public JsonElement serialize(LocalDate date, Type typeOfSrc, JsonSerializationContext context) {
//        return new JsonPrimitive(date.format(DateTimeFormatter.ISO_LOCAL_DATE)); // "yyyy-mm-dd"
//    }
//}

class LocalDateDeserializer implements JsonDeserializer < LocalDate > {
    @Override
    public LocalDate deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {
        return LocalDate.parse(json.getAsString());
    }
}
