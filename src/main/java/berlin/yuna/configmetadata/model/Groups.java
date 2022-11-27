package berlin.yuna.configmetadata.model;

import java.util.Objects;

@SuppressWarnings({"UnusedReturnValue", "unused"})
public class Groups {

    private String name;
    private String type;
    private String description;
    private String sourceType;
    private String sourceMethod;

    public String name() {
        return name;
    }

    public Groups name(final String name) {
        this.name = name;
        return this;
    }

    public String type() {
        return type;
    }

    public Groups type(final String type) {
        this.type = type;
        return this;
    }

    public Groups type(final Class<?> type) {
        this.type = type.getTypeName();
        return this;
    }

    public String description() {
        return description;
    }

    public Groups description(final String description) {
        this.description = description;
        return this;
    }

    public String sourceType() {
        return sourceType;
    }

    public Groups sourceType(final String sourceType) {
        this.sourceType = sourceType;
        return this;
    }

    public Groups sourceType(final Class<?> sourceType) {
        this.sourceType = sourceType.getTypeName();
        return this;
    }

    public String sourceMethod() {
        return sourceMethod;
    }

    public void sourceMethod(final String sourceMethod) {
        this.sourceMethod = sourceMethod;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Groups groups = (Groups) o;
        return Objects.equals(name, groups.name) &&
                Objects.equals(type, groups.type) &&
                Objects.equals(description, groups.description) &&
                Objects.equals(sourceType, groups.sourceType) &&
                Objects.equals(sourceMethod, groups.sourceMethod);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, type, description, sourceType, sourceMethod);
    }

    @Override
    public String toString() {
        return "Groups{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", description='" + description + '\'' +
                ", sourceType='" + sourceType + '\'' +
                ", sourceMethod='" + sourceMethod + '\'' +
                '}';
    }
}
