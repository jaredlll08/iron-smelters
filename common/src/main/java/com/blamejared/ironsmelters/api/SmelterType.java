package com.blamejared.ironsmelters.api;

import com.blamejared.ironsmelters.ISConstants;
import com.blamejared.ironsmelters.config.SmelterConfig;
import com.mojang.serialization.Codec;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.Identifier;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;

public record SmelterType(Identifier id, String displayName, Supplier<SmelterConfig> config,
                          Map<String, Optional<SmelterType>> upgradeFrom) {
    
    private static final Map<Identifier, SmelterType> _ALL = new LinkedHashMap<>();
    public static final Map<Identifier, SmelterType> ALL = Collections.unmodifiableMap(_ALL);
    
    public SmelterType(Identifier id, String displayName, Supplier<SmelterConfig> config, Map<String, Optional<SmelterType>> upgradeFrom) {
        
        this.id = id;
        this.displayName = displayName;
        this.config = config;
        this.upgradeFrom = upgradeFrom;
        SmelterType._ALL.put(id, this);
    }
    
    public static final SmelterType COPPER = new SmelterType(ISConstants.id("copper"), "Copper", () -> ISConstants.CONFIG.vanilla.copper, Map.of("stone", Optional.empty()));
    public static final SmelterType IRON = new SmelterType(ISConstants.id("iron"), "Iron", () -> ISConstants.CONFIG.vanilla.iron, Map.of("stone", Optional.empty(),
            "copper", Optional.of(COPPER)));
    public static final SmelterType GOLD = new SmelterType(ISConstants.id("gold"), "Gold", () -> ISConstants.CONFIG.vanilla.gold, Map.of("iron", Optional.of(IRON)));
    public static final SmelterType DIAMOND = new SmelterType(ISConstants.id("diamond"), "Diamond", () -> ISConstants.CONFIG.vanilla.diamond, Map.of("gold", Optional.of(GOLD)));
    public static final SmelterType OBSIDIAN = new SmelterType(ISConstants.id("obsidian"), "Obsidian", () -> ISConstants.CONFIG.vanilla.obsidian, Map.of("obsidian", Optional.of(DIAMOND)));
    
    public static final Codec<SmelterType> CODEC = Identifier.CODEC.xmap(SmelterType.ALL::get, SmelterType::id);
    
    public MutableComponent displayComponent() {
        
        return Component.translatable("ironsmelters.smeltertype." + this.id());
    }
    
}
