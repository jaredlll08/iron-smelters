package com.blamejared.ironsmelters.mixin;

import net.minecraft.core.NonNullList;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.item.crafting.SingleRecipeInput;
import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(AbstractFurnaceBlockEntity.class)
public interface AccessAbstractFurnaceBlockEntity {
    
    @Invoker
    static boolean callCanBurn(NonNullList<ItemStack> items, int maxStackSize, ItemStack burnResult) {throw new UnsupportedOperationException();}
    
    @Invoker
    static void callBurn(NonNullList<ItemStack> items, ItemStack inputItemStack, ItemStack result) {throw new UnsupportedOperationException();}
    
    @Invoker
    static void callConsumeFuel(NonNullList<ItemStack> items, ItemStack fuel) {throw new UnsupportedOperationException();}
    
    @Accessor("quickCheck")
    RecipeManager.CachedCheck<SingleRecipeInput, ? extends AbstractCookingRecipe> getQuickCheck();
    
    @Accessor
    int getCookingTotalTime();
    
    @Accessor
    void setCookingTotalTime(int cookingTotalTime);
    
    @Accessor
    int getLitTimeRemaining();
    
    @Accessor
    void setLitTimeRemaining(int litTimeRemaining);
    
    @Accessor
    int getLitTotalTime();
    
    @Accessor
    void setLitTotalTime(int litTotalTime);
    
    @Accessor
    int getCookingTimer();
    
    @Accessor
    void setCookingTimer(int cookingTimer);
    
}
