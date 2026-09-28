package com.blamejared.ironsmelters;

import com.blamejared.ironsmelters.config.ISConfig;
import net.darkhax.pricklemc.common.api.config.ConfigManager;
import net.minecraft.resources.Identifier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ISConstants {
    
    public static final String MODID = "ironsmelters";
    public static final Logger LOG = LogManager.getLogger(MODID);
    public static final ISConfig CONFIG = ConfigManager.load(MODID, new ISConfig());
    
    public static Identifier id(String path) {
        
        return Identifier.fromNamespaceAndPath(MODID, path);
    }
    
    public static String sid(final String path) {
        
        return MODID + ":" + path;
    }
    
}
