package com.TejaCompany.handlers;

import com.google.gson.Gson;

public class JsonUtil {

    private static final Gson gson = new Gson();

    // JSON string → Object
    public static <T> T fromJson(String json, Class<T> clazz) {
        return gson.fromJson(json, clazz);
    }

    // Object → JSON string
    public static String toJson(Object obj) {
        return gson.toJson(obj);
    }
}
