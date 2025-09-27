package com.blamejared.ironsmelters.data;

import com.blamejared.ironsmelters.ISConstants;
import com.blamejared.ironsmelters.api.SmelterType;
import com.blamejared.ironsmelters.block.ISBlocks;
import com.blamejared.ironsmelters.registry.RegistryObject;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.LanguageProvider;

import java.util.Map;
import java.util.Optional;

public class ISLangProvider extends LanguageProvider {
    
    public ISLangProvider(PackOutput output) {
        
        super(output, ISConstants.MODID, "en_us");
    }
    
    @Override
    protected void addTranslations() {
        
        this.add("itemGroup.ironsmelters", "Iron Smelters");
        this.add("ironsmelters.upgrade.text", "Upgrades a Smelter from %s to %s");
        this.add("ironsmelters.smeltertype.default", "Stone");
        
        for(SmelterType smelterType : SmelterType.ALL.values()) {
            this.add("ironsmelters.smeltertype." + smelterType.id(), smelterType.displayName());
            furnace(ISBlocks.FURNACES.get(smelterType), smelterType.displayName());
            blastFurnace(ISBlocks.BLAST_FURNACES.get(smelterType), smelterType.displayName());
            smoker(ISBlocks.SMOKER.get(smelterType), smelterType.displayName());
        }
        
        for(SmelterType smelterType : SmelterType.ALL.values()) {
            for(Map.Entry<String, Optional<SmelterType>> upgrade : smelterType.upgradeFrom().entrySet()) {
                String key = "upgrade_" + upgrade.getValue()
                        .map(smelterType1 -> smelterType1.id().getPath())
                        .orElse("stone") + "_to_" + smelterType.id().getPath();
                String display = upgrade.getValue()
                        .map(SmelterType::displayName)
                        .orElse("Stone");
                this.add("item.%s.%s".formatted(ISConstants.MODID, key), "%s to %s Smelter Upgrade".formatted(display, smelterType.displayName()));
            }
        }
        
    }
    
    private void furnace(RegistryObject<Block> block, String name) {
        
        this.addBlock(block, "%s Furnace".formatted(name));
    }
    
    private void blastFurnace(RegistryObject<Block> block, String name) {
        
        this.addBlock(block, "%s Blast Furnace".formatted(name));
    }
    
    private void smoker(RegistryObject<Block> block, String name) {
        
        this.addBlock(block, "%s Smoker".formatted(name));
    }
    
}
