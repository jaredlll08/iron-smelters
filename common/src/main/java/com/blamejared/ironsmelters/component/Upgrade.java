package com.blamejared.ironsmelters.component;

import com.blamejared.ironsmelters.Util;
import com.blamejared.ironsmelters.api.SmelterType;
import com.blamejared.ironsmelters.block.ISAbstractFurnaceBlock;
import com.blamejared.ironsmelters.block.ISBlocks;
import com.blamejared.ironsmelters.block.entity.AbstractISBlockEntity;
import com.blamejared.ironsmelters.registry.RegistryObject;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.RegistryAccess;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipProvider;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.Property;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Predicate;

public record Upgrade(Optional<SmelterType> from, SmelterType to, boolean keepData) implements TooltipProvider {
    
    public static final Predicate<BlockState> PREDICATE = block -> block.getBlock() == Blocks.FURNACE || block.getBlock() == Blocks.SMOKER || block.getBlock() == Blocks.BLAST_FURNACE;
    public static final Codec<Upgrade> CODEC = RecordCodecBuilder.create(
            instance -> instance.group(
                            SmelterType.CODEC.optionalFieldOf("from").forGetter(Upgrade::from),
                            SmelterType.CODEC.fieldOf("to").forGetter(Upgrade::to),
                            Codec.BOOL.optionalFieldOf("keep_data", true).forGetter(Upgrade::keepData)
                    )
                    .apply(instance, Upgrade::new)
    );
    
    public boolean matches(BlockState state) {
        
        if(state.getBlock() instanceof ISAbstractFurnaceBlock isafb) {
            return from().filter(type -> type == isafb.smelterType()).isPresent();
        }
        
        return from.isEmpty() && PREDICATE.test(state);
    }
    
    public boolean upgrade(ServerLevel level, BlockState blockState, BlockPos pos) {
        
        ISAbstractFurnaceBlock.Type type;
        if(blockState.getBlock() instanceof ISAbstractFurnaceBlock isafb) {
            type = isafb.type();
        } else if(blockState.getBlock() == Blocks.FURNACE) {
            type = ISAbstractFurnaceBlock.Type.FURNACE;
        } else if(blockState.getBlock() == Blocks.BLAST_FURNACE) {
            type = ISAbstractFurnaceBlock.Type.BLAST_FURNACE;
        } else if(blockState.getBlock() == Blocks.SMOKER) {
            type = ISAbstractFurnaceBlock.Type.SMOKER;
        } else {
            return false;
        }
        Map<SmelterType, RegistryObject<Block>> map = Collections.emptyMap();
        switch(type) {
            case FURNACE -> map = ISBlocks.FURNACES;
            case BLAST_FURNACE -> map = ISBlocks.BLAST_FURNACES;
            case SMOKER -> map = ISBlocks.SMOKER;
        }
        BlockState newState = map.get(this.to()).get().defaultBlockState();
        if(this.keepData()) {
            for(Map.Entry<Property<?>, Comparable<?>> entry : blockState.getValues().entrySet()) {
                newState = newState.setValue(entry.getKey(), Util.uncheck(entry.getValue()));
            }
        }
        BlockEntity oldEntity = level.getBlockEntity(pos);
        RegistryAccess registryAccess = level.registryAccess();
        CompoundTag save = oldEntity != null ? oldEntity.saveWithoutMetadata(registryAccess) : null;
        level.removeBlockEntity(pos);
        level.setBlock(pos, newState, Block.UPDATE_ALL);
        if(this.keepData() && level.getBlockEntity(pos) instanceof AbstractISBlockEntity aisbe && save != null) {
            aisbe.loadWithComponents(save, registryAccess);
        }
        
        return true;
    }
    
    @Override
    public void addToTooltip(Item.TooltipContext context, Consumer<Component> tooltipAdder, TooltipFlag tooltipFlag) {
        
        tooltipAdder.accept(Component.literal("test"));
    }
    
}