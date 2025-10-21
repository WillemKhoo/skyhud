package com.freereal.mixin;

import com.freereal.data_extractor.ActionBarData;
import com.freereal.data_extractor.SkyblockData;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGameHud.class)
public abstract class ActionBarMixin {

    @Inject(at = @At("HEAD"), method = "setOverlayMessage")
    public void updateFromActionBar(Text message, boolean tinted, CallbackInfo ci) {
        ActionBarData actionBarData = new ActionBarData(message.asTruncatedString(100));
        actionBarData.updateToSkyblockData();

        System.out.println(SkyblockData.getInstance().toString());
    }
}
