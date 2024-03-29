package berlin.yuna.configmetadata.logic;

import berlin.yuna.configmetadata.model.AutoConfigurationClass;
import berlin.yuna.configmetadata.model.ConfigurationMetadata;
import berlin.yuna.configmetadata.model.ExampleEnumConfigOne;
import berlin.yuna.configmetadata.model.ExampleEnumConfigTwo;
import berlin.yuna.configmetadata.model.Groups;
import berlin.yuna.configmetadata.model.Hints;
import berlin.yuna.configmetadata.model.Properties;
import berlin.yuna.configmetadata.model.Values;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static berlin.yuna.configmetadata.logic.MetaDataGenerator.GENERAL_META_DATA_PATH;
import static berlin.yuna.configmetadata.logic.MetaDataGenerator.TYPE_AUTO_CONFIG;
import static berlin.yuna.configmetadata.logic.MetaDataGenerator.TYPE_CONFIG_META_DATA;
import static java.nio.file.Files.readAllBytes;
import static java.util.Objects.requireNonNull;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

@Tag("UnitTest")
class MetadataGeneratorTest {

    @AfterEach
    void tearDown() throws Exception {
        Files.deleteIfExists(Paths.get(GENERAL_META_DATA_PATH.toString(), TYPE_CONFIG_META_DATA));
        Files.deleteIfExists(Paths.get(GENERAL_META_DATA_PATH.toString(), TYPE_AUTO_CONFIG));
    }

    @Test
    void generateMetadataFromEnum() throws IOException, URISyntaxException {
        final ConfigurationMetadata metadata = new ConfigurationMetadata("my.group.one", ExampleEnumConfigOne.class);

        for (ExampleEnumConfigOne c : ExampleEnumConfigOne.values()) {
            metadata.newProperties().name(c.name().toLowerCase()).description(c.getDescription()).type(c.getDefaultValue().getClass());
        }

        final Groups groups = metadata.newGroups("my.group.two", ExampleEnumConfigTwo.class);
        for (ExampleEnumConfigTwo c : ExampleEnumConfigTwo.values()) {
            metadata.newProperties().name(groups, c.name().toLowerCase()).description(c.getDescription()).type(c.getDefaultValue().getClass());
        }

        metadata.generate();
        final Path generated = metadata.generate();

        validateOutput(generated, "spring-configuration-metadata.json");
    }

    @Test
    void generateAutoConfigMetadata() throws IOException, URISyntaxException {
        final AutoConfigurationClass classList = new AutoConfigurationClass(Groups.class, Hints.class);
        classList.newAutoConfigClass(Values.class, Properties.class);

        final Path generated = classList.generate();

        validateOutput(generated, "spring.factories");
    }

    private void validateOutput(final Path generated, final String fileToCompareWith) throws IOException, URISyntaxException {
        assertThat(generated, is(notNullValue()));
        final Path original = Paths.get(requireNonNull(getClass().getClassLoader().getResource(fileToCompareWith)).toURI());
        assertThat(pathToString(generated), is(equalTo(pathToString(original))));
    }

    private String pathToString(final Path generated) throws IOException {
        return new String(readAllBytes(generated)).replaceAll("[\\r\\n]", "\n");
    }
}


