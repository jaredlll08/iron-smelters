package com.blamejared.ironsmelters.component;

import com.blamejared.ironsmelters.ISConstants;
import com.blamejared.ironsmelters.platform.IRegister;
import com.blamejared.ironsmelters.registry.RegistrationProvider;
import com.blamejared.ironsmelters.registry.RegistryObject;
import com.google.auto.service.AutoService;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;

@AutoService(IRegister.class)
public class ISComponents implements IRegister {
    
    private static final RegistrationProvider<DataComponentType<?>> COMPONENTS = RegistrationProvider.get(Registries.DATA_COMPONENT_TYPE, ISConstants.MODID);
    
    public static final RegistryObject<DataComponentType<Upgrade>> UPGRADE = COMPONENTS.register("upgrade", () -> DataComponentType.<Upgrade> builder()
            .persistent(Upgrade.CODEC)
            .build());
    
}
