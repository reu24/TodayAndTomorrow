package com.reu_24.tat.jei;

import com.reu_24.tat.init.BlockInit;
import com.reu_24.tat.init.RecipeSerializerInit;
import com.reu_24.tat.jei.category.AlloyRecipeCategory;
import com.reu_24.tat.jei.category.ElectrolysisRecipeCategory;
import com.reu_24.tat.jei.category.PurifierRecipeCategory;
import com.reu_24.tat.util.ModResourceLocation;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.helpers.IJeiHelpers;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@JeiPlugin
public class JEICustomPlugin implements IModPlugin {

    @Override
    public ResourceLocation getPluginUid() {
        return new ModResourceLocation("default");
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registry) {
        final IJeiHelpers helpers = registry.getJeiHelpers();
        final IGuiHelper gui = helpers.getGuiHelper();

        registry.addRecipeCategories(new AlloyRecipeCategory(gui));
        registry.addRecipeCategories(new ElectrolysisRecipeCategory(gui));
        registry.addRecipeCategories(new PurifierRecipeCategory(gui));
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        registration.addRecipes(getRecipesByType(RecipeSerializerInit.ALLOY_TYPE), new ModResourceLocation("alloy"));
        registration.addRecipes(Arrays.asList("hydrogen", "oxygen") , new ModResourceLocation("electrolysis"));
        registration.addRecipes(getRecipesByType(RecipeSerializerInit.PURIFIER_TYPE), new ModResourceLocation("purifier"));
    }

    protected List<JeiRecipe> getRecipesByType(IRecipeType<?> recipe) {
        World world = Minecraft.getInstance().world;
        List<JeiRecipe> recipes = new ArrayList<>();
        world.getRecipeManager().getRecipes().stream().filter(r -> r.getType() == recipe).forEach(r -> {
            recipes.add(new JeiRecipe(r.getIngredients(), r.getRecipeOutput()));
        });
        return recipes;
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
        registration.addRecipeCatalyst(new ItemStack(BlockInit.ALLOY_FURNACE.get()), new ModResourceLocation("alloy"));
        registration.addRecipeCatalyst(new ItemStack(BlockInit.ELECTROLYSIS_MACHINE.get()), new ModResourceLocation("electrolysis"));
        registration.addRecipeCatalyst(new ItemStack(BlockInit.PURIFIER.get()), new ModResourceLocation("purifier"));
    }
}
