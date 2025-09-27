package com.blamejared.ironsmelters.block.entity;

import com.blamejared.ironsmelters.api.SmelterType;
import com.blamejared.ironsmelters.block.ISAbstractFurnaceBlock;
import com.blamejared.ironsmelters.mixin.AccessAbstractFurnaceBlockEntity;
import com.blamejared.ironsmelters.platform.Services;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.SingleRecipeInput;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.AbstractFurnaceBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public abstract class AbstractISBlockEntity extends AbstractFurnaceBlockEntity {
    
    private final SmelterType type;
    private final Component defaultName;
    private float tickAccumulator = 0;
    
    protected AbstractISBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState blockState, RecipeType<? extends AbstractCookingRecipe> recipeType) {
        
        super(type, pos, blockState, recipeType);
        if(this.getBlockState().getBlock() instanceof ISAbstractFurnaceBlock block) {
            this.type = block.smelterType();
            this.defaultName = block.getName();
        } else {
            throw new IllegalStateException("Expected ISAbstractFurnaceBlock");
        }
    }
    
    @Override
    protected Component getDefaultName() {
        
        return defaultName;
    }
    
    public static void serverTick(Level level, BlockPos pos, BlockState state, AbstractISBlockEntity blockEntity) {
        
        final AccessAbstractFurnaceBlockEntity access = blockEntity.access();
        boolean wasLit = access.callIsLit();
        boolean changed = false;
        if(access.callIsLit()) {
            access.setLitTime(access.getLitTime() - 1);
        }
        
        ItemStack fuel = blockEntity.items.get(SLOT_FUEL);
        ItemStack input = blockEntity.items.get(SLOT_INPUT);
        boolean hasInput = !input.isEmpty();
        boolean hasFuel = !fuel.isEmpty();
        if(access.callIsLit() || (hasFuel && hasInput)) {
            RecipeHolder<?> recipeholder = null;
            if(hasInput) {
                recipeholder = access.getQuickCheck().getRecipeFor(new SingleRecipeInput(input), level)
                        .orElse(null);
            }
            
            int maxStackSize = blockEntity.getMaxStackSize();
            if(!access.callIsLit() && Services.PLATFORM.canBurn(level.registryAccess(), recipeholder, blockEntity.items, maxStackSize, blockEntity)) {
                access.setLitTime(blockEntity.getBurnDuration(fuel));
                access.setLitDuration(access.getLitTime());
                if(access.callIsLit()) {
                    changed = true;
                    if(hasFuel) {
                        Item fuelItem = fuel.getItem();
                        fuel.shrink(1);
                        if(fuel.isEmpty()) {
                            Item remainder = fuelItem.getCraftingRemainingItem();
                            blockEntity.items.set(SLOT_FUEL, remainder == null ? ItemStack.EMPTY : new ItemStack(remainder));
                        }
                    }
                }
            }
            
            if(access.callIsLit() && Services.PLATFORM.canBurn(level.registryAccess(), recipeholder, blockEntity.items, maxStackSize, blockEntity)) {
                blockEntity.tickAccumulator += blockEntity.type().config().get().furnaceMultiplier();
                int passedTicks = (int) Math.floor(blockEntity.tickAccumulator);
                blockEntity.tickAccumulator -= passedTicks;
                access.setCookingProgress(access.getCookingProgress() + passedTicks);
                if(access.getCookingProgress() >= access.getCookingTotalTime()) {
                    blockEntity.tickAccumulator = 0;
                    access.setCookingProgress(0);
                    access.setCookingTotalTime(AccessAbstractFurnaceBlockEntity.callGetTotalCookTime(level, blockEntity));
                    if(Services.PLATFORM.burn(level.registryAccess(), recipeholder, blockEntity.items, maxStackSize, blockEntity)) {
                        blockEntity.setRecipeUsed(recipeholder);
                    }
                    
                    changed = true;
                }
            } else {
                blockEntity.tickAccumulator = 0;
                access.setCookingProgress(0);
            }
        } else if(access.getCookingProgress() > 0) {
            blockEntity.tickAccumulator = 0;
            access.setCookingProgress(Mth.clamp(access.getCookingProgress() - BURN_COOL_SPEED, 0, access.getCookingTotalTime()));
        }
        
        if(wasLit != access.callIsLit()) {
            changed = true;
            state = state.setValue(AbstractFurnaceBlock.LIT, access.callIsLit());
            level.setBlock(pos, state, Block.UPDATE_NEIGHBORS | Block.UPDATE_CLIENTS);
        }
        
        if(changed) {
            setChanged(level, pos, state);
        }
    }
    
    private AccessAbstractFurnaceBlockEntity access() {
        
        return (AccessAbstractFurnaceBlockEntity) this;
    }
    
    public SmelterType type() {
        
        return type;
    }
    
}
