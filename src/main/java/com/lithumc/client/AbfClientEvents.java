package com.lithumc.client;

import com.lithumc.AnythingButFish;
import net.minecraft.client.KeyMapping;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import org.lwjgl.glfw.GLFW;

@EventBusSubscriber(modid = AnythingButFish.MOD_ID, value = Dist.CLIENT, bus = EventBusSubscriber.Bus.MOD)
public class AbfClientEvents {
    public static final KeyMapping OPEN_CONFIG = new KeyMapping(
            "key.anythingbutfish.config", GLFW.GLFW_KEY_O, "key.categories.anythingbutfish");

    @SubscribeEvent
    public static void onRegisterKeyMappings(RegisterKeyMappingsEvent event) {
        event.register(OPEN_CONFIG);
    }
}