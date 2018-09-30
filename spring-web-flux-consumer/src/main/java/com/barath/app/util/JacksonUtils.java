package com.barath.app.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Objects;


public class JacksonUtils {

    private static final ObjectMapper mapper = new ObjectMapper();


    public static String toJson(Object inputObj)  {

        try {
            return mapper.writeValueAsString(inputObj);
        } catch (JsonProcessingException e) {

            e.printStackTrace();
        }
        return Objects.toString(inputObj);
    }

    public static Object fromJson( String json, Class<?> clazz)  {

        try {
            return mapper.readValue(json, clazz);
        } catch (IOException e) {

            e.printStackTrace();
        }
        return null;
    }

}