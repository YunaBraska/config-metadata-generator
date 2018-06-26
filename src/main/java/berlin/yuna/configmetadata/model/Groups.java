package berlin.yuna.configmetadata.model;

import java.util.Objects;

public class Groups {

    private String name;
    private String type;
    private String description;
    private String sourceType;
    private String sourceMethod;

    public String getName() {
        return name;
    }

    public Groups name(String name) {
        this.name = name;
        return this;
    }

    public String getType() {
        return type;
    }

    public Groups type(String type) {
        this.type = type;
        return this;
    }

    public Groups type(Class type) {
        this.type = type.getTypeName();
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Groups description(String description) {
        this.description = description;
        return this;
    }

    public String getSourceType() {
        return sourceType;
    }

    public Groups sourceType(String sourceType) {
        this.sourceType = sourceType;
        return this;
    }

    public Groups sourceType(Class sourceType) {
        this.sourceType = sourceType.getTypeName();
        return this;
    }

    public String getSourceMethod() {
        return sourceMethod;
    }

    public void setSourceMethod(String sourceMethod) {
        this.sourceMethod = sourceMethod;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Groups groups = (Groups) o;
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
