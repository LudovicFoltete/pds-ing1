package org.ing1.pds;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

class PropertiesLoader {

    private static PropertiesLoader ourInstance = new PropertiesLoader();

    static PropertiesLoader getInstance() {
        return ourInstance;
    }

    private Properties loader;

    private PropertiesLoader() {
        Properties properties = new Properties();
        Path source = Paths.get("../conf/config.properties");
        try (InputStream inputStream = Files.newInputStream(source)) {
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        loader = properties;
    }

    int getPort() {
        return Integer.parseInt(loader.getProperty("port"));
    }

    int getNbConnectionDatabase() {
        return Integer.parseInt(loader.getProperty("nb_connection_database"));
    }
}
