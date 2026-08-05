package com.lithumc.client;

import com.lithumc.AnythingButFish;
import com.lithumc.config.AbfYaclBridge;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.lwjgl.glfw.GLFW;

@Mod.EventBusSubscriber(modid = AnythingButFish.MOD_ID, value = Dist.CLIENT)
public class AbfClientEvents {

    // 默认不绑定按键 (UNKNOWN)，玩家可以在 选项 -> 控制 -> 按键设置 中自己改
    public static final KeyMapping OPEN_CONFIG = new KeyMapping(
            "key.anythingbutfish.config",
            GLFW.GLFW_KEY_UNKNOWN,
            "AnythingButFish"
    );

    @SubscribeEvent
    public static void onRegisterKeyMappings(RegisterKeyMappingsEvent event) {
        event.register(OPEN_CONFIG);
    }

    @SubscribeEvent
    public static void onClientTick(TickEvent.ClientTickEvent event) {
        if (event.phase != TickEvent.Phase.END) return;

        Minecraft mc = Minecraft.getInstance();
        if (mc.screen != null) return; // 避免在打开其他界面时触发

        while (OPEN_CONFIG.consumeClick()) {
            if (AbfYaclBridge.isYaclPresent()) {
                mc.setScreen(AbfYaclBridge.createConfigScreen(null));
            } else {
                // 如果没有 YACL，可以在聊天栏提示玩家
                if (mc.player != null) {
                    mc.player.sendSystemMessage(net.minecraft.network.chat.Component.literal(
                            "[AnythingButFish] YACL is not installed. Edit config/anythingbutfish.json5 manually."
                    ));
                }
            }
        }
    }
}