package com.lithumc.client;

import com.lithumc.AnythingButFish;
import com.lithumc.config.AbfYaclBridge;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

// 注意这里没有 bus = MOD，默认使用 FORGE 总线
@Mod.EventBusSubscriber(modid = AnythingButFish.MOD_ID, value = Dist.CLIENT)
public class AbfClientTickHandler {
    @SubscribeEvent
    public static void onClientTick(TickEvent.ClientTickEvent event) {
        if (event.phase != TickEvent.Phase.END) return;
        Minecraft mc = Minecraft.getInstance();
        if (mc.screen != null) return;

        while (AbfClientEvents.OPEN_CONFIG.consumeClick()) {
            if (AbfYaclBridge.isYaclPresent()) {
                mc.setScreen(AbfYaclBridge.createConfigScreen(mc.screen));
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