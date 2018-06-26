package berlin.yuna.configmetadata.model;

import java.util.Objects;

public class Deprecation {

    private String level;
    private String reason;
    private String replacement;

    public String getLevel() {
        return level;
    }

    public Deprecation level(String level) {
        this.level = level;
        return this;
    }

    public String getReason() {
        return reason;
    }

    public Deprecation reason(String reason) {
        this.reason = reason;
        return this;
    }

    public String getReplacement() {
        return replacement;
    }

    public Deprecation setReplacement(String replacement) {
        this.replacement = replacement;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Deprecation that = (Deprecation) o;
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
