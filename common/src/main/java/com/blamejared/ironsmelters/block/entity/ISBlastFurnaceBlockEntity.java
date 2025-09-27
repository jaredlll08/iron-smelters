package com.blamejared.ironsmelters.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.BlastFurnaceMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.state.BlockState;

public class ISBlastFurnaceBlockEntity extends AbstractISBlockEntity {
    
    public ISBlastFurnaceBlockEntity(BlockPos pos, BlockState blockState) {
        
        super(ISBlockEntityTypes.BLAST_FURNACE.get(), pos, blockState, RecipeType.BLASTING);
    }
    
    @Override
    protected int getBurnDuration(ItemStack fuel) {
        
        return super.getBurnDuration(fuel) / 2;
    }
    
    @Override
    protected AbstractContainerMenu createMenu(int i, Inventory inventory) {
        
        return new BlastFurnaceMenu(i, inventory, this, this.dataAccess);
    }
    
}
