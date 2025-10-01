package org.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PersonJsonRoundTripTest {

    private final Gson gson = new GsonBuilder().serializeNulls().create();

    @Test
    void jsonRoundTrip_preservesEqualityAndHash() {
        Person p = new Person("Шевченко", "Тарас", 77);
        String json = gson.toJson(p);
        Person q = gson.fromJson(json, Person.class);

        assertEquals(p, q);
        assertEquals(p.hashCode(), q.hashCode());
    }
}
