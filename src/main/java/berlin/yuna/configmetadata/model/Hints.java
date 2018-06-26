package berlin.yuna.configmetadata.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Hints {

    private String name;
    private List<Values> values = new ArrayList<>();

    public String getName() {
        return name;
    }

    public Hints setName(String name) {
        this.name = name;
        return this;
    }

    public List<Values> getValues() {
        return values;
    }

    public Hints setValues(List<Values> values) {
        this.values = values;
        return this;
    }

    public Values newValues() {
        Values values = new Values();
        getValues().add(values);
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
