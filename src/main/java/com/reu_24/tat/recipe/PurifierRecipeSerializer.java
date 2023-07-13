package com.reu_24.tat.recipe;

public class PurifierRecipeSerializer extends RecipeSerializer<AlloyRecipe> {
    public PurifierRecipeSerializer() {
        super(PurifierRecipe::new, 1);
    }
}
