package berlin.yuna.configmetadata.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Objects;

import static java.util.Objects.requireNonNull;

/**
 * ConfigurationMetadata
 * <p>
 * generates META-INF/spring-configuration-metadata.json
 *
 * @see <a href="https://docs.spring.io/spring-boot/docs/current/reference/html/configuration-metadata.html">Spring boot configuration-metadata</a>
 */
public class ConfigurationMetadata {
    private ArrayList<Hints> hints = new ArrayList<>();
    private ArrayList<Groups> groups = new ArrayList<>();
    private ArrayList<Properties> properties = new ArrayList<>();

    public ConfigurationMetadata(String group, Class sourceType) {
        Groups groups = new Groups();
        groups.setName(group);
        groups.setType(sourceType);
        groups.setSourceType(sourceType);
        getGroups().add(groups);
    }

    public ArrayList<Hints> getHints() {
        return hints;
    }

    public void setHints(ArrayList<Hints> hints) {
        this.hints = hints;
    }

    public ArrayList<Groups> getGroups() {
        return groups;
    }

    public void setGroups(ArrayList<Groups> groups) {
        this.groups = groups;
    }

    public ArrayList<Properties> getProperties() {
        return properties;
    }

    public void setProperties(ArrayList<Properties> properties) {
        this.properties = properties;
    }

    /**
     * Generates metadata
     *
     * @return written metadata path
     * @throws IOException when the file could not be written
     */
    public Path generate() throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        String resPath = requireNonNull(classLoader.getResource("")).getPath();
        resPath = resPath.replace("target/classes", "src/main/resources");
        resPath = resPath.replace("target/test-classes", "src/main/resources");

        return generate(Paths.get(resPath, "META-INF/spring-configuration-metadata.json"));
    }

    /**
     * Generates metadata
     *
     * @param outputPath custom path to write meta data file
     * @return written metadata path
     * @throws IOException when the file could not be written
     */
    public Path generate(Path outputPath) throws IOException {
        outputPath.toFile().getParentFile().mkdirs();
        Files.write(outputPath, buildJson().getBytes());
        return outputPath;
    }

    private String buildJson() throws JsonProcessingException {
        Groups groups = getGroups().get(0);

        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        String json = mapper.writeValueAsString(this);

        json = json.replace(Properties.class.getTypeName(), groups.getSourceType());
        json = json.replace("${default.group}", groups.getName());
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
