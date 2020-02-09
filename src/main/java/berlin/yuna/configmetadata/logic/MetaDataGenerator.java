package berlin.yuna.configmetadata.logic;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import static java.nio.charset.StandardCharsets.UTF_8;

public abstract class MetaDataGenerator {

    public final static Path GENERAL_META_DATA_PATH = getGeneralMetaDataPath();
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
        return write(Paths.get(GENERAL_META_DATA_PATH.toString(), metaDataType), content);
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
        Files.createDirectories(outputPath.getParent());
        Files.deleteIfExists(outputPath);
        Files.writeString(outputPath, content);
        return outputPath;
    }

    private static Path getGeneralMetaDataPath() {
        return Paths.get(System.getProperty("user.dir"), "src/main/resources");
    }
}
