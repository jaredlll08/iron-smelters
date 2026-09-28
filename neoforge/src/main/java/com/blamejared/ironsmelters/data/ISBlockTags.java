package com.blamejared.ironsmelters.data;

import com.blamejared.ironsmelters.ISConstants;
import com.blamejared.ironsmelters.block.ISBlocks;
import com.blamejared.ironsmelters.registry.RegistryObject;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;

import java.util.concurrent.CompletableFuture;

public class ISBlockTags extends BlockTagsProvider {
    
    public ISBlockTags(PackOutput output, CompletableFuture<HolderLookup.Provider> lookup) {
        
        super(output, lookup, ISConstants.MODID);
    }
    
    @Override
    protected void addTags(HolderLookup.Provider provider) {
        
        tag(BlockTags.MINEABLE_WITH_PICKAXE).addAll(ISBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get));
        
        tag(BlockTags.FEATURES_CANNOT_REPLACE).addAll(ISBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get));
    }
    
}
