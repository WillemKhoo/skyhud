package com.freereal;

import com.freereal.gui.BarGui;
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

		LOGGER.info("Hello Fabric world!");
	}
}