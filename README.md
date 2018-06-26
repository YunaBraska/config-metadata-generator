# Config-Metadata-Generator

[![License][License-Image]][License-Url]
[![Build][Build-Status-Image]][Build-Status-Url] 
[![Coverage][Coverage-image]][Coverage-Url] 
[![Maintainable][Maintainable-image]][Maintainable-Url] 
[![Central][Central-image]][Central-Url] 
[![Javadoc][javadoc-image]][javadoc-Url]
[![Gitter][Gitter-image]][Gitter-Url] 

### Description
Manually way/library to generate config metadata for spring boot

### Information
 * [Spring boot configuration-metadata](https://docs.spring.io/spring-boot/docs/current/reference/html/configuration-metadata.html)

### Usage
* You could generate metadata out of a test like this
```java
public class ConfigMetadataGeneratorTest {

	//GENERATION START
    @Test
    public void generateMetadataFromEnum() throws IOException {
        ConfigurationMetadata metadata = new ConfigurationMetadata("my.group", ExampleEnumConfig.class);

        for (ExampleEnumConfig c : ExampleEnumConfig.values()) {
            Class type = c.getDefaultValue().getClass();
            Properties prop = new Properties(c.name().toLowerCase(), c.getDescription(), type);
            metadata.getProperties().add(prop);
        }

        Path generated = metadata.generate();
    }
	//GENERATION END
	
    //Test datasource - so you know where the generated Json is comming from...
    enum ExampleEnumConfig {

        //ExampleDefaultConfig
        CLUSTER_ID("test-cluster", "Cluster ID (default: test-cluster)"),
        PORT(4222, "Use port for clients (default: 8080)"),
        MAX_BYTES(0L, "Max messages total size per channel (0 for unlimited)"),
        INFO(true, "Enable info output"),
        DEBUG(false, "Debug the raw protocol");

        private final Object defaultValue;
        private final String description;

        ExampleEnumConfig(Object defaultValue, String description) {
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
}
```

* output from example:
```json
{
  "hints": [],
  "groups": [
    {
      "name": "my.group",
      "type": "berlin.yuna.configmetadata.logic.ConfigMetadataGeneratorTest$ExampleEnumConfig",
      "sourceType": "berlin.yuna.configmetadata.logic.ConfigMetadataGeneratorTest$ExampleEnumConfig"
    }
  ],
  "properties": [
    {
      "name": "my.group.cluster_id",
      "type": "java.lang.String",
      "description": "Cluster ID (default: test-cluster)",
      "sourceType": "berlin.yuna.configmetadata.logic.ConfigMetadataGeneratorTest$ExampleEnumConfig"
    },
    {
      "name": "my.group.port",
      "type": "java.lang.Integer",
      "description": "Use port for clients (default: 4222)",
      "sourceType": "berlin.yuna.configmetadata.logic.ConfigMetadataGeneratorTest$ExampleEnumConfig"
    },
    {
      "name": "my.group.max_bytes",
      "type": "java.lang.Long",
      "description": "Max messages total size per channel (0 for unlimited)",
      "sourceType": "berlin.yuna.configmetadata.logic.ConfigMetadataGeneratorTest$ExampleEnumConfig"
    },
    {
      "name": "my.group.info",
      "type": "java.lang.Boolean",
      "description": "Enable info output",
      "sourceType": "berlin.yuna.configmetadata.logic.ConfigMetadataGeneratorTest$ExampleEnumConfig"
    },
    {
      "name": "my.group.debug",
      "type": "java.lang.Boolean",
      "description": "Debug the raw protocol",
      "sourceType": "berlin.yuna.configmetadata.logic.ConfigMetadataGeneratorTest$ExampleEnumConfig"
    }
  ]
}
```

![Config-Metadata-Generator](src/main/resources/banner.png "Config-Metadata-Generator")

[License-Url]: https://www.apache.org/licenses/LICENSE-2.0
[License-Image]: https://img.shields.io/badge/License-Apache2-blue.svg
[github-release]: https://github.com/YunaBraska/config-metadata-generator
[Build-Status-Url]: https://travis-ci.org/YunaBraska/config-metadata-generator
[Build-Status-Image]: https://travis-ci.org/YunaBraska/config-metadata-generator.svg?branch=master
[Coverage-Url]: https://codecov.io/gh/YunaBraska/config-metadata-generator?branch=master
[Coverage-image]: https://codecov.io/gh/YunaBraska/config-metadata-generator/branch/master/graphs/badge.svg
[Version-url]: https://github.com/YunaBraska/config-metadata-generator
[Version-image]: https://badge.fury.io/gh/YunaBraska%2Fconfig-metadata-generator.svg
[Central-url]: https://search.maven.org/#search%7Cga%7C1%7Ca%3A%22config-metadata-generator%22
[Central-image]: https://maven-badges.herokuapp.com/maven-central/berlin.yuna/config-metadata-generator/badge.svg
[Maintainable-Url]: https://codeclimate.com/github/YunaBraska/config-metadata-generator
[Maintainable-image]: https://codeclimate.com/github/YunaBraska/config-metadata-generator.svg
[Gitter-Url]: https://gitter.im/config-metadata-generator/Lobby
[Gitter-image]: https://img.shields.io/badge/gitter-join%20chat%20%E2%86%92-brightgreen.svg
[Javadoc-url]: http://javadoc.io/doc/berlin.yuna/config-metadata-generator
[Javadoc-image]: http://javadoc.io/badge/berlin.yuna/config-metadata-generator.svg