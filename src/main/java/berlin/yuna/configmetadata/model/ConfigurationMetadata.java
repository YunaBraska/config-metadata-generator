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
import java.util.List;
import java.util.Objects;

/**
 * ConfigurationMetadata
 * <p>
 * generates META-INF/spring-configuration-metadata.json
 *
 * @see <a href="https://docs.spring.io/spring-boot/docs/current/reference/html/configuration-metadata.html">Spring boot configuration-metadata</a>
 */
@SuppressWarnings({"UnusedReturnValue", "unused"})
public class ConfigurationMetadata extends MetaDataGenerator {
    private List<Hints> hints = new ArrayList<>();
    private List<Groups> groups = new ArrayList<>();
    private List<Properties> properties = new ArrayList<>();

    public ConfigurationMetadata() {

    }

    public ConfigurationMetadata(final String group, final Class<?> sourceType) {
        final Groups result = new Groups();
        result.name(group);
        result.type(sourceType);
        result.sourceType(sourceType);
        groups().add(result);
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

    public List<Hints> hints() {
        return hints;
    }

    public void hints(final List<Hints> hints) {
        this.hints = hints;
    }

    public Hints newHints() {
        final Hints result  = new Hints();
        hints().add(result);
        return result;
    }

    public List<Groups> groups() {
        return groups;
    }

    public void groups(final List<Groups> groups) {
        this.groups = groups;
    }

    public Groups newGroups() {
        final Groups result = new Groups();
        groups().add(result);
        return result;
    }

    public Groups newGroups(final String group, final Class<?> sourceType) {
        final Groups result = new Groups();
        result.name(group);
        result.type(sourceType);
        result.sourceType(sourceType);
        groups().add(result);
        return result;
    }

    public List<Properties> properties() {
        return properties;
    }

    public void properties(final List<Properties> properties) {
        this.properties = properties;
    }

    public Properties newProperties() {
        final Properties result = new Properties();
        properties().add(result);
        return result.sourceType(Properties.class);
    }

    private String buildJson() throws JsonProcessingException {
        final Groups result = groups().get(0);

        final ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        String json = mapper.writeValueAsString(this);

        json = json.replace(Properties.class.getTypeName(), result.sourceType());
        json = json.replace("${default.group}", result.name());
        return json;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final ConfigurationMetadata that = (ConfigurationMetadata) o;
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
