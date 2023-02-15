package com.humana.dhp.eventproc.service.catalogservice.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class GsonUtil {
    private static final Gson GSON = new Gson();
    private GsonUtil(){

    }
    public static <T> T convert(Object o, Class<T> clazz) {
        try {
            String json = GSON.toJson(o);
            return GSON.fromJson(json, clazz);
        } catch (Exception ex) {
            return null;
        }
    }

    public static <T> T convert(Object o, TypeToken<T> type) {
        try {
            String json = GSON.toJson(o);
            return GSON.fromJson(json, type.getType());
        } catch (Exception ex) {
            return null;
        }
    }

    public static String convertObjToString(Object o) {
        try {
            return GSON.toJson(o);
        } catch (Exception ex) {
            return null;
        }
    }
}
