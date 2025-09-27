package com.blamejared.ironsmelters.block.entity;

import com.blamejared.ironsmelters.ISConstants;
import com.blamejared.ironsmelters.block.ISBlocks;
import com.blamejared.ironsmelters.platform.IRegister;
import com.blamejared.ironsmelters.platform.Services;
import com.blamejared.ironsmelters.registry.RegistrationProvider;
import com.blamejared.ironsmelters.registry.RegistryObject;
import com.google.auto.service.AutoService;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;

@SuppressWarnings("DataFlowIssue")
@AutoService(IRegister.class)
public class ISBlockEntityTypes implements IRegister {
    
    private static final RegistrationProvider<BlockEntityType<?>> BLOCK_ENTITY_TYPES = RegistrationProvider.get(Registries.BLOCK_ENTITY_TYPE, ISConstants.MODID);
    
    public static final RegistryObject<BlockEntityType<ISFurnaceBlockEntity>> FURNACE = BLOCK_ENTITY_TYPES.register("furnace", () -> Services.PLATFORM.blockEntityBuilder(ISFurnaceBlockEntity::new, ISBlocks.FURNACES.values()
                    .stream()
                    .map(RegistryObject::get)
                    .toArray(Block[]::new))
            .build(null));
    
    public static final RegistryObject<BlockEntityType<ISBlastFurnaceBlockEntity>> BLAST_FURNACE = BLOCK_ENTITY_TYPES.register("blast_furnace", () -> Services.PLATFORM.blockEntityBuilder(ISBlastFurnaceBlockEntity::new, ISBlocks.BLAST_FURNACES.values()
                    .stream()
                    .map(RegistryObject::get)
                    .toArray(Block[]::new))
            .build(null));
    
    
    public static final RegistryObject<BlockEntityType<ISSmokerBlockEntity>> SMOKER = BLOCK_ENTITY_TYPES.register("smoker", () -> Services.PLATFORM.blockEntityBuilder(ISSmokerBlockEntity::new, ISBlocks.SMOKER.values()
                    .stream()
                    .map(RegistryObject::get)
                    .toArray(Block[]::new))
            .build(null));
    
}
