package com.blamejared.ironsmelters.data;

import com.blamejared.ironsmelters.ISConstants;
import com.blamejared.ironsmelters.block.ISBlocks;
import com.blamejared.ironsmelters.registry.RegistryObject;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import java.util.concurrent.CompletableFuture;

public class ISBlockTags extends BlockTagsProvider {
    
    public ISBlockTags(PackOutput output, CompletableFuture<HolderLookup.Provider> lookup, ExistingFileHelper helper) {
        
        super(output, lookup, ISConstants.MODID, helper);
    }
    
    @Override
    protected void addTags(HolderLookup.Provider provider) {
        
        
        tag(BlockTags.MINEABLE_WITH_PICKAXE).addAll(ISBlocks.BLOCKS.getEntries()
                .stream()
                .map(RegistryObject::getResourceKey)
                .toList());
        
        tag(BlockTags.FEATURES_CANNOT_REPLACE).addAll(ISBlocks.BLOCKS.getEntries()
                .stream()
                .map(RegistryObject::getResourceKey)
                .toList());
    }
    
}
