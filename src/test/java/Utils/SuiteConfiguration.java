package Utils;

import java.io.IOException;
import java.util.Properties;

/**
 * Loads Test configuration from property file
 */
public class SuiteConfiguration {

    private static final String DEBUG_PROPERTIES = System.getProperty("user.dir")+ "//src/test/resources/application.properties";

    private Properties properties;

    public SuiteConfiguration() throws IOException {
        this(System.getProperty("application.properties", DEBUG_PROPERTIES));
    }

    public SuiteConfiguration(String fromResource) throws IOException {
        properties = new Properties();
        properties.load(SuiteConfiguration.class.getResourceAsStream(fromResource));
    }

    public String getProperty(String name) {
        return properties.getProperty(name);
    }
}
