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
    public static void gatherClient(GatherDataEvent event) {
        
        DataGenerator generator = event.getGenerator();
        PackOutput output = generator.getPackOutput();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();
        
        generator.addProvider(event.includeServer(), new ISLoot(output, lookupProvider));
        generator.addProvider(event.includeServer(), new ISBlockTags(output, lookupProvider, event.getExistingFileHelper()));
        generator.addProvider(event.includeServer(), new ISRecipesProvider(output, lookupProvider));
        generator.addProvider(
                event.includeClient(),
                new ISLangProvider(output)
        );
        generator.addProvider(
                event.includeClient(),
                new IsItemModelProvider(output, event.getExistingFileHelper())
        );
        generator.addProvider(event.includeClient(), new ISBlockStatesProvider(output, event.getExistingFileHelper()));
    }
    
}
