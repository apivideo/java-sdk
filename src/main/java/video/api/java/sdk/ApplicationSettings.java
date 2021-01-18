package video.api.java.sdk;

import java.io.IOException;
import java.util.Properties;

public class ApplicationSettings {

    private static final Object PROPERTY_VERSION = "version";

    private static Properties properties;

    private static Properties getProperties() {
        if (properties == null) {
            try {
                properties = new Properties();
                properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("gradle.properties"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return properties;
    }

    public static String getVersion() {
        return (String) getProperties().get(PROPERTY_VERSION);
    }

}
