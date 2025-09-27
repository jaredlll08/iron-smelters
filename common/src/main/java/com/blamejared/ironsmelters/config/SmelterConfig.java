package com.blamejared.ironsmelters.config;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.darkhax.pricklemc.common.api.annotations.Value;

import java.util.Objects;

public final class SmelterConfig {
    
    public static final Codec<SmelterConfig> CODEC = RecordCodecBuilder.create(instance -> instance.group(
                    Codec.FLOAT.fieldOf("furnaceMultiplier").forGetter(SmelterConfig::furnaceMultiplier),
                    Codec.FLOAT.fieldOf("blastFurnaceMultiplier").forGetter(SmelterConfig::blastFurnaceMultiplier),
                    Codec.FLOAT.fieldOf("smokerMultiplier").forGetter(SmelterConfig::smokerMultiplier)
            )
            .apply(instance, SmelterConfig::new));
    @Value(name = "Furnace Speed Multiplier", comment = "How fast items are smelted compared to the vanilla Furnace.")
    private float furnaceMultiplier;
    @Value(name = "Blast Furnace Speed Multiplier", comment = "How fast items are smelted compared to the vanilla Blast Furnace.")
    private float blastFurnaceMultiplier;
    @Value(name = "Smoker Speed Multiplier", comment = "How fast items are smelted compared to the vanilla Smoker.")
    private float smokerMultiplier;
    
    public SmelterConfig(
            float furnaceMultiplier,
            float blastFurnaceMultiplier,
            float smokerMultiplier) {
        
        this.furnaceMultiplier = furnaceMultiplier;
        this.blastFurnaceMultiplier = blastFurnaceMultiplier;
        this.smokerMultiplier = smokerMultiplier;
    }
    
    public float furnaceMultiplier() {return furnaceMultiplier;}
    
    public float blastFurnaceMultiplier() {return blastFurnaceMultiplier;}
    
    public float smokerMultiplier() {return smokerMultiplier;}
    
    @Override
    public boolean equals(Object obj) {
        
        if(obj == this) {
            return true;
        }
        if(obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        var that = (SmelterConfig) obj;
        return Float.floatToIntBits(this.furnaceMultiplier) == Float.floatToIntBits(that.furnaceMultiplier) &&
                Float.floatToIntBits(this.blastFurnaceMultiplier) == Float.floatToIntBits(that.blastFurnaceMultiplier) &&
                Float.floatToIntBits(this.smokerMultiplier) == Float.floatToIntBits(that.smokerMultiplier);
    }
    
    @Override
    public int hashCode() {
        
        return Objects.hash(furnaceMultiplier, blastFurnaceMultiplier, smokerMultiplier);
    }
    
    @Override
    public String toString() {
        
        return "SmelterConfig[" +
                "furnaceMultiplier=" + furnaceMultiplier + ", " +
                "blastFurnaceMultiplier=" + blastFurnaceMultiplier + ", " +
                "smokerMultiplier=" + smokerMultiplier + ']';
    }
    
}
