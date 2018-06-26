package berlin.yuna.configmetadata.logic;

import berlin.yuna.configmetadata.model.ConfigurationMetadata;
import berlin.yuna.configmetadata.model.Properties;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.nio.file.Files.readAllBytes;
import static java.util.Objects.requireNonNull;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class ConfigMetadataGeneratorTest {

    @After
    public void tearDown() throws Exception {
        Files.deleteIfExists(getMetaDataPath());
    }

    @Test
    public void generateMetadataFromEnum() throws IOException {
        ConfigurationMetadata metadata = new ConfigurationMetadata("my.group", ExampleEnumConfig.class);

        for (ExampleEnumConfig c : ExampleEnumConfig.values()) {
            Class type = c.getDefaultValue().getClass();
            Properties prop = new Properties(c.name().toLowerCase(), c.getDescription(), type);
            metadata.getProperties().add(prop);
        }

        Path generated = metadata.generate();
        assertThat(generated, is(notNullValue()));
        Path original = Paths.get(getClass().getClassLoader().getResource("spring-configuration-metadata.json").getPath());
        assertThat(new String(readAllBytes(generated)), is(equalTo(new String(readAllBytes(original)))));
    }

    enum ExampleEnumConfig {

        //ExampleDefaultConfig
        CLUSTER_ID("test-cluster", "Cluster ID (default: test-cluster)"),
        PORT(4222, "Use port for clients (default: 4222)"),
        MAX_BYTES(0L, "Max messages total size per channel (0 for unlimited)"),
        INFO(true, "Enable info output"),
        DEBUG(false, "Debug the raw protocol");

        private final Object defaultValue;
        private final String description;

        ExampleEnumConfig(Object defaultValue, String description) {
            this.defaultValue = defaultValue;
            this.description = description;
        }

        public Object getDefaultValue() {
            return defaultValue;
        }

        public String getDescription() {
            return description;
        }
    }

    private Path getMetaDataPath() {
        ClassLoader classLoader = getClass().getClassLoader();
        String resPath = requireNonNull(classLoader.getResource("")).getPath();
        resPath = resPath.replace("target/classes", "src/main/resources");
        resPath = resPath.replace("target/test-classes", "src/main/resources");
        return Paths.get(resPath, "META-INF/spring-configuration-metadata.json");
    }
}


