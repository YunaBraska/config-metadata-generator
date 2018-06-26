package berlin.yuna.configmetadata.model;

import java.util.Objects;

public class Values {

    private String value;
    private String description;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
