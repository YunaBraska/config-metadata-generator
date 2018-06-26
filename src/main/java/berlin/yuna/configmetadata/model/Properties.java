package berlin.yuna.configmetadata.model;

import java.util.Objects;

public class Properties {

    private String name;
    private String type;
    private String description;
    private String sourceType;
    private Object defaultValue;
    private Deprecation deprecation;

    public Properties(String name, String description, Class type) {
        this(name, description, type, Properties.class);
    }

    public Properties(String name, String description, Class type, Class sourceType) {
        setName(name);
        setDescription(description);
        setType(type);
        setSourceType(sourceType);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        setName("${default.group}", name);
    }

    public void setName(String group, String name) {
        this.name = group + "." + name;
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

    public Object getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(Object defaultValue) {
        this.defaultValue = defaultValue;
    }

    public Deprecation getDeprecation() {
        return deprecation;
    }

    public void setDeprecation(Deprecation deprication) {
        this.deprecation = deprication;
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
