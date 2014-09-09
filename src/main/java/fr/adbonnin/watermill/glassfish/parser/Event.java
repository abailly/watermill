package fr.adbonnin.watermill.glassfish.parser;

import com.google.common.base.Objects;

import java.util.Arrays;
import java.util.List;

public class Event {

    private final List<String> components;

    public Event(String... components) {
        this(Arrays.asList(components));
    }


    public Event(List<String> components) {
        this.components = components;
    }

    public List<String> getComponents() {
        return components;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Event event = (Event) o;

        if (!components.equals(event.components)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return components.hashCode();
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("components", components)
                .toString();
    }
}
