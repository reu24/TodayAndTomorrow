package com.reu_24.tat.jei.category;

import com.reu_24.tat.init.BlockInit;
import com.reu_24.tat.init.ItemInit;
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

public class ElectrolysisRecipeCategory implements IRecipeCategory<String> {

    protected final ResourceLocation TEXTURE = new ModResourceLocation("textures/gui/electrolysis_machine.png");

    protected final IDrawableStatic arrowStatic;
    protected final IDrawableAnimated arrowAnimation;
    protected final IDrawableStatic energyFilled;
    protected final IDrawableStatic lightningFilled;
    protected final IDrawableStatic waterFilled;
    protected final IDrawableStatic hydrogenFilled;
    protected final IDrawableStatic oxygenFilled;

    protected final IDrawable background;
    protected final String name = "Electrolysis Machine";
    protected final IDrawable icon;

    public ElectrolysisRecipeCategory(IGuiHelper gui) {
        arrowStatic = gui.createDrawable(TEXTURE, 176, 117, 24, 23);
        arrowAnimation = gui.createAnimatedDrawable(arrowStatic, 300, IDrawableAnimated.StartDirection.LEFT, false);
        energyFilled = gui.createDrawable(TEXTURE, 176, 14, 12, 48);
        lightningFilled = gui.createDrawable(TEXTURE, 176, 0, 14, 14);
        waterFilled = gui.createDrawable(TEXTURE, 188, 14, 12, 48);
        hydrogenFilled = gui.createDrawable(TEXTURE, 200, 14, 12, 48);
        oxygenFilled = gui.createDrawable(TEXTURE, 212, 14, 12, 48);

        background = gui.createDrawable(TEXTURE, 4, 4, 168, 78);
        icon = gui.createDrawableIngredient(new ItemStack(BlockInit.ELECTROLYSIS_MACHINE.get()));
    }

    @Override
    public ResourceLocation getUid() {
        return new ModResourceLocation("electrolysis");
    }

    @Override
    public Class<? extends String> getRecipeClass() {
        return String.class;
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
    public void setIngredients(String recipe, IIngredients iIngredients) {
        if (recipe.equals("hydrogen")) {
            iIngredients.setOutput(VanillaTypes.ITEM, new ItemStack(ItemInit.HYDROGEN_BUCKET.get()));
        }
        if (recipe.equals("oxygen")) {
            iIngredients.setOutput(VanillaTypes.ITEM, new ItemStack(ItemInit.OXYGEN_BUCKET.get()));
        }
    }

    @Override
    public void setRecipe(IRecipeLayout recipeLayout, String recipe, IIngredients iIngredients) {
        IGuiItemStackGroup stacks = recipeLayout.getItemStacks();
        if (recipe.equals("hydrogen")) {
            stacks.init(0, false, 119, 17);
        }
        if (recipe.equals("oxygen")) {
            stacks.init(0, false, 119, 41);
        }
        stacks.set(iIngredients);
    }

    @Override
    public void draw(String recipe, double mouseX, double mouseY) {
        arrowAnimation.draw(41, 31);
        energyFilled.draw(141, 14);
        lightningFilled.draw(53, 60);
        waterFilled.draw(23, 14);
        hydrogenFilled.draw(71, 14);
        oxygenFilled.draw(105, 14);
    }
}
