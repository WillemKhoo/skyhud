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
public class HungerCancelMixin {

    @Inject(at = @At("HEAD"), method = "renderFood", cancellable = true)
    private void renderFood(DrawContext context, PlayerEntity player, int top, int right, CallbackInfo ci) {
        if (ModConfig.getInstance().HUNGER_BAR_CANCELLED) {
            ci.cancel();
        }
    }
}
