package com.blamejared.ironsmelters.data;

import com.blamejared.ironsmelters.ISConstants;
import com.blamejared.ironsmelters.api.SmelterType;
import com.blamejared.ironsmelters.block.ISBlocks;
import com.blamejared.ironsmelters.item.ISItems;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ModelProvider;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.client.data.models.model.TexturedModel;
import net.minecraft.data.PackOutput;

public class ISModelProvider extends ModelProvider {
    
    public ISModelProvider(PackOutput output) {
        
        super(output, ISConstants.MODID);
    }
    
    
    @Override
    protected void registerModels(BlockModelGenerators blockModels, ItemModelGenerators itemModels) {
        
        for(SmelterType smelterType : SmelterType.ALL.values()) {
            blockModels.createFurnace(ISBlocks.FURNACES.get(smelterType).get(), TexturedModel.ORIENTABLE_ONLY_TOP);
            blockModels.createFurnace(ISBlocks.BLAST_FURNACES.get(smelterType)
                    .get(), TexturedModel.ORIENTABLE_ONLY_TOP);
            blockModels.createFurnace(ISBlocks.SMOKER.get(smelterType).get(), TexturedModel.ORIENTABLE);
            //            furnace(ISBlocks.FURNACES.get(smelterType).get(), smelterType.id().getPath());
            //            blastFurnace(ISBlocks.BLAST_FURNACES.get(smelterType).get(), smelterType.id().getPath());
            //            smoker(ISBlocks.SMOKER.get(smelterType).get(), smelterType.id().getPath());
        }
        
        ISItems.UPGRADES.forEach((smelterType, registryObject) -> itemModels.generateFlatItem(registryObject.get(), ModelTemplates.FLAT_HANDHELD_ITEM));
    }
    
    
    //    private void furnace(Block block, String type) {
    //
    //        Identifier top = ISConstants.rl("block/%s_furnace_top".formatted(type));
    //        Identifier side = ISConstants.rl("block/%s_furnace_side".formatted(type));
    //        Identifier front = ISConstants.rl("block/%s_furnace_front".formatted(type));
    //        Identifier frontOn = ISConstants.rl("block/%s_furnace_front_on".formatted(type));
    //        Identifier model = models().orientable("%s_furnace".formatted(type), side, front, top);
    //        Identifier modelOn = models().orientable("%s_furnace_on".formatted(type), side, frontOn, top);
    //
    //        horizontalBlock(block, blockState -> {
    //            if(blockState.getValue(AbstractFurnaceBlock.LIT)) {
    //                return modelOn;
    //            }
    //            return model;
    //        });
    //        itemModels().simpleBlockItem(block);
    //    }
    //
    //    private void blastFurnace(Block block, String type) {
    //
    //        ResourceLocation top = modLoc("block/%s_blast_furnace_top".formatted(type));
    //        ResourceLocation side = modLoc("block/%s_blast_furnace_side".formatted(type));
    //        ResourceLocation front = modLoc("block/%s_blast_furnace_front".formatted(type));
    //        ResourceLocation frontOn = modLoc("block/%s_blast_furnace_front_on".formatted(type));
    //        BlockModelBuilder model = models().orientable("%s_blast_furnace".formatted(type), side, front, top);
    //        BlockModelBuilder modelOn = models().orientable("%s_blast_furnace_on".formatted(type), side, frontOn, top);
    //
    //        horizontalBlock(block, blockState -> {
    //            if(blockState.getValue(AbstractFurnaceBlock.LIT)) {
    //                return modelOn;
    //            }
    //            return model;
    //        });
    //        itemModels().simpleBlockItem(block);
    //    }
    //
    //    private void smoker(Block block, String type) {
    //
    //        ResourceLocation top = modLoc("block/%s_smoker_top".formatted(type));
    //        ResourceLocation bottom = modLoc("block/%s_smoker_bottom".formatted(type));
    //        ResourceLocation side = modLoc("block/%s_smoker_side".formatted(type));
    //        ResourceLocation front = modLoc("block/%s_smoker_front".formatted(type));
    //        ResourceLocation frontOn = modLoc("block/%s_smoker_front_on".formatted(type));
    //        BlockModelBuilder model = models().orientableWithBottom("%s_smoker".formatted(type), side, front, bottom, top);
    //        BlockModelBuilder modelOn = models().orientableWithBottom("%s_smoker_on".formatted(type), side, frontOn, bottom, top);
    //
    //        horizontalBlock(block, blockState -> {
    //            if(blockState.getValue(AbstractFurnaceBlock.LIT)) {
    //                return modelOn;
    //            }
    //            return model;
    //        });
    //        itemModels().simpleBlockItem(block);
    //    }
    
}
