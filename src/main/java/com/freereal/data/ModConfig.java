package com.freereal.data;

import com.freereal.SkyHud;
import com.freereal.gson.GsonHandler;
import com.google.gson.Gson;

public class ModConfig {
    private static ModConfig instance;

    private String CONFIG_VERSION = SkyHud.MOD_VERSION;

    public boolean HEALTH_BAR_CANCELLED;
    public boolean HUNGER_BAR_CANCELLED;
    public boolean ACTION_BAR_CANCELLED;

    private ModConfig() {
        HEALTH_BAR_CANCELLED = true;
        HUNGER_BAR_CANCELLED = true;
        ACTION_BAR_CANCELLED = true;
    }

    public synchronized static ModConfig getInstance() {
        if (instance == null) {
            // This means that only upon start-up will the JSON file be read
            instance = GsonHandler.readFromFile();

            return instance;
        }

        return instance;
    }

    public boolean checkConfigVersion(String version) {
        if (getVersionMajor(CONFIG_VERSION) < getVersionMajor(version)) {
            return true;
        }
        else {
            return
                    getVersionMajor(CONFIG_VERSION) == getVersionMajor(version) &&
                    getVersionMinor(CONFIG_VERSION) < getVersionMinor(version);
        }
    }

    private static int getVersionMajor(String version) {
        return Integer.parseInt(version.substring(0, version.indexOf(".")));
    }

    private static int getVersionMinor(String version) {
        return Integer.parseInt(version.substring(version.indexOf(".") + 1));
    }

    public void updateConfigVersion(String version) {
        CONFIG_VERSION = version;
    }

    @Override
    public String toString() {
        return CONFIG_VERSION + "\n" +
                HEALTH_BAR_CANCELLED + "\n" +
                HUNGER_BAR_CANCELLED + "\n" +
                ACTION_BAR_CANCELLED + "\n";
    }
}
