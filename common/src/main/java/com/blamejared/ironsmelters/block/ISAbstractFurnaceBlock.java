package com.blamejared.ironsmelters.block;

import com.blamejared.ironsmelters.api.SmelterType;
import net.minecraft.world.level.block.AbstractFurnaceBlock;

public abstract class ISAbstractFurnaceBlock extends AbstractFurnaceBlock {
    
    private final SmelterType smelterType;
    private final Type type;
    
    protected ISAbstractFurnaceBlock(Properties properties, SmelterType smelterType, Type type) {
        
        super(properties);
        this.smelterType = smelterType;
        this.type = type;
    }
    
    public SmelterType smelterType() {
        
        return smelterType;
    }
    
    public Type type() {
        
        return type;
    }
    
    public enum Type {
        FURNACE,
        BLAST_FURNACE,
        SMOKER
    }
    
}
