package berlin.yuna.configmetadata.model;

import java.util.Objects;

public class Values {

    private String value;
    private String description;

    public String getValue() {
        return value;
    }

    public Values setValue(String value) {
        this.value = value;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Values description(String description) {
        this.description = description;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Values values = (Values) o;
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
