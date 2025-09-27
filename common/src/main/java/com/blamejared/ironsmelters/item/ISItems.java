package com.blamejared.ironsmelters.item;

import com.blamejared.ironsmelters.ISCommon;
import com.blamejared.ironsmelters.ISConstants;
import com.blamejared.ironsmelters.api.SmelterType;
import com.blamejared.ironsmelters.block.ISBlocks;
import com.blamejared.ironsmelters.component.ISComponents;
import com.blamejared.ironsmelters.component.Upgrade;
import com.blamejared.ironsmelters.platform.IRegister;
import com.blamejared.ironsmelters.platform.Services;
import com.blamejared.ironsmelters.registry.RegistrationProvider;
import com.blamejared.ironsmelters.registry.RegistryObject;
import com.google.auto.service.AutoService;
import com.google.common.collect.ImmutableMap;
import com.mojang.datafixers.util.Pair;
import net.minecraft.Util;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;

import java.util.Map;
import java.util.Optional;

@AutoService(IRegister.class)
public class ISItems implements IRegister {
    
    public static final RegistrationProvider<Item> ITEMS = RegistrationProvider.get(Registries.ITEM, ISConstants.MODID);
    private static final RegistrationProvider<CreativeModeTab> TABS = RegistrationProvider.get(Registries.CREATIVE_MODE_TAB, ISConstants.MODID);
    
    public static final RegistryObject<CreativeModeTab> TAB = TABS.register(ISConstants.MODID, () -> Services.PLATFORM.creativeTabBuilder()
            .displayItems((parameters, output) -> ISCommon.registerCreativeTabs(output::accept))
            .icon(() -> ISItems.FURNACES.get(SmelterType.IRON).get().getDefaultInstance())
            .title(Component.translatable("itemGroup.ironsmelters"))
            .build());
    
    
    public static final Map<SmelterType, RegistryObject<Item>> FURNACES = Util.make(() -> {
        ImmutableMap.Builder<SmelterType, RegistryObject<Item>> map = ImmutableMap.builder();
        
        for(SmelterType smelterType : SmelterType.ALL.values()) {
            map.put(smelterType, ITEMS.blockItem(smelterType.id()
                    .getPath() + "_furnace", ISBlocks.FURNACES.get(smelterType), properties -> properties));
            
        }
        return map.build();
    });
    
    public static final Map<SmelterType, RegistryObject<Item>> BLAST_FURNACES = Util.make(() -> {
        ImmutableMap.Builder<SmelterType, RegistryObject<Item>> map = ImmutableMap.builder();
        
        for(SmelterType smelterType : SmelterType.ALL.values()) {
            map.put(smelterType, ITEMS.blockItem(smelterType.id()
                    .getPath() + "_blast_furnace", ISBlocks.BLAST_FURNACES.get(smelterType), properties -> properties));
            
        }
        return map.build();
    });
    
    public static final Map<SmelterType, RegistryObject<Item>> SMOKERS = Util.make(() -> {
        ImmutableMap.Builder<SmelterType, RegistryObject<Item>> map = ImmutableMap.builder();
        
        for(SmelterType smelterType : SmelterType.ALL.values()) {
            map.put(smelterType, ITEMS.blockItem(smelterType.id()
                    .getPath() + "_smoker", ISBlocks.SMOKER.get(smelterType), properties -> properties));
            
        }
        return map.build();
    });
    
    public static final Map<Pair<SmelterType, Optional<SmelterType>>, RegistryObject<Item>> UPGRADES = Util.make(() -> {
        ImmutableMap.Builder<Pair<SmelterType, Optional<SmelterType>>, RegistryObject<Item>> map = ImmutableMap.builder();
        
        for(SmelterType smelterType : SmelterType.ALL.values()) {
            for(Map.Entry<String, Optional<SmelterType>> upgrade : smelterType.upgradeFrom().entrySet()) {
                String key = "upgrade_" + upgrade.getValue()
                        .map(fromType -> fromType.id().getPath())
                        .orElse("stone") + "_to_" + smelterType.id().getPath();
                Upgrade upgradeComponent = new Upgrade(upgrade.getValue(), smelterType, true);
                map.put(Pair.of(smelterType, upgrade.getValue()), ITEMS.item(key, properties -> new UpgradeItem(properties.component(ISComponents.UPGRADE.get(), upgradeComponent))));
            }
        }
        return map.build();
    });
    
}
