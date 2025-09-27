package com.blamejared.ironsmelters.platform;

import com.blamejared.ironsmelters.mixin.AccessAbstractFurnaceBlockEntityNeoForge;
import com.google.auto.service.AutoService;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.fml.ModList;
import org.jetbrains.annotations.Nullable;

import java.util.function.BiFunction;

@AutoService(IPlatformHelper.class)
public class NeoForgePlatformHelper implements IPlatformHelper {
    
    @Override
    public CreativeModeTab.Builder creativeTabBuilder() {
        
        return new CreativeModeTab.Builder(CreativeModeTab.Row.TOP, 0);
    }
    
    @Override
    public boolean isModLoaded(String modId) {
        
        return ModList.get().isLoaded(modId);
    }
    
    @Override
    public <T extends BlockEntity> BlockEntityType.Builder<T> blockEntityBuilder(BiFunction<BlockPos, BlockState, T> factory, Block... validBlocks) {
        
        return BlockEntityType.Builder.of(factory::apply, validBlocks);
    }
    
    @Override
    public boolean burn(RegistryAccess registryAccess, RecipeHolder<?> recipe, NonNullList<ItemStack> inventory, int maxStackSize, AbstractFurnaceBlockEntity entity) {
        
        return AccessAbstractFurnaceBlockEntityNeoForge.callBurn(registryAccess, recipe, inventory, maxStackSize, entity);
    }
    
    @Override
    public boolean canBurn(RegistryAccess registryAccess, @Nullable RecipeHolder<?> recipe, NonNullList<ItemStack> inventory, int maxStackSize, AbstractFurnaceBlockEntity entity) {
        
        return AccessAbstractFurnaceBlockEntityNeoForge.callCanBurn(registryAccess, recipe, inventory, maxStackSize, entity);
    }
    
}
