package com.reu_24.tat.tilentity;

import com.reu_24.tat.util.BaseItemHandler;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.minecraftforge.items.wrapper.RecipeWrapper;

import java.util.*;
import java.util.stream.Collectors;

public abstract class ProcessingElectricityTileEntity<I extends BaseItemHandler> extends ElectricityGuiTileEntity<I> {

    public int currentProcessingTime;

    public static final String CURRENT_PROCESSING_TIME = "CurrentProcessingTime";

    public ProcessingElectricityTileEntity(TileEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn);
    }

    @Override
    public void read(CompoundNBT compound) {
        super.read(compound);
        currentProcessingTime = compound.getInt(CURRENT_PROCESSING_TIME);
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        super.write(compound);
        compound.putInt(CURRENT_PROCESSING_TIME, currentProcessingTime);
        return compound;
    }

    public IRecipe<RecipeWrapper> getRecipe() {
        Set<IRecipe<RecipeWrapper>> recipes = findRecipesByType(world);
        for (IRecipe<RecipeWrapper> iRecipe : recipes) {
            if (iRecipe.matches(new RecipeWrapper(inventory), world)) {
                return iRecipe;
            }
        }
        return null;
    }

    public Set<IRecipe<RecipeWrapper>> findRecipesByType(World world) {
        return (Set<IRecipe<RecipeWrapper>>)(world != null ? world.getRecipeManager().getRecipes().stream().filter(recipe -> isRecipe(recipe)).collect(Collectors.toSet()) : Collections.emptySet());
    }

    public Set<ItemStack> getAllRecipeInputs(World world) {
        Set<ItemStack> inputs = new HashSet<>();
        Set<IRecipe<RecipeWrapper>> recipes = findRecipesByType(world);
        for (IRecipe<?> recipe : recipes) {
            NonNullList<Ingredient> ingredients = recipe.getIngredients();
            ingredients.forEach(ingredient -> {
                for (ItemStack stack : ingredient.getMatchingStacks()) {
                    inputs.add(stack);
                }
            });
        }
        return inputs;
    }

    public List<Item> getRecipeInputs() {
        List<Item> items = new ArrayList<>();
        getAllRecipeInputs(world).stream().forEach(itemStack ->
                items.add(itemStack.getItem()));
        return items;
    }

    @Override
    public void tick() {
        super.tick();
        boolean dirty = false;

        if (world != null && !world.isRemote()) {
            if (shouldProcess()) {
                if (currentProcessingTime < getProcessingTime()) {
                    if (currentProcessingTime == 0) {
                        onStartProcessing();
                    }
                    onProcessing();
                    setLit(true);
                    currentProcessingTime++;
                    dirty = true;
                } else {
                    currentProcessingTime = 0;
                    IRecipe<RecipeWrapper> recipe = getRecipe();
                    if (recipe != null) {
                        inventory.insertItem(getOutputSlot(), recipe.getRecipeOutput().copy(), false);
                    }
                    onItemProcessed();
                }
            } else {
                currentProcessingTime = 0;
                setLit(false);
                onCanceledProcessing();
            }
        }

        if (dirty) {
            markDirty();
        }
    }

    protected boolean shouldProcess() {
        return energy.getEnergyStored() >= getEnergyNeededPerTick() && (getRecipe() != null);
    }

    protected void onStartProcessing() { }
    protected void onItemProcessed() { }
    protected void onProcessing() { }
    protected void onCanceledProcessing() { }

    public abstract int getProcessingTime();
    protected abstract int getEnergyNeededPerTick();
    protected abstract int getOutputSlot();

    protected abstract boolean isRecipe(IRecipe<?> recipe);
}
