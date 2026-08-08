package com.lithumc;

import com.lithumc.config.AbfConfig;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Mod(AnythingButFish.MOD_ID)
public class AnythingButFish {
    public static final String MOD_ID = "anythingbutfish";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public AnythingButFish(FMLJavaModLoadingContext context) {
        context.getModEventBus().addListener(this::setup);
        MinecraftForge.EVENT_BUS.register(this);

        AbfConfig cfg = AbfConfig.get();
        LOGGER.info("[AnythingButFish] Mod initialized - good luck fishing!");
        LOGGER.info("[AnythingButFish] Config: item={}% entity={}% xp={}% gotaway={}%",
                cfg.chanceItem, cfg.chanceEntity, cfg.chanceXp, 100 - cfg.thresholdXp());
    }

    private void setup(final FMLCommonSetupEvent event) {}
}