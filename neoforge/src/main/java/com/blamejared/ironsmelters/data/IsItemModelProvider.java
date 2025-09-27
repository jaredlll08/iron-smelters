package com.blamejared.ironsmelters.data;

import com.blamejared.ironsmelters.ISConstants;
import com.blamejared.ironsmelters.item.ISItems;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class IsItemModelProvider extends ItemModelProvider {
    
    public IsItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        
        super(output, ISConstants.MODID, existingFileHelper);
    }
    
    @Override
    protected void registerModels() {
        
        ISItems.UPGRADES.forEach((smelterType, registryObject) -> this.handheldItem(registryObject.get()));
    }
    
}
