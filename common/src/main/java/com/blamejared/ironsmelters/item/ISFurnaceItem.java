package com.blamejared.ironsmelters.item;

import com.blamejared.ironsmelters.block.ISAbstractFurnaceBlock;
import com.blamejared.ironsmelters.config.SmelterConfig;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import java.text.DecimalFormat;
import java.util.function.Consumer;

public class ISFurnaceItem extends BlockItem {
    
    private static final DecimalFormat MULTIPLIER_FORMAT = new DecimalFormat("#.##");
    
    public ISFurnaceItem(Block block, Properties properties) {
        
        super(block, properties);
    }
    
    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, TooltipDisplay display, Consumer<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        
        super.appendHoverText(stack, context, display, tooltipComponents, tooltipFlag);
        if(this.getBlock() instanceof ISAbstractFurnaceBlock block) {
            SmelterConfig smelterConfig = block.smelterType().config().get();
            double multiplier = switch(block.type()) {
                case FURNACE -> smelterConfig.furnaceMultiplier();
                case BLAST_FURNACE -> smelterConfig.blastFurnaceMultiplier();
                case SMOKER -> smelterConfig.smokerMultiplier();
            };
            String type = switch(block.type()) {
                case FURNACE -> Blocks.FURNACE.getDescriptionId();
                case BLAST_FURNACE -> Blocks.BLAST_FURNACE.getDescriptionId();
                case SMOKER -> Blocks.SMOKER.getDescriptionId();
            };
            tooltipComponents.accept(Component.translatable("ironsmelters.smelter.speed_text", MULTIPLIER_FORMAT.format(multiplier), Component.translatable(type))
                    .withStyle(ChatFormatting.GRAY));
        }
    }
    
}
