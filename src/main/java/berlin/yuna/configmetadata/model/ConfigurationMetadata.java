package berlin.yuna.configmetadata.model;

import berlin.yuna.configmetadata.logic.MetaDataGenerator;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Objects;

/**
 * ConfigurationMetadata
 * <p>
 * generates META-INF/spring-configuration-metadata.json
 *
 * @see <a href="https://docs.spring.io/spring-boot/docs/current/reference/html/configuration-metadata.html">Spring boot configuration-metadata</a>
 */
public class ConfigurationMetadata extends MetaDataGenerator {
    private ArrayList<Hints> hints = new ArrayList<>();
    private ArrayList<Groups> groups = new ArrayList<>();
    private ArrayList<Properties> properties = new ArrayList<>();

    public ConfigurationMetadata() {

    }

    public ConfigurationMetadata(final String group, final Class sourceType) {
        Groups groups = new Groups();
        groups.name(group);
        groups.type(sourceType);
        groups.sourceType(sourceType);
        groups().add(groups);
    }

    /**
     * Generates metadata
     *
     * @return written metadata path
     * @throws IOException when the file could not be written
     */
    public Path generate() throws IOException {
        return write(TYPE_CONFIG_META_DATA, buildJson());
    }

    /**
     * Generates metadata
     *
     * @param outputPath custom path to write meta data file
     * @return written metadata path
     * @throws IOException when the file could not be written
     */
    public Path generate(final Path outputPath) throws IOException {
        return write(outputPath, buildJson());
    }

    public ArrayList<Hints> hints() {
        return hints;
    }

    public void hints(final ArrayList<Hints> hints) {
        this.hints = hints;
    }

    public Hints newHints() {
        Hints hints = new Hints();
        hints().add(hints);
        return hints;
    }

    public ArrayList<Groups> groups() {
        return groups;
    }

    public void groups(final ArrayList<Groups> groups) {
        this.groups = groups;
    }

    public Groups newGroups() {
        Groups groups = new Groups();
        groups().add(groups);
        return groups;
    }

    public Groups newGroups(final String group, final Class sourceType) {
        Groups groups = new Groups();
        groups.name(group);
        groups.type(sourceType);
        groups.sourceType(sourceType);
        groups().add(groups);
        return groups;
    }

    public ArrayList<Properties> properties() {
        return properties;
    }

    public void properties(final ArrayList<Properties> properties) {
        this.properties = properties;
    }

    public Properties newProperties() {
        Properties properties = new Properties();
        properties().add(properties);
        return properties.sourceType(Properties.class);
    }

    private String buildJson() throws JsonProcessingException {
        Groups groups = groups().get(0);

        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        String json = mapper.writeValueAsString(this);

        json = json.replace(Properties.class.getTypeName(), groups.sourceType());
        json = json.replace("${default.group}", groups.name());
        return json;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ConfigurationMetadata that = (ConfigurationMetadata) o;
        return Objects.equals(hints, that.hints) &&
                Objects.equals(groups, that.groups) &&
                Objects.equals(properties, that.properties);
    }

    @Override
    public int hashCode() {

        return Objects.hash(hints, groups, properties);
    }

    @Override
    public String toString() {
        return "ConfigurationMetadata{" +
                "hints=" + hints +
                ", groups=" + groups +
                ", properties=" + properties +
                '}';
    }
}
