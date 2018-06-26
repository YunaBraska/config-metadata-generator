package berlin.yuna.configmetadata.model;

import java.util.Objects;

public class Properties {

    private String name;
    private String type;
    private String description;
    private String sourceType;
    private Object defaultValue;
    private Deprecation deprecation;

    public Properties() {
        
    }

    public Properties(String name, String description, Class type) {
        this(name, description, type, Properties.class);
    }

    public Properties(String name, String description, Class type, Class sourceType) {
        name(name);
        description(description);
        type(type);
        sourceType(sourceType);
    }

    public String getName() {
        return name;
    }

    public Properties name(String name) {
        return name("${default.group}", name);
    }

    public Properties name(String group, String name) {
        this.name = group + "." + name;
        return this;
    }

    public String getType() {
        return type;
    }

    public Properties type(String type) {
        this.type = type;
        return this;
    }

    public Properties type(Class type) {
        this.type = type.getTypeName();
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Properties description(String description) {
        this.description = description;
        return this;
    }

    public String getSourceType() {
        return sourceType;
    }

    public Properties sourceType(String sourceType) {
        this.sourceType = sourceType;
        return this;
    }

    public Properties sourceType(Class sourceType) {
        this.sourceType = sourceType.getTypeName();
        return this;
    }

    public Object getDefaultValue() {
        return defaultValue;
    }

    public Properties defaultValue(Object defaultValue) {
        this.defaultValue = defaultValue;
        return this;
    }

    public Deprecation getDeprecation() {
        return deprecation;
    }

    public Properties deprecation(Deprecation deprication) {
        this.deprecation = deprication;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Properties that = (Properties) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(type, that.type) &&
                Objects.equals(description, that.description) &&
                Objects.equals(sourceType, that.sourceType) &&
                Objects.equals(defaultValue, that.defaultValue) &&
                Objects.equals(deprecation, that.deprecation);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, type, description, sourceType, defaultValue, deprecation);
    }

    @Override
    public String toString() {
        return "Hints{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", description='" + description + '\'' +
                ", sourceType='" + sourceType + '\'' +
                ", defaultValue=" + defaultValue +
                ", deprecation=" + deprecation +
                '}';
    }
}
