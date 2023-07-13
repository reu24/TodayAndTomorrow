package com.reu_24.tat.itemgroup;

import com.reu_24.tat.init.BlockInit;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class TodayAndTomorrowItemGroup extends ItemGroup {

    public static final TodayAndTomorrowItemGroup INSTANCE = new TodayAndTomorrowItemGroup(ItemGroup.GROUPS.length, "today_and_tomorrow_item_group");

    protected TodayAndTomorrowItemGroup(int index, String label) {
        super(index, label);
    }

    @Override
    public ItemStack createIcon() {
        return new ItemStack(BlockInit.ALLOY_FURNACE_ITEM.get());
    }
}
