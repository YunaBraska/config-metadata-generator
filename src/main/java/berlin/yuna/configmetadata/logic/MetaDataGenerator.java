package berlin.yuna.configmetadata.logic;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import static java.nio.charset.StandardCharsets.UTF_8;
import static java.util.Objects.requireNonNull;

public abstract class MetaDataGenerator {

    public final static String GENERAL_META_DATA_PATH = getGeneralMetaDataPath();
    public final static String TYPE_CONFIG_META_DATA = "META-INF/spring-configuration-metadata.json";
    public final static String TYPE_AUTO_CONFIG = "META-INF/spring.factories";

    /**
     * Generates metadata
     *
     * @param content      file content to write
     * @param metaDataType type of meta data
     * @return written metadata path
     * @throws IOException when the file could not be written
     */
    public Path write(final String metaDataType, final String content) throws IOException {
        return write(Paths.get(GENERAL_META_DATA_PATH, metaDataType), content);
    }

    /**
     * Generates metadata in unix format
     *
     * @param content    file content to write
     * @param outputPath custom path to write meta data file
     * @return written metadata path
     * @throws IOException when the file could not be written
     */
    public Path write(final Path outputPath, final String content) throws IOException {
        outputPath.toFile().getParentFile().mkdirs();
        outputPath.toFile().delete();
        Files.write(outputPath, content.getBytes(UTF_8), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        return outputPath;
    }

    private static String getGeneralMetaDataPath() {
        try {
            ClassLoader classLoader = MetaDataGenerator.class.getClassLoader();
            String resPath = Paths.get(requireNonNull(classLoader.getResource("")).toURI()).toString();
            resPath = resPath.replace("target/classes", "src/main/resources");
            resPath = resPath.replace("target/test-classes", "src/main/resources");
            return resPath;
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
}
