package com.blamejared.ironsmelters.block.entity;

import com.blamejared.ironsmelters.api.SmelterType;
import com.blamejared.ironsmelters.block.ISAbstractFurnaceBlock;
import com.blamejared.ironsmelters.mixin.AccessAbstractFurnaceBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.SingleRecipeInput;
import net.minecraft.world.level.block.AbstractFurnaceBlock;
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
    
    public static void serverTick(ServerLevel level, BlockPos pos, BlockState state, AbstractISBlockEntity blockEntity) {
        
        final AccessAbstractFurnaceBlockEntity access = blockEntity.access();
        boolean changed = false;
        boolean isLit = false;
        boolean wasLit = false;
        if(access.getLitTimeRemaining() > 0) {
            wasLit = true;
            access.setLitTimeRemaining(access.getLitTimeRemaining() - 1);
            isLit = access.getLitTimeRemaining() > 0;
        }
        
        ItemStack fuel = blockEntity.items.get(SLOT_FUEL);
        ItemStack ingredient = blockEntity.items.get(SLOT_INPUT);
        boolean hasIngredient = !ingredient.isEmpty();
        boolean hasFuel = !fuel.isEmpty();
        
        if(isLit || (hasFuel && hasIngredient)) {
            if(hasIngredient) {
                SingleRecipeInput input = new SingleRecipeInput(ingredient);
                RecipeHolder<? extends AbstractCookingRecipe> recipe = access.getQuickCheck()
                        .getRecipeFor(input, level)
                        .orElse(null);
                if(recipe != null) {
                    final int maxStackSize = blockEntity.getMaxStackSize();
                    ItemStack burnResult = recipe.value().assemble(input);
                    if(!burnResult.isEmpty() && AccessAbstractFurnaceBlockEntity.callCanBurn(blockEntity.items, maxStackSize, burnResult)) {
                        if(!isLit) {
                            int newLitTime = blockEntity.getBurnDuration(level.fuelValues(), fuel);
                            access.setLitTimeRemaining(newLitTime);
                            access.setLitTotalTime(newLitTime);
                            if(newLitTime > 0) {
                                AccessAbstractFurnaceBlockEntity.callConsumeFuel(blockEntity.items, fuel);
                                isLit = true;
                                changed = true;
                            }
                        }
                        
                        if(isLit) {
                            blockEntity.tickAccumulator += blockEntity.type().config().get().furnaceMultiplier();
                            int passedTicks = (int) Math.floor(blockEntity.tickAccumulator);
                            blockEntity.tickAccumulator -= passedTicks;
                            access.setCookingTimer(access.getCookingTimer() + passedTicks);
                            if(access.getCookingTimer() >= access.getCookingTotalTime()) {
                                blockEntity.tickAccumulator = 0;
                                access.setCookingTimer(0);
                                access.setCookingTotalTime(recipe.value().cookingTime());
                                AccessAbstractFurnaceBlockEntity.callBurn(blockEntity.items, ingredient, burnResult);
                                blockEntity.setRecipeUsed(recipe);
                                changed = true;
                            }
                        } else {
                            blockEntity.tickAccumulator = 0;
                            access.setCookingTimer(0);
                        }
                    } else {
                        blockEntity.tickAccumulator = 0;
                        access.setCookingTimer(0);
                    }
                }
            } else {
                blockEntity.tickAccumulator = 0;
                access.setCookingTimer(0);
            }
        } else if(access.getCookingTimer() > 0) {
            blockEntity.tickAccumulator = 0;
            access.setCookingTimer(Mth.clamp(access.getCookingTimer() - BURN_COOL_SPEED, 0, access.getCookingTotalTime()));
        }
        
        if(wasLit != isLit) {
            changed = true;
            state = state.setValue(AbstractFurnaceBlock.LIT, isLit);
            level.setBlockAndUpdate(pos, state);
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
