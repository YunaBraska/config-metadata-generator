package berlin.yuna.configmetadata.model;

import java.util.Objects;

public class Values {

    private String value;
    private String description;

    public String value() {
        return value;
    }

    public Values value(final String value) {
        this.value = value;
        return this;
    }

    public String description() {
        return description;
    }

    public Values description(final String description) {
        this.description = description;
        return this;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Values values = (Values) o;
        return Objects.equals(value, values.value) &&
                Objects.equals(description, values.description);
    }

    @Override
    public int hashCode() {

        return Objects.hash(value, description);
    }

    @Override
    public String toString() {
        return "Values{" +
                "value='" + value + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
