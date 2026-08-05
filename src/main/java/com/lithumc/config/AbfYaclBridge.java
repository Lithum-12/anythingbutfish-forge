package com.lithumc.config;

import com.lithumc.AnythingButFish;
import net.minecraft.client.gui.screens.Screen;
import net.minecraftforge.fml.ModList; // ← 替换 FabricLoader
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.lang.reflect.Method;

@OnlyIn(Dist.CLIENT) // ← 替换 @Environment(EnvType.CLIENT)
public class AbfYaclBridge {
    private static final String YACL_MOD_ID = "yet_another_config_lib_v3";
    private static Boolean yaclPresent = null;

    public static boolean isYaclPresent() {
        if (yaclPresent == null) {
            // Forge 检测 Mod 是否存在的方式
            yaclPresent = ModList.get().isLoaded(YACL_MOD_ID);
            AnythingButFish.LOGGER.info("[AnythingButFish] YACL present: {}", yaclPresent);
        }
        return yaclPresent;
    }

    public static Screen createConfigScreen(Screen parent) {
        if (!isYaclPresent()) return null;
        try {
            Class<?> cls = Class.forName("com.lithumc.config.AbfYaclConfig");
            Method m = cls.getMethod("createScreen", Screen.class);
            return (Screen) m.invoke(null, parent);
        } catch (Exception e) {
            AnythingButFish.LOGGER.error("[AnythingButFish] Failed to open YACL config screen", e);
            return null;
        }
    }
}