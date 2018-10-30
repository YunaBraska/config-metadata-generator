package berlin.yuna.configmetadata.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Hints {

    private String name;
    private List<Values> values = new ArrayList<>();

    public String name() {
        return name;
    }

    public Hints name(final String name) {
        this.name = name;
        return this;
    }

    public List<Values> values() {
        return values;
    }

    public Hints values(final List<Values> values) {
        this.values = values;
        return this;
    }

    public Values newValues() {
        Values values = new Values();
        values().add(values);
        return values;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Hints hints = (Hints) o;
        return Objects.equals(name, hints.name) &&
                Objects.equals(values, hints.values);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, values);
    }

    @Override
    public String toString() {
        return "Hints{" +
                "name='" + name + '\'' +
                ", values=" + values +
                '}';
    }
}
