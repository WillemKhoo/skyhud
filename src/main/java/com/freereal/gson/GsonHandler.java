package com.freereal.gson;

import com.freereal.data.ModConfig;
import com.google.gson.Gson;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class GsonHandler {
    private static final Gson gson = new Gson();

    private final static String CONFIG_DIR = "config/skyhud";
    private final static String CONFIG_PATH = CONFIG_DIR + "/config.json";

    private static boolean fileExists = false;

    /**
     * This should be run when the mod initializes, as it creates a config file if there is none, or reads the file if there is one
     */
    public static void init() {
        boolean dirExists = false;

        try {
            // Creating a new directory for the config file
            File dir = new File(CONFIG_DIR);
            dirExists = dir.mkdir();

            // Creating the config file
            File configFile = new File(CONFIG_PATH);
            fileExists = !configFile.createNewFile();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        if (fileExists) {
            readFromFile();
        } else {
            writeToFile();
        }
    }

    // One of the worst methods I've ever coded, because it can only be used once - that is on the first creator of the singleton
    public static ModConfig readFromFile() {
        try {
            FileReader reader = new FileReader(CONFIG_PATH);

            ModConfig result = gson.fromJson(reader, ModConfig.class);
            reader.close();

            return result;
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return ModConfig.getInstance();
    }

    public static void writeToFile() {
        try {
            FileWriter writer = new FileWriter(CONFIG_PATH);

            String jsonString = gson.toJson(ModConfig.getInstance());
            writer.write(jsonString);

            writer.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void printConfig() {
        System.out.println(gson.toJson(ModConfig.getInstance()));
    }
}
