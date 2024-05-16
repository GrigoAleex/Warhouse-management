package com.example.pt2024_30223_grigorescu_alexandru_assignment_3.business.routing;

import java.util.HashMap;

public class Session {
    private static final Session instance = new Session();
    private final HashMap<String, String> data = new HashMap<>();

    public static void set(String key, String value) { instance.data.put(key, value); }
    public static String get(String key) { return instance.data.get(key); }
    public static Boolean containsKey(String key) { return instance.data.containsKey(key); }
}
