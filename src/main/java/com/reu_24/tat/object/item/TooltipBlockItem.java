package com.reu_24.tat.object.item;

import com.reu_24.tat.util.helper.InputHelper;
import net.minecraft.block.Block;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Supplier;

public class TooltipBlockItem extends BlockItem {

    protected final Supplier<String> tooltip;
    protected final Supplier<String> shiftTooltip;
    protected final Supplier<String> controlTooltip;

    public TooltipBlockItem(Block blockIn, Properties builder, Supplier<String> tooltip, Supplier<String> shiftTooltip, Supplier<String> controlTooltip) {
        super(blockIn, builder);
        this.tooltip = tooltip;
        this.shiftTooltip = shiftTooltip;
        this.controlTooltip = controlTooltip;
    }

    @Override
    public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        super.addInformation(stack, worldIn, tooltip, flagIn);

        if (this.tooltip != null) {
            addTooltip(this.tooltip.get(), tooltip);
        }

        if (this.shiftTooltip != null) {
            if (InputHelper.isHoldingShift()) {
                addTooltip(shiftTooltip.get(), tooltip);
            } else {
                tooltip.add(new StringTextComponent("Hold " + "\u00A76" + "Shift" + "\u00A7f" + " for description."));
            }
        }

        if (this.controlTooltip != null) {
            if (InputHelper.isHoldingControl()) {
                addTooltip(controlTooltip.get(), tooltip);
            } else {
                tooltip.add(new StringTextComponent("Hold " + "\u00A76" + "Control" + "\u00A7f" + " for stats."));
            }
        }
    }

    protected void addTooltip(String toAdd, List<ITextComponent> tooltip) {
        AtomicReference<String> currentAdd = new AtomicReference<>("");
        toAdd.chars().forEach((c) -> {
            if (c == '\n') {
                tooltip.add(new StringTextComponent(currentAdd.get()));
                currentAdd.set("");
            } else {
                currentAdd.updateAndGet(v -> v + (char) c);
            }
        });
        tooltip.add(new StringTextComponent(currentAdd.get()));
    }
}
