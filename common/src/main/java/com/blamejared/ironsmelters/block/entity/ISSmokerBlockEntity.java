package com.blamejared.ironsmelters.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.SmokerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.state.BlockState;

public class ISSmokerBlockEntity extends AbstractISBlockEntity {
    
    public ISSmokerBlockEntity(BlockPos pos, BlockState blockState) {
        
        super(ISBlockEntityTypes.SMOKER.get(), pos, blockState, RecipeType.SMOKING);
    }
    
    @Override
    protected int getBurnDuration(ItemStack fuel) {
        
        return super.getBurnDuration(fuel) / 2;
    }
    
    @Override
    protected AbstractContainerMenu createMenu(int i, Inventory inventory) {
        
        return new SmokerMenu(i, inventory, this, this.dataAccess);
    }
    
}
