package com.reu_24.tat.recipe;

public class AlloyRecipeSerializer extends RecipeSerializer<AlloyRecipe> {
    public AlloyRecipeSerializer() {
        super(AlloyRecipe::new, 2);
    }
}
