package com.reu_24.tat.recipe;

import com.reu_24.tat.TodayAndTomorrow;
import com.reu_24.tat.init.RecipeSerializerInit;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;

public class AlloyRecipe extends Recipe {

    public static ResourceLocation RECIPE_TYPE_ID = new ResourceLocation(TodayAndTomorrow.MOD_ID, "alloy");

    public AlloyRecipe(ResourceLocation id, NonNullList<Ingredient> inputs, ItemStack output) {
        super(id, inputs, output);
    }

    @Override
    public IRecipeType<?> getType() {
        return Registry.RECIPE_TYPE.getValue(RECIPE_TYPE_ID).get();
    }

    @Override
    public IRecipeSerializer<?> getSerializer() {
        return RecipeSerializerInit.ALLOY_SERIALIZER.get();
    }
}
