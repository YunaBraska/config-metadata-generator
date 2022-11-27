package berlin.yuna.configmetadata.model;

public enum ExampleEnumConfigTwo {

    CLUSTER_ID("test-cluster", "Cluster ID (default: test-cluster)"),
    PORT(4222, "Use port for clients (default: 4222)"),
    MAX_BYTES(0L, "Max messages total size per channel (0 for unlimited)"),
    INFO(true, "Enable info output"),
    DEBUG(false, "Debug the raw protocol");

    private final Object defaultValue;
    private final String description;

    ExampleEnumConfigTwo(final Object defaultValue, final String description) {
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
