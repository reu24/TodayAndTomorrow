package com.reu_24.tat.recipe;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.items.wrapper.RecipeWrapper;

public abstract class Recipe implements IRecipe<RecipeWrapper> {

    protected final ResourceLocation id;
    protected final NonNullList<Ingredient> inputs;
    protected final ItemStack output;

    public Recipe(ResourceLocation id, NonNullList<Ingredient> inputs, ItemStack output) {
        this.id = id;
        this.inputs = inputs;
        this.output = output;
    }

    @Override
    public boolean canFit(int width, int height) {
        return false;
    }

    @Override
    public boolean matches(RecipeWrapper inv, World worldIn) {
        for (Ingredient input : inputs) {
            boolean found = false;
            for (int i = 0; i < inv.getSizeInventory(); i++) {
                if (input.test(inv.getStackInSlot(i))) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                return false;
            }
        }
        return true;
    }

    @Override
    public ItemStack getCraftingResult(RecipeWrapper inv) {
        return output;
    }

    @Override
    public ItemStack getRecipeOutput() {
        return output;
    }

    @Override
    public ResourceLocation getId() {
        return id;
    }

    public NonNullList<Ingredient> getIngredients() {
        return inputs;
    }
}
