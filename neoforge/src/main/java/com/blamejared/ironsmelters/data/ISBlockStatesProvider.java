package com.blamejared.ironsmelters.data;

import com.blamejared.ironsmelters.ISConstants;
import com.blamejared.ironsmelters.api.SmelterType;
import com.blamejared.ironsmelters.block.ISBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.AbstractFurnaceBlock;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.model.generators.BlockModelBuilder;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class ISBlockStatesProvider extends BlockStateProvider {
    
    public ISBlockStatesProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        
        super(output, ISConstants.MODID, exFileHelper);
    }
    
    @Override
    protected void registerStatesAndModels() {
        
        for(SmelterType smelterType : SmelterType.ALL.values()) {
            furnace(ISBlocks.FURNACES.get(smelterType).get(), smelterType.id().getPath());
            blastFurnace(ISBlocks.BLAST_FURNACES.get(smelterType).get(), smelterType.id().getPath());
            smoker(ISBlocks.SMOKER.get(smelterType).get(), smelterType.id().getPath());
        }
    }
    
    private void furnace(Block block, String type) {
        
        ResourceLocation top = modLoc("block/%s_furnace_top".formatted(type));
        ResourceLocation side = modLoc("block/%s_furnace_side".formatted(type));
        ResourceLocation front = modLoc("block/%s_furnace_front".formatted(type));
        ResourceLocation frontOn = modLoc("block/%s_furnace_front_on".formatted(type));
        BlockModelBuilder model = models().orientable("%s_furnace".formatted(type), side, front, top);
        BlockModelBuilder modelOn = models().orientable("%s_furnace_on".formatted(type), side, frontOn, top);
        
        horizontalBlock(block, blockState -> {
            if(blockState.getValue(AbstractFurnaceBlock.LIT)) {
                return modelOn;
            }
            return model;
        });
        itemModels().simpleBlockItem(block);
    }
    
    private void blastFurnace(Block block, String type) {
        
        ResourceLocation top = modLoc("block/%s_blast_furnace_top".formatted(type));
        ResourceLocation side = modLoc("block/%s_blast_furnace_side".formatted(type));
        ResourceLocation front = modLoc("block/%s_blast_furnace_front".formatted(type));
        ResourceLocation frontOn = modLoc("block/%s_blast_furnace_front_on".formatted(type));
        BlockModelBuilder model = models().orientable("%s_blast_furnace".formatted(type), side, front, top);
        BlockModelBuilder modelOn = models().orientable("%s_blast_furnace_on".formatted(type), side, frontOn, top);
        
        horizontalBlock(block, blockState -> {
            if(blockState.getValue(AbstractFurnaceBlock.LIT)) {
                return modelOn;
            }
            return model;
        });
        itemModels().simpleBlockItem(block);
    }
    
    private void smoker(Block block, String type) {
        
        ResourceLocation top = modLoc("block/%s_smoker_top".formatted(type));
        ResourceLocation bottom = modLoc("block/%s_smoker_bottom".formatted(type));
        ResourceLocation side = modLoc("block/%s_smoker_side".formatted(type));
        ResourceLocation front = modLoc("block/%s_smoker_front".formatted(type));
        ResourceLocation frontOn = modLoc("block/%s_smoker_front_on".formatted(type));
        BlockModelBuilder model = models().orientableWithBottom("%s_smoker".formatted(type), side, front, bottom, top);
        BlockModelBuilder modelOn = models().orientableWithBottom("%s_smoker_on".formatted(type), side, frontOn, bottom, top);
        
        horizontalBlock(block, blockState -> {
            if(blockState.getValue(AbstractFurnaceBlock.LIT)) {
                return modelOn;
            }
            return model;
        });
        itemModels().simpleBlockItem(block);
    }
    
}
