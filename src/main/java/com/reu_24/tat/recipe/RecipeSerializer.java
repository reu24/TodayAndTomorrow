package com.reu_24.tat.recipe;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.items.wrapper.RecipeWrapper;
import net.minecraftforge.registries.ForgeRegistryEntry;

public abstract class RecipeSerializer<R extends IRecipe<RecipeWrapper>> extends ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<R> {

    protected final CreateRecipe createRecipe;
    protected final int numberOfInputs;

    public RecipeSerializer(CreateRecipe createRecipe, int numberOfInputs) {
        this.createRecipe = createRecipe;
        this.numberOfInputs = numberOfInputs;
    }

    @Override
    public R read(ResourceLocation recipeId, JsonObject json) {
        ItemStack output = CraftingHelper.getItemStack(JSONUtils.getJsonObject(json, "output"), true);
        JsonArray inputs = JSONUtils.getJsonArray(json, "input");
        NonNullList<Ingredient> inputsList = NonNullList.create();
        for (int i = 0; i < numberOfInputs; i++) {
            inputsList.add(Ingredient.deserialize(inputs.get(i)));
        }
        return (R) createRecipe.create(recipeId, inputsList, output);
    }

    @Override
    public R read(ResourceLocation recipeId, PacketBuffer buffer) {
        ItemStack output = buffer.readItemStack();
        NonNullList<Ingredient> inputsList = NonNullList.create();
        for (int i = 0; i < numberOfInputs; i++) {
            inputsList.add(Ingredient.read(buffer));
        }
        return (R) createRecipe.create(recipeId, inputsList, output);
    }

    @Override
    public void write(PacketBuffer buffer, R recipe) {
        for (Ingredient ingredient : recipe.getIngredients()) {
            ingredient.write(buffer);
        }
        buffer.writeItemStack(recipe.getRecipeOutput(), false);
    }


    public interface CreateRecipe {
        IRecipe<RecipeWrapper> create(ResourceLocation recipeId, NonNullList<Ingredient> inputs, ItemStack output);
    }

}
