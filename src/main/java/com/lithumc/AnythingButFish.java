package com.lithumc;

import com.lithumc.config.AbfConfig;
import com.lithumc.config.AbfYaclBridge;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.loading.FMLEnvironment;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Mod(AnythingButFish.MOD_ID)
public class AnythingButFish {
    public static final String MOD_ID = "anythingbutfish";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public AnythingButFish(ModContainer modContainer) {
        AbfConfig cfg = AbfConfig.get();
        LOGGER.info("[AnythingButFish] Mod initialized - good luck fishing!");
        LOGGER.info("[AnythingButFish] Config: item={}% entity={}% xp={}% gotaway={}%",
                cfg.chanceItem, cfg.chanceEntity, cfg.chanceXp, 100 - cfg.thresholdXp());

        // Registering with YACL absent would hand NeoForge a factory that returns null
        if (FMLEnvironment.getDist() == Dist.CLIENT && AbfYaclBridge.isYaclPresent()) {
            modContainer.registerExtensionPoint(
                    IConfigScreenFactory.class,
                    (container, screen) -> AbfYaclBridge.createConfigScreen(screen));
        }
    }
}