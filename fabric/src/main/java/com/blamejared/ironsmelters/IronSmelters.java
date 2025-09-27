package com.blamejared.ironsmelters;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.CreativeModeTabs;

public class IronSmelters implements ModInitializer {
    
    @Override
    public void onInitialize() {
        
        ISCommon.init();
        UseBlockCallback.EVENT.register((player, level, hand, hitResult) -> {
            
            if(ISCommon.interact(player, level, player.getItemInHand(hand), hitResult.getBlockPos())) {
                return InteractionResult.SUCCESS;
            }
            return InteractionResult.PASS;
        });
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.FUNCTIONAL_BLOCKS).register(entries -> {
            ISCommon.registerCreativeTabs(entries::accept);
        });
    }
    
}
