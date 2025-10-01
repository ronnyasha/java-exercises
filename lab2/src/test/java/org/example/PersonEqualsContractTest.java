package org.example;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

class PersonEqualsContractTest {
    @Test
    void equalsAndHashCode_contractIsSatisfied() {
        EqualsVerifier.forClass(Person.class)
                .usingGetClass()
                .verify();
    }
}
