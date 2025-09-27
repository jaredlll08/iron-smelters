package com.blamejared.ironsmelters.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.FurnaceMenu;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.state.BlockState;

public class ISFurnaceBlockEntity extends AbstractISBlockEntity {
    
    public ISFurnaceBlockEntity(BlockPos pos, BlockState blockState) {
        
        super(ISBlockEntityTypes.FURNACE.get(), pos, blockState, RecipeType.SMELTING);
    }
    
    @Override
    protected AbstractContainerMenu createMenu(int i, Inventory inventory) {
        
        return new FurnaceMenu(i, inventory, this, this.dataAccess);
    }
    
}
