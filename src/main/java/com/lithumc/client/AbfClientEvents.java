package com.lithumc.client;

import com.lithumc.AnythingButFish;
import net.minecraft.client.KeyMapping;
import net.minecraft.resources.Identifier;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import org.lwjgl.glfw.GLFW;

@EventBusSubscriber(modid = AnythingButFish.MOD_ID, value = Dist.CLIENT)
public class AbfClientEvents {
    // 1.21.11: the category is a record over an Identifier, not a raw translation key
    public static final KeyMapping.Category CATEGORY =
            KeyMapping.Category.register(Identifier.fromNamespaceAndPath(AnythingButFish.MOD_ID, "config"));

    public static final KeyMapping OPEN_CONFIG = new KeyMapping(
            "key.anythingbutfish.config", GLFW.GLFW_KEY_O, CATEGORY);

    @SubscribeEvent
    public static void onRegisterKeyMappings(RegisterKeyMappingsEvent event) {
        event.register(OPEN_CONFIG);
    }
}
