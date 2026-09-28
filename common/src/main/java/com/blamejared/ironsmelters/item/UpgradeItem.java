package com.blamejared.ironsmelters.item;

import com.blamejared.ironsmelters.api.SmelterType;
import com.blamejared.ironsmelters.component.ISComponents;
import com.blamejared.ironsmelters.component.Upgrade;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;

import java.util.function.Consumer;

public class UpgradeItem extends Item {
    
    public UpgradeItem(Properties properties) {
        
        super(properties);
    }
    
    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, TooltipDisplay display, Consumer<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        
        super.appendHoverText(stack, context, display, tooltipComponents, tooltipFlag);
        if(stack.has(ISComponents.UPGRADE.get())) {
            Upgrade upgrade = stack.get(ISComponents.UPGRADE.get());
            tooltipComponents.accept(Component.translatable("ironsmelters.upgrade.text", upgrade.from()
                            .map(SmelterType::displayComponent)
                            .orElse(Component.translatable("ironsmelters.smeltertype.default"))
                            .withStyle(ChatFormatting.AQUA), upgrade.to().displayComponent().withStyle(ChatFormatting.AQUA))
                    .withStyle(ChatFormatting.GRAY));
        }
    }
    
}
