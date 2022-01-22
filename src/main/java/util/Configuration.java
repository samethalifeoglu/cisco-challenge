package util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Configuration {
    Properties properties;

    public Properties getProperties() {
        properties = new Properties();
        FileInputStream configFile = null;
        try {
            configFile = new FileInputStream(Constants.CONFIG_FILE_PATH);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            properties.load(configFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }

    public String getEmail() {
        return getProperties().getProperty("user-email");
    }

    public String getPass() {
        return getProperties().getProperty("user-pass");
    }
}
