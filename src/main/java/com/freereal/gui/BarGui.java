package com.freereal.gui;

import com.freereal.data_extractor.SkyblockData;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.RenderTickCounter;

public class BarGui {
    private static final SkyblockData sbd = SkyblockData.getInstance();

    public static void render(DrawContext context, RenderTickCounter renderTickCounter) {
        final int SCALED_RESOLUTION_X = context.getScaledWindowWidth();
        final int SCALED_RESOLUTION_Y = context.getScaledWindowHeight();

        final int TEXT_HEIGHT = 8; // pixels tall, according to the scaled resolution

        final TextRenderer textRenderer = MinecraftClient.getInstance().textRenderer;

        // Could abstract this later, don't feel like it now (will haunt me later probably)
        context.drawCenteredTextWithShadow(textRenderer, getHealthString(), SCALED_RESOLUTION_X / 2, SCALED_RESOLUTION_Y / 2 - TEXT_HEIGHT / 2 - TEXT_HEIGHT, 0xFFFF0000);
        context.drawCenteredTextWithShadow(textRenderer, Integer.toString(sbd.def), SCALED_RESOLUTION_X / 2, SCALED_RESOLUTION_Y / 2 - TEXT_HEIGHT / 2, 0xFF00FF00);
        context.drawCenteredTextWithShadow(textRenderer, getManaString(), SCALED_RESOLUTION_X / 2, SCALED_RESOLUTION_Y / 2 - TEXT_HEIGHT / 2 + TEXT_HEIGHT, 0xFF0000FF);
    }

    private static String getHealthString() {
        return Integer.toString(sbd.hp) + "/" + Integer.toString(sbd.maxHp);
    }

    private static String getManaString() {
        return Integer.toString(sbd.mp) + "/" + Integer.toString(sbd.maxMp);
    }
}
