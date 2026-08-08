package com.lithumc.mixin;

import com.lithumc.config.AbfConfig;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder; // 1.20.1 返回这个
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.FishingRodItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(FishingRodItem.class)
public class FishingRodDurabilityMixin {
    @Inject(method = "use", at = @At("RETURN"))
    private void abf$preventDurabilityLoss(Level level, Player player, InteractionHand hand,
                                           CallbackInfoReturnable<InteractionResultHolder<ItemStack>> cir) {
        if (!AbfConfig.get().infiniteDurability) return;
        if (level.isClientSide) return; // 1.20.1 是字段，绝对不能加 ()

        ItemStack stack = player.getItemInHand(hand);
        if (stack.isDamageableItem() && stack.getDamageValue() > 0) {
            stack.setDamageValue(0);
        }
    }
}