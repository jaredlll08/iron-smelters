package com.blamejared.ironsmelters.block;

import com.blamejared.ironsmelters.api.SmelterType;
import com.blamejared.ironsmelters.block.entity.AbstractISBlockEntity;
import com.blamejared.ironsmelters.block.entity.ISBlockEntityTypes;
import com.blamejared.ironsmelters.block.entity.ISFurnaceBlockEntity;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.util.RandomSource;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class ISFurnaceBlock extends ISAbstractFurnaceBlock {
    
    public static final MapCodec<ISFurnaceBlock> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(propertiesCodec(), SmelterType.CODEC.fieldOf("type")
            .forGetter(ISFurnaceBlock::smelterType)).apply(instance, ISFurnaceBlock::new));
    
    public ISFurnaceBlock(Properties properties, SmelterType type) {
        
        super(properties, type, Type.FURNACE);
    }
    
    public MapCodec<ISFurnaceBlock> codec() {
        
        return CODEC;
    }
    
    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState blockState, BlockEntityType<T> blockEntityType) {
        
        return level.isClientSide ? null : createTickerHelper(blockEntityType, ISBlockEntityTypes.FURNACE.get(), AbstractISBlockEntity::serverTick);
    }
    
    @Override
    protected void openContainer(Level level, BlockPos blockPos, Player player) {
        
        BlockEntity blockentity = level.getBlockEntity(blockPos);
        if(blockentity instanceof AbstractISBlockEntity) {
            player.openMenu((MenuProvider) blockentity);
            player.awardStat(Stats.INTERACT_WITH_FURNACE);
        }
    }
    
    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        
        return new ISFurnaceBlockEntity(blockPos, blockState);
    }
    
    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
        
        if(state.getValue(LIT)) {
            double x = pos.getX() + 0.5;
            double y = pos.getY();
            double z = pos.getZ() + 0.5;
            if(random.nextDouble() < 0.1) {
                level.playLocalSound(x, y, z, SoundEvents.FURNACE_FIRE_CRACKLE, SoundSource.BLOCKS, 1.0F, 1.0F, false);
            }
            
            Direction direction = state.getValue(FACING);
            Direction.Axis axis = direction.getAxis();
            double offset = 0.52;
            double hOffset = random.nextDouble() * 0.6 - 0.3;
            double xOffset = axis == Direction.Axis.X ? direction.getStepX() * offset : hOffset;
            double yOffset = random.nextDouble() * 6.0 / 16.0;
            double zOffset = axis == Direction.Axis.Z ? direction.getStepZ() * offset : hOffset;
            level.addParticle(ParticleTypes.SMOKE, x + xOffset, y + yOffset, z + zOffset, 0.0, 0.0, 0.0);
            level.addParticle(ParticleTypes.FLAME, x + xOffset, y + yOffset, z + zOffset, 0.0, 0.0, 0.0);
        }
    }
    
}
