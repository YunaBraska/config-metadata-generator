package berlin.yuna.configmetadata.logic;

import berlin.yuna.system.logic.SystemUtil;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.*;

import static java.nio.charset.StandardCharsets.UTF_8;
import static java.util.Objects.requireNonNull;

public abstract class MetaDataGenerator {

    public final static String GENERAL_META_DATA_PATH = SystemUtil.getMainResource(MetaDataGenerator.class).toString();
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
        Files.write(outputPath, content.getBytes(UTF_8), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        return outputPath;
    }
}
