package com.lithumc.client;

import com.lithumc.AnythingButFish;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.lwjgl.glfw.GLFW;

// 注意这里加了 bus = Mod.EventBusSubscriber.Bus.MOD
@Mod.EventBusSubscriber(modid = AnythingButFish.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class AbfClientEvents {
    public static final KeyMapping OPEN_CONFIG = new KeyMapping(
            "key.anythingbutfish.config", GLFW.GLFW_KEY_O, "AnythingButFish");

    @SubscribeEvent
    public static void onRegisterKeyMappings(RegisterKeyMappingsEvent event) {
        event.register(OPEN_CONFIG);
    }
}