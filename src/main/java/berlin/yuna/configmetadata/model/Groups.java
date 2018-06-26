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

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setType(Class type) {
        this.type = type.getTypeName();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSourceType() {
        return sourceType;
    }

    public void setSourceType(String sourceType) {
        this.sourceType = sourceType;
    }

    public void setSourceType(Class sourceType) {
        this.sourceType = sourceType.getTypeName();
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
