package com.reu_24.tat.jei;

import mezz.jei.api.JeiPlugin;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;

import java.util.List;

public class JeiRecipe {
    public List<Ingredient> ingredients;
    public ItemStack output;

    public JeiRecipe(List<Ingredient> ingredients, ItemStack output) {
        this.ingredients = ingredients;
        this.output = output;
    }
}
