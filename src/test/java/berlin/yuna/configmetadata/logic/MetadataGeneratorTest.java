package berlin.yuna.configmetadata.logic;

import berlin.yuna.configmetadata.model.AutoConfigurationClass;
import berlin.yuna.configmetadata.model.ConfigurationMetadata;
import berlin.yuna.configmetadata.model.ExampleEnumConfig;
import berlin.yuna.configmetadata.model.Groups;
import berlin.yuna.configmetadata.model.Hints;
import berlin.yuna.configmetadata.model.Properties;
import berlin.yuna.configmetadata.model.Values;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static berlin.yuna.configmetadata.logic.MetaDataGenerator.TYPE_AUTO_CONFIG;
import static berlin.yuna.configmetadata.logic.MetaDataGenerator.TYPE_CONFIG_META_DATA;
import static berlin.yuna.configmetadata.logic.MetaDataGenerator.GENERAL_META_DATA_PATH;
import static java.nio.file.Files.readAllBytes;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class MetadataGeneratorTest {

    @After
    public void tearDown() throws Exception {
        Files.deleteIfExists(Paths.get(GENERAL_META_DATA_PATH, TYPE_CONFIG_META_DATA));
        Files.deleteIfExists(Paths.get(GENERAL_META_DATA_PATH, TYPE_AUTO_CONFIG));
    }

    @Test
    public void generateMetadataFromEnum() throws IOException {
        ConfigurationMetadata metadata = new ConfigurationMetadata("my.group", ExampleEnumConfig.class);

        for (ExampleEnumConfig c : ExampleEnumConfig.values()) {
            Class type = c.getDefaultValue().getClass();
            metadata.newProperties().name(c.name().toLowerCase()).description(c.getDescription()).type(type);
        }

        Path generated = metadata.generate();

        validateOutput(generated, "spring-configuration-metadata.json");
    }

    @Test
    public void generateAutoConfigMetadata() throws IOException {
        AutoConfigurationClass classList = new AutoConfigurationClass(Groups.class, Hints.class);
        classList.newAutoConfigClass(Values.class, Properties.class);

        Path generated = classList.generate();

        validateOutput(generated, "spring.factories");
    }

    private void validateOutput(final Path generated, final String fileToCompareWith) throws IOException {
        assertThat(generated, is(notNullValue()));
        Path original = Paths.get(getClass().getClassLoader().getResource(fileToCompareWith).getPath());
        assertThat(new String(readAllBytes(generated)), is(equalTo(new String(readAllBytes(original)))));
    }
}


