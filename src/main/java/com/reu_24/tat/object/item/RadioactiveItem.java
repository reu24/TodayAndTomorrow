package com.reu_24.tat.object.item;

import com.reu_24.tat.init.EffectInit;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.world.World;

public class RadioactiveItem extends Item {

    protected final int strength;

    public static final int AMPLIFIER_DIVISION = 16;
    public static final int TICKS_TO_APPLY = 5 * 20;

    public RadioactiveItem(Properties properties, int strength) {
        super(properties);
        this.strength = strength;
    }

    @Override
    public void inventoryTick(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
        if (!worldIn.isRemote()) {
            int amplifier = (stack.getCount() * strength) / AMPLIFIER_DIVISION - 1;
            if (entityIn instanceof LivingEntity && amplifier >= 0 && ((LivingEntity) entityIn).getActivePotionEffect(EffectInit.RADIOACTIVE.get()) == null) {
                ((LivingEntity) entityIn).addPotionEffect(new EffectInstance(EffectInit.RADIOACTIVE.get(), TICKS_TO_APPLY, amplifier));
            }
        }
    }
}
