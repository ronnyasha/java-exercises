package org.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class  Main {
    public static void main(String[] args) {
        Person original = new Person("Шевченко", "Тарас", 77);

        Gson gson = new GsonBuilder()
                .serializeNulls()
                .create();

        String json = gson.toJson(original);
        Person restored = gson.fromJson(json, Person.class);

        System.out.println("Original: " + original);
        System.out.println("JSON:     " + json);
        System.out.println("Restored: " + restored);
        System.out.println("Equal?    " + original.equals(restored));
    }
}
