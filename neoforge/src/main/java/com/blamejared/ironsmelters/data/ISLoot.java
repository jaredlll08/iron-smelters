package com.blamejared.ironsmelters.data;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;

import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class ISLoot extends LootTableProvider {
    
    public ISLoot(PackOutput output, CompletableFuture<HolderLookup.Provider> lookup) {
        
        super(output, Set.of(), List.of(new SubProviderEntry(ISBlockLoot::new, LootContextParamSets.BLOCK)), lookup);
    }
    
}
