package com.reu_24.tat.jei.category;

import com.reu_24.tat.init.BlockInit;
import com.reu_24.tat.jei.JeiRecipe;
import com.reu_24.tat.util.ModResourceLocation;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.drawable.IDrawableAnimated;
import mezz.jei.api.gui.drawable.IDrawableStatic;
import mezz.jei.api.gui.ingredient.IGuiItemStackGroup;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class PurifierRecipeCategory implements IRecipeCategory<JeiRecipe> {

    protected final ResourceLocation TEXTURE = new ModResourceLocation("textures/gui/electric_furnace.png");

    protected final IDrawableStatic arrowStatic;
    protected final IDrawableAnimated arrowAnimation;
    protected final IDrawableStatic energyFilled;
    protected final IDrawableStatic lightningFilled;

    protected final IDrawable background;
    protected final String name = "Purifier";
    protected final IDrawable icon;

    public PurifierRecipeCategory(IGuiHelper gui) {
        arrowStatic = gui.createDrawable(TEXTURE, 176, 81, 24, 23);
        arrowAnimation = gui.createAnimatedDrawable(arrowStatic, 300, IDrawableAnimated.StartDirection.LEFT, false);
        energyFilled = gui.createDrawable(TEXTURE, 176, 14, 12, 48);
        lightningFilled = gui.createDrawable(TEXTURE, 176, 0, 14, 14);

        background = gui.createDrawable(TEXTURE, 4, 4, 168, 78);
        icon = gui.createDrawableIngredient(new ItemStack(BlockInit.PURIFIER.get()));
    }

    @Override
    public ResourceLocation getUid() {
        return new ModResourceLocation("purifier");
    }

    @Override
    public Class<? extends JeiRecipe> getRecipeClass() {
        return JeiRecipe.class;
    }

    @Override
    public String getTitle() {
        return name;
    }

    @Override
    public IDrawable getBackground() {
        return background;
    }

    @Override
    public IDrawable getIcon() {
        return icon;
    }

    @Override
    public void setIngredients(JeiRecipe jeiRecipe, IIngredients iIngredients) {
        iIngredients.setInputIngredients(jeiRecipe.ingredients);
        iIngredients.setOutput(VanillaTypes.ITEM, jeiRecipe.output);
    }

    @Override
    public void setRecipe(IRecipeLayout recipeLayout, JeiRecipe jeiRecipe, IIngredients iIngredients) {
        IGuiItemStackGroup stacks = recipeLayout.getItemStacks();
        stacks.init(0, true, 51, 31);
        stacks.init(1, false, 112, 31);
        stacks.set(iIngredients);
    }

    @Override
    public void draw(JeiRecipe jeiRecipe, double mouseX, double mouseY) {
        arrowAnimation.draw(75, 32);
        energyFilled.draw(141, 14);
        lightningFilled.draw(53, 50);
    }
}
