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

    public AppConfig config;

    public void load() throws IOException {
        File file = new File(PATH);
        if (!file.exists()) {
            file.createNewFile();
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);

            bw.write("lang: de\n");
            bw.write("db:\n");
            bw.write("  host: localhost\n");
            bw.write("  user: user\n");
            bw.write("  pw: pw\n");

            bw.close();
        }

        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());

        config = mapper.readValue(PATH, AppConfig.class);
    }
}
