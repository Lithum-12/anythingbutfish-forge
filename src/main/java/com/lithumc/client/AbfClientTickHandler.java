package com.lithumc.client;

import com.lithumc.AnythingButFish;
import com.lithumc.config.AbfYaclBridge;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ClientTickEvent;

@EventBusSubscriber(modid = AnythingButFish.MOD_ID, value = Dist.CLIENT)
public class AbfClientTickHandler {
    @SubscribeEvent
    public static void onClientTick(ClientTickEvent.Post event) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.gui.screen() != null) return;

        while (AbfClientEvents.OPEN_CONFIG.consumeClick()) {
            if (AbfYaclBridge.isYaclPresent()) {
                mc.gui.setScreen(AbfYaclBridge.createConfigScreen(mc.gui.screen()));
            } else {
                if (mc.player != null) {
                    mc.player.sendSystemMessage(Component.literal(
                            "[AnythingButFish] YACL is not installed. Edit config/anythingbutfish.json5 manually."
                    ));
                }
            }
        }
    }
}