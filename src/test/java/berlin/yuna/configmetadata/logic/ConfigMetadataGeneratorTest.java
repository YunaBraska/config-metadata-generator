package berlin.yuna.configmetadata.logic;

import berlin.yuna.configmetadata.model.ConfigurationMetadata;
import berlin.yuna.configmetadata.model.ExampleEnumConfig;
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
            metadata.newProperties().name(c.name().toLowerCase()).description(c.getDescription()).type(type);
        }

        Path generated = metadata.generate();

        validateOutput(generated);
    }

    private void validateOutput(Path generated) throws IOException {
        assertThat(generated, is(notNullValue()));
        Path original = Paths.get(getClass().getClassLoader().getResource("spring-configuration-metadata.json").getPath());
        assertThat(new String(readAllBytes(generated)), is(equalTo(new String(readAllBytes(original)))));
    }

    private Path getMetaDataPath() {
        ClassLoader classLoader = getClass().getClassLoader();
        String resPath = requireNonNull(classLoader.getResource("")).getPath();
        resPath = resPath.replace("target/classes", "src/main/resources");
        resPath = resPath.replace("target/test-classes", "src/main/resources");
        return Paths.get(resPath, "META-INF/spring-configuration-metadata.json");
    }
}


