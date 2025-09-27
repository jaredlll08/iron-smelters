package com.blamejared.ironsmelters.mixin;

import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.item.crafting.SingleRecipeInput;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(AbstractFurnaceBlockEntity.class)
public interface AccessAbstractFurnaceBlockEntity {
    
    @Invoker("getTotalCookTime")
    static int callGetTotalCookTime(Level level, AbstractFurnaceBlockEntity blockEntity) {throw new UnsupportedOperationException();}
    
    @Invoker("isLit")
    boolean callIsLit();
    
    @Accessor("quickCheck")
    RecipeManager.CachedCheck<SingleRecipeInput, ? extends AbstractCookingRecipe> getQuickCheck();
    
    @Accessor("litTime")
    int getLitTime();
    
    @Accessor("litTime")
    void setLitTime(int litTime);
    
    @Accessor("litDuration")
    void setLitDuration(int litDuration);
    
    @Accessor("cookingProgress")
    int getCookingProgress();
    
    @Accessor("cookingProgress")
    void setCookingProgress(int cookingProgress);
    
    @Accessor("cookingTotalTime")
    int getCookingTotalTime();
    
    @Accessor("cookingTotalTime")
    void setCookingTotalTime(int cookingTotalTime);
    
}
