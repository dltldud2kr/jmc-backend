package com.example.jmcbackend;


import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class JmcBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(JmcBackendApplication.class, args);

        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject;
        String jsonTest = "{\"id\": \"1\", \"name\":\"jjang-ggae\", \"thumbnail\":\"jpg\", \"score\":\"4\", \"location\":\"chungsuro\"}";

        try {
            jsonObject = (JSONObject) jsonParser.parse(jsonTest);
        } catch(ParseException e){
            throw new RuntimeException(e);
        }

        Map<String, Object> resultMap = new HashMap<>();

        System.out.println("id값 파싱결과" + jsonObject.get("id"));
        System.out.println("id값 파싱결과" + jsonObject.get("name"));

//

    }

}
