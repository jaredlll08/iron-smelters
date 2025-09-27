package com.blamejared.ironsmelters;


import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.item.CreativeModeTabs;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.entity.player.UseItemOnBlockEvent;

@Mod(ISConstants.MODID)
public class IronSmelters {
    
    public IronSmelters(IEventBus modEventBus, ModContainer modContainer) {
        
        ISCommon.init();
        modEventBus.<BuildCreativeModeTabContentsEvent> addListener(event -> {
            if(event.getTabKey().equals(CreativeModeTabs.FUNCTIONAL_BLOCKS)) {
                ISCommon.registerCreativeTabs(event::accept);
            }
        });
        NeoForge.EVENT_BUS.<UseItemOnBlockEvent> addListener(event -> {
            if(event.getPlayer() == null) {
                return;
            }
            if(ISCommon.interact(event.getPlayer(), event.getLevel(), event.getItemStack(), event.getPos())) {
                event.cancelWithResult(ItemInteractionResult.SUCCESS);
            }
        });
        
    }
    
}
