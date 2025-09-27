package com.blamejared.ironsmelters.data;

import com.blamejared.ironsmelters.ISConstants;
import com.blamejared.ironsmelters.api.SmelterType;
import com.blamejared.ironsmelters.item.ISItems;
import com.mojang.datafixers.util.Pair;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.common.Tags;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

public class ISRecipesProvider extends RecipeProvider {
    
    public ISRecipesProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> provider) {
        
        super(output, provider);
    }
    
    @Override
    protected void buildRecipes(RecipeOutput output) {
        
        furnace(output, "furnace", "copper", SmelterType.COPPER, Tags.Items.INGOTS_COPPER, Items.FURNACE);
        cheapOutline(output, "copper_furnace", "iron", "furnace", ISItems.FURNACES.get(SmelterType.IRON), Tags.Items.INGOTS_IRON, ISItems.FURNACES.get(SmelterType.COPPER)
                .get());
        furnace(output, "furnace", "iron", SmelterType.IRON, Tags.Items.INGOTS_IRON, Items.FURNACE);
        furnace(output, "iron_furnace", "gold", SmelterType.GOLD, Tags.Items.INGOTS_GOLD, ISItems.FURNACES.get(SmelterType.IRON)
                .get());
        cheapOutline(output, "gold_furnace", "diamond", "furnace", ISItems.FURNACES.get(SmelterType.DIAMOND), Tags.Items.GEMS_DIAMOND, ISItems.FURNACES.get(SmelterType.GOLD)
                .get());
        furnace(output, "diamond_furnace", "obsidian", SmelterType.OBSIDIAN, Tags.Items.OBSIDIANS_NORMAL, ISItems.FURNACES.get(SmelterType.DIAMOND)
                .get());
        
        
        blastFurnace(output, "blast_furnace", "copper", SmelterType.COPPER, Tags.Items.INGOTS_COPPER, Items.BLAST_FURNACE);
        cheapOutline(output, "copper_blast_furnace", "iron", "blast_furnace", ISItems.BLAST_FURNACES.get(SmelterType.IRON), Tags.Items.INGOTS_IRON, ISItems.BLAST_FURNACES.get(SmelterType.COPPER)
                .get());
        blastFurnace(output, "blast_furnace", "iron", SmelterType.IRON, Tags.Items.INGOTS_IRON, Items.BLAST_FURNACE);
        blastFurnace(output, "iron_blast_furnace", "gold", SmelterType.GOLD, Tags.Items.INGOTS_GOLD, ISItems.BLAST_FURNACES.get(SmelterType.IRON)
                .get());
        cheapOutline(output, "gold_blast_furnace", "diamond", "blast_furnace", ISItems.BLAST_FURNACES.get(SmelterType.DIAMOND), Tags.Items.GEMS_DIAMOND, ISItems.BLAST_FURNACES.get(SmelterType.GOLD)
                .get());
        blastFurnace(output, "diamond_blast_furnace", "obsidian", SmelterType.OBSIDIAN, Tags.Items.OBSIDIANS_NORMAL, ISItems.BLAST_FURNACES.get(SmelterType.DIAMOND)
                .get());
        
        smoker(output, "smoker", "copper", SmelterType.COPPER, Tags.Items.INGOTS_COPPER, Items.SMOKER);
        cheapOutline(output, "copper_smoker", "iron", "smoker", ISItems.SMOKERS.get(SmelterType.IRON), Tags.Items.INGOTS_IRON, ISItems.SMOKERS.get(SmelterType.COPPER)
                .get());
        smoker(output, "smoker", "iron", SmelterType.IRON, Tags.Items.INGOTS_IRON, Items.SMOKER);
        smoker(output, "iron_smoker", "gold", SmelterType.GOLD, Tags.Items.INGOTS_GOLD, ISItems.SMOKERS.get(SmelterType.IRON)
                .get());
        cheapOutline(output, "gold_smoker", "diamond", "smoker", ISItems.SMOKERS.get(SmelterType.DIAMOND), Tags.Items.GEMS_DIAMOND, ISItems.SMOKERS.get(SmelterType.GOLD)
                .get());
        smoker(output, "diamond_smoker", "obsidian", SmelterType.OBSIDIAN, Tags.Items.OBSIDIANS_NORMAL, ISItems.SMOKERS.get(SmelterType.DIAMOND)
                .get());
        
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, ISItems.UPGRADES.get(Pair.of(SmelterType.COPPER, Optional.empty()))
                        .get())
                .define('I', Tags.Items.INGOTS_COPPER)
                .define('S', Tags.Items.COBBLESTONES)
                .pattern("III")
                .pattern("ISI")
                .pattern("III")
                .unlockedBy("has_copper", has(Tags.Items.INGOTS_COPPER))
                .unlockedBy("has_furnace", has(Tags.Items.PLAYER_WORKSTATIONS_FURNACES))
                .save(output, ISConstants.rl("stone_to_copper_upgrade"));
        
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, ISItems.UPGRADES.get(Pair.of(SmelterType.IRON, Optional.empty()))
                        .get())
                .define('I', Tags.Items.INGOTS_IRON)
                .define('S', Tags.Items.COBBLESTONES)
                .pattern("III")
                .pattern("ISI")
                .pattern("III")
                .unlockedBy("has_iron", has(Tags.Items.INGOTS_IRON))
                .unlockedBy("has_furnace", has(Tags.Items.PLAYER_WORKSTATIONS_FURNACES))
                .save(output, ISConstants.rl("stone_to_iron_upgrade"));
        
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, ISItems.UPGRADES.get(Pair.of(SmelterType.IRON, Optional.of(SmelterType.COPPER)))
                        .get())
                .define('I', Tags.Items.INGOTS_IRON)
                .define('S', Tags.Items.COBBLESTONES)
                .define('G', Tags.Items.GLASS_BLOCKS)
                .pattern("III")
                .pattern("GSG")
                .pattern("III")
                .unlockedBy("has_iron", has(Tags.Items.INGOTS_IRON))
                .unlockedBy("has_furnace", has(Tags.Items.PLAYER_WORKSTATIONS_FURNACES))
                .save(output, ISConstants.rl("copper_to_iron_upgrade"));
        
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, ISItems.UPGRADES.get(Pair.of(SmelterType.GOLD, Optional.of(SmelterType.IRON)))
                        .get())
                .define('I', Tags.Items.INGOTS_GOLD)
                .define('S', Tags.Items.COBBLESTONES)
                .pattern("III")
                .pattern("ISI")
                .pattern("III")
                .unlockedBy("has_gold", has(Tags.Items.INGOTS_GOLD))
                .unlockedBy("has_furnace", has(Tags.Items.PLAYER_WORKSTATIONS_FURNACES))
                .save(output, ISConstants.rl("iron_to_gold_upgrade"));
        
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, ISItems.UPGRADES.get(Pair.of(SmelterType.DIAMOND, Optional.of(SmelterType.GOLD)))
                        .get())
                .define('I', Tags.Items.GEMS_DIAMOND)
                .define('S', Tags.Items.COBBLESTONES)
                .define('G', Tags.Items.GLASS_BLOCKS)
                .pattern("III")
                .pattern("GSG")
                .pattern("III")
                .unlockedBy("has_diamond", has(Tags.Items.GEMS_DIAMOND))
                .unlockedBy("has_furnace", has(Tags.Items.PLAYER_WORKSTATIONS_FURNACES))
                .save(output, ISConstants.rl("gold_to_diamond_upgrade"));
        
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, ISItems.UPGRADES.get(Pair.of(SmelterType.OBSIDIAN, Optional.of(SmelterType.DIAMOND)))
                        .get())
                .define('I', Tags.Items.OBSIDIANS_NORMAL)
                .define('S', Tags.Items.COBBLESTONES)
                .pattern("III")
                .pattern("ISI")
                .pattern("III")
                .unlockedBy("has_obsidian", has(Tags.Items.OBSIDIANS_NORMAL))
                .unlockedBy("has_furnace", has(Tags.Items.PLAYER_WORKSTATIONS_FURNACES))
                .save(output, ISConstants.rl("diamond_to_obsidian_upgrade"));
    }
    
    
    private void furnace(RecipeOutput recipeOutput, String from, String to, SmelterType output, TagKey<Item> ingot, ItemLike input) {
        
        outline(recipeOutput, from, to, "furnace", ISItems.FURNACES.get(output), ingot, input);
    }
    
    private void blastFurnace(RecipeOutput recipeOutput, String from, String to, SmelterType output, TagKey<Item> ingot, ItemLike input) {
        
        outline(recipeOutput, from, to, "blast_furnace", ISItems.BLAST_FURNACES.get(output), ingot, input);
    }
    
    private void smoker(RecipeOutput recipeOutput, String from, String to, SmelterType output, TagKey<Item> ingot, ItemLike input) {
        
        outline(recipeOutput, from, to, "smoker", ISItems.SMOKERS.get(output), ingot, input);
    }
    
    private void outline(RecipeOutput recipeOutput, String from, String to, String type, Supplier<Item> output, TagKey<Item> ingot, ItemLike input) {
        
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, output.get())
                .define('O', ingot)
                .define('I', input)
                .pattern("OOO")
                .pattern("OIO")
                .pattern("OOO")
                .unlockedBy("has_" + to, has(ingot))
                .save(recipeOutput, ISConstants.rl(from + "_to_" + to + "_" + type));
    }
    
    private void cheapOutline(RecipeOutput recipeOutput, String from, String to, String type, Supplier<Item> output, TagKey<Item> ingot, ItemLike input) {
        
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, output.get())
                .define('O', ingot)
                .define('G', Tags.Items.GLASS_BLOCKS)
                .define('I', input)
                .pattern("OOO")
                .pattern("GIG")
                .pattern("OOO")
                .unlockedBy("has_" + to, has(ingot))
                .save(recipeOutput, ISConstants.rl(from + "_to_" + to + "_" + type));
    }
    
}
