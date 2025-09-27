package com.blamejared.ironsmelters.block;

import com.blamejared.ironsmelters.api.SmelterType;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.block.AbstractFurnaceBlock;

import java.util.List;

public abstract class ISAbstractFurnaceBlock extends AbstractFurnaceBlock {
    
    private final SmelterType smelterType;
    private final Type type;
    
    protected ISAbstractFurnaceBlock(Properties properties, SmelterType smelterType, Type type) {
        
        super(properties);
        this.smelterType = smelterType;
        this.type = type;
    }
    
    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
        tooltipComponents.add(Component.literal("Smelts items " + this.smelterType.config()
                .get()
                .furnaceMultiplier() + "x times faster than a Furnace.").withStyle(ChatFormatting.GRAY));
    }
    
    public SmelterType smelterType() {
        
        return smelterType;
    }
    
    public Type type() {
        
        return type;
    }
    
    public enum Type {
        FURNACE,
        BLAST_FURNACE,
        SMOKER
    }
    
}
