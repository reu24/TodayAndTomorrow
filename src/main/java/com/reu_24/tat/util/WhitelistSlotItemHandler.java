package com.reu_24.tat.util;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

import java.util.List;

public class WhitelistSlotItemHandler extends SlotItemHandler {

    private List<Item> whiteList;

    public WhitelistSlotItemHandler(IItemHandler itemHandler, int index, int xPosition, int yPosition, List<Item> whiteList) {
        super(itemHandler, index, xPosition, yPosition);

        this.whiteList = whiteList;
    }

    @Override
    public boolean isItemValid(ItemStack stack) {
        if (!super.isItemValid(stack)) {
            return false;
        }
        return whiteList.contains(stack.getItem());
    }
}
