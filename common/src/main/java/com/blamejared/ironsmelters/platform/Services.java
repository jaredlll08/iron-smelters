package com.blamejared.ironsmelters.platform;


import com.blamejared.ironsmelters.ISConstants;

import java.util.ServiceLoader;
import java.util.stream.Stream;

public class Services {
    
    public static final IPlatformHelper PLATFORM = load(IPlatformHelper.class);
    
    public static <T> T load(Class<T> clazz) {
        
        final T loadedService = ServiceLoader.load(clazz)
                .findFirst()
                .orElseThrow(() -> new NullPointerException("Failed to load service for " + clazz.getName()));
        ISConstants.LOG.debug("Loaded {} for service {}", loadedService, clazz);
        return loadedService;
    }
    
    public static <T> Stream<ServiceLoader.Provider<T>> loadAll(Class<T> clazz) {
        
        return ServiceLoader.load(clazz).stream();
    }
    
}
