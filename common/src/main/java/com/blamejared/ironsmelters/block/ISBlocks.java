package com.blamejared.ironsmelters.block;

import com.blamejared.ironsmelters.ISConstants;
import com.blamejared.ironsmelters.api.SmelterType;
import com.blamejared.ironsmelters.platform.IRegister;
import com.blamejared.ironsmelters.registry.RegistrationProvider;
import com.blamejared.ironsmelters.registry.RegistryObject;
import com.google.auto.service.AutoService;
import com.google.common.collect.ImmutableMap;
import net.minecraft.Util;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;

import java.util.Map;

@AutoService(IRegister.class)
public class ISBlocks implements IRegister {
    
    public static final RegistrationProvider<Block> BLOCKS = RegistrationProvider.get(Registries.BLOCK, ISConstants.MODID);
    
    public static final Map<SmelterType, RegistryObject<Block>> FURNACES = Util.make(() -> {
        ImmutableMap.Builder<SmelterType, RegistryObject<Block>> map = ImmutableMap.builder();
        
        for(SmelterType smelterType : SmelterType.ALL.values()) {
            map.put(smelterType, BLOCKS.block(smelterType.id()
                    .getPath() + "_furnace", properties -> new ISFurnaceBlock(properties, smelterType), BlockBehaviour.Properties.ofFullCopy(Blocks.FURNACE)));
        }
        return map.build();
    });
    
    public static final Map<SmelterType, RegistryObject<Block>> BLAST_FURNACES = Util.make(() -> {
        ImmutableMap.Builder<SmelterType, RegistryObject<Block>> map = ImmutableMap.builder();
        
        for(SmelterType smelterType : SmelterType.ALL.values()) {
            map.put(smelterType, BLOCKS.block(smelterType.id()
                    .getPath() + "_blast_furnace", properties -> new ISBlastFurnaceBlock(properties, smelterType), BlockBehaviour.Properties.ofFullCopy(Blocks.BLAST_FURNACE)));
        }
        return map.build();
    });
    
    public static final Map<SmelterType, RegistryObject<Block>> SMOKER = Util.make(() -> {
        ImmutableMap.Builder<SmelterType, RegistryObject<Block>> map = ImmutableMap.builder();
        
        for(SmelterType smelterType : SmelterType.ALL.values()) {
            map.put(smelterType, BLOCKS.block(smelterType.id()
                    .getPath() + "_smoker", properties -> new ISSmokerBlock(properties, smelterType), BlockBehaviour.Properties.ofFullCopy(Blocks.SMOKER)));
        }
        return map.build();
    });
    
}
