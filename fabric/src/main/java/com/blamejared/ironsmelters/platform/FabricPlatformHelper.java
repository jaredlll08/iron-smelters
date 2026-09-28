package com.blamejared.ironsmelters.platform;

import com.google.auto.service.AutoService;
import net.fabricmc.fabric.api.creativetab.v1.FabricCreativeModeTab;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

import java.util.function.BiFunction;

@AutoService(IPlatformHelper.class)
public class FabricPlatformHelper implements IPlatformHelper {
    
    @Override
    public CreativeModeTab.Builder creativeTabBuilder() {
        
        return FabricCreativeModeTab.builder();
    }
    
    @Override
    public boolean isModLoaded(String modId) {
        
        return FabricLoader.getInstance().isModLoaded(modId);
    }
    
    @Override
    public <T extends BlockEntity> BlockEntityType<T> blockEntityBuilder(BiFunction<BlockPos, BlockState, T> factory, Block... validBlocks) {
        
        return FabricBlockEntityTypeBuilder.create(factory::apply, validBlocks).build();
    }
    
}
