package com.blamejared.ironsmelters.data;

import com.blamejared.ironsmelters.block.ISBlocks;
import com.blamejared.ironsmelters.registry.RegistryObject;
import it.unimi.dsi.fastutil.objects.ReferenceOpenHashSet;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;

import java.util.Set;

public class ISBlockLoot extends BlockLootSubProvider {
    
    private final Set<Block> knownBlocks = new ReferenceOpenHashSet<>();
    
    public ISBlockLoot(HolderLookup.Provider provider) {
        
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), provider);
    }
    
    @Override
    protected void generate() {
        
        for(RegistryObject<Block> entry : ISBlocks.BLOCKS.getEntries()) {
            this.dropSelf(entry.get());
        }
    }
    
    @Override
    protected void add(Block block, LootTable.Builder table) {
        
        super.add(block, table);
        knownBlocks.add(block);
    }
    
    @Override
    protected Iterable<Block> getKnownBlocks() {
        
        return knownBlocks;
    }
    
}
