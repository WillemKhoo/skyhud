package com.freereal.mixin.gui_cancels;

import com.freereal.data.ModConfig;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGameHud.class)
public abstract class HeartsCancelMixin {

    @Inject(at = @At("HEAD"), method = "renderHealthBar", cancellable = true)
    private void renderHealthBar(DrawContext context,
                                 PlayerEntity player,
                                 int x,
                                 int y,
                                 int lines,
                                 int regeneratingHeartIndex,
                                 float maxHealth,
                                 int lastHealth,
                                 int health,
                                 int absorption,
                                 boolean blinking,
                                 CallbackInfo ci) {
        if (ModConfig.getInstance().HEALTH_BAR_CANCELLED) {
            ci.cancel();
        }
    }
}
