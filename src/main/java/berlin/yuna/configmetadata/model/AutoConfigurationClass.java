package berlin.yuna.configmetadata.model;

import berlin.yuna.configmetadata.logic.MetaDataGenerator;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static java.util.Arrays.asList;

@SuppressWarnings({"UnusedReturnValue", "unused"})
public class AutoConfigurationClass extends MetaDataGenerator {

    private final List<Class<?>> autoConfigClasses = new ArrayList<>();

    public AutoConfigurationClass() {
    }

    public AutoConfigurationClass(final Class<?>... autoConfigClasses) {
        this.autoConfigClasses.addAll(new ArrayList<>(asList(autoConfigClasses)));
    }

    public List<Class<?>> getAutoConfigClasses() {
        return autoConfigClasses;
    }

    public AutoConfigurationClass newAutoConfigClass(final Class<?>... autoConfigClasses) {
        this.autoConfigClasses.addAll(new ArrayList<>(asList(autoConfigClasses)));
        return this;
    }

    public Path generate() throws IOException {
        final StringBuilder content = new StringBuilder();
        content.append("org.springframework.boot.autoconfigure.EnableAutoConfiguration=\\\n");

        for (Class<?> clazz : getAutoConfigClasses()) {
            content.append(clazz.getTypeName()).append("\n");
        }
        return write(TYPE_AUTO_CONFIG, content.toString());
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final AutoConfigurationClass that = (AutoConfigurationClass) o;
        return Objects.equals(autoConfigClasses, that.autoConfigClasses);
    }

    @Override
    public int hashCode() {

        return Objects.hash(autoConfigClasses);
    }

    @Override
    public String toString() {
        return "AutoConfigurationClass{" +
                "autoConfigClasses=" + autoConfigClasses +
                '}';
    }
}
