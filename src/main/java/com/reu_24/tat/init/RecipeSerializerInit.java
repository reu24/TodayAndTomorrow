package com.reu_24.tat.init;

import com.reu_24.tat.TodayAndTomorrow;
import com.reu_24.tat.recipe.AlloyRecipe;
import com.reu_24.tat.recipe.AlloyRecipeSerializer;
import com.reu_24.tat.recipe.PurifierRecipe;
import com.reu_24.tat.recipe.PurifierRecipeSerializer;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class RecipeSerializerInit {

    public static final DeferredRegister<IRecipeSerializer<?>> RECIPE_SERIALIZERS = new DeferredRegister<>(ForgeRegistries.RECIPE_SERIALIZERS, TodayAndTomorrow.MOD_ID);

    public static <T extends IRecipeType> T registerType(ResourceLocation recipeTypeId) {
        return (T)Registry.register(Registry.RECIPE_TYPE, recipeTypeId, new RecipeType<>());
    }

    private static class RecipeType<T extends IRecipe<?>> implements IRecipeType<T> {
        @Override
        public String toString() {
            return Registry.RECIPE_TYPE.getKey(this).toString();
        }
    }

    public static final IRecipeType<AlloyRecipe> ALLOY_TYPE = registerType(AlloyRecipe.RECIPE_TYPE_ID);
    public static final RegistryObject<AlloyRecipeSerializer> ALLOY_SERIALIZER = RECIPE_SERIALIZERS.register("alloy", AlloyRecipeSerializer::new);

    public static final IRecipeType<PurifierRecipe> PURIFIER_TYPE = registerType(PurifierRecipe.RECIPE_TYPE_ID);
    public static final RegistryObject<PurifierRecipeSerializer> PURIFIER_SERIALIZER = RECIPE_SERIALIZERS.register("purifier", PurifierRecipeSerializer::new);
}
