package model.humans;

import java.util.Objects;

public abstract class Human {
    private final String name;

    protected Human(String name) {
        this.name = Objects.requireNonNull(name);
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" + name + ")";
    }
}
