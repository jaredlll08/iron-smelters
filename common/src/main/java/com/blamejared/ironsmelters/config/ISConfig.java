package com.blamejared.ironsmelters.config;

import com.blamejared.ironsmelters.config.sets.VanillaSmelterConfig;
import net.darkhax.pricklemc.common.api.annotations.Value;

public class ISConfig {
    
    @Value(writeDefault = false, comment = "Configuration for the Vanilla Smelters")
    public VanillaSmelterConfig vanilla = new VanillaSmelterConfig();
    
}