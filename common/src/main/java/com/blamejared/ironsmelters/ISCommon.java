package com.blamejared.ironsmelters;

import com.blamejared.ironsmelters.api.SmelterType;
import com.blamejared.ironsmelters.component.ISComponents;
import com.blamejared.ironsmelters.component.Upgrade;
import com.blamejared.ironsmelters.item.ISItems;
import com.blamejared.ironsmelters.platform.IRegister;
import com.blamejared.ironsmelters.platform.Services;
import com.blamejared.ironsmelters.registry.RegistryObject;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Objects;
import java.util.ServiceLoader;
import java.util.function.Consumer;

public class ISCommon {
    
    public static void init() {
        // Initialize all registry providers
        Services.loadAll(IRegister.class).forEach(ServiceLoader.Provider::get);
    }
    
    public static boolean interact(Player player, Level level, ItemStack stack, BlockPos pos) {
        
        if(!player.isSpectator() && level instanceof ServerLevel sl && stack.has(ISComponents.UPGRADE.get())) {
            Upgrade upgrade = Objects.requireNonNull(stack.get(ISComponents.UPGRADE.get()));
            BlockState blockState = sl.getBlockState(pos);
            if(upgrade.matches(blockState) && upgrade.upgrade(sl, blockState, pos)) {
                stack.consume(1, player);
                return true;
            }
        }
        return false;
    }
    
    public static void registerCreativeTabs(Consumer<ItemLike> registrar) {
        
        for(SmelterType value : SmelterType.ALL.values()) {
            registrar.accept(ISItems.FURNACES.get(value).get());
        }
        for(SmelterType value : SmelterType.ALL.values()) {
            registrar.accept(ISItems.BLAST_FURNACES.get(value).get());
        }
        for(SmelterType value : SmelterType.ALL.values()) {
            registrar.accept(ISItems.SMOKERS.get(value).get());
        }
        
        for(RegistryObject<Item> value : ISItems.UPGRADES.values()) {
            Item item = value.get();
            registrar.accept(item);
        }
    }
    
}
