package com.blamejared.ironsmelters.config.sets;

import com.blamejared.ironsmelters.config.SmelterConfig;
import net.darkhax.pricklemc.common.api.annotations.Value;

public class VanillaSmelterConfig {
    
    @Value(name = "Copper", writeDefault = false, comment = "Configuration for the Copper smelters")
    public SmelterConfig copper = new SmelterConfig(1.6F, 1.6F, 1.6F);
    
    @Value(name = "Iron", writeDefault = false, comment = "Configuration for the Iron smelters")
    public SmelterConfig iron = new SmelterConfig(2F, 2F, 2F);
    
    @Value(name = "Gold", writeDefault = false, comment = "Configuration for the Gold smelters")
    public SmelterConfig gold = new SmelterConfig(4F, 4F, 4F);
    
    @Value(name = "Diamond", writeDefault = false, comment = "Configuration for the Diamond smelters")
    public SmelterConfig diamond = new SmelterConfig(8F, 8F, 8F);
    
    @Value(name = "Obsidian", writeDefault = false, comment = "Configuration for the Obsidian smelters")
    public SmelterConfig obsidian = new SmelterConfig(16F, 16F, 16F);
    
}
