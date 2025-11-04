package com.freereal;

import com.freereal.data.ModConfig;
import com.freereal.gson.GsonHandler;
import com.freereal.gui.BarGui;
import com.google.gson.Gson;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElement;
import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElementRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.hud.VanillaHudElements;
import net.fabricmc.fabric.api.message.v1.ServerMessageEvents;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SkyHud implements ModInitializer {
	public static final String MOD_ID = "skyhud";
    public static final String MOD_VERSION = "1.0";

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

        // Registering rendering for the health bars
        HudElementRegistry.attachElementBefore(VanillaHudElements.HEALTH_BAR, Identifier.of(SkyHud.MOD_ID, "before_health"), BarGui::render);

        // Initializing ModConfig from the config file (if there is one)
        GsonHandler.init();

        // Checking if the config file is up-to-date
        if (ModConfig.getInstance().checkConfigVersion(MOD_VERSION)) {
            ModConfig.getInstance().updateConfigVersion(MOD_VERSION);
            GsonHandler.writeToFile();
        }
    }
}