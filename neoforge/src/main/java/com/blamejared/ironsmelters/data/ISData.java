package com.blamejared.ironsmelters.data;

import com.blamejared.ironsmelters.ISConstants;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.concurrent.CompletableFuture;

@EventBusSubscriber(modid = ISConstants.MODID)
public class ISData {
    
    @SubscribeEvent
    public static void gatherClient(GatherDataEvent.Client event) {
        
        DataGenerator generator = event.getGenerator();
        PackOutput output = generator.getPackOutput();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();
        
        generator.addProvider(true, new ISLangProvider(output));
        generator.addProvider(true, new ISLoot(output, lookupProvider));
        generator.addProvider(true, new ISBlockTags(output, lookupProvider));
        generator.addProvider(true, new ISRecipesProvider.Runner(output, lookupProvider));
        generator.addProvider(true, new ISModelProvider(output));
    }
    
}
