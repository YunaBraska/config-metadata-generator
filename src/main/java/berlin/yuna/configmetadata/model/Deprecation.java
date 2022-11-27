package berlin.yuna.configmetadata.model;

import java.util.Objects;

@SuppressWarnings({"UnusedReturnValue", "unused"})
public class Deprecation {

    private String level;
    private String reason;
    private String replacement;

    public String level() {
        return level;
    }

    public Deprecation level(final String level) {
        this.level = level;
        return this;
    }

    public String reason() {
        return reason;
    }

    public Deprecation reason(final String reason) {
        this.reason = reason;
        return this;
    }

    public String replacement() {
        return replacement;
    }

    public Deprecation replacement(final String replacement) {
        this.replacement = replacement;
        return this;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Deprecation that = (Deprecation) o;
        return Objects.equals(level, that.level) &&
                Objects.equals(reason, that.reason) &&
                Objects.equals(replacement, that.replacement);
    }

    @Override
    public int hashCode() {

        return Objects.hash(level, reason, replacement);
    }

    @Override
    public String toString() {
        return "Deprecation{" +
                "level='" + level + '\'' +
                ", reason='" + reason + '\'' +
                ", replacement='" + replacement + '\'' +
                '}';
    }
}
