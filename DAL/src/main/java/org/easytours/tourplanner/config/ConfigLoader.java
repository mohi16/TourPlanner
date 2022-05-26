package org.easytours.tourplanner.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

public class ConfigLoader {
    private static final String PATH = "./appconfig.yaml";

    private static AppConfig config;

    public static void load() throws IOException {
        File file = new File(PATH);
        if (!file.exists()) {
            if (!file.createNewFile())
            {
                System.out.println("file already exists XD");
            }
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);

            bw.write("lang: de\n");
            bw.write("api: http://localhost:5000\n");

            bw.close();
        }

        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());

        config = mapper.readValue(file, AppConfig.class);
    }

    public static void save() {
        // save config
    }

    public static AppConfig getConfig() {
        return config;
    }
}
